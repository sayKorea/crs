package fatca.system.action;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fatca.common.FTCConstants;
import fatca.common.base.FTCBaseAction;
import fatca.common.util.FTCStringUtil;
import fatca.system.info.FTCUserInfo;
import fatca.system.service.FTCAuthService;
import fatca.system.service.FTCCodeService;

/**
 * @File Name    : AuthAction.java
 * @Package Name : fatca.system.action
 * @author       : 
 * @Description  : 권한 관리
 * @History      : 
 */
public class FTCAuthAction extends FTCBaseAction {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	 /**
	   * 권한 목록 조회 
	   * 
	   * @param mapping 
	   * @param form
	   * @param request
	   * @param response
	   * @return 권한 목록 (list) 
	   * @throws Exception
	   */
	public ActionForward listUsrAuth(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FTCAuthService authService = new FTCAuthService();
	
		log.debug("Start AuthAction.listUsrAuth");
		Map para = getParamMapForPage(request, "", 0);
		
		//request.setAttribute("branchCombo",FTCBranchValue.getBranchList());
		
		if(!"load".equals(this.getStrParam(request, "openType", ""))){
			para.put("slsBrno", this.getStrParam(request, "slsBrno", getUserInfo(request).getSelSlsBrno()));
			Collection list = authService.listUsrAuth(para);
			request.setAttribute("list", list);
			
			request.setAttribute("chgStatus", "N");
		}
	
		return mapping.findForward("list");
		
	}

	/**
	 * 권한 목록 엑셀
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 권한 목록 엑셀 다운
	 * @throws Exception
	 */
	public ActionForward authExcelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FTCAuthService authService = new FTCAuthService();
	
		log.debug("Start AuthAction.authExcelDown");
		
		Map para = getParamMapForPage(request, "grid", FTCConstants.ExcelSize);
			
		List list = authService.listUsrAuth(para);
		
		//헤더 생성 
        String fileName = "AUTH";
        String[] headerName = {"행번","사용자명","권한코드","권한명","등록일자","등록자"};
        String[] columnName = {"userEnob","userEnnm","fatcaAuthCd","fatcaAuthNm","chprDtti","chprEnnm"};
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}
	
	 /**
	   * 직원 조회 
	   * 
	   * @param mapping 
	   * @param form
	   * @param request
	   * @param response
	   * @return 직원 목록 (list) 
	   * @throws Exception
	   */
	public ActionForward listEmp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FTCAuthService authService = new FTCAuthService();
	
		log.debug("Start AuthAction.listEmp");
		Map para = getParamMapForPage(request, "", 0);
		
		//해당 권한 밑의 권한만 셋팅 가능하게 콤보 조정
		FTCCodeService codeService = new FTCCodeService();
		if("00".equals(this.getUserInfo(request).getFatcaAuthCd())){
			request.setAttribute("underAuthCombo",codeService.getCodeList(getStrParam(request,"crym",""), "131"));
		}else {
			
			request.setAttribute("underAuthCombo",codeService.getUnderCodeList(getStrParam(request,"crym",""), "131", 
					this.getUserInfo(request).getFatcaAuthCd()));
		}	
		
		Collection list = authService.listNoAuthEmp(para);
		request.setAttribute("list", list);
	
		return mapping.findForward("listEmp");
		
	}
	
	/**
	 * 권한 저장
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 권한 등록결과
	 * @throws Exception
	 */
	public ActionForward saveUsrAuth(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		log.debug("Start AuthsAction.saveUsrAuth");
				
		FTCAuthService authService = new FTCAuthService();
		FTCUserInfo userInfo = (FTCUserInfo)(request.getSession().getAttribute(FTCConstants.USER_KEY));

		Map para = getParamMapForPage(request, "", 0);	
		
		//권한로그 등록
		para.put("enob", userInfo.getUserEnob());
		para.put("ennm", userInfo.getUserEnnm());
		para.put("userIpAddr", request.getRemoteAddr());
		para.put("brno", userInfo.getSlsBrno());
		
		String dist = FTCStringUtil.nvl(request.getParameter("dist"),"");
		if("register".equals(dist)){
						
			authService.registerUsrAuth(para);			
			return null;
			
		}else if("remove".equals(dist)){
			
			authService.removeUsrAuth(para);
			
		}
				
		return this.listUsrAuth(mapping, form, request, response);
				
	}
		
	
	 /**
	   * 메뉴 권한 목록 조회 
	   * 
	   * @param mapping 
	   * @param form
	   * @param request
	   * @param response
	   * @return 권한 목록 (list) 
	   * @throws Exception
	   */
	public ActionForward listMenuAuth(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FTCAuthService authService = new FTCAuthService();
	
		log.debug("Start AuthAction.listMenuAuth");
		Map para = getParamMapForPage(request, "", 0);
		
		if(!"load".equals(this.getStrParam(request, "openType", ""))){
			FTCCodeService codeService = new FTCCodeService();
			//권한 사용 여부
			request.setAttribute("ynCombo",codeService.getCodeList(getStrParam(request,"crym",""), "802"));
			
			Collection list = authService.listMenuAuth(para);
			request.setAttribute("list", list);			
		}
	
		return mapping.findForward("listMenu");		
	}


	/**
	 * 메뉴 권한 목록 엑셀
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 메뉴 권한 목록 엑셀 다운
	 * @throws Exception
	 */
	public ActionForward menuExcelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FTCAuthService authService = new FTCAuthService();
	
		log.debug("Start AuthAction.menuExcelDown");
		
		Map para = getParamMapForPage(request, "grid", FTCConstants.ExcelSize);
			
		List list = authService.listMenuAuth(para);
		
		//헤더 생성 
        String fileName = "MENU";
        String[] headerName = {"메뉴ID","메뉴명","권한여부","등록일자","등록자"};
        String[] columnName = {"mnuId","mnuNm","useYn","chprDtti","chprEnnm"};
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}
	
	/**
	 * 메뉴 권한 저장
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 메뉴 권한 등록결과
	 * @throws Exception
	 */	
	public ActionForward saveMenuAuth(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		log.debug("Start AuthsAction.saveMenuAuth");
		
		FTCAuthService authService = new FTCAuthService();
		FTCUserInfo userInfo = (FTCUserInfo)(request.getSession().getAttribute(FTCConstants.USER_KEY));

		Map para = getParamMapForPage(request, "", 0);			

		para.put("mnuIdArr", request.getParameterValues("menuId"));
		para.put("useYnArr", request.getParameterValues("useYn"));
		para.put("befUseYnArr", request.getParameterValues("befUseYn"));
		
		para.put("enob", userInfo.getUserEnob());
		para.put("brno", userInfo.getSlsBrno());
			
		authService.saveMenuAuth(para);		
		
		return this.listMenuAuth(mapping, form, request, response);
	}
}
