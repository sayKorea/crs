package crs.sample;

import java.util.HashMap;
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

/**
 * @author CeeDo
 *
 */
public class CrsSampleAction extends FTCBaseAction {

	private Log log = LogFactory.getLog(this.getClass());
	private CrsSampleService service = new CrsSampleService();
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward conditionBarView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("################# Layout Condition Bar View #############");
		request.setAttribute("Y", "Y");
		return new ActionForward("/crs/sample/layoutConditionBar.jsp");
	}
	public ActionForward gridView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("################# Layout Grid View #############");
		return new ActionForward("/crs/sample/layoutGrid.jsp");
	}
	public ActionForward masterDeatilView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("################# Layout Master Detail View #############");
		return new ActionForward("/crs/sample/layoutMasterDetail.jsp");
	}
	public ActionForward masterBindingView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("################# Layout Master Binding View #############");
		return new ActionForward("/crs/sample/layoutMasterBinding.jsp");
	}
	public ActionForward ajaxView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("################# Util Ajax View #############");
		return new ActionForward("/crs/sample/utilAjax.jsp");
	}
	public ActionForward popupView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("################# popup View #############");
		return new ActionForward("/crs/sample/popup/popup.jsp");
	}
	public ActionForward fullView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("################# Layout Full View #############");
		return new ActionForward("/crs/sample/layoutFull.jsp");
	}
	
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void select(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("################# SAMPLE SELECT #############");
		Map<String, String> m = new HashMap<String, String>();
		m.put("test", "1");
		m.put("test1", "2");
		m.put("test2", "3");
		m.put("test3", "4");
		Map<String, String> m2 = new HashMap<String, String>();
		m2.put("test", "1");
		m2.put("test1", "2");
		m2.put("test2", "3");

		JSONObject json = new JSONObject();
		json.put("test", m);
		json.put("test2", m2);
		ResponseJson.objectToJson(request, response, json, "euc-kr");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getBaseYmList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("################# SAMPLE SELECT #############");
		Map param = getParamMapForPage(request, "grid", FTCConstants.DefaultTableRow);
		List<?> resultList = service.getBaseYmList(param);
		request.setAttribute("test", "Y");

		JSONObject json = new JSONObject();
		json.put("resultList", resultList);
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
		log.debug("################# SAMPLE SELECT #############");
		Map param = getParamMapForPage(request, "grid", FTCConstants.DefaultTableRow);
		log.debug("###########################################\n"+param);;
		List<?> resultList = service.getGridList(param);

		JSONObject json = new JSONObject();
		json.put("resultList", resultList);
		ResponseJson.objectToJson(request, response, json, "euc-kr");
	}
}
