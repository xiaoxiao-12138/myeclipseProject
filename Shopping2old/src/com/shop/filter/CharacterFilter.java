package com.shop.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;


public class CharacterFilter implements Filter {

    public CharacterFilter() {
       
    }


	public void init(FilterConfig fConfig) throws ServletException {
		
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request2=(HttpServletRequest) request;
		HttpServletResponse response2=(HttpServletResponse) response;
		request2.setCharacterEncoding("utf-8");
		response2.setContentType("text/html;charset=utf-8");
		CharacterRequest characterRequset=new CharacterRequest(request2);
		chain.doFilter(characterRequset, response);
	}


	public void destroy() {
		
	}
}
class CharacterRequest extends HttpServletRequestWrapper{

	public CharacterRequest(HttpServletRequest request) {
		super(request);
		
	}
	public String getParameter(String name){
		String value=super.getParameter(name);
		if(value==null){
			return null;
		}
		String method=super.getMethod();
		if("get".equalsIgnoreCase(method)){
			try {
				value=new String(value.getBytes("iso-8859-1"),"utf-8");
			} catch (Exception e) {
				throw new  RuntimeException(e);
			}
		}
		return value;
		
	}
	}