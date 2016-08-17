package next.aop;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactoryBean;

import java.lang.reflect.Proxy;

/**
 * Created by granknight on 2016. 8. 11..
 */
public class HelloTest {

    @Test
    public void hello() throws Exception {
        Hello hello = new HelloTarget();

        HelloUppercase proxy = new HelloUppercase(hello);

        System.out.println(proxy.sayHello("granknight"));
    }

    @Test
    public void simpleProxy() throws Exception{
        Hello proxiedHello = (Hello) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{Hello.class}, new UpperCaseHandler(new HelloTarget()));

        System.out.println(proxiedHello.sayHello("test"));
    }

    @Test
    public void proxyFactoryBean() throws Exception{
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(new HelloTarget());
        pfBean.addAdvice(new UppercaseAdvice());
        Hello proxiedHello = (Hello) pfBean.getObject();

        System.out.println(proxiedHello.sayHello("granknight"));
        System.out.println(proxiedHello.sayThankYou("granknight"));
    }


}
