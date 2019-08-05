package com.lc.demoapp.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 数字工具类
 * @Title DecimalUtil.java
 * @Package com.sinotn.online.util
 * @Description 
 * Copyright: Copyright (c) 2018
 * Company:lmt
 * 
 * @author 
 * @date 2018年6月11日10:28:08
 * @version V1.0
 */
public class DecimalUtil {

    /**
     * 
     * @Description: 保留N位小数点
     * @param d
     * @param --format 格式#.#表示一位小数点，#.##表示两位小数点，不填则保留一位小数点
     * @return
     * Double
     * @throws 
     * @author rongjianping
     * 2015年4月28日下午4:01:32
     */
    public static Double getDecimalFormat(Double d,String format){
    	if(format==null){
    		format="#.#";
    	}
    	DecimalFormat df = new DecimalFormat(format);
    	return Double.parseDouble(df.format(d));
    }
    
    /**
     * 
     * @Description: double类型相加 
     * @param add1
     * @param add2
     * @return
     * Double
     * @throws 
     * @author rongjianping
     * 2015年8月28日下午3:52:34
     */
    public static Double add(Double x,Double y){
    	  BigDecimal b1 = new BigDecimal(Double.toString(x));
    	  BigDecimal b2 = new BigDecimal(Double.toString(y));
    	  return  b1.add(b2).doubleValue() ;  
    }
    
    /**
     * 
     * @Description: double类型相减 
     * @param x 被减数
     * @param y 减数
     * @return
     * Double
     * @throws 
     * @author rongjianping
     * 2015年8月28日下午3:54:45
     */
    public static Double sub(Double x,Double y){
  	  BigDecimal b1 = new BigDecimal(Double.toString(x));
  	  BigDecimal b2 = new BigDecimal(Double.toString(y));
  	  return  b1.subtract(b2).doubleValue() ;  
    }
    
    /**
     * 
     * @Description: 乘法 
     * @param x
     * @param y
     * @return
     * double
     * @throws 
     * @author rongjianping
     * 2015年8月28日下午3:58:46
     */
    public static Double mul(double x, double y) {  
        BigDecimal b1 = new BigDecimal(Double.toString(x));  
        BigDecimal b2 = new BigDecimal(Double.toString(y));  
        return b1.multiply(b2).doubleValue();  
    }   
   
    /**
     * 
     * @Description: 除法 
     * @param v1 被除数
     * @param v2
     * @param scale 小数点位数
     * @return
     * double
     * @throws 
     * @author rongjianping
     * 2015年8月28日下午3:59:20
     */
    public static Double div(double x, double y, int scale) {  
        if (scale < 0) {  
            throw new IllegalArgumentException(  
                    "The   scale   must   be   a   positive   integer   or   zero");  
        }  
        BigDecimal b1 = new BigDecimal(Double.toString(x));  
        BigDecimal b2 = new BigDecimal(Double.toString(y));  
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();  
    }
    
    public static void main(String args[]){
    	Double x=0.0;
    	Double y=0.01;
    	Double z=0.0;
    	for(int i=0;i<10;i++){
    		z=add(z,y);
    		x=x+y;
    	}
    	System.out.println("x="+x);
    	System.out.println("z="+z);
    }
}
