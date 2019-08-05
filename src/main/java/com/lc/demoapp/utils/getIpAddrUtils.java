package com.lc.demoapp.utils;

import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * @author
 * @date 2018年6月11日10:29:57
 * 获取外网ip
 */
public class getIpAddrUtils {
	
	
	//获取本地IP地址的方法-String ip=InetAddress.getLocalHost().getHostAddress();
	public static String getLocalAddress(){
		String ip = "";
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ip;
	}
	
	
	
	
	//获取外网本机的IP地址的方法-可行
	public static String getV4IP_1(){
		String ip = "";
		String chinaz = "http://ip.chinaz.com";
		
		StringBuilder inputLine = new StringBuilder();
		String read = "";
		URL url = null;
		HttpURLConnection urlConnection = null;
		BufferedReader in = null;
		try {
			url = new URL(chinaz);
			urlConnection = (HttpURLConnection) url.openConnection();
		    in = new BufferedReader( new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
			while((read=in.readLine())!=null){
				inputLine.append(read+"\r\n");
			}
			//System.out.println(inputLine.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
		Matcher m = p.matcher(inputLine.toString());
		if(m.find()){
			String ipstr = m.group(1);
			ip = ipstr;
			//System.out.println(ipstr);
		}
		return ip;
	}
	
	
	// 获取ip地址-未测试-这是一个-方法-应该 getIpAddr.action访问-先写在这，备份一下吧！
	//测试 输出-0:0:0:0:0:0:0:1
		public  String getIpAddr(HttpServletRequest request)
		{ 
		String ip = request.getHeader("x-forwarded-for"); 
		
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
		{ ip =request.getHeader("Proxy-Client-IP"); } 
		
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
		{ ip =request.getHeader("WL-Proxy-Client-IP"); } 
		
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
		{ ip = request.getRemoteAddr(); }
		 
		return ip; 
		 
		}



		//获取内外网ip 方法2

	public static String INTRANET_IP = getIntranetIp(); // 内网IP
	public static String INTERNET_IP = getInternetIp(); // 外网IP



	/**
	 * 获得内网IP
	 * @return 内网IP
	 */
	private static String getIntranetIp(){
		try{
			return InetAddress.getLocalHost().getHostAddress();
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获得外网IP
	 * @return 外网IP
	 */
	private static String getInternetIp(){
		try{
			Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			Enumeration<InetAddress> addrs;
			while (networks.hasMoreElements())
			{
				addrs = networks.nextElement().getInetAddresses();
				while (addrs.hasMoreElements())
				{
					ip = addrs.nextElement();
					if (ip != null
							&& ip instanceof Inet4Address
							&& ip.isSiteLocalAddress()
							&& !ip.getHostAddress().equals(INTRANET_IP))
					{
						return ip.getHostAddress();
					}
				}
			}

			// 如果没有外网IP，就返回内网IP
			return INTRANET_IP;
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}


	//亚马逊服务 获取外网ip api
	public static String getAmazonaWaiWangIp() throws Exception {
		URL whatismyip = new URL("http://checkip.amazonaws.com");
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(
					whatismyip.openStream()));
			String ip = in.readLine();
			return ip;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//防止非法实例化
	private getIpAddrUtils(){}
	
	//main 测试
	public static void main(String[] args) throws Exception {

		//		System.out.println(getV4IP());
        //      System.out.println(getInternetIp());
        //      System.out.println(getAmazonaWaiWangIp());

	}


}
