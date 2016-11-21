<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>

<script type="text/javascript">
	window.onload = function()
	{
		parent.document.ifrm.showLoading(false);
	}
      
</script>

<%
	String SRC = (String)request.getAttribute("fRealFilePath");
	String FILENAME = (String)request.getAttribute("fNewFileName");
	String CONTENT_TYPE = "application/octet-stream";
	
	System.out.println(SRC);
	System.out.println(FILENAME);
	System.out.println(CONTENT_TYPE);
		
	request.setCharacterEncoding("UTF-8");	
	request.setAttribute("SRC", new File(SRC));
	request.setAttribute("FILENAME",new String(FILENAME.getBytes(),"ISO8859_1"));
	request.setAttribute("CONTENT-TYPE", CONTENT_TYPE);
	pageContext.forward("/DownLoadServlet");
%>

<script language="javascript">
	parent.document.ifrm.showLoading(false);
</script>
