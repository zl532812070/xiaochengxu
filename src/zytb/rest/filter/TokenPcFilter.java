package zytb.rest.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zytb.bean.User;
import zytb.util.CacheTool;

public class TokenPcFilter implements Filter {

	private List<String> exceptUrls;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		if (!exceptUrls.contains(request.getRequestURI())) {// exceptUrls中定义的路径不过滤
			String token = request.getHeader("Authority");
			if (token != null) {
				HashMap<String, User> mySession = CacheTool.getSession();
				if (!mySession.containsKey(token)) {// mySession中是否有token
					response.getWriter().println("{success:false}");
				}else{
					chain.doFilter(request, response);
				}
			} else {
				// System.out.println("没有token参数");
				response.getWriter().println("{success:false}");
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		exceptUrls = Arrays.asList(config.getInitParameter("exceptUrls").split(","));
	}

}
