package next.aop;

import next.config.AppConfig;
import next.service.QnaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by granknight on 2016. 8. 11..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class PerformanceTest {
    @Resource
    private QnaService qnaService;

    @Test
    public void aop() {
        qnaService.findById(1);
    }
}
