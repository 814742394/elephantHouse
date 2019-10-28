package cn.kgc.service.adminService;

import cn.kgc.entity.Street;
import cn.kgc.util.StreetParam;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface StreetService {
	PageInfo<Street> getStreets(StreetParam streetParam);

	boolean addStreet(Street street);

	boolean updateStreet(Street street);

	int deleteStreets(List<Integer> ids);

	List<Street> getStreetsByDistrictId(Integer districtId);
}
