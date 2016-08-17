package next.aop;

/**
 * Created by granknight on 2016. 8. 11..
 */
class HelloTarget implements Hello {
    public String sayHello(String name) {
        return "Hello " + name;
    }
    public String sayHi(String name) {
        return "Hi " + name;
    }
    public String sayThankYou(String name) {
        return "Thank You " + name;
    }
}
