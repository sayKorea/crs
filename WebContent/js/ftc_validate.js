/**
 * �Է°��� ��ȿ���� �˻��Ѵ�.
 * 
 * @param obj	��ü ID
 * @param type	Ÿ��(K : �ѱ�, A : ����, AN : ������ ȥ��)
 * @param min	�ּ� �ڸ��� 
 * @param max	�ִ� �ڸ��� 
 * @param msg	��ü �̸�
 * @return		��ü �̸�
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
 * �Է°��� ũ�⸦ �˻��Ѵ�.
 * 
 * @param obj	��ü ID
 * @param min	�ּ� �ڸ��� 
 * @param max	�ִ� �ڸ��� 
 * @param msg	�޼���
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
				window.alert(msg + "��(��) " + min +"�ڷ� �Է��ϼ���.");
			else
				window.alert(msg + "��(��) �ּ� " + min +"��, �ִ� " + max + "�ڷ� �Է��ϼ���.");

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
 * ��ü�� ���� �������� �˻��Ѵ�.
 * 
 * @param obj	��ü ID
 * @param msg	��ü �̸�
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
				window.alert("[" + str.charAt(i) + "]��(��) ���ڰ� �ƴմϴ�. " + msg + "��(��) ���ڷ� �Է��� �ּ���.");
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
 * ��ü�� ���� �ѱ����� �˻��Ѵ�.
 * 
 * @param obj	��ü ID
 * @param msg	��ü �̸�
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
				window.alert("[" + str.charAt(i) + "]��(��) �ѱ��� �ƴմϴ�. " + msg + "��(��) �ѱ۷� �Է��� �ּ���.");
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
 * ��ü�� ���� �������� �˻��Ѵ�.
 * 
 * @param obj	��ü ID
 * @param msg	��ü �̸�
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
				window.alert("[" + str.charAt(i) + "]��(��) ������ �ƴմϴ�. " + msg + "��(��) �������� �Է��� �ּ���.");
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
 * ��ü�� ���� ���� �Ǵ� �������� �˻��Ѵ�.
 * 
 * @param obj	��ü ID
 * @param msg	��ü �̸�
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
				window.alert("[" + str.charAt(i) + "]��(��) ���� �Ǵ� ���ڰ� �ƴմϴ�. " + msg + "��(��) ���� �Ǵ� ���ڷ� �Է��� �ּ���.");
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
 * �� ��ü�� ���� �������� �˻��Ѵ�.
 * 
 * @param obj1	��ü1 ID
 * @param obj2	��ü2 ID
 * @param msg	��ü �̸�
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
			window.alert(msg + "��(��) ��ġ���� �ʽ��ϴ�. Ȯ�� �� �ٽ� �Է��� �ּ���.");
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
 * ��ü�� ���� ���̵� �������� �˻��Ѵ�.
 * - ���̵����� : ����, ����, _ �� ����Ͽ� 5���̻� 10������
 * 
 * @param obj	��ü ID
 * @param msg	��ü �̸�
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
 * ��ü�� ���� ��й�ȣ�� �������� üũ�Ѵ�.
 * - ���̵����� : ����, ����, _ �� ���
 * 
 * @param obj	��ü
 * @return 
 */
function checkPassword(obj) 
{
	if(!isObject(obj))
		return false;
		
	if(!IsInNumeric(obj.value))
	{
		window.alert("��й�ȣ�� ���ڰ� ���ԵǾ� ���� �ʽ��ϴ�. Ȯ�� �� �ٽ� �Է��� �ּ���.");
		obj.focus();
		return false;
	}
	
	if(!IsInAlpabet(obj.value))
	{
		window.alert("��й�ȣ�� ������ ���ԵǾ� ���� �ʽ��ϴ�. Ȯ�� �� �ٽ� �Է��� �ּ���.");
		obj.focus();
		return false;	
	}
	return true;
	
}
/**
 * �־��� ���ڿ��� ������ ���ԵǾ� �ִ��� üũ�Ѵ�.
 * 
 * @param str 	���ڿ�
 * @return 		�������Կ���
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
 * �־��� ���ڿ��� ���ڰ� ���ԵǾ� �ִ��� üũ�Ѵ�.
 * 
 * @param str 	���ڿ�
 * @return 		�������Կ���
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
 * ��ü�� ���� E-Mail�� �������� �˻��Ѵ�.
 * 
 * @param obj	��ü ID
 * @param msg	��ü �̸�
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
			window.alert(msg + "��(��) ��ȿ���� �ʽ��ϴ�. Ȯ�� �� �ٽ� �Է��ϼ���.");
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
			window.alert(msg + "��(��) ��ȿ���� �ʽ��ϴ�. Ȯ�� �� �ٽ� �Է��ϼ���.");
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
 * ��ü�� ���� �ֹι�ȣ�� �������� �˻��Ѵ�.
 * 
 * @param obj1	��ü1 ID
 * @param obj2	��ü2 ID
 * @param msg	��ü �̸�
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

	if(!isValidSize(obj1, 6, 6, msg + " ���ڸ�"))
	{
		return false;
	}

	if(!isValidSize(obj2, 7, 7, msg + " ���ڸ�"))
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
			window.alert(msg + "��(��) ��ȿ���� �ʽ��ϴ�. Ȯ�� �� �ٽ� �Է��ϼ���.");
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
 * ��ü�� ���� �ֹι�ȣ�� �������� �˻��Ѵ�.
 * 
 * @param obj1	��ü ID
 * @param sep	���� ����
 * @param msg	��ü �̸�
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
		window.alert(msg + "��(��) [" + sep + "]�� �����ؾ� �մϴ�.");
		obj.value = str;
		obj.focus();
		return false;
	}

	if(!Number(arr[0]))
	{
		if(msg)
		{
			window.alert("[" + arr[0] + "]��(��) ���ڰ� �ƴմϴ�. " + msg + "�� ���ڸ��� ���ڷ� �Է��� �ּ���.");
			obj.value = str;
			obj.focus();
		}
	
		return false;
	}

	if(!ValidSize(arr[0], 6, 6))
	{
		if(msg)
		{
			window.alert(msg + "�� ���ڸ��� " + 6 + "�ڷ� �Է��ϼ���.");
			obj.value = str;
			obj.focus();
		}

		return false;
	}

	if(!Number(arr[1]))
	{
		if(msg)
		{
			window.alert("[" + arr[1] + "]��(��) ���ڰ� �ƴմϴ�. " + msg + "�� ���ڸ��� ���ڷ� �Է��� �ּ���.");
			obj.value = str;
			obj.focus();
		}
	
		return false;
	}

	if(!ValidSize(arr[1], 7, 7))
	{
		if(msg)
		{
			window.alert(msg + "�� ���ڸ��� " + 7 + "�ڷ� �Է��ϼ���.");
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
			window.alert(msg + "��(��) ��ȿ���� �ʽ��ϴ�. Ȯ�� �� �ٽ� �Է��ϼ���.");
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
 * ��ü�� ���� ��ȭ��ȣ�� �������� �˻��Ѵ�.
 * 
 * @param obj1	��ü1 ID
 * @param obj2	��ü2 ID
 * @param obj3	��ü3 ID
 * @param msg	��ü �̸�
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

	if(!isNumber(obj1, msg + "�� ������ȣ") || !isValidSize(obj1, 2, 3, msg + "�� ������ȣ"))
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
			window.alert(msg + "�� ������ȣ�� ��ȿ���� �ʽ��ϴ�. Ȯ�� �� �ٽ� �Է��ϼ���.");
			obj1.focus();	
		}

		return false;
	}

	if(!isNumber(obj2, msg + "�� ����ȣ") || !isValidSize(obj2, 3, 4, msg + "�� ����ȣ"))
	{
		return false;
	}

	if(!isNumber(obj3, msg + "�� �޹�ȣ") || !isValidSize(obj3, 4, 4, msg + "�� �޹�ȣ"))
	{
		return false;
	}

	return true;
}

/**
 * ��ü�� ���� ��ȭ��ȣ�� �������� �˻��Ѵ�.
 * 
 * @param obj	��ü ID
 * @param sep	���� ����
 * @param msg	��ü �̸�
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
			window.alert(msg + "��(��) [" + sep + "]�� �����ؾ� �մϴ�.");
			obj.value = str;
			obj.focus();
		}

		return false;
	}


	if(!Number(arr[0]))
	{
		if(msg)
		{
			window.alert("[" + arr[0] + "]��(��) ���ڰ� �ƴմϴ�. " + msg + "�� ������ȣ�� ���ڷ� �Է��� �ּ���.");
			obj.value = str;
			obj.focus();
		}

		return false;
	}

	if(!ValidSize(arr[0], 2, 3))
	{
		if(msg)
		{
			window.alert(msg + "�� ������ȣ�� �ּ� " + 2 + "��, �ִ� " + 3 + "�ڷ� �Է��ϼ���.");
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
			window.alert(msg + "�� ������ȣ�� ��ȿ���� �ʽ��ϴ�. Ȯ�� �� �ٽ� �Է��ϼ���.");
			obj.value = str;
			obj.focus();	
		}

		return false;
	}

	if(!Number(arr[1]))
	{
		if(msg)
		{
			window.alert("[" + arr[1] + "]��(��) ���ڰ� �ƴմϴ�. " + msg + "�� ����ȣ�� ���ڷ� �Է��� �ּ���.");
			obj.value = str;
			obj.focus();
		}
	
		return false;
	}

	if(!ValidSize(arr[1], 3, 4))
	{
		if(msg)
		{
			window.alert(msg + "�� ����ȣ�� �ּ� " + 3 + "��, �ִ� " + 4 + "�ڷ� �Է��ϼ���.");
			obj.value = str;
			obj.focus();
		}
		
		return false;
	}

	if(!Number(arr[2]))
	{
		if(msg)
		{
			window.alert("[" + arr[2] + "]��(��) ���ڰ� �ƴմϴ�. " + msg + "�� �޹�ȣ�� ���ڷ� �Է��� �ּ���.");
			obj.value = str;
			obj.focus();
		}
	
		return false;
	}

	if(!ValidSize(arr[2], 4, 4))
	{
		if(msg)
		{
			window.alert(msg + "�� �޹�ȣ�� " + 4 + "�ڷ� �Է��ϼ���.");
			obj.value = str;
			obj.focus();
		}
		
		return false;
	}

	return true;
}

/**
 * ��ü�� ���� ��ȭ��ȣ�� �������� �˻��Ѵ�.
 * 
 * @param obj1	��ü1 ID
 * @param obj2	��ü2 ID
 * @param obj3	��ü3 ID
 * @param msg	��ü �̸�
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

	if(!isNumber(obj1, msg + "�� ��Ż��ȣ") || !isValidSize(obj1, 3, 3, msg + "�� ��Ż��ȣ"))
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
			window.alert(msg + "�� ��Ż��ȣ�� ��ȿ���� �ʽ��ϴ�. Ȯ�� �� �ٽ� �Է��ϼ���.");
			obj1.focus();	
		}

		return false;
	}

	if(!isNumber(obj2, msg + "�� ����ȣ") || !isValidSize(obj2, 3, 4, msg + "�� ����ȣ"))
	{
		return false;
	}

	if(!isNumber(obj3, msg + "�� �޹�ȣ") || !isValidSize(obj3, 4, 4, msg + "�� �޹�ȣ"))
	{
		return false;
	}

	return true;
}

/**
 * ��ü�� ���� ��ȭ��ȣ�� �������� �˻��Ѵ�.
 * 
 * @param obj	��ü ID
 * @param sep	���� ����
 * @param msg	��ü �̸�
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
			window.alert(msg + "��(��) [" + sep + "]�� �����ؾ� �մϴ�.");
			obj.value = str;
			obj.focus();
		}

		return false;
	}

	if(!Number(arr[0]))
	{
		if(msg)
		{
			window.alert("[" + arr[0] + "]��(��) ���ڰ� �ƴմϴ�. " + msg + "�� ��Ż��ȣ�� ���ڷ� �Է��� �ּ���.");
			obj.value = str;
			obj.focus();
		}

		return false;
	}

	if(!ValidSize(arr[0], 3, 3))
	{
		if(msg)
		{
			window.alert(msg + "�� ��Ż��ȣ�� " + 3 + "�ڷ� �Է��ϼ���.");
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
			window.alert(msg + "�� ��Ż��ȣ�� ��ȿ���� �ʽ��ϴ�. Ȯ�� �� �ٽ� �Է��ϼ���.");
			obj.value = str;
			obj.focus();	
		}

		return false;
	}

	if(!Number(arr[1]))
	{
		if(msg)
		{
			window.alert("[" + arr[1] + "]��(��) ���ڰ� �ƴմϴ�. " + msg + "�� ����ȣ�� ���ڷ� �Է��� �ּ���.");
			obj.value = str;
			obj.focus();
		}
	
		return false;
	}

	if(!ValidSize(arr[1], 3, 4))
	{
		if(msg)
		{
			window.alert(msg + "�� ����ȣ�� �ּ� " + 3 + "��, �ִ� " + 4 + "�ڷ� �Է��ϼ���.");
			obj.value = str;
			obj.focus();
		}
		
		return false;
	}

	if(!Number(arr[2]))
	{
		if(msg)
		{
			window.alert("[" + arr[2] + "]��(��) ���ڰ� �ƴմϴ�. " + msg + "�� �޹�ȣ�� ���ڷ� �Է��� �ּ���.");
			obj.value = str;
			obj.focus();
		}
	
		return false;
	}

	if(!ValidSize(arr[2], 4, 4))
	{
		if(msg)
		{
			window.alert(msg + "�� �޹�ȣ�� " + 4 + "�ڷ� �Է��ϼ���.");
			obj.value = str;
			obj.focus();
		}
		
		return false;
	}

	return true;
}

/**
 * CHCKBOX�� ��� üũ���·� �Ѵ�..
 * 
 * @param obj	��ü ID
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
 * CHCKBOX ��ü�� ��� �����Ѵ�.
 * 
 * @param obj	��ü ID
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
 * CHCKBOX ��ü�� ��� �����Ѵ�.
 * 
 * @param obj	��ü ID
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
 * CHCKBOX, RADIO ��ü�� üũ�Ǿ����� �˻��Ѵ�.
 * 
 * @param obj	��ü ID
 * @param msg	��ü �̸�
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
			//window.alert("[" + msg + "]��(��) �ϳ��̻� �����ؾ� �մϴ�.");
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
 * ��ü�� ���� ������ �������� �˻��Ѵ�.
 * 
 * @param obj	��ü ID
 * @param msg	��ü �̸�
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
			window.alert("[" + str + "]��(��) ��ȿ�� " + msg + "��(��) �ƴմϴ�.");
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
 * ��ü�� ���� ���� �������� �˻��Ѵ�.
 * 
 * @param obj	��ü ID
 * @param msg	��ü �̸�
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
			window.alert("[" + str + "]��(��) ��ȿ�� " + msg + "��(��) �ƴմϴ�.");
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
 * ��ü�� ���� �Ͽ� �������� �˻��Ѵ�.(�������)
 * 
 * @param obj	��ü1 ID
 * @param obj	��ü2 ID
 * @param obj	��ü3 ID
 * @param msg	��ü �̸�
 * @return 
 */

function isValidDay(obj1, obj2, obj3, msg)
{
	return isValidDate(obj1, obj2, obj3, msg);
}

/**
 * ��ü�� ���� �Ͽ� �������� �˻��Ѵ�.
 * 
 * @param obj	��ü1 ID
 * @param obj	��ü2 ID
 * @param obj	��ü3 ID
 * @param msg	��ü �̸�
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
			window.alert("[" + str3 + "]��(��) ��ȿ�� " + msg + "��(��) �ƴմϴ�.");
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
 * ��ü�� ���� �Ͽ� �������� �˻��Ѵ�.(�������)
 * 
 * @param obj	��ü ID
 * @param sep	���� ����
 * @param msg	��ü �̸�
 * @return 
 */
function isValidDay2(obj, sep, msg)
{
	return isValidDate2(obj, sep, msg);
}

/**
 * ��ü�� ���� �Ͽ� �������� �˻��Ѵ�.
 * 
 * @param obj	��ü ID
 * @param sep	���� ����
 * @param msg	��ü �̸�
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
				window.alert(msg + "��(��) [" + sep + "]�� �����ؾ� �մϴ�.");
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
			window.alert("[" + str + "]��(��) ��ȿ�� " + msg + "��(��) �ƴմϴ�.");
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
 * ��ü�� ���� ������ �������� �˻��Ѵ�.
 * 
 * @param obj1	��ü1 ID
 * @param obj2	��ü2 ID
 * @param msg	��ü �̸�
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
 * ��ü�� ���� ������ �������� �˻��Ѵ�.
 * 
 * @param obj	��ü ID
 * @param sep	���� ����
 * @param msg	��ü �̸�
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
				window.alert(msg + "��(��) [" + sep + "]�� �����ؾ� �մϴ�.");
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
			window.alert("[" + str + "]��(��) ��ȿ�� " + msg + "��(��) �ƴմϴ�.");
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

    if(str.indexOf(".") == -1) // �Ҽ����� ������� -> ,(Comma)�� �����Ѵ�
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
    else // �Ҽ����� ���� ���
    {
        var strMainLength = strMain.length;
        if(strMainLength <= size) // �Ҽ����� �ְ� �Ҽ����̻��� 3�ڸ��� �ʰ����� ������� -> �Ҽ����̻� �״�� ����
        {
            strTemp = strMain;
        }
        else // �Ҽ����� �ְ� �Ҽ����̻��� 3�ڸ��� �ʰ��� ��� -> �Ҽ����̻� ,(Comma)�� �����Ѵ�.
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
				window.alert("[" + str.charAt(i) + "]��(��) ���ڰ� �ƴմϴ�. " + msg + "��(��) ���ڷ� �Է��� �ּ���.");
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
			window.alert(msg + "��(��) [" + sep + "]�� �����ؾ� �մϴ�.");
			return false;
		}		
	}

	if(!Number(arr[0]))
	{
		if(msg)
		{
			window.alert("[" + arr[0] + "]��(��) ���ڰ� �ƴմϴ�. " + msg + "�� ��Ż��ȣ�� ���ڷ� �Է��� �ּ���.");
			return false;
		}		
	}

	if(!ValidSize(arr[0], 3, 3))
	{
		if(msg)
		{
			window.alert(msg + "�� ��Ż��ȣ�� " + 3 + "�ڷ� �Է��ϼ���.");
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
			window.alert(msg + "�� ��Ż��ȣ�� ��ȿ���� �ʽ��ϴ�. Ȯ�� �� �ٽ� �Է��ϼ���.");
			return false;
		}		
	}

	if(!Number(arr[1]))
	{
		if(msg)
		{
			window.alert("[" + arr[1] + "]��(��) ���ڰ� �ƴմϴ�. " + msg + "�� ����ȣ�� ���ڷ� �Է��� �ּ���.");
			return false;
		}		
	}

	if(!ValidSize(arr[1], 3, 4))
	{
		if(msg)
		{
			window.alert(msg + "�� ����ȣ�� �ּ� " + 3 + "��, �ִ� " + 4 + "�ڷ� �Է��ϼ���.");
			return false;
		}		
	}

	if(!Number(arr[2]))
	{
		if(msg)
		{
			window.alert("[" + arr[2] + "]��(��) ���ڰ� �ƴմϴ�. " + msg + "�� �޹�ȣ�� ���ڷ� �Է��� �ּ���.");
			return false;
		}		
	}

	if(!ValidSize(arr[2], 4, 4))
	{
		if(msg)
		{
			window.alert(msg + "�� �޹�ȣ�� " + 4 + "�ڷ� �Է��ϼ���.");
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
			window.alert(msg1+"��(��) ��¥ ������ �ƴմϴ� .[ex)2010-01-01]");
			return false;		
		}
		
		if(arr2.length != 3)
		{
			window.alert(msg2+"��(��) ��¥ ������ �ƴմϴ� .[ex)2010-01-01]");
			return false;		
		}
	
		if(!Number(arr1[0]))
		{
			window.alert("[" + arr1[0] + "]�� ���ڰ� �ƴմϴ�. " + msg1 + "�� ������ ���ڷ� �Է��� �ּ���.");
			return false;
		}else if(!Number(arr1[1])){
			window.alert("[" + arr1[1] + "]�� ���ڰ� �ƴմϴ�. " + msg1 + "�� ���� ���ڷ� �Է��� �ּ���.");
			return false;
		}else if(!Number(arr1[2])){
			window.alert("[" + arr1[2] + "]�� ���ڰ� �ƴմϴ�. " + msg1 + "�� ���� ���ڷ� �Է��� �ּ���.");
			return false;
		}
		
		if(!Number(arr2[0]))
		{
			window.alert("[" + arr2[0] + "]�� ���ڰ� �ƴմϴ�. " + msg2 + "�� ������ ���ڷ� �Է��� �ּ���.");
			return false;
		}else if(!Number(arr2[1])){
			window.alert("[" + arr2[1] + "]�� ���ڰ� �ƴմϴ�. " + msg2 + "�� ���� ���ڷ� �Է��� �ּ���.");
			return false;
		}else if(!Number(arr2[2])){
			window.alert("[" + arr2[2] + "]�� ���ڰ� �ƴմϴ�. " + msg2 + "�� ���� ���ڷ� �Է��� �ּ���.");
			return false;
		}
		
		if(arr1[0] > arr2[0])
		{
			window.alert(msg1 + "�� "+ msg2 + "���� �������̾��  �մϴ�.");
			return false;
		}else if(arr1[0] == arr2[0]){
			if(arr1[1] > arr2[1]){
				window.alert(msg1 + "�� "+ msg2 + "���� �������̾��  �մϴ�.");
				return false;
			}else if(arr1[1] == arr2[1]){
				if(arr1[2] > arr2[2]){
					window.alert(msg1 + "�� "+ msg2 + "���� �������̾��  �մϴ�.");
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
				window.alert(msg1+"��(��) ��¥ ������ �ƴմϴ� .[ex)2010-01-01]");
				return false;		
			}
			
			if(arr2.length != 3)
			{
				window.alert(msg2+"��(��) ��¥ ������ �ƴմϴ� .[ex)2010-01-01]");
				return false;		
			}
		
			if(!Number(arr1[0]))
			{
				window.alert("[" + arr1[0] + "]�� ���ڰ� �ƴմϴ�. " + msg1 + "�� ������ ���ڷ� �Է��� �ּ���.");
				return false;
			}else if(!Number(arr1[1])){
				window.alert("[" + arr1[1] + "]�� ���ڰ� �ƴմϴ�. " + msg1 + "�� ���� ���ڷ� �Է��� �ּ���.");
				return false;
			}else if(!Number(arr1[2])){
				window.alert("[" + arr1[2] + "]�� ���ڰ� �ƴմϴ�. " + msg1 + "�� ���� ���ڷ� �Է��� �ּ���.");
				return false;
			}
			
			if(!Number(arr2[0]))
			{
				window.alert("[" + arr2[0] + "]�� ���ڰ� �ƴմϴ�. " + msg2 + "�� ������ ���ڷ� �Է��� �ּ���.");
				return false;
			}else if(!Number(arr2[1])){
				window.alert("[" + arr2[1] + "]�� ���ڰ� �ƴմϴ�. " + msg2 + "�� ���� ���ڷ� �Է��� �ּ���.");
				return false;
			}else if(!Number(arr2[2])){
				window.alert("[" + arr2[2] + "]�� ���ڰ� �ƴմϴ�. " + msg2 + "�� ���� ���ڷ� �Է��� �ּ���.");
				return false;
			}
			
			if(arr1[0] > arr2[0])
			{
				window.alert(msg2 + "�� "+ msg1 + "���� �������̾�� �մϴ�.");
				return false;
			}else if(arr1[0] == arr2[0]){
				if(arr1[1] > arr2[1]){
					window.alert(msg2 + "�� "+ msg1 + "���� �������̾��  �մϴ�.");
					return false;
				}else if(arr1[1] == arr2[1]){
					if(arr1[2] > arr2[2]){
						window.alert(msg2 + "�� "+ msg1 + "���� �������̾��  �մϴ�.");
						return false;
					
					}
				}
			}		
			return true;
		}
	}