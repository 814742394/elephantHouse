package cn.kgc.controller.adminController;

import cn.kgc.entity.House;
import cn.kgc.service.adminService.HouseService;
import cn.kgc.util.HouseParam;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller(value = "aHouseController")
@RequestMapping(value = "/admin/")
public class HouseController {
	@Autowired
	HouseService houseService;

	@RequestMapping(value = "getHouses", produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String, Object> getHouses(
			HttpSession session,
			HouseParam houseParam,
			HttpServletResponse response
	) {
		Map<String, Object> map = new HashMap<>();
		PageInfo<House> pageInfo = houseService.getHouses(houseParam);
		List<House> list = pageInfo.getList();
		for (House house:list){
			System.out.println(house);
		}
		long total = pageInfo.getTotal();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	@RequestMapping(value = "verifyHouse", produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String, Object> verifyHouse(
			House house
	) {
		Map<String, Object> map = new HashMap<>();
		house.setIspass(1);
		if (houseService.verifyHouse(house)) {
			map.put("success", true);
			map.put("message", "审核成功");
		}
		return map;
	}

	@RequestMapping(value = "deleteHouses", produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String, Object> deleteHouses(
			@RequestBody List<Integer> ids
	) {
		Map<String, Object> map = new HashMap<>();
		if (houseService.deleteHouses(ids) > 0) {
			map.put("success", true);
			map.put("message", "删除成功");
		}
		return map;
	}


}
