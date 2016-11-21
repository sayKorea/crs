package fatca.common;

import fatca.common.util.FTCSettings;

/**
 * @File Name    : Constants.java
 * @Package Name : fatca.common
 * @author       : 
 * @Description  : ��� ���� 
 * @History      : 
 */
public class FTCConstants {
	
	public static String Package;
	
	public static final int DefaultTableRow = Integer.parseInt(FTCSettings.get("page.row","20"));
	
	public static final String USER_KEY = "sessionUser";
	
	//public static final String IMAGE_HOST = "http://www.fatca.com";
	public static final int ExcelSize = Integer.parseInt(FTCSettings.get("excel.size","1000000"));
	
	public static final String USER_UPLOAD = FTCSettings.get("file.upload","d:/");
//	public static final String USER_UPLOAD = FTCSettings.get("file.upload","/jejubank/fat/file/upload/");
	
	public static final String USER_DOWNLOAD = FTCSettings.get("file.download","d:/");
//	public static final String USER_DOWNLOAD = FTCSettings.get("file.download","c:/jejubank/fat/file/download/");
	
	
	public static final String DEV_SSOTOKEN = FTCSettings.get("dev.ssotoken");
	public static final String PRD_SSOTOKEN = FTCSettings.get("prd.ssotoken");
	
	public static final String SERVER_TYPE = FTCSettings.get("server.type").toLowerCase();
	
}
