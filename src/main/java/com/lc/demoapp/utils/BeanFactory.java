package com.lc.demoapp.utils;

import java.lang.reflect.Field;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * bean 工厂
 * @Title BeanFactory.java
 * @Description 
 * Company:lmt
 * Copyright: Copyright (c) 2018
 * @date 2018年6月11日10:26:15
 * @version V1.0
 */
@SuppressWarnings("unchecked")
public final class BeanFactory {  
	
	/**
	 * spring 应用上下文
	 */
	private static ApplicationContext ac;
	
	/**
	 * 获取上下文
	 * @return - 应用上下文
	 */
	public static ApplicationContext currentApplicationContext() {
		
		if (null == ac) {
			/**
			 * 防止同时进入竞争区
			 */
			synchronized (BeanFactory.class) {
				if (null == ac) {
			        //初始化IOC容器
					ClassPathXmlApplicationContext xac = new ClassPathXmlApplicationContext("classpath*:/applicationContext.xml");
					xac.registerShutdownHook();
			        ac = xac; 
				}
			}
		}
		return ac;
	}
	
	/**
	 * 判断是否存在ApplicationContext
	 * @return - true,已经存在ApplicationContext
	 */
	public static boolean hasApplicationContext() {
		return (null != ac);
	}
	
	/**
	 * 设置共享ApplicationContext
	 * @param context - ApplicationContext
	 */
	public static void setApplicationContext(ApplicationContext context) {
		
		if (null != context) {
			ac = context;
		}
	}
	
	/**
	 * 获取spring bean
	 * @param beanId - bean ID
	 * @return - spring bean
	 */
	public static <T> T getBean(String beanId) {
		return (T) currentApplicationContext().getBean(beanId);
	}
	
	/**
	 * 获取spring bean
	 * @param <T>
	 * @param beanId  - bean ID
	 * @param returnType - 返回类型
	 * @return - spring bean
	 */ 
	public static <T> T getBean(String beanId, Class<T> returnType) {
		return (T) currentApplicationContext().getBean(beanId, returnType);
	}
	
	/**
	 * 是否存在bean
	 * @param beanId - bean ID
	 * @return - true, 如果存在bean配置
	 */
	public static boolean containBean(String beanId) {
		return currentApplicationContext().containsBean(beanId);
	}
	
	/**
	 * 大约一个未知的对象的属性
	 * @param bean
	 * @return
	 */
	public static String toString(Object bean) {
		 
		if (null == bean) {
			return "null";
		}
		
		//得到类对象
	       Class userCla = (Class) bean.getClass();
	       
	       StringBuilder sb = new StringBuilder();
	       sb.append("@").append(userCla.getSimpleName()).append("{");
	      
	       /*
	        * 得到类中的所有属性集合
	        */
	       Field[] fs = userCla.getDeclaredFields();
	       for(int i = 0 ; i < fs.length; i++) {
	    	   try { 
		           Field f = fs[i];
		           f.setAccessible(true); //设置些属性是可以访问的
		           Object val = f.get(bean);//得到此属性的值   
		           String type = f.getType().toString();//得到此属性的类型
		           sb.append(" ").append(f.getName()).append("[").append(type).append("]:").append(val); 
		           if (i != fs.length - 1) { 
		        	   sb.append(",");
		           }
	    	   } catch (Exception ex ) {
	    		   ex.printStackTrace();
	    	   }
	       }
	       sb.append("}");
	       return sb.toString();
	}
}
