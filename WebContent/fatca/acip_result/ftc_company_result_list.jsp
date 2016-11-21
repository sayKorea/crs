<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %> 

<script>
	function searchTarget2(){
		var f = document.formx;
		if(!isNull(f.slsBrno, '������') && !isNull(f.brnoCombo, '������')){
			if(checkCustNo()){
				f.mnuId.value = 'CRES_R';
				f.action = "<cx:wc/>/acipResult.do?method=companyResult";
				f.submit();
				showLoading(true);
			}
		}
	}

	function goExcel(){
		var f = document.formx;
		f.mnuId.value = 'CRES_R';
		f.target = parent.document.getElementById("ifrmExcel").name;
		f.action = "<cx:wc/>/acipResult.do?method=companyExcelDown";
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
	
	function linkCompany(objcrym, objcsno, objcustnm, objcustseq){
		window.open("<cx:wc/>/acipTarget.do?method=companyView&crym="+objcrym+"&csno="+objcsno+"&custNm="+objcustnm+"&custSeq="+objcustseq+"&mode=popup","popPersonal","width=1180,height=730,top=200,left=200, resizable=yes");
	}	

	function searchPopCust(){
		var f = document.formx;
		window.open("<cx:wc/>/code.do?method=searchPopCopr&init=y&crym="+f.crym.value+"&csno="+f.csno.value+"&custType="+f.custType.value,"pop","width=650,height=620,top=200,left=200");
	}	
	
	function searchPopSls(){
		var f = document.formx;
		window.open("<cx:wc/>/code.do?method=searchPopSls&init=y","pop","width=650,height=620,top=200,left=200");			
	}	

	function goPopup(dist){
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
	
	
	function __submit(){
		if('${sessionScope.sessionUser.mnuAuthMap.CRES_R}'=='Y'){
			searchTarget();
		}
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
		<li class="txt">���� �ǻ��� ��� ���</li>
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
					<cx:cselect dbname="branchCombo" name="brnoCombo" selected="${sessionScope.sessionUser.selSlsBrno}" before=",�ش� ���� ����" onchange="changeSearch2('com');" style="width:192px" />
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
			<td><span>���±ݾױ�������</span></td>
			<td>
				<cx:cselect dbname="amtSctCombo" name="fatcaAmtSctClacd" selected="${param.fatcaAmtSctClacd}" before=",��ü" style="width:85px" />
			</td>
			<td></td>
		</tr>
		<tr>
			<td><span>����ǻ���</span></td>
			<td>
				<cx:cselect dbname="czAcipCombo" name="fatcaCzAcipRscd" selected="${param.fatcaCzAcipRscd}" before=",��ü" style="width:85px" />
			</td>
			<td><span>FATCAȮ�μ��ǻ���</span></td>
			<td>
				<cx:cselect dbname="cfrrptAcipCombo" name="fatcaCfrrptAcipRscd" selected="${param.fatcaCfrrptAcipRscd}" before=",��ü" style="width:85px" />
			</td>
			<td><span>FATCA������</span></td>
			<td>
				<cx:cselect dbname="fatcaCustDvcdCombo" name="fatcaCustDvcd" selected="${param.fatcaCustDvcd}" before=",��ü" style="width:150px" />
			</td>			
		</tr>
		<tr>	
			<td><span>�����󿩺�</span></td>
			<td>
				<cx:cselect dbname="reptTrgetCombo" name="fatcaReptTrgetDvcd" selected="${param.fatcaReptTrgetDvcd}" before=",��ü" style="width:85px" />
			</td>			
			<td><span>�ǻ�ϷῩ��</span></td>
			<td>
				<cx:cselect dbname="ynCombo" name="fatcaAcipComptYn" selected="${param.fatcaAcipComptYn}" before=",��ü" style="width:55px" />
			</td>	
			<td><span>�ǻ�����</span></td>
			<td>
				<input type="text" name="fatcaAcipCgpEnob" value="${sessionScope.sessionUser.selFatcaAcipCgpEnob}" maxlength="7" onChange="changeSearch('fatcaAcipCgpEnnm')" class="text enob"/>
				<input type="text" name="fatcaAcipCgpEnnm" value="${sessionScope.sessionUser.selFatcaAcipCgpEnnm}" size="7" readonly disabled class="readonly ennm" />
				<a href="javascript:goPopup('cgp')"><img src="<cx:wc/>/images/common/icon_search.gif"></a>
			</td>
		</tr>
		<tr>
			<td><span>����ȣ</span></td>
			</td>
			<td>
				<input type="text" name="csno" value="${param.csno}" maxlength="13" onKeydown="changeSearch('custNm');changeSearch('csnoReal');changeSearch('custSeq');" class="text csno" />
				<input type="hidden" name="csnoReal" value="${param.csnoReal}" >
				<input type="text" name="custNm" value="${selCustNm}" readonly disabled class="readonly coprnm" />
				<input type="hidden" name="custSeq" value="${param.custSeq}">
				<a href="javascript:searchPopCust('com')"><img src="<cx:wc/>/images/common/icon_search.gif"></a>
			</td>
			<td>
				<c:if test="${sessionScope.sessionUser.mnuAuthMap.CRES_R == 'Y'}">
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
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.CRES_R == 'Y'}">
				<a href="javascript:goExcel()" class="basic" >����</a>
			</c:if>
		</li>
	</ul>	
	</div>
	

	<!----- /Grid ----->
		<display:table class="simple" name="${list}" size="${totalCnt}" id="grid" partialList="true"  decorator="fatca.common.decorator.FTCTableFormat"
			requestURI="/acipResult.do" pagesize="${requestScope.pagingsize}" sort="external" defaultsort="1" export="false" cellspacing="0">
			<%-- <display:column title="����" style="text-align:center">
				<input type="checkbox" name="selAll" style="border:0px">
			</display:column> --%>
			<display:column property="slsBrno" title="������<br>�ڵ�" groupTitle ="���ΰ�����" style="text-align:center"/>
			<display:column property="slsBrnoNm" title="��������" groupTitle ="���ΰ�����" style="text-align:center"/>
			<display:column property="csno" title="����ȣ" groupTitle ="���ΰ�����" style="text-align:center"/>	
			<display:column title="����" groupTitle ="���ΰ�����" style="text-align:center">
				<a href="javascript:linkCompany('${grid.crym}','${grid.csno}','${grid.custNm}','${grid.custSeq}');" title="${grid.custNm}">${grid.custNmMsk}</a>
			</display:column>
			<display:column property="rnno" title="���ι�ȣ<br>(����ڵ�Ϲ�ȣ)" groupTitle ="���ΰ�����" style="text-align:center"/>		
			<display:column property="fatcaAcipCgpEnnm" title="�ǻ�<br>�����" groupTitle ="�ǻ�����" style="text-align:center"/>	
			<display:column property="fatcaAmtSctClacdNm" title="���±ݾ�<br>��������" groupTitle ="�ǻ�����" style="text-align:center"/>				
			<display:column property="fatcaCzAcipRscdNm" title="����<br>�ǻ���" groupTitle ="�ǻ�����" style="text-align:center"/>
			<display:column property="fatcaCfrrptAcipRscdNm" title="FATCAȮ�μ�<br>�ǻ���"  groupTitle ="�ǻ�����" style="text-align:center"/>			
			<display:column property="fatcaReptTrgetDvcdNm" title="����<br>��󿩺�" groupTitle ="�������" style="text-align:center"/>	
			<display:column property="fatcaAcipComptDt" title="�ǻ�<br>�Ϸ���" groupTitle ="�������" style="text-align:center"/>	
			<display:column property="fatcaCustDvcdNm" title="FATCA<br>�� ����" groupTitle ="�������" style="text-align:center"/>	
		</display:table>
	
	<!----- //Grid ----->
	
</div>
</form>

</body>
</html>
