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
 * @Description  : Servlet Request�� Encoding ����� ���͸��ϴ� Ŭ����
 * @History      : 
 */
public class FTCEncodingFilter implements Filter {
	/** ���ڵ� */
	private String encoding = null;

	/** ���� */
	protected FilterConfig filterConfig = null;

	//private Log log = LogFactory.getLog(this.getClass());

	/**
	 * �Ҹ���
	 */
	public void destroy() {		
		this.encoding = null;
		this.filterConfig = null;
	}

	/**
	 * ���� ó��
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
	 * �ʱ�ȭ
	 * 
	 * @param filterConfig
	 * @throws ServletException
	 */
	public void init(FilterConfig filterConfig) throws ServletException {		
		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
	}

	/**
	 * ���͸� ���´�.
	 * 
	 * @return
	 */
	public FilterConfig getFilterConfig() {
		return this.filterConfig;
	}

	/**
	 * ���͸� �����Ѵ�.
	 * 
	 * @param cfg
	 */
	public void setFilterConfig(FilterConfig cfg) {
		this.filterConfig = cfg;
	}
}
