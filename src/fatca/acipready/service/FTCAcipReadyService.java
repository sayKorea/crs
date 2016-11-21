package fatca.acipready.service;

import java.util.List;
import java.util.Map;

import fatca.common.base.FTCBaseService;
import fatca.common.util.FTCBranchValue;
import fatca.common.util.FTCDbMap;
import fatca.common.util.FTCStringUtil;


/**
 * @File Name    : AcipReadyService.java
 * @Package Name : fatca.acipready.service
 * @author       : 
 * @Description  : 실사 기본정보 관리
 * @History      : 
 */
public class FTCAcipReadyService extends FTCBaseService {

	/**
	  * @param para 입력정보
	  * @return 실사 기본정보 조회 
	  * @throws Exception
	  */
	public FTCDbMap selectBasicInfo(Map para) throws Exception {
		
		return (FTCDbMap)sqlMap.queryForObject("acipready.selectBasicInfo", para);
	}
	
	/**
	  * @param para	입력정보
	  * @throws Exception
	  */
	public void insertBasicInfo(Map para) throws Exception {

		try{
			sqlMap.startTransaction();
			
			//기준년월 조회후 없으면 insert 있으면 update
			FTCDbMap info = this.selectBasicInfo(para);
			
			if(info==null){
				sqlMap.insert("acipready.insertBasicInfo", para);
				
				//기준년월 신규시 공통코드 "00000000" 기준으로 재등록
				sqlMap.insert("acipready.insertCommonCodeByBasicInfo", para);
				sqlMap.insert("acipready.insertCommonCodeDetailByBasicInfo", para);
				
			}else{
				sqlMap.update("acipready.updateBasicInfo", para);
			}
			
			//기준정보 변경시 BranchValue refresh
			FTCBranchValue.refresh();
			
			sqlMap.commitTransaction();
		}catch (Exception e){
			throw e;
		}finally{
			sqlMap.endTransaction();
		}
				
	}


	/**
	  * 동일인 목록
	  * 
	  * @param para	입력정보
	  * @return 동일인 목록 
	  * @throws Exception
	  */
	public List selectSamePersonList(Map para) throws Exception {
		
		return sqlMap.queryForList("acipready.selectSamePersonList", para);
	}	
	
	/**
	  * @param para	입력정보
	  * @throws Exception
	  */
	public void insertSamePerson(Map para) throws Exception {
		
		try{
			sqlMap.startTransaction();
									
			para.put("csnoList", "0"+FTCStringUtil.replace(FTCStringUtil.replace((String)para.get("csnoArr"),"|",","),":",","));
			//선택된것중 min smpsnId. 이걸로 선택된 고객 모두 변경
			String smpsnId = (String)sqlMap.queryForObject("acipready.selectExistSmpsnId", para); 
			//없으면 새로 채번
			if(smpsnId==null){
				smpsnId = (String)sqlMap.queryForObject("acipready.selectSmpsnSeq", para); 
			}
			para.put("smpsnId", smpsnId);
			
			List<String> custList = FTCStringUtil.getTokenList((String)para.get("csnoArr"), "|");			
			for(String cust : custList){				
				
				String[] custInfo = cust.split(":");
				if(!smpsnId.equals(custInfo[0])){
					
					para.put("csno", custInfo[1]);
					if(!"".equals(custInfo[0])){ //원래 채번이 있던거면 min값으로 변경하기 위해 삭제후 insert
						sqlMap.delete("acipready.deleteSamePerson", para);
					}
					
					sqlMap.insert("acipready.insertSamePerson", para);
					//이력 추가
					para.put("rsgYn", "N");
					sqlMap.insert("acipready.insertSamePersonHistory", para);
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
	  * @param para	입력정보
	  * @throws Exception
	  */
	public void deleteSamePerson(Map para) throws Exception {

		try{
			sqlMap.startTransaction();
						
			List<String> custList = FTCStringUtil.getTokenList((String)para.get("csnoArr"), "|");		
			for(String cust : custList){
				String[] custInfo = cust.split(":");
				para.put("smpsnId", custInfo[0]);
				para.put("csno", custInfo[1]);
				//해지일자 업데이트로 제거에서 이력테이블 사용으로 삭제.
				sqlMap.delete("acipready.deleteSamePerson", para);
				//이력 추가
				para.put("rsgYn", "Y");
				sqlMap.insert("acipready.insertSamePersonHistory", para);
			}	
			
			sqlMap.commitTransaction();
		}catch (Exception e){
			throw e;
		}finally{
			sqlMap.endTransaction();
		}
				
	}

	
}
