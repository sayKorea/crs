<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %>
<script language="JavaScript">

	function searchList(){
		var f = document.formx;
		f.action = "<cx:wc/>/auth.do?method=listEmp";
		f.submit();
		showLoading(true);
	}
		
		
	function __submit(){
		searchList();
	}
	
	function sclose(){
		self.close();
	}
	

	function save(){
		
		var f = document.formx;
		if(checkInput()){ 
			
			if(confirm("선택하신 사용자를 ["+f.fatcaAuthCd.options[f.fatcaAuthCd.selectedIndex].text+"] 권한으로 등록하시겠습니까?")){
				f.action = "<cx:wc/>/auth.do?method=saveUsrAuth&dist=register&slsBrno="+f.slsBrno.value+"&fatcaAuthCd="+f.fatcaAuthCd.value+"&empArr="+f.empArr.value;
				f.submit();
				showLoading(true);
				//alert( '${param.popName}' + " 등록이 완료되었습니다.");
				opener.searchList();
				
				sclose();
			}
		}
		
	}
	

	function checkAll(obj){
		var tgt = document.formx.chkEmp;
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
		var obj = f.chkEmp;
		if(obj==null){
			alert('선택된 사용자가 없습니다.');
			return false;
		}
		
		if(!isChecked(obj, '사용자는 한명 이상 선택되어야 합니다.')){
			return;
		}
		
		f.empArr.value = "";
		if(isArray(obj)){
			for(i=0; i<obj.length; i++){	
				if(obj[i].checked){
					f.empArr.value = f.empArr.value+"|"+obj[i].value;
				}	
			}
		}else{
			if(obj.checked){
				f.empArr.value = f.empArr.value+"|"+obj.value;
			}
		}
		
		return true;
		
	}

	function init(){
		self.window.focus();
	}
	
	
</script>			
	
</head>
<body onload="init()" scroll="auto">

   
<form name="formx" method="post">
<input type="hidden" name="empArr">
<input type="hidden" name="slsBrno" value="${param.slsBrno}">
<div class="popWrap">
<!----- /Title ----->
<div class="popTop">
	<ul>
		<li class="left">
			사용자 권한 등록
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
			<td><span>행번</span></td>
			<td>
				<input type="text" name="enob" value="${param.enob}" maxlength="13" size="10" class="text" />
			</td>
			<td><span>사용자명</span></td>
			<td>
				<input type="text" name="userNm" value="${param.userNm}" maxlength="30" size="10" class="text"  style="ime-mode:active"/>
			</td>
			<td>
				<a href="javascript:searchList()" class="button search" >조회</a>
			</td>
			<td><span>지정 권한</span></td>
			<td>
				<cx:cselect dbname="underAuthCombo" name="fatcaAuthCd" selected="${param.fatcaAuthCd}" style="width:110px" />
			</td>
		</tr>
		</tbody></table>
	</div></div>
	<!----- //Search ----->
	</div>

    <c:choose>
    <c:when test="${empty list}"><c:set var="totalCnt" value="0"></c:set></c:when>
    <c:otherwise><c:set var="totalCnt" value="${list[0].totalCnt}"></c:set></c:otherwise>
    </c:choose>

	<!----- /Title ----->
	<div class="sheet_title">
	<ul>
		<li class="txt">[ 총 ${totalCnt} 건 ]</li>
		<li class="btn"><a href="javascript:save()" class="basic">등록</a></li>	
	</ul>
	</div>
	<!----- //Title ----->

	<!----- /Table ----->
	<display:table class="simple" name="${list}" size="${totalCnt}" id="grid" partialList="true" 
			requestURI="/code.do" pagesize="0" sort="external" defaultsort="1" export="false" cellspacing="0">
			<display:column title="<input type='checkbox' name='selAll' onclick='checkAll(this)'>" style="text-align:center; width:5%">
				<input type="checkbox" name="chkEmp" value="${grid.enob}">
			</display:column>
			<display:column property="enob" title="행번" style="text-align:center; width:10%"/>	
			<display:column property="userNm" title="직원명" style="text-align:center; width:20%"/>
			<display:column property="brnm" title="소속점" style="text-align:center; width:15%"/>
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
