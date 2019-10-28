package cn.kgc.controller.portalController;

import cn.kgc.entity.District;
import cn.kgc.service.adminService.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller(value = "pDistrictController")
@RequestMapping("/portal/")
public class DistrictController {
	@Autowired
	DistrictService districtService;
	@RequestMapping(value = "getAllDistrict")
	@ResponseBody
	List<District> getAllDistrict(

	){
		List<District> districts = districtService.getAllDistrict();
		return districts;
	}
}
