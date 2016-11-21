package fatca.aciptarget.service;

import java.util.List;
import java.util.Map;

import fatca.common.base.FTCBaseService;
import fatca.common.util.FTCDbMap;


/**
 * @File Name    : AcipPersonalTargetService.java
 * @Package Name : fatca.aciptarget.service
 * @author       : 
 * @Description  : ���� �ǻ� ����� ��ȸ
 * @History      : 
 */
public class FTCAcipPersonalTargetService extends FTCBaseService {

	/**
	  * ���� �� �ǻ� ����� ��ȸ 
	  * 
	  * @param para	�Է�����
	  * @return �Խù� ��� 
	  * @throws Exception
	  */
	public List selectPersonalList(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectPersonalList", para);
	}

	/**
	  * ���ΰ� ������(�⺻����)
	  * 
	  * @param para	�Է�����
	  * @return �Խù� ��� 
	  * @throws Exception
	  */
	public FTCDbMap selectPrsnlBasList(Map para) throws Exception {
		
		String contSeq = (String)sqlMap.queryForObject("aciptarget.selectPrsnlBasListYn", para);
		if("N".equals(contSeq)){
			//<�ǻ������> ���� [FATCA ����Ȯ�μ����⳻��]�� "���� ����"�� FATCA Ȯ�μ��� ���� ���.[FATCA ����Ȯ�μ� ���⳻��] ���̺���
			return (FTCDbMap)sqlMap.queryForObject("aciptarget.selectPrsnlBasList", para);			
		}else{
			para.put("maxPrstDt", contSeq);
			//<�ǻ������> ���� [FATCA ����Ȯ�μ����⳻��]�� "���� ����"�� FATCA Ȯ�μ��� ���� ���.[FATCA ���⺻] ���̺���
			return (FTCDbMap)sqlMap.queryForObject("aciptarget.selectPrsnlBasYList", para);			
		}
	}
	
	/**
	  * ���ΰ� ������(��������)
	  * 
	  * @param para	�Է�����
	  * @return �Խù� ��� 
	  * @throws Exception
	  */
	public List acntBasList(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectAcntBasList", para);
	}
	
	
	/**
	  * ���ΰ� ������(�ǻ��̷�)
	  * 
	  * @param para	�Է�����
	  * @return �Խù� ��� 
	  * @throws Exception
	  */
	public List acipHistList(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectHistList", para);
	}
	
	/**
	  * ���ΰ� ������(FATCA Ȯ�μ� �����̷�)
	  * 
	  * @param para	�Է�����
	  * @return �Խù� ��� 
	  * @throws Exception
	  */
	public List prstHistList(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectPrstHistList", para);
	}
	
	/**
	  * ���ΰ� ������(�ջ��̷�)
	  * 
	  * @param para	�Է�����
	  * @return �Խù� ��� 
	  * @throws Exception
	  */
	public List adupHistList(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectAdupHistList", para);
	}
	
	/**
	  * ���ΰ� ������(�����̷�)
	  * 
	  * @param para	�Է�����
	  * @return �Խù� ��� 
	  * @throws Exception
	  */
	public List reptHistList(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectReptHistList", para);
	}

	/**
	  * ���ΰ� ������(�ջ��̷� ��)
	  * 
	  * @param para	�Է�����
	  * @return �Խù� ��� 
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
	  * ���ΰ� ������(�����̷� ��)
	  * 
	  * @param para	�Է�����
	  * @return �Խù� ��� 
	  * @throws Exception
	  */
	public List reptHistDetail(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectReptHistDetail", para);
	}	
}
