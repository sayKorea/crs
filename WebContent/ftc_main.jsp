<!DOCTYPE html>
<html>

<head>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@include file="/inc/ftc_sst.inc" %>

<script>
	function clickMenu(sVal){
		this.mainmenus = document.getElementById("L_menu");
		this.submenus = this.mainmenus.getElementsByTagName("span");

		if(sVal == "OPN"){
			for (var i = this.submenus.length; i >0 ; i--){				
				var stobj = "m" + i.toString();
				var objDivA = document.getElementById(stobj);
				objDivA.style.display = "block";
				clickEvent("0");
			}	
		}else if(sVal == "CLS"){
			for (var i = this.submenus.length; i >0 ; i--){				
				var stobj = "m" + i.toString();
				var objDivA = document.getElementById(stobj);	
				objDivA.style.display = "none";			
			}
			clickEvent("0");
		}else{
			var objDiv = document.getElementById(sVal);
			for (var i = this.submenus.length; i >0 ; i--){
				var stobj = "m" + i.toString();
				var objDivA = document.getElementById(stobj);
				if(sVal == stobj){
					//메뉴클릭시 빈화면 호출
					document.getElementById("ifrm").src = "<cx:wc/>/layout/ftc_base"+ i.toString() +".jsp";	
										
					if(objDiv.style.display == "none"){
						objDiv.style.display = "block";
					}else{
						objDiv.style.display = "none";
					}
				}else{
					objDivA.style.display = "none";
				}
			}
			clickEvent("0");
		}
	}
	
	
	function goMenu(go){	
		if(go == 'help'){
			//document.getElementById("ifrm").src = "";	
		}else if(go == 'li11'){			
			document.getElementById("ifrm").src = "<cx:wc/>/acipReady.do?method=modify&openType=load";	
			
		}else if(go == 'li21'){
			document.getElementById("ifrm").src = "<cx:wc/>/acipReady.do?method=samePersonList&openType=load";	
		}else if(go == 'li22'){
			document.getElementById("ifrm").src = "<cx:wc/>/acipTarget.do?method=personalList&openType=load";	
		}else if(go == 'li23'){
			document.getElementById("ifrm").src = "<cx:wc/>/acipTarget.do?method=personalView&openType=load";	
		}else if(go == 'li24'){
			document.getElementById("ifrm").src = "<cx:wc/>/acipTarget.do?method=companyList&openType=load";	
		}else if(go == 'li25'){
			document.getElementById("ifrm").src = "<cx:wc/>/acipTarget.do?method=companyView&openType=load";	
		}else if(go == 'li26'){
			document.getElementById("ifrm").src = "<cx:wc/>/acipTarget.do?method=confirmRequestHist&openType=load";
			
		}else if(go == 'li31'){
			document.getElementById("ifrm").src = "<cx:wc/>/acipResult.do?method=personalResult&openType=load";
		}else if(go == 'li32'){
			document.getElementById("ifrm").src = "<cx:wc/>/acipResult.do?method=companyResult&openType=load";
		}else if(go == 'li33'){
			document.getElementById("ifrm").src = "<cx:wc/>/acipResult.do?method=document&openType=load";
		}else if(go == 'li34'){
			document.getElementById("ifrm").src = "<cx:wc/>/acipResult.do?method=question&openType=load";
		}else if(go == 'li35'){
			document.getElementById("ifrm").src = "<cx:wc/>/acipResult.do?method=mainComplete&openType=load";
		}else if(go == 'li36'){
			document.getElementById("ifrm").src = "<cx:wc/>/acipResult.do?method=branchResultList&openType=load";
		}else if(go == 'li41'){
			document.getElementById("ifrm").src = "<cx:wc/>/acipReport.do?method=personalReportList&openType=load";
		}else if(go == 'li42'){
			document.getElementById("ifrm").src = "<cx:wc/>/acipReport.do?method=companyReportList&openType=load";
		}else if(go == 'li51'){			
			document.getElementById("ifrm").src = "<cx:wc/>/acipMonitor.do?method=monitor&openType=load";
		}else if(go == 'li52'){			
			document.getElementById("ifrm").src = "<cx:wc/>/acipMonitor.do?method=year&openType=load";	
		}else if(go == 'li61'){
			document.getElementById("ifrm").src = "<cx:wc/>/code.do?method=listCode&openType=load";
		}else if(go == 'li62'){
			document.getElementById("ifrm").src = "<cx:wc/>/auth.do?method=listUsrAuth&openType=load";
		}else if(go == 'li63'){
			document.getElementById("ifrm").src = "<cx:wc/>/auth.do?method=listMenuAuth&openType=load";
		}else if(go == 'il80'){	
			document.getElementById("ifrm").src = "<cx:wc/>/code.do?method=execQuery&openType=load";
		}
		
		
		/***********/
		/* ADD CRS */
		/***********/
		 else if(go == 'li71'){	document.getElementById("ifrm").src = "<cx:wc/>/template.do?method=templateView";
		}else if(go == 'li81'){ document.getElementById("ifrm").src = "<cx:wc/>/sample.do?method=conditionBarView";
		}else if(go == 'li82'){ document.getElementById("ifrm").src = "<cx:wc/>/sample.do?method=gridView";
		}else if(go == 'li83'){	document.getElementById("ifrm").src = "<cx:wc/>/sample.do?method=masterDeatilView";
		}else if(go == 'li84'){ document.getElementById("ifrm").src = "<cx:wc/>/sample.do?method=masterBindingView";
// 		}else if(go == 'li85'){ document.getElementById("ifrm").src = "<cx:wc/>/sample.do?method=popupView";
		}else if(go == 'li86'){ document.getElementById("ifrm").src = "<cx:wc/>/sample.do?method=ajaxView";
		}else if(go == 'li88'){ document.getElementById("ifrm").src = "<cx:wc/>/sample.do?method=fullView";
		}else{
				alert('작업중입니다...............');
		}
		if(go!='' && go != 'il80'){
			clickEvent(go);
		}	
	}	

	
	function clickEvent(obnum){			
		this.menu = document.getElementById("L_menu");		
		var links = this.menu.getElementsByTagName("a");
		
		//초기에 설정되어 있는 클릭 효과를 초기화
		for (var i = 0; i < links.length; i++){			
			var subMenunum = links[i].id.substring(0,3); //서브메뉴값 설정
			var objMenu = document.getElementById(subMenunum); //서브메뉴 class id 설정
			var objSubMenu = document.getElementById(links[i].id);  //세부항목 메뉴 class id 설정
			objMenu.className = "";	//서브메뉴 효과제거
			objSubMenu.className = "";	 //세부항목 효과제거
		}
		//클릭시 효과를 주는 부분
		if(obnum != '0'){
			var subMenunum = obnum.substring(0,3);
			var objMenu = document.getElementById(subMenunum);
			var objSubMenu = document.getElementById(obnum);
			objMenu.className = "on";
			objSubMenu.className = "on";
		}	
	}
	
	function resizeDiv(){
		var openSrc = "<cx:wc/>/images/common/btn_left_open.gif";
		var closeSrc = "<cx:wc/>/images/common/btn_left_close.gif";
		var ifrm = document.getElementById("subfrm");
		var screen = document.body.clientWidth;
		var reSize = (screen - 77).toString() + "px";
		
		if(document.getElementById("left_menu").style.display == "none"){
			document.getElementById("left_menu").style.display = "block";
			document.getElementById("leftImg").src = closeSrc;
			ifrm.style.width = "935px";
		}else{
			document.getElementById("left_menu").style.display = "none";
			document.getElementById("leftImg").src = openSrc;
			ifrm.style.width = reSize; // 창 사이즈를 가져와서 동적으로 빼준다.
		}
	}

	function resize(){
/* 		var ifrm = document.getElementById("subfrm");
		var screen = document.body.clientWidth;
		var reSize = (screen - 80).toString() + "px";
		if(screen < 1135){
			ifrm.style.width = reSize;			
		} */
	}

	function __submit(){
			
	}
	
	function goLogout() {
        location.href = "<cx:wc/>/login.do?method=logout&isLogout=Y";
    }
	
	
</script>


<body class="bodywrap" onresize="resize()">
<div id="devdiv" style="display:none; position:absolute; width:150px; z-index:1;"></div>
<div id="wrap">
	<!----- /Logo ----->
	<div id="main_logo">
		<img src="<cx:wc/>/images/common/logo.png" height=42/>
		<p class="system_name">FATCA <c:if test="${sessionScope.sessionUser.serverType == 'dev'}"><font color="orange">TEST</font></c:if>
		<span>(해외금융계좌 납세협력 관리시스템)
		<c:if test="${sessionScope.sessionUser.isCong}">
		<a href="javascript:goQuery()" style="cursor:default"></a>
		</c:if></span></p>
	</div>

	<table border="0" cellpadding="0" cellspacing="0" class="layout_table">
	<tbody><tr>
		<td class="left_main">
			<div class="main_menu">
				<!----- /Expand ----->
				<div class="footer">
					<a href="javascript:resizeDiv()"><img id="leftImg" src="<cx:wc/>/images/common/btn_left_close.gif"/></a>
				</div>
			</div>
		</td>
		
		<td id="left_menu" class="left_sub">
			<div class="sub_menu">
				<div class="name">
					<ul>
						<li class="right">
							<!----- /btn-toggle ----->
							<a href="javascript:clickMenu('CLS')"><img src="<cx:wc/>/images/common/btn_left_1step.gif"/></a>
							<a href="javascript:clickMenu('OPN')"><img src="<cx:wc/>/images/common/btn_left_2step.gif"/></a>
						</li>
					</ul>
				</div>
				
				<!----- /GNB ----->
				<div id="L_menu" class="list">
					<ul>
						<li>
							<span style="display:block" onclick="clickMenu('m1')" id="li1">실사준비관리</span>
							<ul style="display:none" id="m1">
								<li><a href="javascript:goMenu('li11')" id="li11">- 실사 기본정보 관리</a></li>
							</ul>
						</li>
						<li>
							<span onclick="clickMenu('m2')" id="li2">실사대상관리</span>
							<ul style="display:none" id="m2">
								<li><a href="javascript:goMenu('li21')" id="li21">- 동일인 관리</a></li>
								<li><a href="javascript:goMenu('li22')" id="li22">- 개인 실사대상자 조회</a></li>								
								<li><a href="javascript:goMenu('li23')" id="li23">- 개인 상세 조회</a></li>
								<li><a href="javascript:goMenu('li24')" id="li24">- 법인 실사대상자 조회</a></li>
								<li><a href="javascript:goMenu('li25')" id="li25">- 법인 상세 조회</a></li>
								<li><a href="javascript:goMenu('li26')" id="li26">- SMS발송 이력 조회</a></li>
							</ul>
						</li>
						<li>
							<span onclick="clickMenu('m3')" id="li3">실사결과관리</span>					
							<ul style="display:none" id="m3">
								<li><a href="javascript:goMenu('li31')" id="li31">- 개인 실사대상 결과 등록</a></li>
								<li><a href="javascript:goMenu('li32')" id="li32">- 법인 실사대상 결과 등록</a></li>
								<li><a href="javascript:goMenu('li33')" id="li33">- 문서실사 결과 등록</a></li>
								<li><a href="javascript:goMenu('li34')" id="li34">- 고객관계관리자 질의 등록</a></li>
								<li><a href="javascript:goMenu('li35')" id="li35">- 본점 실사 완료</a></li>
								<li><a href="javascript:goMenu('li36')" id="li36">- 영업점 실사 결과 조회</a></li>
							</ul>
						</li>
						<li>
							<span onclick="clickMenu('m4')" id="li4">보고관리</span>
							<ul style="display:none" id="m4">
								<li><a href="javascript:goMenu('li41')" id="li41">- 개인 보고서 조회</a></li>
								<li><a href="javascript:goMenu('li42')" id="li42">- 법인 보고서 조회</a></li>
							</ul>
						</li>
						<li>
							<span onclick="clickMenu('m5')" id="li5">현황관리</span>
							<ul style="display:none" id="m5">									
								<li><a href="javascript:goMenu('li51')" id="li51">- FATCA 실사 현황 조회</a></li>
								<li><a href="javascript:goMenu('li52')" id="li52">- FATCA 연도별 실사 이력 조회</a></li>
							</ul>
						</li>
						<li>
							<span onclick="clickMenu('m6')" id="li6">시스템관리</span>
							<ul style="display:none" id="m6">
								<li><a href="javascript:goMenu('li61')" id="li61">- 공통 코드 관리</a></li>
								<li><a href="javascript:goMenu('li62')" id="li62">- 사용자 권한 관리</a></li>
								<li><a href="javascript:goMenu('li63')" id="li63">- 메뉴 권한 관리</a></li>
							</ul>
						</li>
						
						
						<!------------->
						<!-- ADD CRS -->
						<!------------->
						<li>
							<span onclick="clickMenu('m7')" id="li7">TEMPLATE</span>
							<ul style="display:none" id="m7">
								<li><a href="javascript:goMenu('li71')" id="li71">* Template</a></li>
							</ul>
						</li>
						<li>
							<span onclick="clickMenu('m8')" id="li8">SAMPLE</span>
							<ul style="display:none" id="m8">
								<li><a href="javascript:goMenu('li81')" id="li81">* LAYOUT CONDITION BAR</a></li>
<!-- 								<li><a href="javascript:goMenu('li72')" id="li72">* LAYOUT GRID</a></li> -->
								<li><a href="javascript:goMenu('li83')" id="li83">* LAYOUT MASTER DETAIL</a></li>
								<li><a href="javascript:goMenu('li84')" id="li84">* LAYOUT MASTER BINDING</a></li>
<!-- 								<li><a href="javascript:goMenu('li85')" id="li85">* LAYOUT POPUP</a></li> -->
<!-- 								<li><a href="javascript:goMenu('li86')" id="li86">* UTIL AJAX</a></li> -->
<!-- 								<li><a href="javascript:goMenu('li87')" id="li87">* UTIL TAG</a></li> -->
								<li><a href="javascript:goMenu('li88')" id="li88">* LAYOUT FULL</a></li>
							</ul>
						</li>
					</ul>					
				</div>
				<!----- //GNB ----->
				
	
			</div>
		</td>
		<td class="layout_content">
			<div id="container" class="container">

				<!----- /Utility ----->
				<div class="layout_header">
					<ul>
						<li class="right">
							<div class="top_search">
								<span class="name"><b>${sessionScope.sessionUser.userEnnm}</b> 님 [${sessionScope.sessionUser.slsBrnm} _ ${sessionScope.sessionUser.fatcaAuthNm}]</span>
								<span class="bar">|</span>
								<a href="javascript:goLogout()">로그아웃</a>								
							</div>
						</li>
					</ul>
				</div>
				
				<!----- /Main Contents ----->
				<div id="subfrm" class="layout_subContents">
					<iframe name="ifrm" id="ifrm" src="<cx:wc/>/layout/ftc_base1.jsp" frameborder="0" class="tab_iframes"></iframe>
				</div>
				<iframe name="ifrmExcel" id="ifrmExcel" width="0" height="0" border="0"></iframe>
				<iframe name="ifrmCust" id="ifrmCust" width="0" height="0" border="0"></iframe>
			</div>
		</td>
	</tr>
	</tbody></table>
</div>
</body></html>