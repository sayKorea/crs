<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/ftc_sst.inc" %> 

<script>
	
	function searchTarget2(){
 		var f = document.formx;
 		if(!isNull(f.csno, '고객번호')){
 			f.mnuId.value = 'SAME_R';
 			f.action = "<cx:wc/>/acipReady.do?method=samePersonList";
 			f.submit();	
 			showLoading(true);
 		}
	}
	
	function goExcel(){
		var f = document.formx;
		f.mnuId.value = 'SAME_R';
		f.target = parent.document.getElementById("ifrmExcel").name;
		f.action = "<cx:wc/>/acipReady.do?method=samePersonExcelDown";
		f.submit();
		showLoading(true);
		f.target = "";
		showLoading(false);
	}
	
	function linkPersonal(objcrym, objcsno, objcustnm){
		window.open("<cx:wc/>/acipTarget.do?method=personalView&crym="+objcrym+"&csno="+objcsno+"&custNm="+objcustnm+"&mode=popup","popPersonal","width=1180,height=730,top=200,left=200, resizable=yes");
	}	
	
	function goAction(dist){
		var f = document.formx;
		f.mnuId.value = 'SAME_U';
		if(checkInput()){	
			if(dist == 'reg'){
				var msg = '선택한 고객이 동일인으로 등록됩니다. 진행하시겠습니까?';
			}else {
				var msg = '선택한 고객이 동일인에서 해지됩니다. 진행하시겠습니까?';
			}
			
			if(confirm(msg)){
				f.action = "<cx:wc/>/acipReady.do?method=saveSamePerson&dist="+dist;
				f.submit();
				showLoading(true);
			}	
		}	
	}
	
	function searchPopCust(){
		var f = document.formx;
		window.open("<cx:wc/>/code.do?method=searchPopCopr&init=y&crym="+f.crym.value+"&csno="+f.csno.value,"pop","width=650,height=620,top=200,left=200");
	}	

	function addPopCust(){
		var f = document.formx;
		window.open("<cx:wc/>/code.do?method=searchPopCust&init=y&dist=add&crym="+f.crym.value,"pop","width=650,height=620,top=200,left=200");
	}	
	
		
	function __submit(){
		if('${sessionScope.sessionUser.mnuAuthMap.SAME_R}'=='Y'){
			searchTarget();
		}
	}

	function checkAll(obj){
		var tgt = document.formx.chkCsno;
		if(tgt==null){
			return false;
		}
		if(obj.checked){
			setCheckbox(tgt);
		}else{	
			resetCheckbox(tgt);
		}	
	}
	

	function checkInput(){
		var f = document.formx;
		var obj = f.chkCsno;
		
		/*
		if('${isMainComplete}'=='Y'){
			alert('선택한 기준년월의 실사가 마감되었습니다.\nFATCA 담당자에게 문의하시기 바랍니다.');
			return false;
		}
		*/
		
		if(obj==null){
			alert('선택된 고객이 없습니다.');
			return false;
		}
		if(!isChecked(obj, '고객은 한명 이상 선택되어야 합니다.')){
			return;
		}
		
		f.csnoArr.value = "";
		if(isArray(obj)){
			for(i=0; i<obj.length; i++){	
				if(obj[i].checked){
					f.csnoArr.value = f.csnoArr.value+"|"+obj[i].value;
				}	
			}
		}else{
			if(obj.checked){
				f.csnoArr.value = f.csnoArr.value+"|"+obj.value;
			}
		}
		
		return true;
		
	}

	function addRow(crym, csno, csnm, rnno, regdt){
		
		var tbody = document.getElementById("grid").getElementsByTagName("TBODY")[0];
	    var row = document.createElement("tr");	    
	    var td1 = document.createElement("td");
	    td1.setAttribute("style","text-align:center");
	    td1.innerHTML = "<input type=\"checkbox\" name=\"chkCsno\" value=\"00:"+csno+"\">";
	    var td2 = document.createElement("td");	    
	    var td3 = document.createElement("td");
	    td3.setAttribute("style","text-align:center");
	    td3.innerHTML = csno;
	    var td4 = document.createElement("td");
	    td4.setAttribute("style","text-align:center");
	    td4.innerHTML = rnno;
	    var td5 = document.createElement("td");
	    td5.setAttribute("style","text-align:center");
	    td5.innerHTML = "<a href=\"javascript:linkPersonal('"+crym+"','"+csno+"','"+csnm+"');\">"+csnm+"</a>";
	    var td6 = document.createElement("td");
	    td6.setAttribute("style","text-align:center");
	    td6.innerHTML = regdt;
	    var td7 = document.createElement("td");
	    
	    row.appendChild(td1);
	    row.appendChild(td2);
	    row.appendChild(td3);
	    row.appendChild(td4);
	    row.appendChild(td5);
	    row.appendChild(td6);
	    row.appendChild(td7);
	    tbody.appendChild(row);
	}


</script>
</head>
<body onLoad="init()">      
<form name="formx" method="post">
<input type="hidden" name="csnoArr">
<input type="hidden" name="mnuId">
<input type="hidden" name="crym" value="${sessionScope.sessionUser.selCrym}">
<input type="hidden" name="crym" value="${sessionScope.sessionUser.selCrym}">
<div id="subMain">
	
	<!----- /Search ----->
	<!----- /Title ----->
	<div class="top_title">
	<ul>
		<li class="txt">동일인 관리</li>
	</ul>
	</div>
	
	<!----- /Search ----->
	<div class="sheet_search"><div>
		<table><tbody>
		<tr>
			<td>
				<span>고객번호</span>
			</td>
			<td>
				<input type="text" name="csno" value="${param.csno}" maxlength="13" onKeydown="changeSearch('custNm');changeSearch('csnoReal');changeSearch('custSeq');" class="text csno" />
				<input type="hidden" name="csnoReal" value="${param.csnoReal}" >
				<input type="text" name="custNm" value="${selCustNm}" readonly disabled class="readonly csnm" />
				<input type="hidden" name="custSeq" value="${param.custSeq}" />
				<a href="javascript:searchPopCust()"><img src="<cx:wc/>/images/common/icon_search.gif"></a>				
			</td>	
			<td>
				<c:if test="${sessionScope.sessionUser.mnuAuthMap.SAME_R == 'Y'}">
					<a href="javascript:searchTarget()" class="button search" >조회</a>
				</c:if>
			</td>
		</tr>
		</tbody></table>
	</div></div>
	
	<!----- /Title ----->
	<div class="sheet_title">
	<ul>		
		<li class="btn">
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.SAME_U == 'Y'}">
				<a href="javascript:addPopCust()" class="basic">고객추가</a>
				<a href="javascript:goAction('reg')" class="basic">등록</a>
				<a href="javascript:goAction('del')" class="basic">해지</a>
			</c:if>
			<c:if test="${sessionScope.sessionUser.mnuAuthMap.SAME_R == 'Y'}">
				<a href="javascript:goExcel()" class="basic" >엑셀</a>
			</c:if>
		</li>
	</ul>	
	</div>
	
	<!----- /Grid ----->
				
		<table id="grid" class="simple" cellspacing="0">
		<tr>
		<th style="width:5%"><input type='checkbox' name='selAll' onclick='checkAll(this)'></th>
		<th style="width:15%">FATCA동일인<br>식별번호</th>
		<th style="width:15%">고객번호</th>
		<th style="width:15%">실명번호</th>
		<th style="width:15%">고객명</th>
		<th style="width:15%">고객<br>등록일자</th>
		<th style="width:15%">동일인<br>등록일자</th>
		</tr>
		<c:forEach items="${list}" var="grid">
			<tr class="odd">
			<td style="text-align:center">
				<input type="checkbox" name="chkCsno" value="${grid.smpsnId}:${grid.csno}" >
			</td>
			<td style="text-align:center">${grid.smpsnId}</td>
			<td style="text-align:center">${grid.csno}</td>
			<td style="text-align:center" title="${grid.rnno}">${grid.rnnoMsk}</td>
			<td style="text-align:center">
				<a href="javascript:linkPersonal('${param.crym}','${grid.csno}','${grid.custNm}');"  title="${grid.custNm}">${grid.custNmMsk}</a>
			</td>
			<td style="text-align:center">${grid.regDtlFmt}</td>
			<td style="text-align:center">${grid.rgdtFmt}</td></tr>
		</c:forEach>	
		</table>
	<!----- //Grid ----->
	
</div>
</form>

</body>
</html>
