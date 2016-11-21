package fatca.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * @File Name    : EncodingFilter.java
 * @Package Name : fatca.common.filter;
 * @author       : Administrator
 * @Description  : Servlet Request의 Encoding 방식을 필터링하는 클래스
 * @History      : 
 */
public class FTCEncodingFilter implements Filter {
	/** 인코딩 */
	private String encoding = null;

	/** 필터 */
	protected FilterConfig filterConfig = null;

	//private Log log = LogFactory.getLog(this.getClass());

	/**
	 * 소멸자
	 */
	public void destroy() {		
		this.encoding = null;
		this.filterConfig = null;
	}

	/**
	 * 필터 처리
	 * 
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request.getCharacterEncoding() == null) {
			if (this.encoding != null) {
				request.setCharacterEncoding(this.encoding);
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * 초기화
	 * 
	 * @param filterConfig
	 * @throws ServletException
	 */
	public void init(FilterConfig filterConfig) throws ServletException {		
		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
	}

	/**
	 * 필터를 얻어온다.
	 * 
	 * @return
	 */
	public FilterConfig getFilterConfig() {
		return this.filterConfig;
	}

	/**
	 * 필터를 설정한다.
	 * 
	 * @param cfg
	 */
	public void setFilterConfig(FilterConfig cfg) {
		this.filterConfig = cfg;
	}
}
