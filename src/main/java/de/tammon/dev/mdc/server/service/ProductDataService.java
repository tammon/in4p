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
import org.springframework.stereotype.Service;

/**
 * Created by tammschw on 07/06/15.
 */
@Service
public class ProductDataService {

    /**
     * This function does the complete refresh and initialization of production processes.
     * Just hand over the product and the function will adapt the product and return the refreshed product.
     * @param product product that needs to be refreshed from production subsystems
     * @return refreshed product or original product in case of failure
     */
    public Product refreshProduct(Product product) {
        // use new object to refresh. if refresh fails, the original product will be returned
        Product refreshedProduct = product;
        return refreshedProduct;
    }
}
