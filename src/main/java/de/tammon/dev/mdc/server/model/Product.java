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

package de.tammon.dev.mdc.server.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tammschw on 12/04/15.
 */
@Component
public class Product extends AbstractDocument {

    private String productName;

    /**
     * Product ID that is referenced by the QR Code
     * Does not have to be necessarily the same like the productionId although in most cases they will match
     */
    private String externalProductId;

    @NotNull
    private String productType;

    /**
     * The Date on which this Product was last updated from subsystems
     */
    private Date lastUpdated;

    /**
     * ID and Position that is used during the production process
     */
    private String productionId;
    private String productionIdPos;

    /**
     * Serial number
     */
    private String serialnumber;

    /**
     * A list of assigned simple production parameters
     */
    private List<SimpleProductionParameter> simpleProductionParameters;

    /**
     * A list of assigned X-Y production parameter sets
     */
    private List<XYProductionParameter> xyProductionParameters;

    public Product() {
    }

    /**
     * Creates a new instance of {@link Product}
     *
     * @param productName specify the product's name
     */
    public Product(String productName, String productType) {
        this.productName = productName;
        this.productType = productType;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * Get the {@link Product} ID
     */
    public String getProductionId() {
        return productionId;
    }

    /**
     * Get Product Type
     *
     * @return productType
     */
    public String getProductType() {
        return productType;
    }

    /**
     * Get the external Product ID (reffered by QR Code)
     *
     * @return {@link String} externalProductId
     */
    public String getExternalProductId() {
        return externalProductId;
    }

    /**
     * Set the externalProductId that is reverenced by the QR Code
     *
     * @param externalProductId QR Code Id reference
     */
    public void setExternalProductId(String externalProductId) {
        this.externalProductId = externalProductId;
    }

    /**
     * Set the productionId of the product in the MES
     *
     * @param productionId MES productionId
     */
    public void setProductionId(String productionId) {
        this.productionId = productionId;
    }

    /**
     * Set the position number of the production identification number in the MES
     *
     * @param productionIdPos position number of production in MES
     */
    public void setProductionIdPos(String productionIdPos) {
        this.productionIdPos = productionIdPos;
    }

    /**
     * Adds a simple production parameter to the product
     *
     * @param simpleProductionParameter simple production parameter of the product
     */
    public void addSimpleProductionParameter(SimpleProductionParameter simpleProductionParameter) {
        if (this.simpleProductionParameters == null) this.simpleProductionParameters = new ArrayList<>();
        this.simpleProductionParameters.add(simpleProductionParameter);
    }

    /**
     * Adds a single X-Y production parameters to the product
     *
     * @param xyProductionParameter X-Y production parameter of the product
     */
    public void addXyProductionParameter(XYProductionParameter xyProductionParameter) {
        if (this.xyProductionParameters == null) this.xyProductionParameters = new ArrayList<>();
        this.xyProductionParameters.add(xyProductionParameter);
    }

    /**
     * Adds a list of simple production parameters to the product
     *
     * @param simpleProductionParameters List of simple production parameters of the product
     */
    public void addListOfSimpleProductionParameters(List<SimpleProductionParameter> simpleProductionParameters) {
        simpleProductionParameters.forEach(this::addSimpleProductionParameter);
    }

    /**
     * Adds a list of X-Y production parameters to the product
     *
     * @param xyProductionParameters List of X-Y production parameters of the product
     */
    public void addListOfXyProductionParameters(List<XYProductionParameter> xyProductionParameters) {
        xyProductionParameters.forEach(this::addXyProductionParameter);
    }

    public String getProductName() {
        return productName;
    }

    public String getProductionIdPos() {
        return productionIdPos;
    }

    public List<SimpleProductionParameter> getSimpleProductionParameters() {
        return simpleProductionParameters;
    }

    public List<XYProductionParameter> getXyProductionParameters() {
        return xyProductionParameters;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public void updated() {
        this.lastUpdated = new Date();
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", externalProductId='" + externalProductId + '\'' +
                ", productType='" + productType + '\'' +
                ", lastUpdated=" + lastUpdated +
                ", productionId='" + productionId + '\'' +
                ", productionIdPos='" + productionIdPos + '\'' +
                ", serialnumber='" + serialnumber + '\'' +
                ", simpleProductionParameters=" + simpleProductionParameters +
                ", xyProductionParameters=" + xyProductionParameters +
                '}';
    }
}
