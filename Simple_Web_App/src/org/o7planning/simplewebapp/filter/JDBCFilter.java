package org.o7planning.simplewebapp.filter;

import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.o7planning.simplewebapp.conn.ConnectionUtils;
import org.o7planning.simplewebapp.utils.MyUtils;

/**
 * Servlet Filter implementation class JDBCFilter
 */
@WebFilter(filterName = "jdbcFilter", urlPatterns = { "/*" })
public class JDBCFilter implements Filter {

    /**
     * Default constructor. 
     */
    public JDBCFilter() {
        // TODO Auto-generated constructor stub
    }
    
   
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	private boolean needJDBC(HttpServletRequest req) {
		System.out.println("JDBC Filter");
		
		String servletPath = req.getServletPath();
		
		String pathInfo = req.getPathInfo();
		
		String urlPattern = servletPath;
		
		if(pathInfo != null) {
			urlPattern = servletPath + "/*";
		}
		
		Map<String, ? extends ServletRegistration> servletRegistrations = req.getServletContext().getServletRegistrations();
		
		Collection<? extends ServletRegistration> values = servletRegistrations.values();
		
		for(ServletRegistration sr : values) {
			Collection<String> mappings = sr.getMappings();
			
			if(mappings.contains(urlPattern)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		if(this.needJDBC(req)) {
			
			System.out.println("Open Connection for: " + req.getServletPath());
			
			Connection conn = null;
			
			try {
				conn = ConnectionUtils.getConnection();
				
				conn.setAutoCommit(false);
				
				MyUtils.storeConnection(request, conn);
				
				chain.doFilter(request, response);
				
				conn.commit();
			}catch(Exception e) {
				e.printStackTrace();
				ConnectionUtils.rollbackQuitely(conn);
				throw new ServletException();
				
			}finally {
				ConnectionUtils.closeQuietly(conn);
			}
			
			
		}else {
			chain.doFilter(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
