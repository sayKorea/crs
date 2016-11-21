<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 		prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tlds/kkl.tld" 					prefix="cx" %>

<meta http-equiv="Expires" CONTENT="-1">
<meta http-equiv="Pragma" CONTENT="no-cache">
<meta http-equiv="Cache-Control" CONTENT="no-cache">
<meta http-equiv="Content-Type" CONTENT="text/html; charset=EUC-KR">
<meta http-equiv="Imagetoolbar" CONTENT="no">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />

<title>CRS <c:if test="${sessionScope.sessionUser.serverType == 'dev'}">TEST</c:if></title>

<link rel="stylesheet" href="<cx:wc/>/css/base.css" />
<link rel="stylesheet" href="<cx:wc/>/css/contents.css" />
<link rel="stylesheet" href="<cx:wc/>/css/dotum.css" />
<link rel="stylesheet" href="<cx:wc/>/css/index.css" />
<link rel="stylesheet" href="<cx:wc/>/css/layout.css" />
<link rel="stylesheet" href="<cx:wc/>/css/crsBase.css" />

<script type="text/javascript" src="<cx:wc/>/js/angularjs/angular.min.js"></script>
<script type="text/javascript" src="<cx:wc/>/js/jquery/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="<cx:wc/>/js/jquery/jquery-ui.min.js"></script>
<script type="text/javascript" src="<cx:wc/>/js/jquery/spin.min.js"></script>

<script type="text/javascript" src="<cx:wc/>/js/crs_common.js"></script>

<script type="text/javascript" src="<cx:wc/>/js/ftc_common.js"></script>
<script type="text/javascript" src="<cx:wc/>/js/ftc_Ba2CalPop.js"></script>
<script type="text/javascript" src="<cx:wc/>/js/ftc_validate.js"></script>
<script type="text/javascript" src="<cx:wc/>/js/ftc_Charts.js"></script>
<script type="text/javascript" src="<cx:wc/>/js/ftc_function.js"></script>

<cx:base />
<cx:debug category="fatca.jsp" message=">>> JSP start "/>
<div id='loading'></div>