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

import de.tammon.dev.mdc.server.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tammschw on 21/06/15.
 */
@Service
@Configuration
@EnableScheduling
public class UpdateService {

    @Autowired
    DatabaseService databaseService;

    @Autowired
    HydraService hydraService;

    @Scheduled(fixedRate = 20000)
    public void updateProducts(){
        List<Product> products = databaseService.getAllProducts();
        products.stream().forEach(product -> {
            if (!product.isFinal()) {
                Product newProduct;
                if ((newProduct = hydraService.getProductFromHydraById(product.getExternalProductId())) != null) {
                    newProduct.setId(product.getId());
                    databaseService.save(newProduct);
                }
            }
        });
    }

}
