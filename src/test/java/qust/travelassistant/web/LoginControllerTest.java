package qust.travelassistant.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginController loginController;

    @Test
    public void signIn() {
        String userName = "wal";
        String password = "1508080137";
        String msg = loginController.signIn(userName, password);
        logger.info("userName: " + userName);
        logger.info("password: " + password);
        logger.info(msg);
    }

    @Test
    public void signUp() {
        String userName = "wal";
        String password = "1508080";
        String confirmPassword = "1508080137";
        String msg = loginController.signUp(userName, password, confirmPassword);
        logger.info("userName: " + userName);
        logger.info("password: " + password);
        logger.info("confirmPassword: " + confirmPassword);
        logger.info("msg: " + msg);
    }
}