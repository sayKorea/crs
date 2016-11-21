package fatca.acipresult.action;

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

import fatca.acipresult.service.FTCAcipResultService;
import fatca.common.FTCConstants;
import fatca.common.base.FTCBaseAction;
import fatca.common.decorator.FTCTableFormat;
import fatca.common.util.FTCDbMap;
import fatca.system.info.FTCUserInfo;
import fatca.system.service.FTCCodeService;

/**
 * @File Name    : AcipResultAction.java
 * @Package Name : fatca.acipresult.action
 * @author       : 
 * @Description  : �ǻ� ��� ����
 * @History      : 
 */
public class FTCAcipResultAction extends FTCBaseAction {

	private Log	log	= LogFactory.getLog(this.getClass());


	/**
	 * ���� �ǻ� ��� ���(��ȸ)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ���� �ǻ� ��� ���(list)
	 * @throws Exception
	 */
	public ActionForward document(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("AcipResultAction.question"); 

		FTCAcipResultService service = new FTCAcipResultService();

		Map para = getParamMapForPage(request, "", 0);
	
		//combo
		FTCCodeService codeService = new FTCCodeService();
		request.setAttribute("crymCombo",codeService.getCombo(para, "crym"));

		if(!"load".equals(this.getStrParam(request, "openType", ""))){
			
			FTCDbMap info = service.selectDocumentInfo(para);
			request.setAttribute("info", info);
			
			//�����ǻ���
			request.setAttribute("docAcipCombo",codeService.getCodeList(getStrParam(request,"crym",""), "124"));
			
			/*���� �ǻ� ��*/
			Collection docList = service.documentInfoList(para);
			request.setAttribute("docList", docList);
		}		

		//���� ����
		request.setAttribute("selCustNm", this.getCustNm(para));
		return mapping.findForward("document");
	}
	
	/**
	 * ���� �ǻ� ��� ���(����)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ���� �ǻ� ��� ���(save)
	 * @throws Exception
	 */
	public ActionForward saveDoc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		log.debug("Start AcipResultAction.saveDoc");

		FTCUserInfo userInfo = (FTCUserInfo)(request.getSession().getAttribute(FTCConstants.USER_KEY));
		FTCAcipResultService service = new FTCAcipResultService();
		
		Map para = getParamMapForPage(request, "", 0);
		
		para.put("enob", userInfo.getUserEnob());
		para.put("brno", userInfo.getSlsBrno());


		service.insertDocInfo(para);

		return this.document(mapping, form, request, response);
	}
	
	/**
	 * �� ���� ������ ���� ���(��ȸ)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return �� ���� ������ ���� ���(list)
	 * @throws Exception
	 */
	public ActionForward question(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipResultAction.question"); 

		
		FTCAcipResultService service = new FTCAcipResultService();
		
		Map para = getParamMapForPage(request, "", 0);
	
		//combo
		FTCCodeService codeService = new FTCCodeService();
		request.setAttribute("crymCombo",codeService.getCombo(para, "crym"));
				
		if(!"load".equals(this.getStrParam(request, "openType", ""))){
			FTCDbMap info = service.selectQuestionInfo(para);
			request.setAttribute("info", info);
		}

		//���� ����
		request.setAttribute("selCustNm", this.getCustNm(para));
		return mapping.findForward("question");
	}


	/**
	 * �� ���� ������ ���� ���(����)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return �� ���� ������ ���(save)
	 * @throws Exception
	 */
	public ActionForward saveQues(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipResultAction.saveQues"); 

		FTCUserInfo userInfo = (FTCUserInfo)(request.getSession().getAttribute(FTCConstants.USER_KEY));
		FTCAcipResultService service = new FTCAcipResultService();
		
		Map para = getParamMapForPage(request, "", 0);
	
		para.put("enob", userInfo.getUserEnob());
		para.put("brno", userInfo.getSlsBrno());
		
		service.insertResultInfo(para);
		
		return this.question(mapping, form, request, response);
	}

	/**
	 * ���� �ǻ��� ��� ��� ��ȸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ���ΰ� �ǻ� �����(list)
	 * @throws Exception
	 */
	public ActionForward personalResult(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipResultAction.personalResult"); 

		
		FTCAcipResultService service = new FTCAcipResultService();		
		FTCTableFormat tf = new FTCTableFormat();
		Map para = getParamMapForPage(request, "grid", FTCConstants.DefaultTableRow);
			
		//combo
		FTCCodeService codeService = new FTCCodeService();
		request.setAttribute("crymCombo",codeService.getCombo(para, "crym"));
		//���±ݾױ�������
		//request.setAttribute("amtSctCombo",codeService.getCodeList(getStrParam(request,"crym",""), "114"));
		request.setAttribute("amtSctCombo",codeService.getBetweenCodeList(getStrParam(request,"crym",""), "114", "11","14"));
		
		//�ǻ�ϷῩ��(?YN����?)
		request.setAttribute("ynCombo",codeService.getCodeList(getStrParam(request,"crym",""), "802"));
		//����ǻ���
		request.setAttribute("czAcipCombo",codeService.getCodeList(getStrParam(request,"crym",""), "116"));
		//�����ǻ���
		request.setAttribute("docAcipCombo",codeService.getCodeList(getStrParam(request,"crym",""), "117"));
		//FATCAȮ�μ��ǻ���
		request.setAttribute("cfrrptAcipCombo",codeService.getCodeList(getStrParam(request,"crym",""), "119"));
		//�����������
		request.setAttribute("iqrCombo",codeService.getCodeList(getStrParam(request,"crym",""), "118"));
		//������
		request.setAttribute("reptTrgetCombo",codeService.getCodeList(getStrParam(request,"crym",""), "120"));
		//FATCA������
		request.setAttribute("fatcaCustDvcdCombo",codeService.getBetweenCodeList(getStrParam(request,"crym",""), "115", "11", "13"));
		
		if(!"load".equals(this.getStrParam(request, "openType", ""))){
			List list = service.selectPersonalResultList(para);
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
		return mapping.findForward("gridpersonal");
	}


	/**
	 * ���� �ǻ��� ��� ��� ��ȸ ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ���� �ǻ��� ��� ��� ���� �ٿ�
	 * @throws Exception
	 */
	public ActionForward personalExcelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("AcipResultAction.personalExcelDown"); 

		
		FTCAcipResultService service = new FTCAcipResultService();	
		
		Map para = getParamMapForPage(request, "grid", FTCConstants.ExcelSize);
			
		List list = service.selectPersonalResultList(para);
		
		//��� ���� 
        String fileName = "PRES";
        String[] headerName = {"�������ڵ�","��������","����ȣ","����","�Ǹ��ȣ","���±ݾױ�������","����ǻ���",
        					   "�����ǻ���","FATCAȮ�μ��ǻ���","��������������ǰ��",
        					   "�����󿩺�","�ǻ�Ϸ���","FATCA�� ����"};
        String[] columnName = {"slsBrno", "slsBrnoNm","csno","custNm","rnno","fatcaAmtSctClacdNm","fatcaCzAcipRscdNm",
        					   "fatcaDocAcipRscdNm", "fatcaCfrrptAcipRscdNm","fatcaIqrRscdNm",
        					   "fatcaReptTrgetDvcdNm","fatcaAcipComptDt","fatcaCustDvcdNm"};
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}

	/**
	 * ���� �ǻ��� ��� ��� ��ȸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ���ΰ� �ǻ� �����(list)
	 * @throws Exception
	 */
	public ActionForward companyResult(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipResultAction.companyResult"); 

		
		FTCAcipResultService service = new FTCAcipResultService();
		FTCTableFormat tf = new FTCTableFormat();	
		Map para = getParamMapForPage(request, "grid", FTCConstants.DefaultTableRow);
			
		//combo
		FTCCodeService codeService = new FTCCodeService();
		request.setAttribute("crymCombo",codeService.getCombo(para, "crym"));
		//���±ݾױ�������
		request.setAttribute("amtSctCombo",codeService.getBetweenCodeList(getStrParam(request,"crym",""), "114", "21","24"));

		//�ǻ�ϷῩ��(?YN����?)
		request.setAttribute("ynCombo",codeService.getCodeList(getStrParam(request,"crym",""), "802"));
		//����ǻ���
		request.setAttribute("czAcipCombo",codeService.getCodeList(getStrParam(request,"crym",""), "116"));
		//�����ǻ���
		request.setAttribute("docAcipCombo",codeService.getCodeList(getStrParam(request,"crym",""), "117"));
		//FATCAȮ�μ��ǻ���
		request.setAttribute("cfrrptAcipCombo",codeService.getCodeList(getStrParam(request,"crym",""), "119"));
		//�����������
		request.setAttribute("iqrCombo",codeService.getCodeList(getStrParam(request,"crym",""), "118"));
		//������
		request.setAttribute("reptTrgetCombo",codeService.getCodeList(getStrParam(request,"crym",""), "120"));
		//FATCA������
		request.setAttribute("fatcaCustDvcdCombo",codeService.getBetweenCodeList(getStrParam(request,"crym",""), "115", "21", "29"));
		
		if(!"load".equals(this.getStrParam(request, "openType", ""))){
			List list = service.selectCompanyResultList(para);
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
		return mapping.findForward("gridcompany");
	}
	

	/**
	 * ���� �ǻ��� ��� ��� ��ȸ ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ���� �ǻ��� ��� ��� ���� �ٿ�
	 * @throws Exception
	 */
	public ActionForward companyExcelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("AcipResultAction.companyExcelDown"); 

		
		FTCAcipResultService service = new FTCAcipResultService();	
		
		Map para = getParamMapForPage(request, "grid", FTCConstants.ExcelSize);
			
		List list = service.selectCompanyResultList(para);
		
		//��� ���� 
        String fileName = "CRES";
        String[] headerName = {"�������ڵ�","��������","����ȣ","����","���ι�ȣ(����ڵ�Ϲ�ȣ)","���±ݾױ�������","����ǻ���",
        					   "FATCAȮ�μ��ǻ���","�����󿩺�","�ǻ�Ϸ���","FATCA�� ����"};
        String[] columnName = {"slsBrno", "slsBrnoNm","csno","custNm","rnno","fatcaAmtSctClacdNm","fatcaCzAcipRscdNm",
        					   "fatcaCfrrptAcipRscdNm","fatcaReptTrgetDvcdNm","fatcaAcipComptDt","fatcaCustDvcdNm"};
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}

	/**
	 * ���� �ǻ�Ϸ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return �ǻ�Ϸ� ��ȸ
	 * @throws Exception
	 */
	public ActionForward mainComplete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipTargetAction.mainComplete"); 

		FTCAcipResultService service = new FTCAcipResultService();
		
		Map para = getParamMapForPage(request, "grid", 13);

		//combo
		FTCCodeService codeService = new FTCCodeService();
		request.setAttribute("crymCombo",codeService.getCombo(para, "crym"));		
				
		/*�ٷ� ��ȸ�ϰ� ������
		String crym = this.getStrParam(request, "crym", "");
		if("".equals(crym)){
			para.put("crym", userInfo.getSelCrym());
			para.put("slsBrno", userInfo.getSelSlsBrno());
			para.put("slsBrnm", userInfo.getSelSlsBrnm());
		}	
		*/	
		if(!"load".equals(this.getStrParam(request, "openType", ""))){
			Map info = (Map)service.selectCompleteInfo(para);
			request.setAttribute("info", info);
			
			Collection list = service.selectCompleteList(para);
			request.setAttribute("list", list);
		}
		
		return mapping.findForward("maincomplete");
	}
	

	/**
	 * ���� �ǻ�Ϸ� ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ���� �ǻ�Ϸ� ���� �ٿ�
	 * @throws Exception
	 */
	public ActionForward mainExcelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("AcipResultAction.mainExcelDown"); 

		
		FTCAcipResultService service = new FTCAcipResultService();	
		
		Map para = getParamMapForPage(request, "grid", FTCConstants.ExcelSize);
			
		List list = service.selectCompleteList(para);
		
		//��� ���� 
        String fileName = "MBR";
        String[] headerName = {"������","�������","�ǻ�å����","��ü �Ѱ�","��ü �ǻ�Ϸ�","��ü �ǻ�̿Ϸ�","��ü ������",
        					   "���� �Ѱ�","���� �ǻ�Ϸ�","���� �ǻ�̿Ϸ�","���� ������","���� �Ѱ�","���� �ǻ�Ϸ�",
        					   "���� �ǻ�̿Ϸ�","���� ������" };
        String[] columnName = {"slsBrnoNm","slsStatus","fatcaAcipRpprEnobNm","totCust","totComp","totUncomp","totReport",
						       "comCust","comComp","comUncomp","comReport","perCust","perComp",
						       "perUncomp","perReport" };
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}
	
	/**
	 * ���� �ǻ�Ϸ�(����)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ���� �ǻ�Ϸ�(save)
	 * @throws Exception
	 */
	public ActionForward saveMainComplete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipTargetAction.saveMainComplete"); 

		FTCAcipResultService service = new FTCAcipResultService();
		
		
		Map para = getParamMapForPage(request, "", 0);
		
		FTCUserInfo userInfo = (FTCUserInfo)(request.getSession().getAttribute(FTCConstants.USER_KEY));
		//UserInfo userInfo = this.getUserInfo(request);
		
		para.put("enob", userInfo.getUserEnob());
		String dist = this.getStrParam(request, "dist", "");
		
		int rCnt = service.updateMainComplete(para);
		
		if(rCnt > 0){			
			if("Y".equals(dist)){
				request.setAttribute("msg","�ǻ�Ϸ� ó�� �Ǿ����ϴ�");
				request.setAttribute("isMainComplete", "Y");
			}else{
				request.setAttribute("msg","�ǻ�Ϸᰡ ��� �Ǿ����ϴ�");
				request.setAttribute("isMainComplete", "N");
			}	
		}	
		
		
		return this.mainComplete(mapping, form, request, response);
	}

	

	/**
	 * ������ �ǻ��� ��ȸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ������ �ǻ��� ��ȸ
	 * @throws Exception
	 */
	public ActionForward branchResultList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipTargetAction.branchResultList"); 

		FTCAcipResultService service = new FTCAcipResultService();
		FTCTableFormat tf = new FTCTableFormat();
		
		Map para = getParamMapForPage(request, "grid", FTCConstants.DefaultTableRow);

		//combo
		FTCCodeService codeService = new FTCCodeService();
		request.setAttribute("crymCombo",codeService.getCombo(para, "crym"));
		//���±ݾױ�������
		request.setAttribute("amtSctCombo",codeService.getCodeList(getStrParam(request,"crym",""), "114"));
		//�ǻ�ϷῩ��
		request.setAttribute("ynCombo",codeService.getCodeList(getStrParam(request,"crym",""), "802"));
		//FATCA������
		//request.setAttribute("fatcaTypeCombo",codeService.getCodeList(getStrParam(request,"crym",""), "109"));
		//������(����,����)
		request.setAttribute("factaTpcdCombo",codeService.getCodeList(getStrParam(request,"crym",""), "121"));
		//������
		request.setAttribute("reptTrgetCombo",codeService.getCodeList(getStrParam(request,"crym",""), "120"));		
				
		if(!"load".equals(this.getStrParam(request, "openType", ""))){
			Map info = (Map)service.selectBranchResultInfo(para);
			request.setAttribute("info", info);
			
			List list = service.selectBranchResultList(para);
			List nList = new ArrayList();
			for(int i=0; i<list.size(); i++){
				FTCDbMap nMap = (FTCDbMap)list.get(i);

				nMap.put("custNmMsk", tf.convertNameMskLen((String)nMap.get("custNm")));					
				
				nList.add(nMap);
			}
			request.setAttribute("list", nList);
		}
		//���� ����
		request.setAttribute("selCustNm", this.getCustNm(para));
		
		return mapping.findForward("branchresultlist");
	}

	/**
	 * ������ �ǻ�Ϸ� ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ������ �ǻ�Ϸ� ���� �ٿ�
	 * @throws Exception
	 */
	public ActionForward branchExcelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("AcipResultAction.branchExcelDown"); 

		
		FTCAcipResultService service = new FTCAcipResultService();	
		
		Map para = getParamMapForPage(request, "grid", FTCConstants.ExcelSize);
			
		List list = service.selectBranchResultList(para);
		
		//��� ���� 
        String fileName = "SBR";
        String[] headerName = {"����","�������ڵ�","��������","����ȣ","����","������ȣ","���±ݾױ�������",
        					   "�����󿩺�","�ǻ�Ϸ���","FACTA������",
        					   "�ǻ�å����","�ǻ�����","�����������"};
        String[] columnName = {"fatcaTpcdNm","slsBrno","slsBrnoNm","csno","custNm","rnno","fatcaAmtSctClacdNm",
						       "fatcaReptTrgetDvcdNm","fatcaAcipComptDt","fatcaCustDvcdNm","fatcaAcipRpprNm",
						       "fatcaAcipCgpNm","fatcaCustRelMngrNm",
						       "perUncomp","perReport" };
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}
	
}
