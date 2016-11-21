<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/crs_sst.inc"%>
<script>
	$(function() {
// 		$("#test").click(function() {
// 			$("#testdiv").html("test jquery");
// 		});

// 		$("#getjson").click(function() {
// 			$.ajax({
// 				type : "POST",
// 				url : "<cx:wc/>/sample.do?method=select",
// 				data : "",
// 				dataType : "json",
// 				async : "false",
// 				success : function(jdata) {
// 					alert(jdata.test.test1);
// 				},
// 				error : function(e) {
// 					console.log("error:" + e);
// 				}
// 			});
// 		});
		$("#retriveList").click(function(){
			alert("조회버튼");	
		});
		
		//화면초기화
		init();
	});
	
// 	combo 초기화
	function init(){
		//기준년월 combo
		$.ajax({
			type 		: "POST",
			url 		: "<cx:wc/>/sample.do?method=getBaseYmList",
			data 		: "",
			dataType 	: "json",
			async 		: "false",
			success 	: function(data) {
				var list = data.resultList;
				$("#baseym").empty();
				for(i=0; i<list.length; i++){
					$("#baseym").append("<option value='"+list[i].code+"'>"+list[i].value+"</option>");
				}
			},
			error 		: function( jqXHR, textStatus, errorThrown ) {
				console.log( jqXHR.status 		);
				console.log( jqXHR.statusText 	);
				console.log( jqXHR.responseText );
				console.log( jqXHR.readyState 	);
			}
		});
		
		//사용자명 combo
	}
</script>
</head>
<body>
	<div class="top_title">
		<ul>
			<li class="txt">검색바 1단 SAMPLE</li>	
		</ul>
	</div>
	<div class="sheet_search">
		<div>
			<table>
				<tbody>
					<tr>
						<td><span>기준년월</span></td>
						<td><select id="baseym" name="baseym" style="width: 85px"> </select></td>
						<td><span>코드아이디</span></td>
						<td><input type="text" name="cdIdtfId" value="" style="width: 50px" class="text"></td>
						<td><span>코드명</span></td>
						<td><input type="text" name="cdIdtfKnm" value="" style="width: 150px" class="text"></td>
						<td><span>유효값명</span></td>
						<td><input type="text" name="vldValNm" value="" style="width: 150px" class="text"></td>
						<td><button id="retriveList" class="button search">조회</button></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	</br>
	</br>
	<div class="top_title">
		<ul>
			<li class="txt">검색바 2,3...단 SAMPLE</li>	
		</ul>
	</div>
	<div class="sheet_search">
		<div>
			<table>
				<tbody>
					<tr>
						<td><span>기준년월</span></td>
						<td><select id="baseym1" name="baseym1" style="width: 85px"> </select></td>
						<td><span>코드아이디</span></td>
						<td><input type="text" name="cdIdtfId1" value="" style="width: 50px" class="text"></td>
						<td><span>코드명</span></td>
						<td><input type="text" name="cdIdtfKnm1" value="" style="width: 150px" class="text"></td>
						<td><span>유효값명</span></td>
						<td><input type="text" name="vldValNm1" value="" style="width: 150px" class="text"></td>
						<td><button id="retriveList1" class="button search">조회</button></td>
					</tr>
					<tr>
						<td><span>기준년월</span></td>
						<td><select id="baseym2" name="baseym2" style="width: 85px"> </select></td>
						<td><span>코드아이디</span></td>
						<td><input type="text" name="cdIdtfId2" value="" style="width: 50px" class="text"></td>
						<td><span>코드명</span></td>
						<td><input type="text" name="cdIdtfKnm"2 value="" style="width: 150px" class="text"></td>
						<td><span>유효값명</span></td>
						<td><input type="text" name="vldValNm2" value="" style="width: 150px" class="text"></td>
					</tr>
					<tr>
						<td><span>기준년월</span></td>
						<td><select id="baseym2" name="baseym2" style="width: 85px"> </select></td>
						<td><span>코드아이디</span></td>
						<td><input type="text" name="cdIdtfId2" value="" style="width: 50px" class="text"></td>
						<td><span>코드명</span></td>
						<td><input type="text" name="cdIdtfKnm"2 value="" style="width: 150px" class="text"></td>
						<td><span>유효값명</span></td>
						<td><input type="text" name="vldValNm2" value="" style="width: 150px" class="text"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	
</body>
</html>
