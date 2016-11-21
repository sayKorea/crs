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
 * @Description  : Table 마스크처리
 * @History      : 
 */
public class FTCTableFormat extends TableDecorator {
//	
//	> /* 마스킹 기준 */   
//	> 실명번호(주민번호):뒷자리 6자리 
//	> 성명(대표자명):이름의 첫글자
	//외국사람 성 칼럼 마스킹
//	> 전화번호 (자택,직장,휴대폰)	: 뒷자리 4자리 
//	> 주소:앞 8자리 초과 부문 
//	> IP주소 : 뒷4자리 
//	> 이메일ID : 뒷 4자리 
//	> 계좌번호 : 뒷 4자리 
	
	//핸드폰번호(111-1111-1111) 고려
	//날짜 (2111-11-11)
	//금액(#,###,###)
	//사업자번호()
	
	
	
    public FTCTableFormat() {
        super();
    }
    
	/**
	 *  실명번호(주민번호):뒷자리 6자리 마스킹
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
	 *  성명(대표자명):이름의 첫글자 마스킹
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
	 *  성명(대표자명):이름의 첫글자 마스킹 및 4글자로 줄이기
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
	 *  전화번호 (자택,직장,휴대폰)	: 뒷자리 4자리 마스킹
	 * 
	 * @param val
	 * @return val
	 */
    public String convertTelMsk(String val) {   
    	if(val!=null){
    		String[] tell = FTCStringUtil.getPhoneNo(val); //정규식 체크후 자리수 표현까지 한 후에 Mask처리
    		return tell[0]+"-"+tell[1]+"-****";		
    	}	
    	return val;    		
    }
    
    /**
	 *  주소:앞 8자리 초과 부문 마스킹
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
	 *  이메일ID : 뒷 4자리 마스킹
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
	 *  계좌번호 : 뒷 4자리  마스킹
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
	 *  날짜 (2111-11-11) 마스킹
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
	 *  금액(#,###,###) 마스킹
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
	 *  금액(#,###,###) 마스킹
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
    
    
    
    
    //실사완료일
    public String getFatcaAcipComptDt() {   
    	Map object = (Map) getCurrentRowObject();    
    	String val = (String)object.get("fatcaAcipComptDt");
    	    	
    	return this.convertDateMsk(val);
    	
    }
   
    //고객성명
    public String getCustNm() {   
    	Map object = (Map) getCurrentRowObject();    
    	String val = (String)object.get("custNm");
    	
    	return this.convertNameMsk(val);	
    }
   
    //고객번호
    public String getRnno() {   
    	Map object = (Map) getCurrentRowObject();    
    	String val = (String)object.get("rnno");
    	
    	return this.convertRnnoMsk(val);	
    }
   
    //전화번호
    public String getSmsNtiMpno() {   
    	Map object = (Map) getCurrentRowObject();    
    	String val = (String)object.get("smsNtiMpno");
    	
    	return this.convertTelMsk(val);	
    }
   
    //계좌번호
    public String getAcno() {   
    	Map object = (Map) getCurrentRowObject();    
    	String val = (String)object.get("acno");

    	return this.convertAccountMsk(val);	
    }
   
    //잔액
    public String getAcntBalamt() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("acntBalamt");
    	
    	return this.convertAmountFmt(val);
    }
   
    //이자총액
    public String getNtrstAmt() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("ntrstAmt");
    	
    	return this.convertAmountFmt(val);
    }
   
    //배당총액
    public String getShramt() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("shramt");
    	
    	return this.convertAmountFmt(val);
    }
   
    //기타수익총액
    public String getEtcm() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("etcm");
    	
    	return this.convertAmountFmt(val);
    }
   
    //총거래가액
    public String getTotNcmRbam() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("totNcmRbam");
    	
    	return this.convertAmountFmt(val);
    }
   
    //합산금액 원화
    public String getWcCnvtBalamt() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("wcCnvtBalamt");
    	
    	return this.convertAmountFmt(val);
    }
   
    //합산금액 미화
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
    
    //총계
    public String getTotCnt() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("totCnt");
    	
    	return this.convertAmountFmt(val);
    }

	
    /* 현재 소스에서 어떻게 해야될지 정의 되지 않음.  
    //동일인
    public String getSamePer() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("samePer");

    	return this.convertAmountUsFmt(val);	
    }
       	*/
    
    //실사 대상
    public String getTargetAcip() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("targetAcip");
    	
    	return this.convertAmountFmt(val);
    }
    
    //실사 완료
    public String getComptAcip() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("comptAcip");
    	
    	return this.convertAmountFmt(val);
    }
    
    //보고 대상
    public String getReportAcip() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("reportAcip");
    	
    	return this.convertAmountFmt(val);
    }
    

    //협조 대상
    public String getCprtAcip() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("cprtAcip");
    	
    	return this.convertAmountFmt(val);
    }
   
    //비협조 대상
    public String getNoCprtAcip() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("noCprtAcip");
    	
    	return this.convertAmountFmt(val);
    }
   
    //비보고 대상
    public String getNoReportAcip() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("noReportAcip");
    	
    	return this.convertAmountFmt(val);
    }
    
    //미확정보고 대상
    public String getNoDecisionAcip() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("noDecisionAcip");
    	
    	return this.convertAmountFmt(val);
    }
   
   
    //총 고객
    public String getTotCust() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("totCust");
    	
    	return this.convertAmountFmt(val);
    }
   
    //완료 총 고객
    public String getTotComp() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("totComp");
    	
    	return this.convertAmountFmt(val);
    }
    
    //미완료 총 고객
    public String getTotUncomp() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("totUncomp");
    	
    	return this.convertAmountFmt(val);
    }
    
    //보고 총 고객
    public String getTotReport() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("totReport");
    	
    	return this.convertAmountFmt(val);
    }

    
    //법인 총 고객
    public String getComCust() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("comCust");
    	
    	return this.convertAmountFmt(val);
    }
   
    //법인 완료 총 고객
    public String getComComp() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("comComp");
    	
    	return this.convertAmountFmt(val);
    }
    
    //법인 미완료 총 고객
    public String getComUncomp() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("comUncomp");
    	
    	return this.convertAmountFmt(val);
    }
    
    //법인 보고 총 고객
    public String getComReport() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("comReport");
    	
    	return this.convertAmountFmt(val);
    }

    
    //개인 총 고객
    public String getPerCust() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("perCust");
    	
    	return this.convertAmountFmt(val);
    }
   
    //개인 완료 총 고객
    public String getPerComp() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("perComp");
    	
    	return this.convertAmountFmt(val);
    }
    
    //개인 미완료 총 고객
    public String getPerUncomp() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("perUncomp");
    	
    	return this.convertAmountFmt(val);
    }
    
    //개인 보고 총 고객
    public String getPerReport() {     
    	Map object = (Map) getCurrentRowObject();
    	BigDecimal val = (java.math.BigDecimal)object.get("perReport");
    	
    	return this.convertAmountFmt(val);
    }
}
