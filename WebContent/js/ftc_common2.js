/**
 * ���������� �ʱ�ȭ �Լ�����
 */
var gFunctionName;
//disableRightButton();
//enableEnterKey();
//enableBackspaceKey();

function openZipCodeWindow(fName)
{
	gFunctionName = fName;
	openWindow("/commZipCodeR.do?keyword=", "commZipCodeR", 518, 500, "yes", "no");
}

function openJobCodeWindow(fName)
{
	gFunctionName = fName;
	openWindow("/commJobCodeR.do?code=", "commJobCodeR", 518, 500, "yes", "no");
}

function openBizCodeWindow(fName)
{
	gFunctionName = fName;
	openWindow("/commBizCodeR.do?code=", "commBizCodeR", 518, 500, "yes", "no");
}


function openCalDateWindow(fName)
{
	gFunctionName = fName;
	openWindow("/inc/commCalDate.jsp", "commCalDate", 128, 200, "no", "no");
}

/**
 * �����޼��� ǥ��
 */
function getAlertMsg(code)
{
    var msg = gAlertMessage[code];
    if(typeof(msg) == "undefined")
        return "�����ڵ�/�޼������� ���ǵ��� �ʾҽ��ϴ�.";
    else
        return msg;
}

/**
 * �̺�Ʈ �ʱ�ȭ
 */
function disableRightButton()
{

	if(window.Event)
		document.captureEvents(Event.MOUSEUP);

	function nocontextmenu()
	{
		window.event.cancelBubble = true
		window.event.returnValue = false;
		return false;
	}

	function norightclick(e)
	{
		if(window.Event)
		{
			if(e.which == 2 || e.which == 3)
			return false;
		}
		else if(window.event.button == 2 || window.event.button == 3)
		{
			window.event.cancelBubble = true
			window.event.returnValue = false;
			return false;
		}
	}

	document.oncontextmenu = nocontextmenu;
	document.onmousedown = norightclick;
}

/**
 * �Է� ��ü�� ���� ��� �Ӽ��� �Լ��� �����ش�.
 *
 * @param obj	��ü ID
 * @return
 */
function showProps(obj)
{
	var element = this.getObject(obj);
	var msg = "��ü�� �Ӽ�\n\n";
	var i = 0;

	if(obj)
	{
		for(prop in obj)
		{
			i++;
			msg += i + ". " + prop+'=' + obj[prop] + "\n";
			if(i % 10 ==0)
			{
				alert (msg);
				msg = "";
			}
		}

		if (msg)
			alert (msg);
	}
}

/**
 * �Է� ��ü�� ������ �������� �����ϴ� HTML��ü������ �˻��Ѵ�.
 *
 * @param obj	��ü ID
 * @return
 */
function isObject(obj)
{
	if(typeof(obj) != "object")
	{
		window.alert("��ü�� �������� �ʽ��ϴ�. ��ü�� ö�ڸ� Ȯ���ϼ���.");
		return false;
	}

	return true;
}

/**
 * �Է� ��ü�� Array �������� �˻��Ѵ�.
 *
 * @param obj	��ü ID
 * @return
 */
function isArray(obj)
{
	if((typeof(obj) != "object") || (typeof(obj[0]) != "object"))
	{
		//window.alert("element(checkbox)�� Array�� �ƴմϴ�.");
		return false;
	}

	return true;
}

/**
 * �Է� ���ڿ��� �յ� ����(white space)�� �����Ѵ�.
 *
 * @param str	���ڿ�
 * @return
 */
function trim(str)
{
	var n = str.length;

	var i;
	for(i=0; i<n; i++)
	{
		if(str.charAt(i) != " ")
		{
			break;
		}
	}

	var j;
	for(j=n-1; j>=0; j--)
	{
		if(str.charAt(j) != " ")
		{
			break;
		}
	}

	if(i>j)
	{
		return "";
	}
	else
	{
		return str.substring(i,j+1);
	}
}

/**
 * �Է� ���ڿ��� �տ��� ����(white space)�� �����Ѵ�.
 *
 * @param str	���ڿ�
 * @return
 */
function trimByFront(str)
{
	var n = str.length;

	var i;
	for(i=0; i<n; i++)
	{
		if(str.charAt(i) != " ")
		{
			break;
		}
	}

    return str.substring(i);
}

/**
 * �Է� ���ڿ��� �ڿ��� ����(white space)�� �����Ѵ�.
 *
 * @param str	���ڿ�
 * @return
 */
function trimByBack(str)
{
	var n = str.length;

	var j;
	for(j=n-1; j>=0; j--)
	{
		if(str.charAt(j) != " ")
		{
			break;
		}
	}

	return str.substring(0,j+1);
}

/**
 * �Է� ��ü�� ���� ��(null)������ �˻��Ѵ�.
 *
 * @param obj	��ü ID
 * @param str	��ü �̸�
 * @return
 */
function isNull(obj, msg)
{
	
	if(typeof(obj) != "object"){
		return false;
	}
	var str = trim(obj.value);

	if(strlen(str) == 0)
	{
		if(msg)
		{
			window.alert(msg + "��(��) �Է��ϼ���.");
			obj.value = str;
			obj.focus();
		}

		return true;
	}

	if(msg)
	{
		obj.value = str;
	}

	return false;
}

/**
 * �Է� ��ü�� ����Ʈ������ ���̸� ���Ѵ�.
 *
 * @param obj	��ü ID
 * @return
 */
function strlen(str)
{
	var j = 0;
	for(var i=0; i<str.length; i++)
	{
		if(escape(str.charAt(i)).length == 6)
			j++;
		j++;
	}

	return(j);
}

function getObject(obj)
{
	if(!isObject(obj))
		return false;

	if(typeof(window.document.all[obj]) == "object")
		return window.document.all[obj];
	else
		return null;
}

/**
 * ������ �ڸ����� ������ �߻���Ų��.
 *
 * @param size	���� ũ��
 * @return
 */
function getRandom(size)
{
	var random;
	while((random = Math.random() * Math.pow(10, size) - 1) < Math.pow(10, size - 1))
		;
	return(parseInt(random));
}

/**
 * �Է� ���ڿ��� ����(white space)�� �����Ѵ�.
 *
 * @param str	���ڿ�
 * @return
 */
function removeString(str, delStr)
{
	var oldVal = str;
	var newVal = "";

	var n = oldVal.length;

	for(var i=0; i<n; i++)
	{
		if(oldVal.charAt(i) == delStr)
		{
			continue;
		}
		else
		{
			newVal += oldVal.charAt(i);
		}
	}

	return newVal;
}

/**
 * �Ҽ����� ������ ���ڸ� �Ҽ����� ������ ������ ��ȯ�Ѵ�.
 *
 * @param str	���ڿ�
 * @return
 */
function toInteger(str)
{
	if(str.indexOf(".") == -1)
		return str;
	else
		return str.substring(0,  str.indexOf("."));
}

/**
 * �Ҽ����� �������� ���� ���ڸ� �Ҽ����� ������ �Ǽ��� ��ȯ�Ѵ�.
 *
 * @param str	���ڿ�
 * @return
 */
function toReal(str)
{
	var strIdx = str.indexOf(".")

	if(strIdx == -1)
		return str + ".0";
	else if(strIdx == str.length-1)
		return str + "0";
	else
		return str;
}

/**
 * �Է� ���ڿ��� �տ������� ������ ũ�⸸ŭ ���й��ڷ� ���´�. (�������)
 *
 * @param strMain	���ڿ�
 * @param strIns	���� ����
 * @param size		���� ����
 * @return
 */
function insertCharacterByFront(strMain, strIns, size)
{
	var returnStr = "";
	var tempStrMain = strMain + "";
	var tempStrIns = strIns + "";
	var strMainLength = tempStrMain.length;

	if (strMainLength < size)
	{
		return tempStrMain;
	}
	else
	{
		var strLengthDiv = parseInt(strMainLength / size);
		var strLengthRemnant = parseInt(strMainLength % size);

		for (i = 0; i < strLengthDiv; i++)
		{
			if (i == 0)
			{
				returnStr = tempStrMain.substring(0, size);
			}
			else
			{
				returnStr = returnStr + tempStrIns + tempStrMain.substring(size * i, (size * i) + size);
			}
		}

		if (strLengthRemnant > 0)
		{
			returnStr = returnStr + strIns + tempStrMain.substring(0, strLengthRemnant);
		}

		return returnStr;
	}
}

function addCharacterByFront(strMain, strIns, size)
{
	return insertCharacterByFront(strMain, strIns, size);
}

/**
 * �Է� ���ڿ��� �ڿ������� ������ ũ�⸸ŭ ���й��ڷ� ���´�. (�������)
 *
 * @param strMain	���ڿ�
 * @param strIns	���� ����
 * @param size		���� ����
 * @return
 */
function insertCharacterByBack(strMain, strIns, size)
{
	var returnStr = "";
	var tempStrMain = strMain + "";
	var tempStrIns = strIns + "";
	var strMainLength = tempStrMain.length;

	if (strMainLength < size)
	{
		return tempStrMain;
	}
	else
	{
		var strLengthDiv = parseInt(strMainLength / size);
		var strLengthRemnant = parseInt(strMainLength % size);

		if (strLengthRemnant > 0)
		{
			returnStr = tempStrMain.substring(0, strLengthRemnant) + strIns;
		}

		for (i = 0; i < strLengthDiv; i++)
		{
			if (i == 0)
			{
				returnStr = returnStr + tempStrMain.substring(strLengthRemnant, strLengthRemnant + size);
			}
			else
			{
				returnStr = returnStr + tempStrIns + tempStrMain.substring(strLengthRemnant + (size * i), strLengthRemnant + (size * i) + size);
			}
		}

		return returnStr;
	}
}


/**
 * ���ڿ��� ����(white space)�� �����Ѵ�.
 *
 * @param str	���ڿ�
 * @return
 */
function removeSpace(str)
{
	return removeString(" ");
}

/**
 * �ڹٽ�ũ��Ʈ Date ��ü�� Time ���ڿ��� ��ȯ�Ѵ�.
 *
 * @param date	Date ��ü
 * @return
 */
function toTimeString(date)
{
  var year  = date.getFullYear();
  var month = date.getMonth() + 1; // 1��=0, 12��=11
  var day   = date.getDate();
  var hour  = date.getHours();
  var min   = date.getMinutes();
  var sec   = date.getSeconds();

  if(("" + month).length == 1) {month = "0" + month;}
  if(("" + day).length == 1) {day = "0" + day;}
  if(("" + hour).length == 1) {hour = "0" + hour;}
  if(("" + min).length == 1) {min = "0" + min;}
  if(("" + sec).length == 1) {sec = "0" + sec;}

  return("" + year + month + day + hour + min + sec)
}

/**
 * Time ��Ʈ���� �ڹٽ�ũ��Ʈ Date ��ü�� ��ȯ�Ѵ�.
 *
 * @param time	Time ���ڿ�
 * @return
 */
function toTimeObject(time)
{
  var year  = time.substr(0,4);
  var month = time.substr(4,2) - 1; // 1��=0, 12��=11
  var day   = time.substr(6,2);
  var hour  = time.substr(8,2);
  var min   = time.substr(10,2);

  return new Date(year,month,day,hour,min);
}

/**
 * ���� �ð��� Time �������� �����Ѵ�.
 *
 * @return
 */
function getCurrentTime()
{
  return toTimeString(new Date());
}

/**
 * ���� Ҵ�� YYYY�������� ����
 *
 * @return
 */
function getYear()
{
  return getCurrentTime().substr(0,4);
}

/**
 * ���� ���� MM�������� ����
 *
 * @return
 */
function getMonth()
{
  return getCurrentTime().substr(4,2);
}

/**
 * ���� ���� DD�������� ����
 *
 * @return
 */
function getDay()
{
  return getCurrentTime().substr(6, 2);
}

/**
 * ���ó�¥�� �ش��ϴ� ������ ���Ѵ�.
 *
 * @return
 */
function getDayOfWeek()
{
  var now = new Date();

  var day = now.getDay();
  var week = new Array("��", "��", "ȭ", "��", "��", "��", "��");

  return week[day];
}

/**
 * �־��� Time �� y�� m�� d�� h�� ���̳��� Time�� ���Ѵ�.
 *
 * @param time	Time ��ü
 * @param y		y�� ����
 * @param m		m�� ����
 * @param d		d�� ����
 * @param h		h�� ����
 * @return
 */
function shiftTime(time, y, m, d, h) {
  var date = toTimeObject(time);

  date.setFullYear(date.getFullYear() + y); // y���� ����
  date.setMonth(date.getMonth() + m); // m���� ����
  date.setDate(date.getDate() + d); // d���� ����
  date.setHours(date.getHours() + h); // h�ø� ����

  return toTimeString(date);
}

/**
 * ����κ��� ������ ��ŭ�� �����ð��� ���Ѵ�.
 *
 * @param   nHour   �ð�
 * @return
 */
function getHourBefore(nHour)
{
	return shiftTime(getCurrentTime(), 0, 0, 0, -nHour);
}

/**
 * ����κ��� ������ ��ŭ�� �������� ���Ѵ�.
 *
 * @param   nDay    ��
 * @return
 */
function getDayBefore(nDay)
{
	return shiftTime(getCurrentTime(), 0, 0, -nDay, 0);
}

/**
 * ����κ��� ������ ��ŭ�� �������� ���Ѵ�.
 *
 * @param nMonth    ��
 * @return
 */
function getMonthBefore(nMonth)
{
	return shiftTime(getCurrentTime(), 0, -nMonth, 0, 0);
}

/**
 * ����κ��� ������ ��ŭ�� ���������� ���Ѵ�.
 *
 * @param nYear     ��
 * @return
 */
function getYearBefore(nYear)
{
	return shiftTime(getCurrentTime(), -nYear, 0, 0, 0);
}

/**
 * ����κ��� ������ ��ŭ�� ���Ľð��� ���Ѵ�.
 *
 * @param nHour     �ð�
 * @return
 */
function getHourAfter(nDay)
{
	return shiftTime(getCurrentTime(), 0, 0, 0, nHour);
}

/**
 * ����κ��� ������ ��ŭ�� �����ϸ� ���Ѵ�.
 *
 * @param nDay      ��
 * @return
 */
function getDayAfter(d)
{
	return shiftTime(getCurrentTime(), 0, 0, nDay, 0);
}

/**
 * ����κ��� ������ ��ŭ�� ���Ŀ��� ���Ѵ�.
 *
 * @param nMonth    ��
 * @return
 */
function getMonthBefore(nMonth)
{
	return shiftTime(getCurrentTime(), 0, nMonth, 0, 0);
}

/**
 * ����κ��� ������ ��ŭ�� ���Ŀ����� ���Ѵ�.
 *
 * @param nYear     ��
 * @return
 */
function getYearBefore(nYear)
{
	return shiftTime(getCurrentTime(), nYear, 0, 0, 0);
}

/**
 * �� �ð��� �� ���� �������� ���Ѵ�.
 *
 * @param time1
 * @param time2
 * @return
 */
function getMonthInterval(time1,time2)
{
  var date1 = toTimeObject(time1);
  var date2 = toTimeObject(time2);

  var years  = date2.getFullYear() - date1.getFullYear();
  var months = date2.getMonth() - date1.getMonth();
  var days   = date2.getDate() - date1.getDate();

  return years * 12 + months + ((days >= 0)?0:-1);
}

/**
 * �� �ð��� ���� �������� ���Ѵ�.
 *
 * @param time1
 * @param time2
 * @return
 */
function getDayInterval(time1, time2)
{
  var date1 = toTimeObject(time1);
  var date2 = toTimeObject(time2);
  var day   = 1000 * 3600 * 24;

  return parseInt((date2 - date1) / day, 10);
}

/**
 * �� �ð��� ��ð� �������� ���Ѵ�.
 *
 * @param time1
 * @param time2
 * @return
 */
function getHourInterval(time1, time2)
{
  var date1 = toTimeObject(time1);
  var date2 = toTimeObject(time2);
  var hour  = 1000 * 3600;

  return parseInt((date2 - date1) / hour, 10);
}

/**
 * SELECT ��ü��  �ʱ����(index == 0)���� ����Ǿ����� �˻��Ѵ�.
 *
 * @param obj	��ü ID
 * @param msg	��ü �̸�
 * @return
 */
function isSelected(obj, msg)
{
	if(!isObject(obj))
		return false;

	if(obj.selectedIndex == 0)
	{
		if(msg)
		{
			window.alert("[" + msg + "]��(��) ���õ��� �ʾҽ��ϴ�.");
			obj.focus();
		}

		return false;
	}

	return true;
}

function compareOptionValues(a, b)
{
	// Radix 10: for numeric values
	// Radix 36: for alphanumeric values
	var sA = parseInt(a.value, 36);
	var sB = parseInt(b.value, 36);

	return sA - sB;
}

function compareOptionText(a, b)
{
	// Radix 10: for numeric values
	// Radix 36: for alphanumeric values
	var sA = parseInt(a.text, 36);
	var sB = parseInt(b.text, 36);
	return sA - sB;
}

/**
 * SELECT ����Ʈ���� �ٸ� ����Ʈ�� �׸��� �̵��Ѵ�.
 *
 * @param srcList	���� ����Ʈ
 * @param tarList	��� ����Ʈ
 * @param moveAll	true : ��ü �׸�, false : ������ �׸�
 * @return
 */
function moveDualList(srcList, tarList, moveAll)
{
	if(srcList.length == 0)
	{
		window.alert("�̵��� �׸��� �����ϴ�.");
		return false;
	}

	if((srcList.selectedIndex == -1) && (moveAll == false))
	{
		window.alert("�̵��� �׸��� �����ϼ���.");
		return false;
	}

	newtarList = new Array(tarList.options.length);

	var len = 0;

	for(len = 0; len < tarList.options.length; len++)
	{
		if(tarList.options[ len ] != null)
		{
			newtarList[ len ] = new Option(tarList.options[ len ].text, tarList.options[ len ].value, tarList.options[ len ].defaultSelected, tarList.options[ len ].selected);
		}
	}

	for(var i = 0; i < srcList.options.length; i++)
	{
		if(srcList.options[i] != null && (srcList.options[i].selected == true || moveAll))
		{
			newtarList[ len ] = new Option(srcList.options[i].text, srcList.options[i].value, srcList.options[i].defaultSelected, srcList.options[i].selected);
			len++;
		}
	}

	newtarList.sort(compareOptionValues);   // BY VALUES
	//newtarList.sort(compareOptionText);   // BY TEXT

	for(var j = 0; j < newtarList.length; j++)
	{
		if(newtarList[ j ] != null)
		{
			tarList.options[ j ] = newtarList[ j ];
		}
	}

	for(var i = srcList.options.length - 1; i >= 0; i--)
	{
		if(srcList.options[i] != null && (srcList.options[i].selected == true || moveAll))
		{
		   srcList.options[i] = null;
		}
	}
}

/**
 * ���ڿ��� �ڿ������� 3�ڸ��� ,(comma)��  ���´�. (�������)
 *
 * @param str	���ڿ�
 * @return		,(comma)�� ���Ե� ���ڿ�
 */
function addComma(str)
{
	return insertComma(str);
}

/**
 * ���ڿ��� �ڿ������� 3�ڸ��� ,(comma)��  ���´�.
 *
 * @param str	���ڿ�
 * @return		,(comma)�� ���Ե� ���ڿ�
 */
function insertComma(str)
{
	str = removeComma(str);

	var strIdx = str.indexOf(".");
	if(strIdx == -1)
	{
		return insertCharacterByBack(str, ",", 3);
	}
	else
	{
		var str1 = str.substring(0, strIdx);
		var str2 = str.substring(strIdx);

		return insertCharacterByBack(str1, ",", 3) + str2;
	}
}

/**
 * ,(comma)�� �����Ѵ�.
 *
 * @param str	���ڿ�
 * @return
 */
function removeComma(str)
{
	return removeString(str, ",");
}

/**
 * ��â�� ������ �ɼ����� ����.
 *
 * @param url		â�� URL
 * @param url		â�� �̸�
 * @param width		â�� ����
 * @param height	â�� ����
 * @param scroll	��ũ�� ����(true/false)
 * @param resize	ũ������ ����(true/false)
 * @return			â�� ��ü
 */
function openWindow(url, name, W, H, scroll, resize)
{
    var X = 0;
    var Y = 0;
  
    if(parseInt(W) <= 400 && parseInt(H) <= 400)
    {
//        X = (window.screen.width/2) - (W/2+10);
//        Y = (window.screen.height/2) - (H/2+50);
        if(window.screen.width < W + window.event.screenX)
            X = window.event.screenX - W - 10;
        else
            X = window.event.screenX;

        if(window.screen.height < H + window.event.screenY)
            Y = window.event.screenY - H - 30;
        else
            Y = window.event.screenY;
    }

	var win = window.open(url, name, "status=no, height=" + H + ",width=" + W + ", left=" + X + ", top=" + Y + ", screenX=" + X + ", screenY=" + Y + ", scrollbars=" + scroll + ", resizable=" + resize);
	win.focus();
	return win;
}

/**
 * ss.inc�� ���Ե� ����
 */
function init(){
	on_div_nb = new Array(0,0,0,0)
}

function hide_div(menu_nb, step){
	document.all.item("tab"+menu_nb).style.display = "none"
	document.all.item("div"+menu_nb).style.display = "none"
	on_div_nb[step] = 0

	for(i=step+1; i<=3; i++){
		if(on_div_nb[i] != 0)
		hide_div(on_div_nb[i],i)
	}
}

function show_div(menu_nb, step){
	if(on_div_nb[step] != 0)
		hide_div(on_div_nb[step],step)
	document.all.item("tab"+menu_nb).style.display = ""
	document.all.item("div"+menu_nb).style.display = ""
	on_div_nb[step] = menu_nb
}

function set_div(menu_nb, step){
	var cur_div
		cur_div = document.all.item("div"+menu_nb).style.display
	if(cur_div == "")
		hide_div(menu_nb, step)
	else
		show_div(menu_nb, step)
}

function show_dot(dot_nb){
	document.all.item("off_dot"+dot_nb).style.display = "none"
	document.all.item("on_dot"+dot_nb).style.display = ""
}

function hide_dot(dot_nb){
	document.all.item("on_dot"+dot_nb).style.display = "none"
	document.all.item("off_dot"+dot_nb).style.display = ""
}

function go_menu(nb,value,Num){
	if(nb == 1){
		document.frm_top.action = "top01.htm"
		document.frm_details.action = value
		parent.document.ChNum = Num
	}
	document.frm_top.submit()
	document.frm_details.submit()
}



if (document.getElementById){
document.write('<style type="text/css">\n')
document.write('.submenu{display: none;}\n')
document.write('</style>\n')
}

function SwitchMenu(obj){
 if(document.getElementById){
  var el = document.getElementById(obj);
  if(el==null){
  	return;
  }
  var ar = document.getElementById("maindiv").getElementsByTagName("span");
  
  if(el.style.display != "block"){
    for (var i=0; i<ar.length; i++){
     if (ar[i].className=="submenu")
     	ar[i].style.display = "none";
     if (ar[i].className=="relatedmenu")
     	ar[i].style.display = "none";
    }
    el.style.display = "block";
  }else{
    el.style.display = "none";
  }
 }
}

function SwitchRelatedMenu(obj){
	if(document.getElementById){
		var el = document.getElementById(obj);
		if(el==null){
			return;
		}
		var ar = document.getElementById("maindiv").getElementsByTagName("span");
		
		if(el.style.display!="block"){
			for(var i=0; i<ar.length; i++){
				if(ar[i].className=="relatedmenu"){
					ar[i].style.display="none";
				}
			}
			el.style.display = "block";
		}
	}
}

function isBizDate()
{
  var _iframe = ID_IFRAME;
  _iframe.document.forms[0].bizDate.value = arguments[0].value;
  _iframe.go();
 return false;
}


function changeSearch(obj){
	var fobj = eval("document.formx."+obj);
	fobj.value='';
}


function changeSearch2(dist){
	var f = document.formx;
	
	if(dist=='tex'){
		//f.brnoCombo.options[f.brnoCombo.selectedIndex].value = f.slsBrno.value;
		f.brnoCombo.value = f.slsBrno.value;
		if(f.brnoCombo.value==''){
			f.brnoCombo.value = ''; 
		}
	}else if(dist=='com'){		
		f.slsBrno.value=f.brnoCombo.options[f.brnoCombo.selectedIndex].value;
	}
}


function goQuery(){
	 goMenu('il80');
}


function enableEnterKey()
{
	function onkeypress(e)
	{
        if(window.event.keyCode == 13 && window.event.srcElement.type != "textarea" && window.event.srcElement.type != "button") // 13 : Enter
        {
            //__submit();
        	//alert(window.event.srcElement.name);
        	if(window.event.srcElement.name == "slsBrno"){
        		searchBrno();
        	}else if(window.event.srcElement.name == "csno"){
        		searchCust();
        	}
			window.event.returnValue = false; 
        }
	}
    document.onkeypress = onkeypress;    
}


function enableBackspaceKey()
{
	function onkeydown(e)
	{
        if(window.event.keyCode == 8 && !window.event.srcElement.isTextEdit) // 13 : Enter
        {
			window.event.returnValue = false; 
        }else if(window.event.keyCode == 116){

			window.event.returnValue = false; 
        }
	}
    document.onkeydown = onkeydown;    
}


function searchCust(isSearch){
		var f = document.formx;
		if(f.csno.value!='' && typeof(f.custNm) == "object" && f.custNm.value==''){
			
			if(typeof(f.mode) == "object" && f.mode.value=='popup'){
				f.target = document.getElementById("ifrmPopCust").name;
				f.action = "/code.do?method=checkCustCount&mode=popup&isSearch="+isSearch;
			}else{
				f.target = parent.document.getElementById("ifrmCust").name;
				f.action = "/code.do?method=checkCustCount&isSearch="+isSearch;
			}

			
			f.submit();
			f.target = "";
		}
}


function searchBrno(){
	var f = document.formx;
	
	if(f.slsBrno.value!='' && typeof(f.brnoCombo) == "object"){
		f.brnoCombo.value = f.slsBrno.value;
		if(f.brnoCombo.value==''){
			f.brnoCombo.value = ''; 
		}
		f.slsBrno.select();
	}	
}

function searchTarget(){
	var f = document.formx;
	if(f.csno.value!='' && f.custNm.value==''){
		searchCust('Y');
	}else{
		searchTarget2();
	}
}	

