package fatca.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fatca.common.base.FTCBaseService;


/**
 * @File Name    : CodeUtil.java
 * @Package Name : fatca.common.util;
 * @author       : 
 * @Description  : �ڵ� ����
 * @History      : 
 */
public class FTCCodeUtil extends FTCBaseService {
	/**
	 * HTML�� �޺��ڽ� ������ ���� �ڵ� ����� �����´�.
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
	 * �ڵ� ���� ���´�.
	 * 
	 * @param codeId
	 * @param highCodeId
	 * @return
	 */
	public static String getCodeName(String codeId, String highCodeId) {
		return null;
	}
	
	/**
	 * �ڵ� Ʈ�������� ���´�.
	 * 
	 * @return
	 */
	public static String getCodeTree() {
		StringBuffer str = new StringBuffer();

		return str.toString();
	}
	
	/**
	 * ���̵� üũ
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
	 * �ڵ� List�� ���´�.
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
