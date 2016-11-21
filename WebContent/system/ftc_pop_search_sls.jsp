<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %>
<script language="JavaScript">
	function searchList(){
		var f = document.formx;
		if(inputCheck()){
			f.action = "<cx:wc/>/code.do?method=searchPopSls&isListPage=Y";
			f.submit();
			showLoading(true);
		}	
	}
	
	
	function __submit(){
		searchList();
	}
	
	function sclose(){
		self.close();
	}
	

	function select(no, nm, rppr){
		
		var opf = opener.document.formx;
		var f = document.formx;
		if(f.csnoArr.value == "" && f.csnoSeqArr.value == ""){ //팝업조회	
			opf.slsBrno.value = no;
			opf.slsBrnm.value = nm;
			
			sclose();
			
		}else if(f.csnoArr.value != "" || f.csnoSeqArr.value != ""){ //변경
			if(rppr==''){
				alert(nm+"에 실사책임자가 지정되어 있지 않습니다.\n실사책임자 지정후에 변경하시기 바랍니다.");
			}else if(confirm("선택하신 영업점으로 일괄 변경하시겠습니까?")){
				f.action = "<cx:wc/>/code.do?method=saveCharge&setNo="+no;
				f.submit();
				showLoading(true);
				
				opener.searchTarget2();
				
				sclose();
			}
		}
		
		
	}
	

	function saveAll(id){
		
	}
	

	function inputCheck(){
		var f = document.formx;
		if(f.slsBrno.value == '' && f.slsBrnm.value == '' ){
			alert('영업점코드 또는 영업점명을 입력하세요');
			return false;
		}
		return true;
	}
		
	function init(){
		self.window.focus();
	}
	
	
</script>			
	
</head>
<body onload="init()">
   
<form name="formx" method="post">
<input type="hidden" name="csnoArr" value="${param.csnoArr}">
<input type="hidden" name="csnoSeqArr" value="${param.csnoSeqArr}">
<input type="hidden" name="crym" value="${param.crym}">
<input type="hidden" name="dist" value="${param.dist}">
<div class="popWrap">
<!----- /Title ----->
<div class="popTop">
	<ul>
		<li class="left">
			영업점 조회
		</li>
		<li class="right">
			<div class="btn">
				<a href="javascript:sclose();"><img src="<cx:wc/>/images/common/btn_pop_close.gif"/></a>
			</div>
		</li>
	</ul>
</div>
<!----- //Title ----->

<!----- /Contents ----->
<div class="popContentWrap">
	
	<div id="subMain">
	<!----- /Search ----->
	<div class="sheet_search"><div>
		<table><tbody>
		<tr>
			<td><span>영업점코드</span></td>
			<td>
				<input type="text" name="slsBrno" value="${param.slsBrno}" maxlength="10" class="text" />
			</td>
			<td><span>영업점명</span></td>
			<td>
				<input type="text" name="slsBrnm" value="${param.slsBrnm}" maxlength="30" class="text" style="ime-mode:active"/>
			</td>
			<td>
				<a href="javascript:searchList()" class="button search" >조회</a>
			</td>
		</tr>
		</tbody></table>
	</div></div>
	<!----- //Search ----->
	</div>

	<!----- /Title ----->
	<div class="sheet_title">
	<ul>
		<li class="txt"></li>
		<li class="btn"></li>	
	</ul>
	</div>
	<!----- //Title ----->
    <c:choose>
    <c:when test="${empty list}"><c:set var="totalCnt" value="0"></c:set></c:when>
    <c:otherwise><c:set var="totalCnt" value="${list[0].totalCnt}"></c:set></c:otherwise>
    </c:choose>
	

	<!----- /Table ----->
	<display:table class="simple" name="${list}" size="${totalCnt}" id="grid" partialList="true" 
			requestURI="/code.do" pagesize="${requestScope.pagingsize}" sort="external" defaultsort="1" export="false" cellspacing="0">
			<display:column property="brno" title="영업점번호" style="text-align:center; width:10%"/>	
			<display:column title="영업점명" style="text-align:center; width:20%">	
				<a href="javascript:select('${grid.brno}','${grid.brcNm}','${grid.rpprEnob}');">${grid.brcNm}</a>
			</display:column>	
			<display:column property="tel" title="전화번호" style="text-align:center; width:15%"/>
		</display:table>
	
	<!----- //Table ----->

</div>
<!----- //Contents ----->
	
<!----- /Footer ----->
<!-- div class="popFootWrap">
	<table><tbody>
	<tr>
		<td>
			<a href="javascript:close()" class="large gray">닫기</a>
		</td>
	</tr>
	</tbody></table>
</div -->

</form>

</body>
</html>
