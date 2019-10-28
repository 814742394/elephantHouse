package cn.kgc.controller.adminController;

import cn.kgc.entity.Type;
import cn.kgc.service.adminService.TypeService;
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

@Controller(value = "aTypeController")
@RequestMapping(value = "/admin/")
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
	@RequestMapping(value = "getTypes",produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String,Object> getTypes(
			PageUtil pageUtil
	){
		PageInfo<Type> pageInfo = typeService.getTypes(pageUtil);
		List<Type> list = pageInfo.getList();
		long total = pageInfo.getTotal();
		Map<String,Object> map = new HashMap<>();
		map.put("total",total);
		map.put("rows",list);
		return map;
	}
	@RequestMapping(value = "addType",produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String,Object> addType(
			Type type
	){
		Map<String,Object> map = new HashMap<>();
		if (typeService.addType(type)){
			map.put("success",true);
			map.put("message","添加成功");
		}
		return map;
	}
	@RequestMapping(value = "updateType",produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String,Object> updateType(
			Type type
	){
		Map<String,Object> map = new HashMap<>();
		if (typeService.updateType(type)){
			map.put("success",true);
			map.put("message","修改成功");
		}
		return map;
	}
	@RequestMapping(value = "deleteTypes",produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String,Object> deleteTypes(
			@RequestBody List<Integer> ids
	){
		Map<String,Object> map = new HashMap<>();
		if (typeService.deleteTypes(ids)>0){
			map.put("success",true);
			map.put("message","删除成功");
		}
		return map;
	}
}
