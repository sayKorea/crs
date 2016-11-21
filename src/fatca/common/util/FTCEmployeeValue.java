
package fatca.common.util;

import java.util.HashMap;
import java.util.List;

import fatca.common.base.FTCBaseService;

/**
 * @File Name    : EmployeeValue.java
 * @Package Name : fatca.common.util;
 * @author       : 
 * @Description  : 직원정보 설정
 * @History      : 
 */
public final class FTCEmployeeValue extends FTCBaseService {

	private static FTCEmployeeValue instance;	
	private static HashMap empMap;

	private FTCEmployeeValue() {
		try{
			load();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	/**
	 * 직원정보 값 초기화
	 * 
	 * @return getInstance
	 */
	public synchronized static FTCEmployeeValue refreshOld() {
		instance = null;
		return getInstance();
	}

	/**
	 * 직원정보 값 초기화
	 * 
	 */
	public synchronized static void refresh() {
		instance = null;
	}
	
	/**
	 * 직원정보 getInstance
	 * 
	 * @return instance
	 */
	public synchronized static FTCEmployeeValue getInstance() {
		if (instance == null) {
			try {
				instance = new FTCEmployeeValue();				
			} catch (Exception e) {
				e.getMessage();
			}
		}
		return instance;
	}
	
	/**
	 * 직원정보 load
	 * 
	 * @throws Exception
	 */
	public synchronized void load() throws Exception {
				
		empMap = new HashMap();
		try {			
			List empList = sqlMap.queryForList("system.selectEmpSession",new HashMap());
			for(int k=0; k<empList.size(); k++){
				
				FTCDbMap map = (FTCDbMap)empList.get(k);
				empMap.put(map.get("enob"), map.get("ennm"));
			}	
			if (log.isTraceEnabled()) {
				log.trace("  Loading completed");
			}
		} catch (Exception e) {
			e.getMessage();
		}

	}
	
	/**
	 * 영어이름 가져오기
	 * 
	 * @param enob
	 * @throws ennm
	 */
	public static String getEnnm(String enob) {
		
		String ennm = "";
		try{			
			getInstance();			
			
			ennm = (String)empMap.get(enob);
		
		} catch (Exception e) {
			e.getMessage();
		}
		return ennm;
	}
	
}