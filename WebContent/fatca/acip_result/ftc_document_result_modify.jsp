<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %> 
<script>

	function searchTarget2(){
 		var f = document.formx;
 		if(!isNull(f.csno, '고객번호')){
 			f.mnuId.value = 'DOC_R';
 			f.action = "<cx:wc/>/acipResult.do?method=document";
 			f.submit();
 			showLoading(true);
 		}
	}
	
	//실사기준일자 변경시 리셋
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
			if(confirm("문서 실사 결과를 등록 하시겠습니까?")){
				f.mnuId.value = 'DOC_U';
				f.action = "<cx:wc/>/acipResult.do?method=saveDoc";
				f.submit();
				showLoading(true);
				
				if('${param.mode}' == "popup"){
					opener.searchTarget2();
					sclose();
				}
			}
		}
	}

	function sclose(){
		self.close();
	}
	
	function inputCheck(){
		var f = document.formx;
		
		if('${isMainComplete}'=='Y'){
			alert('선택한 기준년월의 실사가 마감되었습니다.\nFATCA 담당자에게 문의하시기 바랍니다.');
			return false;
		}

		if(isNull(f.custNm)){
			alert('조회된 고객이 없습니다.\n다시 확인 하시기 바랍니다.');
			return false;
		}
		
		if(isNull(f.csno, '고객번호')){
			return false;
		}
		
		return true;
	}

	function changeDataType(no){
		var objSel = eval("document.formx.fatcaAsmInfoItemDvcd" + no);		
		objSel.value = "11";
	}
	
	function changeChk(no){
		var objSel = eval("document.formx.fatcaAsmInfoItemDvcd" + no);		
		var objChk = eval("document.formx.fatcaAcipRscd" + no);
		if(objSel.value == "13")
		{
			objSel.value = "12";
			alert("미수행은 선택 할 수 없습니다.\n정보 없음으로 선택 됩니다.");
		}
		if(objSel.value == "12"){
			for(var i=0; i<objChk.length;i++){
				if(objChk[i].checked){
					objChk[i].checked = false;
					break;
				}
			}
		}else if(objSel.value == "11"){
			objChk[0].checked = true;
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
		if('${sessionScope.sessionUser.mnuAuthMap.DOC_R}'=='Y'){
			search();
		}
	}
	
</script>
</head>
<body onLoad="init()">
    
<form name="formx" method="post">
<input type="hidden" name="mode" value="${param.mode}">
<input type="hidden" name="mnuId">
<input type="hidden" name="custType" value="per">
			
<c:if test="${param.mode == 'popup' }">
	<div class="popWrap" id="divPopW">
	<!----- /Title ----->
	<div class="popTop" id="divPopTitle" >
	<ul>
		<li class="left">
			문서실사 결과 등록
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
		<li class="txt">문서실사 결과 등록</li>
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
				<td><span>기준년월</span></td>
				<td>
					<cx:cselect dbname="crymCombo" name="crym" selected="${sessionScope.sessionUser.selCrym}" style="width:85px" />
				</td>
				<td><span>고객번호</span></td>
				<td>
					<input type="text" name="csno" value="${param.csno}" maxlength="13" onKeydown="changeSearch('custNm');changeSearch('csnoReal');changeSearch('custSeq');" class="text csno" />
					<input type="hidden" name="csnoReal" value="${param.csnoReal}" >
					<input type="text" name="custNm" value="${selCustNm}" readonly disabled class="readonly csnm" />
					<input type="hidden" name="custSeq" value="${param.custSeq}" >
					<a href="javascript:searchPopCust('per')"><img src="<cx:wc/>/images/common/icon_search.gif"></a>
				</td>
				<td>
					<c:if test="${sessionScope.sessionUser.mnuAuthMap.DOC_R == 'Y'}">
						<a href="javascript:searchTarget()" class="button search">조회</a>
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
				<%-- <c:if test="${sessionScope.sessionUser.mnuAuthMap.DOC_U == 'Y' && info!=null && info.fatcaDocAcipRscd != '10'}"> --%>
				<a href="javascript:savequestion()" class="basic">저장</a>
				<%-- </c:if> --%>
			</li>
		</ul>
		</div>
		
		<!----- /Table ----->
		<table class="table"><tbody>
		<colgroup>
		</colgroup>
		<tr>
			<th width="15%">실사담당자</th>
			<td class="center" width="15%">${info.ltChprNm}</td>
			<th width="15%">실사일자</th>
			<td class="center" width="15%">${info.chDtlDtti}</td>
			<th width="15%">실사기준(완료)일자</th>
			<td class="center" width="15%">${info.fatcaAcipComptDtFmt}</td>
		</tr>
		<tbody></table>
		<!----- //Table ----->
		
		<div class="h20"></div>			
	
		<table class="simple" >
	        <colgroup>
	        <col width="50">
	        <col width="150">
	        <col width="60">
	        <col width="70">
	        <col width="70">
	        <col width="70">
	        <col width="70">
	        <col width="70">
	        <col width="70">
	        <col width="70">
	        </colgroup>
	        <tr>
	          <th  class="center" rowspan="2">문서번호</th>
	          <th  class="center" rowspan="2">문서명</th>
	          <th  class="center" rowspan="2">추정정보유무</th>
	          <th  class="center" colspan="7">문서실사결과</th>
	        </tr>
	        <tr>
	          <th  class="center">국적</th>
	          <th  class="center">출생국가</th>
	          <th  class="center">우편주소/<br>거주지주소</th>
	          <th  class="center">전화번호</th>
	          <th  class="center">미국으로<br>자동이체</th>
	          <th  class="center">위임/서명<br>미국주소</th>
	          <th  class="center">대리수령자<br>우편물주소</th>
	        </tr>
<!-- 	        <tr>
	          <td  class="center">&nbsp;</td>
	          <td  class="center">&nbsp;</td>
	          <td  class="center">&nbsp;</td>
	          <td  class="center">&nbsp;</td>
	          <td  class="center">&nbsp;</td>
	          <td  class="center">&nbsp;</td>
	          <td  class="center">&nbsp;</td>
	          <td  class="center">&nbsp;</td>
	          <td  class="center">&nbsp;</td>
	          <td  class="center">&nbsp;</td>
	        </tr> -->
	        <c:forEach items="${docList}" var="doc">
	        <tr >
	        	<td class="subjectP" style="text-align:center">${doc.docCd}</td>
	        	<td class="subjectP" style="text-align:center">${doc.docNm}</td>
	        	<td class="subjectP" style="text-align:center">
	        		<cx:cselect dbname="docAcipCombo" onchange="javascript:changeChk('${doc.docCd}');" name="fatcaAsmInfoItemDvcd${doc.docCd}" selected="${doc.fatcaAsmInfoItemDvcd}" />
				</td>
	        	<td class="subjectP" style="text-align:center">
	        	<input type="radio" name="fatcaAcipRscd${doc.docCd}" value="11" onclick="javascript:changeDataType('${doc.docCd}');" <c:if test="${doc.fatcaAcipRscd == '11'}">checked</c:if>/>
	        	</td>
	        	<td class="subjectP" style="text-align:center">
	        	<input type="radio" name="fatcaAcipRscd${doc.docCd}" value="12" onclick="javascript:changeDataType('${doc.docCd}');" <c:if test="${doc.fatcaAcipRscd == '12'}">checked</c:if>/>
	        	</td>
	        	<td class="subjectP" style="text-align:center">
	        	<input type="radio" name="fatcaAcipRscd${doc.docCd}" value="13" onclick="javascript:changeDataType('${doc.docCd}');" <c:if test="${doc.fatcaAcipRscd == '13'}">checked</c:if>/>
	        	</td>
	        	<td class="subjectP" style="text-align:center">
	        	<input type="radio" name="fatcaAcipRscd${doc.docCd}" value="14" onclick="javascript:changeDataType('${doc.docCd}');" <c:if test="${doc.fatcaAcipRscd == '14'}">checked</c:if>/>
	        	</td>
	        	<td class="subjectP" style="text-align:center">
	        	<input type="radio" name="fatcaAcipRscd${doc.docCd}" value="15" onclick="javascript:changeDataType('${doc.docCd}');" <c:if test="${doc.fatcaAcipRscd == '15'}">checked</c:if>/>
	        	</td>
	        	<td class="subjectP" style="text-align:center">
	        	<input type="radio" name="fatcaAcipRscd${doc.docCd}" value="16" onclick="javascript:changeDataType('${doc.docCd}');" <c:if test="${doc.fatcaAcipRscd == '16'}">checked</c:if>/>
	        	</td>
	        	<td class="subjectP" style="text-align:center">
	        	<input type="radio" name="fatcaAcipRscd${doc.docCd}" value="17" onclick="javascript:changeDataType('${doc.docCd}');" <c:if test="${doc.fatcaAcipRscd == '17'}">checked</c:if>/>
	        	</td>
	        </tr>
	        </c:forEach>
		</table>
		<!----- //Grid ----->
		
	</div>
</div>
</form>
</body>
</html>