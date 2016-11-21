package fatca.common.util;

import java.io.UnsupportedEncodingException;

/**
 * <pre>
 * �ѱ� �ڵ�� ��ȯ�ϴ� Ŭ����
 * 
 * String msg = Hangul.convert(request.getParameter("msg"));
 * </pre>
 */

/**
 * @File Name    : Hangul.java
 * @Package Name : fatca.common.util;
 * @author       : 
 * @Description  : �ѱ� �ڵ�� ��ȯ�ϴ� Ŭ����
 * @History      : String msg = Hangul.convert(request.getParameter("msg"));
 */
public class FTCHangul {
	/**
	 * ASCII �ڵ带 �ѱ� �ڵ�� ��ȭ�Ѵ�.
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
	 * �ѱ� �ڵ带 ASCII �ڵ�� ��ȭ�Ѵ�.
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
	 * UTF�� Encoding�Ѵ�.
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
