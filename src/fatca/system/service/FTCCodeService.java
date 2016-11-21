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
 * @Description  : �ڵ� ����
 * @History      : 
 */
public class FTCCodeService extends FTCBaseService{

	/**
	  * ���� �ڵ� ��ȸ
	  * @param para �Է�����
	  * @return ���� �ڵ� ��� (list)
	  * @throws Exception
	  */
	public List selectCodeList(Map para) throws Exception {
		
		return sqlMap.queryForList("system.selectCodeList", para);
	}


	/**
	   * �޺� ��ȸ
	   * 
	   * @param para �Է����� 
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
	   * �ڵ� �޺� ��ȸ
	   * 
	   * @param crym ���س�� 
	   * @param cdIdtfId �ڵ�ĺ��ھ��̵�
	   * @throws Exception
	   */
	public List getCodeList(String crym, String cdIdtfId) throws Exception {
		Map para = new HashMap();
		para.put("crym",crym);
		para.put("cdIdtfId",cdIdtfId);
				
		return (List)sqlMap.queryForList("system.selectCommonCode", para);
		
	}
	

	/**
	   * �����ڵ� ���� ��ȸ 
	   * 
	   * @param crym ���س�� 
	   * @param cdIdtfId �ڵ�ĺ��ھ��̵�
	   * @param stVale �ڵ�ĺ��ھ��̵�
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
	  * ���� �ڵ� ���� ��ȸ
	  * @param para �Է�����
	  * @return ���� �ڵ� ���� ��� (list)
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
	   * �ڵ� �ϰ� ���
	   * 
	   * @param para �Է����� 
	   * @return int ���ε� ����
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
				
				//�ڵ� ����
				if(((String)para.get("crym")).equals((String)dMap.get("data1"))){
					return rtnCnt;
				}
				if(!befCode.equals((String)dMap.get("data2"))){
					sqlMap.insert("system.insertCommonCodeByUpload", dMap);
					befCode = (String)dMap.get("data2");
				}
				
				//�ڵ� ������
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
	  * ȯ�溯�� ��ȸ
	  * @param para �Է�����
	  * @return ȯ�溯�� ��ȸ (list)
	  * @throws Exception
	  */
	public String getEnvSet(Map para) throws Exception {
		
		return (String)sqlMap.queryForObject("system.selectEnvSet", para);
	}

	/**
	   * ������ ��ȸ
	   * 
	   * @param para �Է����� 
	   * @throws Exception
	   */
	public List getBranchList(Map para) throws Exception {
		
		return (List)sqlMap.queryForList("system.selectBranchList", para);
		
	}
	

	/**
	   * ���� ��ȸ
	   * 
	   * @param para �Է����� 
	   * @throws Exception
	   */
	public List getEmpList(Map para) throws Exception {
		
		return (List)sqlMap.queryForList("system.selectEmpList", para);
		
	}
	

	/**
	   * �� ��ȸ(����)
	   * 
	   * @param para �Է����� 
	   * @throws Exception
	   */
	public List getCustList(Map para) throws Exception {
		
		return (List)sqlMap.queryForList("system.selectCustListODCR", para);
		
	}


	/**
	   * �� ��ȸ(����)
	   * 
	   * @param para �Է����� 
	   * @throws Exception
	   */
	public List getCoprList(Map para) throws Exception {
		
		return (List)sqlMap.queryForList("system.selectCoprList", para);
		
	}
	

	 /**
	   * �� ������, ����� ����
	   * 
	   * @param para �Է����� 
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
				
				//�������� ���̺� �����,å����,����������� ������ �߰� - ����. ���Ѱ������� ���� �۾����� ����
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
					
					//�ǻ��� ������Ʈ
					sqlMap.update(queryString, para);		
					//������ ����� �ش� ������ å���ڷ� ����, �������� '~' ó��.
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
					
					//�ǻ��� ������Ʈ
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
	   * �� ���� ���� üũ
	   * 
	   * @param para �Է�����
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
	   * sql select ó��
	   * 
	   * @param para �Է�����
	   * @return sql ó��(list) 
	   * @throws Exception
	   */
	public List execSelectQuery(Map para) throws Exception {
		
		return (List)sqlMap.queryForList("system.execSelectQuery", para);
	}
	
	 /**
	   * sql insert ó��
	   * 
	   * @param para �Է�����
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
	  * ���� Ȯ��
	  * @param para �Է�����
	  * @return �����ϴ� ����
	  * @throws Exception
	  */
	public List checkCustCount(Map para) throws Exception {
		
		return sqlMap.queryForList("system.selectCustCount", para);
		
	}

	
}
