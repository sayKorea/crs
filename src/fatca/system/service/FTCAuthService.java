package fatca.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fatca.common.base.FTCBaseService;
import fatca.common.util.FTCEmployeeValue;
import fatca.common.util.FTCStringUtil;



/**
 * @File Name    : AcipReadyAction.java
 * @Package Name : fatca.system.service
 * @author       : 
 * @Description  : ���� ����
 * @History      : 
 */
public class FTCAuthService extends FTCBaseService{

	/**
	  * ���� ��� ��ȸ
	  * @param para �Է�����
	  * @return ���� ��� ���� ��� (list)
	  * @throws Exception
	  */
	public List listUsrAuth(Map para) throws Exception {
		
		return sqlMap.queryForList("system.selectUsrAuthList", para);
	}
	

	/**
	  * ���� ���� ���� ��� ��ȸ
	  * @param para �Է�����
	  * @return ���� ���� ���� ��� ��ȸ(list)
	  * @throws Exception
	  */
	public List listNoAuthEmp(Map para) throws Exception {
		
		return sqlMap.queryForList("system.selectNoAuthEmpList", para);
	}

	 /**
	   * ���� ���
	   * 
	   * @param para �Է����� 
	   * @throws Exception
	   */
	public void registerUsrAuth(Map para) throws Exception {
		
		try{
			sqlMap.startTransaction();
								
			List<String> empList = FTCStringUtil.getTokenList((String)para.get("empArr"), "|");			
			for(String userEnob : empList){				
				
				para.put("userEnob", userEnob);
				sqlMap.insert("system.insertUsrAuth", para);
				
				//���ѷα� ��� - ����� ã�Ƽ� �α׿�.
				para.put("authChRsc", "02");
				sqlMap.insert("system.insertAuthLog", para);
			}	
			
			sqlMap.commitTransaction();
		}catch (Exception e){
			throw e;
		}finally{
			sqlMap.endTransaction();
		}
		FTCEmployeeValue.refresh();				
	}

	 /**
	   * ���� ����
	   * 
	   * @param para �Է����� 
	   * @throws Exception
	   */
	public void removeUsrAuth(Map para) throws Exception {
		
		try{
			sqlMap.startTransaction();
								
			List<String> empList = FTCStringUtil.getTokenList((String)para.get("empArr"), "|");			
			for(String userEnob : empList){				
								
				para.put("userEnob", userEnob);
				
				//������ ������ �������� ����
				if(!userEnob.equals((String)para.get("enob"))){
						
					//���ѷα� ��� - ������ ã�Ƽ� �α׿�.
					para.put("authChRsc", "12");
					sqlMap.insert("system.insertAuthLog", para);
									
					sqlMap.insert("system.deleteUsrAuth", para);
				}	
			}	
			
			sqlMap.commitTransaction();
		}catch (Exception e){
			throw e;
		}finally{
			sqlMap.endTransaction();
		}
		FTCEmployeeValue.refresh();		
	}


	 /**
	   * �޴� ���� ��� ��ȸ
	   * 
	   * @param para �Է����� 
	   * @return �޴� ���� ��� 
	   * @throws Exception
	   */
	public List listMenuAuth(Map para) throws Exception {
		
		return sqlMap.queryForList("system.selectMnuAuthList", para);
	}
	
	 /**
	   * �޴� ���� ����
	   * 
	   * @param para �Է����� 
	   * @throws Exception
	   */
	public void saveMenuAuth(Map para) throws Exception {
		
		try{
			sqlMap.startTransaction();			
			String[] arrmnuId = (String[])para.get("mnuIdArr");
			String[] arrUseYn = (String[]) para.get("useYnArr");
			String[] arrBefUseYn = (String[]) para.get("befUseYnArr");
			
			for(int i=0;i<arrmnuId.length;i++){
				String strMnuId = arrmnuId[i];
				String strUseYn = arrUseYn[i];
				String strBefUseYn = arrBefUseYn[i];
				
				Map map = new HashMap();
				
				if(!strUseYn.equals(strBefUseYn)){
					map.put("mnuId", strMnuId);
					map.put("useYn", strUseYn);	
					map.put("befUseYn", strBefUseYn);
					map.put("fatcaAuthCd", para.get("fatcaAuthCd"));	
					map.put("enob", para.get("enob"));
					map.put("brno", para.get("brno"));

					sqlMap.update("system.updateMenuAuth", map);		
					sqlMap.insert("system.insertMenuAuthHis", map);		
				}			
			}
			
			
			sqlMap.commitTransaction();
			
		}catch (Exception e){
			throw e;
		}finally{
			sqlMap.endTransaction();
		}
				
	}

	/**
	   * �ŷ� �α�
	   * 
	   * @param para �Է����� 
	   * @throws Exception
	   */
	public void saveTrnsLog(Map para) throws Exception {
		
		try{
			sqlMap.startTransaction();
											
			if(para.get("csno")!=null && !"".equals((String)para.get("csno"))){
				if(para.get("custSeq")==null || "".equals((String)para.get("custSeq"))) {
					para.put("custSeq", "0000");
				}
				sqlMap.insert("system.insertTrnsLogWithCust", para);
			}else {
				sqlMap.insert("system.insertTrnsLog", para);
			}
			
			sqlMap.commitTransaction();
		}catch (Exception e){
			throw e;
		}finally{
			sqlMap.endTransaction();
		}
				
	}

	/**
	   * �ش� ���س�� ��������
	   * 
	   * @param crym �Է����� 
	   * @return �ǻ� �Ϸ� ��� 
	   * @throws Exception
	   */
	public String isMainComplete(String crym) throws Exception {
		
		return (String)sqlMap.queryForObject("system.selectClsnYn", crym);
	}
	
	
}
