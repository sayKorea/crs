package fatca.system.action;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import SafeSignOn.SSO;
import fatca.common.FTCConstants;
import fatca.common.base.FTCBaseAction;
import fatca.system.info.FTCUserInfo;
import fatca.system.service.FTCCodeService;
import fatca.system.service.FTCThreadLog;
import fatca.system.service.FTCUserService;

/**
 * @File Name    : LoginAction.java
 * @Package Name : fatca.system.action
 * @author       : 
 * @Description  : 로그인 관리
 * @History      : 
 */
public class FTCLoginAction extends FTCBaseAction {
	
	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * 로그인
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 메인 페이지
	 * @throws Exception
	 */
    public ActionForward login(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {
    	
    	log.debug("Start LoginAction.login");
    	
    	FTCUserService userService = new FTCUserService();    	
    	Map para = getParamMapForPage(request, "", 0);
    	
    	FTCCodeService codeService = new FTCCodeService();
    	para.put("envName", "LOGIN_USE_YN");
    	
    	//sso 로그인인지 확인    	
    	if(request.getAttribute("ID") != null || "Y".equals(codeService.getEnvSet(para))){
	    	
    		if(request.getAttribute("ID") != null){
        		para.put("ID", request.getAttribute("ID"));
        		para.put("userId", request.getAttribute("ID"));
        	}
        	
	    	if (userService.checkLogin(para)) {
	    		
	    		para.put("loginIpAddr", request.getRemoteAddr());
	    		FTCUserInfo userInfo = userService.findAuthentication(para);   
	    		
	    		HttpSession session = request.getSession();
	    		session.setAttribute(FTCConstants.USER_KEY, userInfo);
	    		
	    		//서버타입 들고다니기
		   		userInfo.setServerType(FTCConstants.SERVER_TYPE);
		   		
		   		//담당자면 최초 각담당자 선택값 세팅
		   		if("03".equals(userInfo.getFatcaAuthCd())){
		   			userInfo.setSelFatcaAcipRpprEnob(userInfo.getUserEnob());
		   			userInfo.setSelFatcaAcipRpprEnnm(userInfo.getUserEnnm());
		   		}else if("04".equals(userInfo.getFatcaAuthCd())){
		   			userInfo.setSelFatcaAcipCgpEnob(userInfo.getUserEnob());
		   			userInfo.setSelFatcaAcipCgpEnnm(userInfo.getUserEnnm());
		   		}else if("05".equals(userInfo.getFatcaAuthCd())){
		   			userInfo.setSelFatcaCustRelMngrEnob(userInfo.getUserEnob());
		   			userInfo.setSelFatcaCustRelMngrEnnm(userInfo.getUserEnnm());	
		   		}
		   		
	    		//접속 로그
	    		para.put("cnntPrsnEnob", userInfo.getUserEnob());
	    		para.put("userNm", userInfo.getUserEnnm());
	    		para.put("brno", userInfo.getSlsBrno());
	    		para.put("loginLogoutDvcd", "1");
	    		userService.addCnntLog(para);
	    			    		
	    		if("dev".equals(FTCConstants.SERVER_TYPE)){
	    			runLog();
	    		}
	    		
	    	}else{
	    		request.setAttribute("loginMessage","사용자 행번과 비밀번호가 일치하지 않습니다.");    	
	    		return mapping.findForward("login");
	    	}
    	
	    	return mapping.findForward("main");
    	}
    	
    	return mapping.findForward("loginFail");
    }
    
	/**
	 * 로그아웃
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 로그인 페이지
	 * @throws Exception
	 */
    public ActionForward logout(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {
    	
    	log.debug("Start LoginAction.logout");
    	
    	HttpSession session = request.getSession();    	
    	
    	FTCUserService userService = new FTCUserService();  
    	Map para = getParamMapForPage(request, "", 0);
    	
    	String isLogout = request.getParameter("isLogout");
    	
    	if (isLogout!=null && isLogout.equalsIgnoreCase("Y")){
    		FTCUserInfo userInfo = (FTCUserInfo)session.getAttribute(FTCConstants.USER_KEY);
    		if (userInfo==null){
    			return mapping.findForward("login");
		   	}else {
		   		//접속 로그
	    		para.put("cnntPrsnEnob", userInfo.getUserEnob());
	    		para.put("userNm", userInfo.getUserEnnm());
	    		para.put("brno", userInfo.getSlsBrno());
	    		para.put("loginIpAddr", request.getRemoteAddr().toString());
	    		para.put("loginLogoutDvcd", "0");
	    		userService.addCnntLog(para);
		   	}
			
    		session.removeAttribute(FTCConstants.USER_KEY);    		
    	}
    	
    	return mapping.findForward("login");
    }
   
       
    
	/**
	 * SSO 로그인
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 메인 페이지
	 * @throws Exception
	 */
    public ActionForward loginSso(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {
	    	
    		log.debug("Start LoginAction.loginSso");
    	
        	String ssoID = "";
	        String sToken = "";
	        String sApiKey = "368B184727E89AB69FAF";
	        String clientIp = "";
	        int nResult = -1;
	        
	        try {
	        	
	            clientIp = request.getRemoteAddr();
	
	            Cookie[] cookies = request.getCookies();
	            SSO sso = new SSO(sApiKey);
	            
	            if(cookies!=null){
	            	
	            	String ssoToken = FTCConstants.DEV_SSOTOKEN;
	            	if("prd".equals(FTCConstants.SERVER_TYPE)){
	            		ssoToken = FTCConstants.PRD_SSOTOKEN;
	            	}
	            	
	                for(int i=0; i < cookies.length ; i++){
	                    if(ssoToken.equals(cookies[i].getName())) { //dgb_dssotoken
	                        sToken = cookies[i].getValue();
	                    }
	                }
	            }
	            nResult = sso.verifyToken( sToken, clientIp ) ;
	
	            log.info("nResult="+nResult);
	            
	            if(nResult >= 0) {
	            	log.debug("OK!!! SSO ID!["+ ssoID +"]");
	            	ssoID = sso.getValueUserID();
	            }else{
	            	log.debug("Error!!! No SSO ID!");
	            	return mapping.findForward("loginFail");
	            }
	        }catch(Exception e){
	        	throw e;
	        }

	        request.setAttribute("ID",ssoID);
	        return this.login(mapping, form, request, response);
	            
	}		
	

	/**
	 * 로그인
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 로그인 페이지
	 * @throws Exception
	 */
    public ActionForward loginf(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {
    	
    	log.debug("Start LoginAction.loginf");
    	
    	Map para = getParamMapForPage(request, "", 0);
    	
    	FTCCodeService codeService = new FTCCodeService();
    	para.put("envName", "LOGIN_USE_YN");
    	    	
    	if("Y".equals(codeService.getEnvSet(para))){
	    
	    	return mapping.findForward("login");
    	}
    	
    	return mapping.findForward("loginno");
    }
    

	/**
	 * 로그 보이기
	 * 
	 * @throws Exception
	 */
   
    public void runLog() throws Exception {
    	
    	log.debug("Start LoginAction.runLog");
    	
		FTCThreadLog ftl = (FTCThreadLog)servlet.getServletContext().getAttribute("LOGTHREAD");
		if(ftl==null){		
			ftl = new FTCThreadLog();
			Thread t = new Thread(ftl);	
		    t.start();	
		    servlet.getServletContext().setAttribute("LOGTHREAD", ftl);
		}
		
    }
   
}
