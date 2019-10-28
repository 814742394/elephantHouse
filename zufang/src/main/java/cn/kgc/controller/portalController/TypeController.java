package cn.kgc.controller.portalController;

import cn.kgc.entity.Type;
import cn.kgc.service.adminService.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller(value = "pTypeController")
@RequestMapping("/portal/")
public class TypeController {
	@Autowired
	TypeService typeService;
	@RequestMapping(value = "getAllTypes",produces = "application/json;charset=UTF-8")
	@ResponseBody
	List<Type> getAllTypes(

	){
		List<Type> types = typeService.getAllTypes();
		return types;
	}
}
