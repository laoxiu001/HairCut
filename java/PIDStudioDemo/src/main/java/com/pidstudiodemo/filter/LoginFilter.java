package com.pidstudiodemo.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;

@WebFilter(filterName="loginFilter",urlPatterns="/*")
public class LoginFilter implements Filter {
	//Pattern java正则表达式类 
	@Value("$(serverurl)")
    private String serverurl;
	protected static List<Pattern> patterns = new ArrayList<Pattern>();
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		patterns.add(Pattern.compile("login.html"));
        patterns.add(Pattern.compile("forget.thom"));
        patterns.add(Pattern.compile("login/loginAction"));
        patterns.add(Pattern.compile("login/forgotPassword"));
        patterns.add(Pattern.compile("util/.*"));
        patterns.add(Pattern.compile(".idea/.*"));
        patterns.add(Pattern.compile(".*[(\\.js)||(\\.css)||(\\.png)]"));
		System.out.println("过滤器初始化");
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		 HttpServletRequest httpRequest = (HttpServletRequest) request;
	        HttpServletResponse httpResponse = (HttpServletResponse) response;
	        String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
	        if (url.startsWith("/") && url.length() > 1) {
	            url = url.substring(1);
	        }
	        if (isInclude(url)){
	            chain.doFilter(httpRequest, httpResponse);
	            return;
	        } else {
	            HttpSession session = httpRequest.getSession();
	            if (session.getAttribute("managerNumber") != null){
	                // session存在
	                chain.doFilter(httpRequest, httpResponse);
	                return;
	            } else {
	                // session不存在 准备跳转失败
	                httpResponse.sendRedirect("/login/loginAction");
	            }
	        }
		  System.out.println("执行过滤操作");
	      chain.doFilter(request, response);
		
	}
	  /**
     * 是否需要过滤
     * @param url
     * @return
     */
    private boolean isInclude(String url) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }

	public void destroy() {
		// TODO Auto-generated method stub
		 System.out.println("过滤器销毁");
		
	}
	 

}
