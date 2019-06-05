package qust.travelassistant.service;

import qust.travelassistant.entity.City;

import java.time.LocalDate;
import java.util.List;

public interface TravelPlanService {

    List<City> getTravelCityList(String userName);

    List<String> getTravelPlanList(String userName, String city);

    LocalDate getEarliestCreateDate(String userName);

    int getPlaceCount(String userName, LocalDate startTime, LocalDate endTime);

    int getAllCityCount(String userName);

    int getAllPlaceCount(String userName);

    int getCityPlaceCount(String userName, String cityCode);

    int addTravelPlan(String userName, String cityCode, String city, String place);

    int deleteTravelPlan(String userName, String cityCode, String place);

    int deleteTravelCity(String userName, String city);

    int getHotPlaceCount(String userName);

    int getNaturalPlaceCount(String userName);

    int getHumanPlaceCount(String userName);

    int getBusinessPlaceCount(String userName);
}
