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

import de.tammon.dev.mdc.server.model.PageSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tammschw on 12/04/15.
 */
@Controller
public class IndexController {

    @Autowired
    PageSettings pageSettings;

    @RequestMapping(value = {"/", "/successfulOrder"})
    public String servePageIndex() {
        pageSettings.clear();
        System.out.println(pageSettings);
        pageSettings.setPageName("index");
        pageSettings.setTitle("Startseite");
        return "index";
    }

    @ModelAttribute("pageSettings")
    public PageSettings getPageSettings() {
        return this.pageSettings;
    }
}
