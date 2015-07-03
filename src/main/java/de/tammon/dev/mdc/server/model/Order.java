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

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by tammschw on 12/04/15.
 */
@Component
public class Order extends AbstractDocument {

    @NotNull
    private String orderId;

    @DBRef (lazy = true)
    private Customer customer;

    @DBRef (lazy = true)
    private List<Product> products;

    private Date submitDate, lastUpdated;


    public Order() {
        this.submitDate = new Date();
    }

    /**
     * Creates a new order for a {@link Customer} with a minimum of 2 {@link Product}s
     * @param customer specify the {@link Customer} who has submitted the {@link Order}
     * @param products give a List of {@link Product}s which will be assigned to the {@link Order}
     */
    public Order(Customer customer, List<Product> products) {
        this.customer = customer;
        this.products = products;
        this.submitDate = new Date();
    }

    /**
     * Creates a new order for a {@link Customer} with only 1 {@link Product}
     * @param customer specify the {@link Customer} who has submitted the {@link Order}
     * @param product specify the {@link Product} that is contained in this {@link Order}
     */
    public Order(Customer customer, Product product) {
        this.addProduct(product);
        this.customer = customer;
    }

    /**
     * Set the orderId (should match orderIds in all other subsystems, such as SAP)
     * @param orderId order identification number (most cases from subsystem)
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * Adds a List of {@link Product}s to the {@link Order}
     * @param products {@link List} of {@link Product}s to be added
     */
    public void addListOfProducts(List<Product> products) {
        products.forEach(this::addProduct);
    }

    public void addProducts(Product... products) {
        Arrays.stream(products).forEach(this::addProduct);
    }

    public void addProduct (Product product){
        if (this.products == null) this.products = new ArrayList<>();
        this.products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customer=" + customer +
                ", products=" + products +
                '}';
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getOrderId() {
        return orderId;
    }

    public void updated() {
        this.lastUpdated = new Date();
    }
}
