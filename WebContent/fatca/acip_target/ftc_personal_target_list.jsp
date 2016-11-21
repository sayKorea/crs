<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %> 

<script>
	
	function searchTarget2(){
		var f = document.formx;
		if(!isNull(f.slsBrno, '영업점') && !isNull(f.brnoCombo, '영업점')){
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
				popname="실사담당자";
			}else if(dist == 'rppr'){		
				popname="실사책임자";
			}else if(dist == 'rel'){		
				popname="고객관계관리자";
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
			alert('선택한 기준년월의 실사가 마감되었습니다.\n FATCA 담당자에게 문의하시기 바랍니다.');
			return false;
		}
		
		if(obj==null){
			alert('선택된 고객이 없습니다.');
			return false;
		}
		
		
		if(!isChecked(obj, '고객은 한명 이상 선택되어야 합니다.')){
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
		<li class="txt">개인 실사대상자 조회</li>
	</ul>
	</div>
	
	<!----- /Search ----->
	<div class="sheet_search"><div>
		<table><tbody>
		<tr>
			<td><span>영업점</span></td>
			<td>
				<c:if test="${sessionScope.sessionUser.isFatca}">
					<input type="text" name="slsBrno" value="${sessionScope.sessionUser.selSlsBrno}" onFocus="this.select()" maxlength="3" onchange="changeSearch2('tex');" class="text brno"/>
					<cx:cselect dbname="branchCombo" name="brnoCombo" selected="${sessionScope.sessionUser.selSlsBrno}" before=",해당 지점 없음" onchange="changeSearch2('com');" style="width:192px" />
					
				</c:if> 
				<c:if test="${!sessionScope.sessionUser.isFatca}">
					<input type="text" name="slsBrno" value="${sessionScope.sessionUser.selSlsBrno}" maxlength="3" readonly class="readonly brno"/>
					<input type="text" name="slsBrnm" value="${sessionScope.sessionUser.selSlsBrnm}" readonly disabled class="readonly brnm" />
				</c:if>
			</td>
			<td><span>기준년월</span></td>
			<td>
				<cx:cselect dbname="crymCombo" name="crym" selected="${sessionScope.sessionUser.selCrym}" style="width:85px" />
			</td>
			<td><span>계좌금액구간구분</span></td>
			<td>
				<cx:cselect dbname="amtSctCombo" name="fatcaAmtSctClacd" selected="${param.fatcaAmtSctClacd}" defaultValue="13" before="0,전체" style="width:105px" />
			</td>
			<td></td>
		</tr>
		<tr>
			<td><span>실사책임자</span></td>
			<td>
				<input type="text" name="fatcaAcipRpprEnob" value="${sessionScope.sessionUser.selFatcaAcipRpprEnob}" maxlength="7" onChange="changeSearch('fatcaAcipRpprEnnm')" class="text enob" />
				<input type="text" name="fatcaAcipRpprEnnm" value="${sessionScope.sessionUser.selFatcaAcipRpprEnnm}" readonly disabled class="readonly ennm" />
				<a href="javascript:goPopup('rppr')"><img src="<cx:wc/>/images/common/icon_search.gif"></a>
			</td>
			<td><span>실사담당자</span></td>
			<td>
				<input type="text" name="fatcaAcipCgpEnob" value="${sessionScope.sessionUser.selFatcaAcipCgpEnob}" maxlength="7" onChange="changeSearch('fatcaAcipCgpEnnm')" class="text enob"/>
				<input type="text" name="fatcaAcipCgpEnnm" value="${sessionScope.sessionUser.selFatcaAcipCgpEnnm}" size="7" readonly disabled class="readonly ennm" />
				<a href="javascript:goPopup('cgp')"><img src="<cx:wc/>/images/common/icon_search.gif"></a>
			</td>
			<td><span>고객관계관리자</span></td>
			<td>
				<input type="text" name="fatcaCustRelMngrEnob" value="${sessionScope.sessionUser.selFatcaCustRelMngrEnob}" maxlength="7" onChange="changeSearch('fatcaCustRelMngrEnnm')" class="text enob" />
				<input type="text" name="fatcaCustRelMngrEnnm" value="${sessionScope.sessionUser.selFatcaCustRelMngrEnnm}" readonly disabled class="readonly ennm" />
				<a href="javascript:goPopup('rel')"><img src="<cx:wc/>/images/common/icon_search.gif"></a>
			</td>
			<td></td>
		</tr>
		<tr>
			<td>
				<span>고객번호</span>
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
					<a href="javascript:searchTarget()" class="button search" >조회</
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
		    <span>[ 총 ${totalCnt} 건 ]</span>
		</li>
		<li class="btn">
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.PBR_U == 'Y'}">
				<a href="javascript:changePop('sls')" class="basic">영업점 변경</a>
			</c:if>
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.PRPPR_U == 'Y'}">
				<a href="javascript:changePop('rppr')" class="basic">실사책임자 변경</a>
			</c:if>
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.PCGP_U == 'Y'}">
				<a href="javascript:changePop('cgp')" class="basic">실사담당자 변경</a>
			</c:if>
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.PREL_U == 'Y'}">
				<a href="javascript:changePop('rel')" class="basic">고객관계관리자 변경</a>
			</c:if>
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.PTAR_R == 'Y'}">
				<a href="javascript:goExcel()" class="basic" >엑셀</a>
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
			<display:column property="slsBrno" title="영업점<br>코드" style="text-align:center"/>
			<display:column property="slsBrnm" title="영업점명" style="text-align:center"/>
			<display:column property="csno" title="고객번호" style="text-align:center"/>	
			<display:column title="고객명" style="text-align:center">
				<a href="javascript:linkPersonal('${personalGrid.crym}','${personalGrid.csno}','${personalGrid.custNm}');" title="${personalGrid.custNm}">${personalGrid.custNmMsk}</a>
			</display:column>
			<display:column property="rnno" title="실명번호" style="text-align:center"/>	
			<display:column property="fatcaAmtSctClacdNm" title="계좌금액<br>구간구분" style="text-align:center"/>				
			<display:column property="acntCnt" title="대상<br>계좌수" style="text-align:right"/>
			<display:column property="wcCnvtBalamt" title="원화(원)" groupTitle ="합산금액" style="text-align:right"/>
			<display:column property="usdCnvtBalamt" title="미화($)" groupTitle ="합산금액" style="text-align:right"/>
			<display:column property="fatcaAcipRpprEnnm" title="실사<br>책임자" style="text-align:center"/>	
			<display:column property="fatcaAcipCgpEnnm" title="실사<br>담당자" style="text-align:center"/>	
			<display:column property="fatcaCustRelMngrEnnm" title="고객관계<br>관리자" style="text-align:center"/>	
		</display:table>
	
	<!----- //Grid ----->
	
</div>
</form>

</body>
</html>
