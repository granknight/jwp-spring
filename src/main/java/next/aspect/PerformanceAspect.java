package next.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Created by granknight on 2016. 8. 11..
 */
@Component
@Aspect
public class PerformanceAspect {

    Logger log = LoggerFactory.getLogger(PerformanceAspect.class);

    @Pointcut("within(next.service..*) || within(next.dao..*)")
    public void myPointCut(){

    }

    @Around("myPointCut()")
    public Object performanceCheck(ProceedingJoinPoint pjp) throws Throwable {
        log.debug("aspect insert");
        // start stopwatch
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        Object retVal = pjp.proceed();
        // stop stopwatch

        stopWatch.stop();

        log.debug(String.valueOf(stopWatch.getTotalTimeSeconds()) + "s");
        return retVal;
    }

}
