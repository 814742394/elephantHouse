package cn.kgc.controller.adminController;

import cn.kgc.entity.District;
import cn.kgc.service.adminService.DistrictService;
import cn.kgc.util.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller(value = "aDistrictController")
@RequestMapping(value = "/admin/")
public class DistrictController {
	@Autowired
	DistrictService districtService;
	@RequestMapping(value = "getDistricts")
	@ResponseBody
	Map<String,Object> getDistricts(
			PageUtil pageUtil
	){
		PageInfo<District> pageInfo = districtService.getDistricts(pageUtil);
		List<District> list = pageInfo.getList();
		long total = pageInfo.getTotal();
		Map<String,Object> map = new HashMap<>();
		map.put("total",total);
		map.put("rows",list);
		return map;
	}
	@RequestMapping(value = "getAllDistrict")
	@ResponseBody
	List<District>  getAllDistrict(

	){
		List<District> districts = districtService.getAllDistrict();
		return districts;
	}
	@RequestMapping(value = "addDistrict",produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String,Object> addDistrict(
			District district
	){
		Map<String,Object> map = new HashMap<>();
		if (districtService.addDistrict(district)){
			map.put("success",true);
			map.put("message","添加成功");
		}
		return map;
	}
	@RequestMapping(value = "updateDistrict",produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String,Object> updateDistrict(
			District district
	){
		Map<String,Object> map = new HashMap<>();
		if (districtService.updateDistrict(district)){
			map.put("success",true);
			map.put("message","修改成功");
		}
		return map;
	}
	@RequestMapping(value = "deleteDistricts",produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String,Object> deleteDistricts(
			@RequestBody List<Integer> ids
	){
		Map<String,Object> map = new HashMap<>();
		if (districtService.deleteDistricts(ids)>0){
			map.put("success",true);
			map.put("message","删除成功");
		}
		return map;
	}
}
