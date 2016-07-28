package kr.iolo.toybox;

import kr.iolo.toybox.springtemplatebench.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class AppTest {

    @Autowired
    App app;

//    @Test
//    public void testApp() {
//        assertNotNull(app);
//    }

}
