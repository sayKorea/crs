package crs.template;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONObject;

import crs.util.ResponseJson;
import fatca.common.FTCConstants;
import fatca.common.base.FTCBaseAction;
import fatca.common.util.FTCDbMap;

/**
 * @author CeeDo
 *
 */
public class CrsTemplateAction extends FTCBaseAction {

	private Log log = LogFactory.getLog(this.getClass());
	private CrsTemplateService service = new CrsTemplateService();
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward templateView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("################# Layout Template View #############");
		request.setAttribute("Y", "Y");
		return new ActionForward("/crs/template/template.jsp");
	}
	

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getComplete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("################# Template Complete Start #############");
		Map<?, ?> param = getParamMapForPage(request, "grid", FTCConstants.DefaultTableRow);
		
		/* Service Call */
		FTCDbMap resultMap = service.getComplete(param);
		
		/* JSON Converter */
		JSONObject json = new JSONObject();
		json.put("resultMap", resultMap);
		
		/* Write Json Data */
		ResponseJson.objectToJson(request, response, json, "euc-kr");
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getCodeList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("################# getCodeList Start #############");
		Map<?, ?> param = getParamMapForPage(request, "grid", FTCConstants.DefaultTableRow);
		
		/* Service Call */
		List<?> resultList = service.getCodeList(param);
		
		/* JSON Converter */
		JSONObject json = new JSONObject();
		json.put("resultList", resultList);
		
		/* Write Json Data */
		ResponseJson.objectToJson(request, response, json, "euc-kr");
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getGridList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("################# getGridList Start #############");
		Map<?, ?> param = getParamMapForPage(request, "grid", FTCConstants.DefaultTableRow);
		
		/* Service Call */
		List<?> resultList = service.getGridList(param);
		
		/* JSON Converter */
		JSONObject json = new JSONObject();
		json.put("resultList", resultList);
		
		/* Write Json Data */
		ResponseJson.objectToJson(request, response, json, "euc-kr");
	}
	
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("################# updateCode Start #############");
		Map param = getParamMapForPage(request, "grid", FTCConstants.DefaultTableRow);
		
		/* Service Call */
		Object o = service.updateCode(param);
		log.debug("#################################################");
		log.debug(o);
		
		/* JSON Converter */
		JSONObject json = new JSONObject();
		//json.put("cnt", cnt);
		
		/* Write Json Data */
		ResponseJson.objectToJson(request, response, json, "euc-kr");
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void deleteCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("################# deleteCode Start #############");
		Map param = getParamMapForPage(request, "grid", FTCConstants.DefaultTableRow);
		
		/* Service Call */
		Object o = service.deleteCode(param);
		log.debug("#################################################");
		log.debug(o);
		/* JSON Converter */
		JSONObject json = new JSONObject();
		//json.put("cnt", cnt);
		
		/* Write Json Data */
		ResponseJson.objectToJson(request, response, json, "euc-kr");
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void insertCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("################# insertCode Start #############");
		Map param = getParamMapForPage(request, "grid", FTCConstants.DefaultTableRow);
		
		/* Service Call */
		Object o = service.insertCode(param);
		log.debug("#################################################");
		log.debug(o);
		
		/* JSON Converter */
		JSONObject json = new JSONObject();
		//json.put("cnt", cnt);
		
		/* Write Json Data */
		ResponseJson.objectToJson(request, response, json, "euc-kr");
	}
}
