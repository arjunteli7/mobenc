package com.autofin.api.bean;

public class MobileNumberEncrptDecrptBean {

	private String flag;
	private String mobileNumber;
	private String vendorCode;
	private String Secretekey;
	private String message;
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getSecretekey() {
		return Secretekey;
	}
	public void setSecretekey(String secretekey) {
		Secretekey = secretekey;
	}
	public byte[] getBytes() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
