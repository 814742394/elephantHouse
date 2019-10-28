package cn.kgc.service.adminService;

import cn.kgc.entity.Users;
import cn.kgc.util.UserParam;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
	PageInfo<Users> getUsers(UserParam userParam);

	int deleteUsers(List<Integer> ids);
}
