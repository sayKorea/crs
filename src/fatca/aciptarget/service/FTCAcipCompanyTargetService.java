package fatca.aciptarget.service;

import java.util.List;
import java.util.Map;

import fatca.common.base.FTCBaseService;
import fatca.common.util.FTCDbMap;


/**
 * @File Name    : AcipCompanyTargetService.java
 * @Package Name : fatca.aciptarget.service
 * @author       : 
 * @Description  : 법인 실사 대상자 조회
 * @History      : 
 */
public class FTCAcipCompanyTargetService extends FTCBaseService {

	/**
	  * 법인 고객 실사 대상자 조회 
	  * 
	  * @param para	입력정보
	  * @return 게시물 목록 
	  * @throws Exception
	  */
	public List selectCompanyList(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectCompanyList", para);
	}

	/**
	  * 법인고객 상세정보(기본정보)
	  * 
	  * @param para	입력정보
	  * @return 게시물 목록 
	  * @throws Exception
	  */
	public FTCDbMap selectCompBasList(Map para) throws Exception {
		
		String contSeq = (String)sqlMap.queryForObject("aciptarget.selectCompanyBasListYn", para);
		if("N".equals(contSeq)){
			//<실사시작일> 이후 [FATCA 개인확인서제출내역]에 "정상 제출"된 FATCA 확인서가 없을 경우.[FATCA 고객기본] 테이블사용
			return (FTCDbMap)sqlMap.queryForObject("aciptarget.selectCompBasList", para);			
		}else{
			//<실사시작일> 이후 [FATCA 개인확인서제출내역]에 "정상 제출"된 FATCA 확인서가 있을 경우.[FATCA 개인확인서 제출내역] 테이블사용
			return (FTCDbMap)sqlMap.queryForObject("aciptarget.selectCompBasYList", para);			
		}
	}
	
	/**
	  * 법인고객 상세정보(계좌정보)
	  * 
	  * @param para	입력정보
	  * @return 게시물 목록 
	  * @throws Exception
	  */
	public List acntBasList(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectAcntBasList", para);
	}
		
	/**
	  * 법인고객 상세정보(실사이력)
	  * 
	  * @param para	입력정보
	  * @return 게시물 목록 
	  * @throws Exception
	  */
	public List acipHistList(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectCompHistList", para);
	}
	
	/**
	  * 법인고객 상세정보(FATCA 확인서 제출이력)
	  * 
	  * @param para	입력정보
	  * @return 게시물 목록 
	  * @throws Exception
	  */
	public List prstHistList(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectCompPrstHistList", para);
	}
	
	/**
	  * 법인고객 상세정보(합산이력)
	  * 
	  * @param para	입력정보
	  * @return 게시물 목록 
	  * @throws Exception
	  */
	public List adupHistList(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectCompAdupHistList", para);
	}
	
	
	/**
	  * 법인고객 상세정보(보고이력)
	  * 
	  * @param para	입력정보
	  * @return 게시물 목록 
	  * @throws Exception
	  */
	public List reptHistList(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectCompReptHistList", para);
	}

	/**
	  * 법인고객 상세정보(합산이력 상세)
	  * 
	  * @param para	입력정보
	  * @return 게시물 목록 
	  * @throws Exception
	  */
	public List compAdupHistDetail(Map para) throws Exception {
		
		List rtnList = sqlMap.queryForList("aciptarget.selectCompAdupHistDetailBySelf", para);
		String smpsnId = (String)sqlMap.queryForObject("aciptarget.selectCompSmpsnId", para);
		if(!"0".equals(smpsnId)){
			para.put("smpsnId", smpsnId);
			List tempList =  sqlMap.queryForList("aciptarget.selectCompAdupHistDetailBySmpsnId", para);
			rtnList.addAll(tempList);
		}	
		
		return rtnList;
	}

	/**
	  * 법인고객 상세정보(보고이력 상세)
	  * 
	  * @param para	입력정보
	  * @return 게시물 목록 
	  * @throws Exception
	  */
	public List compReptHistDetail(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectCompReptHistDetail", para);
	}
	
	/**
	  * FATCA확인서 제출요청 이력 조회
	  * 
	  * @param para	입력정보
	  * @return 게시물 목록 
	  * @throws Exception
	  */
	public List confirmRequestHist(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectConfirmRequestHist", para);
	}	
}
