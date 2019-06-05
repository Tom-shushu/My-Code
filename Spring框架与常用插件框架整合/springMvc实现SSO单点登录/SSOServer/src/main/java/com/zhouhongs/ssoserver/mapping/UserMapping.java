package com.zhouhongs.ssoserver.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.zhouhongs.ssoserver.bean.User;

public class UserMapping implements RowMapper<User>{

	public User mapRow(ResultSet rs, int i) throws SQLException {
		// TODO Auto-generated method stub
		int userId=rs.getInt("user_id");

	    String userName=rs.getString("user_name");

	    String userPassword=rs.getString("user_password");

	    String userDescribe=rs.getString("user_describe");

	    Date createTime=rs.getDate("create_time");

	    Date updateTime=rs.getDate("update_time");
		User user=new User(userId,userName,userPassword,userDescribe,createTime,updateTime);
	
		return user;
	}

	

	

}
