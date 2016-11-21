package fatca.system.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fatca.common.FTCConstants;
import fatca.common.base.FTCBaseAction;
import fatca.common.util.FTCStringUtil;
import fatca.system.info.FTCUserInfo;
import fatca.system.service.FTCCodeService;

/**
 * @File Name    : AuthAction.java
 * @Package Name : fatca.system.action
 * @author       : 
 * @Description  : �ڵ� ����
 * @History      : 
 */
public class FTCCodeAction extends FTCBaseAction {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * �ڵ� ��ȸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return �ڵ� ��ȸ ���(list)
	 * @throws Exception
	 */
	public ActionForward listCode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FTCCodeService codeService = new FTCCodeService();
	
		log.debug("Start CodeAction.listCode");
		Map para = getParamMapForPage(request, "codeGrid", FTCConstants.DefaultTableRow);
		
		
		if(!"load".equals(getStrParam(request, "openType", ""))){
			Collection list = codeService.selectCodeList(para);
			request.setAttribute("list", list);
		}
		
		//combo
		request.setAttribute("crymCombo",codeService.getCombo(para, "crym"));

		return mapping.findForward("list");
		
	}
	
	/**
	 * �ڵ� ���� �ٿ�ε�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return �ڵ� ��� ���� �ٿ�
	 * @throws Exception
	 */
	public ActionForward excelDown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FTCCodeService codeService = new FTCCodeService();
	
		log.debug("Start CodeAction.excelDown");
		Map para = getParamMapForPage(request, "codeGrid", FTCConstants.ExcelSize);
			
		List list = codeService.selectCodeList(para);
		
		//��� ���� 
        String fileName = "CODE";
        String[] headerName = {"���س⵵", "�ڵ�ĺ��ھ��̵�","�ڵ�ĺ��ڸ�","��ȿ��","��ȿ����","������ȿ��","��뿩��"};
        String[] columnName = {"crym", "cdIdtfId","cdIdtfKnm","vldVal","vldValNm","upperVldVal","useYn"};
        
		genExcel(request, list, fileName, headerName, columnName);
				
		return mapping.findForward("exceldown");
		
	}


	/**
	 * �ٿ�ε� �� ���� ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws Exception
	 */
	public ActionForward deleteExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		log.debug("Start CodeAction.deleteExcel");
		Map para = getParamMapForPage(request, "", 0);
		
		String filePath = FTCConstants.USER_DOWNLOAD;
		filePath = FTCStringUtil.replace(filePath, "\\", "/");
		File delFile = new File(filePath+para.get("fname"));
		if(delFile.isFile()){ //�ٿ���� ���� �����
			delFile.delete();
		}
				
		return null;
		
	}


	/**
	 * �ڵ� ���� ���ε� ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return �ڵ� ���
	 * @throws Exception
	 */
	public ActionForward excelUpload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FTCCodeService codeService = new FTCCodeService();
		FTCUserInfo userInfo = this.getUserInfo(request);
		
		log.debug("Start CodeAction.excelUpload");
		Map para = getParamMapForPage(request, "", 0);
		
		request.setAttribute("crymCombo",codeService.getCombo(para, "crym"));
			
		if(!"load".equals(this.getStrParam(request, "openType", ""))){
			
			para.put("enob", userInfo.getUserEnob());
			para.put("brno", userInfo.getSlsBrno());
			
			//��������		
			String filePath = FTCConstants.USER_UPLOAD;			
			filePath = FTCStringUtil.replace(filePath, "\\", "/");
			
			File file = null;
			File file2 = new File(filePath);
			if(!file2.isDirectory()){ //upload folder ������ �����
				file2.mkdir();
			}
			
			DiskFileUpload fileUpload = new DiskFileUpload();
			fileUpload.setRepositoryPath(filePath);
			fileUpload.setSizeMax(100*1024*1024);
			fileUpload.setSizeThreshold(1024*50);

			List items = fileUpload.parseRequest(request);
			//List fileNames = new ArrayList();
			//List fileSizes = new ArrayList();

			Iterator iterator = items.iterator();
			while (iterator.hasNext()) {

				FileItem item = (FileItem) iterator.next();

				if(item.isFormField()){
					String name = item.getFieldName(); //�Ķ���� �̸�
					String value = item.getString(); //�Ķ���� ��
					para.put(name, value);
					log.debug("para "+name+"="+value);
				}else if (!item.isFormField()) {
					if (item.getSize() > 0) {
						//���� �̸��� �����´�      
						String fileName = item.getName().substring(item.getName().lastIndexOf("\\")+1);
						//fileName = DateUtil.getToday("yyyyMMddHHmmss")+"_"+fileName;
						file = new File(filePath+fileName);	                
						//fileNames.add(fileName);	
						//fileSizes.add(Long.toString(item.getSize()));

						item.write(file);
						log.debug("file "+file.getCanonicalPath());
						para = this.parsingFile(file,para,userInfo);
						
					}
				}
			}
			
			int rCnt = 0;
			if("Y".equals((String)para.get("isEmpty"))){
				request.setAttribute("rtnMsg", "���� ������ ������ ���ų� �� ���� ���ԵǾ� �ֽ��ϴ�.");
			}else{
				rCnt = codeService.uploadCommonCode(para);
				//���� �����
				if(file!=null && file.isFile()){
					file.delete();
				}
			}

			log.debug("rCnt "+rCnt);
			request.setAttribute("rtnMsg", "���� ���Ͽ� �ִ� "+rCnt+"���� Data�� ó���Ǿ����ϴ�. ");
			
		}
				
		return mapping.findForward("excelupload");
		
	}

	private Map parsingFile(File file, Map para, FTCUserInfo userInfo) throws Exception{

		Workbook workbook = null;
		Sheet sheet = null;

		try{
			//excel handling    
			workbook = Workbook.getWorkbook(file);
			sheet = null;
			if( workbook != null) {
				sheet = workbook.getSheet(0);     
				if( sheet != null) {
					int nRowStartIndex = 1;
					int nRowEndIndex  = sheet.getRows();
					int nColumnEndIndex = sheet.getRow(0).length-1;
					List tempList = new ArrayList();
					int cnt =0;		  
					// ���� ���� �Ľ�.		
					for( int nRow = nRowStartIndex; nRow < nRowEndIndex ; nRow++ ){
						Map basicPara = new HashMap();
						//log.debug("nRow="+nRow);
						for(int k=0; k<=nColumnEndIndex; k++){
							
							//upper�� null�̷��� �ϴ� ����
							//if(sheet.getCell(k, nRow).getContents()==null || "".equals(sheet.getCell(k, nRow).getContents())){
							//	para.put("isEmpty", "Y");
							//	log.debug( "Column is null!!" );
							//}else{
								basicPara.put("data"+k, sheet.getCell(k, nRow).getContents());
								
								basicPara.put("crym", (String)para.get("crym"));
								basicPara.put("enob", userInfo.getUserEnob());
								basicPara.put("brno", userInfo.getSlsBrno());
							//}
						}
						tempList.add(cnt++,basicPara);						 	
					}	

					para.put("colCnt", new Integer(nColumnEndIndex));
					para.put("dataList", tempList);
					
					if(cnt==0){
						para.put("isEmpty", "Y");
					}
				}else {
					para.put("isEmpty", "Y");
					log.debug( "Sheet is null!!" );
				}
			}else {
				para.put("isEmpty", "Y");
				log.debug( "WorkBook is null!!" );
			}	        

			para.put("isEmpty", "N");
			log.debug( "parsing complete!!" );
			
		}catch(Exception e){
			e.getMessage();
		}finally{
			if( workbook != null){
				workbook.close();
			}
		}
		return para;
	}

	/**
	 * �� ��ȸ(����)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return �� ��ȸ ���(list)
	 * @throws Exception
	 */
	public ActionForward searchPopCust(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FTCCodeService codeService = new FTCCodeService();
	
		log.debug("Start CodeAction.searchPopCust");
		Map para = getParamMapForPage(request, "grid", FTCConstants.DefaultTableRow);
		
		//combo
		//request.setAttribute("crymCombo",codeService.getCombo(para, "crym"));

		if(!"y".equals(getStrParam(request, "init", ""))){
			Collection list = codeService.getCustList(para);
			request.setAttribute("list", list);
		}	
		
		return mapping.findForward("searchpopcust");
		
	}

	/**
	 * �� ��ȸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return �� ��ȸ ���(list)
	 * @throws Exception
	 */
	public ActionForward searchPopCopr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FTCCodeService codeService = new FTCCodeService();
	
		log.debug("Start CodeAction.searchPopCopr");
		Map para = getParamMapForPage(request, "grid", FTCConstants.DefaultTableRow);
		
		if(!"y".equals(getStrParam(request, "init", "")) || !"".equals(getStrParam(request, "csno", ""))){
			Collection list = codeService.getCoprList(para);
			request.setAttribute("list", list);
		}	
		
		return mapping.findForward("searchpopcopr");
		
	}

	/**
	 * ������ ��ȸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ������ ��ȸ ���(list)
	 * @throws Exception
	 */
	public ActionForward searchPopSls(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FTCCodeService codeService = new FTCCodeService();
	
		log.debug("Start CodeAction.searchPopSls");
		Map para = getParamMapForPage(request, "grid", FTCConstants.DefaultTableRow);
		
		if(!"y".equals(getStrParam(request, "init", ""))){
			Collection list = codeService.getBranchList(para);			 
			request.setAttribute("list", list);
		}	
		
		return mapping.findForward("searchpopsls");
		
	}

	/**
	 * ���� ��ȸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ���� ��ȸ ���(list)
	 * @throws Exception
	 */
	public ActionForward searchPopEmp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FTCCodeService codeService = new FTCCodeService();
	
		log.debug("Start CodeAction.searchPopEmp");
		Map para = getParamMapForPage(request, "grid", FTCConstants.DefaultTableRow);
		
		//if(!"y".equals(getStrParam(request, "init", ""))){
			Collection list = codeService.getEmpList(para);
			request.setAttribute("list", list);
		//}	
		
		return mapping.findForward("searchpopemp");
		
	}
	
	/**
	 * ����� ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ����� ������
	 * @throws Exception
	 */
	public ActionForward saveCharge(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FTCCodeService codeService = new FTCCodeService();
	
		log.debug("Start CodeAction.saveCharge");
		Map para = getParamMapForPage(request, "", 0);
		
		FTCUserInfo userInfo = this.getUserInfo(request);
		para.put("enob", userInfo.getUserEnob());
		para.put("brno", userInfo.getSlsBrno());
		
		codeService.saveCharge(para);
		
		return null;
		
	}
	
	/**
	 * sql ó��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return sql ó�� ������
	 * @throws Exception
	 */
	public ActionForward execQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FTCCodeService codeService = new FTCCodeService();
	
		log.debug("Start CodeAction.execQuery");
		Map para = getParamMapForPage(request, "", 0);
		
		if(!"load".equals(getStrParam(request, "openType", ""))){
			
			List headerList = new ArrayList();		
			List list;
			
			//if(!StringUtil.getLeftTrim(getStrParam(request, "queryStr", "")).toUpperCase().startsWith("SELECT")) {
			if("I".equals(getStrParam(request, "dist", ""))) {
				codeService.execInsertQuery(para);
				Map rtnMap = new HashMap();
				
				rtnMap.put("hname", "result");
				headerList.add(rtnMap);
				list = new ArrayList();
				rtnMap.put("result", "OK");
				list.add(rtnMap);
			}else {
				list = (List)codeService.execSelectQuery(para);
				
				if(list!=null && list.size() > 0) {
					Iterator itr = ((Map)list.get(0)).keySet().iterator();
					while(itr.hasNext()) {
						Map rtnMap = new HashMap();
						
						rtnMap.put("hname", itr.next());
						//log.debug("rtnMap"+rtnMap.get("hname"));
						headerList.add(rtnMap);
					}
				}				
			}
			
			request.setAttribute("list", list);
			request.setAttribute("headerList", headerList);
		}
		
		return mapping.findForward("execquery");
	}
	
	
	/**
	 * �� ���� Ȯ��(����ȣ/�Ǹ��ȣ/���ι�ȣ/����ڹ�ȣ)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return �� ��
	 * @throws Exception
	 */
	public ActionForward checkCustCount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FTCCodeService codeService = new FTCCodeService();
	
		log.debug("Start CodeAction.checkCustCount");
		Map para = getParamMapForPage(request, "", 0);
		
		List rtnList = (List)codeService.checkCustCount(para);
		
		request.setAttribute("custCnt", rtnList.size());
		if(rtnList.size()>0){
			request.setAttribute("custNm", ((Map)rtnList.get(0)).get("custNm"));
			request.setAttribute("csnoReal", ((Map)rtnList.get(0)).get("csno"));
			request.setAttribute("custSeq", ((Map)rtnList.get(0)).get("custSeq"));
		}	
	
		return mapping.findForward("chkcust");
		
	}

	
}
