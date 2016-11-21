/**
 * 입력값의 유효성을 검사한다.
 * 
 * @param obj	객체 ID
 * @param type	타입(K : 한글, A : 영문, AN : 영숫자 혼합)
 * @param min	최소 자리수 
 * @param max	최대 자리수 
 * @param msg	객체 이름
 * @return		객체 이름
 */
function isValid(obj, type, min, max, msg)
{
	if(type == "K")
	{
		if(!isKorean(obj, msg))
		{
			return false;
		}

	}
	else if(type == "A")
	{
		if(!isAlphabet(obj, msg))
		{
			return false;
		}

	}
	else if(type == "N")
	{
		if(!isNumber(obj, msg))
		{
			return false;
		}
	}
	else if(type == "AN")
	{
		if(!isAlphaNumeric(obj, msg))
		{
			return false;
		}

	}


	if(!isValidSize(obj, min, max, msg))
	{
		return false;
	}


	return true;
}

/**
 * 입력값의 크기를 검사한다.
 * 
 * @param obj	객체 ID
 * @param min	최소 자리수 
 * @param max	최대 자리수 
 * @param msg	메세지
 * @return
 */
function isValidSize(obj, min, max, msg)
{
	if(!isObject(obj))
		return false;

	if(isNull(obj, msg))
		return false;

	var str = trim(obj.value);

	if(!(strlen(str) >= min && strlen(str) <= max))
	{
		if(msg)
		{
			if(min == max)
				window.alert(msg + "은(는) " + min +"자로 입력하세요.");
			else
				window.alert(msg + "은(는) 최소 " + min +"자, 최대 " + max + "자로 입력하세요.");

			obj.value = str;
			obj.focus();
		}

		return false;
	}

	if(msg)
	{
		obj.value = str;
	}

	return true;
}

/**
 * 객체의 값이 숫자인지 검사한다.
 * 
 * @param obj	객체 ID
 * @param msg	객체 이름
 * @return 
 */
function isNumber(obj, msg)
{
	if(!isObject(obj))
		return false;

	if(isNull(obj, msg))
		return false;

	var str = trim(obj.value);

	for(var i=0; i < str.length ; i++)
	{
		var code = str.charCodeAt(i);

		if(!(code >= 48 && code <= 57) && code != 44)
		{
			if(msg)
			{
				window.alert("[" + str.charAt(i) + "]은(는) 숫자가 아닙니다. " + msg + "은(는) 숫자로 입력해 주세요.");
				obj.value = str;
				obj.focus();
			}

			return false;
		}
	}

	if(msg)
	{
		obj.value = str;
	}

	return true;
}

/**
 * 객체의 값이 한글인지 검사한다.
 * 
 * @param obj	객체 ID
 * @param msg	객체 이름
 * @return 
 */
function isKorean(obj, msg)
{
	if(!isObject(obj))
		return false;

	if(isNull(obj, msg))
		return false;

	var str = trim(obj.value);

	for(var i=0; i < str.length ; i++)
	{
		var code = str.charCodeAt(i);

		if(!(code > 255))
		{
			if(msg)
			{
				window.alert("[" + str.charAt(i) + "]은(는) 한글이 아닙니다. " + msg + "은(는) 한글로 입력해 주세요.");
				obj.value = str;
				obj.focus();
			}

			return false;
		}
	}

	if(msg)
	{
		obj.value = str;
	}

	return true;
}

/**
 * 객체의 값이 영문인지 검사한다.
 * 
 * @param obj	객체 ID
 * @param msg	객체 이름
 * @return 
 */
function isAlphabet(obj, msg)
{
	if(!isObject(obj))
		return false;

	if(isNull(obj, msg))
		return false;

	var str = trim(obj.value);

	for(var i=0; i < str.length ; i++)
	{
		var code = str.charCodeAt(i);

        if(!(code >= 65 && code <= 122))
		{
			if(msg)
			{
				window.alert("[" + str.charAt(i) + "]은(는) 영문이 아닙니다. " + msg + "은(는) 영문으로 입력해 주세요.");
				obj.value = str;
				obj.focus();
			}

			return false;
		}
	}

	if(msg)
	{
		obj.value = str;
	}

	return true;
}

/**
 * 객체의 값이 영문 또는 숫자인지 검사한다.
 * 
 * @param obj	객체 ID
 * @param msg	객체 이름
 * @return 
 */
function isAlphaNumeric(obj, msg)
{
	if(!isObject(obj))
		return false;

	if(isNull(obj, msg))
		return false;

	var str = trim(obj.value);

	for(var i=0; i < str.length ; i++)
	{
		var code = str.charCodeAt(i);

		if(!(code >= 65 && code <= 122) && !(code >= 48 && code <= 57))
		{
			if(msg)
			{
				window.alert("[" + str.charAt(i) + "]은(는) 영문 또는 숫자가 아닙니다. " + msg + "은(는) 영문 또는 숫자로 입력해 주세요.");
				obj.value = str;
				obj.focus();
			}

			return false;
		}
	}

	if(msg)
	{
		obj.value = str;
	}

	return true;
}


/**
 * 두 객체의 값이 동일한지 검사한다.
 * 
 * @param obj1	객체1 ID
 * @param obj2	객체2 ID
 * @param msg	객체 이름
 * @return 
 */
function isSame(obj1, obj2, msg)
{
	if(!isObject(obj1))
		return false;
	if(!isObject(obj2))
		return false;

	if(isNull(obj1, msg))
		return false;
	if(isNull(obj2, msg))
		return false;

	var str1 = trim(obj1.value);
	var str2 = trim(obj2.value);

	if(str1 != str2)
	{
		if(msg)
		{
			window.alert(msg + "이(가) 일치하지 않습니다. 확인 후 다시 입력해 주세요.");
			obj1.value = str1;
			obj2.value = str2;
			obj1.focus();
		}

		return false;
	}

	if(msg)
	{
		obj1.value = str1;
		obj2.value = str2;
	}

	return true;
}

/**
 * 객체의 값이 아이디에 적합한지 검사한다.
 * - 아이디형식 : 영문, 숫자, _ 를 사용하여 5자이상 10자이하
 * 
 * @param obj	객체 ID
 * @param msg	객체 이름
 * @return 
 */
function isId(obj, msg)
{
	if(!isObject(obj))
		return false;

	if(isNull(obj, msg))
		return false;

	var str = trim(obj.value);

	if(!isValidSize(obj, 5, 10, msg))
	{
		return false;
	}

	if(!isAlphaNumeric(obj, msg))
	{
		return false;
	}

	if(msg)
	{
		obj.value = str;
	}

	return true;
}
/**
 * 객체의 값이 비밀번호에 적합한지 체크한다.
 * - 아이디형식 : 영문, 숫자, _ 를 사용
 * 
 * @param obj	객체
 * @return 
 */
function checkPassword(obj) 
{
	if(!isObject(obj))
		return false;
		
	if(!IsInNumeric(obj.value))
	{
		window.alert("비밀번호에 숫자가 포함되어 있지 않습니다. 확인 후 다시 입력해 주세요.");
		obj.focus();
		return false;
	}
	
	if(!IsInAlpabet(obj.value))
	{
		window.alert("비밀번호에 영문이 포함되어 있지 않습니다. 확인 후 다시 입력해 주세요.");
		obj.focus();
		return false;	
	}
	return true;
	
}
/**
 * 주어진 문자열에 영문이 포함되어 있는지 체크한다.
 * 
 * @param str 	문자열
 * @return 		영문포함여부
 */
function IsInAlpabet(str)
{
	//var str = trim(obj.value);

	for(var i=0; i < str.length ; i++)
	{
		var code = str.charCodeAt(i);

        	if((code >= 65 && code <= 122))
		{
			return true;
		}
	}
	return false;
}
/**
 * 주어진 문자열에 숫자가 포함되어 있는지 체크한다.
 * 
 * @param str 	문자열
 * @return 		숫자포함여부
 */
function IsInNumeric(sText)
{
	var ValidChars = "0123456789.";
	var Char;

 
	for (i = 0; i < sText.length; i++) 
	{ 
		Char = sText.charAt(i); 
		if (ValidChars.indexOf(Char) != -1) 
		{
			return true;
			
		}
	}
   	return false;
   
}



/**
 * 객체의 값이 E-Mail에 적합한지 검사한다.
 * 
 * @param obj	객체 ID
 * @param msg	객체 이름
 * @return 
 */
function isMail(obj, msg)
{
	if(!isObject(obj))
		return false;

	if(isNull(obj, msg))
		return false;

	var str = trim(obj.value);

	var i = str.indexOf("@");
	if(i < 0)
	{
		if(msg)
		{
			window.alert(msg + "이(가) 유효하지 않습니다. 확인 후 다시 입력하세요.");
			obj.value = str;
			obj.focus();
		}

		return false;
	}

	i = str.indexOf(".");
	if(i < 0)
	{
		if(msg)
		{
			window.alert(msg + "이(가) 유효하지 않습니다. 확인 후 다시 입력하세요.");
			obj.value = str;
			obj.focus();
		}

		return false;
	}

	if(msg)
	{
		obj.value = str;
	}

	return true;
}

/**
 * 객체의 값이 주민번호에 적합한지 검사한다.
 * 
 * @param obj1	객체1 ID
 * @param obj2	객체2 ID
 * @param msg	객체 이름
 * @return 
 */
function isJumin(obj1, obj2, msg)
{
	if(!isObject(obj1))
		return false;
	if(!isObject(obj2))
		return false;

	var str1 = obj1.value;
	var str2 = obj2.value;

	if(!isValidSize(obj1, 6, 6, msg + " 앞자리"))
	{
		return false;
	}

	if(!isValidSize(obj2, 7, 7, msg + " 뒷자리"))
	{
		return false;
	}

	var hap = 0;
	for(var i = 0; i < 6; i++)
	{
		var temp = str1.charAt(i) *(i+2);
		hap += temp;
	}

	var n1 = str2.charAt(0);
	var n2 = str2.charAt(1);
	var n3 = str2.charAt(2);
	var n4 = str2.charAt(3);
	var n5 = str2.charAt(4);
	var n6 = str2.charAt(5);
	var n7 = str2.charAt(6);

	hap += n1*8+n2*9+n3*2+n4*3+n5*4+n6*5;
	hap %= 11;
	hap = 11 - hap;
	hap %= 10;

	if(hap != n7)
	{	
		if(msg)
		{
			window.alert(msg + "이(가) 유효하지 않습니다. 확인 후 다시 입력하세요.");
			obj1.value = str1;
			obj2.value = str2;
			obj1.focus();
		}

		return false;
	}

	if(msg)
	{
		obj1.value = str1;
		obj2.value = str2;
	}

	return true;
}

/**
 * 객체의 값이 주민번호에 적합한지 검사한다.
 * 
 * @param obj1	객체 ID
 * @param sep	구분 문자
 * @param msg	객체 이름
 * @return 
 */
function isJumin2(obj, sep, msg)
{
	if(!isObject(obj))
		return false;

	var str = trim(obj.value);
	var arr = new Array(); 
	arr = str.split(sep);

	if(arr.length != 2)
	{
		window.alert(msg + "은(는) [" + sep + "]로 구분해야 합니다.");
		obj.value = str;
		obj.focus();
		return false;
	}

	if(!Number(arr[0]))
	{
		if(msg)
		{
			window.alert("[" + arr[0] + "]은(는) 숫자가 아닙니다. " + msg + "의 앞자리는 숫자로 입력해 주세요.");
			obj.value = str;
			obj.focus();
		}
	
		return false;
	}

	if(!ValidSize(arr[0], 6, 6))
	{
		if(msg)
		{
			window.alert(msg + "의 앞자리는 " + 6 + "자로 입력하세요.");
			obj.value = str;
			obj.focus();
		}

		return false;
	}

	if(!Number(arr[1]))
	{
		if(msg)
		{
			window.alert("[" + arr[1] + "]은(는) 숫자가 아닙니다. " + msg + "의 뒷자리는 숫자로 입력해 주세요.");
			obj.value = str;
			obj.focus();
		}
	
		return false;
	}

	if(!ValidSize(arr[1], 7, 7))
	{
		if(msg)
		{
			window.alert(msg + "의 뒷자리는 " + 7 + "자로 입력하세요.");
			obj.value = str;
			obj.focus();
		}

		return false;
	}

	var hap = 0;
	for(var i = 0; i < 6; i++)
	{
		var temp = arr[0].charAt(i) *(i+2);
		hap += temp;
	}

	var n1 = arr[1].charAt(0);
	var n2 = arr[1].charAt(1);
	var n3 = arr[1].charAt(2);
	var n4 = arr[1].charAt(3);
	var n5 = arr[1].charAt(4);
	var n6 = arr[1].charAt(5);
	var n7 = arr[1].charAt(6);

	hap += n1*8+n2*9+n3*2+n4*3+n5*4+n6*5;
	hap %= 11;
	hap = 11 - hap;
	hap %= 10;

	if(hap != n7)
	{	
		if(msg)
		{
			window.alert(msg + "이(가) 유효하지 않습니다. 확인 후 다시 입력하세요.");
			obj.value = str;
			obj.focus();
		}

		return false;
	}

	if(msg)
	{
		obj.value = str;
	}

	return true;
}

/**
 * 객체의 값이 전화번호에 적합한지 검사한다.
 * 
 * @param obj1	객체1 ID
 * @param obj2	객체2 ID
 * @param obj3	객체3 ID
 * @param msg	객체 이름
 * @return 
 */
function isPhone(obj1, obj2, obj3, msg)
{
	if(!isObject(obj1))
		return false;
	if(!isObject(obj2))
		return false;
	if(!isObject(obj3))
		return false;

	var str1 = trim(obj1.value);
	var str2 = trim(obj2.value);
	var str3 = trim(obj3.value);

	if(!isNumber(obj1, msg + "의 지역번호") || !isValidSize(obj1, 2, 3, msg + "의 지역번호"))
	{
		return false;
	}

	var ddd = ["02", "051", "053", "032", "062", "042", "052", "031", "033", "041", "043", "054", "055", "061", "063", "064"];

	var flag = false;
  	for(var i=0;i<ddd.length;i++)
	{
		if(ddd[i] == str1)
		{
			flag = true;
		}

	}

	if(!flag)
	{
		if(msg)
		{
			window.alert(msg + "의 지역번호가 유효하지 않습니다. 확인 후 다시 입력하세요.");
			obj1.focus();	
		}

		return false;
	}

	if(!isNumber(obj2, msg + "의 국번호") || !isValidSize(obj2, 3, 4, msg + "의 국번호"))
	{
		return false;
	}

	if(!isNumber(obj3, msg + "의 뒷번호") || !isValidSize(obj3, 4, 4, msg + "의 뒷번호"))
	{
		return false;
	}

	return true;
}

/**
 * 객체의 값이 전화번호에 적합한지 검사한다.
 * 
 * @param obj	객체 ID
 * @param sep	구분 문자
 * @param msg	객체 이름
 * @return 
 */
function isPhone2(obj, sep, msg)
{
	if(!isObject(obj))
		return false;

	if(isNull(obj, msg))
		return false;

	var str = trim(obj.value);
	var arr = new Array(); 
	arr = str.split(sep);

	if(arr.length != 3)
	{
		if(msg)
		{
			window.alert(msg + "은(는) [" + sep + "]로 구분해야 합니다.");
			obj.value = str;
			obj.focus();
		}

		return false;
	}


	if(!Number(arr[0]))
	{
		if(msg)
		{
			window.alert("[" + arr[0] + "]은(는) 숫자가 아닙니다. " + msg + "의 지역번호는 숫자로 입력해 주세요.");
			obj.value = str;
			obj.focus();
		}

		return false;
	}

	if(!ValidSize(arr[0], 2, 3))
	{
		if(msg)
		{
			window.alert(msg + "의 지역번호는 최소 " + 2 + "자, 최대 " + 3 + "자로 입력하세요.");
			obj.value = str;
			obj.focus();
		}
		
		return false;
	}

	var ddd = ["02", "051", "053", "032", "062", "042", "052", "031", "033", "041", "043", "054", "055", "061", "063", "064"];

	var flag = false;
  	for(var i=0;i<ddd.length;i++)
	{
		if(ddd[i] == arr[0])
		{
			flag = true;
		}

	}

	if(!flag)
	{
		if(msg)
		{
			window.alert(msg + "의 지역번호가 유효하지 않습니다. 확인 후 다시 입력하세요.");
			obj.value = str;
			obj.focus();	
		}

		return false;
	}

	if(!Number(arr[1]))
	{
		if(msg)
		{
			window.alert("[" + arr[1] + "]은(는) 숫자가 아닙니다. " + msg + "의 국번호는 숫자로 입력해 주세요.");
			obj.value = str;
			obj.focus();
		}
	
		return false;
	}

	if(!ValidSize(arr[1], 3, 4))
	{
		if(msg)
		{
			window.alert(msg + "의 국번호는 최소 " + 3 + "자, 최대 " + 4 + "자로 입력하세요.");
			obj.value = str;
			obj.focus();
		}
		
		return false;
	}

	if(!Number(arr[2]))
	{
		if(msg)
		{
			window.alert("[" + arr[2] + "]은(는) 숫자가 아닙니다. " + msg + "의 뒷번호는 숫자로 입력해 주세요.");
			obj.value = str;
			obj.focus();
		}
	
		return false;
	}

	if(!ValidSize(arr[2], 4, 4))
	{
		if(msg)
		{
			window.alert(msg + "의 뒷번호는 " + 4 + "자로 입력하세요.");
			obj.value = str;
			obj.focus();
		}
		
		return false;
	}

	return true;
}

/**
 * 객체의 값이 전화번호에 적합한지 검사한다.
 * 
 * @param obj1	객체1 ID
 * @param obj2	객체2 ID
 * @param obj3	객체3 ID
 * @param msg	객체 이름
 * @return 
 */
function isCellular(obj1, obj2, obj3, msg)
{
	if(!isObject(obj1))
		return false;
	if(!isObject(obj2))
		return false;
	if(!isObject(obj3))
		return false;

	var str1 = trim(obj1.value)
	var str2 = trim(obj2.value)
	var str3 = trim(obj3.value)

	if(!isNumber(obj1, msg + "의 통신사번호") || !isValidSize(obj1, 3, 3, msg + "의 통신사번호"))
	{
		return false;
	}

	var ddd =["010", "011","016","017","018","019"];

	var flag = false;
  	for(var i = 0; i < ddd.length;i++) {
		if(ddd[i] == str1)
		{
			flag = true;
		}
	}

	if(!flag)
	{
		if(msg)
		{
			window.alert(msg + "의 통신사번호가 유효하지 않습니다. 확인 후 다시 입력하세요.");
			obj1.focus();	
		}

		return false;
	}

	if(!isNumber(obj2, msg + "의 국번호") || !isValidSize(obj2, 3, 4, msg + "의 국번호"))
	{
		return false;
	}

	if(!isNumber(obj3, msg + "의 뒷번호") || !isValidSize(obj3, 4, 4, msg + "의 뒷번호"))
	{
		return false;
	}

	return true;
}

/**
 * 객체의 값이 전화번호에 적합한지 검사한다.
 * 
 * @param obj	객체 ID
 * @param sep	구분 문자
 * @param msg	객체 이름
 * @return 
 */
function isCellular2(obj, sep, msg)
{
	if(!isObject(obj))
		return false;

	if(isNull(obj, msg))
		return false;

	var str = trim(obj.value);
	var arr = new Array(); 
	arr = str.split(sep);

	if(arr.length != 3)
	{
		if(msg)
		{
			window.alert(msg + "은(는) [" + sep + "]로 구분해야 합니다.");
			obj.value = str;
			obj.focus();
		}

		return false;
	}

	if(!Number(arr[0]))
	{
		if(msg)
		{
			window.alert("[" + arr[0] + "]은(는) 숫자가 아닙니다. " + msg + "의 통신사번호는 숫자로 입력해 주세요.");
			obj.value = str;
			obj.focus();
		}

		return false;
	}

	if(!ValidSize(arr[0], 3, 3))
	{
		if(msg)
		{
			window.alert(msg + "의 통신사번호는 " + 3 + "자로 입력하세요.");
			obj.value = str;
			obj.focus();
		}
		
		return false;
	}

	var ddd =["010", "011","016","017","018","019"];

	var flag = false;
  	for(var i=0;i<ddd.length;i++)
	{
		if(ddd[i] == arr[0])
		{
			flag = true;
		}

	}

	if(!flag)
	{
		if(msg)
		{
			window.alert(msg + "의 통신사번호가 유효하지 않습니다. 확인 후 다시 입력하세요.");
			obj.value = str;
			obj.focus();	
		}

		return false;
	}

	if(!Number(arr[1]))
	{
		if(msg)
		{
			window.alert("[" + arr[1] + "]은(는) 숫자가 아닙니다. " + msg + "의 국번호는 숫자로 입력해 주세요.");
			obj.value = str;
			obj.focus();
		}
	
		return false;
	}

	if(!ValidSize(arr[1], 3, 4))
	{
		if(msg)
		{
			window.alert(msg + "의 국번호는 최소 " + 3 + "자, 최대 " + 4 + "자로 입력하세요.");
			obj.value = str;
			obj.focus();
		}
		
		return false;
	}

	if(!Number(arr[2]))
	{
		if(msg)
		{
			window.alert("[" + arr[2] + "]은(는) 숫자가 아닙니다. " + msg + "의 뒷번호는 숫자로 입력해 주세요.");
			obj.value = str;
			obj.focus();
		}
	
		return false;
	}

	if(!ValidSize(arr[2], 4, 4))
	{
		if(msg)
		{
			window.alert(msg + "의 뒷번호는 " + 4 + "자로 입력하세요.");
			obj.value = str;
			obj.focus();
		}
		
		return false;
	}

	return true;
}

/**
 * CHCKBOX를 모두 체크상태로 한다..
 * 
 * @param obj	객체 ID
 * @return 
 */
function setCheckbox(obj)
{
	if(!isObject(obj))
		return false;

	if(!isArray(obj))
	{
		obj.checked = true;
	}else{	
		for(var i = 0; i < obj.length; i++)
		{
			obj[i].checked = true;
		}
	}	
}

/**
 * CHCKBOX 객체를 모두 해제한다.
 * 
 * @param obj	객체 ID
 * @return 
 */
function resetCheckbox(obj)
{
	if(!isObject(obj))
		return false;

	if(!isArray(obj)){
		obj.checked = false;
	}else{

		for(var i = 0; i < obj.length; i++)
		{
			obj[i].checked = false;
		}
	}	
}

/**
 * CHCKBOX 객체를 모두 반전한다.
 * 
 * @param obj	객체 ID
 * @return 
 */
function reverseCheckbox(obj)
{
	if(!isObject(obj))
		return false;

	if(!isArray(obj))
	{
		return false;
	}

	for(var i = 0; i < obj.length; i++)
	{
		if(obj[i].checked == true)
			obj[i].checked = false;
		else
			obj[i].checked = true;
	}
}

/**
 * CHCKBOX, RADIO 객체가 체크되었는지 검사한다.
 * 
 * @param obj	객체 ID
 * @param msg	객체 이름
 * @return 
 */
function isChecked(obj, msg)
{
	if(!isObject(obj))
		return false;

	var flag = false;

	if(isArray(obj))
	{
		for(i=0; i<obj.length; i++)
		{

			if(obj[i].checked)
			{
				flag = true;
				break;
			}	
		}
	}
	else
	{
		if(obj.checked)
		{
			flag = true;
		}
	}

	if(!flag)
	{
		if(msg)
		{
			//window.alert("[" + msg + "]은(는) 하나이상 선택해야 합니다.");
			window.alert(msg);
			if(isArray(obj))
				obj[0].focus();
			else
				obj.focus();
		}

		return false;
	}

	return true;
}

/**
 * 객체의 값이 연도에 적합한지 검사한다.
 * 
 * @param obj	객체 ID
 * @param msg	객체 이름
 * @return 
 */
function isValidYear(obj, msg)
{	
	if(!isObject(obj))
		return false;

	var str = parseInt(trim(obj.value), 10);

	if(!isNumber(obj) || !(str >= 1900 && str <= 2100))
	{
		if(msg)
		{
			window.alert("[" + str + "]은(는) 유효한 " + msg + "이(가) 아닙니다.");
			obj.focus();
		}

		return false;
	}

	if(msg)
	{
		obj.value = str;
	}

	return true;

}

/**
 * 객체의 값이 월에 적합한지 검사한다.
 * 
 * @param obj	객체 ID
 * @param msg	객체 이름
 * @return 
 */
function isValidMonth(obj, msg)
{
	if(!isObject(obj))
		return false;

	var str = parseInt(trim(obj.value), 10);


	if(!isNumber(obj) || parseInt(str) > 12 || parseInt(str) < 1)
	{
		if(msg)
		{
			window.alert("[" + str + "]은(는) 유효한 " + msg + "이(가) 아닙니다.");
			obj.focus();
		}

		return false;
	}

	if(str.length  == 1)
	{
		str = "0" + str;
	}

	if(msg)
	{
		obj.value = str;
	}

	return true;

}

/**
 * 객체의 값이 일에 적합한지 검사한다.(삭제대상)
 * 
 * @param obj	객체1 ID
 * @param obj	객체2 ID
 * @param obj	객체3 ID
 * @param msg	객체 이름
 * @return 
 */

function isValidDay(obj1, obj2, obj3, msg)
{
	return isValidDate(obj1, obj2, obj3, msg);
}

/**
 * 객체의 값이 일에 적합한지 검사한다.
 * 
 * @param obj	객체1 ID
 * @param obj	객체2 ID
 * @param obj	객체3 ID
 * @param msg	객체 이름
 * @return 
 */
function isValidDate(obj1, obj2, obj3, msg)
{
	if(!isObject(obj1))
		return false;
	if(!isObject(obj2))
		return false;
	if(!isObject(obj3))
		return false;

	var str1 = parseInt(trim(obj1.value), 10);
	var str2 = parseInt(trim(obj2.value), 10) - 1;
	var str3 = parseInt(trim(obj3.value), 10);

	if(!isValidYear(obj1, msg))
	{
		return false;
	}

	if(!isValidMonth(obj2, msg))
	{
		return false;
	}
	
	var endstr3 = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	
	if((str1 % 4 == 0 && str1 % 100 != 0) || str1 % 400 == 0)
	{
		endstr3[1] = 29;
	}

	if(!isNumber(obj3) || !(str3 >= 1 && str3 <= endstr3[str2]))
	{
		if(msg)
		{
			window.alert("[" + str3 + "]은(는) 유효한 " + msg + "이(가) 아닙니다.");
			obj3.focus();
		}

		return false;
	}

	if(msg)
	{
		obj1.value = str1;
		obj2.value =(str2.length == 1)?"0" + str2:str2;
		obj3.value =(str3.length == 1)?"0" + str3:str3;
	}

	return true;
}

/**
 * 객체의 값이 일에 적합한지 검사한다.(삭제대상)
 * 
 * @param obj	객체 ID
 * @param sep	구분 문자
 * @param msg	객체 이름
 * @return 
 */
function isValidDay2(obj, sep, msg)
{
	return isValidDate2(obj, sep, msg);
}

/**
 * 객체의 값이 일에 적합한지 검사한다.
 * 
 * @param obj	객체 ID
 * @param sep	구분 문자
 * @param msg	객체 이름
 * @return 
 */
function isValidDate2(obj, sep, msg)
{
	if(!isObject(obj))
		return false;

	if(isNull(obj, msg))
		return false;

	var str = trim(obj.value);
	var arr = new Array();
	arr = str.split(sep);

	if(sep.length == 0)
	{
		arr[0] = str.substring(0, 4);
		arr[1] = str.substring(4, 6);
		arr[2] = str.substring(6, 8);
	}
	else
	{

		if(arr.length != 3)
		{
			if(msg)
			{
				window.alert(msg + "은(는) [" + sep + "]로 구분해야 합니다.");
				obj.value = str;
				obj.focus();
			}

			return false;
		}
	}

	if(!ValidDate(arr[0], arr[1], arr[2]))
	{
		if(msg)
		{
			window.alert("[" + str + "]은(는) 유효한 " + msg + "이(가) 아닙니다.");
			obj.focus();
		}

		return false;
	}

	if(arr[1].length == 1)
	{

		arr[1] = "0" + arr[1];
	}

	if(arr[2].length == 1)
	{

		arr[2] = "0" + arr[2];
	}

	if(msg)
	{
		obj.value = arr[0] + sep + arr[1] + sep + arr[2];
	}

	return true;
}

/**
 * 객체의 값이 연월에 적합한지 검사한다.
 * 
 * @param obj1	객체1 ID
 * @param obj2	객체2 ID
 * @param msg	객체 이름
 * @return 
 */
function isValidYearMonth(obj1, obj2, msg)
{
	if(!isObject(obj1))
		return false;

	if(!isObject(obj2))
		return false;

	var str1 = parseInt(trim(obj1.value), 10);
	var str2 = parseInt(trim(obj2.value), 10);


	if(!isValidYear(obj1, msg) || !isValidMonth(obj2, msg))
	{
		return false;
	}

	if(msg)
	{
		obj1.value = str1;
		obj2.value =(str2.length == 1)?"0" + str2:str2;
	}

	return true;
}

/**
 * 객체의 값이 연월에 적합한지 검사한다.
 * 
 * @param obj	객체 ID
 * @param sep	구분 문자
 * @param msg	객체 이름
 * @return 
 */
function isValidYearMonth2(obj, sep, msg)
{
	if(!isObject(obj))
		return false;

	if(isNull(obj, msg))
		return false;

	var str = trim(obj.value);
	var arr = new Array(); 

	if(sep.length == 0)
	{
		arr[0] = str.substring(0, 4);
		arr[1] = str.substring(4, 6);
	}
	else
	{
		arr = str.split(sep);

		if(arr.length != 2)
		{
			if(msg)
			{
				window.alert(msg + "은(는) [" + sep + "]로 구분해야 합니다.");
				obj.value = str;
				obj.focus();
			}

			return false;
		}
	}


	if(!ValidYear(arr[0]) || !ValidMonth(arr[1]))
	{
		if(msg)
		{
			window.alert("[" + str + "]은(는) 유효한 " + msg + "이(가) 아닙니다.");
			obj.value = str;
			obj.focus();
		}

		return false;
	}

	if(arr[1].length == 1)
	{

		arr[1] = "0" + arr[1];
	}

	if(msg)
	{
		obj.value = arr[0] + sep + arr[1];
	}

	return true;
}

function insertSeperatorByWon(obj, sep)
{
	if(!isObject(obj))
		return false;

	var str = trim(obj.value);
	str = removeString(str, sep);

    var signFlag = false;
    if(str.indexOf("-") != -1)
    {
    	str = removeString(str, "-");
        signFlag = true;
    }

    var size = 3;
	var strMain = "";
    var strTail = "";
	var strTemp = "";
	var strSep = sep;

    if(str.indexOf(".") != -1)
    {
        strMain = str.substring(0, str.indexOf("."));
        strTail = str.substring(str.indexOf("."));
    }
    else
    {
        strMain = str;
    }

    if(str.indexOf(".") == -1) // 소수점이 없을경우 -> ,(Comma)로 분할한다
    {
        var strMainLength  = strMain.length;

        if(strMainLength > size)
        {
            var divLength = parseInt((strMainLength)/ size);
            var remLength = parseInt((strMainLength)% size);

            if (remLength > 0)
            {
                strTemp = strMain.substring(0, remLength) + strSep;
            }

            for (i = 0; i < divLength; i++)
            {
                if (i == 0)
                {
                    strTemp = strTemp + strMain.substring(remLength, remLength + size);
                }
                else
                {
                    strTemp = strTemp + strSep + strMain.substring(remLength + (size * i), remLength + (size * i) + size);
                }
            }
        }
        else
        {
            strTemp = strMain;
        }

        obj.value = (signFlag?"-":"") + strTemp + strTail;
    }
    else // 소수점이 있을 경우
    {
        var strMainLength = strMain.length;
        if(strMainLength <= size) // 소수점이 있고 소수점이상이 3자리를 초과하지 않을경우 -> 소수점이상 그대로 복사
        {
            strTemp = strMain;
        }
        else // 소수점이 있고 소수점이상이 3자리를 초과할 경우 -> 소수점이상 ,(Comma)로 분할한다.
        {
            var divLength = parseInt((strMainLength)/ size);
            var remLength = parseInt((strMainLength)% size);

            if (remLength > 0)
            {
                strTemp = strMain.substring(0, remLength) + strSep;
            }

            for (i = 0; i < divLength; i++)
            {
                if (i == 0)
                {
                    strTemp = strTemp + strMain.substring(remLength, remLength + size);
                }
                else
                {
                    strTemp = strTemp + strSep + strMain.substring(remLength + (size * i), remLength + (size * i) + size);
                }
            }
        }

        obj.value = (signFlag?"-":"") + strTemp + strTail;
    }
}

function insertSeperatorByYearMonth(obj, sep)
{
	if(!isObject(obj))
		return false;

	var str = trim(obj.value);
	str = removeString(str, sep);

	if(str.length == 4)
	{
		obj.value = str.substring(0, 4);
	}
	else if(str.length > 4)
	{
		obj.value = str.substring(0, 4) + sep + str.substring(4, (str.length > 6)?6:str.length);
	}
}

function insertSeperatorBySsn(obj, sep)
{
	if(!isObject(obj))
		return false;

	var str = trim(obj.value);
	str = removeString(str, sep);

	if(str.length == 6)
	{
		obj.value =  str.substring(0, 6);
	}
	else if(str.length >= 6)
	{
		obj.value =  str.substring(0, 6) + sep + str.substring(6, (str.length > 13)?13:str.length);
	}
}

function insertSeperatorByDate(obj, sep)
{
	if(!isObject(obj))
		return false;

	var str = trim(obj.value);
	str = removeString(str, sep);

	if(str.length == 4)
	{
		obj.value = str.substring(0, 4);
	}
	else if(str.length > 4 && str.length < 6)
	{
		obj.value = str.substring(0, 4) + sep + str.substring(4, (str.length>6)?6:str.length);
	}
	else if(str.length == 6)
	{
		obj.value =  str.substring(0, 4) + sep + str.substring(4, 6);
	}
    else if(str.length > 6)
	{
		obj.value =  str.substring(0, 4) + sep + str.substring(4, 6) + sep + str.substring(6, (str.length > 8)?8:str.length);
	}
}

function insertSeperatorByTime(obj, sep)
{
	if(!isObject(obj))
		return false;

	var str = trim(obj.value);
	str = removeString(str, sep);

	if(str.length == 2)
	{
		obj.value = str.substring(0, 2);
	}
	else if(str.length > 2 && str.length < 4)
	{
		obj.value = str.substring(0, 2) + sep + str.substring(2, (str.length > 4)?4:str.length);
	}
}

function insertSeperatorByDay(obj, sep)
{
	return insertSeperatorByDate(obj, sep);
}

function insertSeperatorByJumin(obj, sep)
{
	return insertSeperatorBySsn(obj, sep);
}

function insertSeperatorByBrn(obj, sep)
{
	if(!isObject(obj))
		return false;

	var str = trim(obj.value);
	str = removeString(str, sep);

	if(str.length == 3)
	{
		obj.value =  str.substring(0, 3);
	}
	if(str.length > 3 && str.length < 5)
	{
		obj.value =  str.substring(0, 3) + sep + str.substring(3, (str.length>5)?5:str.length);
	}
	else if(str.length == 5)
	{
		obj.value =  str.substring(0, 3) + sep + str.substring(3, 5);
	}
    else if(str.length > 5)
	{
		obj.value =  str.substring(0, 3) + sep + str.substring(3, 5) + sep + str.substring(5, (str.length > 10)?10:str.length);
	}
}

function insertSeperatorByBizno(obj, sep)
{
	return insertSeperatorByBrn(obj, sep);
}

function insertSeperatorByZipcode(obj, sep)
{
	if(!isObject(obj))
		return false;

	var str = trim(obj.value);
	str = removeString(str, sep);

	if(str.length == 3)
	{
		obj.value =  str.substring(0, 3);
	}
	else if(str.length > 3)
	{
		obj.value =  str.substring(0, 3) + sep + str.substring(3, (str.length > 6)?6:str.length);
	}
}

function isNum(val, msg)
{
	var str = trim(val);

	for(var i=0; i < str.length ; i++)
	{
		var code = str.charCodeAt(i);

		if(!(code >= 48 && code <= 57))
		{
			if(msg)
			{
				window.alert("[" + str.charAt(i) + "]은(는) 숫자가 아닙니다. " + msg + "은(는) 숫자로 입력해 주세요.");
				return false;
			}
		}
	}
	return true;
}

function isTel(val, sep, msg)
{

	var str = trim(val);
	var arr = new Array(); 
	arr = str.split(sep);

	if(arr.length != 3)
	{
		if(msg)
		{
			window.alert(msg + "은(는) [" + sep + "]로 구분해야 합니다.");
			return false;
		}		
	}

	if(!Number(arr[0]))
	{
		if(msg)
		{
			window.alert("[" + arr[0] + "]은(는) 숫자가 아닙니다. " + msg + "의 통신사번호는 숫자로 입력해 주세요.");
			return false;
		}		
	}

	if(!ValidSize(arr[0], 3, 3))
	{
		if(msg)
		{
			window.alert(msg + "의 통신사번호는 " + 3 + "자로 입력하세요.");
			return false;
		}
	}

	var ddd =["010", "011","016","017","018","019"];

	var flag = false;
  	for(var i=0;i<ddd.length;i++)
	{
		if(ddd[i] == arr[0])
		{
			flag = true;
		}

	}

	if(!flag)
	{
		if(msg)
		{
			window.alert(msg + "의 통신사번호가 유효하지 않습니다. 확인 후 다시 입력하세요.");
			return false;
		}		
	}

	if(!Number(arr[1]))
	{
		if(msg)
		{
			window.alert("[" + arr[1] + "]은(는) 숫자가 아닙니다. " + msg + "의 국번호는 숫자로 입력해 주세요.");
			return false;
		}		
	}

	if(!ValidSize(arr[1], 3, 4))
	{
		if(msg)
		{
			window.alert(msg + "의 국번호는 최소 " + 3 + "자, 최대 " + 4 + "자로 입력하세요.");
			return false;
		}		
	}

	if(!Number(arr[2]))
	{
		if(msg)
		{
			window.alert("[" + arr[2] + "]은(는) 숫자가 아닙니다. " + msg + "의 뒷번호는 숫자로 입력해 주세요.");
			return false;
		}		
	}

	if(!ValidSize(arr[2], 4, 4))
	{
		if(msg)
		{
			window.alert(msg + "의 뒷번호는 " + 4 + "자로 입력하세요.");
			return false;
		}		
	}

	return true;
}

function isLessDate(val1, val2, msg1, msg2){
	if(val1 == "" && val2 == ""){
		return true;
	}else{
		var str1 = trim(val1.value);
		var str2 = trim(val2.value);
		var arr1 = new Array(); 
		arr1 = str1.split("-");
		var arr2 = new Array(); 
		arr2 = str2.split("-");
	
		if(arr1.length != 3)
		{
			window.alert(msg1+"은(는) 날짜 형식이 아닙니다 .[ex)2010-01-01]");
			return false;		
		}
		
		if(arr2.length != 3)
		{
			window.alert(msg2+"은(는) 날짜 형식이 아닙니다 .[ex)2010-01-01]");
			return false;		
		}
	
		if(!Number(arr1[0]))
		{
			window.alert("[" + arr1[0] + "]은 숫자가 아닙니다. " + msg1 + "의 연도는 숫자로 입력해 주세요.");
			return false;
		}else if(!Number(arr1[1])){
			window.alert("[" + arr1[1] + "]은 숫자가 아닙니다. " + msg1 + "의 월은 숫자로 입력해 주세요.");
			return false;
		}else if(!Number(arr1[2])){
			window.alert("[" + arr1[2] + "]은 숫자가 아닙니다. " + msg1 + "의 일은 숫자로 입력해 주세요.");
			return false;
		}
		
		if(!Number(arr2[0]))
		{
			window.alert("[" + arr2[0] + "]은 숫자가 아닙니다. " + msg2 + "의 연도는 숫자로 입력해 주세요.");
			return false;
		}else if(!Number(arr2[1])){
			window.alert("[" + arr2[1] + "]은 숫자가 아닙니다. " + msg2 + "의 월은 숫자로 입력해 주세요.");
			return false;
		}else if(!Number(arr2[2])){
			window.alert("[" + arr2[2] + "]은 숫자가 아닙니다. " + msg2 + "의 일은 숫자로 입력해 주세요.");
			return false;
		}
		
		if(arr1[0] > arr2[0])
		{
			window.alert(msg1 + "은 "+ msg2 + "보다 이전일이어야  합니다.");
			return false;
		}else if(arr1[0] == arr2[0]){
			if(arr1[1] > arr2[1]){
				window.alert(msg1 + "은 "+ msg2 + "보다 이전일이어야  합니다.");
				return false;
			}else if(arr1[1] == arr2[1]){
				if(arr1[2] > arr2[2]){
					window.alert(msg1 + "은 "+ msg2 + "보다 이전일이어야  합니다.");
					return false;
				
				}
			}
		}		
		return true;
	}
}
	

	function isGreatDate(val1, val2, msg1, msg2){
		if(val1 == "" && val2 == ""){
			return true;
		}else{
			var str1 = trim(val1.value);
			var str2 = trim(val2.value);
			var arr1 = new Array(); 
			arr1 = str1.split("-");
			var arr2 = new Array(); 
			arr2 = str2.split("-");
		
			if(arr1.length != 3)
			{
				window.alert(msg1+"은(는) 날짜 형식이 아닙니다 .[ex)2010-01-01]");
				return false;		
			}
			
			if(arr2.length != 3)
			{
				window.alert(msg2+"은(는) 날짜 형식이 아닙니다 .[ex)2010-01-01]");
				return false;		
			}
		
			if(!Number(arr1[0]))
			{
				window.alert("[" + arr1[0] + "]은 숫자가 아닙니다. " + msg1 + "의 연도는 숫자로 입력해 주세요.");
				return false;
			}else if(!Number(arr1[1])){
				window.alert("[" + arr1[1] + "]은 숫자가 아닙니다. " + msg1 + "의 월은 숫자로 입력해 주세요.");
				return false;
			}else if(!Number(arr1[2])){
				window.alert("[" + arr1[2] + "]은 숫자가 아닙니다. " + msg1 + "의 일은 숫자로 입력해 주세요.");
				return false;
			}
			
			if(!Number(arr2[0]))
			{
				window.alert("[" + arr2[0] + "]은 숫자가 아닙니다. " + msg2 + "의 연도는 숫자로 입력해 주세요.");
				return false;
			}else if(!Number(arr2[1])){
				window.alert("[" + arr2[1] + "]은 숫자가 아닙니다. " + msg2 + "의 월은 숫자로 입력해 주세요.");
				return false;
			}else if(!Number(arr2[2])){
				window.alert("[" + arr2[2] + "]은 숫자가 아닙니다. " + msg2 + "의 일은 숫자로 입력해 주세요.");
				return false;
			}
			
			if(arr1[0] > arr2[0])
			{
				window.alert(msg2 + "은 "+ msg1 + "보다 이후일이어야 합니다.");
				return false;
			}else if(arr1[0] == arr2[0]){
				if(arr1[1] > arr2[1]){
					window.alert(msg2 + "은 "+ msg1 + "보다 이후일이어야  합니다.");
					return false;
				}else if(arr1[1] == arr2[1]){
					if(arr1[2] > arr2[2]){
						window.alert(msg2 + "은 "+ msg1 + "보다 이후일이어야  합니다.");
						return false;
					
					}
				}
			}		
			return true;
		}
	}