package cn.kgc.service.adminService;

import cn.kgc.entity.House;
import cn.kgc.util.HouseListParam;
import cn.kgc.util.HouseParam;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface HouseService {
	PageInfo<House> getHouses(HouseParam houseParam);

	boolean addHouse(House house);

	boolean updateHouse(House house,String parentPath);

	int deleteHouses(List<Integer> ids);

	boolean deleteHouse(House house);

	boolean verifyHouse(House house);

	PageInfo<House> getHouseList(HouseListParam houseListParam);

	PageInfo<House> getDeletedHouses(HouseParam houseParam);

	boolean recycleHouse(House house);
}
