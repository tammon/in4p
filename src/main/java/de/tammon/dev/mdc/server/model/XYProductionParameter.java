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

package de.tammon.dev.mdc.server.model;

import java.util.Map;

/**
 * Created by tammschw on 01/06/15.
 */
public class XYProductionParameter {

    private Map<String, Double> xyParameterSet;

    /**
     * Adds a new key - value pair to the parameterSet
     * @param key unique x-axis value (e.g. time)
     * @param value y-axis value (e.g. pressure)
     */
    public void addParameter(String key, Double value) {
        xyParameterSet.put(key, value);
    }
}
