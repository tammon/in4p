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

import de.tammon.dev.mdc.server.model.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tammschw on 28/05/15.
 */
@Service
public class DatabaseService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;


    /*
    GENERAL DATABASE FUNCTIONALITIES
     */

    public void resetDb () {
//        TODO: Exception Handling AOP
        mongoTemplate.getDb().dropDatabase();
    }

    public void save(Object... objects) {
        for (Object object : objects) {
            if (object instanceof Order) orderRepository.save((Order) object);
            if (object instanceof Customer) customerRepository.save((Customer) object);
            if (object instanceof Product) productRepository.save((Product) object);
        }
    }

    /**
     * Check if the provided Object is already in database.
     * @param objects will be checked if it is already stored in db
     * @return returns true if it is a duplicate
     */
    public boolean isDuplicateInDatabase(Object... objects) {
        boolean result = false;
        for (Object object : objects) {
            if (object instanceof Order) {
                Order order = (Order) object;
                if (getOrderByOrderId(order.getOrderId()) != null) result |= true;
                else result |= false;
            }
            if (object instanceof Customer) {
                Customer customer = (Customer) object;
                if (getCustomerByEmail(customer.getEmail()) != null) result |= true;
                else result |= false;
            }
            if (object instanceof Product) {
                Product product = (Product) object;
                if ( ((getProductByExternalProductId(product.getExternalProductId()) != null) && product.getExternalProductId() != null)
                        || (getProductByProductionIdAndPosition(product.getProductionId(), product.getProductionIdPos()) != null)
                            && product.getProductionId() != null
                            && product.getProductionId() != null)
                    result |= true;
                else result |= false;
            }
        }
        return result;
    }

    /*
    ORDER AREA
     */

    public Order getOrderByObjectId(String orderObjectId) {
        return orderRepository.findOne(orderObjectId);
    }

    public Order getOrderByContainingProduct (Product product) {
        return orderRepository.findByProductsContaining(product);
    }

    public Order getOrderByOrderId (String orderId) {
        return orderRepository.getByOrderId(orderId);
    }


    /*
    PRODUCT AREA
     */

    public Product getProductByName(String productName) {
        return productRepository.getByProductName(productName);
    }

    public Product getProductByObjectId(String productObjectId) {
        return productRepository.findOne(productObjectId);
    }

    public Product getProductByExternalProductId (String id) {
        Product product = productRepository.getByExternalProductId(id);
        return product;
    }

    public Product getProductByProductionIdAndPosition (String productionId, String position) {
        return productRepository.getByProductionIdAndPosition(productionId, position);
    }


    /*
    CUSTOMER AREA
     */

    public Customer getCustomerByCustomerObjectId(String customerObjectId) {
        return customerRepository.findOne(customerObjectId);
    }

    public Customer getCustomerByCustomerId(String customerId) {
        return customerRepository.findByCustomerId(customerId);
    }

    public List<Customer> getListOfCustomersByLastName (String customerLastName) {
        return customerRepository.findByLastName(customerLastName);
    }

    public Customer getCustomerByEmail(String Email) {
        return customerRepository.getByEmail(Email);
    }
}
