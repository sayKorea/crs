package fatca.acipreport.action;

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

import fatca.acipreport.service.FTCAcipReportService;
import fatca.common.FTCConstants;
import fatca.common.base.FTCBaseAction;
import fatca.system.service.FTCCodeService;

/**
 * @File Name    : AcipReportAction.java
 * @Package Name : fatca.acipreport.action
 * @author       : 
 * @Description  : 보고 관리
 * @History      : 
 */
public class FTCAcipReportAction extends FTCBaseAction {

	private Log	log	= LogFactory.getLog(this.getClass());

	/**
	 * 개인 보고서 조회
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 개인 보고서 조회(list)
	 * @throws Exception
	 */
	public ActionForward personalReportList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipReportAction.personalReportList"); 

		FTCAcipReportService service = new FTCAcipReportService();
		
		Map para = getParamMapForPage(request, "grid", FTCConstants.DefaultTableRow);

		//combo
		FTCCodeService codeService = new FTCCodeService();
		request.setAttribute("crymCombo",codeService.getCombo(para, "crym"));
		request.setAttribute("combo801",codeService.getCodeList((String)para.get("crym"), "801"));		
		
		//if(!"load".equals(this.getStrParam(request, "openType", ""))){		
			Collection list = service.selectPersonalReportList(para);
			request.setAttribute("list", list);
		//}

		//고객명 셋팅
		request.setAttribute("selCustNm", this.getCustNm(para));
		return mapping.findForward("personalreportlist");
	}
	


	/**
	 * 개인 보고서 조회 엑셀
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 개인 보고서 조회 엑셀 다운
	 * @throws Exception
	 */
	public ActionForward personalExcelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("AcipReportAction.personalExcelDown"); 

		FTCAcipReportService service = new FTCAcipReportService();
		Map para = getParamMapForPage(request, "grid", FTCConstants.ExcelSize);
			
		List list = service.selectPersonalReportList(para);
		
		//헤더 생성 
        String fileName = "PRPT";
        String[] headerName = {"영업점코드","영업점명","고객명(한글)","고객명(영문)","주소","TIN","생년월일",
        					   "계좌번호","금융기관","잔액","이자총액","배당총액","기타수익총액","총거래가액"};
        String[] columnName = {"slsBrno", "slsBrnm","custNm","custEnm","fatcaPrsnlEngAddr","perTxpayrNo",
        					   "bird","acno","giinName","giinNo","acntBalamt", "ntrstAmt","shramt","etcm","totNcmRbam"};
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}
	
	/**
	 * 법인 보고서 조회
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 법인 보고서 조회(list)
	 * @throws Exception
	 */
	public ActionForward companyReportList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipReportAction.companyReportList"); 

		FTCAcipReportService service = new FTCAcipReportService();
		
		Map para = getParamMapForPage(request, "grid", FTCConstants.DefaultTableRow);

		//combo
		FTCCodeService codeService = new FTCCodeService();
		request.setAttribute("crymCombo",codeService.getCombo(para, "crym"));
		request.setAttribute("combo801",codeService.getCodeList((String)para.get("crym"), "801"));		
		
		if(!"load".equals(this.getStrParam(request, "openType", ""))){		
			Collection list = service.selectCompanyReportList(para);
			request.setAttribute("list", list);
		}

		//고객명 셋팅
		request.setAttribute("selCustNm", this.getCustNm(para));
		return mapping.findForward("companyreportlist");
	}


	/**
	 * 법인 보고서 조회 엑셀
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 법인 보고서 조회 엑셀 다운
	 * @throws Exception
	 */
	public ActionForward companyExcelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("AcipReportAction.companyExcelDown"); 

		FTCAcipReportService service = new FTCAcipReportService();
		Map para = getParamMapForPage(request, "grid", FTCConstants.ExcelSize);
			
		List list = service.selectCompanyReportList(para);
		
		//헤더 생성 
        String fileName = "CRPT";
        String[] headerName = {"영업점코드","영업점명","법인정보 고객명(한글)","법인정보 고객명(영문)","법인정보 EIN",
        					   "법인정보 주소","법인정보 금융기관","법인정보 GIIN","실질소유자 영문성명","실질소유자 주소",
        					   "실질소유자 TIN","계좌번호","잔액","이자총액","배당총액","기타수익총액","총거래가액"};
        String[] columnName = {"slsBrno", "slsBrnm","fatcaCoprKnm","fatcaCoprEnm","fatcaTxpayrNo",
        					   "fatcaCoprEngAddr","giinName", "giinNo","perNm","fatcaPrsnlEngAddr",
        					   "perTxpayrNo","acno","acntBalamt","ntrstAmt","shramt","etcm","totNcmRbam"};
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}
}
