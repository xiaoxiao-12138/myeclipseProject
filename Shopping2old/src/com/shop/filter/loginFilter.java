package com.shop.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.entity.User;

/**
 * Servlet Filter implementation class loginFilter
 */
public class loginFilter implements Filter {
    private String excludedPage;
    private String[] excludedPages;
    private String userId;

	public void init(FilterConfig filterConfig) throws ServletException {
		excludedPage = filterConfig.getInitParameter("ignores");//�˴���ignores������web.xml���������һ����
        if (excludedPage != null && excludedPage.length() > 0){
            excludedPages = excludedPage.split(",");
        }
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		userId = (String)((HttpServletRequest)request).getSession().getAttribute("userId");
		String url = ((HttpServletRequest) request).getRequestURI();
		//System.out.println(url);
		boolean flag = false;
        for (String page:excludedPages) {
        	if (url.contains(page)){
                flag = true;
            }
        }
        if(flag || userId!=null){
            chain.doFilter(request,response);
        }else{
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}

	
	public void destroy() {
		
	}
}
