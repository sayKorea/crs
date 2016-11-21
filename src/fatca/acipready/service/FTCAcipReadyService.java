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
 * @Description  : �ǻ� �⺻���� ����
 * @History      : 
 */
public class FTCAcipReadyService extends FTCBaseService {

	/**
	  * @param para �Է�����
	  * @return �ǻ� �⺻���� ��ȸ 
	  * @throws Exception
	  */
	public FTCDbMap selectBasicInfo(Map para) throws Exception {
		
		return (FTCDbMap)sqlMap.queryForObject("acipready.selectBasicInfo", para);
	}
	
	/**
	  * @param para	�Է�����
	  * @throws Exception
	  */
	public void insertBasicInfo(Map para) throws Exception {

		try{
			sqlMap.startTransaction();
			
			//���س�� ��ȸ�� ������ insert ������ update
			FTCDbMap info = this.selectBasicInfo(para);
			
			if(info==null){
				sqlMap.insert("acipready.insertBasicInfo", para);
				
				//���س�� �űԽ� �����ڵ� "00000000" �������� ����
				sqlMap.insert("acipready.insertCommonCodeByBasicInfo", para);
				sqlMap.insert("acipready.insertCommonCodeDetailByBasicInfo", para);
				
			}else{
				sqlMap.update("acipready.updateBasicInfo", para);
			}
			
			//�������� ����� BranchValue refresh
			FTCBranchValue.refresh();
			
			sqlMap.commitTransaction();
		}catch (Exception e){
			throw e;
		}finally{
			sqlMap.endTransaction();
		}
				
	}


	/**
	  * ������ ���
	  * 
	  * @param para	�Է�����
	  * @return ������ ��� 
	  * @throws Exception
	  */
	public List selectSamePersonList(Map para) throws Exception {
		
		return sqlMap.queryForList("acipready.selectSamePersonList", para);
	}	
	
	/**
	  * @param para	�Է�����
	  * @throws Exception
	  */
	public void insertSamePerson(Map para) throws Exception {
		
		try{
			sqlMap.startTransaction();
									
			para.put("csnoList", "0"+FTCStringUtil.replace(FTCStringUtil.replace((String)para.get("csnoArr"),"|",","),":",","));
			//���õȰ��� min smpsnId. �̰ɷ� ���õ� �� ��� ����
			String smpsnId = (String)sqlMap.queryForObject("acipready.selectExistSmpsnId", para); 
			//������ ���� ä��
			if(smpsnId==null){
				smpsnId = (String)sqlMap.queryForObject("acipready.selectSmpsnSeq", para); 
			}
			para.put("smpsnId", smpsnId);
			
			List<String> custList = FTCStringUtil.getTokenList((String)para.get("csnoArr"), "|");			
			for(String cust : custList){				
				
				String[] custInfo = cust.split(":");
				if(!smpsnId.equals(custInfo[0])){
					
					para.put("csno", custInfo[1]);
					if(!"".equals(custInfo[0])){ //���� ä���� �ִ��Ÿ� min������ �����ϱ� ���� ������ insert
						sqlMap.delete("acipready.deleteSamePerson", para);
					}
					
					sqlMap.insert("acipready.insertSamePerson", para);
					//�̷� �߰�
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
	  * @param para	�Է�����
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
				//�������� ������Ʈ�� ���ſ��� �̷����̺� ������� ����.
				sqlMap.delete("acipready.deleteSamePerson", para);
				//�̷� �߰�
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
