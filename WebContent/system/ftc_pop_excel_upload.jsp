<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %>
<script language="JavaScript">

	
	function __submit(){
		
	}
	
	function sclose(){
		self.close();
	}
	

	function uploadFile(){
		
		var f = document.formx;
		if(!isNull(f.file,'���ϸ�')){ 
			
			if(confirm("�����Ͻ� ���س���� �ڵ尪�� ��� �����ǰ� \n���ε� ���Ͽ� �ִ� �ڵ尪���� �������˴ϴ�.")){
				f.action = "<cx:wc/>/code.do?method=excelUpload";
				f.submit();
				showLoading(true);
				
				//setTimeout(delayClose(),5000);
			}
		}
		
	}
	
	function delayClose(){
		opener.searchList();
		sclose();
	}

	function init(){
		self.window.focus();
		if('${rtnMsg}'!=''){
			alert('${rtnMsg}');
			delayClose();
		}
	}
	
	
</script>			
	
</head>
<body onload="init()">

   
<form name="formx" method="post" enctype="multipart/form-data">
<div class="popWrap">
<!----- /Title ----->
<div class="popTop">
	<ul>
		<li class="left">
			�ڵ� �ϰ� ���
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
			<td><span>���س��</span></td>
			<td>
				<cx:cselect dbname="crymCombo" name="crym" selected="${sessionScope.sessionUser.selCrym}" style="width:85px" />
			</td>
		</tr>
		<tr>	
			<td><span>���ϸ�</span></td>
			<td>
				<input type="file" name="file" class="text" style="width:250px">
			</td>
			<td>
				<li class="btn"><a href="javascript:uploadFile()" class="basic">���Ͼ��ε�</a></li>	
			</td>
		</tr>
		</tbody></table>
	</div></div>
	<!----- //Search ----->
	</div>
</div>
<!----- //Contents ----->
	
</form>

</body>
</html>
