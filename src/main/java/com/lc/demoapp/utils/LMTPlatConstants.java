package com.lc.demoapp.utils;

/**
 * 通用代码-一些必要的常量
 * 微信公众号 收钱吧
 *
 * @author LC
 */
public class LMTPlatConstants {

    //JS-SDK js-http://####/wxgzh/wxIndex.action
    //public final static String wxgzhURL="http://####/LMTPlat";
    public final static String wxgzhURL = "http:/####/LMTPlat";
    //public final static String wxgzhURL="http://####/LMTPlat";
    //public final static String wxgzhURL="http://####/LMTPlat";
    //JSAPI-TICKET
    public final static String JSAPI_TICKET = "#";


    public final static String USER_SESSION = "userSession";
    public final static String SYS_MESSAGE = "message";
    public final static int pageSize = 5;


    //微信公众号  ahviplc -测试账户###
    //public static final String WEIXIN_APP_ID = "#";//第三方用户唯一凭证
    //public static final String WEIXIN_APP_SECRET = "#";// 第三方用户唯一凭证密钥，即appsecret

    //罗美特平台 appid 密钥
    public static final String WEIXIN_APP_ID = "#";//第三方用户唯一凭证
    public static final String WEIXIN_APP_SECRET = "#";// 第三方用户唯一凭证密钥，即appsecret

    public static final String WEIXIN_TOKEN = "###";//调用接口凭证
    public static final String WEIXIN_ENCODING_AES_KEY = "###";
    public static final String WEIXIN_VERIFY_URL_FAILED = "Verifying URL failed";
    //微信调用接口
    public static final String WEIXIN_API_DOMAIN = "https://api.weixin.qq.com/cgi-bin";//调用域名接口
    public static final String WEIXIN_OPEN_ID = "#";//普通用户的标识，对当前公众号唯一 -ahviplc
    public static final String WEIXIN_TEMPLATE_ID = "#";//模板ID


    //微信公众号 罗美特平台 服务号 -网页授权域名 现在还没有搞定-先保留
//	public static final String WEIXIN_APP_ID = "#";
//	public static final String WEIXIN_APP_SECRET = "#";
//	public static final String WEIXIN_TOKEN = "#";
//	public static final String WEIXIN_ENCODING_AES_KEY = "#";
//	public static final String WEIXIN_VERIFY_URL_FAILED = "Verifying URL failed";


    //网页授权
    public static final String WEIXIN_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
    public static final String WEIXIN_AUTHORIZE_SCOPE_BASE = "snsapi_base";
    public static final String WEIXIN_AUTHORIZE_SCOPE_USERINFO = "snsapi_userinfo";
    public static final String WEIXIN_AUTHORIZE_STATE_DEFAULT = "0";


    //收钱吧
    public static final String vendor_sn = "#";//服务商序列号
    public static final String vendor_key = "#";//服务商密钥
    public static final String code = "#";//激活码（# #门店 激活码-#）-测试激活码：#  -#
    public static final String api_domain = "https://api.shouqianba.com";//收钱吧接入域名(api_domain)

    //public static final  String wx_api_domain = "https://qr.shouqianba.com/gateway"; // 收钱吧接入域名(api_domain)-微信公众号支付-场景支付-网关地址

    public static final String wx_api_domain = "https://m.wosai.cn/qr/gateway"; // 收钱吧接入域名(api_domain)-微信公众号支付-场景支付-网关地址-新给的


    public static final String open_api_domain = "https://api-vendor.shouqianba.com";//收钱吧接入域名(api_domain)-openApi 域名

    // # 设备号下的终端信息
    public static final String terminal_sn96 = "#";//终端号
    public static final String terminal_key96 = "#";//终端密钥

    // #  设备号下的终端信息
    public static final String terminal_sn97 = "#";//终端号
    public static final String terminal_key97 = "#";//终端密钥


    //50a87771-ca8a-4952-a493-9504c39ab498  设备号下的终端信息   -测试激活码33257499激活的终端	  
    public static final String terminal_sn98 = "#";//终端号
    public static final String terminal_key98 = "#";//终端密钥


    //阿里api调用凭证-密钥-替换成你的AK
    public static final String ali_accessKeyId = "#";//你的accessKeyId,阿里api调用凭证 Id
    public static final String ali_accessKeySecret = "#";//你的accessKeySecret-阿里api调用Id 密钥


    //电信CoAP相关api调用接口
    public static final String coap_platformIp = "develop.api.ct10649.com";
    public static final String coap_platformPort = "8743";

    //电信CoAP-server2-测试环境-项目-应用ID(appId)和密钥
    public static final String coap_appId = "#";
    public static final String coap_secret = "#";

    //电信CoAP-aliyun-正式环境-项目-应用ID(appId)和密钥
//	public static final String coap_appId = "#";
//	public static final String coap_secret = "#";

}
