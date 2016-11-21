<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %> 
<script>

	function search(){
		var f = document.formx;
		f.mnuId.value = 'BASIC_R';
		f.action = "<cx:wc/>/acipReady.do?method=modify";
		f.submit();		
		showLoading(true);
	}
	
	//실사기준일자 변경시 리셋
	function reset(){
		var f = document.formx;
				
	}
	
	function save(){
		var f = document.formx;
		
		if(inputCheck()){
			f.mnuId.value = 'BASIC_U';			
			f.action = "<cx:wc/>/acipReady.do?method=save";
			f.submit();
			showLoading(true);
		}	
	}
	
	function inputCheck(){
		var f = document.formx;
		 
		if('${isMainComplete}'=='Y'){
			alert('선택한 기준년월의 실사가 마감되었습니다.\nFATCA 담당자에게 문의하시기 바랍니다.');
			return false;
		}
		
		if(!confirm('실사기준년월에 해당하는 정보가 입력한 정보로 변경등록됩니다.\n진행하시겠습니까?')){
			return false;	
		}
				
		if(!isValidDate2(f.fatcaAcipCrdt, '-', '실사 기준일')){
			return false;
		}
		
		if(!isValidDate2(f.fatcaReptCrdt,'-','보고 기준일')){
			return false;	
		}

		if(!isValidDate2(f.fatcaAcipSdt,'-','실사 시작일')){
			return false;	
		}
		if(!isValidDate2(f.fatcaAcipEdt,'-','실사 종료일')){
			return false;	
		}
		if(!isValidDate2(f.fatcaReptComptDt,'-','보고 완료일')){
			return false;	
		}
		
		if(!isGreatDate(f.fatcaAcipCrdt, f.fatcaReptCrdt, '실사 기준일', '보고 기준일')){
			return false;	
		}
		if(!isGreatDate(f.fatcaAcipCrdt, f.fatcaAcipSdt, '실사 기준일', '실사 시작일')){
			return false;	
		}
		if(!isGreatDate(f.fatcaAcipSdt, f.fatcaAcipEdt, '실사 시작일', '실사 종료일')){
			return false;	
		}
		if(!isGreatDate(f.fatcaAcipEdt, f.fatcaReptComptDt, '실사 종료일', '보고 완료일')){
			return false;	
		}
				
		if(!isValidDate2(f.ftmFwDt,'-','1차 발송일')){
			return false;	
		}
		if(!isValidDate2(f.sctmFwDt,'-','2차 발송일')){
			return false;	
		}
		if(!isValidDate2(f.tdtmFwDt,'-','3차 발송일')){
			return false;	
		}
		if(!isValidDate2(f.frtmFwDt,'-','4차 발송일')){
			return false;	
		}		

		if(!isGreatDate(f.fatcaAcipSdt, f.ftmFwDt, '실사 시작일', '1차 발송일')){
			return false;	
		}
		if(!isGreatDate(f.ftmFwDt, f.sctmFwDt, '1차 발송일', '2차 발송일')){
			return false;	
		}
		if(!isGreatDate(f.sctmFwDt, f.tdtmFwDt, '2차 발송일', '3차 발송일')){
			return false;	
		}
		if(!isGreatDate(f.tdtmFwDt, f.frtmFwDt, '3차 발송일', '4차 발송일')){
			return false;	
		}
		
		if(!isNumber(f.fatcaPrsnlLaCaamt,'소액 계좌(개인 고객)')){
			return false;	
		}
		if(!isNumber(f.fatcaPrsnlHamtCaamt,'고액 계좌(개인 고객)')){
			return false;	
		}
		if(!isNumber(f.fatcaCoprLaCaamt,'소액 계좌(법인 고객)')){
			return false;	
		}
		if(!isNumber(f.fatcaCoprHamtCaamt,'고액 계좌(법인 고객)')){
			return false;	
		}

		if(!isNumber(f.fatcaOdPenAcntCaamt,'기존 연금 계좌')){
			return false;	
		}
		if(!isNumber(f.fatcaOdDpoAcntCaamt,'기존 예금 계좌')){
			return false;	
		}
		if(!isNumber(f.newDpoAcntCaamt,'신규 예금 계좌')){
			return false;	
		}
				
		return true;
	}
	
	
	function openCalendar(dist,anc) {		
		var cal = new CalendarPopup();
		var dist = eval("document.formx."+dist);
		cal.select(dist,anc,'yyyy-MM-dd');
	}
	
	function addComma(obj){
		insertSeperatorByWon(obj,",");
	}
	function addDate(obj){
		insertSeperatorByDate(obj, '-');
	}
	function resetValue(obj){
		obj.value = removeString(removeString(obj.value, ","),"-");
	}
	
	function __submit(){		
		if('${sessionScope.sessionUser.mnuAuthMap.BASIC_R}'=='Y'){
			//search();
		}
	}
	
	function init(){
		var f = document.formx;
		insertSeperatorByDate(f.fatcaAcipCrdt,'-');
		insertSeperatorByDate(f.fatcaReptCrdt,'-');
		insertSeperatorByDate(f.fatcaAcipSdt,'-');
		insertSeperatorByDate(f.fatcaAcipEdt,'-');
		insertSeperatorByDate(f.fatcaReptComptDt,'-');
		
		insertSeperatorByDate(f.ftmFwDt,'-');
		insertSeperatorByDate(f.sctmFwDt,'-');
		insertSeperatorByDate(f.tdtmFwDt,'-');
		insertSeperatorByDate(f.frtmFwDt,'-');
		
		insertSeperatorByWon(f.fatcaPrsnlLaCaamt,",");
		insertSeperatorByWon(f.fatcaPrsnlHamtCaamt,",");
		insertSeperatorByWon(f.fatcaCoprLaCaamt,",");
		insertSeperatorByWon(f.fatcaCoprHamtCaamt,",");
		
		insertSeperatorByWon(f.fatcaOdPenAcntCaamt,",");
		insertSeperatorByWon(f.fatcaOdDpoAcntCaamt,",");
		insertSeperatorByWon(f.newDpoAcntCaamt,",");
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
		<li class="txt">실사 기본정보 관리</li>
	</ul>
	</div>
	<!----- //Title ----->
	<!----- /Search ----->
	<div class="sheet_search"><div>
		<table><tbody>
		<tr>
			<td><span>기준년월</span></td>
			<td>
				<cx:cselect dbname="crymCombo" name="crym" selected="${sessionScope.sessionUser.selCrym}" style="width:85px" />
			</td>
			<td>
				<c:if test="${sessionScope.sessionUser.mnuAuthMap.BASIC_R == 'Y'}">
				<a href="javascript:search()" class="button search">조회</a>
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
			 <%-- <c:if test="${sessionScope.sessionUser.mnuAuthMap.BASIC_U == 'Y'}"> --%>
				<a href="javascript:save()" class="basic">저장</a>
			 <%-- </c:if> --%>
		</li>
	</ul>
	</div>
	<!----- //Title ----->
		
	<!-- table class="tableWrap" border="1" -->
	<table class="tableWrap2" >
	<tbody>
	<colgroup>
		<col width="50%"><col width="50%">
	</colgroup>
	<tr>
		<td class="pR10p">

			<!----- /Title ----->
			<div class="sheet_title">
			<ul>
				<li class="txt">> 실사 기준일자 관리</li>
				<li class="btn">					
				
				</li>
			</ul>
			</div>
			<!----- //Title ----->

			<!----- /Table ----->
			<table class="table"><tbody>
			<colgroup>
				<col width="20%"><col width="30%">
			</colgroup>
			<tr>
				<th>실사 기준일</th>
				<td class="center">
					<input type="text" name="fatcaAcipCrdt" value="${info.fatcaAcipCrdt}" onfocus="resetValue(this)" onBlur="addDate(this)" class="date" maxLength="8"/>
					<a href="javascript:openCalendar('fatcaAcipCrdt','fatcaAcipCrdt')"><img src="<cx:wc/>/images/common/calendar.gif" align="middle"></a>
				</td>
			</tr>
			<tr>
				<th>보고 기준일</th>
				<td class="center">
					<input type="text" name="fatcaReptCrdt" value="${info.fatcaReptCrdt}" onfocus="resetValue(this)" onBlur="addDate(this)" class="date" maxLength="8"/>
					<a href="javascript:openCalendar('fatcaReptCrdt','fatcaReptCrdt')"><img src="<cx:wc/>/images/common/calendar.gif" align="middle"></a>
				</td>
			</tr>
			<tr>
				<th>등록일자</th>
				<td class="center">
					${currentDate}
				</td>
			</tr>
			<tr>
				<th>실사 시작일</th>
				<td class="center">
					<input type="text" name="fatcaAcipSdt" value="${info.fatcaAcipSdt}" onfocus="resetValue(this)" onBlur="addDate(this)" class="date" maxLength="8"/>
					<a href="javascript:openCalendar('fatcaAcipSdt','fatcaAcipSdt')"><img src="<cx:wc/>/images/common/calendar.gif" align="middle"></a>
				</td>
			</tr>
			<tr>
				<th>실사 종료일</th>
				<td class="center">
					<input type="text" name="fatcaAcipEdt" value="${info.fatcaAcipEdt}" onfocus="resetValue(this)" onBlur="addDate(this)" class="date" maxLength="8"/>
					<a href="javascript:openCalendar('fatcaAcipEdt','fatcaAcipEdt')"><img src="<cx:wc/>/images/common/calendar.gif" align="middle"></a>
				</td>
			</tr>
			<tr>
				<th>보고 완료일</th>
				<td class="center">
					<input type="text" name="fatcaReptComptDt" value="${info.fatcaReptComptDt}" onfocus="resetValue(this)" onBlur="addDate(this)" class="date" maxLength="8"/>
					<a href="javascript:openCalendar('fatcaReptComptDt','fatcaReptComptDt')"><img src="<cx:wc/>/images/common/calendar.gif" align="middle"></a>
				</td>
			</tr>
			<tr>
				<th>등록자</th>
				<td class="center">
					${cgpEnnm}
				</td>
			</tr>
			<tbody></table>
			<!----- //Table ----->

		</td>
		<td class="pL10p">

			<!----- /Title ----->
			<div class="sheet_title">
			<ul>
				<li class="txt">> 기준 금액 관리 (합산금액)</li>
				<li class="btn">
				</li>
			</ul>
			</div>
			<!----- //Title ----->

			<!----- /Table ----->
			<table class="table"><tbody>
			<colgroup>
				<col width="20%"><col width="30%">
			</colgroup>
			<tr>
				<th>소액 계좌(개인 고객)</th>
				<td class="right">
					$
					<input type="text" name="fatcaPrsnlLaCaamt" value="${info.fatcaPrsnlLaCaamt}" onfocus="resetValue(this)" onBlur="addComma(this)" maxLength="10" class="text right" style="width:150px" />
				</td>
			</tr>
			<tr>
				<th>고액 계좌(개인 고객)</th>
				<td class="right">
					$
					<input type="text" name="fatcaPrsnlHamtCaamt" value="${info.fatcaPrsnlHamtCaamt}" onfocus="resetValue(this)" onBlur="addComma(this)" maxLength="10" class="text right" style="width:150px" />
				</td>
			</tr>
			<tr>
				<th>소액 계좌(법인 고객)</th>
				<td class="right">
					$
					<input type="text" name="fatcaCoprLaCaamt" value="${info.fatcaCoprLaCaamt}" onfocus="resetValue(this)" onBlur="addComma(this)" maxLength="10" class="text right" style="width:150px" />
				</td>
			</tr>
			<tr>
				<th>고액 계좌(법인 고객)</th>
				<td class="right">
					$
					<input type="text" name="fatcaCoprHamtCaamt" value="${info.fatcaCoprHamtCaamt}" onfocus="resetValue(this)" onBlur="addComma(this)" maxLength="10" class="text right" style="width:150px" />
				</td>
			</tr>
			<tbody></table>
			<!----- //Table ----->

		</td>
	</tr>
	<tr>
		<td class="pR10p">

			<!----- /Title ----->
			<div class="sheet_title">
			<ul>
				<li class="txt">> SMS 발송 일정 관리</li>
				<li class="btn">					
				</li>
			</ul>
			</div>
			<!----- //Title ----->

			<!----- /Table ----->
			<table class="table"><tbody>
			<colgroup>
				<col width="20%"><col width="30%">
			</colgroup>
			<tr>
				<th>1차 발송일</th>
				<td class="center">
					<input type="text" name="ftmFwDt" value="${info.ftmFwDt}" onfocus="resetValue(this)" onBlur="addDate(this)" class="date" maxLength="8"/>
					<a href="javascript:openCalendar('ftmFwDt','ftmFwDt')"><img src="<cx:wc/>/images/common/calendar.gif" align="middle"></a>
				</td>
			</tr>
			<tr>
				<th>2차 발송일</th>
				<td class="center">
					<input type="text" name="sctmFwDt" value="${info.sctmFwDt}" onfocus="resetValue(this)" onBlur="addDate(this)" class="date" maxLength="8"/>
					<a href="javascript:openCalendar('sctmFwDt','sctmFwDt')"><img src="<cx:wc/>/images/common/calendar.gif" align="middle"></a>
				</td>
			</tr>
			<tr>
				<th>3차 발송일</th>
				<td class="center">
					<input type="text" name="tdtmFwDt" value="${info.tdtmFwDt}" onfocus="resetValue(this)" onBlur="addDate(this)" class="date" maxLength="8"/>
					<a href="javascript:openCalendar('tdtmFwDt','tdtmFwDt')"><img src="<cx:wc/>/images/common/calendar.gif" align="middle"></a>
				</td>
			</tr>
			<tr>
				<th>4차 발송일</th>
				<td class="center">
					<input type="text" name="frtmFwDt" value="${info.frtmFwDt}" onfocus="resetValue(this)" onBlur="addDate(this)" class="date" maxLength="8"/>
					<a href="javascript:openCalendar('frtmFwDt','frtmFwDt')"><img src="<cx:wc/>/images/common/calendar.gif" align="middle"></a>
				</td>
			</tr>
			<tbody></table>
			<!----- //Table ----->

		</td>
		<td class="pL10p">

			<!----- /Title ----->
			<div class="sheet_title">
			<ul>
				<li class="txt">> 개인 실사 / 보고대상 기준 금액 관리</li>
				<li class="btn">
				</li>
			</ul>
			</div>
			<!----- //Title ----->

			<!----- /Table ----->
			<table class="table"><tbody>
			<colgroup>
				<col width="20%"><col width="30%">
			</colgroup>
			<tr>
				<th>기존 연금 계좌</th>
				<td class="right">
					$
					<input type="text" name="fatcaOdPenAcntCaamt" value="${info.fatcaOdPenAcntCaamt}" onfocus="resetValue(this)" onBlur="addComma(this)" maxLength="10" class="text right" style="width:150px" />
				</td>
			</tr>
			<tr>
				<th>기존 예금 계좌</th>
				<td class="right">
					$
					<input type="text" name="fatcaOdDpoAcntCaamt" value="${info.fatcaOdDpoAcntCaamt}" onfocus="resetValue(this)" onBlur="addComma(this)" maxLength="10" class="text right" style="width:150px" />
				</td>
			</tr>
			<tr>
				<th>신규 예금 계좌</th>
				<td class="right">
					$
					<input type="text" name="newDpoAcntCaamt" value="${info.newDpoAcntCaamt}" onfocus="resetValue(this)" onBlur="addComma(this)" maxLength="10" class="text right" style="width:150px" />
				</td>
			</tr>
			<tbody></table>
			<!----- //Table ----->

		</td>
	</tr>
	</tbody></table>

</div>
</form>
</body>
</html>