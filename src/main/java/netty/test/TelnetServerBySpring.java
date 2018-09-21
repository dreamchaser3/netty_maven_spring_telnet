package netty.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public final class TelnetServerBySpring {
    public static void main(String[] args) {
        ApplicationContext ctx = new GenericXmlApplicationContext("applicationContext.xml");
        ctx.getBean(TelnetServer.class).start();
    }
}
