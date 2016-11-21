<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %> 
<script>

	function search(){
 		var f = document.formx;
		f.mnuId.value = 'MENU_R';	
		f.action = "<cx:wc/>/auth.do?method=listMenuAuth";
		f.submit();
		showLoading(true);
	}

	function goExcel(){
		var f = document.formx;
		f.mnuId.value = 'MENU_R';
		f.target = parent.document.getElementById("ifrmExcel").name;
		f.action = "<cx:wc/>/auth.do?method=menuExcelDown";
		f.submit();
		showLoading(true);
		f.target = "";
		showLoading(false);
	}
	
	function resizeH(){
		var setDiv = document.getElementById("subgrid");
		var divTitle = document.getElementById("divTitle");
		var screenW = document.body.clientWidth;
		var gridHeight = 470;
		var screenH = document.body.clientHeight;
		
		//ȭ���� height�� 595���� Ŀ�� ��� display �±��� DIV�� �������� �����Ѵ�.
		//595�� ȭ�� ������ ���� �޶�����. 
		if(screenH > 595){
			setDiv.style.height = gridHeight + (screenH - 600)+ "px";
		}
		
		//ȭ�� �ÿ����� ��ư Ŭ���� ���� â�� ����� �˼��� �����Ƿ� �⺻������ 935�� �ٸ��� 
		//div width�� ���̸� �����Ѵ�.
		if(divTitle.offsetWidth != 935){
			setDiv.style.Width = screenW + "px";			
		}else{
			setDiv.style.Width = divTitle.offsetWidth + "px";
		}
	}
	

	function resizeW(){
		var setDiv = document.getElementById("subgrid");
		var screenW = document.body.clientWidth;
		
		//ȭ�� �ÿ����� ��ư Ŭ���� ���� â�� ����� �˼��� �����Ƿ� �⺻������ 935�� �ٸ��� 
		//div width�� ���̸� �����Ѵ�.		
		setDiv.style.Width = screenW + "px";
	}
	
	
	function save(){
		var f = document.formx;
		if(confirm("�޴� ������ ���� �Ͻðڽ��ϱ�?")){
			f.mnuId.value = 'MENU_U';	
			f.action = "<cx:wc/>/auth.do?method=saveMenuAuth";
			f.submit();
			showLoading(true);
		}
	}

	function sclose(){
		self.close();
	}
	
	function init(){
		resizeW();
		resizeH();
	}

	function __submit(){
		if('${sessionScope.sessionUser.mnuAuthMap.MENU_R}'=='Y'){
			search();
		}
	}
	
</script>
</head>
<body onresize="resizeH()" onload="init()">
<form name="formx" method="post">
<input type="hidden" name="custNm" value="${param.custNm}">
<input type="hidden" name="mode" value="${param.mode}">
<input type="hidden" name="mnuId">

<!----- /Title ----->
<div class="top_title" id="divTitle">
<ul>
	<li class="txt">�޴� ���� ����</li>
</ul>
</div>
<!----- //Title ----->

<div id="subMain" >	
	<!----- /Search ----->
	<div class="sheet_search"><div>
		<table><tbody>
		<tr>
			<td><span>����</span></td>
			<td>
				<cx:cselect codekey="131" name="fatcaAuthCd" selected="${param.fatcaAuthCd}" style="width:110px" />
			</td>
			<td>
				<c:if test="${sessionScope.sessionUser.mnuAuthMap.MENU_R == 'Y'}">
					<a href="javascript:search()" class="button search">��ȸ</a>
				</c:if>
			</td>
		</tr>
		</tbody></table>
	</div></div>
	<!----- //Search ----->
	
	<!----- /Title ----->
	<div class="sheet_title">
	<ul>
		<li class="btn">
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.MENU_U == 'Y'}">
				<a href="javascript:save()" class="basic">����</a>
			</c:if>
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.MENU_R == 'Y'}">
				<a href="javascript:goExcel()" class="basic" >����</a>
			</c:if>
		</li>
	</ul>
	</div>
	
    <c:choose>
    <c:when test="${empty list}"><c:set var="totalCnt" value="0"></c:set></c:when>
    <c:otherwise><c:set var="totalCnt" value="${list[0].totalCnt}"></c:set></c:otherwise>
    </c:choose>
	
	<!----- //Title ----->
	<div id="subgrid" style="width:935px; height:472px; position: absolute;border:none;overflow-x:auto;">
		<!----- /Grid ----->
			<display:table class="simple" name="${list}" size="${totalCnt}" id="grid" partialList="true" 
				requestURI="/auth.do" pagesize="0" sort="external" defaultsort="1" export="false" cellspacing="0">
				<display:column property="mnuId" title="�޴�ID" style="text-align:center;"/>
				<display:column property="mnuNm" title="�޴���" style="text-align:left;"/>
				<display:column title="���ѿ���" style="text-align:center">
					<select name="useYn" class="inputbox" style="width:60px">
						<option value="Y" <c:if test="${grid.useYn == 'Y'}">selected</c:if>>Y</option>
						<option value="N" <c:if test="${grid.useYn == 'N'}">selected</c:if>>N</option>
					</select>
					<input type="hidden" name="menuId" value="${grid.mnuId}">
					<input type="hidden" name="befUseYn" value="${grid.useYn}">
				</display:column>
				<display:column property="chprDtti" title="�������" style="text-align:center;"/>
				<display:column property="chprEnnm" title="�����" style="text-align:center;"/>
			</display:table>	
		<!----- //Grid ----->
	</div>
	
</div>
</form>
</body>
</html>