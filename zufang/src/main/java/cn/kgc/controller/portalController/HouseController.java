package cn.kgc.controller.portalController;

import cn.kgc.entity.House;
import cn.kgc.service.adminService.HouseService;
import cn.kgc.util.HouseListParam;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller(value = "pHouseController")
@RequestMapping(value = "/portal/")
public class HouseController {
	@Autowired
	HouseService houseService;
	@RequestMapping(value = "getHouseList",produces = "application/json;charset=utf-8")
	@ResponseBody
	Map<String,Object> getHouseList(
			HouseListParam houseListParam
	){
		Map<String,Object> map = new HashMap<>();
		PageInfo<House> pageInfo = houseService.getHouseList(houseListParam);
		List<House> list = pageInfo.getList();
		long total = pageInfo.getTotal();
		map.put("rows",list);
		map.put("total",total);
		return map;
	}
}
