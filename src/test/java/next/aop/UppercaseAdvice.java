package next.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by granknight on 2016. 8. 11..
 */
class UppercaseAdvice implements MethodInterceptor {
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String ret = (String)invocation.proceed();
        return ret.toUpperCase();
    }

}