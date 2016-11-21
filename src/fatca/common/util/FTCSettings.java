package fatca.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @File Name    : Settings.java
 * @Package Name : fatca.common.util;
 * @author       : 
 * @Description  : ���� ����
 * @History      : 
 */
public class FTCSettings implements FTCFileChangeListener {

	private static String propFile = "fatca.properties";

	private transient static Log log = LogFactory.getLog(FTCSettings.class);

	private static FTCSettings instance;

	//private long checkPeriod = 2000;

	private Properties base = new Properties();

	public static String get(String key) {
		// convenience method as shortcut for:
		return FTCHangul.convertUTF(getInstance().getSetting(key)).trim();
	}

	public static String get(String key, String def) {
		// convenience method as shortcut for:
		if (getInstance().getSetting(key) == null) {
			return def;
		}
		return FTCHangul.convertUTF(getInstance().getSetting(key));
	}

	/**
	 * �������� ���ΰ�ħ
	 * 
	 * @return getInstance
	 */
	public synchronized static FTCSettings refresh() {
		instance = null;
		return getInstance();

	}

	private FTCSettings() {
		// private constructor
	}

	private FTCSettings(String baseFile) throws IOException {
		// needed???
		load(baseFile);

	}

	public synchronized static FTCSettings getInstance() {
		
		if (instance == null) {
			try {
				if (log.isTraceEnabled()) {
					log.trace("Settings.getInstance()");
				}
				instance = new FTCSettings(propFile);
				

			} catch (IOException e) {
				e.getMessage();
			}
		}
		return instance;
	}
	
	/**
	 * load 
	 * 
	 * @param name
	 * @throws IOException
	 */
	public synchronized void load(String name) throws IOException {
		InputStream is = null;

		try {
//			if (log.isTraceEnabled()) {
//				log.trace("  Loading resource '" + name + "'");
//			}
//			ClassLoader classLoader = Thread.currentThread()
//					.getContextClassLoader();
//			if (classLoader == null) {
//			
			ClassLoader classLoader = this.getClass().getClassLoader();
			
			is = classLoader.getResourceAsStream(name);
			if (is != null) {

				base.load(is);
				is.close();

			}
			setCheckPeriod();
			if (log.isTraceEnabled()) {
				log.trace("  Loading resource completed");
			}
		} catch (Throwable t) {
			log.error("loadLocale()", t);
			if (is != null) {
				try {
					is.close();
				} catch (Throwable u) {
					u.printStackTrace();
				}
			}
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Throwable u) {
					u.printStackTrace();
				}
			}
		}

	}
	
	/**
	 * ���� �������� 
	 * 
	 * @param key
	 * @return value
	 */
	public String getSetting(String key) {

		String value = null;

		value = base.getProperty(key);
		return value;
	}

	/**
	 * ���� ����
	 * 
	 * @param fileName
	 */
	public void fileChanged(String fileName) {
		try {
			load(propFile);
		} catch (IOException e) {
			e.getMessage();
		}
	}

	public synchronized void setCheckPeriod() {

		// ���ε带 �ҷ��� �谹�� ���ε�ɼ��� false�� �� �� ���δ��� ��忡�� �Ʒ� �ڸ�Ʈ�� ���켼��.
		
//		try {
//			FileMonitor.getInstance().addFileChangeListener(this, propFile,
//					checkPeriod);
//		} catch (FileNotFoundException e) {
//			e.getMessage();
//		}
	}

	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		getInstance();

	}

}
