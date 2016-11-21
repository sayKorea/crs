<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %> 

<script>

	function searchTarget2(){
		var f = document.formx;
		
		if(!isNull(f.slsBrno,'��������ȣ') && !isNull(f.brnoCombo, '������')){
			if(checkCustNo()){
				f.mnuId.value = 'CRPT_R';			
				f.action = "<cx:wc/>/acipReport.do?method=companyReportList";
				f.submit();
				showLoading(true);
			}
		}	
	}

	function goExcel(){
		var f = document.formx;
		f.mnuId.value = 'CRPT_R';
		f.target = parent.document.getElementById("ifrmExcel").name;
		f.action = "<cx:wc/>/acipReport.do?method=companyExcelDown";
		f.submit();
		showLoading(true);
		f.target = "";
		showLoading(false);
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
	
	function linkCompany(objcrym, objcsno, objcustnm){
		//window.open("<cx:wc/>/acipTarget.do?method=personalView&crym="+objcrym+"&csno="+objcsno+"&custNm="+objcustnm+"&mode=popup","popPersonal","width=1180,height=720,top=200,left=200, resizable=yes");
	}	
	

	function searchPopCust(){
		var f = document.formx;
		window.open("<cx:wc/>/code.do?method=searchPopCopr&init=y&crym="+f.crym.value+"&csno="+f.csno.value+"&custType="+f.custType.value,"pop","width=650,height=620,top=200,left=200");
	}
	
	function searchPopSls(){
		var f = document.formx;
		window.open("<cx:wc/>/code.do?method=searchPopSls&init=y&crym="+f.crym.value,"pop","width=650,height=620,top=200,left=200");
	}
	
		
	function __submit(){
		if('${sessionScope.sessionUser.mnuAuthMap.CRPT_R}'=='Y'){
			searchTarget();
		}
	}

	function addComma(obj){
		insertSeperatorByWon(obj,",");
	}
	
	function init(){
		
	}

</script>
</head>
<body onLoad="init()">
    
<form name="formx" method="post">
<input type="hidden" name="mnuId">
<input type="hidden" name="custType" value="com">
 
<div id="subMain">
	
	<!----- /Search ----->
	<!----- /Title ----->
	<div class="top_title">
	<ul>
		<li class="txt">���� ���� ��ȸ</li>
	</ul>
	</div>
	
	<!----- /Search ----->
	<div class="sheet_search"><div>
		<table><tbody>
		<tr>
			<td><span>������</span></td>
			<td>
				<c:if test="${sessionScope.sessionUser.isFatca}">
					<input type="text" name="slsBrno" value="${sessionScope.sessionUser.selSlsBrno}" onFocus="this.select()" maxlength="3" onchange="changeSearch2('tex');" class="text brno"/>
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
			<td><span>��ǰ����</span></td>
			<td>
				<cx:cselect dbname="combo801" name="fatcaPdClacd" selected="${param.fatcaPdClacd}" before=",��ü" style="width:85px" />
			</td>
			<td></td>
		</tr>
		<tr>
			<td>
				<span>����ȣ</span>
			</td>
			<td>
				<input type="text" name="csno" value="${param.csno}" maxlength="13" onKeydown="changeSearch('custNm');changeSearch('csnoReal');changeSearch('custSeq');" class="text csno" />
				<input type="hidden" name="csnoReal" value="${param.csnoReal}" >
				<input type="text" name="custNm" value="${selCustNm}" readonly disabled class="readonly coprnm" />
				<input type="hidden" name="custSeq" value="${param.custSeq}" >
				<a href="javascript:searchPopCust()"><img src="<cx:wc/>/images/common/icon_search.gif"></a>
			</td>	
			<td>
				<span>�ݾ�</span>
			</td>
			<td>
				<input type="text" name="stAmt" value="${param.stAmt}" maxlength="10" class="text" />~
				<input type="text" name="edAmt" value="${param.edAmt}" maxlength="10" class="text" />
			</td>			
			<td>
				<c:if test="${sessionScope.sessionUser.mnuAuthMap.CRPT_R == 'Y'}">
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
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.CRPT_R == 'Y'}">
				<a href="javascript:goExcel()" class="basic" >����</a>
			</c:if>			
		</li>
	</ul>	
	</div>
	
	<!----- /Grid ----->
		<display:table class="simple" name="${list}" size="${totalCnt}" id="grid" partialList="true"  decorator="fatca.common.decorator.FTCTableFormat"
			requestURI="/acipReport.do" pagesize="${requestScope.pagingsize}" sort="external" defaultsort="1" export="false" cellspacing="0">
			<display:column property="slsBrno" title="������<br>�ڵ�" style="text-align:center"/>
			<display:column property="slsBrnm" title="��������" maxLength="3" style="text-align:center"/>
			<display:column property="fatcaCoprKnm" title="����<br>(�ѱ�)" groupTitle="��������" style="text-align:left"/>
			<display:column property="fatcaCoprEnm" title="����<br>(����)" groupTitle="��������" style="text-align:left"/>
			<display:column property="fatcaTxpayrNo" title="EIN" groupTitle="��������" style="text-align:center"/>
			<display:column property="fatcaCoprEngAddr" title="�ּ�" groupTitle="��������" maxLength="8" style="text-align:left"/>
			<display:column property="perNm" title="��������" groupTitle="����������" maxLength="4" style="text-align:center"/>
			<display:column property="fatcaPrsnlEngAddr" title="�ּ�" groupTitle="����������" maxLength="8" style="text-align:left"/>
			<display:column property="perTxpayrNo" title="TIN" groupTitle="����������" maxLength="4" style="text-align:center"/>
			<display:column property="acno" title="���¹�ȣ" maxLength="4" style="text-align:center"/>
			<display:column property="acntBalamt" title="�ܾ�" style="text-align:right"/>
			<display:column property="ntrstAmt" title="�����Ѿ�" style="text-align:right"/>
			<display:column property="shramt" title="����Ѿ�" style="text-align:right"/>
			<display:column property="etcm" title="��Ÿ����<br>�Ѿ�" style="text-align:right"/>
			<display:column property="totNcmRbam" title="�Ѱŷ�<br>����" style="text-align:right"/>
		</display:table>
	
	<!----- //Grid ----->
	
</div>
</form>

</body>
</html>
