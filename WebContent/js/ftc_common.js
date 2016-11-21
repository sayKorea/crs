/******************************************************************************
  _common.js : ���� Utility �Լ� ���̺귯��
  
  �ۼ��� : 2001. 08. 14
  ������ : 
 *****************************************************************************/

/*----------------------------------------------------------------------------
 *  ������ ����üũ
 *---------------------------------------------------------------------------*/
var browser = navigator.appName;
var version = parseFloat(navigator.appVersion);
if ((Math.round(parseFloat(navigator.appVersion)*100)) - (parseInt(navigator.appVersion) * 100) == 0) {
    version = version + ".0";
        
}
if (navigator.appName.substring(0,9) == "Microsoft") {
    msiestart   = (navigator.appVersion.indexOf('(') + 1);
    msieend     = navigator.appVersion.indexOf(')');
    msiestring  = navigator.appVersion.substring(msiestart, msieend);
    msiearray   = msiestring.split(";");
    msieversion = msiearray[1].split(" ");
    platform    = msiearray[2];
    version     = msieversion[2];
    var browser=navigator.appName;
}



/*----------------------------------------------------------------------------
 *  ���� Utility 
 *---------------------------------------------------------------------------*/
// init_focus : invocates the focus of the given item
// you can use this function for the "onload" event in body tag
function init_focus(itemnm){
	
  if (itemnm != null) {
      var objs = document.getElementsByName(itemnm);
  
      // �ش� ������Ʈ�� �߰ߵ� ��� ��Ŀ��!
      if (objs.length > 0) {
          objs(0).focus();
          return;
      }
  }
          
  if (document.forms.length == 0) return;

  // �ش� ������Ʈ�� �߰ߵ��� �ʴ� ��� ������ ù��° input �׸� focus�� ����
  var field = document.forms[0];
  for (i = 0; i < field.length; i++) {
      if ((field.elements[i].type == "text") || 
          (field.elements[i].type == "textarea") || 
          (field.elements[i].type.toString().charAt(0) == "s")) {
         document.forms[0].elements[i].focus();
         break;
      }
  }
}


// input-text�� ���� Return���� ���� ��� �Ķ���ͷ� �Ѱ��� �Լ��� ȣ���Ѵ�.
// �˻���� ������ �ش� �˻��׸� input�� ���� onkeydown=keydown(�˻��Լ���)�� ���� ���
function keydown(reffunc) {
  if (window.event.keyCode == 13) {
      window.event.cancelBubble = true
      if (reffunc != null) reffunc(keydown.arguments)
  }
}


/*----------------------------------------------------------------------------
 *  �޼���(divMessage) ���� �Լ�
 *---------------------------------------------------------------------------*/
function show_message(msg, mtop) {
	
	var m = document.all.item("divMessage");
	
	if (m == null) {
		sHTML = "<div id=divMessage ondblclick=\"divMessage.style.display='none';\" class=waitmsg></div>";
		document.body.insertAdjacentHTML("afterBegin",sHTML);

		m = document.all.divMessage;
	}
	
	if (msg == "") 
		m.innerHTML = "��ø� ��ٸ��ʽÿ�..."
	else
		m.innerHTML = msg
	
	if (m != null) {
		m.style.height = 40
		m.style.width  = 500
		
		if (mtop == null)
			m.style.top = (document.body.clientHeight - 40) / 2 - 10
		else
			m.style.top = mtop;
		
		m.style.left  = (document.body.clientWidth - 500) / 2 - 40
		m.style.clip = "rect(auto auto auto auto)";
		m.style.zIndex = 0;
		m.style.display = 'block';
	}
}


function hide_message() {
	
	var m = document.all.divMessage;
	
	if (m != null) m.style.display = 'none';
}


/*----------------------------------------------------------------------------
 *  ��ġ ���� �Լ�
 *---------------------------------------------------------------------------*/
// dialogWindow �߾����� �ű��
function dialog_center(w) {
	
	var x = (screen.width  - parseInt(w.dialogWidth))  / 2; 
	var y = (screen.height - parseInt(w.dialogHeight)) / 2;
	w.dialogLeft = x;
	w.dialogTop = y;
}

// Sub-Window �߾����� �ű��
function win_center(w) {
	if (opener == null) {
		dialog_center(w);
	}
	else {
		var x = (screen.width  - document.body.offsetWidth) / 2; 
		var y = (screen.height - document.body.offsetHeight) / 2;
		w.moveTo (x, y);
	}
}

// �ش� element�� ��ġ�� ȭ���� ��ũ���Ѵ�.
function scroll_to(tgtElement) {
  if (tgtElement == null)   window.scrollTo(0, 0);     
  else                      window.scrollTo(0, tgtElement.offsetTop);     
}


/*----------------------------------------------------------------------------
 *  �޴����� �̺�Ʈ ����
 *---------------------------------------------------------------------------*/
function menumouse_click() {
  if ((window.event.button==2) || (window.event.button==3)) {
    alert("���Ȼ� ���콺 ������ ��ư�� ����Ҽ� �����ϴ�.");
  }
}

function menukey_click() {
  if (window.event.keyCode==93) {
    alert("���Ȼ� �޴����⸦ �� �� �����ϴ�.");
  }
}

// �޴����� �̺�Ʈ�� �����Ϸ��� �Ʒ� �� ���忡�� //�� ����
//document.onmousedown=menumouse_click
//document.onkeydown=menukey_click


//��½� �˻� �� button�� ������ �κ��� ��µ��� �ʵ��� �� ��� ��
//��·α׸� ����� ���� ��� �տ� //�� ����
//window.onbeforeprint = beforeprint
//window.onafterprint = afterprint

/*----------------------------------------------------------------------------
 *  ��Ÿ
 *---------------------------------------------------------------------------*/
/*
var debugmsg = '';

function debug (str, flag) {
	if (flag) {
		debugmsg = debugmsg + str+"\n";
	} else {
		debugmsg = str;
	}
	document.all.debugwindow.innerText = debugmsg;
} */


function CheckResident_number(resident_number1, resident_number2){
	blnResident_number = false
	// �߸��� ��������� �˻��մϴ�.
	b_Year = (resident_number2.charAt(0) <= "2") ? "19" : "20"
	/* 2000�⵵���� ������ ��ȣ�� �ٲ������ �������� 2���� �۴ٸ�
	1900�⵵ ���̵ǰ� 2���� ũ�ٸ� 2000�⵵ �̻���� �˴ϴ�. 
	�� 1800�⵵ ���� ��꿡�� �����մϴ�.*/

	b_Year += resident_number1.substr(0, 2)
	// �ֹι�ȣ�� �տ��� 2�ڸ��� �̾ 4�ڸ��� ������ �����մϴ�.
	b_Month = resident_number1.substr(2, 2) - 1
	// ���� ���մϴ�. 1�� ������ �ڹٽ�ũ��Ʈ������ 1���� 0���� ǥ���ϱ� �����Դϴ�.
	b_Date = resident_number1.substr(4, 2)
	b_sum = new Date(b_Year, b_Month, b_Date)
	if ( b_sum.getYear() % 100 != resident_number1.substr(0, 2) || b_sum.getMonth() != b_Month || b_sum.getDate() != b_Date) {
	return blnResident_number
	// ��������� Ÿ�缺�� �˻��Ͽ� ������ ������ �����޼����� ��Ÿ��
	}

	total = 0
	temp = new Array(13)
	for(i=1; i<=6; i++) temp[i] = resident_number1.charAt(i-1)
	for(i=7; i<=13; i++) temp[i] = resident_number2.charAt(i-7)
	for(i=1; i<=12; i++) {
	k = i + 1
	if(k >= 10) k = k % 10 + 2
	/* �� ���� ���� ���� �̾Ƴ��ϴ�. ������ ���� 10���� ũ�ų� ���ٸ�
	���Ŀ� ���� 2�� �ٽ� �����ϰ� �˴ϴ�. */
	total = total + (temp[i] * k)
	// �� �ڸ����� ������ ���Ѱ��� ���� total�� �����ջ��ŵ�ϴ�.
	}

	last_num = (11- (total % 11)) % 10
	// ������ ������ ���� last_num�� �����մϴ�.
	if(last_num == temp[13]) blnResident_number = true
	// laster_num�� �ֹι�ȣ�Ǹ��������� ������ ���� Ʋ���� ������ ��ȯ�մϴ�.
	else blnResident_number = false
	return blnResident_number
}
//make by ����� ����ڵ�Ϲ�ȣ �˻� 
function CheckBusiness_Number(businessNo){
	if (businessNo.length == 10) {
	
		a  	= businessNo.charAt(0);
		b  	= businessNo.charAt(1);
		c  	= businessNo.charAt(2);
		d  	= businessNo.charAt(3);
		e  	= businessNo.charAt(4);
		f  	= businessNo.charAt(5);
		g  	= businessNo.charAt(6);
		h  	= businessNo.charAt(7);
		i  	= businessNo.charAt(8);
		Osub 	= businessNo.charAt(9);
		
		suma = a*1 + b*3 + c*7 + d*1 + e*3 + f*7 + g*1 + h*3;
		sumb = (i*5) %10;
		sumc = parseInt((i*5) / 10,10);
		sumd = sumb + sumc;	
		sume = suma + sumd;
		sumf = a + b + c + d + e + f + g + h + i
		k = sume % 10;
		Modvalue = 10 - k;
		LastVal = Modvalue % 10;
		
		if (sumf == 0) {
			return false;
		}
		
	} else {
		return false;
	}	

  	if ( Osub == LastVal ) {
		return true;
	} else {
		return false;
	}
}

// �޷°˻�
function getCalender(iter,form_name,text_name,text_col_code,text_format, year, month, day)
{
	window.open('/common/calendar/calendar.jsp?iter='+iter+'&form_name='+form_name+'&text_name='+text_name+'&text_col_code='+text_col_code+'&text_format='+text_format+'&this_year='+year+'&this_month='+month+'&this_day='+day, "calendar", "width=210,height=200,status=no,resizable=no,top=250,left=400");
    //vWinCal.opener = self;	    
}

function open_tx_error(tx_id, work_group) {
    var url = "/common/interface/deploy_tx_popup.jsp?tx_id=" + tx_id + "&work_group=" + work_group;
    var status = "toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,width=500,height=400,left=20,top=20";
    window.open(url, "pop_tx_error", status);
}
//window.document.oncontextmenu = new Function("return false");


function GetCookie (name) {

   var arg = name + "=";
   var alen = arg.length;
   var clen = document.cookie.length;
   var i = 0;
  
   while (i < clen) { //while open
      var j = i + alen;
      if (document.cookie.substring(i, j) == arg)
         return getCookieVal (j);
      i = document.cookie.indexOf(" ", i) + 1;
      if (i == 0) break; 
   } //while close

   return null;
}

function getCookieVal (offset) {
   var endstr = document.cookie.indexOf (";", offset);
   if (endstr == -1) endstr = document.cookie.length;
   return unescape(document.cookie.substring(offset, endstr));
}

function SetCookie(name, value) {
   var argv = SetCookie.arguments;
   var argc = SetCookie.arguments.length;
   var expires = (2 < argc) ? argv[2] : null;
   var path = (3 < argc) ? argv[3] : null;
   var domain = (4 < argc) ? argv[4] : null;
   var secure = (5 < argc) ? argv[5] : false;
   document.cookie = name + "=" + escape (value) +
      ((expires == null) ? "" : 
         ("; expires=" + expires.toGMTString())) +
      ((path == null) ? "" : ("; path=" + path)) +
      ((domain == null) ? "" : ("; domain=" + domain)) +
      ((secure == true) ? "; secure" : "");
} 

function go_diagram(src) {
	if (opener == null ) {
		top.ifrm.location=src;
	} else {
		opener.ifrm.location=src;
		opener.focus();
	}
	/* ==> ORG...SRC.. MODIFIED BY KJI...
	if (opener == null ) {
		top.mainFrame.workFrame.location.href=src;
	} else {
		opener.top.mainFrame.workFrame.location.href=src;
		opener.focus();
	}
	*/
	

	
}

function go_detail(src) {

	var style = "width=640,height=380,status=no,resizable=yes,top=100,left=100";



	if (src.indexOf('regulation')==3) {
		style = "width=740,height=550,status=no,resizable=yes,top=100,left=100";
	}
	

	
	var aURL = src;
	window.open(aURL, "popup_meta", style);
}

function pause(numberMillis) {
     var now = new Date();
     var exitTime = now.getTime() + numberMillis;


     while (true) {
          now = new Date();
          if (now.getTime() > exitTime)
              return;
     }
}

	

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;  
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}


function MM_showHideLayers() { //v3.0
  var i,p,v,obj,args=MM_showHideLayers.arguments;
  for (i=0; i<(args.length-2); i+=3) if ((obj=MM_findObj(args[i]))!=null) { v=args[i+2];
    if (obj.style) { obj=obj.style; v=(v=='show')?'visible':(v='hide')?'hidden':v; }
    obj.visibility=v; }
}


function fMouseOut(){
	delayhide = setTimeout("MM_showHideLayers('Layer0','','show','Layer1','','hide','Layer2','','hide','Layer3','','hide','Layer4','','hide','Layer5','','hide')",500);    	
}

function clear_delayhide(){
if (window.delayhide)
clearTimeout(delayhide)
}

function do_onload(){
//MM_preloadImages('/images/menu/menu01_on.gif','/images/menu/menu02_on.gif','/images/menu/menu03_on.gif','/images/menu/menu04_on.gif','/images/menu/menu05_on.gif','/images/menu/menu06_on.gif')
}

if (window.addEventListener)
	window.addEventListener("load", do_onload, false)
else if (window.attachEvent)
	window.attachEvent("onload", do_onload)
else if (document.getElementById)
	window.onload=do_onload



function help_pop(arg1)
{
		
	var href = "/help/help.htm";
	
	windowname = 'helpwin';
	
	newwin = window.open(href, windowname, 
		'width=750,height=500,left=50,top=100,resizable=no,scrollbars=no');
	newwin.focus();
} 


function showLoading(showing){
	var loadingBar = document.getElementById("LOADING_BARS");
	if(showing){
		loadingBar.style.top = 200;//(document.body.clientHeight-80)/2;
		loadingBar.style.left = (document.body.clientWidth-300)/2;
		loadingBar.style.display = "";
	}else{
		loadingBar.style.display = "none";
	}
}
	
/*
	function  nmcs_right_click() { 
		if ((event.button==2) || (event.button==3)) { 
			alert('�� ������������ ���콺 ������ ���߸� ����� �� �����ϴ�.'); 
		} 
	} 
	
function noselectstart(event){
	
		alert(event);
		debugger
	    alert(event.srcElement.type);

		return false;
	}

	function Source_Copy() { 
		event.returnValue = false;
  		window.clipboardData.setData("Text", "�����Ҽ� �����ϴ�.");
	}
	
	document.onmousedown=nmcs_right_click
	
	document.onselectstart=noselectstart
	

	function no_pr(){
		alert('�μ��Ҽ� �����ϴ�. �μ�� ������ ��µ˴ϴ�. �μ⺻�� �����ڿ��� ��û�Ͻʽÿ�.');
		return false;
	}
		
	window.document.onbeforeprint=no_pr;
	window.onbeforeprint=no_pr;
	
	*/
	

