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

package de.tammon.dev.mdc.server.controller.api.rest;

import de.tammon.dev.mdc.server.model.Product;
import de.tammon.dev.mdc.server.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tammschw on 07/06/15.
 */
@RestController
@RequestMapping(value = {"/api/fim/{id}", "/api/product/{id}"})
public class ProductRestController {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Product> getProduct (@PathVariable String id) {
        Product product = databaseService.getProductByExternalProductId(id);
        System.out.println(product);
        if (product != null) return new ResponseEntity<>(product, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
