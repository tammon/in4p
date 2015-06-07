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

package de.tammon.dev.mdc.server.service;

import de.tammon.dev.mdc.server.model.Customer;
import de.tammon.dev.mdc.server.model.Order;
import org.springframework.stereotype.Service;

/**
 * Created by tammschw on 02/06/15.
 */
@Service
public class SAPService {

    public String getCustomerId (Customer customer) {
        return "1234";
    }

    public String getOrderId (Order order) {
        return "5678";
    }
}
