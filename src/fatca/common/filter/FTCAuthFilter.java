package fatca.common.filter;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fatca.common.FTCConstants;
import fatca.common.util.FTCBranchValue;
import fatca.common.util.FTCEmployeeValue;
import fatca.system.info.FTCUserInfo;
import fatca.system.service.FTCAuthService;

/**
 * @File Name    : AuthFilter.java
 * @Package Name : fatca.common.filter;
 * @author       : 
 * @Description  : 권한 분류
 * @History      : 
 */
public class FTCAuthFilter implements Filter {
	public static String LOGIN_KEY = "loggedIn"; 
	
	private String loginAction; 
	private String loginPage;
	private String noAuthPage;	
	private String noSessionPage;
	private String loginSsoAction;
	
	protected FilterConfig filterConfig = null;

	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * 권한 분류
	 * 
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		request.setCharacterEncoding("EUC_KR");
		String servletPath = "";
		
		if(req.getParameter("method")==null){
			servletPath = req.getServletPath();
		}else{
			servletPath = req.getServletPath()+"?method="+req.getParameter("method");
		}
		String conPath = req.getContextPath();
		String uri = req.getRequestURI();
		
		HttpSession session = req.getSession();
		FTCUserInfo userInfo = (FTCUserInfo) session.getAttribute(FTCConstants.USER_KEY);
		
		log.debug("conPath "+conPath+"    servletPath  "+servletPath+"    uri "+uri);
		
		// 로그인 페이지로 가는 중이면 그냥 보내주고
		if (servletPath.equals(this.loginAction) || servletPath.equals(this.loginPage) 
				|| servletPath.equals(this.noAuthPage) || servletPath.equals(this.noSessionPage) 
				|| servletPath.equals(this.loginSsoAction) 
				|| ("/").equals(servletPath) 
				|| servletPath.startsWith("/chart") ) {
			chain.doFilter(req, res);
			
		} else if (userInfo == null) {	
			// 일반페이지로 가는데 userInfo가 세션에 없으면 로그아웃으로 간주				
			log.debug("session out!!");
			res.sendRedirect(req.getContextPath() + this.loginPage);
			
		} else {

			//화면 선택된거 세팅		
			//변경된 영업점,기준년월 세팅
			if(req.getParameter("crym")!=null){
				userInfo.setSelCrym((String)req.getParameter("crym"));
			}
			if(req.getParameter("slsBrno")!=null && !"".equals(req.getParameter("slsBrno"))){
				userInfo.setSelSlsBrno((String)req.getParameter("slsBrno"));
				userInfo.setSelSlsBrnm((String)FTCBranchValue.getBrnm((String)req.getParameter("slsBrno")));
			}
			if(req.getParameter("fatcaAcipRpprEnob")!=null){	
				userInfo.setSelFatcaAcipRpprEnob((String)req.getParameter("fatcaAcipRpprEnob"));
				userInfo.setSelFatcaAcipRpprEnnm((String)FTCEmployeeValue.getEnnm((String)req.getParameter("fatcaAcipRpprEnob")));
			}
			if(req.getParameter("fatcaAcipCgpEnob")!=null){	
				userInfo.setSelFatcaAcipCgpEnob((String)req.getParameter("fatcaAcipCgpEnob"));
				userInfo.setSelFatcaAcipCgpEnnm((String)FTCEmployeeValue.getEnnm((String)req.getParameter("fatcaAcipCgpEnob")));
			}
			if(req.getParameter("fatcaCustRelMngrEnob")!=null){	
				userInfo.setSelFatcaCustRelMngrEnob((String)req.getParameter("fatcaCustRelMngrEnob"));
				userInfo.setSelFatcaCustRelMngrEnnm((String)FTCEmployeeValue.getEnnm((String)req.getParameter("fatcaCustRelMngrEnob")));
			}
			
			//영업점 콤보 세팅
			if("05".equals(userInfo.getFatcaAuthCd())){
				request.setAttribute("branchCombo",userInfo.getBranchComboRel());
			}else{
				request.setAttribute("branchCombo",FTCBranchValue.getBranchList());
			}
			  
			//거래로그 			
			try {
				
				if(req.getParameter("mnuId")!=null){
					FTCAuthService authService = new FTCAuthService();
					HashMap trnsMap = new HashMap();
					
					trnsMap.put("crym", req.getParameter("crym"));
					trnsMap.put("enob", userInfo.getUserEnob());
					trnsMap.put("ennm", userInfo.getUserEnnm());
					trnsMap.put("brno", userInfo.getSlsBrno());
					trnsMap.put("userIpAddr", req.getRemoteAddr());
					trnsMap.put("mnuId", req.getParameter("mnuId"));
					trnsMap.put("csno", req.getParameter("csno"));
					trnsMap.put("custSeq", req.getParameter("custSeq"));
					trnsMap.put("inqRsc", "29");					
					
					authService.saveTrnsLog(trnsMap);	
					
					//마감여부
					request.setAttribute("isMainComplete", authService.isMainComplete((String)req.getParameter("crym")));
				
				}
				
			}catch (Exception e) {
				throw new ServletException();
				//log.debug("거래로그 오류");
			}
			
			
			//String uri = req.getRequestURI();
			chain.doFilter(req, res); 
			
		}
		
	}

	/**
	 * init
	 * 
	 * @param filterConfig

	 * @throws ServletException
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
        		
		this.loginAction = filterConfig.getInitParameter("loginAction");
		this.loginPage = filterConfig.getInitParameter("loginPage");
		this.noAuthPage = filterConfig.getInitParameter("noAuthPage");
		this.noSessionPage = filterConfig.getInitParameter("noSessionPage");
		this.loginSsoAction = filterConfig.getInitParameter("loginSsoAction");
		
	}

	/**
	 * destroy
	 * 
	 */
	public void destroy() {
		log.debug("destroy");
	}
	

    /**
     * 필터를 얻어온다.
     * @return
     */
    public FilterConfig getFilterConfig() {
        return this.filterConfig;
    }

    /**
     * 필터를 설정한다.
     * @param cfg
     */
    public void setFilterConfig(FilterConfig cfg) {
    	this.filterConfig = cfg;
    }	
       
}
