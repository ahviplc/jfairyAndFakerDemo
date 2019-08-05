package com.lc.demoapp.utils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 书写api小工具-api请求工具类
 * @author lmt
 * @dateTime 2018-10-10 12:13:08
 */

public class APIJsonUtil {

	/**
	 * API接收json字符串处理-json转map
	 * @param json
	 * @return
	 */
	public static Map<String,Object> APIConvertJSONtoMap(String json){
		//json=json.replace(" ", "");//去掉半角空格
		//json=json.replace(" ", "");//去掉全角空格
        ObjectMapper mapper = new ObjectMapper();
        Map map = null;
		try {
			map = mapper.readValue(json.toUpperCase(), Map.class);//将json字符串 所有的字母 变大写
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(map);
		return map;
	}


	/**
	 * 将map中元素按照key的英文字母从小到大排列
	 * 按key的英文字母从小到大排列
	 *
	 * @param map 原来的map
	 * @return 排序之后的map
	 *
	 * 备注:TreeMap中所有的元素都保持着某种固定的顺序，如果你需要得到一个有序的结果你就应该使用TreeMap
	 * TreeMap treemap = new TreeMap(mapmap2);//也可以这样
	 */
	public static Map<String,Object> sortMapByKey(Map<String,Object> map) {
		Map<String, Object> sortOkMap = new TreeMap<String, Object>(map);
		return  sortOkMap;

	}


	/**
	 * 一个map形式的key,values转换成指定字符串
	 * 这是一个工具类参数为map
	 * 例如输出demo:【age=18&name=黄家驹&sex=男&】 去最后一个& 则为【age=18&name=黄家驹&sex=男】
	 * 调用后返回结果  String urlParamsByMap = EncryptHelper.getUrlParamsByMap(map);
	 * 输出: age=18&name=黄家驹&sex=男&
	 * 如果想截取后边的&如下；
	 * String substring = urlParamsByMap.substring(0, urlParamsByMap.length() - 1);
	 * 输出: age=18&name=黄家驹&sex=男
	 *
	 * @param map
	 * @return String
	 */
	public static class EncryptHelper {

		public static String getUrlParamsByMap(Map<String, Object> map) {
			if (map == null) {
				return "";
			}
			StringBuffer sb = new StringBuffer();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				sb.append(entry.getKey() + "=" + entry.getValue());
				sb.append("&");
			}
			return sb.toString();
		}


		/**
		 * 不带符号+和&的版本
		 * 此方法 除去了 SIGNATURE和TIMESTAMP
		 * @param map
		 * @return
		 */
		public static String getUrlParamsByMapType2WithoutSign(Map<String, Object> map) {
			if (map == null) {
				return "";
			}
			StringBuffer sb = new StringBuffer();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				////字母 已变成 大写字母了
				if(!entry.getKey().equals("SIGNATURE")&&!entry.getKey().equals("TIMESTAMP")){
                    if( entry.getValue() instanceof Double){//只有当数据类型为double时，才将获取的值进行去零处理，以及最后如果最后一位是小数点【.】则去掉
						sb.append(entry.getKey() +rvZeroAndDotUtil.rvZeroAndDot((entry.getValue()).toString()));
						sb.append("");
					}else {
						sb.append(entry.getKey() + entry.getValue());
						sb.append("");
					}
				}
			}
			return sb.toString();
		}
	}


	/**
	 * 签名机制 解释:
	 * 2 通讯接口安全机制
	 * 为数据交互安全，本协议采用访问身份验证和参数签名的方式，以确保报文传输过程中的完整性和安全性，避免恶意访问。
	 * 2.1 身份验证
	 * 接口调用方需要在调用接口时，在传递参数中增加参数factoryCode（用于证明接入者的身份信息），参数companyCode（用于识别燃气公司信息）。同时接口提供方会另外提供给接入者参数factoryKey作为密钥，此参数不需要传递，但会用于签名算法的计算。
	 * 2.2 时间戳
	 * 接口调用方需要在调用接口时，在参数中增加关于调用时时间的参数timestamp，格式为yyyyMMddHHmmss。此参数用于参数签名，同时也用于监控报文的生命周期。
	 * 2.3 参数签名算法
	 * 接口调用方需要在调用接口时，对参数集按照此算法进行加密，并将加密后的结果放入参数signature中传递。接口提供方将按照相同算法进行加密，并通过比对结果检验参数的合法性。
	 * 以下为本协议采用的签名算法：
	 * MD5( MD5 (（本地参数factoryKey和除去signature和timestamp外所有需传递参数按照 参数名+参数内容（去掉首尾空格））Sort 后拼接 ) +timestamp )
	 *
	 * 原则说明：
	 * 1、MD5为32位MD5加密；
	 * 2、Sort为将参数名和参数内容拼接后的内容按字母顺序排序；
	 * 3、如果参数值为空，则只需要拼接参数名即可；
	 * 4、参与计算的所有字母都采用大写。
	 * 5、如果涉及参数内容为JSON，则参数内容处理为
	 * 参数内容= Sort(键+值（去掉首尾空格）) 拼接 ，组装过程遵循说明中所有原则
	 * 6、	参数不能为数据集
	 */


	/**
	 * 计算字符串的MD5值-getLMTParamsSign
	 * @param  signJsonStr:json字符串-签名字符串-这个参数字符串是包含所有参数的，带有SIGNATURE和TIMESTAMP
	 * @return
	 */
	public static String getLMTParamsSign(String signJsonStr,String timestamp) {
		try{
			//首先 将 这个json字符串 包含所有参数的，带有SIGNATURE和TIMESTAMP的参数字符串，转成map
			Map<String,Object> signMap=APIConvertJSONtoMap(signJsonStr);

			//将signMap 按照key的英文字母从小到大排列 并且同时 去掉SIGNATURE和TIMESTAMP  再toUpperCase() 方法将字符串小写字符转换为大写

			String signStr=EncryptHelper.getUrlParamsByMapType2WithoutSign(sortMapByKey(signMap)).toUpperCase();

			//去掉SIGNATURE和TIMESTAMP  之后 先md5一下 再 toUpperCase() 方法将字符串小写字符转换为大写
			String signStrMd5First=CreateMD5.encryptMd5(signStr).toUpperCase();

			//将signStrMd5First+timestamp 第二次md5  再toUpperCase() 方法将字符串小写字符转换为大写
			String signStrMd5Second=CreateMD5.encryptMd5(signStrMd5First+timestamp).toUpperCase();

			return signStrMd5Second;//这个值 即为SIGNATURE ，和传过来的SIGNATURE进行比较 校验身份合法性

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return  null ;
		}
	}



	public static void main(String[] args) {
		Map mapmap=new HashMap<String,Object>();
		mapmap.put("1","1");
		mapmap.put("2","2");
		mapmap.put("3","3");

		Map mapmap2=new HashMap<String,Object>();
		mapmap2.put("a","1");
		mapmap2.put("c","2");
		mapmap2.put("b","3");
		mapmap2.put("da",mapmap);
		mapmap2.put("dc",mapmap);
		mapmap2.put("db",mapmap);
		mapmap2.put("dj",mapmap);
		mapmap2.put("df",mapmap);

		Map mapmap3=new HashMap<String,Object>();
		mapmap3.put("A","1");
		mapmap3.put("B","2");
		mapmap3.put("C","3");
		mapmap3.put("DA",4);
		mapmap3.put("DC",5);
		mapmap3.put("SIGNATURE",6);
		mapmap3.put("DJ",7);
		mapmap3.put("TIMESTAMP",8);

		System.out.println("将map中元素按照key的英文字母从小到大排列，之后:    "+sortMapByKey(mapmap2));

		String urlParamsByMap = EncryptHelper.getUrlParamsByMap(sortMapByKey(mapmap2));

		System.out.println("一个map形式的key,values转换成指定字符串:    "+urlParamsByMap);

		String substring = urlParamsByMap.substring(0, urlParamsByMap.length() - 1);

		System.out.println("一个map形式的key,values转换成指定字符串,去&之后:    "+substring);

		System.out.println("将map中元素按照key的英文字母从小到大排列-无符号版本:    "+EncryptHelper.getUrlParamsByMapType2WithoutSign(sortMapByKey(mapmap3)));

		System.out.println("Date转时间:    "+DateUtil.convert(new Date(),DateUtil.DEFAULT_TIMESTAMP));

		System.out.println("时间转Date:    "+DateUtil.convert("20181010121952",DateUtil.DEFAULT_TIMESTAMP));
	}

}
