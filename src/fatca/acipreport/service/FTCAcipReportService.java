package fatca.acipreport.service;

import java.util.List;
import java.util.Map;

import fatca.common.base.FTCBaseService;


/**
 * @File Name    : AcipReportService.java
 * @Package Name : fatca.acipreport.service
 * @author       : 
 * @Description  : 보고 관리
 * @History      : 
 */
public class FTCAcipReportService extends FTCBaseService {


	/**
	  * @param para 입력정보
	  * @return 개인 보고서 목록 
	  * @throws Exception
	  */
	public List selectPersonalReportList(Map para) throws Exception {
		
		return sqlMap.queryForList("acipreport.selectPersonalReportList", para);
	}
	
	/**
	  * @param para 입력정보
	  * @return 법인 보고서 목록 
	  * @throws Exception
	  */
	public List selectCompanyReportList(Map para) throws Exception {
		
		return sqlMap.queryForList("acipreport.selectCompanyReportList", para);
	}

	
}
