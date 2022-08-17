package com.autofin.api.custom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ACCEPTED)
public class ErrorResponse
{
    public ErrorResponse(String memob,String memoc,String memod,String memoe,String memoOcc,String contStatusOccr,String errmsg,String status,String msg) {
        super();
        this.memob = memob;
        this.memoc = memoc;
        this.memod = memod;
        this.memoe = memoe;
        this.memoOcc = memoOcc;
        this.contStatusOccr = contStatusOccr;
        this.errmsg = errmsg;
        this.status = status;
        this.msg = msg;
    }
    public String memob;
	public String memoc;
	public String memod;
	public String memoe;
	public String memoOcc;
	public String contStatusOccr;
	public String errmsg;
	public String status;
    private String msg;
	public String getMemob() {
		return memob;
	}
	public void setMemob(String memob) {
		this.memob = memob;
	}
	public String getMemoc() {
		return memoc;
	}
	public void setMemoc(String memoc) {
		this.memoc = memoc;
	}
	public String getMemod() {
		return memod;
	}
	public void setMemod(String memod) {
		this.memod = memod;
	}
	public String getMemoe() {
		return memoe;
	}
	public void setMemoe(String memoe) {
		this.memoe = memoe;
	}
	public String getMemoOcc() {
		return memoOcc;
	}
	public void setMemoOcc(String memoOcc) {
		this.memoOcc = memoOcc;
	}
	public String getContStatusOccr() {
		return contStatusOccr;
	}
	public void setContStatusOccr(String contStatusOccr) {
		this.contStatusOccr = contStatusOccr;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
    
}