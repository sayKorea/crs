<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html >
<html>
<head> 
<meta http-equiv="X-UA-Compatible" content="IE=8">
<%@include file="/inc/ftc_sst.inc" %> 

<script>
	function init(){
		var f = document.formx;
		var parmType = f.type.value;

		if(parmType == 'adupHist'){
			document.getElementById("adupHistDetail").style.display = "block";				
		}else if(parmType == 'reptHist'){
			document.getElementById("reptHistDetail").style.display = "block";
		}else if(parmType == 'compAdupHist'){
			document.getElementById("compAdupHistDetail").style.display = "block";
		}else if(parmType == 'compReptHist'){
			document.getElementById("compReptHistDetail").style.display = "block";
		}
		
		parent.showLoading(false);
	}

</script>

</head>
<body onLoad="init()">

<form name="formx" method="post">
<input type="hidden" name="type" value="${param.type}">
<input type="hidden" name="crym" value="${param.crym}">
<input type="hidden" name="reptDtFmt" value="${param.reptDtFmt}">
<input type="hidden" name="csno" value="${param.csno}">
<input type="hidden" name="custSeq" value="${param.custSeq}">

<!-- 영업점번호 slsBrno -->
<!-- 기준년월 crym -->
<!-- 실사책임자 fatcaAcioRpprEnob -->
<!-- 실사담당자 fatcaAcipCgpEnob -->
<!-- 고객관계관리자 fatcaCustRelMngrEnob -->

<div id="subMain">
	<!----- /Table ----->
	<table class="table" id="adupHistDetail" style="display:none"><tbody>
	<colgroup>
        <col width="8%">
        <col width="10%">
        <col width="20%">
        <col width="12%">
        <col width="8%">
        <col width="12%">
        <col width="12%">
        <col width="14%">
	</colgroup>
	<tr>
		<th class="center">기준년월</th>
		<th class="center">상품구분</th>
		<th class="center">상품명</th>
		<th class="center">계좌번호</th>
		<th class="center">통화코드</th>
		<th class="center">잔액</th>
		<th class="center">원화환산금액</th>
		<th class="center">미화환산금액</th>
	</tr>
		<c:forEach items="${adupHistDetail}" var="adupHist">
		<tr>
	       	<td class="center">&nbsp;${adupHist.crymFmt}</td>
	       	<td class="center">${adupHist.fatcaPdClacdNm}</td>
	       	<td class="left">${adupHist.pdKnm}</td> 	
	       	<td class="center">${adupHist.acno}</td>
	       	<td class="center">${adupHist.curc}</td>
	       	<td class="right">${adupHist.acntBalamt}</td>
	       	<td class="right">${adupHist.wcCnvtBalamt}</td>
	       	<td class="right">${adupHist.usdCnvtBalamt}</td>   
		</tr>
		</c:forEach>
	<c:if test="${empty adupHistDetail}">
		<tr>
	       	<td class="center">&nbsp;</td>
	       	<td class="center"></td>
	       	<td class="left"></td> 	
	       	<td class="center"></td>
	       	<td class="center"></td>
	       	<td class="right"></td>
	       	<td class="right"></td>
	       	<td class="right"></td>   
		</tr>
	</c:if>
	<tbody></table>
	<!----- //Table ----->
	
	<!----- /Table ----->
	<table class="table" id="reptHistDetail" style="display:none"><tbody>
	<colgroup>
        <col width="8%">
        <col width="8%">
        <col width="11%">
        <col width="11%">
        <col width="19%">
        <col width="9%">
        <col width="12%">
        <col width="8%">
        <col width="12%">
	</colgroup>
	<tr>
		<th class="center">기준년월</th>
		<th class="center">보고일자</th>
		<th class="center">이름(영문)</th>
		<th class="center">이름(국문)</th>
		<th class="center">주소</th>
		<th class="center">TIN</th>
		<th class="center">계좌번호</th>
		<th class="center">생년월일</th>
		<th class="center">잔고</th>
	</tr>
		<c:forEach items="${reptHistDetail}" var="reptHist">
		<tr>
	       	<td class="center">${reptHist.crymFmt}</td>
	       	<td class="center">${reptHist.reptDtFmt}</td>
	       	<td class="center">${reptHist.engFullNm}</td> 	
	       	<td class="center">${reptHist.custNm}</td>
	       	<td>${reptHist.fatcaPrsnlEngAddr}</td>
	       	<td class="center">${reptHist.fatcaTxpayrNo}</td>
	       	<td class="center">${reptHist.acno}</td>
	       	<td class="center">${reptHist.bird}</td>
	       	<td class="right">${reptHist.acntBalamt}</td>   
		</tr>
		</c:forEach>
	<c:if test="${empty reptHistDetail}">
		<tr>
	       	<td class="center">&nbsp;</td>
	       	<td class="center"></td>
	       	<td class="center"></td> 	
	       	<td class="center"></td>
	       	<td class="center"></td>
	       	<td class="center"></td>
	       	<td class="center"></td>
	       	<td class="center"></td> 
	       	<td class="center"></td>   
		</tr>
	</c:if>
	<tbody></table>
	<!----- //Table ----->
	
	<!----- /Table ----->
	<table class="table" id="compAdupHistDetail" style="display:none"><tbody>
	<colgroup>
        <col width="8%">
        <col width="10%">
        <col width="20%">
        <col width="12%">
        <col width="8%">
        <col width="12%">
        <col width="12%">
        <col width="14%">
	</colgroup>
	<tr>
		<th class="center">기준년월</th>
		<th class="center">상품구분</th>
		<th class="center">상품명</th>
		<th class="center">계좌번호</th>
		<th class="center">통화코드</th>
		<th class="center">잔액</th>
		<th class="center">원화환산금액</th>
		<th class="center">미화환산금액</th>
	</tr>
		<c:forEach items="${compAdupHistDetail}" var="compAdupHist">
		<tr>
	       	<td class="center">${compAdupHist.crymFmt}</td>
	       	<td class="center">${compAdupHist.fatcaPdClacdNm}</td>
	       	<td class="left">${compAdupHist.pdKnm}</td> 	
	       	<td class="center">${compAdupHist.acno}</td>
	       	<td class="center">${compAdupHist.curc}</td>
	       	<td class="right">${compAdupHist.acntBalamt}</td>
	       	<td class="right">${compAdupHist.wcCnvtBalamt}</td>
	       	<td class="right">${compAdupHist.usdCnvtBalamt}</td>   
		</tr>
		</c:forEach>
		<c:if test="${empty compAdupHistDetail}">
			<tr>
		       	<td class="center">&nbsp;</td>
		       	<td class="center"></td>
		       	<td class="center"></td> 	
		       	<td class="center"></td>
		       	<td class="center"></td>
		       	<td class="center"></td>
		       	<td class="center"></td> 
		       	<td class="center"></td>   
			</tr>
		</c:if>
	<tbody></table>
	<!----- //Table ----->
	
	<!----- /Table ----->
	<table class="table" id="compReptHistDetail" style="display:none"><tbody>
	<colgroup>
        <col width="8%">
        <col width="8%">
        <col width="11%">
        <col width="11%">
        <col width="19%">
        <col width="9%">
        <col width="12%">
        <col width="12%">
	</colgroup>
	<tr>
		<th class="center">기준년월</th>
		<th class="center">보고일자</th>
		<th class="center">이름(영문)</th>
		<th class="center">이름(국문)</th>
		<th class="center">주소</th>
		<th class="center">TIN</th>
		<th class="center">계좌번호</th>
		<th class="center">잔고</th>
	</tr>
		<c:forEach items="${compReptHistDetail}" var="compRept">
			<tr>
		       	<td class="center">${compRept.crymFmt}</td>
		       	<td class="center">${compRept.reptDtFmt}</td>
		       	<td class="center">${compRept.fatcaCoprEnm}</td> 	
		       	<td class="center">${compRept.fatcaCoprKnm}</td>
		       	<td>${compRept.fatcaCoprEngAddr}</td>
		       	<td class="center">${compRept.fatcaTxpayrNo}</td>
		       	<td class="center">${compRept.acno}</td>
		       	<td class="right">${compRept.acntBalamt}</td>   
			</tr>
		</c:forEach>
		<c:if test="${empty compReptHistDetail}">
			<tr>
		       	<td class="center">&nbsp;</td>
		       	<td class="center"></td>
		       	<td class="center"></td> 	
		       	<td class="center"></td>
		       	<td class="center"></td>
		       	<td class="center"></td>
		       	<td class="center"></td> 
		       	<td class="center"></td>   
			</tr>
		</c:if>
	
	<tbody></table>
	<!----- //Table ----->
	
</div>
</form>

</body>
</html>
