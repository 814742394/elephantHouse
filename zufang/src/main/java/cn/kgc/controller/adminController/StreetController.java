package cn.kgc.controller.adminController;

import cn.kgc.entity.Street;
import cn.kgc.service.adminService.StreetService;
import cn.kgc.util.StreetParam;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller(value = "aStreetController")
@RequestMapping(value = "/admin/")
public class StreetController {
	@Autowired
	StreetService streetService;
	@RequestMapping(value = "getStreets",produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String,Object> getStreets(
			StreetParam streetParam
	){
		PageInfo<Street> pageInfo = streetService.getStreets(streetParam);
		List<Street> list = pageInfo.getList();
		long total = pageInfo.getTotal();
		Map<String,Object> map = new HashMap<>();
		map.put("total",total);
		map.put("rows",list);
		return map;
	}
	@RequestMapping(value = "addStreet",produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String,Object> addStreet(
			Street street
	){
		Map<String,Object> map = new HashMap<>();
		if (streetService.addStreet(street)){
			map.put("success",true);
			map.put("message","添加成功");
		}
		return map;
	}
	@RequestMapping(value = "updateStreet",produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String,Object> updateStreet(
			Street street
	){
		Map<String,Object> map = new HashMap<>();
		if (streetService.updateStreet(street)){
			map.put("success",true);
			map.put("message","修改成功");
		}
		return map;
	}
	@RequestMapping(value = "deleteStreets",produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String,Object> deleteStreets(
			@RequestBody List<Integer> ids
	){
		Map<String,Object> map = new HashMap<>();
		if (streetService.deleteStreets(ids)>0){
			map.put("success",true);
			map.put("message","修改成功");
		}
		return map;
	}
	@RequestMapping(value = "getStreetsByDistrictId",produces = "application/json;charset=UTF-8")
	@ResponseBody
	List<Street> getStreetsByDistrictId(
			@RequestParam(defaultValue = "1001") Integer districtId
	){
		List<Street> streets = streetService.getStreetsByDistrictId(districtId);
		return streets;
	}
}
