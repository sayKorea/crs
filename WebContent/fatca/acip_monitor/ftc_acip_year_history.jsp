<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %> 

<script>
	
	function searchYear(){
		var f = document.formx;
		f.mnuId.value = 'YEAR_R';
		f.action = "<cx:wc/>/acipMonitor.do?method=year";
		f.submit();		
		showLoading(true);
	}
	
	function goExcel(){
		var f = document.formx;
		f.mnuId.value = 'YEAR_R';
		f.target = parent.document.getElementById("ifrmExcel").name;
		f.action = "<cx:wc/>/acipMonitor.do?method=yearExcelDown";
		f.submit();
		showLoading(true);
		f.target = "";
		showLoading(false);
	}
	
	function __submit(){
		if('${sessionScope.sessionUser.mnuAuthMap.YEAR_R}'=='Y'){
			//searchYear();
		}
	}	

</script>
</head>
<body onLoad="init()">
    
<form name="formx" method="post">
<input type="hidden" name="mnuId">
<div id="subMain">
	<!----- /Title ----->
	<div class="top_title">
	<ul>
		<li class="txt">FACTA 연도별 실사이력 조회</li>
	</ul>
	</div>
	<!----- /Search ----->
	<div class="sheet_search"><div>
		<table><tbody>
		<tr>
			<td><span>기준년월</span></td>
			<td>
				<cx:cselect dbname="crymStCombo" name="crymSt" selected="${param.crymSt}" style="width:85px" />
			</td>
			<td>
				~
			</td>
			<td>
				<cx:cselect dbname="crymEndCombo" name="crymEnd" selected="${param.crymEnd}" style="width:85px" />
			</td>
			<td><span>FATCA 고객 구분</span></td>
			<td>
				<cx:cselect dbname="custDvcdCombo" name="fatcaCustDvcd" selected="${param.fatcaCustDvcd}" before=",전체" style="width:85px" />
			</td>
			<td>			
				<c:if test="${sessionScope.sessionUser.mnuAuthMap.YEAR_R == 'Y'}">
					<a href="javascript:searchYear()" class="button search" >조회</a>
				</c:if>
			</td>
		</tr>
		</tbody></table>
	</div></div>
	<!----- //Search ----->
	
	<!----- /Title ----->
	<div class="sheet_title">
	<ul>
		<li class="btn">
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.YEAR_R == 'Y'}">
				<a href="javascript:goExcel()" class="basic" >엑셀</a>
			</c:if>
		</li>
	</ul>
	</div>
	
    <c:choose>
    <c:when test="${empty list}"><c:set var="totalCnt" value="0"></c:set></c:when>
    <c:otherwise><c:set var="totalCnt" value="${list[0].totalCnt}"></c:set></c:otherwise>
    </c:choose>

	
	<!----- //Title ----->

	<!----- /Grid ----->
		<display:table class="simple" name="${list}" size="${totalCnt}" id="grid" partialList="true" decorator="fatca.common.decorator.FTCTableFormat"
			requestURI="/acipMonitor.do" pagesize="0" sort="external" defaultsort="1" export="false" cellspacing="0">
			<display:column property="crym" title="기준년도" style="text-align:center;width:20%"/>	
			<display:column property="targetAcip" title="실사대상<br>고객수" style="text-align:right;width:15%"/>	
			<display:column property="reportAcip" title="합계" groupTitle="보고대상" style="text-align:right;width:15%"/>
			<display:column property="cprtAcip" title="협조" groupTitle="보고대상" style="text-align:right;width:15%"/>
			<display:column property="noCprtAcip" title="비협조" groupTitle="보고대상" style="text-align:right;width:15%"/>
			<display:column property="noReportAcip" title="비보고대상" style="text-align:right;width:15%"/>
		</display:table>
	
	<!----- //Grid ----->
	
</div>
</form>

</body>
</html>
