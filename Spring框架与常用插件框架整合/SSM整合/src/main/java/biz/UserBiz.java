package biz;

import java.util.List;

import bean.UserInfo;

public interface UserBiz {
	List<UserInfo> findAllUser(int nums);
}
