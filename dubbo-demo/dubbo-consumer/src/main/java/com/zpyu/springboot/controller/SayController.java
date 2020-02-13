
import com.zpyu.springboot.service.ISay;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zpyu521
 * @Date: 2019/8/9
 * @Description:
 * @Version: 1.0
 */
@RestController
public class SayController {

    @Reference(version = "1.0.0")
    private ISay say;

    @RequestMapping("/say")
    public String say(String name) throws Exception {
        String say = this.say.say(name);
        System.out.println(say);
        return say;
    }

}
