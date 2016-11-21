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
		if(!isNull(f.file,'파일명')){ 
			
			if(confirm("선택하신 기준년월의 코드값이 모두 삭제되고 \n업로드 파일에 있는 코드값으로 재지정됩니다.")){
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
			코드 일괄 등록
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
			<td><span>기준년월</span></td>
			<td>
				<cx:cselect dbname="crymCombo" name="crym" selected="${sessionScope.sessionUser.selCrym}" style="width:85px" />
			</td>
		</tr>
		<tr>	
			<td><span>파일명</span></td>
			<td>
				<input type="file" name="file" class="text" style="width:250px">
			</td>
			<td>
				<li class="btn"><a href="javascript:uploadFile()" class="basic">파일업로드</a></li>	
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
