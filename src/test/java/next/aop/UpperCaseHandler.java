package next.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by granknight on 2016. 8. 11..
 */
class UpperCaseHandler implements InvocationHandler {
    Object target;

    public UpperCaseHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object ret = method.invoke(target, args);
        if (ret instanceof String) {
            return ((String) ret).toUpperCase();
        } else {
            return ret;
        }
    }
}
