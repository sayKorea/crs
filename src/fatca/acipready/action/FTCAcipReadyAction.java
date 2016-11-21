package fatca.acipready.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fatca.acipready.service.FTCAcipReadyService;
import fatca.common.FTCConstants;
import fatca.common.base.FTCBaseAction;
import fatca.common.decorator.FTCTableFormat;
import fatca.common.util.FTCDateUtil;
import fatca.common.util.FTCDbMap;
import fatca.common.util.FTCStringUtil;
import fatca.system.info.FTCUserInfo;
import fatca.system.service.FTCCodeService;

/**
 * @File Name    : AcipReadyAction.java
 * @Package Name : fatca.acipready.action
 * @author       : 
 * @Description  : �ǻ� �⺻���� ����
 * @History      : 
 */
public class FTCAcipReadyAction extends FTCBaseAction {

	private Log	log	= LogFactory.getLog(this.getClass());

	/**
	 * �ǻ� �⺻���� ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return �ǻ� �⺻���� ��ȸ(map)
	 * @throws Exception
	 */
	public ActionForward modify(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipReadyAction.modify"); 

		FTCUserInfo userInfo = (FTCUserInfo)(request.getSession().getAttribute(FTCConstants.USER_KEY));
		FTCAcipReadyService service = new FTCAcipReadyService();
		
		Map para = getParamMapForPage(request, "", 0);
	
		//combo
		FTCCodeService codeService = new FTCCodeService();
		//���س�� �޺�
		request.setAttribute("crymCombo",codeService.getCombo(para, "crym"));
				
		if(!"load".equals(getStrParam(request, "openType", ""))){
			FTCDbMap info = service.selectBasicInfo(para);
			request.setAttribute("info", info);
			
			//�������� �Ǵ� ��ȸ�� �������
			if(para.get("crym")!=null){
				
				request.setAttribute("currentDate",FTCDateUtil.getDateString(info.getString("rgdt"),"yyyy-MM-dd"));
				request.setAttribute("cgpEnnm",info.getString("cgpEnnm"));
			}else{
				request.setAttribute("currentDate",FTCDateUtil.getToday("yyyy-MM-dd"));
				request.setAttribute("cgpEnnm",userInfo.getUserEnnm());
			}
		}
		
		return mapping.findForward("modify");
	}

	
	/**
	 * �ǻ� �⺻���� ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return �ǻ� �⺻���� ��ϰ��
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipReadyAction.save"); 

		FTCUserInfo userInfo = (FTCUserInfo)(request.getSession().getAttribute(FTCConstants.USER_KEY));
		FTCAcipReadyService service = new FTCAcipReadyService();
		
		Map para = getParamMapForPage(request, "", 0);
	
		//�Է°����� dash, comma ����
		Iterator ir = ((Set)para.keySet()).iterator();
		while(ir.hasNext()){
			String key = (String)ir.next();
			String value = (para.get(key)).toString();
			para.put(key, FTCStringUtil.removeCharacter(FTCStringUtil.removeCharacter(value, '-'),','));			
		}
		
		
		//�ǻ�������� �������� ���س�� ����
		para.put("crym", FTCStringUtil.subString(para.get("fatcaAcipCrdt").toString(), 0, 6));
		
		para.put("enob", userInfo.getUserEnob());
		para.put("brno", userInfo.getSlsBrno());
		
		service.insertBasicInfo(para);
		
		ActionForward result = new ActionForward();
        result.setPath("/acipReady.do?method=modify"); 
        result.setRedirect(true);
        return result;
	}


	/**
	 * ������ ��ȸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ������ ���(list)
	 * @throws Exception
	 */
	public ActionForward samePersonList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipTargetAction.samePersonList"); 

		FTCAcipReadyService service = new FTCAcipReadyService();
		FTCTableFormat tf = new FTCTableFormat();	
		
		Map para = getParamMapForPage(request, "", 0);

		//combo
		FTCCodeService codeService = new FTCCodeService();
		request.setAttribute("crymCombo",codeService.getCombo(para, "crym"));
		
		if(!"load".equals(this.getStrParam(request, "openType", ""))){
			List list = service.selectSamePersonList(para);
			List nList = new ArrayList();
			for(int i=0; i<list.size(); i++){
				FTCDbMap nMap = (FTCDbMap)list.get(i);

				nMap.put("rnnoMsk", tf.convertRnnoMsk((String)nMap.get("rnno")));
				nMap.put("custNmMsk", tf.convertNameMsk((String)nMap.get("custNm")));					
				nMap.put("rgdtFmt", tf.convertDateMsk((String)nMap.get("rgdt")));
				
				nList.add(nMap);
			}
			request.setAttribute("list", nList);
		}
		
		request.setAttribute("selCustNm", this.getCustNm(para));
		return mapping.findForward("samepersonlist");
	}

	/**
	 * ������ ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ������ ��ϰ��
	 * @throws Exception
	 */
	public ActionForward saveSamePerson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("AcipTargetAction.registSamePerson"); 

		FTCAcipReadyService service = new FTCAcipReadyService();
		
		Map para = getParamMapForPage(request, "", 0);

		//combo
		FTCCodeService codeService = new FTCCodeService();
		request.setAttribute("crymCombo",codeService.getCombo(para, "crym"));
		
		FTCUserInfo userInfo = (FTCUserInfo)(request.getSession().getAttribute(FTCConstants.USER_KEY));
		para.put("enob", userInfo.getUserEnob());
		para.put("brno", userInfo.getSlsBrno());
		
		String dist = this.getStrParam(request, "dist", "");
		if("reg".equals(dist)){
			service.insertSamePerson(para);
		}else if("del".equals(dist)){
			service.deleteSamePerson(para);
		}
		
		return this.samePersonList(mapping, form, request, response);
		
	}
	

	/**
	 * ������ ��� ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ������ ��� ���� �ٿ�
	 * @throws Exception
	 */
	public ActionForward samePersonExcelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FTCAcipReadyService service = new FTCAcipReadyService();

		log.debug("AcipTargetAction.registSamePerson"); 
		Map para = getParamMapForPage(request, "grid", FTCConstants.ExcelSize);
			
		List list = service.selectSamePersonList(para);
		
		//��� ���� 
        String fileName = "SAME";
        String[] headerName = {"FATCA�����νĺ���ȣ", "����ȣ","�Ǹ��ȣ","����","���������","�����ε������"};
        String[] columnName = {"smpsnId", "csno","rnno","custNm","regDtl","rgdt"};
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}
}
