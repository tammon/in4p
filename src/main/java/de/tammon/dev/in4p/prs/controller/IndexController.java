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

package de.tammon.dev.in4p.prs.controller;

import de.tammon.dev.in4p.pots.model.Order;
import de.tammon.dev.in4p.pots.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

/**
 * Created by tammschw on 12/04/15.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    Order order;

    @Autowired
    Product product, product1, product2;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        product.setProductName("test1");
        product.setProductType("te1");
        product.setProductId("1");
        product1.setProductName("test2");
        product1.setProductType("te1");
        product2.setProductName("test2");
        product2.setProductType("te1");

        List<Product> products = new ArrayList<Product>();
        products.add(product);
        products.add(product1);
        products.add(product2);

        order.addProduct(product);
        order.addProducts(products);
        System.out.println(order.getProducts());
        return "index";
    }
}
