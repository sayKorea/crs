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
 * @Description  : 사용자 관리
 * @History      : 
 */
public class FTCUserService extends FTCBaseService{  
	
	 /**
	   * 로그인 확인
	   * 
	   * @param para 입력정보 
	   * @return 로그인 여부 
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
	   * 세션 등록용 사용자 정보및 권한 목록 조회
	   * 
	   * @param para 입력정보 
	   * @return 사용자별 권한 목록 
	   * @throws Exception
	   */
	public FTCUserInfo findAuthentication(Map para) throws Exception {
		
		//유저기본
		FTCUserInfo userInfo = (FTCUserInfo)sqlMap.queryForObject("system.setUserInfo", para);	
				
		//버튼 권한을 받아 맵으로 userInfo에 세팅하고 화면에서 세션에서 받아 비교 사용
		para.put("fatcaAuthCd",userInfo.getFatcaAuthCd());
		List<Map> mnuAuthList = sqlMap.queryForList("system.selectMnuAuthList", para);
		
		Map mnuAuthMap = new HashMap();
		for(Map map : mnuAuthList){
			mnuAuthMap.put(map.get("mnuId").toString(), map.get("useYn").toString());
		}
		
		//log.debug(mnuAuthMap.toString());
		userInfo.setMnuAuthMap(mnuAuthMap);
		
		//05고객용 콤보 
		//userInfo.setBranchComboRel(sqlMap.queryForList("system.selectBranchForRel", para));
			
		return userInfo;
	}
	
	//조회 고객명 찾기
	public String getCustNm(Map para) throws Exception {
		
		return (String)sqlMap.queryForObject("system.selectCustNm", para);
	}
	
	 /**
	   * 접속로그
	   * 
	   * @param para 입력정보 
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