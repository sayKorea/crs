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
	 * String Ÿ��ĳ�����ϴ� ������ ���ֱ� ���� �߰���. 
	 * @param key
	 */
	public String getString(Object key);

	/**
	 * Int Ÿ��ĳ�����ϴ� ������ ���ֱ� ���� �߰���. 
	 * @param key
	 */
	public int getInt(Object key);

	/**
	 * Long Ÿ��ĳ�����ϴ� ������ ���ֱ� ���� �߰���. 
	 * @param key
	 */
	public long getLong(Object key);

	/**
	 * Floa Ÿ��ĳ�����ϴ� ������ ���ֱ� ���� �߰���. 
	 * @param key
	 */
	public float getFloat(Object key);

	/**
	 * Double Ÿ��ĳ�����ϴ� ������ ���ֱ� ���� �߰���. 
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