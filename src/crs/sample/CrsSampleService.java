package crs.sample;

import java.util.List;
import java.util.Map;

import fatca.common.base.FTCBaseService;
import fatca.common.util.FTCDbMap;


/**
 * @author CeeDo
 *
 */
public class CrsSampleService extends FTCBaseService {
	/**
	 * @param para
	 * @return
	 * @throws Exception
	 */
	public FTCDbMap select(Map<?, ?> para) throws Exception {
		return  (FTCDbMap)sqlMap.queryForObject("sample.select", para);
	}
	
	public List<?> getBaseYmList(Map<?, ?> param) throws Exception {
		return  sqlMap.queryForList("sample.getBaseYmList", param);
	}
	
	public List<?> getGridList(Map<?, ?> param) throws Exception {
		return  sqlMap.queryForList("sample.getGridList", param);
	}
}
