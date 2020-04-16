package com.vergilyn.examples.springboot.filter.custom;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.vergilyn.examples.springboot.filter.common.FilterRule;
import com.vergilyn.examples.springboot.filter.common.ParameterRequestWrapper;

import org.springframework.context.annotation.Profile;


/**
 * @ WebFilter：将一个实现了javax.servlet.Filter接口的类定义为过滤器
 * SpingBootApplication加入@ServletComponentScan扫描注入webFilter。
 */
@WebFilter(filterName="customFilter",urlPatterns="/needFilter")
@Profile("servlet")
public class ServletFilter implements Filter {
	private final static String clazz = ServletFilter.class.getSimpleName();
	
	@Override
	public void destroy() {
		System.out.println(clazz + " >>>> destroy().");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain) throws IOException, ServletException {
		System.out.println(clazz + " >>>> doFilter(...).");
		req = new ParameterRequestWrapper((HttpServletRequest)req, FilterRule.filterRule(req));
		chain.doFilter(req, resp);
	}


	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println(clazz + " >>>> init(...).");
	}

}
