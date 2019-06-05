package qust.travelassistant.dao;

import org.apache.ibatis.annotations.*;
import qust.travelassistant.entity.City;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface TravelPlanDao {

    @Select("SELECT DISTINCT city_code, city FROM travel_plan WHERE user_name = #{userName}")
    List<City> getTravelCityList(String userName);

    @Select("SELECT place FROM travel_plan WHERE user_name = #{userName} AND city_code = #{cityCode}")
    List<String> getTravelPlanList(@Param("userName") String userName, @Param("cityCode") String cityCode);

    @Select("SELECT MIN(create_time) FROM travel_plan WHERE user_name = #{userName}")
    LocalDate getEarliestCreateDate(String userName);

    @Select("SELECT COUNT(*) FROM travel_plan WHERE user_name = #{userName}")
    int getAllPlaceCount(String userName);

    @Select("SELECT COUNT(DISTINCT city_code) FROM travel_plan WHERE user_name = #{userName}")
    int getAllCityCount(String userName);

    @Select("SELECT COUNT(*) FROM travel_plan WHERE user_name = #{userName} AND city_code = #{cityCode}")
    int getCityPlaceCount(@Param("userName") String userName, @Param("cityCode") String cityCode);

    @Select("SELECT COUNT(*) FROM travel_plan WHERE user_name = #{userName} AND create_time >= #{startTime} AND create_time < #{endTime}")
    int getPlaceCount(String userName, LocalDate startTime, LocalDate endTime);

    @Insert("INSERT INTO travel_plan(user_name, city_code, city, place) VALUES(#{userName}, #{cityCode}, #{city}, #{place});")
    int insertTravelPlan(@Param("userName") String userName, @Param("cityCode") String cityCode, @Param("city") String city, @Param("place") String place);

    @Delete("DELETE FROM travel_plan WHERE user_name = #{userName} AND city_code = #{cityCode} AND place = #{place};")
    int deleteTravelPlan(@Param("userName") String userName, @Param("cityCode") String cityCode, @Param("place") String place);

    @Delete("DELETE FROM travel_plan WHERE user_name = #{userName} AND city_code = #{cityCode};")
    int deleteTravelCity(@Param("userName") String userName, @Param("cityCode") String cityCode);

    @Select("SELECT COUNT(*) FROM travel_plan, place_model WHERE user_name = #{userName}" +
            " AND travel_plan.city_code = place_model.city_code AND travel_plan.place = place_model.place" +
            " AND is_hot = 1")
    int getHotPlaceCount(String userName);

    @Select("SELECT COUNT(*) FROM travel_plan, place_model WHERE user_name = #{userName}" +
            " AND travel_plan.city_code = place_model.city_code AND travel_plan.place = place_model.place" +
            " AND is_natural = 1")
    int getNaturalPlaceCount(String userName);

    @Select("SELECT COUNT(*) FROM travel_plan, place_model WHERE user_name = #{userName}" +
            " AND travel_plan.city_code = place_model.city_code AND travel_plan.place = place_model.place" +
            " AND is_human = 1")
    int getHumanPlaceCount(String userName);

    @Select("SELECT COUNT(*) FROM travel_plan, place_model WHERE user_name = #{userName}" +
            " AND travel_plan.city_code = place_model.city_code AND travel_plan.place = place_model.place" +
            " AND is_business = 1")
    int getBusinessPlaceCount(String userName);
}
