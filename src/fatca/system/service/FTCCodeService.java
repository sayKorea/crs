/**
 * 
 */
package fatca.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fatca.common.base.FTCBaseService;
import fatca.common.util.FTCStringUtil;


/**
 * @File Name    : CodeService.java
 * @Package Name : fatca.system.service
 * @author       : 
 * @Description  : 코드 관리
 * @History      : 
 */
public class FTCCodeService extends FTCBaseService{

	/**
	  * 공통 코드 조회
	  * @param para 입력정보
	  * @return 공통 코드 목록 (list)
	  * @throws Exception
	  */
	public List selectCodeList(Map para) throws Exception {
		
		return sqlMap.queryForList("system.selectCodeList", para);
	}


	/**
	   * 콤보 조회
	   * 
	   * @param para 입력정보 
	   * @throws Exception
	   */
	public List getCombo(Map para, String comboName) throws Exception {
		
		if("crym".equals(comboName)){
			return (List)sqlMap.queryForList("system.comboCrym", para);
		}else if("sms".equals(comboName)){
			return (List)sqlMap.queryForList("system.comboSms", para);
		}
		
		return null;
	}
	
	/**
	   * 코드 콤보 조회
	   * 
	   * @param crym 기준년월 
	   * @param cdIdtfId 코드식별자아이디
	   * @throws Exception
	   */
	public List getCodeList(String crym, String cdIdtfId) throws Exception {
		Map para = new HashMap();
		para.put("crym",crym);
		para.put("cdIdtfId",cdIdtfId);
				
		return (List)sqlMap.queryForList("system.selectCommonCode", para);
		
	}
	

	/**
	   * 공통코드 구간 조회 
	   * 
	   * @param crym 기준년월 
	   * @param cdIdtfId 코드식별자아이디
	   * @param stVale 코드식별자아이디
	   * @throws Exception
	   */
	public List getBetweenCodeList(String crym, String cdIdtfId, String stVal, String edVal) throws Exception {
		Map para = new HashMap();
		para.put("crym",crym);
		para.put("cdIdtfId",cdIdtfId);
		para.put("stVal",stVal);
		para.put("edVal",edVal);
		
		return (List)sqlMap.queryForList("system.selectBetweenCommonCode", para);
		
	}
	
	
	/**
	  * 공통 코드 제약 조회
	  * @param para 입력정보
	  * @return 공통 코드 제약 목록 (list)
	  * @throws Exception
	  */
	public List getUnderCodeList(String crym, String cdIdtfId, String underVal) throws Exception {
		Map para = new HashMap();
		para.put("crym",crym);
		para.put("cdIdtfId",cdIdtfId);
		para.put("underVal",underVal);
		
		return (List)sqlMap.queryForList("system.selectUnderCommonCode", para);
		
	}

	/**
	   * 코드 일괄 등록
	   * 
	   * @param para 입력정보 
	   * @return int 업로드 갯수
	   * @throws Exception
	   */
	public int uploadCommonCode(Map para) throws Exception {
		
		int rtnCnt = 0;
		try{
			sqlMap.startTransaction();
			
			//int colCnt = Integer.parseInt((String)para.get("colCnt"));
			sqlMap.delete("system.deleteCommonCodeByUpload", para);
			sqlMap.delete("system.deleteCommonCodeDetailByUpload", para);
			
			sqlMap.startBatch();
			
			String befCode = "";
			List dataList = (List)para.get("dataList");
			if(dataList == null){
				log.debug("dataList is zero");
				return rtnCnt;
			}
			for(int i=0; i<dataList.size(); i++){
				Map dMap = (Map)dataList.get(i);
				
				//코드 정보
				if(((String)para.get("crym")).equals((String)dMap.get("data1"))){
					return rtnCnt;
				}
				if(!befCode.equals((String)dMap.get("data2"))){
					sqlMap.insert("system.insertCommonCodeByUpload", dMap);
					befCode = (String)dMap.get("data2");
				}
				
				//코드 디테일
				sqlMap.insert("system.insertCommonCodeDetailByUpload", dMap);
				rtnCnt++;
			}
			
			sqlMap.executeBatch();
			
			sqlMap.commitTransaction();
		}catch (Exception e){
			throw e;
		}finally{
			sqlMap.endTransaction();
		}
		
		return rtnCnt;
				
	}
	
	/**
	  * 환경변수 조회
	  * @param para 입력정보
	  * @return 환경변수 조회 (list)
	  * @throws Exception
	  */
	public String getEnvSet(Map para) throws Exception {
		
		return (String)sqlMap.queryForObject("system.selectEnvSet", para);
	}

	/**
	   * 영업점 조회
	   * 
	   * @param para 입력정보 
	   * @throws Exception
	   */
	public List getBranchList(Map para) throws Exception {
		
		return (List)sqlMap.queryForList("system.selectBranchList", para);
		
	}
	

	/**
	   * 직원 조회
	   * 
	   * @param para 입력정보 
	   * @throws Exception
	   */
	public List getEmpList(Map para) throws Exception {
		
		return (List)sqlMap.queryForList("system.selectEmpList", para);
		
	}
	

	/**
	   * 고객 조회(개인)
	   * 
	   * @param para 입력정보 
	   * @throws Exception
	   */
	public List getCustList(Map para) throws Exception {
		
		return (List)sqlMap.queryForList("system.selectCustListODCR", para);
		
	}


	/**
	   * 고객 조회(법인)
	   * 
	   * @param para 입력정보 
	   * @throws Exception
	   */
	public List getCoprList(Map para) throws Exception {
		
		return (List)sqlMap.queryForList("system.selectCoprList", para);
		
	}
	

	 /**
	   * 고객 영업점, 담당자 변경
	   * 
	   * @param para 입력정보 
	   * @throws Exception
	   */
	public void saveCharge(Map para) throws Exception {
		
		try{
			sqlMap.startTransaction();
			
			String dist = (String)para.get("dist");
			String queryString = "";	
			if("sls".equals(dist)){
				queryString = "system.updateCustSls";
			}else{	
				if("rppr".equals(dist)){
					queryString = "system.updateCustRppr";
					//para.put("fatcaAuthCd","03");
				}else if("cgp".equals(dist)){
					queryString = "system.updateCustCgp";	
					//para.put("fatcaAuthCd","04");			
				}else if("rel".equals(dist)){
					queryString = "system.updateCustRel";
					//para.put("fatcaAuthCd","05");
				}	
				
				//직원권한 테이블에 담당자,책임자,고객관계관리자 없을시 추가 - 삭제. 권한관리에서 사전 작업으로 진행
				/*
				if(isNotExistEmp(para)){
					sqlMap.insert("system.insertAuthEmp", para);	
				}
				*/
				
			}
			
			if("".equals((String)para.get("csnoSeqArr"))){
				List custList = FTCStringUtil.getTokenList((String)para.get("csnoArr"), "|");			
				for(int i=0; i<custList.size(); i++){
					para.put("csno", custList.get(i).toString());
					para.put("custSeq", "0000");
					
					//실사결과 업데이트
					sqlMap.update(queryString, para);		
					//영업점 변경시 해당 영업점 책임자로 변경, 나머지는 '~' 처리.
					if("sls".equals(dist)){
						sqlMap.update("system.updateCustSlsRpprCgpRel", para);	
					}
				}	
			}else{
				List<String> custSeqList = FTCStringUtil.getTokenList((String)para.get("csnoSeqArr"), "|");	
				
				for(String cust : custSeqList){
					String[] custInfo = cust.split(":");
					para.put("csno", custInfo[0]);
					para.put("custSeq", custInfo[1]);		
					
					//실사결과 업데이트
					sqlMap.update(queryString, para);	
					if("sls".equals(dist)){
						sqlMap.update("system.updateCustSlsRpprCgpRel", para);	
					}
					
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
	   * 고객 존재 여부 체크
	   * 
	   * @param para 입력정보
	   * @return false 
	   * @throws Exception
	   */
	public boolean isNotExistEmp(Map para) throws Exception {
		
		String userEnob = (String)sqlMap.queryForObject("system.selectExistEmp", para);
		if(userEnob==null || "".equals(userEnob)){
			return true;
		}
		
		return false;
	}
	
	 /**
	   * sql select 처리
	   * 
	   * @param para 입력정보
	   * @return sql 처리(list) 
	   * @throws Exception
	   */
	public List execSelectQuery(Map para) throws Exception {
		
		return (List)sqlMap.queryForList("system.execSelectQuery", para);
	}
	
	 /**
	   * sql insert 처리
	   * 
	   * @param para 입력정보
	   * @throws Exception
	   */
	public void execInsertQuery(Map para) throws Exception {
		
		try{
			sqlMap.startTransaction();
			
			sqlMap.insert("system.execInsertQuery", para);
			
			sqlMap.commitTransaction();
		}catch (Exception e){
			throw e;
		}finally{
			sqlMap.endTransaction();
		}
		
	}
	

	/**
	  * 고객수 확인
	  * @param para 입력정보
	  * @return 존재하는 고객수
	  * @throws Exception
	  */
	public List checkCustCount(Map para) throws Exception {
		
		return sqlMap.queryForList("system.selectCustCount", para);
		
	}

	
}
