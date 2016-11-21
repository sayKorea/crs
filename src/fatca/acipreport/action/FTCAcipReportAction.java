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
 * @Description  : ���� ����
 * @History      : 
 */
public class FTCAcipReportAction extends FTCBaseAction {

	private Log	log	= LogFactory.getLog(this.getClass());

	/**
	 * ���� ���� ��ȸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ���� ���� ��ȸ(list)
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

		//���� ����
		request.setAttribute("selCustNm", this.getCustNm(para));
		return mapping.findForward("personalreportlist");
	}
	


	/**
	 * ���� ���� ��ȸ ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ���� ���� ��ȸ ���� �ٿ�
	 * @throws Exception
	 */
	public ActionForward personalExcelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("AcipReportAction.personalExcelDown"); 

		FTCAcipReportService service = new FTCAcipReportService();
		Map para = getParamMapForPage(request, "grid", FTCConstants.ExcelSize);
			
		List list = service.selectPersonalReportList(para);
		
		//��� ���� 
        String fileName = "PRPT";
        String[] headerName = {"�������ڵ�","��������","����(�ѱ�)","����(����)","�ּ�","TIN","�������",
        					   "���¹�ȣ","�������","�ܾ�","�����Ѿ�","����Ѿ�","��Ÿ�����Ѿ�","�Ѱŷ�����"};
        String[] columnName = {"slsBrno", "slsBrnm","custNm","custEnm","fatcaPrsnlEngAddr","perTxpayrNo",
        					   "bird","acno","giinName","giinNo","acntBalamt", "ntrstAmt","shramt","etcm","totNcmRbam"};
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}
	
	/**
	 * ���� ���� ��ȸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ���� ���� ��ȸ(list)
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

		//���� ����
		request.setAttribute("selCustNm", this.getCustNm(para));
		return mapping.findForward("companyreportlist");
	}


	/**
	 * ���� ���� ��ȸ ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ���� ���� ��ȸ ���� �ٿ�
	 * @throws Exception
	 */
	public ActionForward companyExcelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("AcipReportAction.companyExcelDown"); 

		FTCAcipReportService service = new FTCAcipReportService();
		Map para = getParamMapForPage(request, "grid", FTCConstants.ExcelSize);
			
		List list = service.selectCompanyReportList(para);
		
		//��� ���� 
        String fileName = "CRPT";
        String[] headerName = {"�������ڵ�","��������","�������� ����(�ѱ�)","�������� ����(����)","�������� EIN",
        					   "�������� �ּ�","�������� �������","�������� GIIN","���������� ��������","���������� �ּ�",
        					   "���������� TIN","���¹�ȣ","�ܾ�","�����Ѿ�","����Ѿ�","��Ÿ�����Ѿ�","�Ѱŷ�����"};
        String[] columnName = {"slsBrno", "slsBrnm","fatcaCoprKnm","fatcaCoprEnm","fatcaTxpayrNo",
        					   "fatcaCoprEngAddr","giinName", "giinNo","perNm","fatcaPrsnlEngAddr",
        					   "perTxpayrNo","acno","acntBalamt","ntrstAmt","shramt","etcm","totNcmRbam"};
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}
}
