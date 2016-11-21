<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %> 

<script language="JavaScript">
		
	function inputCheck(){
		var f = document.formx;
		
		if (f.queryStr.value == '') {
			alert('실행할 쿼리를 입력하세요.');
			f.queryStr.focus();
			return false;
		}
		
		return true;
	}
		
	function execQuery(dist) {
		var f = document.formx;
		
		if(inputCheck()){
			f.action = "<cx:wc/>/code.do?method=execQuery&dist="+dist;
			f.submit();
		}
		
	}

	
	function __submit(){
		execQuery();
	}
	
	function init(){
		
	}
		
	
</script>

</head>
<body onLoad="init()">      
<form name="formx" method="post">
<div id="subMain">
	
	<!----- /Search ----->
	<!----- /Title ----->
	<div class="top_title">
	<ul>
		<li class="txt">Execute Query</li>
	</ul>
	</div>
		
	
	<!----- /Title ----->
	<div class="sheet_title">
	<ul>		
		<li class="btn">
		<c:if test="${sessionScope.sessionUser.isAdmin}">
			<a href="javascript:execQuery('S')" class="basic">SELECT</a>
			<a href="javascript:execQuery('I')" class="basic">INSERT</a>
		</c:if>
		</li>
	</ul>	
	</div>
	
	<!----- /Grid ----->
				
		<table id="ingrid" class="simple" cellspacing="0">
		<tr>
		<th style="width:15%">Query</th>
		</tr>
		<tr class="odd">
		<td style="text-align:center">
			<textarea name="queryStr" rows="25" cols="149">${param.queryStr}</textarea>
		</td>
		</table>
		
    <c:choose>
    <c:when test="${empty list}"><c:set var="totalCnt" value="0"></c:set></c:when>
    <c:otherwise><c:set var="totalCnt" value="${list[0].totalCnt}"></c:set></c:otherwise>
    </c:choose>
		
		
		<!----- /Grid ----->
		<display:table class="simple" name="${list}" size="${totalCnt}" id="grid" partialList="true" 
			requestURI="/code.do" pagesize="0" sort="external" defaultsort="1" export="false" cellspacing="0">
			<c:forEach items="${headerList}" var="hea">
			<display:column property="${hea.hname}" title="${hea.hname}" style="text-align:center"/>
			</c:forEach>
		</display:table>
	
	<!----- //Grid ----->
	
</div>
</form>

</body>
</html>
