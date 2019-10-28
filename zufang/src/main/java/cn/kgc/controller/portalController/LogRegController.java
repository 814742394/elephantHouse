package cn.kgc.controller.portalController;

import cn.kgc.entity.Users;
import cn.kgc.service.portalService.PotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/portal/")
public class LogRegController {
	@Autowired
	PotalService potalService;

	@RequestMapping(value = "isUserLogin",produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String,Object> isUserLogin(
		HttpSession session
	){
		Map<String,Object> map = new HashMap<>();
		Users loginuser = (Users) session.getAttribute("loginuser");
		if (loginuser!=null){
			map.put("success",true);
			map.put("message",loginuser.getName());
		}
		return map;
	}

	@RequestMapping(value = "isUserNameExist",produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String,Object> isUserNameExist(
		Users users
	){
		Map<String,Object> map = new HashMap<>();
		if (potalService.isUserNameExist(users)){
			map.put("success",true);
			map.put("message","用户名已存在");
		}else{
			map.put("message","用户名可用");
		}
		return map;
	}
	@RequestMapping(value = "reg",produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String,Object> reg(
		Users users
	){
		System.out.println("reg");
		Map<String,Object> map = new HashMap<>();
		if (potalService.reg(users)){
			map.put("success",true);
			map.put("message","注册成功");
		}else{
			map.put("message","注册失败");
		}
		return map;
	}
	@RequestMapping(value = "login_",produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String,Object> login_(
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session,
			Users user
			/*@RequestParam(value = "remember",defaultValue = "0") Integer remember*/

	){
		Map<String,Object> map = new HashMap<>();
		Users loginuser = potalService.login(user);
		if (loginuser!=null){
			session.setAttribute("loginuser",loginuser);
			session.setMaxInactiveInterval(600);

				Cookie cookie = new Cookie("JSESSIONID",session.getId());
				cookie.setMaxAge(600);
				response.addCookie(cookie);

			map.put("success",true);
			map.put("message","登录成功");
			if (loginuser.getIsadmin()==1){
				map.put("isadmin",true);
			}
		}else{
			map.put("message","用户名或密码错误");
		}
		return map;
	}
	@RequestMapping(value = "logout",produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String,Object> logout(
			HttpSession session
	){
		session.invalidate();
		Map<String,Object> map = new HashMap<>();
		map.put("success",true);
		map.put("message","退出成功");
		return map;
	}
}
