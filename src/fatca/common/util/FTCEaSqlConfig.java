package fatca.common.util;

import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import fatca.common.FTCConstants;

/**
 * @File Name    : EaSqlConfig.java
 * @Package Name : fatca.common.util;
 * @author       : 
 * @Description  : ibatis ¼³Á¤
 * @History      : 
 */
public class FTCEaSqlConfig {
	private static final SqlMapClient sqlMap;
	static {
		try {
			String resource = "sqlMap-config.xml" ;
			if(FTCConstants.SERVER_TYPE.equals("loc")){
				resource = "sqlMap-config-loc.xml";
			}
			
			Reader reader = Resources.getResourceAsReader (resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException (e);
		}
	}
	public static SqlMapClient getSqlMapInstance () {
		return sqlMap;
	}
}