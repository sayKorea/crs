<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE HTML>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %> 

<script>
	
	function search(){
		var f = document.formx;
		if(!isNull(f.slsBrno, '영업점') && !isNull(f.brnoCombo, '영업점')){
			f.mnuId.value = 'MBR_R';
			f.action = "<cx:wc/>/acipResult.do?method=mainComplete";
			f.submit();
			showLoading(true);
		}
	}

	function goExcel(){
		var f = document.formx;
		f.mnuId.value = 'MBR_R';
		f.target = parent.document.getElementById("ifrmExcel").name;
		f.action = "<cx:wc/>/acipResult.do?method=mainExcelDown";
		f.submit();
		showLoading(true);
		f.target = "";
		showLoading(false);
	}
	
	function __submit(){
		if('${sessionScope.sessionUser.mnuAuthMap.MBR_R}'=='Y'){
			search();
		}
	}
		
	function goAction(dist){
		var f = document.formx;	
		var conMsg = '실사 완료 되면 해당 기준년월의 모든 입력작업이 차단됩니다.\n단, 필요시 취소가 가능합니다.\n진행하시겠습니까?';
		if(dist=='N'){
			conMsg = '완료가 취소되면 차된되었던 입력작업이 가능합니다.\n진행하시겠습니까?';
		}
		if(confirm(conMsg)){
			f.mnuId.value = 'MBR_U';
			f.action = "<cx:wc/>/acipResult.do?method=saveMainComplete&dist="+dist;
			f.submit();
			showLoading(true);
		}
	}
	
	function searchPopSls(){
		var f = document.formx;
		window.open("<cx:wc/>/code.do?method=searchPopSls&init=y","pop","width=650,height=620,top=200,left=200");			
	}	

	function searchPopEmp(dist){
		var f = document.formx;
		if(dist == 'cgp'){		
			popname="실사담당자";
		}else if(dist == 'rppr'){		
			popname="실사책임자";
		}else if(dist == 'rel'){		
			popname="고객관계관리자";
		}
		
		window.open("<cx:wc/>/code.do?method=searchPopEmp&slsBrno="+f.slsBrno.value+"&dist="+dist+"&init=y&popName="+popname,"pop","width=650,height=620,top=200,left=200");
	}	
	
	function init(){
		var f = document.formx;
		var msg = '${msg}';
		if(msg != ''){
			alert(msg);
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
		<li class="txt">본점 실사완료</li>
	</ul>
	</div>
	<!----- /Search ----->
	<div class="sheet_search"><div>
		<table><tbody>
		<tr>
			<td><span>영업점</span></td>
			<td>
				<c:if test="${sessionScope.sessionUser.isFatca}">
					<input type="text" name="slsBrno" value="${sessionScope.sessionUser.selSlsBrno}" onFocus="this.select()" maxlength="3" onChange="changeSearch2('tex');" class="text brno"/>
					<cx:cselect dbname="branchCombo" name="brnoCombo" selected="${sessionScope.sessionUser.selSlsBrno}" before=",해당 지점 없음" after="999,[999] 은행합계" onchange="changeSearch2('com');" style="width:192px" />
					
				</c:if> 
				<c:if test="${!sessionScope.sessionUser.isFatca}">
					<input type="text" name="slsBrno" value="${sessionScope.sessionUser.selSlsBrno}" maxlength="3" readonly class="readonly brno"/>
					<input type="text" name="slsBrnm" value="${sessionScope.sessionUser.selSlsBrnm}" readonly disabled class="readonly brnm" />
				</c:if>
			</td>
			<td><span>기준년월</span></td>
			<td>
				<cx:cselect dbname="crymCombo" name="crym" selected="${sessionScope.sessionUser.selCrym}" style="width:85px" />
			</td>
			<td>
				<%-- <c:if test="${sessionScope.sessionUser.mnuAuthMap.MBR_R == 'Y'}"> --%>
					<a href="javascript:search()" class="button search" >조회</a>
				<%-- </c:if> --%>
			</td>
		</tr>
		</tbody></table>
	</div></div>
	<!----- //Search ----->
	<div></div>
	
	<tr><td>&nbsp;</td></tr>
	
	<table class="tableWrap2" >
	<tbody>
	<colgroup>
		<col width="50%"><col width="50%">
	</colgroup>
	<tr>
		<td class="pR10p">

			<!----- /Table ----->
			<table class="table"><tbody>
			<colgroup>
				<col width="20%"><col width="30%">
			</colgroup>
			<tr>
				<th>전체고객수 (완료/대상)</th>
				<td class="center">
					${info.ttotComp} / ${info.ttotCust} (명)
				</td>
			</tr>
			<tr>
				<th>개인고객수 (완료/대상)</th>
				<td class="center">
					${info.tperComp} / ${info.tperCust} (명)
				</td>
			</tr>
			<tr>
				<th>법인고객수 (완료/대상)</th>
				<td class="center">
					${info.tcomComp} / ${info.tcomCust} (명)
				</td>
			</tr>
			<tbody></table>
			<!----- //Table ----->

		</td>
		<td class="pL10p">

			<!----- /Table ----->
			<table class="table"><tbody>
			<colgroup>
				<col width="20%"><col width="30%">
			</colgroup>
			<tr>
				<th>완료담당자</th>
				<td class="center">
					${info.cgpEnobNm}
				</td>
			</tr>
			<tr>
				<th>완료일자</th>
				<td class="center">
					${info.clsnDt}&nbsp;
				</td>
			</tr>
			<tr>
				<th>실사기준일자</th>
				<td class="center">
					${info.fatcaAcipCrdt}
				</td>
			</tr>
			<tbody></table>
			<!----- //Table ----->

		</td>
	</tr>
	</tbody></table>
	
	
	
    <c:choose>
    <c:when test="${empty list}"><c:set var="totalCnt" value="0"></c:set></c:when>
    <c:otherwise><c:set var="totalCnt" value="${list[0].totalCnt}"></c:set></c:otherwise>
    </c:choose>
	
	<!----- /Title ----->
	<div class="sheet_title">
	<ul>
		<li class="txt">
			<span>[ 총 ${totalCnt} 건 ]</span>			
		</li>		
		<li class="btn">
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.MBR_U == 'Y'}">
				<c:if test="${isMainComplete != 'Y'}">
					<a href="javascript:goAction('Y')" class="basic">실사완료</a>
				</c:if>
				<c:if test="${isMainComplete == 'Y'}">
					<a href="javascript:goAction('N')" class="basic">완료취소</a>
				</c:if>
			</c:if>
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.MBR_R == 'Y'}">
				<a href="javascript:goExcel()" class="basic" >엑셀</a>
			</c:if>
		</li>
	</ul>
	</div>
	<!----- //Title ----->
	

	<!----- /Grid ----->
		<display:table class="simple" name="${list}" size="${totalCnt}" id="grid" partialList="true" decorator="fatca.common.decorator.FTCTableFormat"
			requestURI="/acipResult.do" pagesize="13" sort="external" defaultsort="1" export="false" cellspacing="0">
			<display:column property="slsBrno" title="영업점<br>코드" style="text-align:center"/>
			<display:column property="slsBrnoNm" title="영업점" maxLength="5" style="text-align:center"/>
			<display:column property="slsStatus" title="진행상태" style="text-align:center"/>
			<display:column property="fatcaAcipRpprEnobNm" title="실사<br>책임자" style="text-align:center"/>
			<display:column property="totCust" title="총고객" groupTitle="전체고객" style="text-align:right"/>
			<display:column property="totComp" title="실사<br>완료" groupTitle="전체고객" style="text-align:right"/>
			<display:column property="totUncomp" title="실사<br>미완료" groupTitle="전체고객" style="text-align:right"/>
			<display:column property="totReport" title="보고<br>대상" groupTitle="전체고객" style="text-align:right"/>
			<display:column property="comCust" title="총고객" groupTitle="법인고객" style="text-align:right"/>
			<display:column property="comComp" title="실사<br>완료" groupTitle="법인고객" style="text-align:right"/>
			<display:column property="comUncomp" title="실사<br>미완료" groupTitle="법인고객" style="text-align:right"/>
			<display:column property="comReport" title="보고대상" groupTitle="법인고객" style="text-align:right"/>
			<display:column property="perCust" title="총고객" groupTitle="개인고객" style="text-align:right"/>
			<display:column property="perComp" title="실사<br>완료" groupTitle="개인고객" style="text-align:right"/>
			<display:column property="perUncomp" title="실사<br>미완료" groupTitle="개인고객" style="text-align:right"/>
			<display:column property="perReport" title="보고<br>대상" groupTitle="개인고객" style="text-align:right"/>
		</display:table>
	
	<!----- //Grid ----->
	
</div>
</form>

</body>
</html>
