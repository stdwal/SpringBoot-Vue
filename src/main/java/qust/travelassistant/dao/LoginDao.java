package qust.travelassistant.dao;

import org.apache.ibatis.annotations.*;
import qust.travelassistant.entity.Login;

import java.util.Date;

public interface LoginDao {

    @Select("SELECT * FROM login WHERE user_name = #{userName}")
    Login queryByUserName(String userName);

    @Insert("INSERT INTO login(user_name, password) VALUES(#{userName}, #{password});")
    void insertLogin(@Param("userName") String userName, @Param("password") String password);

    @Update("UPDATE login SET last_time = #{lastTime} WHERE user_name = #{userName};")
    void updateLogin(@Param("userName") String userName, @Param("lastTime") Date lastTime);
}
