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

package de.tammon.dev.mdc.server.aspects.Logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

/**
 * Created by tammschw on 01/06/15.
 */
@Aspect
@Component
public class ControllerLoggingAspect extends AbstractLogger {

    /**
     * ASPECT
     * Logs all important parameters that are parsed to Thymeleaf by the servePage controller methods
     *
     * @param joinPoint servePage controller method join
     * @param model     {@link Model} that is parsed to Thymeleaf
     * @param result    return value of joinPoint
     */
    @AfterReturning(pointcut = "de.tammon.dev.mdc.server.aspects.Pointcuts.servePage(model)", returning = "result")
    public void afterReturningServePage(JoinPoint joinPoint, Model model, String result) {
        logger = getLogger(joinPoint.getTarget().getClass());
        logger.debug("Parsing template '" + result + "' with the model attributes " + model.toString() + " to Thymeleaf");
    }

    @After("de.tammon.dev.mdc.server.aspects.Pointcuts.serveProductPage(id)")
    public void afterServingProductPage (JoinPoint joinPoint, String id) {
        if (id == null) logger.debug(joinPoint.getSignature().toShortString() + "says: No id (externalProductionId) provided to requestHandling product controller method. Serving example Product.");
        else logger.debug(joinPoint.getSignature().toShortString() + "says: Got an id (externalProductionId). Now trying to get the corresponding product from database.");
    }
}
