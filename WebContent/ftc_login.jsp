<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/kkl.tld" prefix="cx" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8">
<title>제주은행-FATCA</title>

<link rel="stylesheet" href="<cx:wc/>/css/dotum.css" />
<link rel="stylesheet" href="<cx:wc/>/css/base.css" />
<link rel="stylesheet" href="<cx:wc/>/css/login.css" /> 
<script type="text/javascript" src="<cx:wc/>/js/ftc_common2.js"></script>

<script language="JavaScript">
		
	function checkLogin(){
		var f = document.formx;
		
		if (f.userId.value == '') {
			alert('사용자행번을 입력해 주세요.');
			f.userId.focus();
			return false;
		}
		if (f.password.value == '') {
			alert('비밀번호를 입력해 주세요.');
			f.password.focus();
			return false;
		}
		
		return true;
	}
		
	function goLogin() {
		var f = document.formx;
		
		if(checkLogin()){
			f.action = "<cx:wc/>/login.do?method=login";
			f.submit();
		}
		
	}

		
	function goLogout() {
        location.href = "<cx:wc/>/login.do?method=goLogin&isLogout=Y";
    }
	
	function __submit(){
		goLogin();
	}
	
	function init(){
		var f = document.formx;		
		
		
		
		breakOut();
		
		var msg = '${loginMessage}';
		if(msg){
			alert(msg);
			document.formx.userId.focus();
		}
	}
	
	function breakOut() { 
		
		if(opener != null){			
			opener.location.href = "<cx:wc/>/ftc_login.jsp";
			self.close();
		}else if (self != top) {
	        top.location.href = "<cx:wc/>/ftc_login.jsp";
		}
	}
	
	
</script>

</head>
<body class="login" onload="init()">
<form name="formx" method="post">
<div id="devdiv" style="display:none; position:absolute; width:150px; z-index:1;"></div>
<div class="login_wrap">
	
	<div class="login_main">
	<div class="login_main_wrap">
			
			<!----- /Logo, System Name ----->
			<div class="top">
				<ul>
					<li class="left"><img src="<cx:wc/>/images/common/logo.png" ></li>
					<li class="right"><strong>FATCA</strong> System</li>
				</ul>
			</div>
			
			<!----- /Contents Wrap ----->
			<div class="body">
				
				<div class="contents">
					<div class="form_wrap">
						<!----- /ID ----->
						<div class="id">
							<input id="userId" name="userId" type="text" maxLength="7" value="930706" style="ime-mode: inactive" placeholder="사용자행번" />
						</div>
						<!----- /Password ----->
						<div class="password">
							<input id="password" name="password" type="password" maxLength="10" value="077" placeholder="비밀번호" />
						</div>
						<!----- /Btn Login ----->
						<br>
						<div class="btn">
							<a href="javascript:goLogin()"><img src="<cx:wc/>/images/login/btn_login.gif"></a>
						</div>
					</div>
				</div>

				<!----- /Copyright ----->
				<div class="footer">
					<div class="copyright">Copyright(C) DAEGU BANK. All Rights Reserved.</div>	
				</div>
			</div>
			<!----- //Contents Wrap ----->

	</div>
	</div>
</div>
</form>
</body>
</html>