<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %> 
<OBJECT ID="MADNPCtrl" CLASSID="CLSID:E2EE8C6C-C74F-449C-8B8B-012B47C23E84" codebase="/cab/MADWC.cab" VIEWASTEXT/>
<script language="JavaScript">
		
	function openExcel(fRealFilePath, fNewFileName){		 
		alert(fRealFilePath+ fNewFileName);
		MADNPCtrl.URL = fRealFilePath;
		MADNPCtrl.NewFileName = fNewFileName; 
			
		MADNPCtrl.AutoFlag = "0";					// 0(선택),1(바로열기),2(저장) 
		MADNPCtrl.NURL = ""; 
		nRet = MADNPCtrl.DownloadURL; 
		
		if (nRet == "40000") {
			// 다운로드 성공
			//self.opener = self;
			//self.close();
		}
		else if(nRet == "1") {
			// 다운로드 취소
		}
		else{
			alert("파일열기에 실패 하였습니다." + nRet);
		}
		
		deleteExcel();
	}
	
	function init(){
		parent.document.ifrm.showLoading(false);
		
		alert('${fRealFilePath}');
		openExcel('${fRealFilePath}','${fNewFileName}');
	}
		
	function deleteExcel(){
		var f = document.formx;
		f.action = "<cx:wc/>/code.do?method=deleteExcel";
		f.submit();
	}
	
</script>

</head>
<body onLoad="alert('11111111');init();">    
<form name="formx" method="post">
<input type="hidden" name="fname" value="${fNewFileName}">
</form>  
</body>
</html>
