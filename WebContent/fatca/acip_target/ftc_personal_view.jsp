<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %> 

<script>
	function searchTarget(){
		var f = document.formx;
 		if(!isNull(f.csno, '����ȣ')){
 			showLoading(true);
			f.mnuId.value = 'PDTL_R';
 			f.action = "<cx:wc/>/acipTarget.do?method=personalView";
 			f.submit();	
 			showLoading(true);
 		}
	}
	

	function __submit(){
		if('${sessionScope.sessionUser.mnuAuthMap.PDTL_R}'=='Y'){
			searchPersonal();
		}
	}

	function goAdupHist(go, crym, csno, custseq){
		showLoading(true);
		document.getElementById("ifrm").src = "<cx:wc/>/acipTarget.do?method=adupHistDetail&type="+go+"&crym="+crym+"&csno="+csno;	
	}

	function goReptHist(go, crym, reptdtfmt, csno, custseq){	
		showLoading(true);
		document.getElementById("ifrm").src = "<cx:wc/>/acipTarget.do?method=reptHistDetail&type="+go+"&crym="+crym+"&reptDtFmt="+reptdtfmt+"&csno="+csno;	
	}

	function tabClick(objtype){
		/* �ʱ� �Ǽ��� */
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
			document.getElementById("ifrm").src = "<cx:wc/>/acipTarget.do?method=adupHistDetail&type=adupHist";			
		}else if(objtype == "reptHist"){
			document.getElementById("tab4t").style.display = "block";
			document.getElementById("tab4c").style.display = "block";
			document.getElementById("tab4frm").style.display = "block";
			document.getElementById("tab4").className = "selected";
			document.getElementById("ifrm").src = "<cx:wc/>/acipTarget.do?method=reptHistDetail&type=reptHist";
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
<input type="hidden" name="mode" value="${param.mode}">
<input type="hidden" name="slsBrno" value="${param.slsBrno}">
<input type="hidden" name="mnuId">
<input type="hidden" name="custType" value="per">
			
<c:if test="${param.mode == 'popup' }">
	<div class="popWrap" id="divPopW">
	<!----- /Title ----->
	<div class="popTop" id="divPopTitle" >
	<ul>
		<li class="left">
			���� ����ȸ
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
		<li class="txt">���� ����ȸ</li>
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
				<td><span>���س��</spanp></td>
				<td>
					<cx:cselect dbname="crymCombo" name="crym" selected="${sessionScope.sessionUser.selCrym}" style="width:85px" />
				</td>
			<td>
				<span>����ȣ</span>
			</td>
			<td>
				<input type="text" name="csno" value="${param.csno}" maxlength="13" onKeydown="changeSearch('custNm');changeSearch('csnoReal');changeSearch('custSeq');" class="text csno" />
				<input type="hidden" name="csnoReal" value="${param.csnoReal}" >
				<input type="text" name="custNm" value="${selCustNm}" readonly disabled class="readonly csnm" />
				<input type="hidden" name="custSeq" value="${param.custSeq}" />
				<a href="javascript:searchPopCust('per')"><img src="<cx:wc/>/images/common/icon_search.gif"></a>
			</td>			
			<td>
				<c:if test="${sessionScope.sessionUser.mnuAuthMap.PDTL_R == 'Y'}">
					<a href="javascript:searchTarget()" class="button search" >��ȸ</
				</c:if>
			</td>
			</tr>
			</tbody></table>
		</div></div>
		
		<!----- /Title ----->
		<div class="sheet_title">
		<ul>
			<li class="txt">�⺻����</li>
		</ul>
		</div>
		<!----- //Title ----->
	
		<!----- /Table ----->
		<table class="table"><tbody>
		<colgroup>
			<col width="9%"><col width="10%">
			<col width="9%"><col width="10%">
			<col width="10%"><col width="10%">
			<col width="10%"><col width="10%">
		</colgroup>
		<tr>
			<th>����ȣ</th>
				<td class="center" name="csno" >${prsnlBas.csno}</td>
			<th>����</th>
				<td class="center" name="custNm" >${prsnlBas.custNm}</td>
			<th>���±ݾױ�������</th>
				<td class="center" name="fatcaAmtSctClacdNm" >${prsnlBas.fatcaAmtSctClacdNm}</td>
			<th>FATCA ������</th>
				<td class="center" name="fatcaCustDvcdNm" >${prsnlBas.fatcaCustDvcdNm}</td>
		</tr>
		<tr>
			<th>�Ǹ��ȣ</th>
				<td class="center" name="rnno" >${prsnlBas.rnno}</td>
			<th>����</th>
				<td class="center" name="custNacd" >${prsnlBas.custNacd}</td>
			<th>������ ����</th>
				<td class="center" name="taxtnNacd" >${prsnlBas.taxtnNacd}</td>
			<th>����</th>
				<td class="center" name="sex" >${prsnlBas.sex}</td>
		</tr>
		<tr>
			<th>��ȭ��ȣ</th>
				<td class="center" name="phone" >${prsnlBas.phone}</td>
			<th>�ּ�</th>
				<td class="left" name="address" colspan="5">${prsnlBas.address}</td>
		</tr>
		<tr>
			<th>�������ڵ�/��</th>
				<td class="center" name="slsBrnoFmt">${prsnlBas.slsBrnoFmt}</td>
			<th>�ǻ�å����</th>
				<td class="center" name="fatcaAcipRpprEnnm">${prsnlBas.fatcaAcipRpprEnnm}</td>
			<th>�ǻ�����</th>
				<td class="center" name="fatcaAcipCgpEnnm">${prsnlBas.fatcaAcipCgpEnnm}</td>
			<th>�����������</th>
				<td class="center" name="fatcaCustRelMngrEnnm">${prsnlBas.fatcaCustRelMngrEnnm}</td>
		</tr>
		<tbody></table>
		<!----- //Table ----->
	
		<!----- /Title ----->
		<div class="sheet_title">
		<ul>
			<li class="txt">��������<span> (����:��, $)</span></li>
		</ul>
		</div>
		<!----- //Title ----->
	
		<!----- /Table ----->
		<table class="table"><tbody>
		<colgroup>
			<col width="10%"><col width="10%">
		</colgroup>
		<tr>
			<th class="center" colspan="2">�ջ�ݾ�</th>		
			<th class="center" colspan="2">���ݰ���</th>
			<th class="center" colspan="2">��Ź����</th>
			<th class="center" colspan="2">���б�/��ä��</th>
			<th class="center" colspan="2">����/���ݰ���</th>
		</tr>
		<tr>
			<th class="center">��ȭ</th>
			<th class="center">��ȭ</th>
			<th class="center">��ȭ</th>
			<th class="center">��ȭ</th>
			<th class="center">��ȭ</th>
			<th class="center">��ȭ</th>
			<th class="center">��ȭ</th>
			<th class="center">��ȭ</th>
			<th class="center">��ȭ</th>
			<th class="center">��ȭ</th>
		</tr>
		<tr>		
			<td class="right" name="acntSmamt">${prsnlBas.acntSmamt}</td>
			<td class="right" name="acntSumtUsdCnvtAmt">${prsnlBas.acntSumtUsdCnvtAmt}</td>
			<td class="right" name="dpoSmamt">${prsnlBas.dpoSmamt}</td>
			<td class="right" name="acntSmamtUsdCnvtAmt">${prsnlBas.dpoSumtUsdCnvtAmt}</td>
			<td class="right" name="trstSmamt">${prsnlBas.trstSmamt}</td>
			<td class="right" name="acntSmamtUsdCnvtAmt">${prsnlBas.trstSumtUsdCnvtAmt}</td>
			<td class="right" name="dbtrSmamt">${prsnlBas.dbtrSmamt}</td>
			<td class="right" name="acntSmamtUsdCnvtAmt">${prsnlBas.dbtrSumtUsdCnvtAmt}</td>
			<td class="right" name="penSmamt">${prsnlBas.penSmamt}</td>
			<td class="right" name="acntSmamtUsdCnvtAmt">${prsnlBas.penSumtUsdCnvtAmt}</td>
		</tr>
		<tbody></table>
		<!----- //Table ----->
	
		<!----- /Title ----->
		<div class="sheet_title">
		<ul>
			<li class="txt">�ǻ�����</li>
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
			<th class="center">����ǻ���</th>
				<td class="center" name="fatcaCzAcipRscdNm">${prsnlBas.fatcaCzAcipRscdNm}</td>
			<th class="center">�����ǻ���</th>
				<td class="center" name="fatcaDocAcipRscdNm">${prsnlBas.fatcaDocAcipRscdNm}</td>
			<th class="center">�����������<br /> ���ǰ��</th>
				<td class="center" name="fatcaIqrRscdNm">${prsnlBas.fatcaIqrRscdNm}</td>
			<th class="center">FATCA Ȯ�μ�<br />�ǻ���</th>
				<td class="center" name="fatcaCfrrptAcipRscdNm">${prsnlBas.fatcaCfrrptAcipRscdNm}</td>
		</tr>
		<tbody></table>
		<!----- //Table ----->
	
		<!----- /Tab ----->
		<div class="mainTab">
			<ul class="tabMenu">
				<li id="tab1" class="selected"><a href="javascript:tabClick('acipHist')">�ǻ��̷�</a></li>
				<li id="tab2"><a href="javascript:tabClick('prstHist')">FATCA Ȯ�μ� �����̷�</a></li>
				<li id="tab3"><a href="javascript:tabClick('adupHist')">�ջ��̷�</a></li>
				<li id="tab4"><a href="javascript:tabClick('reptHist')">�����̷�</a></li>
			</ul>
		</div>
		<!----- //Tab ----->
	
		<!----- /Grid ----->
		<!-- �ǻ��̷� -->
		<table class="simple" id="tab1t" style="display:block"><tbody>
		<colgroup>
	        <col width="8%">
	        <col width="9%">
	        <col width="13%">
	        <col width="10%">
	        <col width="10%">
	        <col width="14%">
	        <col width="14%">
	        <col width="10%">
	        <col width="12%">
		</colgroup>
		<tr>
			<th class="center">���س��</th>
			<th class="center">�ǻ�Ϸ�����</th>
			<th class="center">���±ݾ�<br>��������</th>
			<th class="center">����ǻ���</th>
			<th class="center">�����ǻ���</th>
			<th class="center">�����������<br>���ǰ��</th>
			<th class="center">FATCAȮ�μ�<br>�ǻ���</th>
			<th class="center">�����󿩺�</th>
			<th class="center">FATCA<br>������</th>
		</tr>
		
		<c:forEach items="${acipHist}" var="func">
		<tr>
	       	<td class="center">&nbsp;${func.crymFmt}</td>
	       	<td class="center">${func.comptDtFmt}</td>
	       	<td class="center">${func.fatcaAmtSctClacdNm}</td> 	
	       	<td class="center">${func.fatcaCzAcipRscdNm}</td>
	       	<td class="center">${func.fatcaDocAcipRscdNm}</td>
	       	<td class="center">${func.fatcaIqrRscdNm}</td>
	       	<td class="center">${func.fatcaCfrrptAcipRscdNm}</td>
	       	<td class="center">${func.fatcaReptTrgetDvcdNm}</td>
	       	<td class="center">${func.fatcaCustDvcdNm}</td>       
		</tr>
		</c:forEach>
		
		<tbody></table>
		<!----- //Grid ----->
		
		
		<!----- /Grid ----->
		<!-- FATCA Ȯ�μ������̷� -->
		<table class="simple" id="tab2t" style="display:none"><tbody>
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
			<th class="center">���س��</th>
			<th class="center">��������</th>
			<th class="center">Ȯ�μ�<br>�Ϸù�ȣ</th>
			<th class="center">FATCA<br>������</th>
			<th class="center">TIN</th>
			<th class="center">First<br>Name</th>
			<th class="center">Last<br>Name</th>
			<th class="center">�ּ�</th>
			<th class="center">���ⱸ��</th>
		</tr>
		
		<c:forEach items="${prstHist}" var="func">
		<tr>
	       	<td class="center">&nbsp;${func.crymFmt}</td>
	       	<td class="center">${func.prstDtFmt}</td>
	       	<td class="center">${func.fatcaCfrrptSeq}</td>
	       	<td class="center">${func.fatcaCustTpcdNm}</td> 	
	       	<td class="center">${func.fatcaTxpayrNo}</td>
	       	<td class="center">${func.fatcaEngFsn}</td>
	       	<td class="center">${func.fatcaEnm}</td>
	       	<td class="center">${func.fatcaPrsnlHgAddr}</td>
	       	<td class="center">${func.fatcaPrstDvcdNm}</td>        
		</tr>
		</c:forEach>	
		<c:if test="${empty prstHist}">
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
		<!----- //Grid ----->
		
		
		<!----- /Grid ----->
		<!-- �ջ��̷� -->
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
				<th class="center" rowspan="2">���س��</th>	
				<th class="center" rowspan="2">�ջ�����</th>	
				<th class="center" colspan="2">�ջ�ݾ�</th>		
				<th class="center" colspan="2">���ݰ���</th>
				<th class="center" colspan="2">��Ź����</th>
				<th class="center" colspan="2">���б�/��ä��</th>
				<th class="center" colspan="2">����/���ݰ���</th>
			</tr>
			<tr>
				<th class="center">��ȭ</th>
				<th class="center">��ȭ</th>
				<th class="center">��ȭ</th>
				<th class="center">��ȭ</th>
				<th class="center">��ȭ</th>
				<th class="center">��ȭ</th>
				<th class="center">��ȭ</th>
				<th class="center">��ȭ</th>
				<th class="center">��ȭ</th>
				<th class="center">��ȭ</th>
			</tr>
			
			<c:forEach items="${adupHist}" var="func">
			<tr>
		       	<td class="center">&nbsp;${func.crymFmt}</td>
		       	<td class="center"><a href="javascript:goAdupHist('adupHist','${func.crym}','${func.csno}','${func.custSeq}');">${func.fatcaAdupdtFmt}</a></td>
		       	<td class="right">${func.acntSmamt}</td> 	
		       	<td class="right">${func.acntSumtUsdCnvtAmt}</td>
		       	<td class="right">${func.dpoSmamt}</td>
		       	<td class="right">${func.dpoSumtUsdCnvtAmt}</td>
		       	<td class="right">${func.trstSmamt}</td>
		       	<td class="right">${func.trstSumtUsdCnvtAmt}</td>
		       	<td class="right">${func.dbtrSmamt}</td>
		       	<td class="right">${func.dbtrSumtUsdCnvtAmt}</td>
		       	<td class="right">${func.penSmamt}</td>
		       	<td class="right">${func.penSumtUsdCnvtAmt}</td>
		       	
			</tr>
			</c:forEach>
			
		<tbody></table>	
			
			
		<div class="sheet_title" id="tab3c" style="display:none">	
			<ul>
			<li class="txt">���� ��<span> (����:��, $)</span></li>
			</ul>
		</div>
		
	
		<!----- /Grid ----->
		<!-- �����̷� -->
		<table class="table" id="tab4t" style="display:none"><tbody>		
			<colgroup>
		        <col width="25%">
		        <col width="25%">
		        <col width="25%">
		        <col width="25%">
		    </colgroup>
			<tr>
				<th class="center">���س��</th>
				<th class="center">��������</th>
				<th class="center">������</th>
				<th class="center">FATCA������</th>
			</tr>
			
			<c:forEach items="${reptHist}" var="func">
			<tr>
		       	<td class="center">${func.crymFmt}</td>
		       	<td class="center"><a href="javascript:goReptHist('reptHist','${func.crym}','${func.reptDtFmt}','${func.csno}','${func.custSeq}');">${func.reptDtFmt}</a></td>
		       	<td class="center">${func.fatcaReptTrgetDvcdNm}</td>
		       	<td class="center">${func.fatcaCustDvcdNm}</td>
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
				<li class="txt">����󼼳���<span> (����:��, $)</span></li>
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
</div>
</form>
<iframe name="ifrmPopCust" id="ifrmPopCust" width="0" height="0" border="0"></iframe>
</body>
</html>
