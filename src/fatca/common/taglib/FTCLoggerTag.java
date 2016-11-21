package fatca.common.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @File Name    : LoggerTag.java
 * @Package Name : fatca.common.taglib;
 * @author       : 
 * @Description  : 로그 태그 라이브러리
 * @History      : 
 */
public abstract class FTCLoggerTag extends BodyTagSupport {
    
	private static final long serialVersionUID = 8871887471208043976L;
	
	private String category;
    private String message;

    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    

	/**
	 *  태그 라이브러리 시작
	 * 
	 * @return int
	 * @throws JspException
	 */ 
    public int doStartTag() throws JspException  {
        if ( this.message != null ) {
            Log logCategory = getLoggingCategory();
            if ( isEnabled( logCategory ) ) {
                // Log now as doAfterBody() may not be called for an empty tag 
                log( logCategory, this.message );
            }
            return SKIP_BODY;
        }
        return EVAL_BODY_TAG;
    }
    

	/**
	 *  태그 라이브러리 종료
	 * 
	 * @return int
	 * @throws JspException
	 */
    public int doAfterBody() throws JspException {
        if (this.message == null) {
            Log logCategory = getLoggingCategory();
            if ( isEnabled( logCategory ) ) {
                log( logCategory, getBodyContent().getString().trim() );
            }
        }
        return SKIP_BODY;
    }
    
    // Implementation methods
    //------------------------------------------------------------------------- 
    protected abstract boolean isEnabled(Log logCategory);
    protected abstract void log(Log logCategory, String message);
    
    protected Log getLoggingCategory() {
        if ( this.category == null ) {
            // used to be Category.getRoot();
            return LogFactory.getLog("");
        }
        else {
            return LogFactory.getLog( this.category );
        }
    }
}

