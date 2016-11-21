<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %> 
<script>

	function searchTarget2(){
 		var f = document.formx;
 		if(!isNull(f.csno, '����ȣ')){
 			f.mnuId.value = 'QNA_R';
 			f.action = "<cx:wc/>/acipResult.do?method=question";
 			f.submit();
 			showLoading(true);
 		}
	}
	
	//�ǻ�������� ����� ����
	function reset(){
		var f = document.formx;				
	}

	function searchPopCust(){
		var f = document.formx;
		window.open("<cx:wc/>/code.do?method=searchPopCopr&init=y&crym="+f.crym.value+"&csno="+f.csno.value+"&custType="+f.custType.value,"pop","width=650,height=620,top=200,left=200");
	}

	function savequestion(){
		var f = document.formx;
		
		if(inputCheck()){
			if(confirm("����������� ���� ��� �Ͻðڽ��ϱ�?")){
	 			f.mnuId.value = 'QNA_U';
				f.action = "<cx:wc/>/acipResult.do?method=saveQues";
				f.submit();
				showLoading(true);
				if('${param.mode}' == "popup"){
					opener.searchTarget2();	
					sclose();
				}
			}
		}
	}
	
	function inputCheck(){
		var f = document.formx;
		
		if('${isMainComplete}'=='Y'){
			alert('������ ���س���� �ǻ簡 �����Ǿ����ϴ�.\nFATCA ����ڿ��� �����Ͻñ� �ٶ��ϴ�.');
			return false;
		}
		
		if(isNull(f.custNm)){
			alert('��ȸ�� ���� �����ϴ�.\n�ٽ� Ȯ�� �Ͻñ� �ٶ��ϴ�.');
			return false;
		}
				
		if(isNull(f.csno, '����ȣ')){
			return false;
		}

		if(!isChecked(f.fatcaIqrDtlCd, '���� �ǰ��� �ϳ� �̻� ���õǾ�� �մϴ�.')){
			return false;
		}

		if(isNull(f.slbrOpnCn, '����������� �� �ǰ�')){
			return false;
		}
		
		
		return true;
	}
	
	function checkRdo(obj){
		var f = document.formx;
		f.fatcaIqrRscd.value = "11";
		if(obj == "01"){
			f.slbrOpnCn.value = "�ش����";	
			f.fatcaIqrRscd.value = "12";
		}else if(obj == "02"){
			f.slbrOpnCn.value = "�̱��ùα���/���ֱ���";	
		}else if(obj == "03"){
			f.slbrOpnCn.value = "����3�Ⱓ ���ֱⰣ�� 183�� �̻��ΰ�?";	
		}else if(obj == "04"){
			f.slbrOpnCn.value = "�Ա� ù�ؿ� �̱� �����ڸ� �����Ͽ��°�?";	
		}
	}

	function initScreen(){
		var f = document.formx;
		f.mode.value = '${param.mode}';
		var mode = f.mode.value;
		if(mode == "popup" ){
			document.getElementById("divPopTitle").style.display = "block";
			document.getElementById("divTitle").style.display = "none";
		}else{
			document.getElementById("divPopW").className = "";
			document.getElementById("divPopTitle").style.display = "none";
			document.getElementById("divTitle").style.display = "block";
		}
	}	

	function sclose(){
		self.close();
	}

	function init(){
		var f = document.formx;
		f.mode.value = '${param.mode}';
		var mode = f.mode.value;
		if(mode == "popup" ){
			self.window.focus();
		}
	}	

	function __submit(){
		if('${sessionScope.sessionUser.mnuAuthMap.QNA_R}'=='Y'){
			search();
		}
	}
	
</script>
</head>
<body onLoad="init()">
    
<form name="formx" method="post">
<input type="hidden" name="fatcaIqrRscd" value="${info.fatcaIqrRscd}">
<input type="hidden" name="mode" value="${param.mode}">
<input type="hidden" name="mnuId">
<input type="hidden" name="custType" value="per">
		
<c:if test="${param.mode == 'popup' }">
	<div class="popWrap" id="divPopW">
	<!----- /Title ----->
	<div class="popTop" id="divPopTitle" >
	<ul>
		<li class="left">
			����������� ���� ���
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
		<li class="txt">����������� ���� ���</li>
	</ul>
	</div>
	<!----- //Title ----->
</c:if>	


<!----- /Contents ----->
<div class="popContentWrap">	
	<div id="subMain">	
		<!----- /Search ----->
		<div class="sheet_search"><div>
			<table><tbody>
			<tr>
				<td><span>���س��</span></td>
				<td>
					<cx:cselect dbname="crymCombo" name="crym" selected="${sessionScope.sessionUser.selCrym}" style="width:85px" />
				</td>
				<td><span>����ȣ</span></td>
				<td>
					<input type="text" name="csno" value="${param.csno}" maxlength="13" onKeydown="changeSearch('custNm');changeSearch('csnoReal');changeSearch('custSeq');" class="text csno" />
					<input type="hidden" name="csnoReal" value="${param.csnoReal}" >
					<input type="text" name="custNm" value="${selCustNm}" readonly disabled class="readonly csnm" />
					<input type="hidden" name="custSeq" value="${param.custSeq}" >
					<a href="javascript:searchPopCust('per')"><img src="<cx:wc/>/images/common/icon_search.gif"></a>
				</td>
				<td>
					<c:if test="${sessionScope.sessionUser.mnuAuthMap.QNA_R == 'Y'}">
					<a href="javascript:searchTarget()" class="button search">��ȸ</a>
					</c:if>
				</td>
			</tr>
			</tbody></table>
		</div></div>
		<!----- //Search ----->
	
		<div class="h10"></div>
		
		<!----- /Title ----->
		<div class="sheet_title">
		<ul>
			<li class="txt"></li>
			<li class="btn">
				<c:if test="${sessionScope.sessionUser.mnuAuthMap.QNA_U == 'Y' && info!=null && info.fatcaIqrRscd != '10'}">
				<a href="javascript:savequestion()" class="basic">����</a>
				</c:if>
			</li>
		</ul>
		</div>
		<!----- //Title ----->
		
				<!----- /Table ----->
				<table class="table"><tbody>
				<colgroup>
				</colgroup>
				<tr>
					<th width="15%">�ǻ�����</th>
					<td class="center" width="15%">${info.ltChprNm}</td>
					<th width="15%">�ǻ�����</th>
					<td class="center" width="15%">${info.ltChDtlDtti}</td>
					<th width="15%">�ǻ����(�Ϸ�)����</th>
					<td class="center" width="15%">${info.fatcaAcipComptDt}</td>
				</tr>
				<tbody></table>
				<!----- //Table ----->
		
		<div class="h20"></div>
			
	
		<!----- /Table ----->
		<table class="table"><tbody>
		<colgroup>
			<col width="5%"><col width="*">
		</colgroup>
		<tr>
			<td class="center">
				<input type="radio" onclick="checkRdo('01')" name="fatcaIqrDtlCd" value="01" <c:if test="${info.fatcaIqrDtlCd == '01'}">checked</c:if>/>
			</td>
			<td>�ش����</td>
		</tr>
		<tr>
			<td class="center">
				<input type="radio" onclick="checkRdo('02')" name="fatcaIqrDtlCd" value="02" <c:if test="${info.fatcaIqrDtlCd == '02'}">checked</c:if>/>
			</td>
			<td>�̱��ùα���/���ֱ���</td>
		</tr>
		<tr>
			<td class="center">
				<input type="radio" onclick="checkRdo('03')" name="fatcaIqrDtlCd" value="03" <c:if test="${info.fatcaIqrDtlCd == '03'}">checked</c:if>/>
			</td>
			<td>����3�Ⱓ ���ֱⰣ�� 183�� �̻��ΰ�?</td>
		</tr>
		<tr>
			<td class="center">
				<input type="radio" onclick="checkRdo('04')" name="fatcaIqrDtlCd" value="04" <c:if test="${info.fatcaIqrDtlCd == '04'}">checked</c:if>/>
			</td>
			<td>�Ա� ù�ؿ� �̱� �����ڸ� �����Ͽ��°�?</td>
		</tr>
		<tbody></table>
		
	
		<div class="h20"></div>
		
		<!----- /Title ----->
		<div class="sheet_title">
		<ul>
			<li class="txt">����������� �� �ǰ�</li>
			</li>
		</ul>
		</div>
		<!----- //Title ----->
	
		<textarea rows="10" name="slbrOpnCn" style="width:98%" imemode="Hangul"  >${info.slbrOpnCn}</textarea>
	
	</div>
</div>
</form>
</body>
</html>