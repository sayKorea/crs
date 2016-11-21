<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %>
<script language="javaScript">

	
	function init(){
		this.resizeTo(600,500);		
		if(opener!=null){	
			opener.location.href = "<cx:wc/>/login.do?method=goLogin";
			setTimeout("this.close()",5000);
		}else{
			location.href = "<cx:wc/>/login.do?method=goLogin";
		}	
		
	}
	
</script>	
</head>
<body onLoad="init()">

<table class="infoWrap"><tbody>
<tr>
	<td>
		<div class="info">
			<ul>
				<li class="title">
					Information - <span>No Session</span>
				</li>
				<li class="contents">
					- 세션이 종료 되었습니다. 로그인페이지로 이동합니다.
				</li>
			</ul>
		</div>
	</td>
</tr>
</tbody></table>


</body>	
</html>