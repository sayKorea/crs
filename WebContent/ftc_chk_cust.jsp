<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %> 
<script language="JavaScript">
		
	
	function init(){
		
		var f = document.formx;
		
		if(f.mode.value=='popup'){ //개인,법인 상세 팝업
			if(f.custCnt.value=='0'){ //입력정보 고객 없음
				parent.alert('입력하신 정보와 일치하는 고객이 없습니다.');
			}else if (f.custCnt.value=='1'){ //한명
				parent.document.formx.custNm.value = f.custNm.value;
				parent.document.formx.csnoReal.value = f.csnoReal.value;
				parent.document.formx.custSeq.value = f.custSeq.value;
				if(f.isSearch.value == 'Y'){
					parent.searchTarget2();	
				}
			}else{ //하나 이상
				parent.searchPopCust();
			}
		}else{ 
			if(f.custCnt.value=='0'){ //입력정보 고객 없음
				parent.document.ifrm.alert('입력하신 정보와 일치하는 고객이 없습니다.');
			}else if (f.custCnt.value=='1'){ //한명
				parent.document.ifrm.document.formx.custNm.value = f.custNm.value;
				parent.document.ifrm.document.formx.csnoReal.value = f.csnoReal.value;
				parent.document.ifrm.document.formx.custSeq.value = f.custSeq.value;
				if(f.isSearch.value == 'Y'){
					parent.document.ifrm.searchTarget2();	
				}
			}else{ //하나 이상
				parent.document.ifrm.searchPopCust();
			}
			
		}
		
	}
		
	
</script>

</head>
<body onLoad="init()">    
<form name="formx" method="post">
<input type="hidden" name="custCnt" value="${custCnt}">
<input type="hidden" name="custNm" value="${custNm}">
<input type="hidden" name="csnoReal" value="${csnoReal}">
<input type="hidden" name="custSeq" value="${custSeq}">
<input type="hidden" name="isSearch" value="${param.isSearch}">
<input type="hidden" name="mode" value="${param.mode}">
</form>  
</body>
</html>
