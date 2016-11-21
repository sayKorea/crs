<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %>


<script language="JavaScript">
	function searchList(){
		var f = document.formx;
		if(inputCheck()){
			f.action = "<cx:wc/>/code.do?method=searchPopEmp&slsBrno="+f.slsBrno.value;
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
	

	function select(no, nm){
		var opf = opener.document.formx;	
		var f = document.formx;

		dist = document.formx.dist.value;
		
		if(f.csnoArr.value == "" && f.csnoSeqArr.value == ""){ //�˾���ȸ	
			if(dist == 'cgp'){
				opf.fatcaAcipCgpEnob.value = no;
				opf.fatcaAcipCgpEnnm.value = nm;
			}else if(dist == 'rppr'){
				opf.fatcaAcipRpprEnob.value = no;
				opf.fatcaAcipRpprEnnm.value = nm;
			}else if(dist == 'rel'){
				opf.fatcaCustRelMngrEnob.value = no;
				opf.fatcaCustRelMngrEnnm.value = nm;	
			}
		
			sclose();
		}else if(f.csnoArr.value != "" || f.csnoSeqArr.value != ""){ //����
			
			if(confirm("�����Ͻ� �������η� �ϰ� �����Ͻðڽ��ϱ�?")){
				f.action = "<cx:wc/>/code.do?method=saveCharge&setNo="+no;
				f.submit();
				showLoading(true);
				//alert( '${param.popName}' + " ������ �Ϸ�Ǿ����ϴ�.");
				opener.searchTarget2();
				sclose();
			}
		}
		
	}
	
	function inputCheck(){
		var f = document.formx;
		if(f.enob.value == '' && f.userNm.value == '' ){
			alert('��� �Ǵ� �������� �Է��ϼ���');
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
<input type="hidden" name="popName" value="${param.popName}">
<input type="hidden" name="slsBrno" value="${param.slsBrno}">
<div class="popWrap">
<!----- /Title ----->
<div class="popTop">
	<ul>
		<li class="left">
			${param.popName}
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
			<td><span>���</span></td>
			<td>
				<input type="text" name="enob" value="${param.enob}" maxlength="13" class="text" />
			</td>
			<td><span>������</span></td>
			<td>
				<input type="text" name="userNm" value="${param.userNm}" maxlength="30" class="text"  style="ime-mode:active"/>
			</td>
			<td>
				<a href="javascript:searchList()" class="button search" >��ȸ</a>
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
			<display:column property="enob" title="���" style="text-align:center; width:10%"/>	
			<display:column title="������" style="text-align:center; width:20%">	
				<a href="javascript:select('${grid.enob}','${grid.userNm}');">${grid.userNm}</a>
			</display:column>	
			<display:column property="fatcaAuthNm" title="���Ѹ�" style="text-align:center; width:15%"/>
			<display:column property="brnm" title="�Ҽ���" style="text-align:center; width:15%"/>
		</display:table>
	
	<!----- //Table ----->

</div>
<!----- //Contents ----->
	
<!----- /Footer ----->
<!-- div class="popFootWrap">
	<table><tbody>
	<tr>
		<td>
			<a href="javascript:close()" class="large gray">�ݱ�</a>
		</td>
	</tr>
	</tbody></table>
</div -->

</form>

</body>
</html>
