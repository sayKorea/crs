package fatca.common.base;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import fatca.common.FTCConstants;
import fatca.common.util.FTCCiHashMap;
import fatca.common.util.FTCDateUtil;
import fatca.common.util.FTCExcelStyle;
import fatca.common.util.FTCStringUtil;
import fatca.system.info.FTCUserInfo;
import fatca.system.service.FTCUserService;

/**
 * @File Name    : BaseAction.java
 * @Package Name : fatca.common.base;
 * @author       : 
 * @Description  : BaseService
 * @History      : 
 */
public class FTCBaseAction extends Action {

	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * The Class instance of this <code>DispatchAction</code> class.
	 */
	protected Class clazz = this.getClass();

	/**
	 * The message resources for this package.
	 */
	protected static MessageResources messages = MessageResources
			.getMessageResources("org.apache.struts.actions.LocalStrings");

	/**
	 * The set of Method objects we have introspected for this class, keyed by
	 * method name. This collection is populated as different methods are
	 * called, so that introspection needs to occur only once per method name.
	 */
	protected HashMap methods = new HashMap();

	/**
	 * The set of argument type classes for the reflected method call. These are
	 * the same for all calls, so calculate them only once.
	 */
	protected Class[] types = { ActionMapping.class, ActionForm.class,HttpServletRequest.class, HttpServletResponse.class };

	/**
	 * ActionServlet�� ����Ʈ�� �� �޼��带 ���Ѵ�. ��ӹ޴� Ŭ������ �Ʒ��� executeAction �޼��带 �����ϰ� ������
	 * ���μ����� ó���ؾ� �Ѵ�.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return �޼���
	 * @throws Exception
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		
		log.debug("pass base action");

		if (isCancelled(request)) {
			ActionForward af = cancelled(mapping, form, request, response);
			if (af != null) {
				return af;
			}
		}
		
		// Get the method's name. This could be overridden in subclasses.
		String name = getMethodName(mapping, form, request, response, "method");

		// Prevent recursive calls
		if ("execute".equals(name) || "perform".equals(name)) {
			String message = messages.getMessage("dispatch.recursive", mapping
					.getPath());

			log.error(message);
			throw new ServletException(message);
		}
		
		if (name == null || "".equals(name)) {

			HttpSession session = request.getSession();

			ActionForward aforward = null;
			
			if (session != null) {
				session.setAttribute("lastUsedRequest", request);
				// RequestUtils.
				session.setAttribute("lastURL", request.getQueryString());
			}
			
			
			// ���� �׼�����
			try {
				aforward = execute(mapping, form, request, response, session);
				if (aforward.getPath().lastIndexOf("jsp") == 0) {
					request.setAttribute("tojsp", "tojsp");
				}
				

			} catch (Exception e) {
				//System.out.println("Exception in BaseEngine!!" + e.toString());
				request.setAttribute("error_exception", e.toString());
				ActionForward result = new ActionForward();
				result.setName("errorpage");
				result.setPath("/error/error.jsp");
				result.setRedirect(false);
				return result;
			}
			return (aforward);
		}


		return dispatchMethod(mapping, form, request, response, name);
	}

	/**
	 *  �̰� ����Ŭ�������� �����ؾ��� �߻� �伭��.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param session
	 * @return null
	 * @throws Exception
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		return null;
	}

	/**
	 * Constants.USER_KEY�� ��������� ���ǿ� setAttribute()�� ���ش�.
	 * 
	 * @param request
	 * @param obj
	 */
	protected void setUserSession(HttpServletRequest request, Object obj) {
		HttpSession session = request.getSession(true);
		session.setAttribute(FTCConstants.USER_KEY, obj);
	}

	
	/**
	 * Constants.USER_KEY�� ��������� ������� ���� ���� ��ü�� ��ȯ�Ѵ�. ���� ����� ������ ���ٸ� null�� ���ϵȴ�.
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	protected Object getUserSession(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if (session == null) {
			return null;
		}

		return session.getAttribute(FTCConstants.USER_KEY);
	}

	/**
	 * Constants.USER_KEY�� ��������� ������� ���� ���� ��ü�� �����Ѵ�.
	 * 
	 * @param request
	 * @return
	 */
	protected void removeUserSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(FTCConstants.USER_KEY);
		}
	}

	
	/**
	 * Method which is dispatched to when there is no value for specified
	 * request parameter included in the request. Subclasses of
	 * <code>DispatchAction</code> should override this method if they wish to
	 * provide default behavior different than throwing a ServletException.
	 */
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String message = messages.getMessage("dispatch.parameter", mapping
				.getPath(), mapping.getParameter());

		log.error(message);

		throw new ServletException(message);
	}

	/**
	 * Method which is dispatched to when the request is a cancel button submit.
	 * Subclasses of <code>DispatchAction</code> should override this method
	 * if they wish to provide default behavior different than returning null.
	 * 
	 * @since Struts 1.2.0
	 */
	protected ActionForward cancelled(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return null;
	}

	/**
	 * Dispatch to the specified method.
	 * 
	 * @since Struts 1.1
	 */
	protected ActionForward dispatchMethod(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String name) throws Exception {

		// Make sure we have a valid method name to call.
		// This may be null if the user hacks the query string.
		if (name == null) {
			return this.unspecified(mapping, form, request, response);
		}

		// Identify the method object to be dispatched to
		Method method = null;
		try {
			method = getMethod(name);

		} catch (NoSuchMethodException e) {
			String message = messages.getMessage("dispatch.method", mapping
					.getPath(), name);
			log.error(message, e);

			String userMsg = messages.getMessage("dispatch.method.user",
					mapping.getPath());
			throw new NoSuchMethodException(userMsg);
		}

		ActionForward forward = null;
		try {
			Object args[] = { mapping, form, request, response };
			forward = (ActionForward) method.invoke(this, args);

		} catch (ClassCastException e) {
			String message = messages.getMessage("dispatch.return", mapping
					.getPath(), name);
			log.error(message, e);
			throw e;

		} catch (IllegalAccessException e) {
			String message = messages.getMessage("dispatch.error", mapping
					.getPath(), name);
			log.error(message, e);
			throw e;

		} catch (InvocationTargetException e) {
			// Rethrow the target exception if possible so that the
			// exception handling machinery can deal with it
			Throwable t = e.getTargetException();
			if (t instanceof Exception) {
				throw ((Exception) t);
			} else {
				String message = messages.getMessage("dispatch.error", mapping
						.getPath(), name);
				log.error(message, e);
				throw new ServletException(t);
			}
		}

		// Return the returned ActionForward instance
		return (forward);
	}

	/**
	 * Introspect the current class to identify a method of the specified name
	 * that accepts the same parameter types as the <code>execute</code>
	 * method does.
	 * 
	 * @param name
	 *            Name of the method to be introspected
	 * 
	 * @exception NoSuchMethodException
	 *                if no such method can be found
	 */
	protected Method getMethod(String name) throws NoSuchMethodException {

		synchronized (this.methods) {
			Method method = (Method) methods.get(name);
			
			if (method == null) {
				method = clazz.getMethod(name, this.types);
				this.methods.put(name, method);
			}
			
			return (method);
		}

	}

	/**
	 * Returns the method name, given a parameter's value.
	 * 
	 * @param mapping
	 *            The ActionMapping used to select this instance
	 * @param form
	 *            The optional ActionForm bean for this request (if any)
	 * @param request
	 *            The HTTP request we are processing
	 * @param response
	 *            The HTTP response we are creating
	 * @param parameter
	 *            The <code>ActionMapping</code> parameter's name
	 * 
	 * @return The method's name.
	 * @since Struts 1.2.0
	 */
	protected String getMethodName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String parameter) throws Exception {

		// Identify the method name to be dispatched to.
		// dispatchMethod() will call unspecified() if name is null
		return request.getParameter(parameter);
	}
	
	protected Map getParamMapForPage(HttpServletRequest request,
			String tableId, int pageSize) {
			
		return getParamMapForPage(request, tableId, pageSize, "2");
	}
	
	protected Map getParamMapForPage(HttpServletRequest request,
			String tableId, int pageSize, String defaultOrder) {

		Map para = null;
		try {

			// ������ ����Ʈ���� �� �ο츦 Ŭ������ �������� �� ��������
			// hidden�� detailview_pagelist ��� �̸����� value�� �ڽ��� �ǵ��ư�
			// ������ ���̵� �־�� ��.
			if (tableId.equals(request.getParameter("detailview_pagelist"))) {
				if (null != request.getSession().getAttribute("page_list_")) {
					para = (Map) request.getSession()
							.getAttribute("page_list_");
					if (tableId.equals((String) para.get("paged_table_id"))) {
						para.put("session_revive", "true");
						return para;
					}
				}
			}
		} catch (Exception exr) {
			// �������� ����.
			exr.printStackTrace();
		}

		Enumeration org = request.getParameterNames();
		para = new HashMap();
		while(org.hasMoreElements()){
			String names = (String)org.nextElement();
			//para.put(names.replaceAll("_", ""), request.getParameter(names));
			para.put(names, request.getParameter(names));
			
		}		
		//csnoReal�� ������ csno�� �ٲ��ֱ�. �Ѿ�� parameter�� �ٲ�� ���� sql ����
		if(para.get("csnoReal")!=null && !"".equals(para.get("csnoReal"))){
			para.put("csno", para.get("csnoReal"));
		}
		
		org = request.getAttributeNames();
		while (org.hasMoreElements()) {
			String names = (String) org.nextElement();
			para.put(names, request.getAttribute(names));
		}

		if( tableId != null){
		if (null == request.getParameter(new ParamEncoder(tableId)
				.encodeParameterName(TableTagParameters.PARAMETER_EXPORTTYPE))) {
			int currentPage = new Integer(
					FTCStringUtil
							.nvl(
									request
											.getParameter(new ParamEncoder(
													tableId)
													.encodeParameterName(TableTagParameters.PARAMETER_PAGE)),
									"1")).intValue();
			int startP = ((currentPage - 1) * pageSize);
			int endP = startP + pageSize;
			request.setAttribute("pagingsize", pageSize);
			para.put("pagingStart", new Integer(startP));
			para.put("pagingEnd", new Integer(endP));
		} else {
			para.put("pagingStart", new Integer(0));
			para.put("pagingEnd", new Integer(1000000));

		}

		para
				.put(
						"preferredOrder",
						FTCStringUtil
								.nvl(
										request
												.getParameter(new ParamEncoder(
														tableId)
														.encodeParameterName(TableTagParameters.PARAMETER_SORT)),
										"1"));
		
		if ("2"
				.equals(FTCStringUtil
						.nvl(
								request
										.getParameter(new ParamEncoder(tableId)
												.encodeParameterName(TableTagParameters.PARAMETER_ORDER)),
								defaultOrder))) {
			para.put("preferredSort", "ASC");
		} else {
			para.put("preferredSort", "DESC");
		}

		para.put("paged_table_id", tableId);
		}
		return para;

	}

	public String getStrParam(HttpServletRequest req, String paramId, String defaultValue) {
		
		Object val = (Object)req.getParameter(paramId);
		if(val == null){
			return defaultValue;
		}
		return val.toString();
		
	}
	
	public FTCUserInfo getUserInfo(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		FTCUserInfo userInfo = ((FTCUserInfo)session.getAttribute(FTCConstants.USER_KEY));
		if (userInfo == null ) {
			userInfo = new FTCUserInfo();
			userInfo.setUserEnob("test");
		}
		
		return userInfo;
	}

	
	public String getCustNm(Map para) throws Exception {
		
		if(para.get("csno")!=null && !"".equals(para.get("csno"))){	
			FTCUserService custService = new FTCUserService();
			return custService.getCustNm(para);
		}
		
		return "";
	}	
	
	/**
	 * Excel Download�� ���� ����.
	 * 
	 * @param request
	 * @param list 
	 * @param fileName
	 * @param headerName
	 * @param columnName
	 */
	public void genExcel(HttpServletRequest request, List list, String fileName, String[] headerName, String[] columnName) {
		 
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(); // Sheet ����        
        
	    //Style�� ���� ��ü ����
        FTCExcelStyle style = new FTCExcelStyle(workbook);
        HSSFRow row; // Row ����       
        HSSFCell cell;// Cell ����        
      
        //ù��° ��Ʈ Header Row ����
        row = sheet.createRow(0); 
        for(int i=0; i<headerName.length; i++){
	        cell = row.createCell(i);
	        cell.setCellStyle(style.headerStyle);
	        cell.setCellValue(headerName[i]);
        } 
        
        /*
         * ���� ����
         */       
        String tempVal = "";
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i + 1); // Row ����
			FTCCiHashMap map = (FTCCiHashMap)list.get(i);

			for (int j = 0; j < headerName.length; j++) {
				cell = row.createCell(j);
				if (map.get(columnName[j]) instanceof java.math.BigDecimal) {
					cell.setCellValue(map.getDouble(columnName[j]));
				}else{
					
					tempVal = map.getString(columnName[j]);
					
					//���ΰ� ����,�Ǹ��ȣ ����ŷ
					if("custNm".equals(columnName[j])){
						if(tempVal!=null){
							tempVal = "*"+FTCStringUtil.subString(tempVal,1);	
				    	}
					}else if("rnno".equals(columnName[j])){
						if(tempVal!=null && tempVal.length()>8){
							tempVal = FTCStringUtil.subString(tempVal,0,7)+"******";
				    	}
					}
					cell.setCellValue(tempVal);
				}
				//cell.setCellStyle(style.rightAlignStle);				
			}
		}
		
		for(int i=0; i<headerName.length; i++){
			sheet.autoSizeColumn(i);
		}

		// Content Setting
		fileName += "_" + FTCDateUtil.getToday("yyyyMMddHHmmssSSS")+".xls";		
		FileOutputStream fos = null;
		try{
			
			String filePath = FTCConstants.USER_DOWNLOAD;
			File file2 = new File(filePath);
			if(!file2.isDirectory()){
				file2.mkdir();
			}
			
	        File file = new File(filePath+fileName);
			fos = new FileOutputStream(file);
			
			log.debug(file);
			log.debug(fos);
			
			//server dev
			log.debug("###################################################################################");
			log.debug(FTCConstants.USER_DOWNLOAD);
			log.debug("###################################################################################");
			request.setAttribute("fRealFilePath", FTCConstants.USER_DOWNLOAD+fileName); 	
			//local windows
			//request.setAttribute("fRealFilePath", file.getCanonicalPath().replace("\\","/"));
			request.setAttribute("fNewFileName", fileName);
			
			log.debug(request.getAttribute("fRealFilePath"));
			//log.debug(request.getAttribute("fNewFileName"));
	        workbook.write(fos);
	        fos.close();
		}catch (Exception e){
			e.printStackTrace();
			e.getMessage();
		}finally{
			try{
				fos.close();
			}catch (IOException e){
				e.getMessage();
			}	
		}
		
	}
	
}