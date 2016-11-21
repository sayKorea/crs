<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/crs_sst.inc"%>
<script>
	$(function() {
// 		alert("${Y}");
		$("#retriveList").click(function() {
			alert("��ȸ��ư");
		});
		//ȭ���ʱ�ȭ
		init();
	});

	// 	combo �ʱ�ȭ
	function init() {
		//���س�� combo
		$.ajax({
			type : "POST",
			url : "<cx:wc/>/sample.do?method=getBaseYmList",
			data : "",
			dataType : "json",
			async : "false",
			success : function(data) {
				var list = data.resultList;
				$("#baseym").empty();
				for (i = 0; i < list.length; i++) {
					$("#baseym").append("<option value='"+list[i].code+"'>" + list[i].value + "</option>");
					$("#baseym1").append("<option value='"+list[i].code+"'>" + list[i].value + "</option>");
					$("#baseym2").append("<option value='"+list[i].code+"'>" + list[i].value + "</option>");
					$("#baseym3").append("<option value='"+list[i].code+"'>" + list[i].value + "</option>");
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.status);
				console.log(jqXHR.statusText);
				console.log(jqXHR.responseText);
				console.log(jqXHR.readyState);
			}
		});

		//����ڸ� combo
	}
</script>
</head>
<body>
	<form method="post" onSubmit="return false;">
		<div id="subMain">
			<div class="top_title">
				<ul>
					<li class="txt">�˻��� 1�� </li>
				</ul>
			</div>
			<div class="sheet_search">
				<div>
					<table>
						<tbody>
							<tr>
								<td><span>���س��</span></td>
								<td><select id="baseym" name="baseym" style="width: 85px"> </select></td>
								<td><span>�ڵ���̵�</span></td>
								<td><input type="text" name="cdIdtfId" value="" style="width: 50px" class="text"></td>
								<td><span>�ڵ��</span></td>
								<td><input type="text" name="cdIdtfKnm" value="" style="width: 150px" class="text"></td>
								<td><span>��ȿ����</span></td>
								<td><input type="text" name="vldValNm" value="" style="width: 150px" class="text"></td>
								<td><button id="retriveList" class="button search">��ȸ</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			</br> </br>
			<div class="top_title">
				<ul>
					<li class="txt">�˻��� 2,3...��</li>
				</ul>
			</div>
			<div class="sheet_search">
				<div>
					<table>
						<tbody>
							<tr>
								<td><span>���س��</span></td>
								<td><select id="baseym1" name="baseym1" style="width: 85px">
								</select></td>
								<td><span>�ڵ���̵�</span></td>
								<td><input type="text" name="cdIdtfId1" value="" style="width: 50px" class="text"></td>
								<td><span>�ڵ��</span></td>
								<td><input type="text" name="cdIdtfKnm1" value="" style="width: 150px" class="text"></td>
								<td><span>��ȿ����</span></td>
								<td><input type="text" name="vldValNm1" value="" style="width: 150px" class="text"></td>
								<td><button id="retriveList1" class="button search">��ȸ</button></td>
							</tr>
							<tr>
								<td><span>���س��</span></td>
								<td><select id="baseym2" name="baseym2" style="width: 85px">
								</select></td>
								<td><span>�ڵ���̵�</span></td>
								<td><input type="text" name="cdIdtfId2" value="" style="width: 50px" class="text"></td>
								<td><span>�ڵ��</span></td>
								<td><input type="text" name="cdIdtfKnm2"  value="" style="width: 150px" class="text"></td>
								<td><span>��ȿ����</span></td>
								<td><input type="text" name="vldValNm2" value="" style="width: 150px" class="text"></td>
							</tr>
							<tr>
								<td><span>���س��</span></td>
								<td><select id="baseym3" name="baseym3" style="width: 85px"> </select></td>
								<td><span>�ڵ���̵�</span></td>
								<td><input type="text" name="cdIdtfId3" value="" style="width: 50px" class="text"></td>
								<td><span>�ڵ��</span></td>
								<td><input type="text" name="cdIdtfKnm3"  value="" style="width: 150px" class="text"></td>
								<td><span>��ȿ����</span></td>
								<td><input type="text" name="vldValNm3" value="" style="width: 150px" class="text"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
