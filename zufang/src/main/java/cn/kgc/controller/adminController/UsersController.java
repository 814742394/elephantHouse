package cn.kgc.controller.adminController;

import cn.kgc.entity.Users;
import cn.kgc.service.adminService.UserService;
import cn.kgc.util.UserParam;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller(value = "aUsersController")
@RequestMapping(value = "/admin/")
public class UsersController {
	@Autowired
	UserService userService;
	@RequestMapping(value = "getUsers",produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String,Object> getUsers(
			UserParam userParam
	){
		PageInfo<Users> pageInfo = userService.getUsers(userParam);
		List<Users> list = pageInfo.getList();
		long total = pageInfo.getTotal();
		Map<String,Object> map = new HashMap<>();
		map.put("total",total);
		map.put("rows",list);
		return map;
	}
	@RequestMapping(value = "deleteUsers",produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String,Object> deleteUsers(
			@RequestBody List<Integer> ids
	){
		Map<String,Object> map = new HashMap<>();
		if (userService.deleteUsers(ids)>0){
			map.put("success",true);
			map.put("message","删除成功");
		}
		return map;
	}
}
