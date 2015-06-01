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

package de.tammon.dev.mdc.server.controller;

import de.tammon.dev.mdc.server.model.Customer;
import de.tammon.dev.mdc.server.model.Order;
import de.tammon.dev.mdc.server.model.Product;
import de.tammon.dev.mdc.server.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tammschw on 10/05/15.
 */
@Controller
public class ProductController {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = {"/product","/fim"}, method = RequestMethod.GET)
    public String servePageProduct(Model model, String id) {
        Product product;

        if (id != null && (product = databaseService.getProductByExternalProductId(id)) != null) {
            Order order;
            Customer customer;

            // Get order and customer from database selected by containing product
            // add them to model if they were found in database
            if ((order = databaseService.getOrderByContainingProduct(product)) != null) model.addAttribute(order);
            if ((customer = order.getCustomer()) != null) {
                model.addAttribute(customer);
                model.addAttribute("customerTitle", getCustomerTitle(customer));
            }
        } else product = getExampleProduct();


        model.addAttribute(product);
        model.addAttribute("title", "Ihre Produktdaten");

        return "product";
    }

    private Product getExampleProduct() {
        Product product = new Product();
        product.setProductName("Taschenlampe");
        product.setProductType("1");
        return product;
    }

    private String getCustomerTitle(Customer customer) {
        switch (customer.getGender()) {
            case MALE: return "Herr";
            case FEMALE: return "Frau";
            default: return null;
        }
    }
}
