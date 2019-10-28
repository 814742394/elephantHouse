package cn.kgc.filter;

import cn.kgc.entity.Users;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
		HttpServletRequest request= (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse)resp;
		HttpSession session = request.getSession();
		Users loginuser = (Users)session.getAttribute("loginuser");
		if (loginuser.getIsadmin()!=1){
			response.sendRedirect("/portal/login.htm");
		}else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig config) throws ServletException {

	}

}
