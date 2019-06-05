package qust.travelassistant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qust.travelassistant.dto.ChartData;
import qust.travelassistant.dto.CityAndCount;
import qust.travelassistant.dto.DateAndCount;
import qust.travelassistant.dto.TypeAndCount;
import qust.travelassistant.entity.City;
import qust.travelassistant.service.TravelPlanService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/travel-assistant/api")
public class TravelPlanController {

    @Autowired
    private TravelPlanService travelPlanService;

    @RequestMapping(value = "/{userName}/cityList",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public List<City> getTravelCityList(@PathVariable("userName") String userName) {
        return travelPlanService.getTravelCityList(userName);
    }

    @RequestMapping(value = "/{userName}/{cityCode}",
            method = RequestMethod.DELETE,
            produces = {"application/json;charset=UTF-8"})
    public void deleteTravelCity(@PathVariable("userName") String userName, @PathVariable("cityCode") String cityCode) {
        travelPlanService.deleteTravelCity(userName, cityCode);
    }


    @RequestMapping(value = "/{userName}/{cityCode}/planList",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public List<String> getTravelPlanList(@PathVariable("userName") String userName, @PathVariable("cityCode") String cityCode) {
        return travelPlanService.getTravelPlanList(userName, cityCode);
    }

    @RequestMapping(value = "/{userName}/{cityCode}/planList",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public void addTravelPlan(@PathVariable("userName") String userName, @PathVariable("cityCode") String cityCode, String city, String place) {
        travelPlanService.addTravelPlan(userName, cityCode, city, place);
    }

    @RequestMapping(value = "/{userName}/{cityCode}/planList",
            method = RequestMethod.DELETE,
            produces = {"application/json;charset=UTF-8"})
    public void deleteTravelPlan(@PathVariable("userName") String userName, @PathVariable("cityCode") String cityCode, String place) {
        travelPlanService.deleteTravelPlan(userName, cityCode, place);
    }


    @RequestMapping(value = "/{userName}/cityCount",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public int getAllCityCount(@PathVariable("userName") String userName) {
        return travelPlanService.getAllCityCount(userName);
    }

    @RequestMapping(value = "/{userName}/placeCount",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public int getAllPlaceCount(@PathVariable("userName") String userName) {
        return travelPlanService.getAllPlaceCount(userName);
    }

    public int getCityPlaceCount(String userName, String cityCode) {
        return travelPlanService.getCityPlaceCount(userName, cityCode);
    }

    @RequestMapping(value = "/{userName}/newPlaceChart",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public ChartData<DateAndCount> newPlaceChartData(@PathVariable("userName") String userName) {
        ChartData<DateAndCount> result = new ChartData<>();
        List<String> columns = new ArrayList<>();
        columns.add("日期");
        columns.add("新增计划");
        result.setColumns(columns);

        List<DateAndCount> rows = new ArrayList<>();
        LocalDate startDate = travelPlanService.getEarliestCreateDate(userName);
        LocalDate endDate = LocalDate.now();
        LocalDate date = startDate;
        while (!date.isAfter(endDate)) {
            int cnt = travelPlanService.getPlaceCount(userName, date, date.plusDays(1));
            rows.add(new DateAndCount(date.toString(), cnt));
            date = date.plusDays(1);
        }
        result.setRows(rows);
        return result;
    }

    @RequestMapping(value = "/{userName}/placePreferenceChart",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public ChartData<TypeAndCount> placePreferenceChartData(@PathVariable("userName") String userName) {
        ChartData<TypeAndCount> result = new ChartData<>();
        List<String> columns = new ArrayList<>();
        columns.add("类型");
        columns.add("数量");
        result.setColumns(columns);

        List<TypeAndCount> rows = new ArrayList<>();
        rows.add(new TypeAndCount("热门", travelPlanService.getHotPlaceCount(userName)));
        rows.add(new TypeAndCount("人文", travelPlanService.getHumanPlaceCount(userName)));
        rows.add(new TypeAndCount("自然", travelPlanService.getNaturalPlaceCount(userName)));
        rows.add(new TypeAndCount("商业", travelPlanService.getBusinessPlaceCount(userName)));
        result.setRows(rows);
        return result;
    }

    @RequestMapping(value = "/{userName}/cityPlaceCountChart",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public ChartData<CityAndCount> cityPlaceCountChartData(@PathVariable("userName") String userName) {
        ChartData<CityAndCount> result = new ChartData<>();
        List<String> columns = new ArrayList<>();
        columns.add("城市");
        columns.add("地点");
        result.setColumns(columns);

        List<City> cityList = getTravelCityList(userName);
        List<CityAndCount> rows = new ArrayList<>();
        for (int i = 0; i < cityList.size(); i++) {
            rows.add(new CityAndCount(cityList.get(i).getCity(), getCityPlaceCount(userName, cityList.get(i).getCityCode())));
        }
        result.setRows(rows);
        return result;
    }
}
