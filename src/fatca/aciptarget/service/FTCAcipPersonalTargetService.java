package fatca.aciptarget.service;

import java.util.List;
import java.util.Map;

import fatca.common.base.FTCBaseService;
import fatca.common.util.FTCDbMap;


/**
 * @File Name    : AcipPersonalTargetService.java
 * @Package Name : fatca.aciptarget.service
 * @author       : 
 * @Description  : 개인 실사 대상자 조회
 * @History      : 
 */
public class FTCAcipPersonalTargetService extends FTCBaseService {

	/**
	  * 개인 고객 실사 대상자 조회 
	  * 
	  * @param para	입력정보
	  * @return 게시물 목록 
	  * @throws Exception
	  */
	public List selectPersonalList(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectPersonalList", para);
	}

	/**
	  * 개인고객 상세정보(기본정보)
	  * 
	  * @param para	입력정보
	  * @return 게시물 목록 
	  * @throws Exception
	  */
	public FTCDbMap selectPrsnlBasList(Map para) throws Exception {
		
		String contSeq = (String)sqlMap.queryForObject("aciptarget.selectPrsnlBasListYn", para);
		if("N".equals(contSeq)){
			//<실사시작일> 이후 [FATCA 개인확인서제출내역]에 "정상 제출"된 FATCA 확인서가 없을 경우.[FATCA 개인확인서 제출내역] 테이블사용
			return (FTCDbMap)sqlMap.queryForObject("aciptarget.selectPrsnlBasList", para);			
		}else{
			para.put("maxPrstDt", contSeq);
			//<실사시작일> 이후 [FATCA 개인확인서제출내역]에 "정상 제출"된 FATCA 확인서가 없을 경우.[FATCA 고객기본] 테이블사용
			return (FTCDbMap)sqlMap.queryForObject("aciptarget.selectPrsnlBasYList", para);			
		}
	}
	
	/**
	  * 개인고객 상세정보(계좌정보)
	  * 
	  * @param para	입력정보
	  * @return 게시물 목록 
	  * @throws Exception
	  */
	public List acntBasList(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectAcntBasList", para);
	}
	
	
	/**
	  * 개인고객 상세정보(실사이력)
	  * 
	  * @param para	입력정보
	  * @return 게시물 목록 
	  * @throws Exception
	  */
	public List acipHistList(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectHistList", para);
	}
	
	/**
	  * 개인고객 상세정보(FATCA 확인서 제출이력)
	  * 
	  * @param para	입력정보
	  * @return 게시물 목록 
	  * @throws Exception
	  */
	public List prstHistList(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectPrstHistList", para);
	}
	
	/**
	  * 개인고객 상세정보(합산이력)
	  * 
	  * @param para	입력정보
	  * @return 게시물 목록 
	  * @throws Exception
	  */
	public List adupHistList(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectAdupHistList", para);
	}
	
	/**
	  * 개인고객 상세정보(보고이력)
	  * 
	  * @param para	입력정보
	  * @return 게시물 목록 
	  * @throws Exception
	  */
	public List reptHistList(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectReptHistList", para);
	}

	/**
	  * 개인고객 상세정보(합산이력 상세)
	  * 
	  * @param para	입력정보
	  * @return 게시물 목록 
	  * @throws Exception
	  */
	public List adupHistDetail(Map para) throws Exception {
		
		List rtnList = sqlMap.queryForList("aciptarget.selectAdupHistDetailBySelf", para);
		String smpsnId = (String)sqlMap.queryForObject("aciptarget.selectSmpsnId", para);
		if(!"0".equals(smpsnId)){
			para.put("smpsnId", smpsnId);
			List tempList =  sqlMap.queryForList("aciptarget.selectAdupHistDetailBySmpsnId", para);
			rtnList.addAll(tempList);
		}	
		
		return rtnList;
	}

	/**
	  * 개인고객 상세정보(보고이력 상세)
	  * 
	  * @param para	입력정보
	  * @return 게시물 목록 
	  * @throws Exception
	  */
	public List reptHistDetail(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectReptHistDetail", para);
	}	
}
