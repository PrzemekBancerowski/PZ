package com.project.pz.webserver.aspect;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pi10521 on 2016-06-14.
 */
@Aspect
@Component
public class ControllerExecutionMonitor {

    protected final String CLASS_NAME = "CLASS_NAME";

    protected final String METHOD_NAME = "METHOD_NAME";

    protected final String METHOD_PARAMS = "METHOD_PARAMS";

    protected final String EXECUTION_TIME = "EXECUTION_TIME";

    protected final String EXCEPTION = "EXCEPTION";

    private final StopWatch stopWatch = new StopWatch();

    private final Logger logger = Logger.getLogger(ControllerExecutionMonitor.class);

    @Before("execution(* com.project.pz.webserver.controller.*.*(..))")
    public void logExecutionStart(JoinPoint joinPoint) {
        StrSubstitutor sub = new StrSubstitutor(getParamsMap(joinPoint));
        logger.info(sub.replace("[${" + CLASS_NAME + "}] execution of method ${" + METHOD_NAME + "} with params: (${" + METHOD_PARAMS + "}) ..."));
    }

    @Around("execution(* com.project.pz.webserver.controller.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        stopWatch.start();
        Object retVal = joinPoint.proceed();
        stopWatch.stop();

        Map<String, String> params = getParamsMap(joinPoint);
        params.put(EXECUTION_TIME, "" + stopWatch.getTotalTimeMillis());
        StrSubstitutor sub = new StrSubstitutor(params);
        logger.info(sub.replace("[${" + CLASS_NAME + "}] execution of method ${" + METHOD_NAME + "} with params: (${" + METHOD_PARAMS + "}) completed in ${" + EXECUTION_TIME + "} ms."));
        return retVal;
    }

    @AfterThrowing(value = "execution(* com.project.pz.webserver.controller.*.*(..))",
            throwing = "ex", argNames = "joinPoint,ex")
    public void stopStopWatch(JoinPoint joinPoint, Exception ex) {
        if (stopWatch.isRunning()) {
            stopWatch.stop();

            Map<String, String> params = getParamsMap(joinPoint);
            params.put(EXECUTION_TIME, "" + stopWatch.getTotalTimeMillis());
            params.put(EXCEPTION, ex.getMessage());
            StrSubstitutor sub = new StrSubstitutor(params);
            logger.info(sub.replace("[${" + CLASS_NAME + "}] execution of method ${" + METHOD_NAME + "} with params: (${" + METHOD_PARAMS + "}) failed after ${"
                    + EXECUTION_TIME + "} ms. Reason: ${" + EXCEPTION + "}"));
        }
    }

    private Map<String, String> getParamsMap(JoinPoint joinPoint) {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put(CLASS_NAME, joinPoint.getTarget().getClass().getSimpleName());
        paramsMap.put(METHOD_NAME, joinPoint.getSignature().getName());
        paramsMap.put(METHOD_PARAMS, getMethodParamsString(joinPoint));
        return paramsMap;
    }

    protected String getMethodParamsString(JoinPoint joinPoint) {
        StringBuilder builder = new StringBuilder();
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            builder.append(arg.toString()).append(",");
        }
        if (args.length > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

}
