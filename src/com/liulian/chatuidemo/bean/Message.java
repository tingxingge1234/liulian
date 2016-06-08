package com.liulian.chatuidemo.bean;

public class Message {

	boolean result;
	String msg;

	public Message() {
		super();
	}
	
	public Message(boolean result, String msg) {
		super();
		this.result = result;
		this.msg = msg;
	}
	
	public boolean isResult() {
		return result;
	}
	
	public void setResult(boolean result) {
		this.result = result;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return "Message [result=" + result + ", msg=" + msg + "]";
	}

}
