
package fatca.common.taglib;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.taglib.logic.IterateTag;
import org.apache.struts.util.RequestUtils;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;

/**
 * @File Name    : BaseHandlerTag.java
 * @Package Name : fatca.common.taglib;
 * @author       : 
 * @Description  : cx 태그라이브러리 베이스 
 * @History      : 
 */
public class FTCBaseHandlerTag extends BodyTagSupport {

    /**
     * Commons Logging instance.
     */
    private Log log = LogFactory.getLog(FTCBaseHandlerTag.class);

    // ----------------------------------------------------- Instance Variables

    /**
     * The default Locale for our server.
     */
    //protected static final Locale defaultLocale = Locale.getDefault();

    //  Navigation Management

    /** Access key character. */
    protected String accesskey = null;

    /** Tab index value. */
    protected String tabindex = null;

    //  Indexing ability for Iterate

    /** Whether to created indexed names for fields
      * @since Struts 1.1
      */
    protected boolean indexed = false;

    //  Mouse Events

    /** Mouse click event. */
    private String onclick = null;

    /** Mouse double click event. */
    private String ondblclick = null;

    /** Mouse over component event. */
    private String onmouseover = null;

    /** Mouse exit component event. */
    private String onmouseout = null;

    /** Mouse moved over component event. */
    private String onmousemove = null;

    /** Mouse pressed on component event. */
    private String onmousedown = null;

    /** Mouse released on component event. */
    private String onmouseup = null;

    //  Keyboard Events

    /** Key down in component event. */
    private String onkeydown = null;

    /** Key released in component event. */
    private String onkeyup = null;

    /** Key down and up together in component event. */
    private String onkeypress = null;

    // Text Events

    /** Text selected in component event. */
    private String onselect = null;

    /** Content changed after component lost focus event. */
    private String onchange = null;

    // Focus Events and States

    /** Component lost focus event. */
    private String onblur = null;

    /** Component has received focus event. */
    private String onfocus = null;

    /** Component is disabled. */
    private boolean disabled = false;

    /** Component is readonly. */
    private boolean readonly = false;

    // CSS Style Support

    /** Style attribute associated with component. */
    private String style = null;

    /** Named Style class associated with component. */
    private String styleClass = null;

    /** Identifier associated with component.  */
    private String styleId = null;

    // Other Common Attributes

    /** The alternate text of this element. */
    private String alt = null;

    /** The message resources key of the alternate text. */
    private String altKey = null;

    /** The name of the message resources bundle for message lookups. */
    private String bundle = null;

    /** The name of the session attribute key for our locale. */
    private String locale = Globals.LOCALE_KEY;

    /** The advisory title of this element. */
    private String title = null;

    /** The message resources key of the advisory title. */
    private String titleKey = null;

    // ------------------------------------------------------------- Properties

    //private Class loopTagSupportClass = null;
    //private Method loopTagSupportGetStatus = null;
    //private Class loopTagStatusClass = null;
    //private Method loopTagStatusGetIndex = null;
    //private boolean triedJstlInit = false;
    //private boolean triedJstlSuccess = false;

    //  Navigation Management

    /** Sets the accessKey character. */
    public void setAccesskey(String accessKey) {
        this.accesskey = accessKey;
    }

    /** Returns the accessKey character. */
    public String getAccesskey() {
        return (this.accesskey);
    }

    /** Sets the tabIndex value. */
    public void setTabindex(String tabIndex) {
    	log.debug("");
        this.tabindex = tabIndex;
    }

    /**
     * @jsp.attribute	required="false"
     *                  rtexprvalue="true"
     *                  description="tabindex"
     */
    public String getTabindex() {
        return (this.tabindex);
    }

    //  Indexing ability for Iterate [since Struts 1.1]

    /** Sets the indexed value.
      * @since Struts 1.1
      */
    public void setIndexed(boolean indexed) {
        this.indexed = indexed;
    }

    /** Returns the indexed value.
      * @since Struts 1.1
      */
    public boolean getIndexed() {
        return (this.indexed);
    }

    // Mouse Events
    public void setOnclick(String onClick) {
        this.onclick = onClick;
    }

    /**
     * @jsp.attribute	required="false"
     *                  rtexprvalue="true"
     *                  description="onclick"
     */
    public String getOnclick() {
        return this.onclick;
    }

    /** Sets the onDblClick event handler. */
    public void setOndblclick(String onDblClick) {
        this.ondblclick = onDblClick;
    }

    /** Returns the onDblClick event handler. */
    public String getOndblclick() {
        return this.ondblclick;
    }

    /** Sets the onMouseDown event handler. */
    public void setOnmousedown(String onMouseDown) {
        this.onmousedown = onMouseDown;
    }

    /**
     * @jsp.attribute	required="false"
     *                  rtexprvalue="true"
     *                  description="onmousedown"
     */
    public String getOnmousedown() {
        return this.onmousedown;
    }

    /** Sets the onMouseUp event handler. */
    public void setOnmouseup(String onMouseUp) {
        this.onmouseup = onMouseUp;
    }

    /**
     * @jsp.attribute	required="false"
     *                  rtexprvalue="true"
     *                  description="onmouseup"
     */
    public String getOnmouseup() {
        return this.onmouseup;
    }

    /** Sets the onMouseMove event handler. */
    public void setOnmousemove(String onMouseMove) {
        this.onmousemove = onMouseMove;
    }

    /** Returns the onMouseMove event handler. */
    public String getOnmousemove() {
        return this.onmousemove;
    }

    /** Sets the onMouseOver event handler. */
    public void setOnmouseover(String onMouseOver) {
        this.onmouseover = onMouseOver;
    }

    /** Returns the onMouseOver event handler. */
    public String getOnmouseover() {
        return this.onmouseover;
    }

    /** Sets the onMouseOut event handler. */
    public void setOnmouseout(String onMouseOut) {
        this.onmouseout = onMouseOut;
    }

    /** Returns the onMouseOut event handler. */
    public String getOnmouseout() {
        return this.onmouseout;
    }

    // Keyboard Events

    /** Sets the onKeyDown event handler. */
    public void setOnkeydown(String onKeyDown) {
        this.onkeydown = onKeyDown;
    }

    /** Returns the onKeyDown event handler. */
    public String getOnkeydown() {
        return this.onkeydown;
    }

    /** Sets the onKeyUp event handler. */
    public void setOnkeyup(String onKeyUp) {
        this.onkeyup = onKeyUp;
    }

    /** Returns the onKeyUp event handler. */
    public String getOnkeyup() {
        return this.onkeyup;
    }

    /** Sets the onKeyPress event handler. */
    public void setOnkeypress(String onKeyPress) {
        this.onkeypress = onKeyPress;
    }

    /** Returns the onKeyPress event handler. */
    public String getOnkeypress() {
        return this.onkeypress;
    }

    // Text Events

    /** Sets the onChange event handler. */
    public void setOnchange(String onChange) {
        this.onchange = onChange;
    }

    /**
     * @jsp.attribute	required="false"
     *                  rtexprvalue="true"
     *                  description="onchange"
     */
    public String getOnchange() {
        return this.onchange;
    }

    /** Sets the onSelect event handler. */
    public void setOnselect(String onSelect) {
        this.onselect = onSelect;
    }

    /** Returns the onSelect event handler. */
    public String getOnselect() {
        return this.onselect;
    }

    // Focus Events and States

    /** Sets the onBlur event handler. */
    public void setOnblur(String onBlur) {
        this.onblur = onBlur;
    }

    /**
     * @jsp.attribute	required="false"
     *                  rtexprvalue="true"
     *                  description="onblur"
     */
    public String getOnblur() {
        return this.onblur;
    }

    /** Sets the onFocus event handler. */
    public void setOnfocus(String onFocus) {
        this.onfocus = onFocus;
    }

    /**
     * @jsp.attribute	required="false"
     *                  rtexprvalue="true"
     *                  description="onClick"
     */
    public String getOnfocus() {
        return this.onfocus;
    }

    /** Sets the disabled event handler. */
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    /** Returns the disabled event handler. */
    public boolean getDisabled() {
        return this.disabled;
    }

    /** Sets the readonly event handler. */
    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    /** Returns the readonly event handler. */
    public boolean getReadonly() {
        return this.readonly;
    }

    // CSS Style Support

    /** Sets the style attribute. */
    public void setStyle(String style) {
        this.style = style;
    }

    /**
     * @jsp.attribute	required="false"
     *                  rtexprvalue="true"
     *                  description="style"
     */
    public String getStyle() {
        return this.style;
    }

    /** Sets the style class attribute. */
    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    /**
     * @jsp.attribute	required="false"
     *                  rtexprvalue="true"
     *                  description="styleClass"
     */
    public String getStyleClass() {
        return this.styleClass;
    }

    /** Sets the style id attribute.  */
    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    /** Returns the style id attribute.  */
    public String getStyleId() {
        return this.styleId;
    }

    // Other Common Elements

    /** Returns the alternate text attribute. */
    public String getAlt() {
        return this.alt;
    }

    /** Sets the alternate text attribute. */
    public void setAlt(String alt) {
        this.alt = alt;
    }

    /** Returns the message resources key of the alternate text. */
    public String getAltKey() {
        return this.altKey;
    }

    /** Sets the message resources key of the alternate text. */
    public void setAltKey(String altKey) {
        this.altKey = altKey;
    }

    /** Returns the name of the message resources bundle to use. */
    public String getBundle() {
        return this.bundle;
    }

    /** Sets the name of the message resources bundle to use. */
    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    /** Returns the name of the session attribute for our locale. */
    public String getLocale() {
        return this.locale;
    }

    /** Sets the name of the session attribute for our locale. */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /** Returns the advisory title attribute. */
    public String getTitle() {
        return this.title;
    }

    /** Sets the advisory title attribute. */
    public void setTitle(String title) {
        this.title = title;
    }

    /** Returns the message resources key of the advisory title. */
    public String getTitleKey() {
        return this.titleKey;
    }

    /** Sets the message resources key of the advisory title. */
    public void setTitleKey(String titleKey) {
        this.titleKey = titleKey;
    }

    // --------------------------------------------------------- Public Methods

    /**
     * Release any acquired resources.
     */
    public void release() {

        super.release();
        this.accesskey = null;
        this.alt = null;
        this.altKey = null;
        this.bundle = null;
        this.indexed = false;
        this.locale = Globals.LOCALE_KEY;
        this.onclick = null;
        this.ondblclick = null;
        this.onmouseover = null;
        this.onmouseout = null;
        this.onmousemove = null;
        this.onmousedown = null;
        this.onmouseup = null;
        this.onkeydown = null;
        this.onkeyup = null;
        this.onkeypress = null;
        this.onselect = null;
        this.onchange = null;
        this.onblur = null;
        this.onfocus = null;
        this.disabled = false;
        this.readonly = false;
        this.style = null;
        this.styleClass = null;
        this.styleId = null;
        this.tabindex = null;
        this.title = null;
        this.titleKey = null;

    }

    // ------------------------------------------------------ Protected Methods

    /**
     * Return the text specified by the literal value or the message resources
     * key, if any; otherwise return <code>null</code>.
     *
     * @param literal Literal text value or <code>null</code>
     * @param key Message resources key or <code>null</code>
     *
     * @exception JspException if both arguments are non-null
     */
    protected String message(String literal, String key) throws JspException {

        if (literal != null) {
            if (key != null) {
                JspException e = new JspException("Jsp error");
                RequestUtils.saveException(pageContext, e);
//                throw e;
                return null;
            } else {
                return (literal);
            }
        } else {
            if (key != null) {
                return (RequestUtils.message(pageContext, getBundle(), getLocale(), key));
            } else {
                return null;
            }
        }

    }

   
    private Integer getJstlLoopIndex () {
    	
    	Class loopTagSupportClass = null;
        Method loopTagSupportGetStatus = null;
        Class loopTagStatusClass = null;
        Method loopTagStatusGetIndex = null;
        boolean triedJstlInit = false;
        boolean triedJstlSuccess = false;
    	
		if (!triedJstlInit) {
			triedJstlInit = true;
		    try {
		    	loopTagSupportClass =
			    RequestUtils.applicationClass("javax.servlet.jsp.jstl.core.LoopTagSupport");
		    	loopTagSupportGetStatus =
			    loopTagSupportClass.getDeclaredMethod("getLoopStatus", null);
		    	loopTagStatusClass =
			    RequestUtils.applicationClass("javax.servlet.jsp.jstl.core.LoopTagStatus");
		    	loopTagStatusGetIndex =
			    loopTagStatusClass.getDeclaredMethod("getIndex", null);
		    	triedJstlSuccess = true;
		    }
		    // These just mean that JSTL isn't loaded, so ignore
		    catch (ClassNotFoundException ex) {
		    	ex.getMessage();
		    }catch (NoSuchMethodException ex) {
		    	ex.getMessage();
		    }
		}
		if (triedJstlSuccess) {
		    try {
			Object loopTag = findAncestorWithClass(this, loopTagSupportClass);
			if (loopTag == null)  {
			    return null;
			}
			Object status = loopTagSupportGetStatus.invoke(loopTag, null);
			return (Integer) loopTagStatusGetIndex.invoke(status, null);
		    }
		    catch (IllegalAccessException ex) {
			log.error(ex.getMessage(), ex);
		    }
		    catch (IllegalArgumentException ex) {
			log.error(ex.getMessage(), ex);
		    }
		    catch (InvocationTargetException ex) {
			log.error(ex.getMessage(), ex);
		    }
		    catch (NullPointerException ex) {
			log.error(ex.getMessage(), ex);
		    }
		    catch (ExceptionInInitializerError ex) {
			log.error(ex.getMessage(), ex);
		    }
		}
		return null;
    }

    /**
     *  Appends bean name with index in brackets for tags with
     *  'true' value in 'indexed' attribute.
     *  @param handlers The StringBuffer that output will be appended to.
     *  @exception JspException if 'indexed' tag used outside of iterate tag.
     */
    protected void prepareIndex(StringBuffer handlers, String name) throws JspException {
	int index = 0;
	boolean found = false;

        // look for outer iterate tag
        IterateTag iterateTag = (IterateTag) findAncestorWithClass(this, IterateTag.class);
	// Look for JSTL loops
	if (iterateTag == null) {
	    Integer i = getJstlLoopIndex();
	    if (i != null) {
		index = i.intValue();
		found = true;
	    }
	} else {
	    index = iterateTag.getIndex();
	    found = true;
	}
        if (!found) {
            // this tag should only be nested in iteratetag, if it's not, throw exception
            JspException e = new JspException("indexed.noEnclosingIterate");
            RequestUtils.saveException(pageContext, e);
            throw e;
        }
        if (name != null){
            handlers.append(name);
        }
        handlers.append("[");
        handlers.append(index);
        handlers.append("]");
        if (name != null){
            handlers.append(".");
        }
    }

    /**
     * Prepares the style attributes for inclusion in the component's HTML tag.
     * @return The prepared String for inclusion in the HTML tag.
     * @exception JspException if invalid attributes are specified
     */
    protected String prepareStyles() throws JspException {
        String value = null;
        StringBuffer styles = new StringBuffer();
        if (this.style != null) {
            styles.append(" style=\"");
            styles.append(getStyle());
            styles.append("\"");
        }
        if (this.styleClass != null) {
            styles.append(" class=\"");
            styles.append(getStyleClass());
            styles.append("\"");
        }
        if (this.styleId != null) {
            styles.append(" id=\"");
            styles.append(getStyleId());
            styles.append("\"");
        }
        value = message(this.title, this.titleKey);
        if (value != null) {
            styles.append(" title=\"");
            styles.append(value);
            styles.append("\"");
        }
        value = message(this.alt, this.altKey);
        if (value != null) {
            styles.append(" alt=\"");
            styles.append(value);
            styles.append("\"");
        }

        // tabindex 에 대해 EL 처리
        try{
            Object tmp = ExpressionUtil.evalNotNull("out", "tabindex", this.tabindex,
                Object.class, this, this.pageContext);
            if(tmp!=null){
            	this.tabindex = tmp.toString();
            }
        }catch(NullAttributeException ex){
        	this.tabindex = null;
        }

        // 내가 추가(tablindex)
        if(this.tabindex!=null){
            styles.append(" tabindex=\"");
            styles.append(this.tabindex);
            styles.append("\"");
        }

        return styles.toString();
    }

    /**
     * Prepares the event handlers for inclusion in the component's HTML tag.
     * @return The prepared String for inclusion in the HTML tag.
     */
    protected String prepareEventHandlers() {
        try{
            processEL();
            StringBuffer handlers = new StringBuffer();
            prepareMouseEvents(handlers);
            prepareKeyEvents(handlers);
            prepareTextEvents(handlers);
            prepareFocusEvents(handlers);
            return handlers.toString();
        }catch(Exception e){
            //System.out.println("[에러] " + e);        	
            return "";
        }
    }

    /**
     * Prepares the mouse event handlers, appending them to the the given
     * StringBuffer.
     * @param handlers The StringBuffer that output will be appended to.
     */
    protected void prepareMouseEvents(StringBuffer handlers) {
        if (this.onclick != null) {
            handlers.append(" onclick=\"");
            handlers.append(getOnclick());
            handlers.append("\"");
        }

        if (this.ondblclick != null) {
            handlers.append(" ondblclick=\"");
            handlers.append(getOndblclick());
            handlers.append("\"");
        }

        if (this.onmouseover != null) {
            handlers.append(" onmouseover=\"");
            handlers.append(getOnmouseover());
            handlers.append("\"");
        }

        if (this.onmouseout != null) {
            handlers.append(" onmouseout=\"");
            handlers.append(getOnmouseout());
            handlers.append("\"");
        }

        if (this.onmousemove != null) {
            handlers.append(" onmousemove=\"");
            handlers.append(getOnmousemove());
            handlers.append("\"");
        }

        if (this.onmousedown != null) {
            handlers.append(" onmousedown=\"");
            handlers.append(getOnmousedown());
            handlers.append("\"");
        }

        if (this.onmouseup != null) {
            handlers.append(" onmouseup=\"");
            handlers.append(getOnmouseup());
            handlers.append("\"");
        }
    }

    /**
     * Prepares the keyboard event handlers, appending them to the the given
     * StringBuffer.
     * @param handlers The StringBuffer that output will be appended to.
     */
    protected void prepareKeyEvents(StringBuffer handlers) {

        if (this.onkeydown != null) {
            handlers.append(" onkeydown=\"");
            handlers.append(getOnkeydown());
            handlers.append("\"");
        }

        if (this.onkeyup != null) {
            handlers.append(" onkeyup=\"");
            handlers.append(getOnkeyup());
            handlers.append("\"");
        }

        if (this.onkeypress != null) {
            handlers.append(" onkeypress=\"");
            handlers.append(getOnkeypress());
            handlers.append("\"");
        }
    }

    /**
     * Prepares the text event handlers, appending them to the the given
     * StringBuffer.
     * @param handlers The StringBuffer that output will be appended to.
     */
    protected void prepareTextEvents(StringBuffer handlers) {

        if (this.onselect != null) {
            handlers.append(" onselect=\"");
            handlers.append(getOnselect());
            handlers.append("\"");
        }

        if (this.onchange != null) {
            handlers.append(" onchange=\"");
            handlers.append(getOnchange());
            handlers.append("\"");
        }
    }

    /**
     * Prepares the focus event handlers, appending them to the the given
     * StringBuffer.
     * @param handlers The StringBuffer that output will be appended to.
     */
    protected void prepareFocusEvents(StringBuffer handlers) {

        if (this.onblur != null) {
            handlers.append(" onblur=\"");
            handlers.append(getOnblur());
            handlers.append("\"");
        }

        if (this.onfocus != null) {
            handlers.append(" onfocus=\"");
            handlers.append(getOnfocus());
            handlers.append("\"");
        }

        if (this.disabled) {
            handlers.append(" disabled=\"disabled\"");
        }

        if (this.readonly) {
            handlers.append(" readonly=\"readonly\"");
        }

    }

    /**
     * Allows HTML tags to find out if they're nested within an %lt;html:html&gt; tag that
     * has xhtml set to true.
     * @return true if the tag is nested within an html tag with xhtml set to true, false
     * otherwise.
     * @since Struts 1.1
     */
    protected boolean isXhtml() {
        String xhtml =
            (String) this.pageContext.getAttribute(Globals.XHTML_KEY, PageContext.PAGE_SCOPE);

        if ("true".equalsIgnoreCase(xhtml)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the closing brace for an input element depending on xhtml status.  The tag
     * must be nested within an %lt;html:html&gt; tag that has xhtml set to true.
     * @return String - &gt; if xhtml is false, /&gt; if xhtml is true
     * @since Struts 1.1
     */
    protected String getElementClose() {
        if (this.isXhtml()) {
            return " />";
        } else {
            return ">";
        }
    }

    /**
     * Searches all scopes for the bean and calls BeanUtils.getProperty() with the
     * given arguments and converts any exceptions into JspException.
     *
     * @param beanName The name of the object to get the property from.
     * @param property The name of the property to get.
     * @return The value of the property.
     * @throws JspException
     * @since Struts 1.1
     */
    protected String lookupProperty(String beanName, String property)
        throws JspException {

        Object bean = RequestUtils.lookup(this.pageContext, beanName, null);
        if (bean == null) {
            throw new JspException("getter.bean");
        }

        try {
            return BeanUtils.getProperty(bean, property);

        } catch (IllegalAccessException e) {
            throw new JspException(
                "getter.access");

        } catch (InvocationTargetException e) {
            Throwable t = e.getTargetException();
            throw new JspException(
                "getter.result");

        } catch (NoSuchMethodException e) {
            throw new JspException(
                "getter.method");
        }
    }

    /**
     * EL 처리
     */
    private void processEL() throws Exception{

        // onclick 에 대해 EL 처리
        try{
            Object tmp = ExpressionUtil.evalNotNull("out", "onclick", this.onclick,
                Object.class, this, this.pageContext);
            if(tmp!=null){
            	this.onclick = tmp.toString();
            }
        }catch(NullAttributeException ex){
        	this.onclick = null;
        }

        // onchange 에 대해 EL 처리
        try{
            Object tmp = ExpressionUtil.evalNotNull("out", "onchange", this.onchange,
                Object.class, this, this.pageContext);
            if(tmp!=null){
            	this.onchange = tmp.toString();
            }
        }catch(NullAttributeException ex){
        	this.onchange = null;
        }

    }

}
