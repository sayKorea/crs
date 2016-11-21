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
 * @Description  : �ǻ� ��� ����
 * @History      : 
 */
public class FTCAcipTargetAction extends FTCBaseAction {

	private Log	log	= LogFactory.getLog(this.getClass());

	/**
	 * ���ΰ� �ǻ� ����� ��ȸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ���ΰ� �ǻ� �����(list)
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
		//���±ݾױ�������
		request.setAttribute("amtSctCombo",codeService.getBetweenCodeList(getStrParam(request,"crym",""), "114", "11","14"));

		//��������
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
		

		//���� ����
		request.setAttribute("selCustNm", this.getCustNm(para));
		return mapping.findForward("personallist");
	}


	/**
	 * ���ΰ� �ǻ� ����� ��ȸ ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ���ΰ� �ǻ� ����� ��ȸ ���� �ٿ�
	 * @throws Exception
	 */
	public ActionForward personalExcelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("AcipPersonalTargetService.companyExcelDown"); 

		FTCAcipPersonalTargetService service = new FTCAcipPersonalTargetService();
		Map para = getParamMapForPage(request, "personalGrid", FTCConstants.ExcelSize);
			
		List list = service.selectPersonalList(para);
		
		//��� ���� 
        String fileName = "PTAR";
        String[] headerName = {"�������ڵ�","��������","����ȣ","����","�Ǹ��ȣ","���±ݾױ�������","�����¼�",
        					   "��ȭ(��)","��ȭ($)","�ǻ�å����","�ǻ�����","�����������"};
        String[] columnName = {"slsBrno", "slsBrnm","csno","custNm","rnno","fatcaAmtSctClacdNm","acntCnt",
        					   "wcCnvtBalamt", "usdCnvtBalamt","fatcaAcipRpprEnnm","fatcaAcipCgpEnnm","fatcaCustRelMngrEnnm"};
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}
	
	
	/**
	 * ���� ����ȸ ��ȸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ���� ����ȸ(list)
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
				

		//��������
		request.setAttribute("currentDate",FTCDateUtil.getToday());
				
		if(!"load".equals(this.getStrParam(request, "openType", ""))){			
			/*���α⺻*/
			FTCDbMap info = service.selectPrsnlBasList(para);
			request.setAttribute("prsnlBas", info);
	
			/*�ǻ��̷�*/
			Collection listAcipHist = service.acipHistList(para);
			request.setAttribute("acipHist", listAcipHist);
	
			/*�����̷�*/
			Collection listPrstHist = service.prstHistList(para);
			request.setAttribute("prstHist", listPrstHist);
	
			/*�ջ��̷�*/
			Collection listAdupHist = service.adupHistList(para);
			request.setAttribute("adupHist", listAdupHist);
			
			/*�����̷�*/
			Collection listReptHist = service.reptHistList(para);
			request.setAttribute("reptHist", listReptHist);
		}

		//���� ����
		request.setAttribute("selCustNm", this.getCustNm(para));
		return mapping.findForward("personalview");

	}
	

	/**
	 * ���ΰ� ����ȸ - ��(�ջ��̷� ��)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ���ΰ� �ǻ� �����(list)
	 * @throws Exception
	 */
	public ActionForward adupHistDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipTargetAction.adupHistDetail"); 
		FTCAcipPersonalTargetService service = new FTCAcipPersonalTargetService();
		
		Map para = getParamMapForPage(request, "", 0);
		
		/*�ǻ��̷�*/
		if(para.get("crym")!=null){
			Collection listAdupHistDetail = service.adupHistDetail(para);
			request.setAttribute("adupHistDetail", listAdupHistDetail);
		}
		return mapping.findForward("aduphistdetail");
		
	}

	/**
	 * ���ΰ� ����ȸ - ��(�����̷� ��)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ���ΰ� �ǻ� �����(list)
	 * @throws Exception
	 */
	public ActionForward reptHistDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipTargetAction.reptHistDetail"); 
		FTCAcipPersonalTargetService service = new FTCAcipPersonalTargetService();		
		Map para = getParamMapForPage(request, "", 0);
		
		/*�ǻ��̷�*/
		if(para.get("crym")!=null){
			Collection listReptHistDetail = service.reptHistDetail(para);
			request.setAttribute("reptHistDetail", listReptHistDetail);
		}
		return mapping.findForward("aduphistdetail");		
	}
	

	/**
	 * ���ΰ� �ǻ� ����� ��ȸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ���ΰ� �ǻ� �����(list)
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
		//���±ݾױ�������
		request.setAttribute("amtSctCombo",codeService.getBetweenCodeList(getStrParam(request,"crym",""), "114", "21","24"));

		//��������
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
		

		//���� ����
		request.setAttribute("selCustNm", this.getCustNm(para));
		return mapping.findForward("companylist");
	}


	/**
	 * ���ΰ� �ǻ� ����� ��ȸ ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ���ΰ� �ǻ� ����� ��ȸ ���� �ٿ�
	 * @throws Exception
	 */
	public ActionForward companyExcelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("AcipPersonalTargetService.companyExcelDown"); 

		FTCAcipCompanyTargetService service = new FTCAcipCompanyTargetService();
		Map para = getParamMapForPage(request, "grid", FTCConstants.ExcelSize);
			
		List list = service.selectCompanyList(para);
		
		//��� ���� 
        String fileName = "CTAR";
        String[] headerName = {"�������ڵ�","��������","����ȣ","����","���ι�ȣ/����ڹ�ȣ","���±ݾױ�������","�����¼�",
				   "��ȭ(��)","��ȭ($)","�ǻ�å����","�ǻ�����"};
        String[] columnName = {"slsBrno", "slsBrnm","csno","custNm","rnno","fatcaAmtSctClacdNm","acntCnt",
				   "wcCnvtBalamt", "usdCnvtBalamt","fatcaAcipRpprEnnm","fatcaAcipCgpEnnm"};
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}
	
	/**
	 * ���ΰ� ����ȸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ���ΰ� ����ȸ(list)
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
				

		//��������
		request.setAttribute("currentDate",FTCDateUtil.getToday());
				
		if(!"load".equals(this.getStrParam(request, "openType", ""))){				
			/*���α⺻*/
			FTCDbMap info = service.selectCompBasList(para);
			request.setAttribute("compBas", info);
	
			/*�ǻ��̷�*/
			Collection listAcipHist = service.acipHistList(para);
			request.setAttribute("acipHist", listAcipHist);
	
			/*�����̷�*/
			Collection listPrstHist = service.prstHistList(para);
			request.setAttribute("prstHist", listPrstHist);
	
			/*�ջ��̷�*/
			Collection listAdupHist = service.adupHistList(para);
			request.setAttribute("adupHist", listAdupHist);
			
			/*�����̷�*/
			Collection listReptHist = service.reptHistList(para);
			request.setAttribute("reptHist", listReptHist);
		}

		//���� ����
		request.setAttribute("selCustNm", this.getCustNm(para));
		return mapping.findForward("companyview");		
	}
	

	/**
	 * ���ΰ� ����ȸ - ��(�ջ��̷� ��)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ���ΰ� �ǻ� �����(list)
	 * @throws Exception
	 */
	public ActionForward compAdupHistDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipTargetAction.compAdupHistDetail"); 
		FTCAcipCompanyTargetService service = new FTCAcipCompanyTargetService();
		
		Map para = getParamMapForPage(request, "", 0);
		
		/*�ǻ��̷�*/
		if(para.get("crym")!=null){
			Collection listAdupHistDetail = service.compAdupHistDetail(para);
			request.setAttribute("compAdupHistDetail", listAdupHistDetail);
		}
		return mapping.findForward("aduphistdetail");
		
	}

	/**
	 * ���ΰ� ����ȸ - ��(�����̷� ��)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ���ΰ� �ǻ� �����(list)
	 * @throws Exception
	 */
	public ActionForward compReptHistDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipTargetAction.compReptHistDetail"); 
		FTCAcipCompanyTargetService service = new FTCAcipCompanyTargetService();		
		Map para = getParamMapForPage(request, "", 0);
		
		/*�ǻ��̷�*/
		if(para.get("crym")!=null){
			Collection listReptHistDetail = service.compReptHistDetail(para);
			request.setAttribute("compReptHistDetail", listReptHistDetail);
		}	
		return mapping.findForward("aduphistdetail");
		
	}
	

	/**
	 * FATCAȮ�μ� �����û �̷� ��ȸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return FATCAȮ�μ� �����û �̷�(list)
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
		//���±ݾױ�������
		request.setAttribute("amtSctCombo",codeService.getCodeList(getStrParam(request,"crym",""), "114"));		

		//SMS�߼�����
		request.setAttribute("smsSendCombo",codeService.getCodeList(getStrParam(request,"crym",""), "130"));

		//��������
		request.setAttribute("currentDate",FTCDateUtil.getToday());
				
		if(!"load".equals(this.getStrParam(request, "openType", ""))){	
			Collection list = service.confirmRequestHist(para);
			request.setAttribute("list", list);
		}

		//���� ����
		request.setAttribute("selCustNm", this.getCustNm(para));
		return mapping.findForward("confirmrequesthist");
	}
	

	/**
	 * FATCAȮ�μ� �����û �̷� ��ȸ ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return FATCAȮ�μ� �����û �̷� ��ȸ ���� �ٿ�
	 * @throws Exception
	 */
	public ActionForward confirmExcelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("AcipTargetAction.confirmRequestHist"); 

		FTCAcipCompanyTargetService service = new FTCAcipCompanyTargetService();
		
		Map para = getParamMapForPage(request, "grid", FTCConstants.ExcelSize);
			
		List list = service.confirmRequestHist(para);
		
		//��� ���� 
        String fileName = "SMS";
        String[] headerName = {"���س��","�߼�����","�߼�����","����ȣ","����","��ȭ��ȣ","�߼۳���"};
        String[] columnName = {"crym", "smsFwDge","fwDt","csno","custNm","smsNtiMpno","smsMsgCn"};
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}
	
}
