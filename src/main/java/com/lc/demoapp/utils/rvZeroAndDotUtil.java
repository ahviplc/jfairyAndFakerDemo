package com.lc.demoapp.utils;

import java.math.BigDecimal;
import java.text.ParseException;

/**
 * 小工具
 * 去掉多余零 如果最后一位是小数点【.】则去掉
 * @author lmt
 * @dateTime 2018年11月8日13:34:07
 */
public class rvZeroAndDotUtil {

    /**
     * 方法
     * 去掉多余零 如果最后一位是小数点【.】则去掉
     * @param s
     * @return
     */
    public static String rvZeroAndDot(String s){
        if (s.isEmpty()) {
            return null;
        }
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    /**
     * 测试main方法
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {

        BigDecimal b1 = new BigDecimal("100.10");
        BigDecimal b2 = new BigDecimal("100.00");
        BigDecimal b3 = new BigDecimal("100.010");

        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);

        System.out.println(rvZeroAndDot(b1.toString()));
        System.out.println(rvZeroAndDot(b2.toString()));
        System.out.println(rvZeroAndDot(b3.toString()));
    }


}
