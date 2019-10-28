package cn.kgc.service.portalService.Impl;

import cn.kgc.entity.Users;
import cn.kgc.entity.UsersExample;
import cn.kgc.mapper.*;
import cn.kgc.service.portalService.PotalService;
import cn.kgc.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PotalServiceImpl implements PotalService {
	@Autowired
	UsersMapper usersMapper;
	@Autowired
	HouseMapper houseMapper;
	@Autowired
	StreetMapper streetMapper;
	@Autowired
	DistrictMapper districtMapper;
	@Autowired
	TypeMapper typeMapper;
	@Override
	public boolean isUserNameExist(Users users) {
		UsersExample usersExample = new UsersExample();
		UsersExample.Criteria criteria = usersExample.createCriteria();
		criteria.andNameEqualTo(users.getName());
		List<Users> users1 = usersMapper.selectByExample(usersExample);
		if (users1.size()!=0){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean reg(Users users) {
		users.setIsadmin(0);
		users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));
		int i = usersMapper.insertSelective(users);
		return i>0;
	}

	@Override
	public Users login(Users user) {
		UsersExample usersExample = new UsersExample();
		UsersExample.Criteria criteria = usersExample.createCriteria();
		criteria.andNameEqualTo(user.getName());
		criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(user.getPassword()));
		List<Users> users = usersMapper.selectByExample(usersExample);
		if (users.size()!=0){
			return users.get(0);
		}else {
			return null;
		}
	}
}
