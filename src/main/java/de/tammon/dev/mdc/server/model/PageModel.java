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

import org.springframework.stereotype.Component;

/**
 * Created by tammschw on 04/06/15.
 */
@Component
public class PageModel {
    private boolean orderSucceeded, orderFailed, doNotClear;
    private String title, pageName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = this.title == null ? title : this.title;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public boolean getOrderSucceeded() {
        return orderSucceeded;
    }

    public boolean isOrderFailed() {
        return orderFailed;
    }

    @Override
    public String toString() {
        return "PageSettings{" +
                "orderSucceeded=" + orderSucceeded +
                ", orderFailed=" + orderFailed +
                ", doNotClear=" + doNotClear +
                ", title='" + title + '\'' +
                ", pageName='" + pageName + '\'' +
                '}';
    }

    public void orderSucceeded() {
        this.orderSucceeded = true;
    }

    public void orderFailed() { this.orderFailed = true; }

    public void doNotClearOnNextUse() {
        this.doNotClear = true;
    }

    public void clear () {
        if (!doNotClear) {
            orderSucceeded = false;
            orderFailed = false;
            title = null;
            pageName = null;

            doNotClear = false;
        } else doNotClear = false;
    }
}