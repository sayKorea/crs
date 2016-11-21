<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %>

<script language="JavaScript">
	function searchList(){
		var f = document.formx;
		f.mnuId.value = 'CODE_R';		
		f.action = "<cx:wc/>/code.do?method=listCode";
		f.submit();
		showLoading(true);
	}
		
	function goExcel(){
		var f = document.formx;
		f.mnuId.value = 'CODE_R';
		f.target = parent.document.getElementById("ifrmExcel").name;
		f.action = "<cx:wc/>/code.do?method=excelDown";
		f.submit();
		showLoading(true);
		f.target = "";
		showLoading(false);
	}
		
	function goUpload(){
		var f = document.formx;
		window.open("<cx:wc/>/code.do?method=excelUpload&openType=load&crym="+f.crym.value,"pop","width=550,height=200,top=300,left=200");
	}
	
	function __submit(){
		if('${sessionScope.sessionUser.mnuAuthMap.CODE_R}'=='Y'){
			searchList();
		}
	}
		
</script>			
	
</head>
<body>

   
<form name="formx" method="post">
<input type="hidden" name="mnuId">
 
<div id="subMain">
	<div class="top_title">
	<ul>
		<li class="txt">���� �ڵ� ����</li>	
		</li>
	</ul>
	</div>
	<!----- /Search ----->
	<div class="sheet_search"><div>
		<table><tbody>
		<tr>
			<td><span>���س��</span></td>
			<td>
				<cx:cselect dbname="crymCombo" name="crym" selected="${param.crym}" style="width:85px" />
			</td>
			<td><span>�ڵ���̵�</span></td>
			<td>
				<input type="text" name="cdIdtfId" value="${param.cdIdtfId}" style="width:50px"  class="text" />
			</td>
			<td><span>�ڵ��</span></td>
			<td>
				<input type="text" name="cdIdtfKnm" value="${param.cdIdtfKnm}" style="width:150px"  class="text" />
			</td>
			<td><span>��ȿ����</span></td>
			<td>
				<input type="text" name="vldValNm" value="${param.vldValNm}" style="width:150px"  class="text" />
			</td>
			<td>
				<c:if test="${sessionScope.sessionUser.mnuAuthMap.CODE_R == 'Y'}">
					<a href="javascript:searchList()" class="button search" >��ȸ</a>					
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
		<li class="txt"><span>[ �� ${totalCnt}�� ]</span>			
		</li>
		<li class="btn">
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.CODE_R == 'Y'}">
				<a href="javascript:goExcel()" class="basic" >�����ٿ�ε�</a>
			</c:if>	
			<c:if test="${sessionScope.sessionUser.isAdmin}">
				<a href="javascript:goUpload()" class="basic" >�������ε�</a>
			</c:if>
		</li>
	</ul>
	</div>
	<!----- //Title ----->

	<!----- /Grid ----->
		<display:table class="simple" name="${list}" size="${totalCnt}" id="codeGrid" partialList="true" 
			requestURI="/code.do" pagesize="${requestScope.pagingsize}" sort="external" defaultsort="1" export="false" cellspacing="0">
			<display:column property="crym" title="���س⵵" style="text-align:center; width:5%"/>
			<display:column property="cdIdtfId" title="�ڵ�ĺ���<br>���̵�" sortable="true" sortName="cd_idtf_id" style="text-align:center; width:8%"/>	
			<display:column property="cdIdtfKnm" title="�ڵ�ĺ��ڸ�" sortable="true" sortName="cd_idtf_knm" style="text-align:left; width:23%"/>	
			<display:column property="vldVal" title="��ȿ��" style="text-align:center; width:10%"/>			
			<display:column property="vldValNm" title="��ȿ����" style="text-align:left; width:46%"/>
			<display:column property="useYn" title="���<br>����" style="text-align:center; width:5%"/>
		</display:table>
	
	<!----- //Grid ----->
	
</div>
</form>
</body>
</html>
