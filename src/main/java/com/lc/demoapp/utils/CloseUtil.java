package com.lc.demoapp.utils;

import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.zip.ZipFile;

/**
 * 关闭资源工具类
 * <p>备注: 可以关闭JDK里面绝大多数需要关闭的资源对象,同时不对外抛出异常
 * <p>  如果第三方需要关闭的资源类遵循jdk的接口标准也可以关闭,主要使用接口Closeable;
 * @Title CloseUtil.java
 * @Description 
 * Copyright: Copyright (c) 2018
 * Company:lmt
 * @author 
 * @date 2018年6月11日10:26:47
 * @version V1.0
 */
public final class CloseUtil {

	/**
	 * 关闭Connection资源对象
	 * 备注: 如果资源对象不为null, 关闭资源,不抛出任何异常
	 * @param rsc -- 资源对象
	 */
	public static void closeSilently(Connection rsc) {
		
		if (null != rsc) {
			try { rsc.close(); } catch (Exception ex) { /* 消除异常 */ }
		}
	}
	
	/**
	 * 关闭Statement资源对象
	 * 备注: 如果资源对象不为null, 关闭资源,不抛出任何异常
	 * @param rsc -- 资源对象
	 */
	public static void closeSilently(Statement rsc) {
		
		if (null != rsc) {
			try { rsc.close(); } catch (Exception ex) { /* 消除异常 */ }
		}
	}
	
	/**
	 * 关闭ResultSet资源对象
	 * 备注: 如果资源对象不为null, 关闭资源,不抛出任何异常
	 * @param rsc -- 资源对象
	 */
	public static void closeSilently(ResultSet rsc) {
		
		if (null != rsc) {
			try { rsc.close(); } catch (Exception ex) { /* 消除异常 */ }
		}
	}
	
	/**
	 * 关闭InputStream资源对象
	 * 备注: 如果资源对象不为null, 关闭资源,不抛出任何异常
	 * @param rsc -- 资源对象
	 */
	public static void closeSilently(InputStream rsc) {
		
		if (null != rsc) {
			try { rsc.close(); } catch (Exception ex) { /* 消除异常 */ }
		}
	}
	
	/**
	 * 关闭OutputStream资源对象
	 * 备注: 如果资源对象不为null, 关闭资源,不抛出任何异常
	 * @param rsc -- 资源对象
	 */
	public static void closeSilently(OutputStream rsc) {
		
		if (null != rsc) {
			try { rsc.close(); } catch (Exception ex) { /* 消除异常 */ }
		}
	}
	
	/**
	 * 关闭Closeable资源对象
	 * 备注: 如果资源对象不为null, 关闭资源,不抛出任何异常
	 * @param rsc -- 资源对象
	 */
	public static void closeSilently(Closeable rsc) {
		
		if (null != rsc) {
			try { rsc.close(); } catch (Exception ex) { /* 消除异常 */ }
		}
	}
	
	/**
	 * 关闭ServerSocket资源对象
	 * 备注: 如果资源对象不为null, 关闭资源,不抛出任何异常
	 * @param rsc -- 资源对象
	 */
	public static void closeSilently(ServerSocket rsc) {
		
		if (null != rsc) {
			try { rsc.close(); } catch (Exception ex) { /* 消除异常 */ }
		}
	}
	
	/**
	 * 关闭Socket资源对象
	 * 备注: 如果资源对象不为null, 关闭资源,不抛出任何异常
	 * @param rsc -- 资源对象
	 */
	public static void closeSilently(Socket rsc) {
		
		if (null != rsc) {
			try { rsc.close(); } catch (Exception ex) { /* 消除异常 */ }
		}
	}
	
	/**
	 * 关闭DatagramSocket资源对象
	 * 备注: 如果资源对象不为null, 关闭资源,不抛出任何异常
	 * @param rsc -- 资源对象
	 */
	public static void closeSilently(DatagramSocket rsc) {
		
		if (null != rsc) {
			try { rsc.close(); } catch (Exception ex) { /* 消除异常 */ }
		}
	}
	
	/**
	 * 关闭ZipFile资源对象
	 * 备注: 如果资源对象不为null, 关闭资源,不抛出任何异常
	 * @param rsc -- 资源对象
	 */
	public static void closeSilently(ZipFile rsc) {
		
		if (null != rsc) {
			try { rsc.close(); } catch (Exception ex) { /* 消除异常 */ }
		}
	}
	
	/**
	 * 关闭Scanner资源对象
	 * 备注: 如果资源对象不为null, 关闭资源,不抛出任何异常
	 * @param rsc -- 资源对象
	 */
	public static void closeSilently(Scanner rsc) {
		
		if (null != rsc) {
			try { rsc.close(); } catch (Exception ex) { /* 消除异常 */ }
		}
	}
	
	/**
	 * 防止非法实例化
	 */
	private CloseUtil() { }
}
