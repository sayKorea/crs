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
					//�޴�Ŭ���� ��ȭ�� ȣ��
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
				alert('�۾����Դϴ�...............');
		}
		if(go!='' && go != 'il80'){
			clickEvent(go);
		}	
	}	

	
	function clickEvent(obnum){			
		this.menu = document.getElementById("L_menu");		
		var links = this.menu.getElementsByTagName("a");
		
		//�ʱ⿡ �����Ǿ� �ִ� Ŭ�� ȿ���� �ʱ�ȭ
		for (var i = 0; i < links.length; i++){			
			var subMenunum = links[i].id.substring(0,3); //����޴��� ����
			var objMenu = document.getElementById(subMenunum); //����޴� class id ����
			var objSubMenu = document.getElementById(links[i].id);  //�����׸� �޴� class id ����
			objMenu.className = "";	//����޴� ȿ������
			objSubMenu.className = "";	 //�����׸� ȿ������
		}
		//Ŭ���� ȿ���� �ִ� �κ�
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
			ifrm.style.width = reSize; // â ����� �����ͼ� �������� ���ش�.
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
		<span>(�ؿܱ������� �������� �����ý���)
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
							<span style="display:block" onclick="clickMenu('m1')" id="li1">�ǻ��غ����</span>
							<ul style="display:none" id="m1">
								<li><a href="javascript:goMenu('li11')" id="li11">- �ǻ� �⺻���� ����</a></li>
							</ul>
						</li>
						<li>
							<span onclick="clickMenu('m2')" id="li2">�ǻ������</span>
							<ul style="display:none" id="m2">
								<li><a href="javascript:goMenu('li21')" id="li21">- ������ ����</a></li>
								<li><a href="javascript:goMenu('li22')" id="li22">- ���� �ǻ����� ��ȸ</a></li>								
								<li><a href="javascript:goMenu('li23')" id="li23">- ���� �� ��ȸ</a></li>
								<li><a href="javascript:goMenu('li24')" id="li24">- ���� �ǻ����� ��ȸ</a></li>
								<li><a href="javascript:goMenu('li25')" id="li25">- ���� �� ��ȸ</a></li>
								<li><a href="javascript:goMenu('li26')" id="li26">- SMS�߼� �̷� ��ȸ</a></li>
							</ul>
						</li>
						<li>
							<span onclick="clickMenu('m3')" id="li3">�ǻ�������</span>					
							<ul style="display:none" id="m3">
								<li><a href="javascript:goMenu('li31')" id="li31">- ���� �ǻ��� ��� ���</a></li>
								<li><a href="javascript:goMenu('li32')" id="li32">- ���� �ǻ��� ��� ���</a></li>
								<li><a href="javascript:goMenu('li33')" id="li33">- �����ǻ� ��� ���</a></li>
								<li><a href="javascript:goMenu('li34')" id="li34">- ����������� ���� ���</a></li>
								<li><a href="javascript:goMenu('li35')" id="li35">- ���� �ǻ� �Ϸ�</a></li>
								<li><a href="javascript:goMenu('li36')" id="li36">- ������ �ǻ� ��� ��ȸ</a></li>
							</ul>
						</li>
						<li>
							<span onclick="clickMenu('m4')" id="li4">�������</span>
							<ul style="display:none" id="m4">
								<li><a href="javascript:goMenu('li41')" id="li41">- ���� ���� ��ȸ</a></li>
								<li><a href="javascript:goMenu('li42')" id="li42">- ���� ���� ��ȸ</a></li>
							</ul>
						</li>
						<li>
							<span onclick="clickMenu('m5')" id="li5">��Ȳ����</span>
							<ul style="display:none" id="m5">									
								<li><a href="javascript:goMenu('li51')" id="li51">- FATCA �ǻ� ��Ȳ ��ȸ</a></li>
								<li><a href="javascript:goMenu('li52')" id="li52">- FATCA ������ �ǻ� �̷� ��ȸ</a></li>
							</ul>
						</li>
						<li>
							<span onclick="clickMenu('m6')" id="li6">�ý��۰���</span>
							<ul style="display:none" id="m6">
								<li><a href="javascript:goMenu('li61')" id="li61">- ���� �ڵ� ����</a></li>
								<li><a href="javascript:goMenu('li62')" id="li62">- ����� ���� ����</a></li>
								<li><a href="javascript:goMenu('li63')" id="li63">- �޴� ���� ����</a></li>
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
								<span class="name"><b>${sessionScope.sessionUser.userEnnm}</b> �� [${sessionScope.sessionUser.slsBrnm} _ ${sessionScope.sessionUser.fatcaAuthNm}]</span>
								<span class="bar">|</span>
								<a href="javascript:goLogout()">�α׾ƿ�</a>								
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