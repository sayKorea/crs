package fatca.common.util;

import java.io.UnsupportedEncodingException;

/**
 * <pre>
 * 한글 코드로 변환하는 클래스
 * 
 * String msg = Hangul.convert(request.getParameter("msg"));
 * </pre>
 */

/**
 * @File Name    : Hangul.java
 * @Package Name : fatca.common.util;
 * @author       : 
 * @Description  : 한글 코드로 변환하는 클래스
 * @History      : String msg = Hangul.convert(request.getParameter("msg"));
 */
public class FTCHangul {
	/**
	 * ASCII 코드를 한글 코드로 변화한다.
	 * 
	 * @param args
	 * @return String
	 */
	public static String convert(String args) {
	  try {
		String convertString = new String(args.getBytes("8859_1"), "KSC5601");

		return convertString;
	  }
	  catch (NullPointerException e) {
		e.toString();
	  }
	  catch (UnsupportedEncodingException e) {
	  	e.toString();
	  }

	  return null;
	}
	
	/**
	 * 한글 코드를 ASCII 코드로 변화한다.
	 * @param args
	 * @return
	 */
	public static String deconvert(String args) {
	  try {
		String convertString = new String(args.getBytes("euc-kr"), "8859_1");

		return convertString;
	  } catch (NullPointerException e) {
		e.toString();
	  } catch (UnsupportedEncodingException e) {
	  	e.toString();
	  }

	  return null;
	}	
	
	/**
	 * UTF로 Encoding한다.
	 * @param args
	 * @return
	 */
    public static String convertUTF(String args)
    {
        try
        {
            String convertString = new String(args.getBytes("8859_1"), "UTF-8");
            return convertString;
        }
        catch(NullPointerException e)
        {
            e.toString();
        }
        catch(UnsupportedEncodingException e)
        {
            e.toString();
        }
        return null;
    }	

}
