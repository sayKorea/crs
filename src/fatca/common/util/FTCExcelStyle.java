package fatca.common.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


/**
 * @File Name    : ExcelStyle.java
 * @Package Name : fatca.common.util
 * @author       : 
 * @Description  : ���� �ٿ�ε� ��Ÿ��
 * @History      : 
 */
public class FTCExcelStyle {
	static HSSFWorkbook WB = null;
	/*�����Ÿ��*/
	public HSSFCellStyle headerStyle = null;
	/*���뽺Ÿ��*/
	public HSSFCellStyle contentStyle = null;
	/*�ݾ׽�Ÿ��*/
	public HSSFCellStyle moneyStyle = null;
	/*�ݾ׽�Ÿ��(�Ҽ�2�ڸ�)*/
	public HSSFCellStyle point2Style = null;
	/*������ ���� ��Ÿ��*/
	public HSSFCellStyle rightAlignStle = null;
	/*��� ���� ��Ÿ��*/
	public HSSFCellStyle centerAlignStyle = null;
	/*��� ����Į�� ��Ÿ��*/
	public HSSFCellStyle centerRedAlignStyle = null;
	

	/**
	 * ���� ��Ÿ��
	 * 
	 * @param wb ������ũ��
	 */
	public FTCExcelStyle(HSSFWorkbook wb) {
		WB = wb;

		HSSFFont headerFont = WB.createFont();
		headerFont.setFontName("����");
		headerFont.setFontHeightInPoints((short)10);
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		this.headerStyle = WB.createCellStyle();
		this.headerStyle.setFont(headerFont);
		
		this.headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		this.headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		//this.headerStyle.setFillForegroundColor(WB.getCustomPalette().findSimilarColor((byte)0x00, (byte)0xb0, (byte)0xff).getIndex());
		this.headerStyle.setFillForegroundColor(WB.getCustomPalette().findSimilarColor(228, 235, 240).getIndex());
		this.headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		HSSFFont contentFont = WB.createFont();
		contentFont.setFontName("����");
		contentFont.setFontHeightInPoints((short)9);
		contentFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		HSSFFont contentRedFont = WB.createFont();
		contentRedFont.setFontName("����");
		contentRedFont.setFontHeightInPoints((short)9);
		contentRedFont.setColor(HSSFFont.COLOR_RED);
		contentRedFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		
		this.contentStyle = WB.createCellStyle();
		this.contentStyle.setFont(contentFont);

		this.moneyStyle = WB.createCellStyle();
		this.moneyStyle.setFont(contentFont);
		this.moneyStyle.setDataFormat(WB.createDataFormat().getFormat("###,###,###,##0"));
		this.moneyStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		
		this.point2Style = WB.createCellStyle();
		this.point2Style.setFont(contentFont);
		this.point2Style.setDataFormat(WB.createDataFormat().getFormat("###,###,###,##0.00"));
		this.point2Style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		
		this.rightAlignStle = WB.createCellStyle();
		this.rightAlignStle.setFont(contentFont);
		this.rightAlignStle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

		this.centerAlignStyle = WB.createCellStyle();
		this.centerAlignStyle.setFont(contentFont);
		this.centerAlignStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		this.centerRedAlignStyle = WB.createCellStyle();
		this.centerRedAlignStyle.setFont(contentRedFont);
		this.centerRedAlignStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	}

}
