package fatca.acipresult.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fatca.common.base.FTCBaseService;
import fatca.common.util.FTCDbMap;


/**
 * @File Name    : AcipResultService.java
 * @Package Name : fatca.acipresult.service
 * @author       : 
 * @Description  : 보고 관리
 * @History      : 
 */
public class FTCAcipResultService extends FTCBaseService {
	

	/**
	  * 문서 실사 결과 등록 조회
	  * 
	  * @param para	입력정보
	  * @return 문서 실사 결과 목록 
	  * @throws Exception
	  */
	public FTCDbMap selectDocumentInfo(Map para) throws Exception {
		
		return (FTCDbMap)sqlMap.queryForObject("acipresult.selectDocumentResultInfo", para);
	}

	/**
	  * 문서 실사 결과(상세항목)
	  * 
	  * @param para	입력정보
	  * @return 문서 실사 결과(상세항목)(list)
	  * @throws Exception
	  */
	public List documentInfoList(Map para) throws Exception {
		
		return sqlMap.queryForList("acipresult.selectDocumentInfoList", para);
	}
	
	/**
	  * 문서 실사 결과(저장)
	  * 
	  * @param para	입력정보
	  * @throws Exception
	  */
	public void insertDocInfo(Map para) throws Exception {

		try{
			sqlMap.startTransaction();
			
			List docObj = sqlMap.queryForList("acipresult.selectDocNumList", para);

			sqlMap.delete("acipresult.deleteDocAcipRslt", para);
			
			para.put("fatcaAsmInfoItemDvcdTotal", "12");
			for(int i=0;i<docObj.size();i++){
				Map map = (Map)docObj.get(i);
				String docNum = map.get("docNumCd").toString();								
				Map newmap = new HashMap();
				newmap.put("crym", para.get("crym"));
				newmap.put("csno", para.get("csno"));				
				newmap.put("docCd", docNum);
				
				newmap.put("enob", para.get("enob"));
				newmap.put("brno", para.get("brno"));
				
				String fatcaAsmInfoItemDvcd = (String)para.get("fatcaAsmInfoItemDvcd"+docNum); 
				if("11".equals(fatcaAsmInfoItemDvcd)){
					newmap.put("fatcaAsmInfoItemDvcd", fatcaAsmInfoItemDvcd);
					newmap.put("fatcaAcipRscd", para.get("fatcaAcipRscd"+docNum));
					para.put("fatcaAsmInfoItemDvcdTotal", fatcaAsmInfoItemDvcd);
				}else{
					newmap.put("fatcaAsmInfoItemDvcd", "12");
					newmap.put("fatcaAcipRscd", "00");
				}
				sqlMap.insert("acipresult.insertDocAcipRslt", newmap);		
							
			}	
			
			//실사완료일 업데이트
			sqlMap.update("acipresult.updateAcipRsltDoc", para);
			//fatca확인서결과코드 업데이트
			sqlMap.update("acipresult.updateCfrrptAcipRscd", para);
			
			sqlMap.commitTransaction();
		} catch (Exception e){
			throw e;
		}finally{
			sqlMap.endTransaction();
		}
	}
	
	
	/**
	  * 고객관계자 질의 등록 조회
	  * 
	  * @param para	입력정보
	  * @return 고객관계자 질의 등록 목록(list) 
	  * @throws Exception
	  */
	public FTCDbMap selectQuestionInfo(Map para) throws Exception {
		
		return (FTCDbMap)sqlMap.queryForObject("acipresult.selectQuestionInfo", para);
	}
	
	/**
	  * 고객관계자 질의 등록 저장
	  * 
	  * @param para	입력정보
	  * @throws Exception
	  */
	public void insertResultInfo(Map para) throws Exception {

		try{
			sqlMap.startTransaction();
			
			//기준년월 조회후 없으면 insert 있으면 update
			String checkYn = (String)sqlMap.queryForObject("acipresult.selectCustIqrRsltInfo", para);
			
			if("N".equals(checkYn)){
				sqlMap.insert("acipresult.insertCustIqrRslt", para);
				
			}else{
				sqlMap.update("acipresult.updateCustIqrRslt", para);
			}	
			
			//실사완료일 업데이트
			sqlMap.update("acipresult.updateAcipRslt", para);
			
			//fatca확인서결과코드 업데이트
			sqlMap.update("acipresult.updateCfrrptAcipRscd", para);
			
			sqlMap.commitTransaction();
		} catch (Exception e){
			throw e;
		}finally{
			sqlMap.endTransaction();
		}
	}

	/**
	  * 개인 실사대상 결과 등록 조회
	  * 
	  * @param para	입력정보
	  * @return 개인 실사대상 결과 등록(list) 
	  * @throws Exception
	  */
	public List selectPersonalResultList(Map para) throws Exception {
		
		return sqlMap.queryForList("acipresult.selectPersonalResultList", para);
	}

	/**
	  * 법인 실사대상 결과 등록 조회
	  * 
	  * @param para	입력정보
	  * @return 법인 실사대상 결과 등록(list) 
	  * @throws Exception
	  */
	public List selectCompanyResultList(Map para) throws Exception {
		
		return sqlMap.queryForList("acipresult.selectCompanyResultList", para);
	}
	

	
	/**
	  * 본점 실사완료
	  * 
	  * @param para	입력정보
	  * @return 본점 실사완료
	  * @throws Exception
	  */
	public Map selectCompleteInfo(Map para) throws Exception {
		
		return (Map)sqlMap.queryForObject("acipresult.selectCompleteInfo", para);
	}
	
	/**
	  * 본점 실사완료 목록
	  * 
	  * @param para	입력정보
	  * @return 본점 실사완료(list)
	  * @throws Exception
	  */
	public List selectCompleteList(Map para) throws Exception {
		
		return sqlMap.queryForList("acipresult.selectCompleteList", para);
	}
	
	/**
	  * 본점 실사완료 저장
	  * 
	  * @param para	입력정보
	  * @return 본점 실사완료(sav)
	  * @throws Exception
	  */
	public int updateMainComplete(Map para) throws Exception {
		
		int rCnt = 0;
		try{
			sqlMap.startTransaction();
			
			rCnt = sqlMap.update("acipresult.updateMainComplete", para);
			
			sqlMap.commitTransaction();
			
		} catch (Exception e){
			throw e;
			
		}finally{
			sqlMap.endTransaction();
		}
		return rCnt;
	}

	/**
	  * 영업점 실사결과 조회
	  * 
	  * @param para	입력정보
	  * @return 실사결과 목록 
	  * @throws Exception
	  */
	public List selectBranchResultList(Map para) throws Exception {
		
		return sqlMap.queryForList("acipresult.selectBranchResultList", para);
	}
	

	/**
	  * 영업점 실사결과 조회(현황)
	  * 
	  * @param para	입력정보
	  * @return 실사결과 현황 
	  * @throws Exception
	  */
	public Map selectBranchResultInfo(Map para) throws Exception {
		
		return (Map)sqlMap.queryForObject("acipresult.selectBranchResultInfo", para);
	}
	
	
	
	
}
