package fatca.common.util;

import java.util.Map;

/**
 * @File Name    : DbMap.java
 * @Package Name : fatca.common.util;
 * @author       : 
 * @Description  : DbMap
 * @History      : 
 */
public interface FTCDbMap  {
	/**
	 * @see java.util.Map#containsKey(java.lang.Object)
	 * 
	 * @param key
	 * @return boolean
	 */
	public boolean containsKey(Object key);

	/**
	 * @see java.util.Map#get(java.lang.Object)
	 * 
	 * @param key
	 */
	public Object get(Object key);

	/**
	 * String 타입캐스팅하는 불편을 엾애기 위해 추가함. 
	 * @param key
	 */
	public String getString(Object key);

	/**
	 * Int 타입캐스팅하는 불편을 엾애기 위해 추가함. 
	 * @param key
	 */
	public int getInt(Object key);

	/**
	 * Long 타입캐스팅하는 불편을 엾애기 위해 추가함. 
	 * @param key
	 */
	public long getLong(Object key);

	/**
	 * Floa 타입캐스팅하는 불편을 엾애기 위해 추가함. 
	 * @param key
	 */
	public float getFloat(Object key);

	/**
	 * Double 타입캐스팅하는 불편을 엾애기 위해 추가함. 
	 * @param key
	 */
	public double getDouble(Object key);

	/**
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 * @param key
	 * @param value
	 * 
	 * @return put
	 */
	public Object put(Object key, Object value);

	/**
	 * @see java.util.Map#putAll(java.util.Map)
	 * 
	 * @param m
	 */
	public void putAll(Map m);

	/**
	 * @see java.util.Map#remove(java.lang.ObjecT)
	 * 
	 * @param key
	 * @return Object
	 */
	public Object remove(Object key);
}