package fatca.common;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import fatca.common.util.FTCStringUtil;

/**
 * 
 * @author U200186
 *
 */
public class DownLoadServlet extends HttpServlet {

	private Log log = LogFactory.getLog(this.getClass());

	// //excel Content-Type : application/vnd.ms-excel

	/**
	 * default Content-Type
	 */
	private final static String DEFAULT_CONTENT_TYPE = "application/octet-stream";

	private String m_strEncoding = null;

	// private String m_strCharset = null;

	public DownLoadServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		m_strEncoding = config.getInitParameter("encoding");
		// m_strCharset = config.getInitParameter("charset");
	}

	/**
	 * HttpServlet.service().
	 * 
	 * @param req
	 *            javax.servlet.http.HttpServletRequest
	 * @param res
	 *            javax.servlet.http.HttpServletResponse
	 * @exception javax.servlet.ServletException
	 */
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException {
		try {
			Object src = req.getAttribute("SRC");

			if (src == null)
				throw new ServletException("SRC attribute can not be null (java.io.File)...");

			// SRC : java.io.File (mandatory)
			// FILENAME : file name ( optional, default = SRC.getName() )
			// CONTENT-TYPE : mime type (optional, default =
			// application/octet-stream)
			else if (src instanceof File) {
				download(res, (File) src, (String) req.getAttribute("FILENAME"), (String) req
						.getAttribute("CONTENT-TYPE"));
			}

			// SRC : java.io.InputStream (mandatory)
			// FILENAME : file name (mandatory)
			// CONTENT-TYPE : mime type (optional, default =
			// application/octet-stream)
			else if (src instanceof InputStream) {
				String filename = (String) req.getAttribute("FILENAME");
				if (filename == null)
					throw new ServletException("FILENAME attribute can not be null (java.io.InputStream)...");

				download(res, (InputStream) src, (String) req.getAttribute("FILENAME"), (String) req
						.getAttribute("CONTENT-TYPE"));
			}

			// SRC : java.lang.String (mandatory, csv/xls string)
			// FILENAME : file name (mandatory)
			// CONTENT-TYPE : mime type (optional, default =
			// application/octet-stream)
			else if (src instanceof String) {
				String filename = (String) req.getAttribute("FILENAME");
				if (filename == null)
					throw new ServletException("FILENAME attribute can not be null (java.lang.String)...");

				download(res, (String) src, (String) req.getAttribute("FILENAME"), (String) req
						.getAttribute("CONTENT-TYPE"));
			}

			
			else
				throw new ServletException("Unsupported SRC type..");
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
	}

	/**
	 * Download the file with the designed file name and Content-Type from a
	 * designed <code>InputStream</code>.
	 * 
	 * @param res
	 *            javax.servlet.http.HttpServletResponse or response (at jsp)
	 * @param src
	 *            the <code>java.io.InputStream</code> for the source file
	 * @param filename
	 *            the file name to be downloaded
	 * @param contentType
	 *            Content-Type. If the <code>contentType</code> is
	 *            <code>null</code>, use the default value(<code>application/octet-stream</code>)
	 * @exception java.io.IOException
	 *                download failed.
	 */
	private void download(HttpServletResponse res, InputStream src, String filename, String contentType)
			throws IOException {
		ServletOutputStream out = null;
		try {
			if (m_strEncoding != null){
				//filename = new String(filename.getBytes(m_strEncoding), "8859_1");
			}    
			if (contentType == null) {
				contentType = getServletContext().getMimeType(filename);
				if (contentType == null)
					contentType = DEFAULT_CONTENT_TYPE;
			}
			
			log.debug(m_strEncoding + " : " + filename);

			out = res.getOutputStream();
			res.setCharacterEncoding("UTF-8");
			res.reset();
			res.setContentType(contentType);
			res.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(filename, "utf-8")  + "\"");

			int readByte;
			byte buf[] = new byte[1024];

			while ((readByte = src.read(buf)) != -1) {
				out.write(buf, 0, readByte);
			}

			out.flush();
			res.setStatus(HttpServletResponse.SC_NO_CONTENT);
			
			log.debug(filename.endsWith(".xls"));
			
			boolean isExcel = filename.endsWith(".xls");
			
			log.debug(" [ isExcel ] "+ isExcel);
			
			if(isExcel == true){
				String filePath = FTCConstants.USER_DOWNLOAD;
				filePath = FTCStringUtil.replace(filePath, "\\", "/");
				File delFile = new File(filePath+filename);
				
				log.debug(" [ filePath+filename ] "+filePath+filename);
				if(delFile.isFile()){ //다운받은 파일 지우기
					delFile.delete();
					log.debug(" 파일을 삭제하였습니다. "+filePath+filename);
				}
			}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
		
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * Download a designed <code>String</code> with the designed filename and
	 * <code>Content-Type</code>
	 * 
	 * @param res
	 *            javax.servlet.http.HttpServletResponse or response (at jsp).
	 * @param src
	 *            the source <code>String</code> (special charactor separated
	 *            string).
	 * @param filename
	 *            the file name to be downloaded.
	 * @param contentType
	 *            Content-Type. If the <code>contentType</code> is
	 *            <code>null</code>, use the default value(<code>application/octet-stream</code>)
	 * @exception java.io.IOException
	 *                download failed.
	 */
	private void download(HttpServletResponse res, String src, String filename, String contentType) throws IOException {
		ByteArrayInputStream in = null;
		try {
			in = new ByteArrayInputStream(src.getBytes());
			download(res, in, filename, contentType);
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * Download a designed file with the designed file name and
	 * <code>Content-Type</code>
	 * 
	 * @param res
	 *            javax.servlet.http.HttpServletResponse or response (at jsp).
	 * @param src
	 *            the sourece <code>java.io.File</code> Object
	 * @param filename
	 *            the file name to be downloaded, if the <code>filename</code>
	 *            is <code>null</code>, use the file name to be retrieved
	 *            from the <code>File.getName()</code>
	 * @param contentType
	 *            Content-Type. If the <code>contentType</code> is
	 *            <code>null</code>, use the default value(<code>application/octet-stream</code>)
	 * @exception java.io.IOException
	 *                download failed.
	 */
	private void download(HttpServletResponse res, File src, String filename, String contentType) throws IOException {
		FileInputStream in = null;
		try {
			in = new FileInputStream(src);
			download(res, in, (filename == null ? src.getName() : filename), contentType);
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
			}
		}
	}
}
