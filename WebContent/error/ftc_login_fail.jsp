<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %>
</head>

<body>

<table class="infoWrap"><tbody>
<tr>
	<td>
		<div class="info">
			<ul>
				<li class="title">
					Information - <span>로그인 실패</span>
				</li>
				<li class="contents">
					- 입력하신 로그인 정보와 FATCA 시스템에 등록된 정보가 일치하지 않습니다.<br>
					- 로그인 정보 또는 등록된 사용자 여부를 확인하시기 바랍니다. 
				</li>
				<li class="contents">
					<strong>문의처</strong><br />
					- FATCA 시스템 관리자
				</li>
				<li>
					<input type="text" class="large gray" value="이전단계" onClick="history.go(-1);"/>
				</li>
			</ul>
		</div>
	</td>
</tr>
</tbody></table>

</body>
</html>
