package mapper;

import java.util.List;

import bean.UserInfo;

public interface UserMapper {

	List<UserInfo> findAllUser(int nums);
	
}
