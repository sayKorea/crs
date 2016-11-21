package fatca.common.taglib;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;

import fatca.common.util.FTCCodeUtil;



/**
 * @File Name    : CodeSelect.java
 * @Package Name : fatca.common.taglib
 * @author       : 
 * @Description  : select box를 만들어주는 태그로서 코드테이블에서 가져와서 select box를 만들어줌.
 * @History      : 
 */
public class FTCCodeSelect extends FTCBaseHandlerTag {
	
	/**
	 * UID
	 */
	private static final long serialVersionUID = -2824266947642943323L;



	//private static Logger logger = Logger.getLogger(CodeSelect.class);

	//private CodeUtil codeUtil = new CodeUtil();
	
	private String key; // <option value="KEY">

	private String value; // <option>VALUE</option>

	private String name; // <select NAME="teSelect">

	private String size; // <select SIZE=10>

	private String multiple; // <select MULTIPLE=true>

	private String dbname; // data name

	private String selected; // selected

	private String width; // <select width="100">

	private String valuefield = "value";

	private String labelfield = "label";

	private String codekey;
	
	private String onDisabled;
	
	private String before;
	
	private String after;
	
	private String title;
	
	private String required;
	
	private String onchange;
	
	private String defaultValue;
	
	private List dblist = null;

	
	public String getDefaultValue() {
		return this.defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getOnchange() {
		return this.onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	public void setCodekey(String codekey) {
		this.codekey = codekey;
	}

	/**
	 * @jsp.attribute required="false" 
	 * 					rtexprvalue="false"	  
	 * 
	 * @param multiple
	 *            The multiple to set.
	 */
	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}

	/**
	 * @jsp.attribute required="false" 
	 * 							rtexprvalue="false" 
	 * @param dbname
	 *            The dbname to set.
	 */
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}


	/**
	 * @jsp.attribute required="false" rtexprvalue="true" description="<select
	 *                width=100>"
	 * @return Returns the width.
	 */
	public void setWidth(String width) {
		this.width = width;
	}


	/**
	 * @jsp.attribute required="false" rtexprvalue="false" description=" "
	 * @param labelFieldName
	 *            The labelFieldName to set.
	 */
	public void setLabelfield(String labelFieldName) {
		this.labelfield = labelFieldName;
	}

	/**
	 * @jsp.attribute required="false" rtexprvalue="false" description=" "
	 * @param valueFieldName
	 *            The valueFieldName to set.
	 */
	public void setValuefield(String valueFieldName) {
		this.valuefield = valueFieldName;
	}
	
	

	/**
	 * 	@jsp.attribute required="false" rtexprvalue="false" 
	 *  @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @jsp.attribute required="false" 
	 * 						rtexprvalue="true" 
	 * 
	 * @param size
	 *            The size to set.
	 */
	public void setSize(String size) {
		this.size = size;
	}


	/**
	 * @jsp.attribute required="false" 
	 * 							rtexprvalue="false" 
	 * 
	 * @param selected
	 *            The selected to set.
	 */
	public void setSelected(String selected) {
		this.selected = selected;
	}
	
	/**
	 * @jsp.attribute required="false" 
	 * 							rtexprvalue="false" 
	 * 
	 * @param onDisabled
	 *            The onDisabled to set.
	 */	

	public void setOnDisabled(String onDisabled) {
		this.onDisabled = onDisabled;
	}
	
	/**
	 * @jsp.attribute required="false" 
	 * 							rtexprvalue="false" 
	 * 
	 * @param before
	 *            The before to set.
	 */	

	public void setBefore(String before) {
		this.before = before;
	}

	/**
	 * @jsp.attribute required="false" 
	 * 							rtexprvalue="false" 
	 * 
	 * @param after
	 *            The after to set.
	 */	

	public void setAfter(String after) {
		this.after = after;
	}
	
	
	
/**
	 * @jsp.attribute required="false" 
	 * 							rtexprvalue="false" 
	 * 
	 * @param title
	 *            The title to set.
	 */	

	public void setId(String idtitle) {
		this.title = idtitle;
	}

/**
	 * @jsp.attribute required="false" 
	 * 							rtexprvalue="false" 
	 * 
	 * @param id
	 *            The id to set.
	 */	

	public void setRequired(String required) {
		this.required = required;
	}
	
	
	/**
	 * Release any acquired resources.
	 */
	public void release() {

		super.release();
		this.dbname = null;
		this.name = null;
		this.size = null;
		this.multiple = null;
		this.key = null;
		this.value = null;
		this.selected = null;
		//this.valuefield = null;
		//this.labelfield = null;
		this.codekey = null;
		this.onDisabled = null;
		this.dblist	 = null;
		this.title	 = null;
		this.required	 = null;
		this.onchange	 = null;
		this.defaultValue	 = null;
	}
	
	/**
	 * 
	 * @return SKIP_BODY
	 */
	public int doStartTag() {
		return SKIP_BODY;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public int doEndTag() {
		JspWriter out = null;
		Object tmp = null;
		try{
	        try{
	            tmp = ExpressionUtil.evalNotNull("cselect", "name", this.name, Object.class, this, this.pageContext);
	            if(tmp!=null){
	            	this.name = tmp.toString();
	            }
	        }catch(NullAttributeException ex){
	        	this.name = "";
	        }
	        try{
	            tmp = ExpressionUtil.evalNotNull("cselect", "codekey", this.codekey, Object.class, this, this.pageContext);
	            if(tmp!=null){
	            	this.codekey = tmp.toString();
	            }
	        }catch(NullAttributeException ex){
	        	this.codekey = "";
	        }
	        try{
	            tmp = ExpressionUtil.evalNotNull("cselect", "selected", this.selected, Object.class, this, this.pageContext);
	            if(tmp!=null && !"".equals(tmp)){
	            	this.selected = tmp.toString();
	            }else{
	            	this.selected = this.defaultValue;
	            }
	        }catch(NullAttributeException ex){
	        	this.selected = "";
	        } 
	        
	        try{
	            tmp = ExpressionUtil.evalNotNull("cselect", "onchange", this.onchange, Object.class, this, this.pageContext);
	            if(tmp!=null){
	            	this.onchange = tmp.toString();
	            }else{
	            	this.onchange = null;
	            }
	        }catch(NullAttributeException ex){
	        	this.onchange = null;
	        } 
	        
	            tmp = ExpressionUtil.evalNotNull("cselect", "dbname", this.dbname, Object.class, this, this.pageContext);
	            if(tmp!=null){
	            	if ( tmp instanceof String){
	            		this.dbname = tmp.toString();
	            	}
	            	
	            	if ( tmp instanceof List){
	            		this.dblist = (List)tmp;
	            	}
	            	
	            	
	            }

        }catch (Exception e) {
        	e.getMessage();
			//logger.error(e);
		}
		
		
		try {
			out = pageContext.getOut();
			String outString = getSelectString();
			
			out.print(outString);
			this.dblist = null;
		} catch (Exception e) {
			e.getMessage();
			//logger.error(e);
		}
		return EVAL_PAGE;
	}



	public String getSelectString() {

		StringBuffer sb = new StringBuffer();
		
		sb.append("<SELECT");
		if (this.name != null) {
			sb.append(" NAME=\"").append(this.name).append("\" ");
		}
		
		if (this.size != null) {
			sb.append(" size='" + this.size + "' ");
		}
		
		if (this.multiple != null) {
			//sb.append(" multiple='" + multiple + "' ");
			sb.append(" multiple ");
		}
		
		if (this.width != null) {
			sb.append(" width='" + this.width + "' ");
		}
		
		if (this.onDisabled != null) {
			sb.append(" disabled  ");
		}
		
		if (this.title != null) {
			sb.append(" title='" + this.title + "' ");
		}
		
		if (this.required != null) {
			sb.append(" required ");
		}
		
		if (this.onchange != null) {
			sb.append(" onChange=\"" + this.onchange + "\" ");
		}
		
        sb.append(prepareEventHandlers());
        try {
			sb.append(prepareStyles());
		} catch (JspException e) {
			e.getMessage();
		}

		sb.append(" > ");
		sb.append("\r\t");
		
		sb.append(getOptionString());

		sb.append("\r\t</SELECT> ");
		
		return sb.toString();
	}

	public String getOptionString() {
		
		StringBuffer sbOption = new StringBuffer();
		FTCCodeUtil codeUtil = new FTCCodeUtil();

		if ( this.codekey == null && this.dblist == null ) {
			if(this.dbname != null ){
				this.dblist = (ArrayList) this.pageContext.getRequest().getAttribute(this.dbname);
				this.valuefield = "codeId";
				this.labelfield = "codeName";
			}	
		} else if (this.codekey != null){
			this.dblist = codeUtil.getCodeList(this.codekey);
			this.valuefield = "codeId";
			this.labelfield = "codeName";
		} 
		
		if (this.dblist == null) {
			return this.dbname + "은 null입니다.";
		}

		if (this.dblist.isEmpty()) {
			return this.dbname + "데이터가 없습니다.";
		}
		if (this.dblist != null) {
			if (this.before != null) {
					
				String beforeValue[] = before.split(","); 
				sbOption.append("\r\t\t<OPTION VALUE=\"").append(beforeValue[0]).append("\"");
				sbOption.append(">");
				sbOption.append(beforeValue[1]);
				sbOption.append("</Option>");
			}
			
			for (int i = 0; i < dblist.size(); i++) {
				Object databean = dblist.get(i);
				try {
					this.key = (String) BeanUtils.getProperty((Object) databean, this.valuefield);
				} catch (IllegalAccessException e) {
					e.getMessage();
				} catch (InvocationTargetException e) {
					e.getMessage();
				} catch (NoSuchMethodException e) {
					this.key = "" + i;
				}

				try {
					this.value = (String) BeanUtils.getProperty((Object) databean, this.labelfield);
				} catch (IllegalAccessException e) {
					e.getMessage();
				} catch (InvocationTargetException e) {
					e.getMessage();
				} catch (NoSuchMethodException e) {
					e.getMessage();
				}

				sbOption.append("\r\t\t<OPTION VALUE=\"").append(this.key).append("\"");
				if (this.selected != null && selected.equals(this.key)) {
					sbOption.append(" selected ");
				}
				sbOption.append(">");
				sbOption.append(this.value);
				sbOption.append("</Option>");
			}
			
			if (this.after != null) {
				String afterValue[] = after.split(","); 
				sbOption.append("\r\t\t<OPTION VALUE=\"").append(afterValue[0]).append("\"");
				if (this.selected != null && selected.equals(afterValue[0])) {
					sbOption.append(" selected ");
				}
				sbOption.append(">");
				sbOption.append(afterValue[1]);
				sbOption.append("</Option>");
			}
		}
		return sbOption.toString();
	}



	




}
