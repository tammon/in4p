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
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

/**
 * Created by tammschw on 01/06/15.
 */
@Aspect
@Component
public class ControllerLoggingAspect {

    private Logger logger;

    /**
     * ASPECT
     * Declares pointcut to all controller methods that serve Thymeleaf pages
     * @param model {@link Model} that is parsed to Thymeleaf
     */
    @Pointcut("execution(String de.tammon.dev.mdc.server.controller..*.servePage*(..)) && args(model)")
    public void servePage(Model model) {}


    /**
     * ASPECT
     * Logs all important parameters that are parsed to Thymeleaf by the servePage controller methods
     * @param joinPoint servePage controller method join
     * @param model {@link Model} that is parsed to Thymeleaf
     * @param result return value of joinPoint
     */
    @AfterReturning(pointcut = "servePage(model)", returning = "result")
    public void afterReturningServePage(JoinPoint joinPoint, Model model, String result) {
        logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        logger.debug("Parsing template '" + result + "' with the model attributes " + model.toString() + " to Thymeleaf");
    }
}
