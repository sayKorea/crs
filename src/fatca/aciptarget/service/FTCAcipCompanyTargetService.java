package fatca.aciptarget.service;

import java.util.List;
import java.util.Map;

import fatca.common.base.FTCBaseService;
import fatca.common.util.FTCDbMap;


/**
 * @File Name    : AcipCompanyTargetService.java
 * @Package Name : fatca.aciptarget.service
 * @author       : 
 * @Description  : ���� �ǻ� ����� ��ȸ
 * @History      : 
 */
public class FTCAcipCompanyTargetService extends FTCBaseService {

	/**
	  * ���� �� �ǻ� ����� ��ȸ 
	  * 
	  * @param para	�Է�����
	  * @return �Խù� ��� 
	  * @throws Exception
	  */
	public List selectCompanyList(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectCompanyList", para);
	}

	/**
	  * ���ΰ� ������(�⺻����)
	  * 
	  * @param para	�Է�����
	  * @return �Խù� ��� 
	  * @throws Exception
	  */
	public FTCDbMap selectCompBasList(Map para) throws Exception {
		
		String contSeq = (String)sqlMap.queryForObject("aciptarget.selectCompanyBasListYn", para);
		if("N".equals(contSeq)){
			//<�ǻ������> ���� [FATCA ����Ȯ�μ����⳻��]�� "���� ����"�� FATCA Ȯ�μ��� ���� ���.[FATCA ���⺻] ���̺���
			return (FTCDbMap)sqlMap.queryForObject("aciptarget.selectCompBasList", para);			
		}else{
			//<�ǻ������> ���� [FATCA ����Ȯ�μ����⳻��]�� "���� ����"�� FATCA Ȯ�μ��� ���� ���.[FATCA ����Ȯ�μ� ���⳻��] ���̺���
			return (FTCDbMap)sqlMap.queryForObject("aciptarget.selectCompBasYList", para);			
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
		
		return sqlMap.queryForList("aciptarget.selectCompHistList", para);
	}
	
	/**
	  * ���ΰ� ������(FATCA Ȯ�μ� �����̷�)
	  * 
	  * @param para	�Է�����
	  * @return �Խù� ��� 
	  * @throws Exception
	  */
	public List prstHistList(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectCompPrstHistList", para);
	}
	
	/**
	  * ���ΰ� ������(�ջ��̷�)
	  * 
	  * @param para	�Է�����
	  * @return �Խù� ��� 
	  * @throws Exception
	  */
	public List adupHistList(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectCompAdupHistList", para);
	}
	
	
	/**
	  * ���ΰ� ������(�����̷�)
	  * 
	  * @param para	�Է�����
	  * @return �Խù� ��� 
	  * @throws Exception
	  */
	public List reptHistList(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectCompReptHistList", para);
	}

	/**
	  * ���ΰ� ������(�ջ��̷� ��)
	  * 
	  * @param para	�Է�����
	  * @return �Խù� ��� 
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
	  * ���ΰ� ������(�����̷� ��)
	  * 
	  * @param para	�Է�����
	  * @return �Խù� ��� 
	  * @throws Exception
	  */
	public List compReptHistDetail(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectCompReptHistDetail", para);
	}
	
	/**
	  * FATCAȮ�μ� �����û �̷� ��ȸ
	  * 
	  * @param para	�Է�����
	  * @return �Խù� ��� 
	  * @throws Exception
	  */
	public List confirmRequestHist(Map para) throws Exception {
		
		return sqlMap.queryForList("aciptarget.selectConfirmRequestHist", para);
	}	
}
