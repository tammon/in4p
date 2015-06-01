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
import de.tammon.dev.mdc.server.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by tammschw on 12/05/15.
 */
@Controller
public class OrderController {

    /**
     * processes the standard order view and the order success and fail page.
     * @param model is processed by Thymeleaf in the view
     * @param submitted Request Parameter that sets if a order was submitted before requesting this site
     * @param success Request Parameter that sets if the order process of a previously submitted order was successful
     * @return the view "template" with the right attributes in the model according to the Request Parameters
     */
    @RequestMapping("/order")
    public String servePageOrder (Model model,
                         @RequestParam(required = false, defaultValue = "false") Boolean submitted,
                         @RequestParam(required = false, defaultValue = "false") Boolean success) {

        // in case order was submitted and the order process was successful
        if(submitted && success){
            model.addAttribute("title", "Bestellung erfolgreich");
            return "order-success";
        } else {
            // in case order was submitted but the order process was not successful
            if (submitted && !success){
                model.addAttribute("title", "Bestellung fehlgeschlagen");
                return "order";
            } else {
                // no parameters provided or parameters not plausible (e.g. !submitted && success) --> providing normal order page
                model.addAttribute("title", "Bestellen Sie Ihre Taschenlampe");
                return "order";
            }
        }
    }

    /**
     * processes a submitted order via a POST method (does not process any request with a GET method)
     * @param model is processed by Thymeleaf in the view
     * @param customer is set by the submitting form
     * @param product is set by the submitting form
     * @return a redirection to order with set parameters according to the success of the submit
     */
    @RequestMapping(value = "/submitorder", method = RequestMethod.POST)
    public String submitOrder (Model model, Customer customer, Product product) {
        if (customer == null || product == null) return "redirect:/order?submitted=true&success=false";

        System.out.println(customer.toString());
        System.out.println(product.toString());
        return "redirect:/order?submitted=true&success=true";
    }
}
