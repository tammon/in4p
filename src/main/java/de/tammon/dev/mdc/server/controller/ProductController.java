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
import de.tammon.dev.mdc.server.model.PageModel;
import de.tammon.dev.mdc.server.model.Product;
import de.tammon.dev.mdc.server.service.DatabaseService;
import de.tammon.dev.mdc.server.service.HydraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

/**
 * Created by tammschw on 10/05/15.
 */
@Controller
public class ProductController extends AbstractMdcController {

    private static final String PRODUCT_PAGE = "product";

    @Autowired
    DatabaseService databaseService;

    @Autowired
    HydraService hydraService;

    @RequestMapping(value = {"/product", "/fim"}, method = RequestMethod.GET)
    public String servePageProduct(Model model,
                                   String id,
                                   Locale locale) {
        pageModel.clear();
        Product product;
        Order order;
        Customer customer;

        if (id == null) product = getExampleProduct();
        else {
            if ((product = databaseService.getProductByExternalProductId(id)) != null) {

                // Get order and customer from database selected by containing product
                // add them to model if they were found in database
                if ((order = databaseService.getOrderByContainingProduct(product)) != null) {
                    model.addAttribute(order);
                    if ((customer = order.getCustomer()) != null) {
                        model.addAttribute(customer);
                        model.addAttribute("customerTitle", getCustomerTitle(customer, locale));
                    }
                }
            } else {
                if ((product = hydraService.getProductFromHydraById(id)) != null)
                    if (!product.noSimpleProdParams())
                        databaseService.save(product);
            }
        }

        if (product != null) model.addAttribute(product);
        pageModel.setTitle(messageSource.getMessage("product.pageTitle", null, locale));

        return PRODUCT_PAGE;
    }

    private Product getExampleProduct() {
        Product product = new Product();
        product.setProductName("Taschenlampe");
        product.setProductType("TASCHENLAMPE");
        return product;
    }

    private String getCustomerTitle(Customer customer,
                                    Locale locale) {
        switch (customer.getGender()) {
            case MALE:
                return messageSource.getMessage("customer.title.male", null, locale);
            case FEMALE:
                return messageSource.getMessage("customer.title.female", null, locale);
            default:
                return null;
        }
    }

    @ModelAttribute("pageModel")
    public PageModel getPageModel() {
        return pageModel;
    }
}
