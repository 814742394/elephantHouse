package cn.kgc.service.adminService.Impl;

import cn.kgc.entity.Street;
import cn.kgc.entity.StreetExample;
import cn.kgc.mapper.DistrictMapper;
import cn.kgc.mapper.StreetMapper;
import cn.kgc.service.adminService.StreetService;
import cn.kgc.util.StreetParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StreetServiceImpl implements StreetService {
	@Autowired
	StreetMapper streetMapper;
	@Autowired
	DistrictMapper districtMapper;
	@Override
	public PageInfo<Street> getStreets(StreetParam streetParam) {
		StreetExample streetExample = new StreetExample();
		StreetExample.Criteria criteria = streetExample.createCriteria();
		if (streetParam.getDistrictId()!=null){
			criteria.andDistrictIdEqualTo(streetParam.getDistrictId());
		}
		PageHelper.startPage(streetParam.getPage(),streetParam.getRows());
		List<Street> streets = streetMapper.selectByExample(streetExample);
		for (Street street:streets){
			street.setDistrict(districtMapper.selectByPrimaryKey(street.getDistrictId()));
		}
		PageInfo<Street> pageInfo = new PageInfo<>(streets);
		return pageInfo;
	}

	@Override
	public boolean addStreet(Street street) {
		int i = streetMapper.insertSelective(street);
		return i>0;
	}

	@Override
	public boolean updateStreet(Street street) {
		int i = streetMapper.updateByPrimaryKeySelective(street);
		return i>0;
	}

	@Override
	public int deleteStreets(List<Integer> ids) {
		StreetExample streetExample = new StreetExample();
		StreetExample.Criteria criteria = streetExample.createCriteria();
		criteria.andIdIn(ids);
		int i = streetMapper.deleteByExample(streetExample);
		return i;
	}

	@Override
	public List<Street> getStreetsByDistrictId(Integer districtId) {
		StreetExample streetExample = new StreetExample();
		StreetExample.Criteria criteria = streetExample.createCriteria();
		criteria.andDistrictIdEqualTo(districtId);
		List<Street> streets = streetMapper.selectByExample(streetExample);
		return streets;
	}
}
