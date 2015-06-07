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
import de.tammon.dev.mdc.server.service.SAPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by tammschw on 12/05/15.
 */
@Controller
public class OrderController extends AbstractMdcController {

    private static final String ORDER_PAGE = "order";
    private static final String REDIRECT_TO_INDEX = "redirect:/";
    private static final String REDIRECT_TO_ORDER = "redirect:/order";
    @Autowired
    DatabaseService databaseService;
    @Autowired
    SAPService sapService;

    /**
     * processes the standard order view and the order success and fail page.
     *
     * @return the view "template" with the right attributes in the model according to the Request Parameters
     */
    @RequestMapping(value = {"/order", "/submitorder"}, method = RequestMethod.GET)
    public String servePageOrder(Customer customer,
                                 Locale locale) {
        // no parameters provided or parameters not plausible (e.g. !submitted && success) --> providing normal order page
        pageModel.clear();
        pageModel.setTitle(messageSource.getMessage("order.page.title", null, locale));
        return ORDER_PAGE;
    }

    /**
     * processes a submitted order via a POST method (does not process any request with a GET method)
     *
     * @param customer is set by the submitting form
     * @param product  is set by the submitting form
     * @return a redirection to order with set parameters according to the success of the submit
     */
    @RequestMapping(value = "/submitorder", method = RequestMethod.POST)
    public String submitOrder(@ModelAttribute @Valid Customer customer,
                              BindingResult bindingResult,
                              Product product,
                              Locale locale) {

        if (bindingResult.hasErrors()) return ORDER_PAGE;

        pageModel.clear();

        if (!databaseService.doExist(product)) {

            if (!databaseService.doExist(customer)) customer.setCustomerId(sapService.getCustomerId(customer));
            else customer = databaseService.getCustomerByEmail(customer.getEmail());

            Order order = new Order(customer, product);
            order.setOrderId(sapService.getOrderId(order));

            databaseService.save(customer, product, order);

            pageModel.orderSucceeded();
            pageModel.setTitle(messageSource.getMessage("order.orderSuccess.attributeName", null, locale));
            pageModel.doNotClearOnNextUse();

            return REDIRECT_TO_INDEX;
        } else {
            pageModel.setTitle(messageSource.getMessage("order.orderFailed.attributeName", null, locale));
            pageModel.orderFailed();
            pageModel.doNotClearOnNextUse();
            return REDIRECT_TO_ORDER;
        }
    }

    @ModelAttribute("pageModel")
    public PageModel getPageModel() {
        return pageModel;
    }
}
