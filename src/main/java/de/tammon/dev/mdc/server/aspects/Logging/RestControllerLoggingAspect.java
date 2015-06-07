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
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Created by tammschw on 07/06/15.
 */
@Aspect
@Component
public class RestControllerLoggingAspect extends AbstractLogger {

    @AfterReturning(pointcut = "de.tammon.dev.mdc.server.aspects.Pointcuts.allRestGetMethods()", returning = "result")
    public void afterReturningRestControllerGetMethods (JoinPoint joinPoint, Object result) {
        logger = getLogger(joinPoint.getTarget().getClass());
        if ((new ResponseEntity<>(HttpStatus.NOT_FOUND)).equals(result)) logger.error(joinPoint.getSignature().toShortString() + ": Didn't find requested object! Sorry...");
        else logger.debug(joinPoint.getSignature().toShortString() + ": Found the requested object and served it as json " + result.toString());
    }
}
