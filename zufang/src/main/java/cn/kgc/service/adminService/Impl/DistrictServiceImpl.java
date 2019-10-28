package cn.kgc.service.adminService.Impl;

import cn.kgc.entity.District;
import cn.kgc.entity.DistrictExample;
import cn.kgc.mapper.DistrictMapper;
import cn.kgc.service.adminService.DistrictService;
import cn.kgc.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DistrictServiceImpl implements DistrictService {
	@Autowired
	DistrictMapper districtMapper;

	@Override
	public PageInfo<District> getDistricts(PageUtil pageUtil) {
		PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
		List<District> districts = districtMapper.selectByExample(null);
		PageInfo<District> pageInfo = new PageInfo<>(districts);
		return pageInfo;
	}

	@Override
	public boolean addDistrict(District district) {
		int i = districtMapper.insertSelective(district);
		return i>0;
	}

	@Override
	public boolean updateDistrict(District district) {
		int i = districtMapper.updateByPrimaryKeySelective(district);
		return i>0;
	}

	@Override
	public Integer deleteDistricts(List<Integer> values) {
		DistrictExample districtExample = new DistrictExample();
		DistrictExample.Criteria criteria = districtExample.createCriteria();
		criteria.andIdIn(values);
		int i = districtMapper.deleteByExample(districtExample);
		return i;
	}

	@Override
	public List<District> getAllDistrict() {
		List<District> districts = districtMapper.selectByExample(null);
		return districts;
	}
}
