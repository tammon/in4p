/*
 * Copyright (c) 2015. Tammo Schwindt (Tammon) <dev@tammon.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.tammon.dev.mdc.server.service;

import de.tammon.dev.mdc.server.DAO.HydraDao;
import de.tammon.dev.mdc.server.model.Product;
import de.tammon.dev.mdc.server.model.SimpleProductionParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by tammschw on 20/06/15.
 */
@Service
public class HydraService {

    @Value("${mpdv.hydra.host.name}")
    String hostName;

    @Value("${mpdv.hydra.host.restPort}")
    String portNumber;

    @Value("${mpdv.hydra.usrNumber}")
    String usrNumber;

    @Value("${mpdv.hydra.listType}")
    String listType;

    @Value("${mpdv.hydra.productionParametersToInclude}")
    String parametersToIncludeRaw;

    @Autowired
    HydraDao hydraDao;

    public void setHydraSettings() {
        hydraDao.setHostName(hostName);
        hydraDao.setListType(listType);
        hydraDao.setPortNumber(portNumber);
        hydraDao.setUsrNumber(usrNumber);
    }

    public List<SimpleProductionParameter> getSimpleProductionParametersFromHydraByOrderId(String id) {
        return hydraDao.getSimpleProductionParametersByOrderId(id);
    }

    public Product getProductFromHydraById(String id) {
        Product product = new Product();

        String[] parametersToInclude = getProductionParametersToInclude();

        List<SimpleProductionParameter> simpleProductionParameters;
        if ((simpleProductionParameters = getSimpleProductionParametersFromHydraByOrderId(id)) == null)
            return null;

        simpleProductionParameters.stream().forEach(simpleProductionParameter -> {
            String value = simpleProductionParameter.getValue();

            switch (simpleProductionParameter.getAttributeName()) {
                case "ANR":
                    product.setExternalProductId(value);
                    break;
                case "AUNR":
                    product.setProductionId(value);
                    break;
                case "AGNR":
                    product.setProductionIdPos(value);
                    break;
                case "ATKBEZ":
                    product.setProductName(value);
                    break;
                case "ATK":
                    product.setProductType(value);
                    break;
                case "ANR_ANZEIB":
                    SimpleDateFormat simpleTimeZone = new SimpleDateFormat("HH:mm:ss");
                    simpleTimeZone.setTimeZone(TimeZone.getTimeZone("GMT"));
                    simpleProductionParameter.setValue(
                            simpleTimeZone.format(
                                    new Date(Integer.parseInt(value) * 1000)));
                    simpleProductionParameter.setUnit("Uhr");
                    break;
                case "ANR_ANDATB":
                    simpleProductionParameter.setValue(value.replace("/", "."));
                    break;
                case "ASTTXT":
                    simpleProductionParameter.setName("Status");
                    if (value.equals("beendet")) product.setFinal();
                    break;
                case "EGR:DAUER":
                    simpleProductionParameter.setUnit("s");
                    break;
            }

            for (String parameter : parametersToInclude)
                if (simpleProductionParameter.getAttributeName().equals(parameter))
                    product.addSimpleProductionParameter(simpleProductionParameter);
        });

        return product.noSimpleProdParams() ? null : product;
    }

    private String[] getProductionParametersToInclude() {
        return parametersToIncludeRaw.split(",");
    }
}
