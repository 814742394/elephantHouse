package cn.kgc.service.adminService.Impl;

import cn.kgc.entity.Type;
import cn.kgc.entity.TypeExample;
import cn.kgc.mapper.TypeMapper;
import cn.kgc.service.adminService.TypeService;
import cn.kgc.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {
	@Autowired
	TypeMapper typeMapper;
	@Override
	public PageInfo<Type> getTypes(PageUtil pageUtil) {
		PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
		List<Type> types = typeMapper.selectByExample(null);
		PageInfo<Type> pageInfo = new PageInfo<>(types);
		return pageInfo;
	}

	@Override
	public boolean addType(Type type) {
		int i = typeMapper.insertSelective(type);
		return i>0;
	}

	@Override
	public boolean updateType(Type type) {
		int i = typeMapper.updateByPrimaryKeySelective(type);
		return i>0;
	}

	@Override
	public int deleteTypes(List<Integer> ids) {
		TypeExample typeExample = new TypeExample();
		TypeExample.Criteria criteria = typeExample.createCriteria();
		criteria.andIdIn(ids);
		int i = typeMapper.deleteByExample(typeExample);
		return i;
	}

	@Override
	public List<Type> getAllTypes() {
		List<Type> types = typeMapper.selectByExample(null);
		return types;
	}
}
