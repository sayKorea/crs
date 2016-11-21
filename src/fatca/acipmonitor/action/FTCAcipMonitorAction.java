package fatca.acipmonitor.action;

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

import fatca.acipmonitor.service.FTCAcipMonitorService;
import fatca.common.FTCConstants;
import fatca.common.base.FTCBaseAction;
import fatca.system.service.FTCCodeService;

/**
 * @File Name    : AcipMonitorAction.java
 * @Package Name : fatca.acipmonitor.action
 * @author       : 
 * @Description  : 현황 관리
 * @History      : 
 */
public class FTCAcipMonitorAction extends FTCBaseAction {

	private Log	log	= LogFactory.getLog(this.getClass());

	/**
	 * FATCA 실사 모니터링
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 모니터링 목록(list)
	 * @throws Exception
	 */
	public ActionForward monitor(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Start BoardAction.branchStausMonitor");
		FTCAcipMonitorService acipMonitorService = new FTCAcipMonitorService();
		
		Map para = getParamMapForPage(request, "monitorGrid", FTCConstants.DefaultTableRow);
		
/*		para.put("slsBrno", "%" + getStrParam(request, "slsBrno", "") + "%");
		para.put("search2", "%" + getStrParam(request, "search2", "") + "%");
		para.put("search3", "%" + getStrParam(request, "search3", "") + "%");
		para.put("search4", "%" + getStrParam(request, "search4", "") + "%");*/

		if(!"init".equals(getStrParam(request, "type", ""))){
			Collection list = acipMonitorService.selectBranchStausList(para);
			request.setAttribute("list", list);
		}
		
		//combo
		FTCCodeService codeService = new FTCCodeService();
		request.setAttribute("crymCombo",codeService.getCombo(para, "crym"));

		//고객명 셋팅
		request.setAttribute("selCustNm", this.getCustNm(para));
		return mapping.findForward("monitor");
	}
	
	
	/**
	 * FATCA 실사 모니터링 엑셀
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return FATCA 실사 모니터링 엑셀 다운
	 * @throws Exception
	 */
	public ActionForward monitorExcelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FTCAcipMonitorService acipMonitorService = new FTCAcipMonitorService();
	
		log.debug("Start acipMonitorService.monitorExcelDown");
		Map para = getParamMapForPage(request, "monitorGrid", FTCConstants.ExcelSize);
			
		List list = acipMonitorService.selectBranchStausList(para);
		
		//헤더 생성 
        String fileName = "SBR";
        String[] headerName = {"영업점", "실사책임자","실사담당자","고객관계관리자","실사대상고객수(명)",
        					   "실사완료고객수(명)","진척률(%)","합계","협조","비협조","비보고대상(명)"};
        String[] columnName = {"slsBrnoNm", "fatcaAcipRpprEnobNm","fatcaAcipCgpEnobNm","fatcaCustRelMngrEnobNm",
        					   "targerAcip","comptAcip","acipRate","reportAcip","cprtAcip","noCprtAcip","noReportAcip"};
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}
	

	/**
	 * FACTA 연도별 실사이력 조회
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return FACTA 연도별 실사이력 목록(list)
	 * @throws Exception
	 */
	public ActionForward year(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Start BoardAction.year");
		FTCAcipMonitorService acipMonitorService = new FTCAcipMonitorService();
		
		Map para = getParamMapForPage(request, "grid", FTCConstants.DefaultTableRow);
		
		//combo
		FTCCodeService codeService = new FTCCodeService();
		//문서실사결과
		request.setAttribute("custDvcdCombo",codeService.getCodeList(getStrParam(request,"crym",""), "121"));
		request.setAttribute("crymStCombo",codeService.getCombo(para, "crym"));
		request.setAttribute("crymEndCombo",codeService.getCombo(para, "crym"));
		
		if(!"init".equals(getStrParam(request, "type", ""))){
			Collection list = acipMonitorService.selectAcipYearHistoryList(para);
			request.setAttribute("list", list);
		}

		//고객명 셋팅
		request.setAttribute("selCustNm", this.getCustNm(para));
		return mapping.findForward("year");
	}
	

	/**
	 * FACTA 연도별 실사이력 엑셀
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return FACTA 연도별 실사이력 엑셀 다운
	 * @throws Exception
	 */
	public ActionForward yearExcelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FTCAcipMonitorService acipMonitorService = new FTCAcipMonitorService();
		
		log.debug("Start acipMonitorService.yearExcelDown");
		Map para = getParamMapForPage(request, "grid", FTCConstants.ExcelSize);
			
		List list = acipMonitorService.selectAcipYearHistoryList(para);
		
		//헤더 생성 
        String fileName = "YEAR";
        String[] headerName = {"기준년도", "총 실사대상자(명)","보고대상(명)","비협조(명)","비보고대상(명)"};
        String[] columnName = {"crym", "totCnt","reportAcip","noCprtAcip","noReportAcip"};
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}
	
}
