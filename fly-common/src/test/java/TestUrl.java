import com.star.fly.url.URL;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * Created by  wuyunxing on   2016/7/26.
 */

public class TestUrl {
    @Test
    public void encoder(){
        URL url = new URL("aaa","bbb",null,0);
        URL.urlEncoder(url.toString());
        Assert.assertNotNull(url);
    }
    @Test
    public  void decoder(){
        URL url = new URL("aaa","bbb",null,0);
        String url_str = URL.urlDecoder(URL.urlEncoder(url.toString()));
        Assert.assertEquals(url_str,url.toString());
    }

    @Test
    public void random(){
        Random random = new Random();

        System.out.println(random.nextInt(5));
    }
}
