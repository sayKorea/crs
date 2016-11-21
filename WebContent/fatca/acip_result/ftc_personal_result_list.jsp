<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %> 

<script>
	function searchTarget2(){
		var f = document.formx;
		if(!isNull(f.slsBrno, '영업점') && !isNull(f.brnoCombo, '영업점')){
			f.mnuId.value = 'PRES_R';
			f.action = "<cx:wc/>/acipResult.do?method=personalResult";
			f.submit();		
			showLoading(true);
		}
	}

	function goExcel(){
		var f = document.formx;
		f.mnuId.value = 'PRES_R';
		f.target = parent.document.getElementById("ifrmExcel").name;
		f.action = "<cx:wc/>/acipResult.do?method=personalExcelDown";
		f.submit();
		showLoading(true);
		f.target = "";
		showLoading(false);
	}
	
	function linkPersonal(objcrym, objcsno){
		window.open("<cx:wc/>/acipTarget.do?method=personalView&crym="+objcrym+"&csno="+objcsno+"&mode=popup","popPersonal","width=1180,height=730,top=200,left=200, resizable=yes");
	}	

	function linkQuestion(objcrym, objcsno, objcustnm){
		window.open("<cx:wc/>/acipResult.do?method=question&crym="+objcrym+"&csno="+objcsno+"&custNm="+objcustnm+"&mode=popup","popQuestion","width=1080,height=560,top=200,left=200, resizable=yes");
	}	

	function linkDocument(objcrym, objcsno, objcustnm){
		window.open("<cx:wc/>/acipResult.do?method=document&crym="+objcrym+"&csno="+objcsno+"&custNm="+objcustnm+"&mode=popup","popDocument","width=1080,height=520,top=200,left=200, resizable=yes");
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
			popname="실사담당자";
		}else if(dist == 'rppr'){		
			popname="실사책임자";
		}else if(dist == 'rel'){		
			popname="고객관계관리자"; 
		}
		
		window.open("<cx:wc/>/code.do?method=searchPopEmp&slsBrno="+f.slsBrno.value+"&dist="+dist+"&init=y&popName="+popname,"pop","width=650,height=620,top=200,left=200");
	}		
	
	function __submit(){
		if('${sessionScope.sessionUser.mnuAuthMap.PRES_R}'=='Y'){
			searchTarget();
		}
	}


</script>
</head>
<body>
    
<form name="formx" method="post">
<input type="hidden" name="mnuId">
<input type="hidden" name="custType" value="per">
 
<div id="subMain">
	
	<!----- /Search ----->
	<!----- /Title ----->
	<div class="top_title">
	<ul>
		<li class="txt">개인 실사대상 결과 등록</li>
	</ul>
	</div>
	
	<!----- /Search ----->
	<div class="sheet_search"><div>
		<table><tbody>
		<tr>
			<td><span>영업점</span></td>
			<td>
				<c:if test="${sessionScope.sessionUser.isFatca}">
					<input type="text" name="slsBrno" value="${sessionScope.sessionUser.selSlsBrno}" onFocus="this.select()" maxlength="3" onChange="changeSearch2('tex');" class="text brno"/>
					<cx:cselect dbname="branchCombo" name="brnoCombo" selected="${sessionScope.sessionUser.selSlsBrno}" before=",해당 지점 없음" onchange="changeSearch2('com');" style="width:152px" />
					
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
				<cx:cselect dbname="amtSctCombo" name="fatcaAmtSctClacd" selected="${param.fatcaAmtSctClacd}" defaultValue="13" before="0,전체" style="width:85px" />
			</td>
			<td></td>
		</tr>
		<tr>			
			<td><span>전산실사결과</span></td>
			<td>
				<cx:cselect dbname="czAcipCombo" name="fatcaCzAcipRscd" selected="${param.fatcaCzAcipRscd}" before=",전체" style="width:85px" />
			</td>
			<td><span>문서실사결과</span></td>
			<td>
				<cx:cselect dbname="docAcipCombo" name="fatcaDocAcipRscd" selected="${param.fatcaDocAcipRscd}" before=",전체" style="width:85px" />
			</td>
			<td><span>관리자질의결과</span></td>
			<td>
				<cx:cselect dbname="iqrCombo" name="fatcaIqrRscd" selected="${param.fatcaIqrRscd}" before=",전체" style="width:85px" />
			</td>
		</tr>
		<tr>
			<td><span>FATCA확인서실사결과</span></td>
			<td>
				<cx:cselect dbname="cfrrptAcipCombo" name="fatcaCfrrptAcipRscd" selected="${param.fatcaCfrrptAcipRscd}" before=",전체" style="width:85px" />
			</td>	
			<td><span>FATCA고객유형</span></td>
			<td>
				<cx:cselect dbname="fatcaCustDvcdCombo" name="fatcaCustDvcd" selected="${param.fatcaCustDvcd}" before=",전체" style="width:85px" />
			</td>
			<td><span>보고대상여부</span></td>
			<td>
				<cx:cselect dbname="reptTrgetCombo" name="fatcaReptTrgetDvcd" selected="${param.fatcaReptTrgetDvcd}" before=",전체" style="width:85px" />
			</td>
		</tr>
		<tr>
			<td><span>고객번호</span></td>
			</td>
			<td>
				<input type="text" name="csno" value="${param.csno}" maxlength="13" onKeydown="changeSearch('custNm');changeSearch('csnoReal');changeSearch('custSeq');" class="text csno" />
				<input type="hidden" name="csnoReal" value="${param.csnoReal}" >
				<input type="text" name="custNm" value="${selCustNm}" readonly disabled class="readonly csnm" />
				<input type="hidden" name="custSeq" value="${param.custSeq}" />
				<a href="javascript:searchPopCust('per')"><img src="<cx:wc/>/images/common/icon_search.gif"></a>
			</td>
			<td><span>실사완료여부</span></td>
			<td>
				<cx:cselect dbname="ynCombo" name="fatcaAcipComptYn" selected="${param.fatcaAcipComptYn}" before=",전체" style="width:85px" />
			</td>					
			<td><span>실사담당자</span></td>
			<td>
				<input type="text" name="fatcaAcipCgpEnob" value="${sessionScope.sessionUser.selFatcaAcipCgpEnob}" maxlength="7" onChange="changeSearch('fatcaAcipCgpEnnm')" class="text enob"/>
				<input type="text" name="fatcaAcipCgpEnnm" value="${sessionScope.sessionUser.selFatcaAcipCgpEnnm}" size="7" readonly disabled class="readonly ennm" />
				<a href="javascript:goPopup('cgp')"><img src="<cx:wc/>/images/common/icon_search.gif"></a>
			</td>			
			<td>
				<c:if test="${sessionScope.sessionUser.mnuAuthMap.PRES_R == 'Y'}">
					<a href="javascript:searchTarget()" class="button search" >조회</a>
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
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.PRES_R == 'Y'}">
				<a href="javascript:goExcel()" class="basic" >엑셀</a>
			</c:if>			
		</li>
	</ul>	
	</div>
	

	<!----- /Grid ----->
		<display:table class="simple" name="${list}" size="${totalCnt}" id="grid" partialList="true" decorator="fatca.common.decorator.FTCTableFormat" 
			requestURI="/acipResult.do" pagesize="${requestScope.pagingsize}" sort="external" defaultsort="1" export="false" cellspacing="0">
			<display:column property="slsBrno" title="영업점<br>코드" groupTitle ="개인고객정보" style="text-align:center"/>
			<display:column property="slsBrnoNm" title="영업점명" groupTitle ="개인고객정보" style="text-align:center"/>
			<display:column property="csno" title="고객번호" groupTitle ="개인고객정보" style="text-align:center"/>	
			<display:column title="고객명" groupTitle ="개인고객정보" style="text-align:center">
				<a href="javascript:linkPersonal('${grid.crym}','${grid.csno}','${grid.custNm}');" title="${grid.custNm}">${grid.custNmMsk}</a>
			</display:column>
			<display:column property="rnno" title="실명번호" groupTitle ="개인고객정보" style="text-align:center"/>
			<display:column property="fatcaAcipCgpEnnm" title="실사<br>담당자" groupTitle ="실사정보" style="text-align:center"/>
			<display:column property="fatcaAmtSctClacdNm" title="계좌금액<br>구간구분" groupTitle ="실사정보" style="text-align:center"/>				
			<display:column property="fatcaCzAcipRscdNm" title="전산<br>실사결과" groupTitle ="실사정보" style="text-align:center"/>
			<display:column title="문서<br>실사결과" groupTitle ="실사정보" style="text-align:center">
				<c:if test="${grid.fatcaDocAcipRscd != '10'}">
				<a href="javascript:linkDocument('${grid.crym}','${grid.csno}','${grid.custNm}');">${grid.fatcaDocAcipRscdNm}</a>
				</c:if>
				<c:if test="${grid.fatcaDocAcipRscd == '10'}">
				${grid.fatcaDocAcipRscdNm}
				</c:if>
			</display:column>
			<display:column title="관리자<br>질의 결과" groupTitle ="실사정보" style="text-align:center">
				<c:if test="${grid.fatcaDocAcipRscd != '10'}">
				<a href="javascript:linkQuestion('${grid.crym}','${grid.csno}','${grid.custNm}');">${grid.fatcaIqrRscdNm}</a>
				</c:if>
				<c:if test="${grid.fatcaDocAcipRscd == '10'}">
				${grid.fatcaIqrRscdNm}
				</c:if>
			</display:column>
			<display:column property="fatcaCfrrptAcipRscdNm" title="FATCA확인서<br>실사결과"  groupTitle ="실사정보" style="text-align:center"/>
			<display:column property="fatcaReptTrgetDvcdNm" title="보고<br>대상여부" groupTitle ="최종결과" style="text-align:center"/>	
			<display:column property="fatcaAcipComptDt" title="실사<br>완료일" groupTitle ="최종결과" style="text-align:center"/>	
			<display:column property="fatcaCustDvcdNm" title="FATCA<br>고객 유형" groupTitle ="최종결과" style="text-align:center"/>	
		</display:table>
	
	<!----- //Grid ----->
	
</div>
</form>

</body>
</html>
