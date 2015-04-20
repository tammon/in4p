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

package de.tammon.dev.in4p.pots.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tammschw on 13/04/15.
 */
public class ProductionParameter {

    private String name;
    private String unit;
    private Map<String, Double> parameterSet;


    /**
     * creates a new {@link ProductionParameter} object with the given name.
     * @param name Name of Parameter set must not be {@literal null}
     */
    public ProductionParameter(String name) {
        this.name = name;
    }

    /**
     * Adds a new key - value pair to the parameterSet
     * @param key unique x-axis value (e.g. time)
     * @param value y-axis value (e.g. pressure)
     */
    public void addParameter(String key, Double value) {
        parameterSet.put(key, value);
    }
}
