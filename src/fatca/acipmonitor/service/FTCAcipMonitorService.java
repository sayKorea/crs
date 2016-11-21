package fatca.acipmonitor.service;

import java.util.List;
import java.util.Map;

import fatca.common.base.FTCBaseService;


/**
 * @File Name    : AcipMonitorService.java
 * @Package Name : fatca.acipmonitor.service
 * @author       : 
 * @Description  : FATCA 실사 모니터링 - (영업점 진척현황) 
 * @History      : 
 */
public class FTCAcipMonitorService extends FTCBaseService {

	/**
	  * @param para 입력정보
	  * @return 영업점 실사 현황 조회
	  * @throws Exception
	  */
	public List selectBranchStausList(Map para) throws Exception {
		
		return sqlMap.queryForList("acipmonitor.selectBranchStausList", para);
	}	
	
	/**
	  * @param para	입력정보
	  * @return 년도별 실사이력 조회
	  * @throws Exception
	  */
	public List selectAcipYearHistoryList(Map para) throws Exception {
		
		return sqlMap.queryForList("acipmonitor.selectAcipYearHistoryList", para);
	}	
}
