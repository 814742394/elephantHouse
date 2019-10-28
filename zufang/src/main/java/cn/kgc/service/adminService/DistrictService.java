package cn.kgc.service.adminService;

import cn.kgc.entity.District;
import cn.kgc.util.PageUtil;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DistrictService {
	PageInfo<District> getDistricts(PageUtil pageUtil);

	boolean addDistrict(District district);

	boolean updateDistrict(District district);

	Integer deleteDistricts(List<Integer> values);

	List<District> getAllDistrict();
}
