package cn.kgc.service.adminService;

import cn.kgc.entity.Type;
import cn.kgc.util.PageUtil;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TypeService {
	PageInfo<Type> getTypes(PageUtil pageUtil);

	boolean addType(Type type);

	boolean updateType(Type type);

	int deleteTypes(List<Integer> ids);

	List<Type> getAllTypes();
}
