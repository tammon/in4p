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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tammschw on 12/05/15.
 */
@Controller
public class OrderController {

    @RequestMapping("/order")
    public String order (Model model) {
        model.addAttribute("container", "order");
        model.addAttribute("title", "Bestellen Sie Ihre Taschenlampe");
        return "template";
    }

    @RequestMapping(value = "/submitorder", method = RequestMethod.POST)
    public String submitOrder (Model model, Customer customer, Product product) {
        System.out.println(customer.toString());
        System.out.println(product.toString());
        model.addAttribute("container", "order");
        model.addAttribute("title", "Bestellen Sie Ihre Taschenlampe");
        return "template";
    }
}
