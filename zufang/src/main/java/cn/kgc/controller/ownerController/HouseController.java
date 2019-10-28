package cn.kgc.controller.ownerController;

import cn.kgc.entity.House;
import cn.kgc.entity.Users;
import cn.kgc.service.adminService.HouseService;
import cn.kgc.util.HouseParam;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller(value = "oHouseController")
@RequestMapping("/owner/")
public class HouseController {
	@Autowired
	HouseService houseService;

	@RequestMapping(value = "recycleHouse", produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String, Object> recycleHouse(
			House house
	) {
		Map<String, Object> map = new HashMap<>();
		house.setIsdel(0);
		if (houseService.recycleHouse(house)) {
			map.put("success", true);
			map.put("message", "恢复成功");
		}
		return map;
	}

	@RequestMapping(value = "getDeletedHouses",produces = "application/json;charset=utf-8")
	@ResponseBody
	Map<String, Object> getDeletedHouses(
			HttpSession session,
			HouseParam houseParam,
			HttpServletResponse response
	) {
		Map<String, Object> map = new HashMap<>();
		Users loginuser = (Users) session.getAttribute("loginuser");
		/**
		 * isdel(Integer state);1查已删除，0查未删除
		 */
		houseParam.setIsdel(1);
		houseParam.setUserId(loginuser.getId());
		PageInfo<House> pageInfo = houseService.getDeletedHouses(houseParam);
		List<House> list = pageInfo.getList();
		for (House house:list){
			System.out.println(house);
		}
		long total = pageInfo.getTotal();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	@RequestMapping(value = "getHouses", produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String, Object> getHouses(
			HttpSession session,
			HouseParam houseParam,
			HttpServletResponse response,
			HttpServletRequest request
	) {
		Map<String, Object> map = new HashMap<>();
		Users loginuser = (Users) session.getAttribute("loginuser");
		/**
		 * isdel:0未删除，1已删除
		 */
		houseParam.setUserId(loginuser.getId());
		houseParam.setIsdel(0);

		PageInfo<House> pageInfo = houseService.getHouses(houseParam);
		List<House> list = pageInfo.getList();
		long total = pageInfo.getTotal();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	@RequestMapping(value = "addHouse", produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String, Object> addHouse(
			HttpSession session,
			House house,
			@RequestParam(value = "houseImg", required = false) MultipartFile attach
	) {
		String fileName = null;
		Map<String,Object> map = new HashMap<>();
		if (attach!=null&&!attach.isEmpty()) {
			String originalFilename = attach.getOriginalFilename();
			String extension = FilenameUtils.getExtension(originalFilename);
			//限制文件大小为500kb
			if (attach.getSize() > 500000) {
				map.put("message", "文件不能超过500kb");
			} else if (extension.equalsIgnoreCase("jpg")
					|| extension.equalsIgnoreCase("png")
					|| extension.equalsIgnoreCase("jpeg")
					|| extension.equalsIgnoreCase("pneg")) {
				String parentPath = "D:\\upload\\img";
				fileName = System.currentTimeMillis() + "." + extension;
				File file = new File(parentPath, fileName);
				if (!file.exists()) {
					file.mkdirs();
				}
				try {
					attach.transferTo(file);
				} catch (IOException e) {
					e.printStackTrace();
					map.put("message", "文件上传失败");
				}
			} else {
				map.put("message", "文件格式不正确");
				return map;
			}
		}

		Users loginuser = (Users) session.getAttribute("loginuser");
		house.setUserId(loginuser.getId());
		house.setIsdel(0);
		house.setIspass(0);
		if (fileName!=null){
			house.setPath(fileName);
		}


		if (houseService.addHouse(house)) {
			map.put("success", true);
			map.put("message", "添加成功");
		}
		return map;
	}

	@RequestMapping(value = "updateHouse", produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String, Object> updateHouse(
			HttpSession session,
			House house,
			@RequestParam(value = "houseImg", required = false) MultipartFile attach
	) {
		String parentPath = "D:\\upload\\img";
		String fileName = null;
		Map<String,Object> map = new HashMap<>();
		if (!attach.isEmpty()) {
			String originalFilename = attach.getOriginalFilename();
			String extension = FilenameUtils.getExtension(originalFilename);
			//限制文件大小为500kb
			if (attach.getSize() > 500000) {
				map.put("message", "文件不能超过500kb");
			} else if (extension.equalsIgnoreCase("jpg")
					|| extension.equalsIgnoreCase("png")
					|| extension.equalsIgnoreCase("jpeg")
					|| extension.equalsIgnoreCase("pneg")) {
				fileName = System.currentTimeMillis() + "." + extension;
				File file = new File(parentPath, fileName);
				if (!file.exists()) {
					file.mkdirs();
				}
				try {
					attach.transferTo(file);
				} catch (IOException e) {
					e.printStackTrace();
					map.put("message", "文件上传失败");
				}
			} else {
				map.put("message", "文件格式不正确");
				return map;
			}
		}

		if (fileName!=null){
			house.setPath(fileName);
		}
		if (houseService.updateHouse(house,parentPath)) {
			map.put("success", true);
			map.put("message", "修改成功");
		}
		return map;
	}

	@RequestMapping(value = "deleteHouse", produces = "application/json;charset=UTF-8")
	@ResponseBody
	Map<String,Object> deleteHouse(
			House house
	){
		Map<String, Object> map = new HashMap<>();
		if (houseService.deleteHouse(house)) {
			map.put("success", true);
			map.put("message", "删除成功");
		}
		return map;
	}
}
