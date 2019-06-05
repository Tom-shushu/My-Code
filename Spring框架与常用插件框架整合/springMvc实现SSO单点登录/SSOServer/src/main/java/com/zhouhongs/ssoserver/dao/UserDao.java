package com.zhouhongs.ssoserver.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;
import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zhouhongs.ssoserver.bean.User;
import com.zhouhongs.ssoserver.mapping.UserMapping;
@Repository
public class UserDao {

	@Autowired
	private  JdbcTemplate jdbcTemplate;


	public String getName(){
		return jdbcTemplate.queryForObject("select user_name from user_info where user_id=1", String.class);
	}
	
	public User login(String userName,String userPassword){
		User u=new User();
		String sql=" select * from user_info where user_name=? and user_password=? ";
		
		Object[] param= new Object[]{userName,userPassword};
		
		u=jdbcTemplate.queryForObject(sql, new UserMapping(), param);
		
 		return u;
	}

}
