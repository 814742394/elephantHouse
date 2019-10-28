package cn.kgc.controller.portalController;

import cn.kgc.entity.Street;
import cn.kgc.service.adminService.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller(value = "pStreectController")
@RequestMapping("/portal/")
public class StreetController {
	@Autowired
	StreetService streetService;
	@RequestMapping(value = "getStreetsByDistrictId",produces = "application/json;charset=UTF-8")
	@ResponseBody
	List<Street> getStreetsByDistrictId(
			@RequestParam(defaultValue = "1001") Integer districtId
	){
		List<Street> streets = streetService.getStreetsByDistrictId(districtId);
		return streets;
	}
}
