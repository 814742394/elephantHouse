package cn.kgc.service.adminService.Impl;

import cn.kgc.entity.House;
import cn.kgc.entity.HouseExample;
import cn.kgc.entity.Street;
import cn.kgc.entity.StreetExample;
import cn.kgc.mapper.*;
import cn.kgc.service.adminService.HouseService;
import cn.kgc.util.HouseListParam;
import cn.kgc.util.HouseParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class HouseServiceImpl implements HouseService {
	@Autowired
	HouseMapper houseMapper;
	@Autowired
	TypeMapper typeMapper;
	@Autowired
	UsersMapper usersMapper;
	@Autowired
	StreetMapper streetMapper;
	@Autowired
	DistrictMapper districtMapper;
	@Override
	public PageInfo<House> getHouses(HouseParam houseParam) {
		HouseExample houseExample = new HouseExample();
		HouseExample.Criteria criteria = houseExample.createCriteria();
		if (houseParam.getUserId()!=null){
			criteria.andUserIdEqualTo(houseParam.getUserId());
		}
		if (houseParam.getIsdel()!=null){
			criteria.andIsdelEqualTo(houseParam.getIsdel());
		}
		if (houseParam.getIspass()!=null){
			criteria.andIspassEqualTo(houseParam.getIspass());
		}
		PageHelper.startPage(houseParam.getPage(),houseParam.getRows());
		List<House> houses = houseMapper.selectByExample(houseExample);
		for (House house:houses){
			Street street = streetMapper.selectByPrimaryKey(house.getStreetId());
			street.setDistrict(districtMapper.selectByPrimaryKey(street.getDistrictId()));

			house.setType(typeMapper.selectByPrimaryKey(house.getTypeId()));
			house.setUsers(usersMapper.selectByPrimaryKey(house.getUserId()));
			house.setStreet(street);
		}
		PageInfo<House> pageInfo = new PageInfo<>(houses);
		return pageInfo;
	}

	//房主
	@Override
	public boolean addHouse(House house) {
		int i = houseMapper.insertSelective(house);
		return i>0;
	}

	//房主
	@Override
	public boolean updateHouse(House newHouse,String parentPath) {
		House oldHouse = houseMapper.selectByPrimaryKey(newHouse.getId());
		if (oldHouse==null){
			System.out.println("oldHouse is null!!!!!!!!!");
		}
		if (oldHouse.getPath()!=null){
			File oldImg = new File(parentPath,oldHouse.getPath());
			oldImg.delete();
		}
		int i = houseMapper.updateByPrimaryKeySelective(newHouse);
		return i>0;
	}

	@Override
	public int deleteHouses(List<Integer> ids) {
		HouseExample houseExample = new HouseExample();
		HouseExample.Criteria criteria = houseExample.createCriteria();
		int i = houseMapper.deleteByExample(houseExample);
		return i;
	}

	@Override
	public boolean deleteHouse(House house) {
		house.setIsdel(1);
		int i = houseMapper.updateByPrimaryKeySelective(house);
		return i>0;
	}

	@Override
	public boolean verifyHouse(House house) {
		int i = houseMapper.updateByPrimaryKeySelective(house);
		return i>0;
	}

	public List<Integer> getStreetIds(Integer districtId){
		StreetExample streetExample = new StreetExample();
		StreetExample.Criteria criteria = streetExample.createCriteria();
		criteria.andDistrictIdEqualTo(districtId);
		List<Street> streets = streetMapper.selectByExample(streetExample);
		List<Integer> streetIds = new ArrayList<>();
		for (Street street:streets){
			streetIds.add(street.getId());
		}
		return streetIds;
	}
	@Override
	public PageInfo<House> getHouseList(HouseListParam houseListParam) {
		HouseExample houseExample = new HouseExample();
		HouseExample.Criteria criteria = houseExample.createCriteria();
		criteria.andIsdelEqualTo(0);
		criteria.andIspassEqualTo(1);
		if (houseListParam.getTitle()!=null&&!houseListParam.getTitle().isEmpty()){
			criteria.andTitleLike("%"+houseListParam.getTitle()+"%");
		}
		if(houseListParam.getMinPrice()!=null){
			criteria.andPriceGreaterThanOrEqualTo(houseListParam.getMinPrice());
		}
		if (houseListParam.getMaxPrice()!=null){
			criteria.andPriceLessThanOrEqualTo(houseListParam.getMaxPrice());
		}
		if (houseListParam.getDistrictId()!=null){
			criteria.andStreetIdIn(getStreetIds(houseListParam.getDistrictId()));
		}
		if (houseListParam.getStreetId()!=null){
			criteria.andStreetIdEqualTo(houseListParam.getStreetId());
		}
		if (houseListParam.getTypeId()!=null){
			criteria.andTypeIdEqualTo(houseListParam.getTypeId());
		}
		if (houseListParam.getFloorage()!=null){
			criteria.andFloorageEqualTo(houseListParam.getFloorage());
		}
		PageHelper.startPage(houseListParam.getPage(),houseListParam.getRows());
		List<House> houses = houseMapper.selectByExample(houseExample);
		for (House house:houses){
			Street street = streetMapper.selectByPrimaryKey(house.getStreetId());
			street.setDistrict(districtMapper.selectByPrimaryKey(street.getDistrictId()));

			house.setType(typeMapper.selectByPrimaryKey(house.getTypeId()));
			house.setUsers(usersMapper.selectByPrimaryKey(house.getUserId()));
			house.setStreet(street);
		}
		PageInfo<House> pageInfo = new PageInfo<>(houses);
		return pageInfo;
	}

	@Override
	public PageInfo<House> getDeletedHouses(HouseParam houseParam) {
		HouseExample houseExample = new HouseExample();
		HouseExample.Criteria criteria = houseExample.createCriteria();
		if (houseParam.getUserId()!=null){
			criteria.andUserIdEqualTo(houseParam.getUserId());
		}
		if (houseParam.getIsdel()!=null){
			criteria.andIsdelEqualTo(houseParam.getIsdel());
		}
		PageHelper.startPage(houseParam.getPage(),houseParam.getRows());
		List<House> houses = houseMapper.selectByExample(houseExample);
		for (House house:houses){
			Street street = streetMapper.selectByPrimaryKey(house.getStreetId());
			street.setDistrict(districtMapper.selectByPrimaryKey(street.getDistrictId()));

			house.setType(typeMapper.selectByPrimaryKey(house.getTypeId()));
			house.setUsers(usersMapper.selectByPrimaryKey(house.getUserId()));
			house.setStreet(street);
		}
		PageInfo<House> pageInfo = new PageInfo<>(houses);
		return pageInfo;
	}

	@Override
	public boolean recycleHouse(House house) {
		int i = houseMapper.updateByPrimaryKeySelective(house);
		return i>0;
	}
}
