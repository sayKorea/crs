package fatca.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fatca.common.base.FTCBaseService;
import fatca.system.info.FTCUserInfo;



/**
 * @File Name    : AuthAction.java
 * @Package Name : fatca.system.action
 * @author       : 
 * @Description  : ����� ����
 * @History      : 
 */
public class FTCUserService extends FTCBaseService{  
	
	 /**
	   * �α��� Ȯ��
	   * 
	   * @param para �Է����� 
	   * @return �α��� ���� 
	   * @throws Exception
	   */
	public boolean checkLogin(Map para) throws Exception {
		
		String info = (String)sqlMap.queryForObject("system.checkLogin", para);
		
		if (info != null && !"".equals(info)) {
			return true;
		}
		return false;
	}
	

	 /**
	   * ���� ��Ͽ� ����� ������ ���� ��� ��ȸ
	   * 
	   * @param para �Է����� 
	   * @return ����ں� ���� ��� 
	   * @throws Exception
	   */
	public FTCUserInfo findAuthentication(Map para) throws Exception {
		
		//�����⺻
		FTCUserInfo userInfo = (FTCUserInfo)sqlMap.queryForObject("system.setUserInfo", para);	
				
		//��ư ������ �޾� ������ userInfo�� �����ϰ� ȭ�鿡�� ���ǿ��� �޾� �� ���
		para.put("fatcaAuthCd",userInfo.getFatcaAuthCd());
		List<Map> mnuAuthList = sqlMap.queryForList("system.selectMnuAuthList", para);
		
		Map mnuAuthMap = new HashMap();
		for(Map map : mnuAuthList){
			mnuAuthMap.put(map.get("mnuId").toString(), map.get("useYn").toString());
		}
		
		//log.debug(mnuAuthMap.toString());
		userInfo.setMnuAuthMap(mnuAuthMap);
		
		//05���� �޺� 
		//userInfo.setBranchComboRel(sqlMap.queryForList("system.selectBranchForRel", para));
			
		return userInfo;
	}
	
	//��ȸ ���� ã��
	public String getCustNm(Map para) throws Exception {
		
		return (String)sqlMap.queryForObject("system.selectCustNm", para);
	}
	
	 /**
	   * ���ӷα�
	   * 
	   * @param para �Է����� 
	   * @throws Exception
	   */
	public void addCnntLog(Map para) throws Exception {
		
		try{
			sqlMap.startTransaction();
			
			sqlMap.insert("system.insertCnttLog", para);
			
			sqlMap.commitTransaction();
		}catch (Exception e){
			throw e;
		}finally{
			sqlMap.endTransaction();
		}
		
	}
		
	
}