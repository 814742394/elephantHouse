package cn.kgc.service.portalService;

import cn.kgc.entity.Users;

public interface PotalService {
	boolean isUserNameExist(Users users);

	boolean reg(Users users);

	Users login(Users user);
}
