package com.lc.demoapp.utils;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 异常定义与操作类,更新错误编码后,记得同步到wiki上面
 * @Title Errors.java
 * @Description 
 * Copyright: Copyright (c) 2018
 * Company:lmt
 * @author 
 * @date 2018年6月11日10:29:12
 * @version V1.0
 */
public class Errors {
	
	/* system exception code range:[000, 099] */  
	public static final LogicError E000 = new LogicError("000", "未知异常"); 
	public static final LogicError E001 = new LogicError("001", "网络错误"); 
	public static final LogicError E002 = new LogicError("002", "应用层未知异常"); 
	public static final LogicError E003 = new LogicError("003", "数据访问未知异常"); 
	public static final LogicError E004 = new LogicError("004", "缓存访问未知异常"); 
	public static final LogicError E005 = new LogicError("005", "RPC调用未知异常"); 
	public static final LogicError E006 = new LogicError("006", "鉴权异常(authentication error)"); 
	public static final LogicError E007 = new LogicError("007", "授权异常(authorization error)"); 

	/* input exception code range:[100, 199]  */
	public static final LogicError E100 = new LogicError("100", "传入的参数不对"); 
	public static final LogicError E101 = new LogicError("101", "JSON字符串语法不正确"); 
	public static final LogicError E102 = new LogicError("102", "请求的JSON串为空"); 
	public static final LogicError E106 = new LogicError("106", "解析json-xml流为JSON对象出错");
	public static final LogicError E108 = new LogicError("108", "content-type MIME unsupported");
	public static final LogicError E109 = new LogicError("109", "XML解析成MAP对象出错");
	public static final LogicError E110 = new LogicError("110", "HTTP 404 error, action not found!");
	public static final LogicError E111 = new LogicError("111", "鉴权不通过(access authorization failure)");
	public static final LogicError E112 = new LogicError("112", "HTTP 格式化输出异常(format exception)");
	public static final LogicError E113 = new LogicError("113", "HTTP 执行Preprocessor失败(call preprocessor exception)");
	public static final LogicError E114 = new LogicError("114", "操作超时(operation timeout)");
	public static final LogicError E115 = new LogicError("115", "数据加解密失败(encrypte failure)");
	public static final LogicError E116 = new LogicError("116", "数据解压缩失败(compress failure)");
	public static final LogicError E117 = new LogicError("117", "HTTP GET方法执行异常");
	public static final LogicError E118 = new LogicError("118", "HTTP POST方法执行异常");
	public static final LogicError E119 = new LogicError("119", "FTP 获取服务器上的文件列表异常");
	public static final LogicError E120 = new LogicError("120", "FTP 初始化连接服务器异常");
	public static final LogicError E121 = new LogicError("121", "FTP 关闭连接异常");
	public static final LogicError E122 = new LogicError("122", "FTP 获取服务器上的文件列表异常");
	public static final LogicError E123 = new LogicError("123", "FTP 初始化连接服务器异常");
	public static final LogicError E124 = new LogicError("124", "FTP 关闭连接异常");
	public static final LogicError E125 = new LogicError("125", "HTTP 请求处理异常");
	
	/* database error code range:[300, 399]  */
	public static final LogicError E301 = new LogicError("301", "数据库执行超时");
	public static final LogicError E302 = new LogicError("302", "数据库执行DML(update) SQL语句异常");
	public static final LogicError E303 = new LogicError("303", "数据库执行QUERY(select) SQL语句异常");
	public static final LogicError E304 = new LogicError("304", "没有找到数据库");
	public static final LogicError E305 = new LogicError("305", "数据库执行事务控制(transaction)的SQL语句异常");
	public static final LogicError E306 = new LogicError("306", "数据库执行SQL语句, 影响行数(affacted)异常");
	public static final LogicError E307 = new LogicError("307", "解析动态SQL异常");
	public static final LogicError E330 = new LogicError("330", "目标数据源不可用");
	public static final LogicError E331 = new LogicError("331", "匹配数据库节点集群失败");
	public static final LogicError E333 = new LogicError("333", "数据库操作失败");
	
	/* cache error code range:[400, 499]  */
	public static final LogicError E401 = new LogicError("401", "进程内缓存操作异常");
	public static final LogicError E402 = new LogicError("402", "分布式缓存操作异常");
	public static final LogicError E403 = new LogicError("403", "初始化缓存异常");
	public static final LogicError E405 = new LogicError("405", "获取分布式锁异常"); 

	/* rpc exception  code range:[500, 599] */
	public static final LogicError E501 = new LogicError("501", "Server Interval Error! "); 
	public static final LogicError E502 = new LogicError("502", "Method not found! "); 
	public static final LogicError E503 = new LogicError("503", "json-rpc protocal Error! "); 
	public static final LogicError E504 = new LogicError("504", "mvc-action duplicate definition! "); 
	
	/* input validate code range:[700, 799] */
	public static final LogicError E700 = new LogicError("700", "缺少参数");
	public static final LogicError E701 = new LogicError("701", "required缺少参数");
	public static final LogicError E702 = new LogicError("702", "integer: 没有或者必须为整数 无参数");
	public static final LogicError E703 = new LogicError("703", "integerRange:整数范围必须在参数0和参数1之间; param0和param1必须能被转化成整数");
	public static final LogicError E704 = new LogicError("704", "date: 必须为日期格式, param0必须为yyyy-mm-dd");
	public static final LogicError E705 = new LogicError("705", "enum:必须是一系列枚举值中的一个（param0中用逗号分割出来的集合）");
	public static final LogicError E706 = new LogicError("706", "minLength: 参数最小长度不正确");
	public static final LogicError E707 = new LogicError("707", "maxLength: 参数最大长度不正确");
	public static final LogicError E708 = new LogicError("708", "mask: 允许自定义正则表达式来进行校验, param0为表达式字符串");
	public static final LogicError E709 = new LogicError("709", "double: 允许为空或者必须为double数 无参数");
	public static final LogicError E710 = new LogicError("710", "doubleRange:浮点范围必须在参数0和参数1之间; param0和param1必须能被转化成浮点数");
	public static final LogicError E711 = new LogicError("711", "参数类型不匹配");
	public static final LogicError E712 = new LogicError("712", "变量值不相等");
	public static final LogicError E713 = new LogicError("713", "格式化数据异常");
	public static final LogicError E714 = new LogicError("714", "java bean赋值异常");
	public static final LogicError E715 = new LogicError("715", "访问java bean属性异常");
	public static final LogicError E716 = new LogicError("716", "调用java bean方法异常"); 
	public static final LogicError E717 = new LogicError("717", "对象序列化异常"); 
	public static final LogicError E718 = new LogicError("718", "对象反序列化异常"); 
	public static final LogicError E719 = new LogicError("719", "未找到目标对象");  
	public static final LogicError E720 = new LogicError("720", "编码解码数据异常");   
	public static final LogicError E721 = new LogicError("721", "加解密数据异常");  
	

	/* app[base] code range:[2000, 2099] */
	/* app[user] code range:[2100, 2199] */
	 
	/**
	 * 抛出异常type的一个实例
	 * @param type - 异常类型样本类型
	 * @param cause - 异常原因对象
	 */
	public static void throwEx(LogicError type, Throwable cause) {
		throw clone(type, cause);
	}

	/**
	 * 抛出异常type的一个实例
	 * @param type - 异常类型样本类型 
	 * @exception - LogicError
	 */
	public static void throwEx(LogicError type) {
		throw clone(type);
	}
	
	/**
	 * 将异常原因对象转成卓望基础运行时异常实例
	 * 备注: 如果类型不匹配,使用[999:未知异常]类型将其包装成卓望基础运行时异常
	 * @param ex - 异常类型样本类型 
	 * @return - 卓望基础运行时异常实例
	 */
	public static LogicError valueOf(Throwable ex) {
		
		LogicError e = null;
		if (ex instanceof LogicError) {
			e = (LogicError)ex;
		} else if (ex instanceof NullPointerException) {
			e = clone(E700, ex);
		} else if (ex instanceof ClassCastException) { 
			e = clone(E711, ex);
		} else if (ex instanceof SQLException) { 
			e = clone(E333, ex);
		} else {
			e = clone(E000, ex);
		}
		return e;
	}
	
	/**
	 * 将异常原因对象转成卓望基础运行时异常实例
	 * 备注: 如果类型不匹配,使用指定包装类型将其包装成卓望基础运行时异常
	 * @param ex - 异常类型样本类型
	 * @param wrapType - 异常包装类型
	 * @return - 卓望基础运行时异常实例
	 */
	public static LogicError valueOf(Throwable ex, LogicError wrapType) {
		
		LogicError are = null;
		if (ex instanceof LogicError) {
			are = (LogicError)ex;
		} else {
			are = clone(wrapType, ex);
		}
		return are;
	}
	
	/**
	 * 根据异常类型样本类型和异常原因对象构造一个异常新实例
	 * @param type - 异常类型样本类型
	 * @param cause - 异常原因对象
	 * @return - 异常新实例
	 */
	public static LogicError clone(LogicError type, Throwable cause) {

		LogicError e = new LogicError();
		e.setCode(type.getCode());
		e.setMsg(type.getMsg());
		e.setReason(type.getReason());
		e.setCause(cause);
		e.setAction(type.getAction()); 
		return e;
	}
	
	/**
	 * 根据异常类型样本类型和异常原因对象构造一个异常新实例
	 * @param type - 异常类型样本类型
	 * @param reason - 异常原因描述
	 * @return - 异常新实例
	 */
	public static LogicError clone(LogicError type, String reason) {

		LogicError e = clone(type); 
		e.setReason(reason); 
		return e;
	} 
	
	/**
	 * 根据异常类型样本类型和异常原因对象构造一个异常新实例
	 * @param type - 异常类型样本类型
	 * @return - 异常新实例
	 */
	public static LogicError clone(LogicError type) {

		LogicError e = new LogicError();
		e.setCode(type.getCode());
		e.setMsg(type.getMsg()); 
		e.setAction(type.getAction());  
		return e;
	}
	
	/**
	 * 根据异常类型样本类型和异常原因对象构造一个Map对象
	 * @param ex - 异常对象
	 * @return - Map对象: {sn, code, msg, reason, action}
	 */
	public static Map<String, String> toMap(LogicError ex) {

		Map<String, String> errorMap = new LinkedHashMap<String, String>();
		errorMap.put("code", ex.getCode());
		errorMap.put("sn", ex.getSn());
		errorMap.put("message", ex.getMsg());
		errorMap.put("reason", ex.getReason());
		errorMap.put("action", ex.getAction());
		return errorMap;
	} 
	
	/**
	 * 将Map对象转成异常对象
	 * @param errorMap - Map对象: {errorSn, errorCode, errorMsg, errorCause, errorAction}
	 * @return - 异常对象
	 */
	public static LogicError parse(Map<String, String> errorMap) {
		
		LogicError ex = new LogicError(); 
		ex.setSn(errorMap.get("sn"));
		ex.setCode(errorMap.get("code"));
		ex.setMsg(errorMap.get("message"));
		ex.setReason(errorMap.get("reason"));
		ex.setAction(errorMap.get("action"));
		return ex;		
	} 
}
