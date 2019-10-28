package cn.kgc.service.adminService.Impl;

import cn.kgc.entity.Users;
import cn.kgc.entity.UsersExample;
import cn.kgc.mapper.UsersMapper;
import cn.kgc.service.adminService.UserService;
import cn.kgc.util.UserParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	UsersMapper usersMapper;

	@Override
	public PageInfo<Users> getUsers(UserParam userParam) {
		UsersExample usersExample = new UsersExample();
		UsersExample.Criteria criteria = usersExample.createCriteria();
		criteria.andIsadminNotEqualTo(1);
		if (userParam.getName()!=null&&!userParam.getName().isEmpty()){
			criteria.andNameLike("%"+userParam.getName()+"%");
		}
		if (userParam.getTelephone()!=null&&!userParam.getTelephone().isEmpty()){
			criteria.andTelephoneLike("%"+userParam.getTelephone()+"%");
		}
		PageHelper.startPage(userParam.getPage(),userParam.getRows());
		List<Users> users = usersMapper.selectByExample(usersExample);
		PageInfo<Users> pageInfo = new PageInfo<>(users);
		return pageInfo;
	}

	@Override
	public int deleteUsers(List<Integer> ids) {
		UsersExample usersExample = new UsersExample();
		UsersExample.Criteria criteria = usersExample.createCriteria();
		criteria.andIdIn(ids);
		int i = usersMapper.deleteByExample(usersExample);
		return i;
	}
}
