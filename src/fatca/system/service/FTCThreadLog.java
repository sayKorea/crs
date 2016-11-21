package fatca.system.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fatca.common.util.FTCDateUtil;


/**
 * @File Name    : FTCThreadLog.java
 * @Package Name : fatca.system.service
 * @author       : 
 * @Description  : 로그 발생 관리
 * @History      : 
 */
public class FTCThreadLog implements Runnable {
	 
	private Log log = LogFactory.getLog(this.getClass());
	 /**
	   * 로그 발생 
	   *  
	   */
	 public void run() {
		 
		 try{
			while(true){ 
				
				log.debug("###########################################");
				log.debug("##                                       ##");
				log.debug("##                                       ##");
				log.debug("##   for long take scene...              ##");
				log.debug("     "+FTCDateUtil.getToday("yyyy-mm-dd")+" "+FTCDateUtil.getTime(':'));
				log.debug("##                                       ##");
				log.debug("##                                       ##");
				log.debug("##                                       ##");
				log.debug("##                                       ##");
				log.debug("##                                       ##");
				log.debug("##                                       ##");
				log.debug("##                                       ##");
				log.debug("###########################################");
				
				Thread.sleep(156000);
				
			}
		 }catch(Exception e){
			 e.getMessage();
		 }
	 }
	
}
