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
import de.tammon.dev.mdc.server.model.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tammschw on 28/05/15.
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    private void save(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer getCustomerByCustomerObjectId(String customerObjectId) {
        return customerRepository.findOne(customerObjectId);
    }

    public Customer getCustomerByCustomerId(String customerId) {
        return customerRepository.findByCustomerId(customerId);
    }

    public List<Customer> getCustomersByLastName (String customerLastName) {
        return customerRepository.findByLastName(customerLastName);
    }

}
