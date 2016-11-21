package fatca.common.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @File Name    : UrlBaseTag.java
 * @Package Name : fatca.system.action
 * @author       : 
 * @Description  : UrlBaseTag
 * @History      : 
 */
public class FTCUrlBaseTag extends TagSupport {

	//private Log log = LogFactory.getLog(this.getClass());
	
	//private static String webContext = null;

	/**
	 * Render the specified error messages if there are any.
	 *
	 * @return
	 * @throws JspException
	 *                      if a JSP exception has occurred
	 */
	public int doStartTag() throws JspException {

		String webContext = null;
		// Print the results to our output writer
		try {
			if (webContext == null){
				webContext = ((HttpServletRequest) pageContext
						.getRequest()).getContextPath();
			}
			
			(pageContext.getOut()).print(webContext);
		} catch (java.io.IOException ioe) {
			throw new JspException();
		}
		// Continue processing this page
		return (EVAL_BODY_INCLUDE);

	}

	/**
	 * Release any acquired resources.
	 */
	public void release() {

		super.release();

	}

}