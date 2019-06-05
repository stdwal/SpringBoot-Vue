package qust.travelassistant.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import qust.travelassistant.entity.City;
import qust.travelassistant.entity.TravelPlan;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TravelPlanControllerTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TravelPlanController travelPlanController;

    @Test
    public void getTravelCityList() {
        String userName = "admin";
        List<City> result = travelPlanController.getTravelCityList(userName);
        logger.info("userName: " + userName);
        logger.info("result: " + result);
    }

    @Test
    public void deleteTravelCity() {
        String userName = "admin";
        String cityCode = "010";
        logger.info("userName: " + userName);
        logger.info("cityCode: " + cityCode);
    }

    @Test
    public void getTravelPlanList() {
        String userName = "admin";
        String cityCode = "0574";
        List<String> result = travelPlanController.getTravelPlanList(userName, cityCode);
        logger.info("userName: " + userName);
        logger.info("cityCode: " + cityCode);
        logger.info("result: " + result);
    }

    @Test
    public void addTravelPlan() {
    }

    @Test
    public void deleteTravelPlan() {
    }

    @Test
    public void getAllCityCount() {
    }

    @Test
    public void getAllPlaceCount() {
    }

    @Test
    public void getCityPlaceCount() {
    }

    @Test
    public void newPlaceChartData() {
    }

    @Test
    public void placePreferenceChartData() {
    }

    @Test
    public void cityPlaceCountChartData() {
    }
}