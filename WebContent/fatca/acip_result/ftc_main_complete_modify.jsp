<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE HTML>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %> 

<script>
	
	function search(){
		var f = document.formx;
		if(!isNull(f.slsBrno, '������') && !isNull(f.brnoCombo, '������')){
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
		var conMsg = '�ǻ� �Ϸ� �Ǹ� �ش� ���س���� ��� �Է��۾��� ���ܵ˴ϴ�.\n��, �ʿ�� ��Ұ� �����մϴ�.\n�����Ͻðڽ��ϱ�?';
		if(dist=='N'){
			conMsg = '�Ϸᰡ ��ҵǸ� ���ȵǾ��� �Է��۾��� �����մϴ�.\n�����Ͻðڽ��ϱ�?';
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
			popname="�ǻ�����";
		}else if(dist == 'rppr'){		
			popname="�ǻ�å����";
		}else if(dist == 'rel'){		
			popname="�����������";
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
		<li class="txt">���� �ǻ�Ϸ�</li>
	</ul>
	</div>
	<!----- /Search ----->
	<div class="sheet_search"><div>
		<table><tbody>
		<tr>
			<td><span>������</span></td>
			<td>
				<c:if test="${sessionScope.sessionUser.isFatca}">
					<input type="text" name="slsBrno" value="${sessionScope.sessionUser.selSlsBrno}" onFocus="this.select()" maxlength="3" onChange="changeSearch2('tex');" class="text brno"/>
					<cx:cselect dbname="branchCombo" name="brnoCombo" selected="${sessionScope.sessionUser.selSlsBrno}" before=",�ش� ���� ����" after="999,[999] �����հ�" onchange="changeSearch2('com');" style="width:192px" />
					
				</c:if> 
				<c:if test="${!sessionScope.sessionUser.isFatca}">
					<input type="text" name="slsBrno" value="${sessionScope.sessionUser.selSlsBrno}" maxlength="3" readonly class="readonly brno"/>
					<input type="text" name="slsBrnm" value="${sessionScope.sessionUser.selSlsBrnm}" readonly disabled class="readonly brnm" />
				</c:if>
			</td>
			<td><span>���س��</span></td>
			<td>
				<cx:cselect dbname="crymCombo" name="crym" selected="${sessionScope.sessionUser.selCrym}" style="width:85px" />
			</td>
			<td>
				<%-- <c:if test="${sessionScope.sessionUser.mnuAuthMap.MBR_R == 'Y'}"> --%>
					<a href="javascript:search()" class="button search" >��ȸ</a>
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
				<th>��ü���� (�Ϸ�/���)</th>
				<td class="center">
					${info.ttotComp} / ${info.ttotCust} (��)
				</td>
			</tr>
			<tr>
				<th>���ΰ��� (�Ϸ�/���)</th>
				<td class="center">
					${info.tperComp} / ${info.tperCust} (��)
				</td>
			</tr>
			<tr>
				<th>���ΰ��� (�Ϸ�/���)</th>
				<td class="center">
					${info.tcomComp} / ${info.tcomCust} (��)
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
				<th>�Ϸ�����</th>
				<td class="center">
					${info.cgpEnobNm}
				</td>
			</tr>
			<tr>
				<th>�Ϸ�����</th>
				<td class="center">
					${info.clsnDt}&nbsp;
				</td>
			</tr>
			<tr>
				<th>�ǻ��������</th>
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
			<span>[ �� ${totalCnt} �� ]</span>			
		</li>		
		<li class="btn">
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.MBR_U == 'Y'}">
				<c:if test="${isMainComplete != 'Y'}">
					<a href="javascript:goAction('Y')" class="basic">�ǻ�Ϸ�</a>
				</c:if>
				<c:if test="${isMainComplete == 'Y'}">
					<a href="javascript:goAction('N')" class="basic">�Ϸ����</a>
				</c:if>
			</c:if>
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.MBR_R == 'Y'}">
				<a href="javascript:goExcel()" class="basic" >����</a>
			</c:if>
		</li>
	</ul>
	</div>
	<!----- //Title ----->
	

	<!----- /Grid ----->
		<display:table class="simple" name="${list}" size="${totalCnt}" id="grid" partialList="true" decorator="fatca.common.decorator.FTCTableFormat"
			requestURI="/acipResult.do" pagesize="13" sort="external" defaultsort="1" export="false" cellspacing="0">
			<display:column property="slsBrno" title="������<br>�ڵ�" style="text-align:center"/>
			<display:column property="slsBrnoNm" title="������" maxLength="5" style="text-align:center"/>
			<display:column property="slsStatus" title="�������" style="text-align:center"/>
			<display:column property="fatcaAcipRpprEnobNm" title="�ǻ�<br>å����" style="text-align:center"/>
			<display:column property="totCust" title="�Ѱ�" groupTitle="��ü��" style="text-align:right"/>
			<display:column property="totComp" title="�ǻ�<br>�Ϸ�" groupTitle="��ü��" style="text-align:right"/>
			<display:column property="totUncomp" title="�ǻ�<br>�̿Ϸ�" groupTitle="��ü��" style="text-align:right"/>
			<display:column property="totReport" title="����<br>���" groupTitle="��ü��" style="text-align:right"/>
			<display:column property="comCust" title="�Ѱ�" groupTitle="���ΰ�" style="text-align:right"/>
			<display:column property="comComp" title="�ǻ�<br>�Ϸ�" groupTitle="���ΰ�" style="text-align:right"/>
			<display:column property="comUncomp" title="�ǻ�<br>�̿Ϸ�" groupTitle="���ΰ�" style="text-align:right"/>
			<display:column property="comReport" title="������" groupTitle="���ΰ�" style="text-align:right"/>
			<display:column property="perCust" title="�Ѱ�" groupTitle="���ΰ�" style="text-align:right"/>
			<display:column property="perComp" title="�ǻ�<br>�Ϸ�" groupTitle="���ΰ�" style="text-align:right"/>
			<display:column property="perUncomp" title="�ǻ�<br>�̿Ϸ�" groupTitle="���ΰ�" style="text-align:right"/>
			<display:column property="perReport" title="����<br>���" groupTitle="���ΰ�" style="text-align:right"/>
		</display:table>
	
	<!----- //Grid ----->
	
</div>
</form>

</body>
</html>
