/**
 * 
 */
package crs.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author PMH
 *
 */
public class ResponseJson {
	/**
	 * @param request
	 * @param response
	 * @param obj
	 * @param charset
	 * @throws Exception
	 */
	public synchronized static void objectToJson(HttpServletRequest request, HttpServletResponse response, Object obj, String charset) throws Exception {
		PrintWriter out = null;
		try {
			 response.setContentType("text/html; charset=" + charset);
			 request.setCharacterEncoding(charset);
			response.setHeader("Cache-Control", "no-cache");
			out = response.getWriter();
		} catch (UnsupportedEncodingException e1) {
			try { out = response.getWriter(); } catch (IOException e) { e.printStackTrace(); }
		} catch (IOException e) { e.printStackTrace(); }
		out.print(obj);
		out.flush();
		out.close();
	}
}
