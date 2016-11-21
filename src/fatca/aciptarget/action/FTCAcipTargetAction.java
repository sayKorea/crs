package fatca.aciptarget.action;

import java.util.ArrayList;
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

import fatca.aciptarget.service.FTCAcipCompanyTargetService;
import fatca.aciptarget.service.FTCAcipPersonalTargetService;
import fatca.common.FTCConstants;
import fatca.common.base.FTCBaseAction;
import fatca.common.decorator.FTCTableFormat;
import fatca.common.util.FTCDateUtil;
import fatca.common.util.FTCDbMap;
import fatca.system.service.FTCCodeService;

/**
 * @File Name    : AcipTargetAction.java
 * @Package Name : fatca.aciptarget.action
 * @author       : 
 * @Description  : 실사 대상 관리
 * @History      : 
 */
public class FTCAcipTargetAction extends FTCBaseAction {

	private Log	log	= LogFactory.getLog(this.getClass());

	/**
	 * 개인고객 실사 대상자 조회
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 개인고객 실사 대상자(list)
	 * @throws Exception
	 */
	public ActionForward personalList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipTargetAction.personalList"); 

		FTCAcipPersonalTargetService service = new FTCAcipPersonalTargetService();
		FTCTableFormat tf = new FTCTableFormat();
		
		Map para = getParamMapForPage(request, "personalGrid", FTCConstants.DefaultTableRow);

		//combo
		FTCCodeService codeService = new FTCCodeService();
		request.setAttribute("crymCombo",codeService.getCombo(para, "crym"));
		//계좌금액구간구분
		request.setAttribute("amtSctCombo",codeService.getBetweenCodeList(getStrParam(request,"crym",""), "114", "11","14"));

		//현재일자
		request.setAttribute("currentDate",FTCDateUtil.getToday());
		
		if(!"load".equals(this.getStrParam(request, "openType", ""))){
			List list = service.selectPersonalList(para);
			List nList = new ArrayList();
			for(int i=0; i<list.size(); i++){
				FTCDbMap nMap = (FTCDbMap)list.get(i);

				nMap.put("rnnoMsk", tf.convertRnnoMsk((String)nMap.get("rnno")));
				nMap.put("custNmMsk", tf.convertNameMskLen((String)nMap.get("custNm")));					
				
				nList.add(nMap);
			}
			request.setAttribute("list", nList);
		}
		

		//고객명 셋팅
		request.setAttribute("selCustNm", this.getCustNm(para));
		return mapping.findForward("personallist");
	}


	/**
	 * 개인고객 실사 대상자 조회 엑셀
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 개인고객 실사 대상자 조회 엑셀 다운
	 * @throws Exception
	 */
	public ActionForward personalExcelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("AcipPersonalTargetService.companyExcelDown"); 

		FTCAcipPersonalTargetService service = new FTCAcipPersonalTargetService();
		Map para = getParamMapForPage(request, "personalGrid", FTCConstants.ExcelSize);
			
		List list = service.selectPersonalList(para);
		
		//헤더 생성 
        String fileName = "PTAR";
        String[] headerName = {"영업점코드","영업점명","고객번호","고객명","실명번호","계좌금액구간구분","대상계좌수",
        					   "원화(원)","미화($)","실사책임자","실사담당자","고객관계관리자"};
        String[] columnName = {"slsBrno", "slsBrnm","csno","custNm","rnno","fatcaAmtSctClacdNm","acntCnt",
        					   "wcCnvtBalamt", "usdCnvtBalamt","fatcaAcipRpprEnnm","fatcaAcipCgpEnnm","fatcaCustRelMngrEnnm"};
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}
	
	
	/**
	 * 개인 상세조회 조회
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 개인 상세조회(list)
	 * @throws Exception
	 */
	public ActionForward personalView(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipTargetAction.personalView"); 
		FTCAcipPersonalTargetService service = new FTCAcipPersonalTargetService();
		
		Map para = getParamMapForPage(request, "", 0);
		
		//combo
		FTCCodeService codeService = new FTCCodeService();
		request.setAttribute("crymCombo",codeService.getCombo(para, "crym"));
				

		//현재일자
		request.setAttribute("currentDate",FTCDateUtil.getToday());
				
		if(!"load".equals(this.getStrParam(request, "openType", ""))){			
			/*개인기본*/
			FTCDbMap info = service.selectPrsnlBasList(para);
			request.setAttribute("prsnlBas", info);
	
			/*실사이력*/
			Collection listAcipHist = service.acipHistList(para);
			request.setAttribute("acipHist", listAcipHist);
	
			/*제출이력*/
			Collection listPrstHist = service.prstHistList(para);
			request.setAttribute("prstHist", listPrstHist);
	
			/*합산이력*/
			Collection listAdupHist = service.adupHistList(para);
			request.setAttribute("adupHist", listAdupHist);
			
			/*보고이력*/
			Collection listReptHist = service.reptHistList(para);
			request.setAttribute("reptHist", listReptHist);
		}

		//고객명 셋팅
		request.setAttribute("selCustNm", this.getCustNm(para));
		return mapping.findForward("personalview");

	}
	

	/**
	 * 개인고객 상세조회 - 탭(합산이력 상세)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 개인고객 실사 대상자(list)
	 * @throws Exception
	 */
	public ActionForward adupHistDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipTargetAction.adupHistDetail"); 
		FTCAcipPersonalTargetService service = new FTCAcipPersonalTargetService();
		
		Map para = getParamMapForPage(request, "", 0);
		
		/*실사이력*/
		if(para.get("crym")!=null){
			Collection listAdupHistDetail = service.adupHistDetail(para);
			request.setAttribute("adupHistDetail", listAdupHistDetail);
		}
		return mapping.findForward("aduphistdetail");
		
	}

	/**
	 * 개인고객 상세조회 - 탭(보고이력 상세)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 개인고객 실사 대상자(list)
	 * @throws Exception
	 */
	public ActionForward reptHistDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipTargetAction.reptHistDetail"); 
		FTCAcipPersonalTargetService service = new FTCAcipPersonalTargetService();		
		Map para = getParamMapForPage(request, "", 0);
		
		/*실사이력*/
		if(para.get("crym")!=null){
			Collection listReptHistDetail = service.reptHistDetail(para);
			request.setAttribute("reptHistDetail", listReptHistDetail);
		}
		return mapping.findForward("aduphistdetail");		
	}
	

	/**
	 * 법인고객 실사 대상자 조회
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 법인고객 실사 대상자(list)
	 * @throws Exception
	 */
	public ActionForward companyList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipTargetAction.companyList"); 

		FTCAcipCompanyTargetService service = new FTCAcipCompanyTargetService();
		FTCTableFormat tf = new FTCTableFormat();
		
		Map para = getParamMapForPage(request, "grid", FTCConstants.DefaultTableRow);

		//combo
		FTCCodeService codeService = new FTCCodeService();
		request.setAttribute("crymCombo",codeService.getCombo(para, "crym"));
		//계좌금액구간구분
		request.setAttribute("amtSctCombo",codeService.getBetweenCodeList(getStrParam(request,"crym",""), "114", "21","24"));

		//현재일자
		request.setAttribute("currentDate",FTCDateUtil.getToday());
		
		if(!"load".equals(this.getStrParam(request, "openType", ""))){	
			List list = service.selectCompanyList(para);
			List nList = new ArrayList();
			for(int i=0; i<list.size(); i++){
				FTCDbMap nMap = (FTCDbMap)list.get(i);

				nMap.put("rnnoMsk", tf.convertRnnoMsk((String)nMap.get("rnno")));
				nMap.put("custNmMsk", tf.convertNameMskLen((String)nMap.get("custNm")));					
				
				nList.add(nMap);
			}
			request.setAttribute("list", nList);
		}
		

		//고객명 셋팅
		request.setAttribute("selCustNm", this.getCustNm(para));
		return mapping.findForward("companylist");
	}


	/**
	 * 법인고객 실사 대상자 조회 엑셀
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 법인고객 실사 대상자 조회 엑셀 다운
	 * @throws Exception
	 */
	public ActionForward companyExcelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("AcipPersonalTargetService.companyExcelDown"); 

		FTCAcipCompanyTargetService service = new FTCAcipCompanyTargetService();
		Map para = getParamMapForPage(request, "grid", FTCConstants.ExcelSize);
			
		List list = service.selectCompanyList(para);
		
		//헤더 생성 
        String fileName = "CTAR";
        String[] headerName = {"영업점코드","영업점명","고객번호","고객명","법인번호/사업자번호","계좌금액구간구분","대상계좌수",
				   "원화(원)","미화($)","실사책임자","실사담당자"};
        String[] columnName = {"slsBrno", "slsBrnm","csno","custNm","rnno","fatcaAmtSctClacdNm","acntCnt",
				   "wcCnvtBalamt", "usdCnvtBalamt","fatcaAcipRpprEnnm","fatcaAcipCgpEnnm"};
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}
	
	/**
	 * 법인고객 상세조회
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 법인고객 상세조회(list)
	 * @throws Exception
	 */
	public ActionForward companyView(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipTargetAction.companyView"); 
		FTCAcipCompanyTargetService service = new FTCAcipCompanyTargetService();
		
		Map para = getParamMapForPage(request, "", 0);
		
		//combo
		FTCCodeService codeService = new FTCCodeService();
		request.setAttribute("crymCombo",codeService.getCombo(para, "crym"));
				

		//현재일자
		request.setAttribute("currentDate",FTCDateUtil.getToday());
				
		if(!"load".equals(this.getStrParam(request, "openType", ""))){				
			/*개인기본*/
			FTCDbMap info = service.selectCompBasList(para);
			request.setAttribute("compBas", info);
	
			/*실사이력*/
			Collection listAcipHist = service.acipHistList(para);
			request.setAttribute("acipHist", listAcipHist);
	
			/*제출이력*/
			Collection listPrstHist = service.prstHistList(para);
			request.setAttribute("prstHist", listPrstHist);
	
			/*합산이력*/
			Collection listAdupHist = service.adupHistList(para);
			request.setAttribute("adupHist", listAdupHist);
			
			/*보고이력*/
			Collection listReptHist = service.reptHistList(para);
			request.setAttribute("reptHist", listReptHist);
		}

		//고객명 셋팅
		request.setAttribute("selCustNm", this.getCustNm(para));
		return mapping.findForward("companyview");		
	}
	

	/**
	 * 법인고객 상세조회 - 탭(합산이력 상세)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 법인고객 실사 대상자(list)
	 * @throws Exception
	 */
	public ActionForward compAdupHistDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipTargetAction.compAdupHistDetail"); 
		FTCAcipCompanyTargetService service = new FTCAcipCompanyTargetService();
		
		Map para = getParamMapForPage(request, "", 0);
		
		/*실사이력*/
		if(para.get("crym")!=null){
			Collection listAdupHistDetail = service.compAdupHistDetail(para);
			request.setAttribute("compAdupHistDetail", listAdupHistDetail);
		}
		return mapping.findForward("aduphistdetail");
		
	}

	/**
	 * 법인고객 상세조회 - 탭(보고이력 상세)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 법인고객 실사 대상자(list)
	 * @throws Exception
	 */
	public ActionForward compReptHistDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipTargetAction.compReptHistDetail"); 
		FTCAcipCompanyTargetService service = new FTCAcipCompanyTargetService();		
		Map para = getParamMapForPage(request, "", 0);
		
		/*실사이력*/
		if(para.get("crym")!=null){
			Collection listReptHistDetail = service.compReptHistDetail(para);
			request.setAttribute("compReptHistDetail", listReptHistDetail);
		}	
		return mapping.findForward("aduphistdetail");
		
	}
	

	/**
	 * FATCA확인서 제출요청 이력 조회
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return FATCA확인서 제출요청 이력(list)
	 * @throws Exception
	 */
	public ActionForward confirmRequestHist(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipTargetAction.confirmRequestHist"); 

		FTCAcipCompanyTargetService service = new FTCAcipCompanyTargetService();
		
		Map para = getParamMapForPage(request, "grid", FTCConstants.DefaultTableRow);

		//combo
		FTCCodeService codeService = new FTCCodeService();
		request.setAttribute("crymCombo",codeService.getCombo(para, "crym"));
		//계좌금액구간구분
		request.setAttribute("amtSctCombo",codeService.getCodeList(getStrParam(request,"crym",""), "114"));		

		//SMS발송차수
		request.setAttribute("smsSendCombo",codeService.getCodeList(getStrParam(request,"crym",""), "130"));

		//현재일자
		request.setAttribute("currentDate",FTCDateUtil.getToday());
				
		if(!"load".equals(this.getStrParam(request, "openType", ""))){	
			Collection list = service.confirmRequestHist(para);
			request.setAttribute("list", list);
		}

		//고객명 셋팅
		request.setAttribute("selCustNm", this.getCustNm(para));
		return mapping.findForward("confirmrequesthist");
	}
	

	/**
	 * FATCA확인서 제출요청 이력 조회 엑셀
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return FATCA확인서 제출요청 이력 조회 엑셀 다운
	 * @throws Exception
	 */
	public ActionForward confirmExcelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("AcipTargetAction.confirmRequestHist"); 

		FTCAcipCompanyTargetService service = new FTCAcipCompanyTargetService();
		
		Map para = getParamMapForPage(request, "grid", FTCConstants.ExcelSize);
			
		List list = service.confirmRequestHist(para);
		
		//헤더 생성 
        String fileName = "SMS";
        String[] headerName = {"기준년월","발송차수","발송일자","고객번호","고객명","전화번호","발송내역"};
        String[] columnName = {"crym", "smsFwDge","fwDt","csno","custNm","smsNtiMpno","smsMsgCn"};
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}
	
}
