<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE HTML >
<html>
<head> 
<%@include file="/inc/ftc_sst.inc" %> 

<script>
	function searchTarget2(){
		var f = document.formx;
 		if(!isNull(f.csno, '고객번호')){
 			if(checkCustNo()){
				f.mnuId.value = 'CDTL_R';
	 			f.action = "<cx:wc/>/acipTarget.do?method=companyView";
	 			f.submit();	
	 			showLoading(true);
 			}
 		}
	}
	
	function checkCustNo(){
		var f = document.formx;
		
		if(f.csno.value == ''){
			f.custSeq.value = '';
			return true;
		}else if(f.csno.value != ''){
			if(f.custNm.value!=''){
				return true;		
			}else{
				searchPopCust('com');
				return false;
			}
		}		
	}
	
	function __submit(){
		if('${sessionScope.sessionUser.mnuAuthMap.CDTL_R}'=='Y'){
			searchPersonal();
		}
	}

	function goAdupHist(go, crym, csno, custseq){
		showLoading(true);
		document.getElementById("ifrm").src = "<cx:wc/>/acipTarget.do?method=compAdupHistDetail&type="+go+"&crym="+crym+"&csno="+csno+"&custSeq="+custseq;	
	}

	function goReptHist(go, crym, reptdtfmt, csno, custseq){	
		showLoading(true);
		document.getElementById("ifrm").src = "<cx:wc/>/acipTarget.do?method=compReptHistDetail&type="+go+"&crym="+crym+"&reptDtFmt="+reptdtfmt+"&csno="+csno+"&custSeq="+custseq;	
	}

	function tabClick(objtype){
		/* 초기 탭설정 */
		document.getElementById("tab1t").style.display = "none"; 
		document.getElementById("tab1").className = "";
		document.getElementById("tab2t").style.display = "none";
		document.getElementById("tab2").className = "";
		document.getElementById("tab3t").style.display = "none";
		document.getElementById("tab3").className = "";
		document.getElementById("tab3c").style.display = "none";
		document.getElementById("tab4t").style.display = "none";
		document.getElementById("tab4").className = "";
		document.getElementById("tab4c").style.display = "none";
		document.getElementById("tab4frm").style.display = "none";
	
		if(objtype == "acipHist"){
			document.getElementById("tab1t").style.display = "block";
			document.getElementById("tab1").className = "selected";
		}else if(objtype == "prstHist"){
			document.getElementById("tab2t").style.display = "block";
			document.getElementById("tab2").className = "selected";
		}else if(objtype == "adupHist"){
			document.getElementById("tab3t").style.display = "block";
			document.getElementById("tab3c").style.display = "block";
			document.getElementById("tab4frm").style.display = "block";
			document.getElementById("tab3").className = "selected";
			document.getElementById("ifrm").src = "<cx:wc/>/acipTarget.do?method=compAdupHistDetail&type=compAdupHist";			
		}else if(objtype == "reptHist"){
			document.getElementById("tab4t").style.display = "block";
			document.getElementById("tab4c").style.display = "block";
			document.getElementById("tab4frm").style.display = "block";
			document.getElementById("tab4").className = "selected";
			document.getElementById("ifrm").src = "<cx:wc/>/acipTarget.do?method=compReptHistDetail&type=compReptHist";
		}
	}

	function searchPopCust(){
		var f = document.formx;
		window.open("<cx:wc/>/code.do?method=searchPopCopr&init=y&crym="+f.crym.value+"&csno="+f.csno.value+"&custType="+f.custType.value,"pop","width=650,height=620,top=200,left=200");
	}
	
	function init(){
		var f = document.formx;
		f.mode.value = '${param.mode}';
		var mode = f.mode.value;
		if(mode == "popup" ){
			self.window.focus();
		}
	}

	function sclose(){
		self.close();
	}
	
</script>
</head>
<body onLoad="init()">
    
<form name="formx" method="post">
<input type="hidden" name="mnuId">
<input type="hidden" name="mode" value="${param.mode}">
<input type="hidden" name="slsBrno" value="${param.slsBrno}">
<input type="hidden" name="custType" value="com">
			
<c:if test="${param.mode == 'popup' }">
	<div class="popWrap" id="divPopW">
	<!----- /Title ----->
	<div class="popTop" id="divPopTitle" >
	<ul>
		<li class="left">
			법인 상세조회
		</li>
		<li class="right">
			<div class="btn">
				<a href="javascript:sclose();"><img src="<cx:wc/>/images/common/btn_pop_close.gif"/></a>
				</div>
			</li>
		</ul>
	</div>
</c:if>	

<c:if test="${param.mode != 'popup' }">
	<!----- /Title ----->
	<div class="top_title" id="divTitle">
	<ul>
		<li class="txt">법인 상세조회</li>
	</ul>
	</div>
	<!----- //Title ----->
</c:if>	


<!----- /Contents ----->
<div class="popContentWrap">

	<div id="subMain">			
		<!----- /Search ----->			
		<div class="sheet_search">
		<div>
			<table><tbody>		
			<tr>
				<td><span>기준년월</spanp></td>
				<td>
					<cx:cselect dbname="crymCombo" name="crym" selected="${sessionScope.sessionUser.selCrym}" style="width:85px" />
				</td>
			<td>
				<span>고객번호</span>
			</td>
			<td>
				<input type="text" name="csno" value="${param.csno}" maxlength="13" onKeydown="changeSearch('custNm');changeSearch('csnoReal');changeSearch('custSeq');" class="text csno" />
				<input type="hidden" name="csnoReal" value="${param.csnoReal}" >
				<input type="text" name="custNm" value="${selCustNm}" readonly disabled class="readonly coprnm" />
				<input type="hidden" name="custSeq" value="${param.custSeq}" >
				<a href="javascript:searchPopCust('com')"><img src="<cx:wc/>/images/common/icon_search.gif"></a>
			</td>			
			<td>
				<c:if test="${sessionScope.sessionUser.mnuAuthMap.CDTL_R == 'Y'}">
					<a href="javascript:searchTarget()" class="button search" >조회</a>
				</c:if>
			</td>
			</tr>
			</tbody></table>
		</div></div>
		
		<!----- /Title ----->
		<div class="sheet_title">
		<ul>
			<li class="txt">기본정보</li>
		</ul>
		</div>
		<!----- //Title ----->
	
		<!----- /Table ----->
		<table class="table"><tbody>
		<colgroup>
			<col width="10%"><col width="10%">
			<col width="10%"><col width="10%">
			<col width="10%"><col width="10%">
			<col width="10%"><col width="10%">
		</colgroup>
		<tr>
			<th>고객번호</th>
				<td class="center" name="csno" >${compBas.csno}</td>
			<th>고객명</th>
				<td class="center" name="custNm" >${compBas.custNm}</td>
			<th>고객구분</th>
				<td class="center" name="fatcaAmtSctClacdNm" >${compBas.fatcaAmtSctClacdNm}</td>
			<th>FATCA 고객유형</th>
				<td class="center" name="fatcaCustDvcdNm" >${compBas.fatcaCustDvcdNm}</td>
		</tr>
		<tr>
			<th>표준산업분류코드</th>
				<td class="center" name="idsClacd" >${compBas.idsClacd}</td>
			<th>분류명</th>
				<td class="left" name="idsClacdNm" colspan = "5">${compBas.idsClacdNm}</td>
		</tr>
		<tr>
			<th>전화번호</th>
				<td class="center" name="phone" >${compBas.phone}</td>
			<th>법인주소(소재지주소)</th>
				<td class="left" name="addr" colspan="5">${compBas.addr}</td>
		</tr>
		<tr>
			<th>영업점코드/명</th>
				<td class="center" name="br">${compBas.br}</td>
			<th>법인/사업자등록번호</th>
				<td class="center" name="bizno">${compBas.bizno}</td>
			<th>실사책임자</th>
				<td class="center" name="fatcaAcipRpprEnnm">${compBas.fatcaAcipRpprEnnm}</td>
			<th>실사담당자</th>
				<td class="center" name="fatcaAcipCgpEnnm">${compBas.fatcaAcipCgpEnnm}</td>
		</tr>
		<tbody></table>
		<!----- //Table ----->
	
		<!----- /Title ----->
		<div class="sheet_title">
		<ul>
			<li class="txt">계좌정보<span> (단위:원, $)</span></li>
		</ul>
		</div>
		<!----- //Title ----->
	
		<!----- /Table ----->
		<table class="table"><tbody>
		<colgroup>
			<col width="10%"><col width="10%">
		</colgroup>
		<tr>
			<th class="center" colspan="2">합산금액</th>		
			<th class="center" colspan="2">예금계좌</th>
			<th class="center" colspan="2">수탁계좌</th>
			<th class="center" colspan="2">지분권/사채권</th>
			<th class="center" colspan="2">보험/연금계좌</th>
		</tr>
		<tr>
			<th class="center">원화</th>
			<th class="center">미화</th>
			<th class="center">원화</th>
			<th class="center">미화</th>
			<th class="center">원화</th>
			<th class="center">미화</th>
			<th class="center">원화</th>
			<th class="center">미화</th>
			<th class="center">원화</th>
			<th class="center">미화</th>
		</tr>
		<tr>		
			<td class="right" name="acntSmamt">&nbsp;${compBas.acntSmamt}</td>
			<td class="right" name="acntSumtUsdCnvtAmt">${compBas.acntSumtUsdCnvtAmt}</td>
			<td class="right" name="dpoSmamt">${compBas.dpoSmamt}</td>
			<td class="right" name="acntSmamtUsdCnvtAmt">${compBas.dpoSumtUsdCnvtAmt}</td>
			<td class="right" name="trstSmamt">${compBas.trstSmamt}</td>
			<td class="right" name="acntSmamtUsdCnvtAmt">${compBas.trstSumtUsdCnvtAmt}</td>
			<td class="right" name="dbtrSmamt">${compBas.dbtrSmamt}</td>
			<td class="right" name="acntSmamtUsdCnvtAmt">${compBas.dbtrSumtUsdCnvtAmt}</td>
			<td class="right" name="penSmamt">${compBas.penSmamt}</td>
			<td class="right" name="acntSmamtUsdCnvtAmt">${compBas.penSumtUsdCnvtAmt}</td>
		</tr>
		<tbody></table>
		<!----- //Table ----->
	
		<!----- /Title ----->
		<div class="sheet_title">
		<ul>
			<li class="txt">실사정보</li>
		</ul>
		</div>
		<!----- //Title ----->
	
		<!----- /Table ----->
		<table class="table"><tbody>
		<colgroup>
			<col width="10%"><col width="13%">
			<col width="10%"><col width="13%">
		</colgroup>
		<tr>
			<th>전산실사결과</th>
				<td class="center" name="fatcaCzAcipRscdNm">${compBas.fatcaCzAcipRscdNm}</td>
			<th>FATCA 확인서 실사결과</th>
				<td class="center" name="fatcaCfrrptAcipRscdNm">${compBas.fatcaCfrrptAcipRscdNm}</td>
		</tr>
		</tbody></table>
		<!----- //Table ----->
	
		<!----- /Tab ----->
		<div class="mainTab">
			<ul class="tabMenu">
				<li id="tab1" class="selected"><a href="javascript:tabClick('acipHist')">실사이력</a></li>
				<li id="tab2"><a href="javascript:tabClick('prstHist')">FATCA 확인서 제출이력</a></li>
				<li id="tab3"><a href="javascript:tabClick('adupHist')">합산이력</a></li>
				<li id="tab4"><a href="javascript:tabClick('reptHist')">보고이력</a></li>
			</ul>
		</div>
		<!----- //Tab ----->
	
		<!----- /Grid ----->
		<!-- 실사이력 -->
		<table class="table" id="tab1t" style="display:block"><tbody>
		<colgroup>
	        <col width="13%">
	        <col width="10%">
	        <col width="10%">
	        <col width="14%">
	        <col width="10%">
	        <col width="12%">
		</colgroup>
		<tr>
			<th class="center">기준년월</th>
			<th class="center">실사완료일자</th>
			<th class="center">전산실사결과</th>
			<th class="center">FATCA 확인서실사결과</th>
			<th class="center">보고대상여부</th>
			<th class="center">FATCA 고객유형</th>
		</tr>
		
		<c:forEach items="${acipHist}" var="func">
		<tr>
	       	<td class="center">&nbsp;${func.crymFmt}</td>
	       	<td class="center">${func.comptDtFmt}</td>
	       	<td class="center">${func.fatcaCzAcipRscdNm}</td>
	       	<td class="center">${func.fatcaCfrrptAcipRscdNm}</td>
	       	<td class="center">${func.fatcaReptTrgetDvcdNm}</td>
	       	<td class="center">${func.fatcaCustDvcdNm}</td>       
		</tr>
		</c:forEach>
		
		<tbody></table>
		<!----- //Grid ----->
		
		
		<!----- /Grid ----->
		<!-- FATCA 확인서제출이력 -->
		<table class="table" id="tab2t" style="display:none"><tbody>
		<colgroup>
	        <col width="7%">
	        <col width="9%">
	        <col width="7%">
	        <col width="12%">
	        <col width="12%">
	        <col width="8%">
	        <col width="10%">
	        <col width="15%">
	        <col width="12%">
		</colgroup>
		<tr>
			<th class="center">기준년월</th>
			<th class="center">제출일자</th>
			<th class="center">확인서<br>일련번호</th>
			<th class="center">FATCA 고객유형</th>
			<th class="center">TIN(EIN)</th>
			<th class="center">법인/사업자번호</th>
			<th class="center">소재지국</th>
			<th class="center">소재지주소</th>
			<th class="center">전화번호</th>
		</tr>
		
		<c:forEach items="${prstHist}" var="prst">
		<tr>
	       	<td class="center">&nbsp;${prst.crymFmt}</td>
	       	<td class="center">${prst.prstDtFmt}</td>
	       	<td class="center">${prst.fatcaCfrrptSeq}</td>
	       	<td class="center">${prst.fatcaCorpTpcdNm}</td> 	
	       	<td class="center">${prst.fatcaTxpayrNo}</td>
	       	<td class="center">${prst.bzno}</td>
	       	<td class="center">${prst.lopNacd}</td>
	       	<td class="center">${prst.fatcaCoprHgAddr}</td>
	       	<td class="center">${prst.fatcaCustTlno}</td>        
		</tr>
		</c:forEach>
		<c:if test="${empty prstHist}">
			<tr>
		       	<td class="center"></td>
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
		<!----- //Grid ----->
		
		
		<!----- /Grid ----->
		<!-- 합산이력 -->
	
		<table class="table" id="tab3t" style="display:none"><tbody>
			<colgroup>	
		        <col width="8%">
		        <col width="8%">
		        <col width="10%">
		        <col width="10%">
		        <col width="8%">
		        <col width="8%">
		        <col width="8%">
		        <col width="8%">
		        <col width="8%">
		        <col width="8%">
		        <col width="8%">
		        <col width="8%">
			</colgroup>
			<tr>
				<th class="center" rowspan="2">기준년월</th>	
				<th class="center" rowspan="2">합산일자</th>	
				<th class="center" colspan="2">합산금액</th>		
				<th class="center" colspan="2">예금계좌</th>
				<th class="center" colspan="2">수탁계좌</th>
				<th class="center" colspan="2">지분권/사채권</th>
				<th class="center" colspan="2">보험/연금계좌</th>
			</tr>
			<tr>
				<th class="center">원화</th>
				<th class="center">미화</th>
				<th class="center">원화</th>
				<th class="center">미화</th>
				<th class="center">원화</th>
				<th class="center">미화</th>
				<th class="center">원화</th>
				<th class="center">미화</th>
				<th class="center">원화</th>
				<th class="center">미화</th>
			</tr>
			
			<c:forEach items="${adupHist}" var="adup">
			<tr>
		       	<td class="center">&nbsp;${adup.crymFmt}</td>
		       	<td class="center"><a href="javascript:goAdupHist('compAdupHist','${adup.crym}','${adup.csno}','${adup.custSeq}');">${adup.fatcaAdupdtFmt}</a></td>
		       	<td class="right">${adup.acntSmamt}</td> 	
		       	<td class="right">${adup.acntSumtUsdCnvtAmt}</td>
		       	<td class="right">${adup.dpoSmamt}</td>
		       	<td class="right">${adup.dpoSumtUsdCnvtAmt}</td>
		       	<td class="right">${adup.trstSmamt}</td>
		       	<td class="right">${adup.trstSumtUsdCnvtAmt}</td>
		       	<td class="right">${adup.dbtrSmamt}</td>
		       	<td class="right">${adup.dbtrSumtUsdCnvtAmt}</td>
		       	<td class="right">${adup.penSmamt}</td>
		       	<td class="right">${adup.penSumtUsdCnvtAmt}</td>
		       	
			</tr>
			</c:forEach>
			
		<tbody></table>	
			
		<div class="sheet_title" id="tab3c" style="display:none">	
			<ul>
			<li class="txt">계좌 상세<span> (단위:원, $)</span></li>
			</ul>
		</div>
		
	
		
		<!----- /Grid ----->
		<!-- 보고이력 -->
		<table class="table" id="tab4t" style="display:none"><tbody>		
			<colgroup>
		        <col width="25%">
		        <col width="25%">
		        <col width="25%">
		        <col width="25%">
		    </colgroup>
			<tr>
				<th class="center">기준년월</th>
				<th class="center">보고일자</th>
				<th class="center">보고여부</th>
				<th class="center">FATCA고객유형</th>
			</tr>
			
			<c:forEach items="${reptHist}" var="rept">
			<tr>
		       	<td class="center">&nbsp;${rept.crymFmt}</td>
		       	<td class="center"><a href="javascript:goReptHist('compReptHist','${rept.crym}','${rept.reptDtFmt}','${rept.csno}','${rept.custSeq}');">&nbsp; ${rept.reptDtFmt}</a></td>
		       	<td class="center">${rept.fatcaReptTrgetDvcdNm}</td>
		       	<td class="center">${rept.fatcaCustDvcdNm}</td>
			</tr>
			</c:forEach>
			<c:if test="${empty reptHist}">
				<tr>
			       	<td class="center">&nbsp;</td>
			       	<td class="center"></td>
			       	<td class="left"></td> 	
			       	<td class="center"></td>   
				</tr>
			</c:if>
			<tbody></table>
			
		<div class="sheet_title" id="tab4c" style="display:none">	
			<ul>
				<li class="txt">보고상세내역<span> (단위:원, $)</span></li>
			</ul>
		</div>

		<div>	
			<table class="grid" id="tab4frm" width="100%" style="display:none"><tbody>
			<iframe name="ifrm" id="ifrm" src="" frameborder="0" width="100%" height="95px" scrolling="false"></iframe>
			</tbody></table>
		</div>
		
		<!----- //Grid ----->	
	</div>
</div>
</form>
<iframe name="ifrmPopCust" id="ifrmPopCust" width="0" height="0" border="0"></iframe>
</body>
</html>
