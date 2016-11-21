package  fatca.common.decorator;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;

import org.displaytag.decorator.TableDecorator;

import fatca.common.util.FTCStringUtil;

/**
 * @File Name    : TableFormat.java
 * @Package Name : fatca.common.decorator;
 * @author       : 
 * @Description  : Table ����ũó��
 * @History      : 
 */
public class FTCTableFormat extends TableDecorator {
//	
//	> /* ����ŷ ���� */   
//	> �Ǹ��ȣ(�ֹι�ȣ):���ڸ� 6�ڸ� 
//	> ����(��ǥ�ڸ�):�̸��� ù����
	//�ܱ���� �� Į�� ����ŷ
//	> ��ȭ��ȣ (����,����,�޴���)	: ���ڸ� 4�ڸ� 
//	> �ּ�:�� 8�ڸ� �ʰ� �ι� 
//	> IP�ּ� : ��4�ڸ� 
//	> �̸���ID : �� 4�ڸ� 
//	> ���¹�ȣ : �� 4�ڸ� 
	
	//�ڵ�����ȣ(111-1111-1111) ���
	//��¥ (2111-11-11)
	//�ݾ�(#,###,###)
	//����ڹ�ȣ()
	
	
	
    public FTCTableFormat() {
        super();
    }
    
	/**
	 *  �Ǹ��ȣ(�ֹι�ȣ):���ڸ� 6�ڸ� ����ŷ
	 * 
	 * @param val
	 * @return val
	 */
    public String convertRnnoMsk(String val) {      
    	if(val!=null && val.length()>8){
    		return FTCStringUtil.subString(val,0,6)+"-"+FTCStringUtil.subString(val,7,8)+"******";
    	}	
    	return val;
    }
    
	/**
	 *  ����(��ǥ�ڸ�):�̸��� ù���� ����ŷ
	 * 
	 * @param val
	 * @return val
	 */
    public String convertNameMsk(String val) {   
    	if(val!=null){
    		return "*"+FTCStringUtil.subString(val,1);	
    	}	
    	return val;    		
    }
    

	/**
	 *  ����(��ǥ�ڸ�):�̸��� ù���� ����ŷ �� 4���ڷ� ���̱�
	 * 
	 * @param val
	 * @return val
	 */
    public String convertNameMskLen(String val) {   
    	if(val!=null){
    		if(val.length()>4){
    			return "*"+FTCStringUtil.subString(val,1,4)+"...";
    		}else{
    			return "*"+FTCStringUtil.subString(val,1);
    		}
    	}	
    	return val;    		
    }

	/**
	 *  ��ȭ��ȣ (����,����,�޴���)	: ���ڸ� 4�ڸ� ����ŷ
	 * 
	 * @param val
	 * @return val
	 */
    public String convertTelMsk(String val) {   
    	if(val!=null){
    		String[] tell = FTCStringUtil.getPhoneNo(val); //���Խ� üũ�� �ڸ��� ǥ������ �� �Ŀ� Maskó��
    		return tell[0]+"-"+tell[1]+"-****";		
    	}	
    	return val;    		
    }
    
    /**
	 *  �ּ�:�� 8�ڸ� �ʰ� �ι� ����ŷ
	 * 
	 * @param val
	 * @return val
	 */
    public String convertAddressMsk(String val) {   
    	if(val!=null){
    		return FTCStringUtil.subString(val,0,8)+"******";	
    	}	
    	return val;    		
    }

    /**
	 *  �̸���ID : �� 4�ڸ� ����ŷ
	 * 
	 * @param val
	 * @return val
	 */
    public String convertEmailMsk(String val) {   
    	if(val!=null){
    		return FTCStringUtil.subString(val,0,(val.length()-4))+"****";
    	}	
    	return val;    	
    }


    /**
	 *  ���¹�ȣ : �� 4�ڸ�  ����ŷ
	 * 
	 * @param val
	 * @return val
	 */
    public String convertAccountMsk(String val) {   
    	if(val!=null){
    		return FTCStringUtil.subString(val,0,(val.length()-4))+"****";
    	}	
    	return val;    	
    }


    /**
	 *  ��¥ (2111-11-11) ����ŷ
	 * 
	 * @param val
	 * @return val
	 */
    public String convertDateMsk(String val) {   
    	if(val!=null){
    		return FTCStringUtil.subString(val,0,4)+"-"+FTCStringUtil.subString(val,4,6)+"-"+FTCStringUtil.subString(val,6,8);
    	}	
    	return val;    	
    }


    /**
	 *  �ݾ�(#,###,###) ����ŷ
	 * 
	 * @param val
	 * @return val
	 */
    public String convertAmountFmt(BigDecimal val) {   
    	if(val!=null){
    		DecimalFormat moneyFormat =  new DecimalFormat("#,###,###"); //$NON-NLS-1$
        	return moneyFormat.format(val);	
    	}	
    	return "0";    		
    }
    
    /**
	 *  �ݾ�(#,###,###) ����ŷ
	 * 
	 * @param val
	 * @return val
	 */
    public String convertAmountUsFmt(BigDecimal val) {   
    	if(val!=null){
    		DecimalFormat moneyFormat =  new DecimalFormat("#,###,##0.00"); //$NON-NLS-1$
        	return moneyFormat.format(val);	
    	}	
    	return "0";    		
    }
    
    
    
    
    //�ǻ�Ϸ���
    public String getFatcaAcipComptDt() {   
    	Map object = (Map) getCurrentRowObject();    
    	String val = (String)object.get("fatcaAcipComptDt");
    	    	
    	return this.convertDateMsk(val);
    	
    }
   
    //������
    public String getCustNm() {   
    	Map object = (Map) getCurrentRowObject();    
    	String val = (String)object.get("custNm");
    	
    	return this.convertNameMsk(val);	
    }
   
    //����ȣ
    public String getRnno() {   
    	Map object = (Map) getCurrentRowObject();    
    	String val = (String)object.get("rnno");
    	
    	return this.convertRnnoMsk(val);	
    }
   
    //��ȭ��ȣ
    public String getSmsNtiMpno() {   
    	Map object = (Map) getCurrentRowObject();    
    	String val = (String)object.get("smsNtiMpno");
    	
    	return this.convertTelMsk(val);	
    }
   
    //���¹�ȣ
    public String getAcno() {   
    	Map object = (Map) getCurrentRowObject();    
    	String val = (String)object.get("acno");

    	return this.convertAccountMsk(val);	
    }
   
    //�ܾ�
    public String getAcntBalamt() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("acntBalamt");
    	
    	return this.convertAmountFmt(val);
    }
   
    //�����Ѿ�
    public String getNtrstAmt() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("ntrstAmt");
    	
    	return this.convertAmountFmt(val);
    }
   
    //����Ѿ�
    public String getShramt() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("shramt");
    	
    	return this.convertAmountFmt(val);
    }
   
    //��Ÿ�����Ѿ�
    public String getEtcm() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("etcm");
    	
    	return this.convertAmountFmt(val);
    }
   
    //�Ѱŷ�����
    public String getTotNcmRbam() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("totNcmRbam");
    	
    	return this.convertAmountFmt(val);
    }
   
    //�ջ�ݾ� ��ȭ
    public String getWcCnvtBalamt() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("wcCnvtBalamt");
    	
    	return this.convertAmountFmt(val);
    }
   
    //�ջ�ݾ� ��ȭ
    public String getUsdCnvtBalamt() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("usdCnvtBalamt");
    	
    	return this.convertAmountUsFmt(val);
    }

    
    //EIN
    public String getFatcaTxpayrNo() {   
    	Map object = (Map) getCurrentRowObject();    
    	String val = (String)object.get("fatcaTxpayrNo");

    	return this.convertRnnoMsk(val);	
    }
    
    //TIN
    public String getPerTxpayrNo() {   
    	Map object = (Map) getCurrentRowObject();    
    	String val = (String)object.get("perTxpayrNo");
    	
    	return this.convertRnnoMsk(val);	
    }
    
    //�Ѱ�
    public String getTotCnt() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("totCnt");
    	
    	return this.convertAmountFmt(val);
    }

	
    /* ���� �ҽ����� ��� �ؾߵ��� ���� ���� ����.  
    //������
    public String getSamePer() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("samePer");

    	return this.convertAmountUsFmt(val);	
    }
       	*/
    
    //�ǻ� ���
    public String getTargetAcip() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("targetAcip");
    	
    	return this.convertAmountFmt(val);
    }
    
    //�ǻ� �Ϸ�
    public String getComptAcip() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("comptAcip");
    	
    	return this.convertAmountFmt(val);
    }
    
    //���� ���
    public String getReportAcip() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("reportAcip");
    	
    	return this.convertAmountFmt(val);
    }
    

    //���� ���
    public String getCprtAcip() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("cprtAcip");
    	
    	return this.convertAmountFmt(val);
    }
   
    //������ ���
    public String getNoCprtAcip() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("noCprtAcip");
    	
    	return this.convertAmountFmt(val);
    }
   
    //�񺸰� ���
    public String getNoReportAcip() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("noReportAcip");
    	
    	return this.convertAmountFmt(val);
    }
    
    //��Ȯ������ ���
    public String getNoDecisionAcip() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("noDecisionAcip");
    	
    	return this.convertAmountFmt(val);
    }
   
   
    //�� ��
    public String getTotCust() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("totCust");
    	
    	return this.convertAmountFmt(val);
    }
   
    //�Ϸ� �� ��
    public String getTotComp() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("totComp");
    	
    	return this.convertAmountFmt(val);
    }
    
    //�̿Ϸ� �� ��
    public String getTotUncomp() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("totUncomp");
    	
    	return this.convertAmountFmt(val);
    }
    
    //���� �� ��
    public String getTotReport() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("totReport");
    	
    	return this.convertAmountFmt(val);
    }

    
    //���� �� ��
    public String getComCust() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("comCust");
    	
    	return this.convertAmountFmt(val);
    }
   
    //���� �Ϸ� �� ��
    public String getComComp() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("comComp");
    	
    	return this.convertAmountFmt(val);
    }
    
    //���� �̿Ϸ� �� ��
    public String getComUncomp() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("comUncomp");
    	
    	return this.convertAmountFmt(val);
    }
    
    //���� ���� �� ��
    public String getComReport() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("comReport");
    	
    	return this.convertAmountFmt(val);
    }

    
    //���� �� ��
    public String getPerCust() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("perCust");
    	
    	return this.convertAmountFmt(val);
    }
   
    //���� �Ϸ� �� ��
    public String getPerComp() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("perComp");
    	
    	return this.convertAmountFmt(val);
    }
    
    //���� �̿Ϸ� �� ��
    public String getPerUncomp() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("perUncomp");
    	
    	return this.convertAmountFmt(val);
    }
    
    //���� ���� �� ��
    public String getPerReport() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("perReport");
    	
    	return this.convertAmountFmt(val);
    }
}
