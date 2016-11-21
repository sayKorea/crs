<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %>
<script language="JavaScript">

	function searchList(){
		var f = document.formx;
		
		if(!isNull(f.slsBrno, '영업점') && !isNull(f.brnoCombo, '영업점')){
			f.mnuId.value = 'AUTH_R';		
			f.action = "<cx:wc/>/auth.do?method=listUsrAuth";
			f.submit();
			showLoading(true);
		}
	}

	function goExcel(){
		var f = document.formx;
		f.mnuId.value = 'MENU_R';
		f.target = parent.document.getElementById("ifrmExcel").name;
		f.action = "<cx:wc/>/auth.do?method=authExcelDown";
		f.submit();
		showLoading(true);
		f.target = "";
		showLoading(false);
	}
		
	function __submit(){
		if('${sessionScope.sessionUser.mnuAuthMap.AUTH_R}'=='Y'){
			searchList();
		}
	}
	
	
	function removeAuth(dist){
		var f = document.formx;		
		if(checkInput()){
			if(confirm("선택하신 사용자의 권한을 삭제하시겠습니까?")){
				f.action = "<cx:wc/>/auth.do?method=saveUsrAuth&dist=remove";
				f.submit();
				showLoading(true);
			}
		}		
	}	
	
	function registerAuth(dist){
		
		var f = document.formx;
		if(!isNull(f.slsBrno, '영업점')){
			if(f.chgStatus.value=='N'){
				window.open("<cx:wc/>/auth.do?method=listEmp&slsBrno="+f.slsBrno.value+"&fatcaAuthCd="+f.fatcaAuthCd.value,"pop","width=650,height=770,top=200,left=200");
			}else{
				alert('영업점 변경후 목록이 조회되지 않았습니다.\n조회 후 등록하시기 바랍니다.');
			}
		}
	}	
	
	function changeStatus(){
		
		var f = document.formx;
		f.chgStatus.value = "Y";
	}

	function searchPopSls(){
		var f = document.formx;
		window.open("<cx:wc/>/code.do?method=searchPopSls&init=y","pop","width=650,height=620,top=200,left=200");			
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


	function resizeH(){
		var setDiv = document.getElementById("subgrid");
		var divTitle = document.getElementById("divTitle");
		var screenW = document.body.clientWidth;
		var gridHeight = 470;
		var screenH = document.body.clientHeight;
		
		//화면의 height가 595보다 커질 경우 display 태그의 DIV를 동적으로 변경한다.
		//595는 화면 영역에 따라서 달라진다. 
		if(screenH > 595){
			setDiv.style.height = gridHeight + (screenH - 600)+ "px";
		}
		
		//화면 늘여보기 버튼 클릭시 현재 창의 사이즈를 알수가 없으므로 기본사이즈 935와 다르면 
		//div width의 길이를 변경한다.
		if(divTitle.offsetWidth != 935){
			setDiv.style.Width = screenW + "px";			
		}else{
			setDiv.style.Width = divTitle.offsetWidth + "px";
		}
	}
	

	function resizeW(){
		var setDiv = document.getElementById("subgrid");
		var screenW = document.body.clientWidth;
		
		//화면 늘여보기 버튼 클릭시 현재 창의 사이즈를 알수가 없으므로 기본사이즈 935와 다르면 
		//div width의 길이를 변경한다.		
		setDiv.style.Width = screenW + "px";
	}
	

	function init(){
		resizeW();
		resizeH();
	}

</script>			
	
</head>
<body onresize="resizeH()" onload="init()">

   
<form name="formx" method="post">
 <input type="hidden" name="empArr">
 <input type="hidden" name="chgStatus" value="${chgStatus}">
<input type="hidden" name="mnuId">
 

	<div class="top_title" id="divTitle">
	<ul>
		<li class="txt">사용자 권한 관리</li>	
		</li>
	</ul>
	</div>
	
	<div id="subMain">
	<!----- /Search ----->
	<div class="sheet_search"><div>
		<table><tbody>
		<tr>
			<td><span>영업점</span></td>
			<td>
				<c:if test="${sessionScope.sessionUser.isFatca}">
					<input type="text" name="slsBrno" value="${sessionScope.sessionUser.selSlsBrno}" onFocus="this.select()" maxlength="3" onChange="changeSearch2('tex'); changeStatus();" class="text brno"/>
					<cx:cselect dbname="branchCombo" name="brnoCombo" selected="${sessionScope.sessionUser.selSlsBrno}" before=",해당 지점 없음" after="999,[999] 은행합계" onchange="changeSearch2('com'); changeStatus();" style="width:192px" />
					
				</c:if> 
				<c:if test="${!sessionScope.sessionUser.isFatca}">
					<input type="text" name="slsBrno" value="${sessionScope.sessionUser.selSlsBrno}" maxlength="3" readonly class="readonly brno"/>
					<input type="text" name="slsBrnm" value="${sessionScope.sessionUser.selSlsBrnm}" readonly disabled class="readonly brnm" />
				</c:if>
				<c:if test="${sessionScope.sessionUser.isFatca}">
					<!-- a href="javascript:searchPopSls()"><img src="<cx:wc/>/images/common/icon_search.gif"></a -->
				</c:if>
			</td>
			<td><span>권한</span></td>
			<td>
				<cx:cselect codekey="131" name="fatcaAuthCd" selected="${param.fatcaAuthCd}" before=",전체" style="width:110px" />
			</td>
			<td>
				<c:if test="${sessionScope.sessionUser.mnuAuthMap.AUTH_R == 'Y'}">
					<a href="javascript:searchList()" class="button search" >조회</a>
				</c:if>
			</td>
		</tr>
		</tbody></table>
	</div></div>
	<!----- //Search ----->
	
    <c:choose>
    <c:when test="${empty list}"><c:set var="totalCnt" value="0"></c:set></c:when>
    <c:otherwise><c:set var="totalCnt" value="${list[0].totalCnt}"></c:set></c:otherwise>
    </c:choose>
	

	<!----- /Title ----->
	<div class="sheet_title">
	<ul>
		<li class="txt"><span>[ 총 ${totalCnt} 건 ]</span>				
		</li>
		<li class="btn">
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.AUTH_U == 'Y'}">
				<a href="javascript:registerAuth()" class="basic">사용자 권한 등록</a>
				<a href="javascript:removeAuth()" class="basic">사용자 권한 삭제</a>
			</c:if>
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.AUTH_R == 'Y'}">
				<a href="javascript:goExcel()" class="basic" >엑셀</a>
			</c:if>
		</li>
	</ul>
	</div>
	<!----- //Title ----->

	<div id="subgrid" style="width:935px; height:472px; position: absolute;border:none;overflow-x:auto;">
	<!----- /Grid ----->
		<display:table class="simple" name="${list}" size="${totalCnt}" id="grid" partialList="true" 
			requestURI="/auth.do" pagesize="0" sort="external" defaultsort="1" export="false" cellspacing="0">
			<display:column title="<input type='checkbox' name='selAll' onclick='checkAll(this)'>" style="text-align:center; width:5%">
				<input type="checkbox" name="chkEmp" value="${grid.userEnob}">
			</display:column>
			<display:column property="slsBrno" title="영업점<br>코드" style="text-align:center; width:5%"/>
			<display:column property="brnm" title="영업점" style="text-align:center; width:15%"/>
			<display:column property="userEnob" title="행번" style="text-align:center; width:10%"/>
			<display:column property="userEnnm" title="사용자명" style="text-align:center; width:15%"/>
			<display:column property="fatcaAuthCd" title="권한코드" style="text-align:center; width:7%"/>	
			<display:column property="fatcaAuthNm" title="권한명" style="text-align:center; width:15%"/>
			<display:column property="chprDtti" title="등록일자" style="text-align:center; width:15%"/>
			<display:column property="chprEnnm" title="등록자" style="text-align:center; width:15%"/>
		</display:table>
	
	<!----- //Grid ----->
	</div>
</div>
</form>

</body>
</html>
