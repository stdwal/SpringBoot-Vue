package qust.travelassistant.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginDaoTest {

    @Autowired
    private LoginDao loginDao;

    @Test
    public void queryByUserName() {
        String userName = "admin";
        System.out.println(loginDao.queryByUserName(userName));
    }
}