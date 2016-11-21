package crs.template;

import java.util.List;
import java.util.Map;

import fatca.common.base.FTCBaseService;
import fatca.common.util.FTCDbMap;
import fatca.common.util.FTCStringUtil;


/**
 * @author CeeDo
 *
 */
public class CrsTemplateService extends FTCBaseService {

	public FTCDbMap getComplete(Map<?, ?> param) throws Exception {
		return  (FTCDbMap)sqlMap.queryForObject("template.getComplete", param);
	}
	
	public List<?> getCodeList(Map<?, ?> param) throws Exception {
		return  sqlMap.queryForList("template.getCodeList", param);
	}
	
	public List<?> getGridList(Map<?, ?> param) throws Exception {
		return  sqlMap.queryForList("template.getGridList", param);
	}
	

	
	public Object insertCode(Map<?, ?> param) throws Exception {
		Object o = new Object();
		try{
			sqlMap.startTransaction();
			o = sqlMap.insert("template.insertCode", param);
			log.debug(o);
			sqlMap.commitTransaction();
		}catch (Exception e){
			throw e;
		}finally{
			sqlMap.endTransaction();
		}
		return o;
	}
	
	public Object updateCode(Map<?, ?> param) throws Exception {
		Object o = new Object();
		try{
			sqlMap.startTransaction();
			o = sqlMap.insert("template.updateCode", param);
			log.debug(o);
			sqlMap.commitTransaction();
		}catch (Exception e){
			throw e;
		}finally{
			sqlMap.endTransaction();
		}
		return o;
	}
	
	public Object deleteCode(Map<?, ?> param) throws Exception {
		Object o = new Object();
		try{
			sqlMap.startTransaction();
			o = sqlMap.insert("template.deleteCode", param);
			log.debug(o);
			sqlMap.commitTransaction();
		}catch (Exception e){
			throw e;
		}finally{
			sqlMap.endTransaction();
		}
		return o;
	}
	
}
