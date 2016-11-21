<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/kkl.tld" prefix="cx" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<META HTTP-EQUIV="Expires" CONTENT="-1">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta http-equiv="Imagetoolbar" content="no">
<title>제주은행-FATCA <c:if test="${sessionScope.sessionUser.serverType == 'dev'}">TEST</c:if></title>

<link rel="stylesheet" href="<cx:wc/>/css/base.css" />
<link rel="stylesheet" href="<cx:wc/>/css/contents.css" />
<link rel="stylesheet" href="<cx:wc/>/css/dotum.css" />
<link rel="stylesheet" href="<cx:wc/>/css/index.css" />
<link rel="stylesheet" href="<cx:wc/>/css/layout.css" />


<script type="text/javascript" src="<cx:wc/>/js/ftc_common.js"></script>
<script type="text/javascript" src="<cx:wc/>/js/ftc_common2.js"></script>
<script type="text/javascript" src="<cx:wc/>/js/ftc_Ba2CalPop.js"></script>
<script type="text/javascript" src="<cx:wc/>/js/ftc_validate.js"></script>
<script type="text/javascript" src="<cx:wc/>/js/ftc_Charts.js"></script>
<script type="text/javascript" src="<cx:wc/>/js/ftc_function.js"></script>

<script>

var cx_wc = "<cx:wc/>";
</script>


<cx:base />
<cx:debug category="fatca.jsp" message=">>> JSP start "/>
<div id="LOADING_BARS" style="display:none; position:absolute; text-align:center; margin:0; top:0; left:0; auto; z-index:9999;">
<IMG src="<cx:wc/>/images/loading.gif">
</div>