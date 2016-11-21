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
 * @Description  : 권한 관리
 * @History      : 
 */
public class FTCAuthService extends FTCBaseService{

	/**
	  * 권한 목록 조회
	  * @param para 입력정보
	  * @return 권한 등록 직원 목록 (list)
	  * @throws Exception
	  */
	public List listUsrAuth(Map para) throws Exception {
		
		return sqlMap.queryForList("system.selectUsrAuthList", para);
	}
	

	/**
	  * 권한 없는 직원 목록 조회
	  * @param para 입력정보
	  * @return 권한 없는 유저 목록 조회(list)
	  * @throws Exception
	  */
	public List listNoAuthEmp(Map para) throws Exception {
		
		return sqlMap.queryForList("system.selectNoAuthEmpList", para);
	}

	 /**
	   * 권한 등록
	   * 
	   * @param para 입력정보 
	   * @throws Exception
	   */
	public void registerUsrAuth(Map para) throws Exception {
		
		try{
			sqlMap.startTransaction();
								
			List<String> empList = FTCStringUtil.getTokenList((String)para.get("empArr"), "|");			
			for(String userEnob : empList){				
				
				para.put("userEnob", userEnob);
				sqlMap.insert("system.insertUsrAuth", para);
				
				//권한로그 등록 - 등록후 찾아서 로그에.
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
	   * 권한 삭제
	   * 
	   * @param para 입력정보 
	   * @throws Exception
	   */
	public void removeUsrAuth(Map para) throws Exception {
		
		try{
			sqlMap.startTransaction();
								
			List<String> empList = FTCStringUtil.getTokenList((String)para.get("empArr"), "|");			
			for(String userEnob : empList){				
								
				para.put("userEnob", userEnob);
				
				//본인은 경고없이 삭제에서 제외
				if(!userEnob.equals((String)para.get("enob"))){
						
					//권한로그 등록 - 삭제전 찾아서 로그에.
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
	   * 메뉴 권한 목록 조회
	   * 
	   * @param para 입력정보 
	   * @return 메뉴 권한 목록 
	   * @throws Exception
	   */
	public List listMenuAuth(Map para) throws Exception {
		
		return sqlMap.queryForList("system.selectMnuAuthList", para);
	}
	
	 /**
	   * 메뉴 권한 저장
	   * 
	   * @param para 입력정보 
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
	   * 거래 로그
	   * 
	   * @param para 입력정보 
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
	   * 해당 기준년월 마감여부
	   * 
	   * @param crym 입력정보 
	   * @return 실사 완료 목록 
	   * @throws Exception
	   */
	public String isMainComplete(String crym) throws Exception {
		
		return (String)sqlMap.queryForObject("system.selectClsnYn", crym);
	}
	
	
}
