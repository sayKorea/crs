package fatca.common.taglib;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;


/**
 * @File Name    : DebugTag.java
 * @Package Name : fatca.common.taglib;
 * @author       : 
 * @Description  : jsp명 표시 디버그 태그라이브러리
 * @History      : 
 */
public class FTCDebugTag extends FTCLoggerTag {
    
	private static final long serialVersionUID = -5463638936474958024L;

	protected boolean isEnabled(Log logCategory) {
        return logCategory.isDebugEnabled();
    }

    protected void log(Log logCategory, String message) {
    	message += "  [" + ((HttpServletRequest) pageContext
				.getRequest()).getRequestURI() + "]";
        logCategory.debug(message);
    }
}

