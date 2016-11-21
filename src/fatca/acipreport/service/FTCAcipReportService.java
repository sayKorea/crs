package fatca.acipreport.service;

import java.util.List;
import java.util.Map;

import fatca.common.base.FTCBaseService;


/**
 * @File Name    : AcipReportService.java
 * @Package Name : fatca.acipreport.service
 * @author       : 
 * @Description  : ���� ����
 * @History      : 
 */
public class FTCAcipReportService extends FTCBaseService {


	/**
	  * @param para �Է�����
	  * @return ���� ���� ��� 
	  * @throws Exception
	  */
	public List selectPersonalReportList(Map para) throws Exception {
		
		return sqlMap.queryForList("acipreport.selectPersonalReportList", para);
	}
	
	/**
	  * @param para �Է�����
	  * @return ���� ���� ��� 
	  * @throws Exception
	  */
	public List selectCompanyReportList(Map para) throws Exception {
		
		return sqlMap.queryForList("acipreport.selectCompanyReportList", para);
	}

	
}
