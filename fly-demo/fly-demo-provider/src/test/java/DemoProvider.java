import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by  wuyunxing on   2016/7/29.
 */

public class DemoProvider {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/application.xml");
    }
}
