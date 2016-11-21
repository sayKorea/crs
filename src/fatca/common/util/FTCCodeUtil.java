package fatca.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fatca.common.base.FTCBaseService;


/**
 * @File Name    : CodeUtil.java
 * @Package Name : fatca.common.util;
 * @author       : 
 * @Description  : 코드 정보
 * @History      : 
 */
public class FTCCodeUtil extends FTCBaseService {
	/**
	 * HTML의 콤보박스 형태의 공통 코드 목록을 가져온다.
	 * 
	 * @param codeId
	 * @param selectedId
	 * @return
	 */
	public static String getComboBox(String codeId, String selectedId) {
		StringBuffer str = new StringBuffer();

		return str.toString();
	}

	public static String getComboBox(String codeId, String attr,
			String selectedId) {
		StringBuffer str = new StringBuffer(); 

		return str.toString();
	}

	/**
	 * 코드 명을 얻어온다.
	 * 
	 * @param codeId
	 * @param highCodeId
	 * @return
	 */
	public static String getCodeName(String codeId, String highCodeId) {
		return null;
	}
	
	/**
	 * 코드 트리구조를 얻어온다.
	 * 
	 * @return
	 */
	public static String getCodeTree() {
		StringBuffer str = new StringBuffer();

		return str.toString();
	}
	
	/**
	 * 아이디 체크
	 * 
	 * @param codeId
	 * @param highCodeId
	 * @return res
	 */
	public static boolean checkCodeId(String codeId, String highCodeId) {
		boolean res = true;

		return res;
	}
	
	/**
	 * 코드 List을 얻어온다.
	 * 
	 * @param codeKey
	 * @return null
	 */
	public List getCodeList(String codeKey) {
		Map para = new HashMap();
		para.put("cdIdtfId",codeKey);
		
		try{
			return sqlMap.queryForList("system.selectCommonCode", para);
			
		}catch(Exception e){
			e.getMessage();
		}
		return null;
	}
}
