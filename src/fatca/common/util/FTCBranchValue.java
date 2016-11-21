
package fatca.common.util;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fatca.common.base.FTCBaseService;

/**
 * @File Name    : BranchValue.java
 * @Package Name : fatca.common.util;
 * @author       : 
 * @Description  : 영업점 정보
 * @History      : 
 */
public final class FTCBranchValue extends FTCBaseService {

	private static FTCBranchValue instance;

	private static Log log = LogFactory.getLog("com.common.util.BranchValue");
	private static HashMap branchMap;
	private static List branchList;

	private FTCBranchValue() {
		try{
			load();
		} catch (Exception e) {
			e.getMessage();			
		}
	}

	/**
	 * 영업점 설정 초기화
	 * 
	 * @return 영업점 설정 초기화
	 * @throws Exception
	 */
	public synchronized static FTCBranchValue refreshOld() {		
		instance = null;
		return getInstance();
	}
	

	/**
	 * 영업점 설정 초기화
	 * 
	 * @throws Exception
	 */
	public synchronized static void refresh() {
		log.debug("BranchValue.refresh()");
		instance = null;
	}

	public synchronized static FTCBranchValue getInstance() {
		if (instance == null) {
			try {
				instance = new FTCBranchValue();				
			} catch (Exception e) {
				e.getMessage();
			}
		}
		return instance;
	}
	
	/**
	 * 영업점 설정 변수 호출
	 * 
	 * @throws Exception
	 */
	public synchronized void load() throws Exception {
		log.debug("BranchValue.load()");
		branchMap = new HashMap();
		try {
			
			branchList = sqlMap.queryForList("system.selectBranchSession",new HashMap());
			for(int k=0; k<branchList.size(); k++){
				
				FTCDbMap map = (FTCDbMap)branchList.get(k);
				branchMap.put(map.get("brno"), map.get("brcNm"));
			}	
			if (log.isTraceEnabled()) {
				log.trace("Loading completed");
			}
		} catch (Exception e) {
			e.getMessage();
		}

	}

	public static String getBrnm(String brno) {
		
		String brnm = "";
		try{			
			getInstance();			
			
			if(branchMap.get(brno)!=null){
				brnm = (String)branchMap.get(brno);
			}
			
		} catch (Exception e) {
			e.getMessage();
		}
		return brnm;
	}
	

	public static List getBranchList() {
		
		try{			
			getInstance();		
			
		} catch (Exception e) {
			e.getMessage();
		}
		return branchList;
	}

}