<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %> 

<script>

	function searchTarget2(){
		var f = document.formx;
		f.mnuId.value = 'SMS_R';
		f.action = "<cx:wc/>/acipTarget.do?method=confirmRequestHist";
		f.submit();
		showLoading(true);
	}

	function goExcel(){
		var f = document.formx;
		f.mnuId.value = 'SMS_R';
		f.target = parent.document.getElementById("ifrmExcel").name;
		f.action = "<cx:wc/>/acipTarget.do?method=confirmExcelDown";
		f.submit();
		showLoading(true);
		f.target = "";
		showLoading(false);
	}
	
	function searchPopCust(){
		var f = document.formx;
		window.open("<cx:wc/>/code.do?method=searchPopCopr&init=y&crym="+f.crym.value+"&csno="+f.csno.value,"pop","width=650,height=620,top=200,left=200");
	}	
		
	function __submit(){
		if('${sessionScope.sessionUser.mnuAuthMap.SMS_R}'=='Y'){
			searchTarget();
		}
	}


</script>
</head>
<body onLoad="init()">
    
<form name="formx" method="post">
<input type="hidden" name="mnuId">

<div id="subMain">
	
	<!----- /Search ----->
	<!----- /Title ----->
	<div class="top_title">
	<ul>
		<li class="txt">SMS�߼� �̷� ��ȸ</li>
	</ul>
	</div>
	
	<!----- /Search ----->
	<div class="sheet_search"><div>
		<table><tbody>
		<tr>
			<td><span>���س��</span></td>
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
				<input type="hidden" name="custSeq" value="${param.custSeq}" >
				<a href="javascript:searchPopCust()"><img src="<cx:wc/>/images/common/icon_search.gif"></a>
			</td>	
			<td><span>SMS�߼�����</span></td>
			<td>
				<cx:cselect dbname="smsSendCombo" name="fatcaSmsFwDge" selected="${param.fatcaSmsFwDge}" before=",��ü" style="width:85px" />
			</td>
			<td>
				<c:if test="${sessionScope.sessionUser.mnuAuthMap.SMS_R == 'Y'}">
					<a href="javascript:searchTarget()" class="button search" >��ȸ</a>
				</c:if>				
			</td>
		</tr>
		</tbody></table>
	</div></div>
		
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
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.SMS_R == 'Y'}">
				<a href="javascript:goExcel()" class="basic" >����</a>
			</c:if>			
		</li>
	</ul>	
	</div>
	

	<!----- /Grid ----->
		<display:table class="simple" name="${list}" size="${totalCnt}" id="grid" partialList="true"  decorator="fatca.common.decorator.FTCTableFormat"
			requestURI="/acipTarget.do" pagesize="${requestScope.pagingsize}" sort="external" defaultsort="1" export="false" cellspacing="0">
			<display:column property="crymFmt" title="���س��" style="text-align:center"/>
			<display:column property="smsFwDge" title="�߼�����" style="text-align:center"/>	
			<display:column property="fwDtFmt" title="�߼�����" style="text-align:center"/>	
			<display:column property="csno" title="����ȣ" style="text-align:center"/>	
			<display:column property="custNm" title="����" style="text-align:left"/>	
			<display:column property="smsNtiMpno" title="��ȭ��ȣ" style="text-align:center"/>			
			<display:column property="smsMsgCn" title="�߼۳���" maxLength="20" style="text-align:left"/>
		</display:table>
	
	<!----- //Grid ----->
	
	
</div>
</form>

</body>
</html>
