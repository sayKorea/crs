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
	
	//�ǻ�������� ����� ����
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
			alert('������ ���س���� �ǻ簡 �����Ǿ����ϴ�.\nFATCA ����ڿ��� �����Ͻñ� �ٶ��ϴ�.');
			return false;
		}
		
		if(!confirm('�ǻ���س���� �ش��ϴ� ������ �Է��� ������ �����ϵ˴ϴ�.\n�����Ͻðڽ��ϱ�?')){
			return false;	
		}
				
		if(!isValidDate2(f.fatcaAcipCrdt, '-', '�ǻ� ������')){
			return false;
		}
		
		if(!isValidDate2(f.fatcaReptCrdt,'-','���� ������')){
			return false;	
		}

		if(!isValidDate2(f.fatcaAcipSdt,'-','�ǻ� ������')){
			return false;	
		}
		if(!isValidDate2(f.fatcaAcipEdt,'-','�ǻ� ������')){
			return false;	
		}
		if(!isValidDate2(f.fatcaReptComptDt,'-','���� �Ϸ���')){
			return false;	
		}
		
		if(!isGreatDate(f.fatcaAcipCrdt, f.fatcaReptCrdt, '�ǻ� ������', '���� ������')){
			return false;	
		}
		if(!isGreatDate(f.fatcaAcipCrdt, f.fatcaAcipSdt, '�ǻ� ������', '�ǻ� ������')){
			return false;	
		}
		if(!isGreatDate(f.fatcaAcipSdt, f.fatcaAcipEdt, '�ǻ� ������', '�ǻ� ������')){
			return false;	
		}
		if(!isGreatDate(f.fatcaAcipEdt, f.fatcaReptComptDt, '�ǻ� ������', '���� �Ϸ���')){
			return false;	
		}
				
		if(!isValidDate2(f.ftmFwDt,'-','1�� �߼���')){
			return false;	
		}
		if(!isValidDate2(f.sctmFwDt,'-','2�� �߼���')){
			return false;	
		}
		if(!isValidDate2(f.tdtmFwDt,'-','3�� �߼���')){
			return false;	
		}
		if(!isValidDate2(f.frtmFwDt,'-','4�� �߼���')){
			return false;	
		}		

		if(!isGreatDate(f.fatcaAcipSdt, f.ftmFwDt, '�ǻ� ������', '1�� �߼���')){
			return false;	
		}
		if(!isGreatDate(f.ftmFwDt, f.sctmFwDt, '1�� �߼���', '2�� �߼���')){
			return false;	
		}
		if(!isGreatDate(f.sctmFwDt, f.tdtmFwDt, '2�� �߼���', '3�� �߼���')){
			return false;	
		}
		if(!isGreatDate(f.tdtmFwDt, f.frtmFwDt, '3�� �߼���', '4�� �߼���')){
			return false;	
		}
		
		if(!isNumber(f.fatcaPrsnlLaCaamt,'�Ҿ� ����(���� ��)')){
			return false;	
		}
		if(!isNumber(f.fatcaPrsnlHamtCaamt,'��� ����(���� ��)')){
			return false;	
		}
		if(!isNumber(f.fatcaCoprLaCaamt,'�Ҿ� ����(���� ��)')){
			return false;	
		}
		if(!isNumber(f.fatcaCoprHamtCaamt,'��� ����(���� ��)')){
			return false;	
		}

		if(!isNumber(f.fatcaOdPenAcntCaamt,'���� ���� ����')){
			return false;	
		}
		if(!isNumber(f.fatcaOdDpoAcntCaamt,'���� ���� ����')){
			return false;	
		}
		if(!isNumber(f.newDpoAcntCaamt,'�ű� ���� ����')){
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
		<li class="txt">�ǻ� �⺻���� ����</li>
	</ul>
	</div>
	<!----- //Title ----->
	<!----- /Search ----->
	<div class="sheet_search"><div>
		<table><tbody>
		<tr>
			<td><span>���س��</span></td>
			<td>
				<cx:cselect dbname="crymCombo" name="crym" selected="${sessionScope.sessionUser.selCrym}" style="width:85px" />
			</td>
			<td>
				<c:if test="${sessionScope.sessionUser.mnuAuthMap.BASIC_R == 'Y'}">
				<a href="javascript:search()" class="button search">��ȸ</a>
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
				<a href="javascript:save()" class="basic">����</a>
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
				<li class="txt">> �ǻ� �������� ����</li>
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
				<th>�ǻ� ������</th>
				<td class="center">
					<input type="text" name="fatcaAcipCrdt" value="${info.fatcaAcipCrdt}" onfocus="resetValue(this)" onBlur="addDate(this)" class="date" maxLength="8"/>
					<a href="javascript:openCalendar('fatcaAcipCrdt','fatcaAcipCrdt')"><img src="<cx:wc/>/images/common/calendar.gif" align="middle"></a>
				</td>
			</tr>
			<tr>
				<th>���� ������</th>
				<td class="center">
					<input type="text" name="fatcaReptCrdt" value="${info.fatcaReptCrdt}" onfocus="resetValue(this)" onBlur="addDate(this)" class="date" maxLength="8"/>
					<a href="javascript:openCalendar('fatcaReptCrdt','fatcaReptCrdt')"><img src="<cx:wc/>/images/common/calendar.gif" align="middle"></a>
				</td>
			</tr>
			<tr>
				<th>�������</th>
				<td class="center">
					${currentDate}
				</td>
			</tr>
			<tr>
				<th>�ǻ� ������</th>
				<td class="center">
					<input type="text" name="fatcaAcipSdt" value="${info.fatcaAcipSdt}" onfocus="resetValue(this)" onBlur="addDate(this)" class="date" maxLength="8"/>
					<a href="javascript:openCalendar('fatcaAcipSdt','fatcaAcipSdt')"><img src="<cx:wc/>/images/common/calendar.gif" align="middle"></a>
				</td>
			</tr>
			<tr>
				<th>�ǻ� ������</th>
				<td class="center">
					<input type="text" name="fatcaAcipEdt" value="${info.fatcaAcipEdt}" onfocus="resetValue(this)" onBlur="addDate(this)" class="date" maxLength="8"/>
					<a href="javascript:openCalendar('fatcaAcipEdt','fatcaAcipEdt')"><img src="<cx:wc/>/images/common/calendar.gif" align="middle"></a>
				</td>
			</tr>
			<tr>
				<th>���� �Ϸ���</th>
				<td class="center">
					<input type="text" name="fatcaReptComptDt" value="${info.fatcaReptComptDt}" onfocus="resetValue(this)" onBlur="addDate(this)" class="date" maxLength="8"/>
					<a href="javascript:openCalendar('fatcaReptComptDt','fatcaReptComptDt')"><img src="<cx:wc/>/images/common/calendar.gif" align="middle"></a>
				</td>
			</tr>
			<tr>
				<th>�����</th>
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
				<li class="txt">> ���� �ݾ� ���� (�ջ�ݾ�)</li>
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
				<th>�Ҿ� ����(���� ��)</th>
				<td class="right">
					$
					<input type="text" name="fatcaPrsnlLaCaamt" value="${info.fatcaPrsnlLaCaamt}" onfocus="resetValue(this)" onBlur="addComma(this)" maxLength="10" class="text right" style="width:150px" />
				</td>
			</tr>
			<tr>
				<th>��� ����(���� ��)</th>
				<td class="right">
					$
					<input type="text" name="fatcaPrsnlHamtCaamt" value="${info.fatcaPrsnlHamtCaamt}" onfocus="resetValue(this)" onBlur="addComma(this)" maxLength="10" class="text right" style="width:150px" />
				</td>
			</tr>
			<tr>
				<th>�Ҿ� ����(���� ��)</th>
				<td class="right">
					$
					<input type="text" name="fatcaCoprLaCaamt" value="${info.fatcaCoprLaCaamt}" onfocus="resetValue(this)" onBlur="addComma(this)" maxLength="10" class="text right" style="width:150px" />
				</td>
			</tr>
			<tr>
				<th>��� ����(���� ��)</th>
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
				<li class="txt">> SMS �߼� ���� ����</li>
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
				<th>1�� �߼���</th>
				<td class="center">
					<input type="text" name="ftmFwDt" value="${info.ftmFwDt}" onfocus="resetValue(this)" onBlur="addDate(this)" class="date" maxLength="8"/>
					<a href="javascript:openCalendar('ftmFwDt','ftmFwDt')"><img src="<cx:wc/>/images/common/calendar.gif" align="middle"></a>
				</td>
			</tr>
			<tr>
				<th>2�� �߼���</th>
				<td class="center">
					<input type="text" name="sctmFwDt" value="${info.sctmFwDt}" onfocus="resetValue(this)" onBlur="addDate(this)" class="date" maxLength="8"/>
					<a href="javascript:openCalendar('sctmFwDt','sctmFwDt')"><img src="<cx:wc/>/images/common/calendar.gif" align="middle"></a>
				</td>
			</tr>
			<tr>
				<th>3�� �߼���</th>
				<td class="center">
					<input type="text" name="tdtmFwDt" value="${info.tdtmFwDt}" onfocus="resetValue(this)" onBlur="addDate(this)" class="date" maxLength="8"/>
					<a href="javascript:openCalendar('tdtmFwDt','tdtmFwDt')"><img src="<cx:wc/>/images/common/calendar.gif" align="middle"></a>
				</td>
			</tr>
			<tr>
				<th>4�� �߼���</th>
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
				<li class="txt">> ���� �ǻ� / ������ ���� �ݾ� ����</li>
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
				<th>���� ���� ����</th>
				<td class="right">
					$
					<input type="text" name="fatcaOdPenAcntCaamt" value="${info.fatcaOdPenAcntCaamt}" onfocus="resetValue(this)" onBlur="addComma(this)" maxLength="10" class="text right" style="width:150px" />
				</td>
			</tr>
			<tr>
				<th>���� ���� ����</th>
				<td class="right">
					$
					<input type="text" name="fatcaOdDpoAcntCaamt" value="${info.fatcaOdDpoAcntCaamt}" onfocus="resetValue(this)" onBlur="addComma(this)" maxLength="10" class="text right" style="width:150px" />
				</td>
			</tr>
			<tr>
				<th>�ű� ���� ����</th>
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