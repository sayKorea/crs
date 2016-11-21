package fatca.system.info;

import java.util.List;
import java.util.Map;

import fatca.common.base.FTCBaseInfo;

/**
 * @File Name    : UserInfo.java
 * @Package Name : fatca.system.info
 * @author       : 
 * @Description  : 유저 세션 정보
 * @History      : 
 */
public class FTCUserInfo extends FTCBaseInfo {
	
	private String userEnob;  
	private String userEnnm;
	private String slsBrno; 
	private String slsBrnm;
	private String fatcaAuthCd;
	private String fatcaAuthNm;
		
	private String selCrym;		//선택된 기준년월
	private String selSlsBrno; //선택된 영업점번호
	private String selSlsBrnm;	//선택된 영업점명
		
	private String selFatcaAcipRpprEnob;	//선택된 실사책임자
	private String selFatcaAcipRpprEnnm;	
	private String selFatcaAcipCgpEnob;	//선택된 실사담당자
	private String selFatcaAcipCgpEnnm;
	private String selFatcaCustRelMngrEnob;//선택된 고객관계관리자
	private String selFatcaCustRelMngrEnnm;
	
	private Map mnuAuthMap;
	private String congYn;
	
	private String serverType;
	
	private List branchComboRel;
			
	public Boolean getIsCong() {
		if("Y".equals(this.congYn)){ 
			return true;
		}else {
			return false;
		}
	}
	
	public Boolean getIsAdmin() {
		if("00".equals(this.fatcaAuthCd)){
			return true;
		}
		return false;
	}
	
	public Boolean getIsFatca() {
		if("00".equals(this.fatcaAuthCd) || "01".equals(this.fatcaAuthCd) || "02".equals(this.fatcaAuthCd) || "05".equals(this.fatcaAuthCd)){ 
			return true;
		}else {
			return false;
		}
	}	
	
	public List getBranchComboRel() {
		return this.branchComboRel;
	}

	public void setBranchComboRel(List branchComboRel) {
		this.branchComboRel = branchComboRel;
	}

	public void setMnuAuthMap(Map mnuAuthMap) {
		this.mnuAuthMap = mnuAuthMap;
	}
	
	public Map getMnuAuthMap() {
		return this.mnuAuthMap;
	}

	public String getUserEnob() {
		return this.userEnob;
	}

	public void setUserEnob(String userEnob) {
		this.userEnob = userEnob;
	}

	public String getUserEnnm() {
		return this.userEnnm;
	}

	public void setUserEnnm(String userEnnm) {
		this.userEnnm = userEnnm;
	}

	public String getSlsBrno() {
		return this.slsBrno;
	}

	public void setSlsBrno(String slsBrno) {
		this.slsBrno = slsBrno;
	}

	public String getSlsBrnm() {
		return this.slsBrnm;
	}

	public void setSlsBrnm(String slsBrnm) {
		this.slsBrnm = slsBrnm;
	}

	public String getFatcaAuthCd() {
		return this.fatcaAuthCd;
	}

	public void setFatcaAuthCd(String fatcaAuthCd) {
		this.fatcaAuthCd = fatcaAuthCd;
	}	

	public String getFatcaAuthNm() {
		return this.fatcaAuthNm;
	}

	public void setFatcaAuthNm(String fatcaAuthNm) {
		this.fatcaAuthNm = fatcaAuthNm;
	}
	
	public String getSelCrym() {
		return this.selCrym;
	}

	public void setSelCrym(String selCrym) {
		this.selCrym = selCrym;
	}

	public String getSelSlsBrno() {
		return this.selSlsBrno;
	}

	public void setSelSlsBrno(String selSlsBrno) {
		this.selSlsBrno = selSlsBrno;
	}

	public String getSelSlsBrnm() {
		return this.selSlsBrnm;
	}

	public void setSelSlsBrnm(String selSlsBrnm) {
		this.selSlsBrnm = selSlsBrnm;
	}

	public String getSelFatcaAcipRpprEnob() {
		return this.selFatcaAcipRpprEnob;
	}

	public void setSelFatcaAcipRpprEnob(String selFatcaAcipRpprEnob) {
		this.selFatcaAcipRpprEnob = selFatcaAcipRpprEnob;
	}

	public String getSelFatcaAcipRpprEnnm() {
		return this.selFatcaAcipRpprEnnm;
	}

	public void setSelFatcaAcipRpprEnnm(String selFatcaAcipRpprEnnm) {
		this.selFatcaAcipRpprEnnm = selFatcaAcipRpprEnnm;
	}

	public String getSelFatcaAcipCgpEnob() {
		return this.selFatcaAcipCgpEnob;
	}

	public void setSelFatcaAcipCgpEnob(String selFatcaAcipCgpEnob) {
		this.selFatcaAcipCgpEnob = selFatcaAcipCgpEnob;
	}

	public String getSelFatcaAcipCgpEnnm() {
		return this.selFatcaAcipCgpEnnm;
	}

	public void setSelFatcaAcipCgpEnnm(String selFatcaAcipCgpEnnm) {
		this.selFatcaAcipCgpEnnm = selFatcaAcipCgpEnnm;
	}

	public String getSelFatcaCustRelMngrEnob() {
		return this.selFatcaCustRelMngrEnob;
	}

	public void setSelFatcaCustRelMngrEnob(String selFatcaCustRelMngrEnob) {
		this.selFatcaCustRelMngrEnob = selFatcaCustRelMngrEnob;
	}

	public String getSelFatcaCustRelMngrEnnm() {
		return this.selFatcaCustRelMngrEnnm;
	}

	public void setSelFatcaCustRelMngrEnnm(String selFatcaCustRelMngrEnnm) {
		this.selFatcaCustRelMngrEnnm = selFatcaCustRelMngrEnnm;
	}

	public String getCongYn() {
		return this.congYn;
	}

	public void setCongYn(String congYn) {
		this.congYn = congYn;
	}

	public String getServerType() {
		return this.serverType;
	}

	public void setServerType(String serverType) {
		this.serverType = serverType;
	}
	
	
	
}
