package fatca.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


/**
 * ����, �ð��� ���õ� �ټ��� ���
 * <p>
 * �������� ������ ����
 * <pre>
 *	 Symbol   Meaning                 Presentation        Example
 *	 ------   -------                 ------------        -------
 *	 G        era designator          (Text)              AD
 *	 y        year                    (Number)            1996
 *	 M        month in year           (Text & Number)     July & 07
 *	 d        day in month            (Number)            10
 *	 h        hour in am/pm (1~12)    (Number)            12
 *	 H        hour in day (0~23)      (Number)            0
 *	 m        minute in hour          (Number)            30
 *	 s        second in minute        (Number)            55
 *	 S        millisecond             (Number)            978
 *	 E        day in week             (Text)              Tuesday
 *	 D        day in year             (Number)            189
 *	 F        day of week in month    (Number)            2 (2nd Wed in July)
 *	 w        week in year            (Number)            27
 *	 W        week in month           (Number)            2
 *	 a        am/pm marker            (Text)              PM
 *	 k        hour in day (1~24)      (Number)            24
 *	 K        hour in am/pm (0~11)    (Number)            0
 *	 z        time zone               (Text)              Pacific Standard Time
 *	 '        escape for text         (Delimiter)
 *	 ''       single quote            (Literal)           '
 *
 *  [����]
 *	 Format Pattern                         Result
 *	 --------------                         -------
 *	 "yyyyMMdd"                        ->>  19960710
 *	 "yyyy-MM-dd"                      ->>  1996-07-10
 *	 "HHmmss"                          ->>  210856
 *	 "HH:mm:ss"                        ->>  21:08:56
 *	 "hh:mm:ss"                        ->>  09:08:56
 *	 "yyyy.MM.dd hh:mm:ss"             ->>  1996.07.10 15:08:56
 *	 "EEE, MMM d, ''yy"                ->>  Wed, July 10, '96
 *	 "h:mm a"                          ->>  12:08 PM
 *	 "hh 'o''clock' a, zzzz"           ->>  12 o'clock PM, Pacific Daylight Time
 *	 "K:mm a, z"                       ->>  0:00 PM, PST
 *	 "yyyyy.MMMMM.dd GGG hh:mm aaa"    ->>  1996.July.10 AD 12:08 PM
 *
 * </pre>
 * ��Ÿ �ڼ��� ���� <a href="http://java.sun.com/j2se/1.3/docs/api/java/text/SimpleDateFormat.html">SimpleDateFormat</a>
 * Class API Document �� �����Ұ�
 */

/**
 * @File Name    : DateUtil.java
 * @Package Name : fatca.common.util;
 * @author       : 
 * @Description  : ����, �ð��� ���õ� �ټ��� ���
 * @History      : 
 */
public class FTCDateUtil{
	/**
	 * ���ڿ��� ���� ���ڰ����� ����
	 *
	 * @param textDate ���ڰ��� ���� 8�ڸ� ���ڿ� ��) '20010806'
	 * @return ���ڰ��̸� true, �ƴϸ� false
	 */
	public static boolean isDate(String textDate) {
		try {
			dateCheck(textDate);
		} catch ( Exception e ) {
			return false;
		}
		return true;
   	}

	/**
	 * �������� Date Value Check�� ��
	 * @param textDate
	 */
	private static void dateCheck(String textDate) throws Exception {
		if ( textDate.length() != 8 ){
			throw new Exception("[" + textDate + "] is not date value");			
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		try {
			sdf.setLenient( false );
			sdf.parse(textDate);
		}catch(Exception e) {
			throw new Exception("[" + textDate + "] is not date value");
		}
		
   	}

	/**
	 * ���ڰ��� ���� 8�ڸ� ���ڿ��� Calendar ��ü�� ����
	 * @param textDate ���ڰ��� ���� 8�ڸ� ���ڿ� ��) '20010806'
	 * @return Calendar ��ü
	 */
    public static Calendar getCalendar(String textDate) throws Exception {
    	//dateCheck(textDate);
    	int year = Integer.parseInt(textDate.substring(0,4));
    	int month = Integer.parseInt(textDate.substring(4,6));
    	int date = Integer.parseInt(textDate.substring(6,8));
    	
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
		
    	if (textDate.length() == 14) {
    		int hour = Integer.parseInt(textDate.substring(8,10));
			int minute = Integer.parseInt(textDate.substring(10,12));
			int second = Integer.parseInt(textDate.substring(12,14));
			cal.set(year,month-1,date,hour,minute,second);
    	} else {
			cal.set(year,month-1,date);	
    	}
    	
    	
        return cal;
    }

	/**
	 * ���ڰ��� ���� 8�ڸ� ���ڿ��� Date ��ü�� ����
	 * @param textDate ���ڰ��� ���� 8�ڸ� ���ڿ� ��) '20010806'
	 * @return Date ��ü
	 */
    public static Date getDate(String textDate) throws Exception{
        return getCalendar(textDate).getTime();
    }

	/**
	 * �־��� Date ��ü�� �̿��Ͽ� �־��� ���� ��¥���� ���ڿ��� ����.
	 * @param date ���ϴ� ������ Date ��ü
	 * @param pattern ���ϴ� ���� ����
	 * @return �־��� ������ ����
	 */
    public static String getDateString(Date date, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

	/**
	 * �־��� Date ��ü�� �̿��Ͽ� �⺻��¥��('yyyyMMdd')�� ���ڿ��� ����.
	 * @param date ���ϴ� ������ Date ��ü
	 * @return �־��� ������ ����
	 */
    public static String getDateString(Date date) throws Exception{
        return getDateString(date, "yyyyMMdd");
    }

	/**
	 * �־��� ���ڸ� �̿��Ͽ� �־��� ���� ��¥���� ���ڿ��� ����.
	 * @param textDate ���ڰ��� ���� 8�ڸ� ���ڿ� ��) '20010806'
	 * @param pattern ���ϴ� ���� ����
	 * @return �־��� ������ ����
	 */
    public static String getDateString(String textDate, String pattern)  throws Exception{
    	String date = null;
    	if (textDate != null && !"".equals(textDate)) {
    		date = getDateString(getDate(textDate), pattern);
    	} else {
    		date = "";
    	}
        return date;
    }



	/**
	 * �־��� ���� ��¥�� �ý������ڸ� ����
	 * @param pattern ���ϴ� ���� ����
	 * @return �ý��� ����
	 */
    public static String getToday(String pattern){
        return getDateString(new Date(), pattern);
    }

	/**
	 * �⺻����('yyyyMMdd') ��¥�� �ý������ڸ� ����
	 * @param pattern ���ϴ� ���� ����
	 * @return �⺻��('yyyyMMdd')�� �ý��� ����
	 */
    public static String getToday(){
        return getToday("yyyyMMdd");
    }

	/**
	 * �⺻����('HHmmss') ��¥�� �ý��۽ð��� ����
	 * @param pattern ���ϴ� ���� ����
	 * @return �⺻��('HHmmss')�� �ý��� �ð�
	 */
    public static String getTime(){
        return getToday("HHmmss");
    }

	/**
	 * ������ �и��ڸ� �̿��� �ý������ڸ� ����
	 * @param delmt ���ϴ� �и��� ���� ��) ':', '/' ...
	 * @return �и��ڰ� ���Ե� �ý��� �ð�
	 */
    public static String getTime(char delmt){
        return getToday("HH" + delmt + "mm" + delmt + "ss");
    }

	/**
	 * ������ ���ڷ� ���� �����Ⱓ ������ ���ڸ� ����
	 * @param fromDate ��������
	 * @param termDays ���ϴ� �Ⱓ
	 * @param both ����ֱ� ����
	 * @return �����Ⱓ ������ ���� ('yyyyMMdd')
	 */
    public static String getToDate(String fromDate, int termDays, boolean both) throws Exception{
    	if (both) {
    		termDays = termDays - 1;
    	}
    	
    	Calendar cal = getCalendar(fromDate);
    	cal.add(Calendar.DATE, termDays);
        return getDateString(cal.getTime(),"yyyyMMdd");
    }
    
    //���� ���ں��� ������ �ϼ��� �� ���� ����
    public static String getCondDate(int termDays) throws Exception{
    	Calendar cal = getCalendar(getToday());
    	cal.add(Calendar.DATE, termDays*-1);
        return getDateString(cal.getTime(),"yyyy/MM/dd");
    }
    
    
    
    /**
     * ������ �������� ���� �����Ⱓ�� ���� ���´�.
     * @param fromDate ���� ����
     * @param termMonths ���ϴ� �Ⱓ
     * @param both ����ֱ� ����
     * @return �����Ⱓ ������ ���� ('yyyyMMdd')
     * @throws Exception
     */
    public static String getToMonth(String fromDate, int termMonths, boolean both) throws Exception {
		if (both){
			termMonths = termMonths - 1;
		}
		Calendar cal = getCalendar(fromDate);
		cal.add(Calendar.MONTH, termMonths);
		return getDateString(cal.getTime(),"yyyyMMdd");
    }
    
    /**
     * �ش�Ǵ� ���� ������ ���ڸ� ���´�.
     * @param date ���� ����
     * @return ���������� ������ �� ����
     * @throws Exception
     */
    public static String getLastDayOfMonth(String date) throws Exception {
    	Calendar cal = getCalendar(date);
    	cal.roll(Calendar.MONTH, true);
    	
    	String firstDate = getDateString(cal.getTime(), "yyyyMM01");
    	Calendar cal2 = getCalendar(firstDate);
    	cal2.add(Calendar.DATE, -1);
    	
    	return getDateString(cal2.getTime(), "yyyyMMdd");
    	
    }

	/**
	 * ������ ���ڷ� ���� �����Ⱓ ������ ���ڸ� ����ֱ������� ����.
	 * @param fromDate ��������
	 * @param termDays ���ϴ� �Ⱓ
	 * @return �����Ⱓ ������ ���� ('yyyyMMdd')
	 */
    public static String getToDate(String fromDate, int termDays) throws Exception{
        return getToDate(fromDate, termDays, false);
    }

	/**
	 * �����Ϸκ��� �����ϱ����� �ϼ��� ����
	 * @param fromDate ��������
	 * @param toDate ��������
	 * @param both ����ֱ� ����
	 * @return �������ڷ� ���� �����ϱ����� �ϼ�
	 */
    public static int getDiffDays(Date fromDate, Date toDate, boolean both){
		long diffDays = toDate.getTime() - fromDate.getTime();
		long days = diffDays / (24 * 60 * 60 * 1000);
		if (both){
			if (days >= 0){
				days += 1; 
			}else{
				days -= 1;
			}
		}
		
		return new Long(days).intValue();
    }

	/**
	 * �����Ϸκ��� �����ϱ����� �ϼ��� ����ֱ�� �����.
	 * @param fromDate ��������
	 * @param toDate ��������
	 * @return �������ڷ� ���� �����ϱ����� �ϼ�
	 */
    public static int getDiffDays(Date fromDate, Date toDate){
		return getDiffDays(fromDate, toDate, false);
    }

	/**
	 * �����Ϸκ��� �����ϱ����� �ϼ��� ����
	 * @param fromDate ��������
	 * @param toDate ��������
	 * @param both ����ֱ� ����
	 * @return �������ڷ� ���� �����ϱ����� �ϼ�
	 */
    public static int getDiffDays(String fromDate, String toDate, boolean both) throws Exception{
		return getDiffDays(getDate(fromDate),getDate(toDate), both);
    }

	/**
	 * �����Ϸκ��� �����ϱ����� �ϼ��� ����ֱ�� �����.
	 * @param fromDate ��������
	 * @param toDate ��������
	 * @return �������ڷ� ���� �����ϱ����� �ϼ�
	 */
    public static int getDiffDays(String fromDate, String toDate) throws Exception{
		return getDiffDays(getDate(fromDate),getDate(toDate), false);
    }
    
    public static String getCurrent(){
        return getDateString(new Date(), "yyyyMMddhhmmss");
    }
    
}