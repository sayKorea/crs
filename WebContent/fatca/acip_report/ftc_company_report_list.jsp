<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %> 

<script>

	function searchTarget2(){
		var f = document.formx;
		
		if(!isNull(f.slsBrno,'영업점번호') && !isNull(f.brnoCombo, '영업점')){
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
		<li class="txt">법인 보고서 조회</li>
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
					<cx:cselect dbname="branchCombo" name="brnoCombo" selected="${sessionScope.sessionUser.selSlsBrno}" before=",해당 지점 없음" after="999,[999] 은행합계" onchange="changeSearch2('com');" style="width:192px" />
					
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
			<td><span>상품구분</span></td>
			<td>
				<cx:cselect dbname="combo801" name="fatcaPdClacd" selected="${param.fatcaPdClacd}" before=",전체" style="width:85px" />
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
				<input type="text" name="custNm" value="${selCustNm}" readonly disabled class="readonly coprnm" />
				<input type="hidden" name="custSeq" value="${param.custSeq}" >
				<a href="javascript:searchPopCust()"><img src="<cx:wc/>/images/common/icon_search.gif"></a>
			</td>	
			<td>
				<span>금액</span>
			</td>
			<td>
				<input type="text" name="stAmt" value="${param.stAmt}" maxlength="10" class="text" />~
				<input type="text" name="edAmt" value="${param.edAmt}" maxlength="10" class="text" />
			</td>			
			<td>
				<c:if test="${sessionScope.sessionUser.mnuAuthMap.CRPT_R == 'Y'}">
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
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.CRPT_R == 'Y'}">
				<a href="javascript:goExcel()" class="basic" >엑셀</a>
			</c:if>			
		</li>
	</ul>	
	</div>
	
	<!----- /Grid ----->
		<display:table class="simple" name="${list}" size="${totalCnt}" id="grid" partialList="true"  decorator="fatca.common.decorator.FTCTableFormat"
			requestURI="/acipReport.do" pagesize="${requestScope.pagingsize}" sort="external" defaultsort="1" export="false" cellspacing="0">
			<display:column property="slsBrno" title="영업점<br>코드" style="text-align:center"/>
			<display:column property="slsBrnm" title="영업점명" maxLength="3" style="text-align:center"/>
			<display:column property="fatcaCoprKnm" title="고객명<br>(한글)" groupTitle="법인정보" style="text-align:left"/>
			<display:column property="fatcaCoprEnm" title="고객명<br>(영문)" groupTitle="법인정보" style="text-align:left"/>
			<display:column property="fatcaTxpayrNo" title="EIN" groupTitle="법인정보" style="text-align:center"/>
			<display:column property="fatcaCoprEngAddr" title="주소" groupTitle="법인정보" maxLength="8" style="text-align:left"/>
			<display:column property="perNm" title="영문성명" groupTitle="실질소유자" maxLength="4" style="text-align:center"/>
			<display:column property="fatcaPrsnlEngAddr" title="주소" groupTitle="실질소유자" maxLength="8" style="text-align:left"/>
			<display:column property="perTxpayrNo" title="TIN" groupTitle="실질소유자" maxLength="4" style="text-align:center"/>
			<display:column property="acno" title="계좌번호" maxLength="4" style="text-align:center"/>
			<display:column property="acntBalamt" title="잔액" style="text-align:right"/>
			<display:column property="ntrstAmt" title="이자총액" style="text-align:right"/>
			<display:column property="shramt" title="배당총액" style="text-align:right"/>
			<display:column property="etcm" title="기타수익<br>총액" style="text-align:right"/>
			<display:column property="totNcmRbam" title="총거래<br>가액" style="text-align:right"/>
		</display:table>
	
	<!----- //Grid ----->
	
</div>
</form>

</body>
</html>
