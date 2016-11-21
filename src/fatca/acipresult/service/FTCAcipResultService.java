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
 * @Description  : ���� ����
 * @History      : 
 */
public class FTCAcipResultService extends FTCBaseService {
	

	/**
	  * ���� �ǻ� ��� ��� ��ȸ
	  * 
	  * @param para	�Է�����
	  * @return ���� �ǻ� ��� ��� 
	  * @throws Exception
	  */
	public FTCDbMap selectDocumentInfo(Map para) throws Exception {
		
		return (FTCDbMap)sqlMap.queryForObject("acipresult.selectDocumentResultInfo", para);
	}

	/**
	  * ���� �ǻ� ���(���׸�)
	  * 
	  * @param para	�Է�����
	  * @return ���� �ǻ� ���(���׸�)(list)
	  * @throws Exception
	  */
	public List documentInfoList(Map para) throws Exception {
		
		return sqlMap.queryForList("acipresult.selectDocumentInfoList", para);
	}
	
	/**
	  * ���� �ǻ� ���(����)
	  * 
	  * @param para	�Է�����
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
			
			//�ǻ�Ϸ��� ������Ʈ
			sqlMap.update("acipresult.updateAcipRsltDoc", para);
			//fatcaȮ�μ�����ڵ� ������Ʈ
			sqlMap.update("acipresult.updateCfrrptAcipRscd", para);
			
			sqlMap.commitTransaction();
		} catch (Exception e){
			throw e;
		}finally{
			sqlMap.endTransaction();
		}
	}
	
	
	/**
	  * �������� ���� ��� ��ȸ
	  * 
	  * @param para	�Է�����
	  * @return �������� ���� ��� ���(list) 
	  * @throws Exception
	  */
	public FTCDbMap selectQuestionInfo(Map para) throws Exception {
		
		return (FTCDbMap)sqlMap.queryForObject("acipresult.selectQuestionInfo", para);
	}
	
	/**
	  * �������� ���� ��� ����
	  * 
	  * @param para	�Է�����
	  * @throws Exception
	  */
	public void insertResultInfo(Map para) throws Exception {

		try{
			sqlMap.startTransaction();
			
			//���س�� ��ȸ�� ������ insert ������ update
			String checkYn = (String)sqlMap.queryForObject("acipresult.selectCustIqrRsltInfo", para);
			
			if("N".equals(checkYn)){
				sqlMap.insert("acipresult.insertCustIqrRslt", para);
				
			}else{
				sqlMap.update("acipresult.updateCustIqrRslt", para);
			}	
			
			//�ǻ�Ϸ��� ������Ʈ
			sqlMap.update("acipresult.updateAcipRslt", para);
			
			//fatcaȮ�μ�����ڵ� ������Ʈ
			sqlMap.update("acipresult.updateCfrrptAcipRscd", para);
			
			sqlMap.commitTransaction();
		} catch (Exception e){
			throw e;
		}finally{
			sqlMap.endTransaction();
		}
	}

	/**
	  * ���� �ǻ��� ��� ��� ��ȸ
	  * 
	  * @param para	�Է�����
	  * @return ���� �ǻ��� ��� ���(list) 
	  * @throws Exception
	  */
	public List selectPersonalResultList(Map para) throws Exception {
		
		return sqlMap.queryForList("acipresult.selectPersonalResultList", para);
	}

	/**
	  * ���� �ǻ��� ��� ��� ��ȸ
	  * 
	  * @param para	�Է�����
	  * @return ���� �ǻ��� ��� ���(list) 
	  * @throws Exception
	  */
	public List selectCompanyResultList(Map para) throws Exception {
		
		return sqlMap.queryForList("acipresult.selectCompanyResultList", para);
	}
	

	
	/**
	  * ���� �ǻ�Ϸ�
	  * 
	  * @param para	�Է�����
	  * @return ���� �ǻ�Ϸ�
	  * @throws Exception
	  */
	public Map selectCompleteInfo(Map para) throws Exception {
		
		return (Map)sqlMap.queryForObject("acipresult.selectCompleteInfo", para);
	}
	
	/**
	  * ���� �ǻ�Ϸ� ���
	  * 
	  * @param para	�Է�����
	  * @return ���� �ǻ�Ϸ�(list)
	  * @throws Exception
	  */
	public List selectCompleteList(Map para) throws Exception {
		
		return sqlMap.queryForList("acipresult.selectCompleteList", para);
	}
	
	/**
	  * ���� �ǻ�Ϸ� ����
	  * 
	  * @param para	�Է�����
	  * @return ���� �ǻ�Ϸ�(sav)
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
	  * ������ �ǻ��� ��ȸ
	  * 
	  * @param para	�Է�����
	  * @return �ǻ��� ��� 
	  * @throws Exception
	  */
	public List selectBranchResultList(Map para) throws Exception {
		
		return sqlMap.queryForList("acipresult.selectBranchResultList", para);
	}
	

	/**
	  * ������ �ǻ��� ��ȸ(��Ȳ)
	  * 
	  * @param para	�Է�����
	  * @return �ǻ��� ��Ȳ 
	  * @throws Exception
	  */
	public Map selectBranchResultInfo(Map para) throws Exception {
		
		return (Map)sqlMap.queryForObject("acipresult.selectBranchResultInfo", para);
	}
	
	
	
	
}
