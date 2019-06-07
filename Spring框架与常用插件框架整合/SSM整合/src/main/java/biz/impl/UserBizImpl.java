package biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.UserInfo;
import biz.UserBiz;
import mapper.UserMapper;

@Service
public class UserBizImpl implements UserBiz {
	@Autowired
	private UserMapper um;
	public List<UserInfo> findAllUser(int nums) {
		return um.findAllUser(nums);
	}
}
