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
 * @Description  : 실사 대상 관리
 * @History      : 
 */
public class FTCAcipResultAction extends FTCBaseAction {

	private Log	log	= LogFactory.getLog(this.getClass());


	/**
	 * 문서 실사 결과 등록(조회)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 문서 실사 결과 등록(list)
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
			
			//문서실사결과
			request.setAttribute("docAcipCombo",codeService.getCodeList(getStrParam(request,"crym",""), "124"));
			
			/*문서 실사 상세*/
			Collection docList = service.documentInfoList(para);
			request.setAttribute("docList", docList);
		}		

		//고객명 셋팅
		request.setAttribute("selCustNm", this.getCustNm(para));
		return mapping.findForward("document");
	}
	
	/**
	 * 문서 실사 결과 등록(저장)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 문서 실사 결과 등록(save)
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
	 * 고객 관계 관리자 질의 등록(조회)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 고객 관계 관리자 질의 등록(list)
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

		//고객명 셋팅
		request.setAttribute("selCustNm", this.getCustNm(para));
		return mapping.findForward("question");
	}


	/**
	 * 고객 관계 관리자 질의 등록(저장)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 고객 관계 관리자 등록(save)
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
	 * 개인 실사대상 결과 등록 조회
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 개인고객 실사 대상자(list)
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
		//계좌금액구간구분
		//request.setAttribute("amtSctCombo",codeService.getCodeList(getStrParam(request,"crym",""), "114"));
		request.setAttribute("amtSctCombo",codeService.getBetweenCodeList(getStrParam(request,"crym",""), "114", "11","14"));
		
		//실사완료여부(?YN인지?)
		request.setAttribute("ynCombo",codeService.getCodeList(getStrParam(request,"crym",""), "802"));
		//전산실사결과
		request.setAttribute("czAcipCombo",codeService.getCodeList(getStrParam(request,"crym",""), "116"));
		//문서실사결과
		request.setAttribute("docAcipCombo",codeService.getCodeList(getStrParam(request,"crym",""), "117"));
		//FATCA확인서실사결과
		request.setAttribute("cfrrptAcipCombo",codeService.getCodeList(getStrParam(request,"crym",""), "119"));
		//고객관계관리자
		request.setAttribute("iqrCombo",codeService.getCodeList(getStrParam(request,"crym",""), "118"));
		//보고대상
		request.setAttribute("reptTrgetCombo",codeService.getCodeList(getStrParam(request,"crym",""), "120"));
		//FATCA고객유형
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
		

		//고객명 셋팅
		request.setAttribute("selCustNm", this.getCustNm(para));
		return mapping.findForward("gridpersonal");
	}


	/**
	 * 개인 실사대상 결과 등록 조회 엑셀
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 개인 실사대상 결과 등록 엑셀 다운
	 * @throws Exception
	 */
	public ActionForward personalExcelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("AcipResultAction.personalExcelDown"); 

		
		FTCAcipResultService service = new FTCAcipResultService();	
		
		Map para = getParamMapForPage(request, "grid", FTCConstants.ExcelSize);
			
		List list = service.selectPersonalResultList(para);
		
		//헤더 생성 
        String fileName = "PRES";
        String[] headerName = {"영업점코드","영업점명","고객번호","고객명","실명번호","계좌금액구간구분","전산실사결과",
        					   "문서실사결과","FATCA확인서실사결과","고객관계관리자질의결과",
        					   "보고대상여부","실사완료일","FATCA고객 유형"};
        String[] columnName = {"slsBrno", "slsBrnoNm","csno","custNm","rnno","fatcaAmtSctClacdNm","fatcaCzAcipRscdNm",
        					   "fatcaDocAcipRscdNm", "fatcaCfrrptAcipRscdNm","fatcaIqrRscdNm",
        					   "fatcaReptTrgetDvcdNm","fatcaAcipComptDt","fatcaCustDvcdNm"};
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}

	/**
	 * 법인 실사대상 결과 등록 조회
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 법인고객 실사 대상자(list)
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
		//계좌금액구간구분
		request.setAttribute("amtSctCombo",codeService.getBetweenCodeList(getStrParam(request,"crym",""), "114", "21","24"));

		//실사완료여부(?YN인지?)
		request.setAttribute("ynCombo",codeService.getCodeList(getStrParam(request,"crym",""), "802"));
		//전산실사결과
		request.setAttribute("czAcipCombo",codeService.getCodeList(getStrParam(request,"crym",""), "116"));
		//문서실사결과
		request.setAttribute("docAcipCombo",codeService.getCodeList(getStrParam(request,"crym",""), "117"));
		//FATCA확인서실사결과
		request.setAttribute("cfrrptAcipCombo",codeService.getCodeList(getStrParam(request,"crym",""), "119"));
		//고객관계관리자
		request.setAttribute("iqrCombo",codeService.getCodeList(getStrParam(request,"crym",""), "118"));
		//보고대상
		request.setAttribute("reptTrgetCombo",codeService.getCodeList(getStrParam(request,"crym",""), "120"));
		//FATCA고객유형
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
		

		//고객명 셋팅
		request.setAttribute("selCustNm", this.getCustNm(para));
		return mapping.findForward("gridcompany");
	}
	

	/**
	 * 법인 실사대상 결과 등록 조회 엑셀
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 법인 실사대상 결과 등록 엑셀 다운
	 * @throws Exception
	 */
	public ActionForward companyExcelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("AcipResultAction.companyExcelDown"); 

		
		FTCAcipResultService service = new FTCAcipResultService();	
		
		Map para = getParamMapForPage(request, "grid", FTCConstants.ExcelSize);
			
		List list = service.selectCompanyResultList(para);
		
		//헤더 생성 
        String fileName = "CRES";
        String[] headerName = {"영업점코드","영업점명","고객번호","고객명","법인번호(사업자등록번호)","계좌금액구간구분","전산실사결과",
        					   "FATCA확인서실사결과","보고대상여부","실사완료일","FATCA고객 유형"};
        String[] columnName = {"slsBrno", "slsBrnoNm","csno","custNm","rnno","fatcaAmtSctClacdNm","fatcaCzAcipRscdNm",
        					   "fatcaCfrrptAcipRscdNm","fatcaReptTrgetDvcdNm","fatcaAcipComptDt","fatcaCustDvcdNm"};
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}

	/**
	 * 본점 실사완료
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 실사완료 조회
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
				
		/*바로 조회하고 싶으면
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
	 * 본점 실사완료 엑셀
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 본점 실사완료 엑셀 다운
	 * @throws Exception
	 */
	public ActionForward mainExcelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("AcipResultAction.mainExcelDown"); 

		
		FTCAcipResultService service = new FTCAcipResultService();	
		
		Map para = getParamMapForPage(request, "grid", FTCConstants.ExcelSize);
			
		List list = service.selectCompleteList(para);
		
		//헤더 생성 
        String fileName = "MBR";
        String[] headerName = {"영업점","진행상태","실사책임자","전체 총고객","전체 실사완료","전체 실사미완료","전체 보고대상",
        					   "법인 총고객","법인 실사완료","법인 실사미완료","법인 보고대상","개인 총고객","개인 실사완료",
        					   "개인 실사미완료","개인 보고대상" };
        String[] columnName = {"slsBrnoNm","slsStatus","fatcaAcipRpprEnobNm","totCust","totComp","totUncomp","totReport",
						       "comCust","comComp","comUncomp","comReport","perCust","perComp",
						       "perUncomp","perReport" };
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}
	
	/**
	 * 본점 실사완료(저장)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 본점 실사완료(save)
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
				request.setAttribute("msg","실사완료 처리 되었습니다");
				request.setAttribute("isMainComplete", "Y");
			}else{
				request.setAttribute("msg","실사완료가 취소 되었습니다");
				request.setAttribute("isMainComplete", "N");
			}	
		}	
		
		
		return this.mainComplete(mapping, form, request, response);
	}

	

	/**
	 * 영업점 실사결과 조회
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 영업점 실사결과 조회
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
		//계좌금액구간구분
		request.setAttribute("amtSctCombo",codeService.getCodeList(getStrParam(request,"crym",""), "114"));
		//실사완료여부
		request.setAttribute("ynCombo",codeService.getCodeList(getStrParam(request,"crym",""), "802"));
		//FATCA고객유형
		//request.setAttribute("fatcaTypeCombo",codeService.getCodeList(getStrParam(request,"crym",""), "109"));
		//고객구분(개인,법인)
		request.setAttribute("factaTpcdCombo",codeService.getCodeList(getStrParam(request,"crym",""), "121"));
		//보고대상
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
		//고객명 셋팅
		request.setAttribute("selCustNm", this.getCustNm(para));
		
		return mapping.findForward("branchresultlist");
	}

	/**
	 * 영업점 실사완료 엑셀
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 영업점 실사완료 엑셀 다운
	 * @throws Exception
	 */
	public ActionForward branchExcelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("AcipResultAction.branchExcelDown"); 

		
		FTCAcipResultService service = new FTCAcipResultService();	
		
		Map para = getParamMapForPage(request, "grid", FTCConstants.ExcelSize);
			
		List list = service.selectBranchResultList(para);
		
		//헤더 생성 
        String fileName = "SBR";
        String[] headerName = {"구분","영업점코드","영업점명","고객번호","고객명","고유번호","계좌금액구간구분",
        					   "보고대상여부","실사완료일","FACTA고객구분",
        					   "실사책임자","실사담당자","고객관계관리자"};
        String[] columnName = {"fatcaTpcdNm","slsBrno","slsBrnoNm","csno","custNm","rnno","fatcaAmtSctClacdNm",
						       "fatcaReptTrgetDvcdNm","fatcaAcipComptDt","fatcaCustDvcdNm","fatcaAcipRpprNm",
						       "fatcaAcipCgpNm","fatcaCustRelMngrNm",
						       "perUncomp","perReport" };
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}
	
}
