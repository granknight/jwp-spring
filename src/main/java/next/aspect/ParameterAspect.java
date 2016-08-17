package next.aspect;

import org.apache.tomcat.jni.Proc;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by granknight on 2016. 8. 11..
 */
@Aspect
@Component
public class ParameterAspect {

    Logger log = LoggerFactory.getLogger(ParameterAspect.class);


    @Before("execution(* next.dao..*.*(..))")//next dao 패키지와 하위패키지의 모든 메소드 호출 표현식
    public Object parameterScan(JoinPoint joinPoint){

        Signature signature = joinPoint.getSignature();


        log.debug("class name : {}", signature.getDeclaringTypeName());

        log.debug("signature name : {}",signature.getName());


        Object[] args = joinPoint.getArgs();

        for(Object obj : args){
            log.debug("args : {}",obj);
        }


        return null;
    }
}
