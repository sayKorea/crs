<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %>
<script language="JavaScript">
	function searchTarget2(){
		var f = document.formx;
		if(inputCheck()){
			f.action = "<cx:wc/>/code.do?method=searchPopCust&isListPage=Y";
			f.submit();
			showLoading(true);
		}
	}
		
	function __submit(){
		//searchTarget2();
	}
	
	function sclose(){
		self.close();
	}
	

	function select(csno, csnm, rnno, regdt){
		
		var f = document.formx;
		
		if(f.dist.value=="add"){
			opener.addRow(f.crym.value, csno, csnm, rnno, regdt);
		}else{
			var opn = opener.document.formx;
			opn.csno.value = csno;
			opn.custNm.value = csnm;				
		}
		sclose();
		
	}
	
	function inputCheck(){
		var f = document.formx;
		if(f.csno.value == '' && f.csnm.value == '' ){
			alert('고객번호 또는 고객명을 입력하세요');
			return false;
		}
		return true;
	}
	
	function init(){
		//pop_search_copr.jsp로 통일하나 동일인 관리는 사용
		self.window.focus();
	}
</script>			
	
</head>
<body onload="init()">

   
<form name="formx" method="post">
<input type="hidden" name="dist" value="${param.dist}">
<input type="hidden" name="crym" value="${param.crym}">
<div class="popWrap">
<!----- /Title ----->
<div class="popTop">
	<ul>
		<li class="left">
			고객 추가
		</li>
		<li class="right">
			<div class="btn">
				<a href="javascript:sclose();"><img src="<cx:wc/>/images/common/btn_pop_close.gif"/></a>
			</div>
		</li>
	</ul>
</div>
<!----- //Title ----->

<!----- /Contents ----->
<div class="popContentWrap">
	
	<div id="subMain">
	<!----- /Search ----->
	<div class="sheet_search"><div>
		<table><tbody>
		<tr>
			<td><span>고객번호</span></td>
			<td>
				<input type="text" name="csno" value="${param.csno}" maxlength="13" class="text" />
			</td>
			<td><span>고객명</span></td>
			<td>
				<input type="text" name="csnm" value="${param.csnm}" maxlength="30" class="text" style="ime-mode:active"/>
			</td>
			<td>
				<a href="javascript:searchTarget2()" class="button search" >조회</a>
			</td>
		</tr>
		</tbody></table>
	</div></div>
	<!----- //Search ----->
	</div>

	<!----- /Title ----->
	<div class="sheet_title">
	<ul>
		<li class="txt"></li>
		<li class="btn"></li>	
	</ul>
	</div>
	<!----- //Title ----->
	
    <c:choose>
    <c:when test="${empty list}"><c:set var="totalCnt" value="0"></c:set></c:when>
    <c:otherwise><c:set var="totalCnt" value="${list[0].totalCnt}"></c:set></c:otherwise>
    </c:choose>

	<!----- /Table ----->
	<display:table class="simple" name="${list}" size="${totalCnt}" id="grid" partialList="true" decorator="fatca.common.decorator.FTCTableFormat" 
			requestURI="/code.do" pagesize="${requestScope.pagingsize}" sort="external" defaultsort="1" export="false" cellspacing="0">
			<display:column property="csno" title="고객번호" style="text-align:center; width:10%"/>	
			<display:column title="고객명" style="text-align:center; width:20%">	
				<a href="javascript:select('${grid.csno}','${grid.custNm}','${grid.rnno}','${grid.regDtlFmt}' );">${grid.custNm}</a>
			</display:column>	
			<display:column property="rnno" title="실명번호" style="text-align:center; width:35%"/>	
		</display:table>
	
	<!----- //Table ----->

</div>
<!----- //Contents ----->
	
<!----- /Footer ----->
<!-- div class="popFootWrap">
	<table><tbody>
	<tr>
		<td>
			<a href="javascript:close()" class="large gray">닫기</a>
		</td>
	</tr>
	</tbody></table>
</div -->

</form>

</body>
</html>
