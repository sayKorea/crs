<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %> 

<script>
	
	function searchTarget2(){
		var f = document.formx;
		if(!isNull(f.slsBrno, '������') && !isNull(f.brnoCombo, '������')){
			showLoading(true);
			f.mnuId.value = 'PTAR_R';
			f.action = "<cx:wc/>/acipTarget.do?method=personalList";
			f.submit();
			showLoading(true);
		}
	}

	function goExcel(){
		var f = document.formx;
		f.mnuId.value = 'PTAR_R';
		f.target = parent.document.getElementById("ifrmExcel").name;
		f.action = "<cx:wc/>/acipTarget.do?method=personalExcelDown";
		f.submit();
		showLoading(true);
		f.target = "";
		showLoading(false);
	}
	
	function linkPersonal(objcrym, objcsno, objcustnm){
		window.open("<cx:wc/>/acipTarget.do?method=personalView&crym="+objcrym+"&csno="+objcsno+"&custNm="+objcustnm+"&mode=popup","popPersonal","width=1180,height=730,top=200,left=200, resizable=yes");
	}	
	

	function searchPopCust(){
		var f = document.formx;
		window.open("<cx:wc/>/code.do?method=searchPopCopr&init=y&crym="+f.crym.value+"&csno="+f.csno.value+"&custType="+f.custType.value,"pop","width=650,height=620,top=200,left=200");
	}	
		
	function changePop(dist){
				
		if(checkInput()){
			goPopup(dist);
		}		
	}	
	
	function goPopup(dist){
		
		var f = document.formx;
		if(dist == 'sls'){		
			window.open("<cx:wc/>/code.do?method=searchPopSls&init=y&crym="+f.crym.value+"&dist="+dist+"&csnoArr="+f.csnoArr.value,"pop","width=650,height=620,top=200,left=200");	
		}else{	
			if(dist == 'cgp'){		
				popname="�ǻ�����";
			}else if(dist == 'rppr'){		
				popname="�ǻ�å����";
			}else if(dist == 'rel'){		
				popname="�����������";
			}
			window.open("<cx:wc/>/code.do?method=searchPopEmp&init=y&slsBrno="+f.slsBrno.value+"&crym="+f.crym.value+"&slsBrno="+f.slsBrno.value+"&dist="+dist+"&popName="+popname+"&csnoArr="+f.csnoArr.value,"pop","width=650,height=620,top=200,left=200");
		}
		
	}	
	
	function __submit(){
		if('${sessionScope.sessionUser.mnuAuthMap.PTAR_R}'=='Y'){
			searchTarget();
		}
	}


	function checkAll(obj){
		var tgt = document.formx.chkCsno;
		if(tgt==null){
			return false;
		}
		if(obj.checked){
			setCheckbox(tgt);
		}else{	
			resetCheckbox(tgt);
		}	
	}
	

	function checkInput(){
		var f = document.formx;
		var obj = f.chkCsno;
		
		if('${isMainComplete}'=='Y'){
			alert('������ ���س���� �ǻ簡 �����Ǿ����ϴ�.\n FATCA ����ڿ��� �����Ͻñ� �ٶ��ϴ�.');
			return false;
		}
		
		if(obj==null){
			alert('���õ� ���� �����ϴ�.');
			return false;
		}
		
		
		if(!isChecked(obj, '���� �Ѹ� �̻� ���õǾ�� �մϴ�.')){
			return;
		}
		
		f.csnoArr.value = "";
		if(isArray(obj)){
			for(i=0; i<obj.length; i++){	
				if(obj[i].checked){
					f.csnoArr.value = f.csnoArr.value+"|"+obj[i].value;
				}	
			}
		}else{
			if(obj.checked){
				f.csnoArr.value = f.csnoArr.value+"|"+obj.value;
			}
		}
		
		return true;
		
	}

	function init(){
	}

</script>
</head>
<body onLoad="init()">  
    
<form name="formx" method="post">
<input type="hidden" name="csnoArr">
<input type="hidden" name="mnuId"> 
<input type="hidden" name="custType" value="per">

<div id="subMain">
	
	<!----- /Search ----->
	<!----- /Title ----->
	<div class="top_title">
	<ul>
		<li class="txt">���� �ǻ����� ��ȸ</li>
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
				<cx:cselect dbname="amtSctCombo" name="fatcaAmtSctClacd" selected="${param.fatcaAmtSctClacd}" defaultValue="13" before="0,��ü" style="width:105px" />
			</td>
			<td></td>
		</tr>
		<tr>
			<td><span>�ǻ�å����</span></td>
			<td>
				<input type="text" name="fatcaAcipRpprEnob" value="${sessionScope.sessionUser.selFatcaAcipRpprEnob}" maxlength="7" onChange="changeSearch('fatcaAcipRpprEnnm')" class="text enob" />
				<input type="text" name="fatcaAcipRpprEnnm" value="${sessionScope.sessionUser.selFatcaAcipRpprEnnm}" readonly disabled class="readonly ennm" />
				<a href="javascript:goPopup('rppr')"><img src="<cx:wc/>/images/common/icon_search.gif"></a>
			</td>
			<td><span>�ǻ�����</span></td>
			<td>
				<input type="text" name="fatcaAcipCgpEnob" value="${sessionScope.sessionUser.selFatcaAcipCgpEnob}" maxlength="7" onChange="changeSearch('fatcaAcipCgpEnnm')" class="text enob"/>
				<input type="text" name="fatcaAcipCgpEnnm" value="${sessionScope.sessionUser.selFatcaAcipCgpEnnm}" size="7" readonly disabled class="readonly ennm" />
				<a href="javascript:goPopup('cgp')"><img src="<cx:wc/>/images/common/icon_search.gif"></a>
			</td>
			<td><span>�����������</span></td>
			<td>
				<input type="text" name="fatcaCustRelMngrEnob" value="${sessionScope.sessionUser.selFatcaCustRelMngrEnob}" maxlength="7" onChange="changeSearch('fatcaCustRelMngrEnnm')" class="text enob" />
				<input type="text" name="fatcaCustRelMngrEnnm" value="${sessionScope.sessionUser.selFatcaCustRelMngrEnnm}" readonly disabled class="readonly ennm" />
				<a href="javascript:goPopup('rel')"><img src="<cx:wc/>/images/common/icon_search.gif"></a>
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
				<input type="text" name="custNm" value="${selCustNm}" readonly disabled class="readonly csnm" />
				<input type="hidden" name="custSeq" value="${param.custSeq}" />
				<a href="javascript:searchPopCust('per')"><img src="<cx:wc/>/images/common/icon_search.gif"></a>
			</td>			
			<td>
				<c:if test="${sessionScope.sessionUser.mnuAuthMap.PTAR_R == 'Y'}">
					<a href="javascript:searchTarget()" class="button search" >��ȸ</
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
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.PBR_U == 'Y'}">
				<a href="javascript:changePop('sls')" class="basic">������ ����</a>
			</c:if>
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.PRPPR_U == 'Y'}">
				<a href="javascript:changePop('rppr')" class="basic">�ǻ�å���� ����</a>
			</c:if>
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.PCGP_U == 'Y'}">
				<a href="javascript:changePop('cgp')" class="basic">�ǻ����� ����</a>
			</c:if>
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.PREL_U == 'Y'}">
				<a href="javascript:changePop('rel')" class="basic">����������� ����</a>
			</c:if>
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.PTAR_R == 'Y'}">
				<a href="javascript:goExcel()" class="basic" >����</a>
			</c:if>
		</li>
	</ul>	
	</div>
	

	<!----- /Grid ----->
		<display:table class="simple" name="${list}" size="${totalCnt}" id="personalGrid" partialList="true" decorator="fatca.common.decorator.FTCTableFormat" 
			requestURI="/acipTarget.do" pagesize="${requestScope.pagingsize}" sort="external" defaultsort="1" export="false" cellspacing="0">
			<display:column title="<input type='checkbox' name='selAll' onclick='checkAll(this)'>" style="text-align:center">
				<input type="checkbox" name="chkCsno" value="${personalGrid.csno}">
			</display:column>
			<display:column property="slsBrno" title="������<br>�ڵ�" style="text-align:center"/>
			<display:column property="slsBrnm" title="��������" style="text-align:center"/>
			<display:column property="csno" title="����ȣ" style="text-align:center"/>	
			<display:column title="����" style="text-align:center">
				<a href="javascript:linkPersonal('${personalGrid.crym}','${personalGrid.csno}','${personalGrid.custNm}');" title="${personalGrid.custNm}">${personalGrid.custNmMsk}</a>
			</display:column>
			<display:column property="rnno" title="�Ǹ��ȣ" style="text-align:center"/>	
			<display:column property="fatcaAmtSctClacdNm" title="���±ݾ�<br>��������" style="text-align:center"/>				
			<display:column property="acntCnt" title="���<br>���¼�" style="text-align:right"/>
			<display:column property="wcCnvtBalamt" title="��ȭ(��)" groupTitle ="�ջ�ݾ�" style="text-align:right"/>
			<display:column property="usdCnvtBalamt" title="��ȭ($)" groupTitle ="�ջ�ݾ�" style="text-align:right"/>
			<display:column property="fatcaAcipRpprEnnm" title="�ǻ�<br>å����" style="text-align:center"/>	
			<display:column property="fatcaAcipCgpEnnm" title="�ǻ�<br>�����" style="text-align:center"/>	
			<display:column property="fatcaCustRelMngrEnnm" title="������<br>������" style="text-align:center"/>	
		</display:table>
	
	<!----- //Grid ----->
	
</div>
</form>

</body>
</html>
