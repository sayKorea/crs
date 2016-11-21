package fatca.common.util;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import oracle.sql.CLOB;


/**
 * @File Name    : CiHashMap.java
 * @Package Name : fatca.common.util;
 * @author       : 
 * @Description  : CiHashMap 정보
 * @History      : 
 */
public class FTCCiHashMap extends HashMap implements FTCDbMap {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2842877277470561984L;

	/**
	 * java.util.Map#containsKey(java.lang.Object)
	 * 
	 * @param key
	 * @return containsKey 정보
	 */
	public boolean containsKey(Object key) {
		return super.containsKey(key.toString().toLowerCase());
	}

	/**
	 * java.util.Map#get(java.lang.Object)
	 * 
	 * @param key
	 * @return key 정보
	 */
	public Object get(Object key) {
		 
		Object obj = super.get(key.toString().toLowerCase());
		if(obj instanceof CLOB){
			obj = getStringForCLOB((CLOB)obj);
		}
		return obj;
	}

	/**
	 * 아래 메소드들은 타입태스팅하는 불편을 엾애기 위해 추가함.
	 * 
	 * @param key
	 * @return ""
	 */
	public String getString(Object key) {
		
		Object obj = super.get(key.toString().toLowerCase());
		if ( obj !=null) {
			return obj.toString();
		}
		
		return "";
	}

	/**
	 * Object형식에 int 형 key값 가져오기
	 * 
	 * @param key
	 * @return int형 정보
	 */
	public int getInt(Object key) {
		Object op = super.get(key.toString().toLowerCase());
		if (op instanceof BigDecimal) {
			return ((BigDecimal) (op)).intValue();
		} else if (op instanceof Integer) {
			return ((Integer) (op)).intValue();
		} else {
			return 0;
		}
	}

	/**
	 * Object형식에 Long형 key값 가져오기
	 * 
	 * @param key
	 * @return Long형 정보
	 */
	public long getLong(Object key) {
		Object op = super.get(key.toString().toLowerCase());
		if (op instanceof BigDecimal) {
			return ((BigDecimal) (op)).longValue();
		} else if (op instanceof Long) {
			return ((Long) (op)).longValue();
		} else {
			return 0;
		}

	}
	
	/**
	 * Object형식에 Float형 key값 가져오기
	 * 
	 * @param key
	 * @return Float형 정보
	 */
	public float getFloat(Object key) {
		Object op = super.get(key.toString().toLowerCase());
		if (op instanceof BigDecimal) {
			return ((BigDecimal) (op)).floatValue();
		} else if (op instanceof Float) {
			return ((Float) (op)).floatValue();
		} else {
			return 0;
		}
	}

	/**
	 * Object형식에 Double형 key값 가져오기
	 * 
	 * @param key
	 * @return Double형 정보
	 */
	public double getDouble(Object key) {
		Object op = super.get(key.toString().toLowerCase());
		if (op instanceof BigDecimal) {
			return ((BigDecimal) (op)).doubleValue();
		} else if (op instanceof Double) {
			return ((Double) (op)).doubleValue();
		} else {
			return 0;
		}

	}

	/**
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 * Object형식에 put 변수 설정
	 * 
	 * @param key
	 * @param value
	 * @return Object형식에 put 정보
	 */
	public Object put(Object key, Object value) {
		
	
		return super.put(FTCStringUtil.removeCharacter(key.toString().toLowerCase().trim(),'_'), value);
	}


	/**
	 * @see java.util.Map#putAll(java.util.Map)
	 * Object형식에 putAll 변수 설정
	 * 
	 * @param m
	 */
	public void putAll(Map m) {
		Iterator iter = m.keySet().iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			Object value = m.get(key);
			this.put(key, value);
		}
	}

	/**
	 * @see java.util.Map#remove(java.lang.ObjecT)
	 * Object형식에 제거
	 * 
	 * @param key
	 * @return Object형식 제거
	 */
	public Object remove(Object key) {
		return super.remove(key.toString().toLowerCase());
	}
	
	/**
	 * @see java.util.Map#remove(java.lang.ObjecT)
	 * CLOB에서 String 형식으로 변환
	 * 
	 * @param clob
	 * @return String 형식 가져오기
	 */
	public static String getStringForCLOB(CLOB clob) {
        
        StringBuffer sbf = new StringBuffer();
        Reader br = null;
        char[] buf = new char[1024];
        int readcnt;

        try {
            br = clob.getCharacterStream(0L);

            while ((readcnt=br.read(buf,0,1024))!=-1) {
                sbf.append(buf,0,readcnt);
            }

        } catch (Exception e) {
           e.getMessage();
        }finally{
            if(br!=null)
            {
                try {
                    br.close();
                } catch (IOException e) {
                	e.getMessage();
               }
            }
        }
        return sbf.toString();
    } 
	
	/**
	 * @see java.util.Map#remove(java.lang.ObjecT)
	 * String형식에서 String 형식으로 체크
	 * 
	 * @return str 형식 체크
	 * @throws Exception
	 */
	public String toStringCheck() throws Exception {
		String str = "";
		StringBuffer sfstr = new StringBuffer();		
		Iterator it = super.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			Object value = super.get(key);
			String op="";
			StringBuffer sf = new StringBuffer();
			if( value instanceof String[]){
				for( int r = 0; r < ((String[])value).length ; r++){
					//op += ((String[])value)[r];
					sf.append(((String[])value)[r]);
					op  = sf.toString();
				}
			}
			else{
			    //op = value.toString();
				op = value.toString();
			}
			int p = op.indexOf('\'');
			if( p >= 0) {
				throw new Exception("입력필드에 ' 를 사용할수 없습니다.");
			}
			//str += "["+key +" > "+ op +"]";
			sfstr.append("["+key +" > "+ op +"]");
			str  = sfstr.toString();
		}
		
		return str;
	}

	/**
	 * @see java.util.Map#remove(java.lang.ObjecT)
	 * String에서 String 형식으로 가져오기
	 * 
	 * @return String 형식 가져오기
	 */
	public String toString()  {
		String str = "";
		StringBuffer sfstr = new StringBuffer();
		Iterator it = super.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			Object value = super.get(key);
			String op="";
			StringBuffer sf = new StringBuffer();
			if( value instanceof String[]){
				for( int r = 0; r < ((String[])value).length ; r++){
					//op += ((String[])value)[r];
					sf.append(((String[])value)[r]);
					op  = sf.toString();
				}
			}else{
				if(value == null){
					value = "값이 없습니다.";
				}
			    op = value.toString();
			}
			//str += "["+key +" > "+ op +"]";
			sfstr.append("["+key +" > "+ op +"]");
			str  = sfstr.toString();
		}
		
		return str;
	}

}