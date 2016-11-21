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
 * @Description  : ��Ȳ ����
 * @History      : 
 */
public class FTCAcipMonitorAction extends FTCBaseAction {

	private Log	log	= LogFactory.getLog(this.getClass());

	/**
	 * FATCA �ǻ� ����͸�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ����͸� ���(list)
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

		//���� ����
		request.setAttribute("selCustNm", this.getCustNm(para));
		return mapping.findForward("monitor");
	}
	
	
	/**
	 * FATCA �ǻ� ����͸� ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return FATCA �ǻ� ����͸� ���� �ٿ�
	 * @throws Exception
	 */
	public ActionForward monitorExcelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FTCAcipMonitorService acipMonitorService = new FTCAcipMonitorService();
	
		log.debug("Start acipMonitorService.monitorExcelDown");
		Map para = getParamMapForPage(request, "monitorGrid", FTCConstants.ExcelSize);
			
		List list = acipMonitorService.selectBranchStausList(para);
		
		//��� ���� 
        String fileName = "SBR";
        String[] headerName = {"������", "�ǻ�å����","�ǻ�����","�����������","�ǻ������(��)",
        					   "�ǻ�Ϸ����(��)","��ô��(%)","�հ�","����","������","�񺸰���(��)"};
        String[] columnName = {"slsBrnoNm", "fatcaAcipRpprEnobNm","fatcaAcipCgpEnobNm","fatcaCustRelMngrEnobNm",
        					   "targerAcip","comptAcip","acipRate","reportAcip","cprtAcip","noCprtAcip","noReportAcip"};
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}
	

	/**
	 * FACTA ������ �ǻ��̷� ��ȸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return FACTA ������ �ǻ��̷� ���(list)
	 * @throws Exception
	 */
	public ActionForward year(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Start BoardAction.year");
		FTCAcipMonitorService acipMonitorService = new FTCAcipMonitorService();
		
		Map para = getParamMapForPage(request, "grid", FTCConstants.DefaultTableRow);
		
		//combo
		FTCCodeService codeService = new FTCCodeService();
		//�����ǻ���
		request.setAttribute("custDvcdCombo",codeService.getCodeList(getStrParam(request,"crym",""), "121"));
		request.setAttribute("crymStCombo",codeService.getCombo(para, "crym"));
		request.setAttribute("crymEndCombo",codeService.getCombo(para, "crym"));
		
		if(!"init".equals(getStrParam(request, "type", ""))){
			Collection list = acipMonitorService.selectAcipYearHistoryList(para);
			request.setAttribute("list", list);
		}

		//���� ����
		request.setAttribute("selCustNm", this.getCustNm(para));
		return mapping.findForward("year");
	}
	

	/**
	 * FACTA ������ �ǻ��̷� ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return FACTA ������ �ǻ��̷� ���� �ٿ�
	 * @throws Exception
	 */
	public ActionForward yearExcelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FTCAcipMonitorService acipMonitorService = new FTCAcipMonitorService();
		
		log.debug("Start acipMonitorService.yearExcelDown");
		Map para = getParamMapForPage(request, "grid", FTCConstants.ExcelSize);
			
		List list = acipMonitorService.selectAcipYearHistoryList(para);
		
		//��� ���� 
        String fileName = "YEAR";
        String[] headerName = {"���س⵵", "�� �ǻ�����(��)","������(��)","������(��)","�񺸰���(��)"};
        String[] columnName = {"crym", "totCnt","reportAcip","noCprtAcip","noReportAcip"};
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}
	
}
