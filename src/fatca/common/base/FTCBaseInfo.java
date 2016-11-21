package fatca.common.base;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibatis.sqlmap.client.SqlMapClient;

import fatca.common.util.FTCEaSqlConfig;

/**
 * @File Name    : BaseInfo.java
 * @Package Name : fatca.common.base;
 * @author       : 
 * @Description  : BaseInfo
 * @History      : 
 */
public class FTCBaseInfo   {
	
	protected static Log log = LogFactory.getLog(FTCBaseInfo.class);

	
	protected SqlMapClient sqlMap = FTCEaSqlConfig.getSqlMapInstance();

	
	public static String getStringForCLOB(Clob clob) {
        
        StringBuffer sbf = new StringBuffer();
        Reader br = null;
        char[] buf = new char[1024];
        int readcnt;

        try {
            br = clob.getCharacterStream();

            while ((readcnt=br.read(buf,0,1024))!=-1) {
                sbf.append(buf,0,readcnt);
            }

        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                 log.error("Failed to create String object from CLOB", e);
            }

        }finally{
            if(br!=null)
            {
                try {
                    br.close();
                } catch (IOException e) {
                    if (log.isErrorEnabled()) {
                        log.error("Failed to close BufferedReader object", e);
                    }    	
               }
            }
        }
        return sbf.toString();
    }  

	
}
