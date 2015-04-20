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

package de.tammon.dev.in4p.pots.model;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by tammschw on 12/04/15.
 */
@Component
public class Product extends AbstractDocument {

    private String productName;
    private String externalProductId;
    private String productType;
    private String productionId;
    private String productionIdPos;
    private List<ProductionParameter> productionParameters;

    public Product() {
    }

    /**
     * Creates a new instance of {@link Product}
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
     * Set the {@link Product} ID
     * @param productId new {@link Product}
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * Get Product Type
     * @return productType
     */
    public String getProductType() {
        return productType;
    }

    public String getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productId='" + productId + '\'' +
                ", productType='" + productType + '\'' +
                ", productionId='" + productionId + '\'' +
                ", productionIdPos='" + productionIdPos + '\'' +
                ", productionParameters=" + productionParameters +
                '}';
    }
}
