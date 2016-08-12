import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by  wuyunxing on   2016/7/27.
 */

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/application.xml");
        context.start();
    }
}
