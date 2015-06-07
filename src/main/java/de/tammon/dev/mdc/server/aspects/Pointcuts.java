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

package de.tammon.dev.mdc.server.aspects;

import de.tammon.dev.mdc.server.model.Customer;
import de.tammon.dev.mdc.server.model.Product;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 * Created by tammschw on 02/06/15.
 */
@Aspect
@Component
public class Pointcuts {

    @Pointcut("execution(* de.tammon.dev.mdc.server.service.DatabaseService.*(Object))")
    public void allObjectAcceptingDatabaseMethods() {
    }

    /**
     * Pointcut
     * Declares pointcut to all controller methods that serve Thymeleaf pages
     *
     * @param model {@link Model} that is parsed to Thymeleaf
     */
    @Pointcut("execution(String de.tammon.dev.mdc.server.controller..*.servePage*(..)) && args(model)")
    public void servePage(Model model) {
    }

    /**
     * Pointcut
     * Declares pointcut to product controller method that serve Thymeleaf page
     *
     * @param id external production ID referred by QR-Code
     */
    @Pointcut("execution(String de.tammon.dev.mdc.server.controller..*.servePageProduct(..)) && args(id)")
    public void serveProductPage(String id) {
    }

    @Pointcut("execution(* de.tammon.dev.mdc.server.service.*.save(..))")
    public void saveToDatabase() {
    }

    @Pointcut("execution(* de.tammon.dev.mdc.server.service.DatabaseService.get*(..))")
    public void allDatabaseGetMethods() {
    }

    @Pointcut("execution(String de.tammon.dev.mdc.server.controller.OrderController.submitOrder(..)) && args(customer, bindingResult, product)")
    public void submitOrderHandler(Customer customer, BindingResult bindingResult, Product product) {
    }

    @Pointcut("execution(* de.tammon.dev.mdc.server.controller.api.rest.*.get*(..))")
    public void allRestGetMethods () {}
}
