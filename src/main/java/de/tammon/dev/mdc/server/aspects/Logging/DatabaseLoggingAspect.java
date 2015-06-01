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
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by tammschw on 01/06/15.
 * Logging for repositories and database transactions
 */
@Aspect
@Component
public class DatabaseLoggingAspect extends AbstractLogger{

    @Pointcut("execution(* de.tammon.dev.mdc.server.service.*.save(..))")
    public void saveToDatabase() {}

    @Pointcut("execution(* de.tammon.dev.mdc.server.service.DatabaseService.get*(..))")
    public void allGetMethods() {}

    @Before("saveToDatabase()")
    public void beforeSaveToDatabase(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        logger = getLogger(joinPoint.getTarget().getClass());
        for (Object arg : args) logger.debug("Parse object to database repository for saving: " + arg);
    }

    @AfterReturning(value = "allGetMethods()", returning = "result")
    public void aroundDatabaseGetMethods(JoinPoint joinPoint, Object result) {
        logger = getLogger(joinPoint.getTarget().getClass());
        logger.debug(joinPoint.getSignature().toShortString()
                + ": Tried to get object of "
                + ((MethodSignature)joinPoint.getSignature()).getReturnType().toString()
                + " from database by the provided Parameters "
                + (new String(Arrays
                    .stream(Arrays.copyOf(joinPoint.getArgs(), joinPoint.getArgs().length, String[].class))
                    .collect(Collectors.joining(" ")))));
        if (result == null) logger.error(joinPoint.getSignature().toShortString() + ": Didn't find requested object! Sorry...");
        else logger.debug(joinPoint.getSignature().toShortString() + ": Found the requested object " + result.toString());
    }
}
