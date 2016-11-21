package fatca.acipmonitor.service;

import java.util.List;
import java.util.Map;

import fatca.common.base.FTCBaseService;


/**
 * @File Name    : AcipMonitorService.java
 * @Package Name : fatca.acipmonitor.service
 * @author       : 
 * @Description  : FATCA �ǻ� ����͸� - (������ ��ô��Ȳ) 
 * @History      : 
 */
public class FTCAcipMonitorService extends FTCBaseService {

	/**
	  * @param para �Է�����
	  * @return ������ �ǻ� ��Ȳ ��ȸ
	  * @throws Exception
	  */
	public List selectBranchStausList(Map para) throws Exception {
		
		return sqlMap.queryForList("acipmonitor.selectBranchStausList", para);
	}	
	
	/**
	  * @param para	�Է�����
	  * @return �⵵�� �ǻ��̷� ��ȸ
	  * @throws Exception
	  */
	public List selectAcipYearHistoryList(Map para) throws Exception {
		
		return sqlMap.queryForList("acipmonitor.selectAcipYearHistoryList", para);
	}	
}
