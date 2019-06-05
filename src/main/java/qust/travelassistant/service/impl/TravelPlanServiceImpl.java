package qust.travelassistant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qust.travelassistant.dao.TravelPlanDao;
import qust.travelassistant.entity.City;
import qust.travelassistant.service.TravelPlanService;

import java.time.LocalDate;
import java.util.List;

@Service
public class TravelPlanServiceImpl implements TravelPlanService {

    @Autowired
    private TravelPlanDao travelPlanDao;

    @Override
    public List<City> getTravelCityList(String userName) {
        return travelPlanDao.getTravelCityList(userName);
    }

    @Override
    public List<String> getTravelPlanList(String userName, String cityCode) {
        return travelPlanDao.getTravelPlanList(userName, cityCode);
    }

    @Override
    public LocalDate getEarliestCreateDate(String userName) {
        return travelPlanDao.getEarliestCreateDate(userName);
    }

    @Override
    public int getPlaceCount(String userName, LocalDate startTime, LocalDate endTime) {
        return travelPlanDao.getPlaceCount(userName, startTime, endTime);
    }

    @Override
    public int getAllCityCount(String userName) {
        return travelPlanDao.getAllCityCount(userName);
    }

    @Override
    public int getAllPlaceCount(String userName) {
        return travelPlanDao.getAllPlaceCount(userName);
    }

    @Override
    public int getCityPlaceCount(String userName, String cityCode) {
        return travelPlanDao.getCityPlaceCount(userName, cityCode);
    }

    @Override
    public int addTravelPlan(String userName, String cityCode, String city, String place) {
        return travelPlanDao.insertTravelPlan(userName, cityCode, city, place);
    }

    @Override
    public int deleteTravelPlan(String userName, String cityCode, String place) {
        return travelPlanDao.deleteTravelPlan(userName, cityCode, place);
    }

    @Override
    public int deleteTravelCity(String userName, String cityCode) {
        return travelPlanDao.deleteTravelCity(userName, cityCode);
    }

    @Override
    public int getHotPlaceCount(String userName) {
        return travelPlanDao.getHotPlaceCount(userName);
    }

    @Override
    public int getNaturalPlaceCount(String userName) {
        return travelPlanDao.getNaturalPlaceCount(userName);
    }

    @Override
    public int getHumanPlaceCount(String userName) {
        return travelPlanDao.getHumanPlaceCount(userName);
    }

    @Override
    public int getBusinessPlaceCount(String userName) {
        return travelPlanDao.getBusinessPlaceCount(userName);
    }
}
