package com.lc.demoapp.utils;

import java.util.Random;

/*
 * 16 一个16位数字组成的随机字符串
 * 
 * 6066 8540 9215 9852
 * 
 *  String num=String.valueOf(l);
     Double d=Double.valueOf(num);
 * 
 */
public class Random16 {

	public static Long RandomNum16() {

		Random ran = new Random();
		int a = ran.nextInt(99999999);
		int b = ran.nextInt(99999999);
		long longnum = a * 100000000L + b;		
		return longnum;

	}

	public static void main(String[] args) {
              System.out.println(RandomNum16());
	}

}
