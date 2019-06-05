package qust.travelassistant.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TravelPlanDaoTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TravelPlanDao travelPlanDao;

    @Test
    public void testGetEarliestCreateTime() {
        String userName = "admin";
        LocalDate date = travelPlanDao.getEarliestCreateDate(userName);
        logger.info("result: " + date);
    }

    @Test
    public void testGetPlaceCount() throws ParseException {
        String userName = "admin";
//        LocalDate startTime = travelPlanDao.getEarliestCreateDate(userName);
//        LocalDate endTime = LocalDate.now();
//        int cnt = travelPlanDao.getPlaceCount(userName, startTime, endTime);
        LocalDate startTime = LocalDate.parse("2019-04-29");
        LocalDate endTime = LocalDate.parse("2019-04-30");
        int cnt = travelPlanDao.getPlaceCount(userName, startTime, endTime);
        logger.info("cnt: " + cnt);
    }

}