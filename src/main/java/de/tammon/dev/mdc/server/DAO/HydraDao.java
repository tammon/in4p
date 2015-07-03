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

package de.tammon.dev.mdc.server.DAO;

import de.tammon.dev.mdc.server.model.SimpleProductionParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tammschw on 19/06/15.
 */
@Component
public class HydraDao {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    static public final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";

    @Autowired
    RestTemplate restTemplate;

    String listType, portNumber, hostName, usrNumber;

    public List<SimpleProductionParameter> getSimpleProductionParametersByOrderId(String id) {
        String dataList;
        if ((dataList = getListByOrderId(id)) == null) return null;

        List<SimpleProductionParameter> simpleProductionParameters = new ArrayList<>();

        String[] headerValueSplitter = dataList.split("(?<=PRUEFSTAT:FARBE=\\|)", -1);

        String[] header = headerValueSplitter[0].split("\\|");
        String[] values = headerValueSplitter[1].split("\\|", -1);

        try {
            for (int i = 0; i < values.length; i++) {
                SimpleProductionParameter simpleProductionParameter = new SimpleProductionParameter();
                simpleProductionParameter.setAttributeName((header[i].split("="))[0].trim());
                simpleProductionParameter.setName((header[i].split("="))[1].trim());
                simpleProductionParameter.setValue(values[i].trim());

                if (checkIsNumericAndIsNotEmpty(values[i])) simpleProductionParameters.add(simpleProductionParameter);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.error(e.toString());
        }

        return simpleProductionParameters;
    }

    private String getListByOrderId(String id) {
        try {
            return restTemplate.getForObject(
                    "http://" + hostName + ":" + portNumber + "/api/?dlg={DLG}",
                    String.class,
                    "LIST;" + listType + "|DAT=today|ZEI=now|USR=" + usrNumber + "|MOD=A|ANR=" + id);
        } catch (ResourceAccessException e) {
            logger.error(e.toString());
            return null;
        }
    }

    private boolean checkIsNumericAndIsNotEmpty(String value) {
        double d;
        try {
            d = Double.parseDouble(value);
        } catch (NumberFormatException nfe) {
            return !value.isEmpty();
        }
        return (d != 0);
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getUsrNumber() {
        return usrNumber;
    }

    public void setUsrNumber(String usrNumber) {
        this.usrNumber = usrNumber;
    }
}
