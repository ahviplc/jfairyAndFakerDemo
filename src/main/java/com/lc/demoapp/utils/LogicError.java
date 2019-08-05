package com.lc.demoapp.utils;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;

/**
 * 逻辑错误
 * @Title LogicError.java
 * @Package 
 * @Description 
 * Copyright: Copyright (c) 2018
 * Company:lmt
 * @author 
 * @date 2018年6月11日10:32:27
 * @version V1.0
 */
public class LogicError extends RuntimeException implements Cloneable {
 
	private static final long serialVersionUID = 1666385134759048787L;

	/**
	 * 错误序列号
	 */
	protected String sn;
	
	/**
	 * 错误代码
	 */
	protected String code;
	
	/**
	 * 错误信息
	 */
	protected String msg;
	
	/**
	 * 错误引发原因
	 */
	protected String reason;
	
	/**
	 * 错误发生后可(或者建议)采取的行为
	 */
	protected String action;
	
	/**
	 * 原生异常对象
	 */
	protected Throwable cause;

	/**
	 * 获取新的唯一错误号
	 * @return -- 有序UUID值
	 */
	protected static String getNewSn() {
		 
		UUID uid = UUID.randomUUID();
		long mh = uid.getMostSignificantBits();
		long lh = uid.getLeastSignificantBits();
		long now = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder(50);
		sb.append(now);
		sb.append(".");
		sb.append(Math.abs(mh & lh));
		return sb.toString();  
	} 
	public LogicError()	{
		this.sn = getNewSn();
	}
	
	public LogicError(String code, String msg) {
		
		this.sn = getNewSn();
		this.code = code;
		this.msg = msg;
	}
	
	public LogicError(String code, String msg, Throwable cause) {

		this.sn = getNewSn();
		this.code = code;
		this.msg = msg;
		this.setCause(cause);
	}
	
	public LogicError(String code, String msg, String reason) {

		this.sn = getNewSn();
		this.code = code;
		this.msg = msg;
		this.reason = reason;
	}
	
	public LogicError(String code, String msg, Throwable cause, String action) {

		this.sn = getNewSn();
		this.code = code;
		this.msg = msg;
		this.setCause(cause);
		this.action = action;
	}
	
	public LogicError(String sn, String code, String msg, String reason, String action) {
		
		this.sn = sn;
		this.code = code;
		this.msg = msg;
		this.reason = reason;
		this.action = action;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		
		if (null != this.cause) {
			StringBuilder sb = new StringBuilder();
			sb.append("@").append(cause.getClass().getSimpleName()).append("{");
			sb.append(" msg:").append(cause.getMessage());
			sb.append("}");
			this.reason = reason + " >>>cause: " + sb.toString();
		} else {
			this.reason = reason;
		}
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getSn() {
		return sn;
	}
	
	public void setSn(String sn) {
		this.sn = sn;
	}

	public Throwable getCause() {
		return cause;
	}

	/**
	 * @param cause the cause to set
	 */
	public LogicError setCause(Throwable cause) {
		
		this.cause = cause;
		if (null != cause) {
			StringBuilder sb = new StringBuilder();
			sb.append("@").append(cause.getClass().getSimpleName()).append("{");
			sb.append(" msg:").append(cause.getMessage());
			sb.append("}");
			if (null != this.reason) {
				this.reason += " >>>cause: " + sb.toString();
			} else {
				this.reason = sb.toString();
			}
		}
		return this;
	}

	public void printStackTrace(PrintStream s) {
		synchronized (s) { 
			s.print(this.getMessage()); 
			if (null != cause) {
				s.println(" >>>stack: ");
				cause.printStackTrace(s);
			}
		}
	}

	public void printStackTrace(PrintWriter s) {
		synchronized (s) { 
			s.print(this.getMessage()); 
			if (null != cause) { 
				s.println(" >>>stack: ");
				cause.printStackTrace(s);
			}
		}
	}
	
	public String printString() {
		
		StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    printStackTrace(pw);
	    return sw.toString();
	}
	
	@Override
	public LogicError clone() { 

		LogicError e = new LogicError();
		e.code = this.code;
		e.msg = this.msg;
		e.reason = this.reason;
		e.action = this.action; 
		return e;
	}

	@Override
	public String getMessage() { 
		return toString();
	}
	
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("@").append(this.getClass().getSimpleName()).append("{");
		sb.append(" sn:").append(sn).append(",");
		sb.append(" code:").append(code).append(",");
		sb.append(" msg:").append(msg).append(",");
		sb.append("\n reason:").append(reason).append(",");
		sb.append("\n action:").append(action);
		sb.append(" }");
		return sb.toString();
	}
}
