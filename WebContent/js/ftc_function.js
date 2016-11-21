/**
 * 입력값의 크기를 검사한다.
 * 
 * @param str	문자열
 * @param min	최소 자리수 
 * @param max	최대 자리수 
 * @return
 */
function ValidSize(str, min, max)
{
	if(!(strlen(str) >= min && strlen(str) <= max))
	{
		return false;
	}

	return true;
}

/**
 * 객체의 값이 숫자인지 검사한다.
 * 
 * @param str	문자열
 * @return 
 */
function Number(str)
{

	for(var i=0; i < str.length ; i++)
	{
		var code = str.charCodeAt(i);

		if ((code < 48) || (code > 57))
		{
			return false;
		}
	}

	return true;
}

/**
 * 객체의 값이 한글인지 검사한다.
 * 
 * @param str	문자열
 * @return 
 */
function Korean(str)
{
	for(var i=0; i < str.length ; i++)
	{
		var code = str.charCodeAt(i);

		if (!(code > 255))
		{
			return false;
		}
	}

	return true;
}

/**
 * 객체의 값이 영문인지 검사한다.
 * 
 * @param str	문자열
 * @return 
 */
function Alphabet(str)
{
	for(var i=0; i < str.length ; i++)
	{
		var code = str.charCodeAt(i);

		if (!((code >= 65) || (code <= 122)))
		{
			return false;
		}
	}

	return true;
}

/**
 * 객체의 값이 영문 또는 숫자인지 검사한다.
 * 
 * @param str	문자열
 * @return 
 */
function AlphaNumeric(str)
{
	for(var i=0; i < str.length ; i++)
	{
		var code = str.charCodeAt(i);

		if(!((code >= 65) && (code <= 122)) && !((code >= 48) && (code <= 57)))
		{
			return false;
		}
	}

	return true;
}

/**
 * 두 객체의 값이 동일한지 검사한다.
 * 
 * @param str1	문자열1
 * @param str2	문자열2
 * @return 
 */
function Same(str1, str2)
{
	if(str1 != str2)
	{
		return false;
	}

	return true;
}

/**
 * 객체의 값이 아이디에 적합한지 검사한다.
 * - 아이디형식 : 영문, 숫자, _ 를 사용하여 5자이상 10자이하
 * 
 * @param str	문자열
 * @return 
 */
function Id(str)
{
	if(!ValidSize(str, 5, 10))
	{
		return false;
	}

	if(!AlphaNumeric(str))
	{
		return false;
	}

	return true;
}

/**
 * 객체의 값이 E-Mail에 적합한지 검사한다.
 * 
 * @param str	문자열
 * @return 
 */
function Mail(str)
{
	var i = str.indexOf("@");
	if(i < 0)
	{
		return false;
	}

	i = str.indexOf(".");
	if(i < 0)
	{
		return false;
	}

	return true;
}

/**
 * 객체의 값이 주민번호에 적합한지 검사한다.
 * 
 * @param str1	문자열1
 * @param str1	문자열2
 * @return 
 */
function Jumin(str1, str2)
{
	if(!ValidSize(str1, 6, 6, msg + " 앞자리"))
	{
		return false;
	}

	if(!ValidSize(str2, 7, 7, msg + " 뒷자리"))
	{
		return false;
	}

	var hap = 0;
	for(var i = 0; i < 6; i++)
	{
		var temp = str1.charAt(i) * (i+2);
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
		return false;
	}

	return true;
}

/**
 * 객체의 값이 전화번호에 적합한지 검사한다.
 * 
 * @param str1	문자열1
 * @param str2	문자열2
 * @param str3	문자열3
 * @return 
 */
function Phone(str1, str2, str3)
{
	if(!Number(str1) || !ValidSize(str1, 2, 3))
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
		return false;
	}

	if(!Number(str2) || !ValidSize(str2, 3, 4))
	{
		return false;
	}

	if(!Number(str3) || !ValidSize(str3, 4, 4))
	{
		return false;
	}

	return true;
}

/**
 * 객체의 값이 전화번호에 적합한지 검사한다.
 * 
 * @param str1	문자열1
 * @param str2	문자열2
 * @param str3	문자열3
 * @return 
 */
function Cellular(str1, str2, str3)
{
	if(!Number(str1) || !ValidSize(str1, 3, 3))
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
		return false;
	}

	if(!Number(str2) || !ValidSize(str2, 3, 4))
	{
		return false;
	}

	if(!Number(str3) || !ValidSize(str3, 4, 4))
	{
		return false;
	}

	return true;
}

/**
 * 객체의 값이 연도에 적합한지 검사한다.
 * 
 * @param str	문자열
 * @return 
 */
function ValidYear(str)
{	
	var year = parseInt(trim(str), 10);

	if(!Number(str) || !ValidSize(str, 4, 4) || !(year >= 0))
	{
		return false;
	}

	return true;

}

/**
 * 객체의 값이 월에 적합한지 검사한다.
 * 
 * @param str	문자열
 * @return 
 */
function ValidMonth(str)
{
	var month = parseInt(trim(str), 10);


	if(!Number(str) || parseInt(month) > 12 || parseInt(month) < 1)
	{
		return false;
	}

	return true;

}

/**
 * 객체의 값이 일에 적합한지 검사한다. (삭제대상)
 * 
 * @param str1	문자열1
 * @param str2	문자열2
 * @param str3	문자열3
 * @return 
 */
function ValidDay(str1, str2, str3)
{
	return ValidDate(str1, str2, str3)
}

/**
 * 객체의 값이 일에 적합한지 검사한다.
 * 
 * @param str1	문자열1
 * @param str2	문자열2
 * @param str3	문자열3
 * @return 
 */
function ValidDate(str1, str2, str3)
{
	var year = parseInt(trim(str1), 10);
	var month = parseInt(trim(str2), 10) - 1;
	var day = parseInt(trim(str3), 10);

	if(!ValidYear(str1))
	{
		return false;
	}

	if(!ValidMonth(str2))
	{
		return false;
	}
	
	var endDay = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	
	if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
	{
		endDay[1] = 29;
	}

	if(!Number(str3) || !(day >= 1 && day <= endDay[month]))
	{
		return false;
	}

	return true;
}

/**
 * 객체의 값이 연월에 적합한지 검사한다.
 * 
 * @param str1	문자열1
 * @param str2	문자열2
 * @return 
 */
function ValidYearMonth(str1, str2)
{
	var year = parseInt(trim(str1), 10);
	var month = parseInt(trim(str2), 10);


	if(!ValidYear(str1) || !ValidMonth(str2))
	{
		return false;
	}

	return true;
}