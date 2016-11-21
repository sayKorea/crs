<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %> 

<script>
	
	function searchMonitor(){
		var f = document.formx;
		if(!isNull(f.slsBrno, '영업점') && !isNull(f.brnoCombo, '영업점')){
			f.mnuId.value = 'MON_R';
			f.action = "<cx:wc/>/acipMonitor.do?method=monitor";
			f.submit();		
			showLoading(true);
		}
	}
	
	function goExcel(){
		var f = document.formx;
		f.mnuId.value = 'MON_R';
		f.target = parent.document.getElementById("ifrmExcel").name;
		f.action = "<cx:wc/>/acipMonitor.do?method=monitorExcelDown";
		f.submit();
		showLoading(true);
		f.target = "";
		showLoading(false);
	}
	
	function __submit(){
		
		if('${sessionScope.sessionUser.mnuAuthMap.MON_R}'=='Y'){
			//searchMonitor();
		}
	}
	
	function searchBranch(){
		window.open("<cx:wc/>/.do?method=listPlan","","width=773,height=610,top=200,left=200,resizable=1");
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
		
		window.open("<cx:wc/>/code.do?method=searchPopEmp&dist="+dist+"&init=y&slsBrno="+f.slsBrno.value+"&popName="+popname,"pop","width=650,height=620,top=200,left=200");
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
		<li class="txt">FACTA 실사 현황 조회</li>
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
			<td></td>
		</tr>
		<tr>
			<td>
				<span>실사책임자</span>
			</td>
			<td>
				
				<input type="text" name="fatcaAcipRpprEnob" value="${sessionScope.sessionUser.selFatcaAcipRpprEnob}" maxlength="7" onChange="changeSearch('fatcaAcipRpprEnnm')" class="text enob" />
				<input type="text" name="fatcaAcipRpprEnnm" value="${sessionScope.sessionUser.selFatcaAcipRpprEnnm}" readonly disabled class="readonly ennm" />
				<a href="javascript:searchPopEmp('rppr')"><img src="<cx:wc/>/images/common/icon_search.gif"></a>
			</td>
			<td>
				<span>실사담당자</span>
			</td>
			<td>
				<input type="text" name="fatcaAcipCgpEnob" value="${sessionScope.sessionUser.selFatcaAcipCgpEnob}" maxlength="7" onChange="changeSearch('fatcaAcipCgpEnnm')" class="text enob"/>
				<input type="text" name="fatcaAcipCgpEnnm" value="${sessionScope.sessionUser.selFatcaAcipCgpEnnm}" size="7" readonly disabled class="readonly ennm" />
				<a href="javascript:searchPopEmp('cgp')"><img src="<cx:wc/>/images/common/icon_search.gif"></a>
			</td>
			<td>
				<c:if test="${sessionScope.sessionUser.mnuAuthMap.MON_R == 'Y'}">
					<a href="javascript:searchMonitor()" class="button search" >조회</a>
				</c:if>	
			</td>
		</tr>
		</tbody></table>
	</div></div>
	<!----- //Search ----->
	
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
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.MON_R == 'Y'}">
				<a href="javascript:goExcel()" class="basic" >엑셀</a>
			</c:if>
		</li>
	</ul>
	</div>
	<!----- //Title ----->

	<!----- /Grid ----->
		<display:table class="simple" name="${list}" size="${totalCnt}" id="monitorGrid" partialList="true" decorator="fatca.common.decorator.FTCTableFormat" 
			requestURI="/acipMonitor.do" pagesize="${requestScope.pagingsize}" sort="external" defaultsort="1" export="false" cellspacing="0">
			<display:column property="slsBrno" title="영업점<br>코드" style="text-align:center"/>
			<display:column property="slsBrnoNm" title="영업점" style="text-align:center"/>
			<display:column property="fatcaAcipRpprEnobNm" title="실사<br>책임자" style="text-align:center"/>	
			<display:column property="fatcaAcipCgpEnobNm" title="실사<br>담당자" style="text-align:center"/>				
			<display:column property="targetAcip" title="실사대상<br>고객수" style="text-align:right"/>
			<display:column property="comptAcip" title="실사완료<br>고객수" style="text-align:right"/>
			<display:column property="acipRate" title="진척률(%)" style="text-align:right"/>
			<display:column property="reportAcip" title="합계" groupTitle="보고대상" style="text-align:right"/>
			<display:column property="cprtAcip" title="협조" groupTitle="보고대상" style="text-align:right"/>
			<display:column property="noCprtAcip" title="비협조" groupTitle="보고대상" style="text-align:right"/>
			<display:column property="noReportAcip" title="비보고<br>대상" style="text-align:right"/>
			<display:column property="noDecisionAcip" title="보고<br>미확정" style="text-align:right"/>
		</display:table>
	
	<!----- //Grid ----->
	
</div>
</form>

</body>
</html>
