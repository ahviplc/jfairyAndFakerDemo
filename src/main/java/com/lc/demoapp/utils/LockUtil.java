package com.lc.demoapp.utils;

/**
 *  并发控制锁帮助类
 * @Title LockUtil.java
 * @Package 
 * @Description 
 * Copyright: Copyright (c) 2018
 * Company:lmt
 * @author 
 * @date 2018年6月11日10:32:04
 * @version V1.0
 */
public class LockUtil {

	/**
	 * 并发锁集合
	 */
	private static Object[] locks;
	
	static {
		int concurrency = 512; //默认并发度:512
		locks = new Object[concurrency];
		for (int i = 0; i < concurrency; i++) {
			locks[i] = new Object();
		}
	}
 
	/**
	 * 根据key获取锁
	 * 备注: 相同的key获取到同一把锁
	 * @param k - key
	 * @return - 锁对象
	 */
	public static Object getLock(Object k) {
		
		if (null == k) {
			throw new NullPointerException("lock key is null");
		}
		return getLock(k.hashCode());
	} 
	
	/**
	 * 根据keys获取锁
	 * 备注: 相同的key获取到同一把锁
	 * @param keys - keys
	 * @return - 锁对象
	 */
	public static Object getLock(Object ... keys) {
		
		if (null == keys) {
			throw new NullPointerException("lock key is null");
		}
		int h = 0;
		for (int i = 0; i < keys.length; i++) {
			if (null != keys[i]) {
				h += keys[i].hashCode();
			}
		}
		return getLock(h);
	}
	
	/**
	 * 根据int获取锁
	 * 备注: 相同的key获取到同一把锁
	 * @param i - int
	 * @return - 锁对象
	 */
	private static Object getLock(int i) {
		  
		int index = Math.abs(i) % locks.length;
		return locks[index];
	}
	
	/**
	 * 防止非法实例化
	 */
	private LockUtil() { }
}
