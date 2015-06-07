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

import de.tammon.dev.mdc.server.model.PageModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

/**
 * Created by tammschw on 12/04/15.
 */
@Controller
public class IndexController extends AbstractMdcController {

    private static final String INDEX_PAGE = "index";

    @RequestMapping(value = {"/", "/successfulOrder"})
    public String servePageIndex(Locale locale) {
        pageModel.clear();
        pageModel.setPageName(INDEX_PAGE);
        pageModel.setTitle(messageSource.getMessage("index.page.title", null, locale));
        return INDEX_PAGE;
    }

    @ModelAttribute("pageModel")
    public PageModel getPageModel() {
        return this.pageModel;
    }
}
