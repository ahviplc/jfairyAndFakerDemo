package com.lc.demoapp.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author lmt
 * @datetime 2018年8月27日15:57:18
 * @desc 发送短信 工具类
 */

public final class SendMessagesUtils {



    //短信模板-可在短信控制台中找到
    public final static  String SMS_142947036="SMS_142947036";//SMS_142947036-微信端绑定手机号校验码-您的校验码：${code}，您正在绑定手机号，成为罗美特平台会员，感谢您的支持！
    public final static  String SMS_150742028="SMS_150742028";//SMS_150742028-欠费-你好，表计${meterNo},燃气余额不足，请及时充值。
    public final static  String SMS_150742027="SMS_150742027";//SMS_150742027-一般报警：断电…-你好，表计${meterNo},电量不足，请及时更换电池。
    public final static  String SMS_150742026="SMS_150742026";//SMS_150742026-非常报警，涉嫌盗气、不正常使用-你好，表计${meterNo},涉嫌盗气，请查验表计。

    /**
     * sendMessageWithMobileNoAndTemplateCode(String phoneNumbers,String templateCode)
     * 微信端 实名认证 绑定手机号 发送验证码 使用
     * @param phoneNumbers 要发送给的手机号
     * @param templateCode  短信模板-可在短信控制台中找到
     * @param requestForSession  传来session 为了请求成功之后，将短信验证码随机数字 存入session之中，以便后续操作
     * @throws Exception
     */

    public  static SendSmsResponse sendMessageWithMobileNoAndTemplateCode(String phoneNumbers,String templateCode,HttpServletRequest requestForSession)  throws Exception  {

        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）

        //替换成你的AK
        final String accessKeyId = LMTPlatConstants.ali_accessKeyId;//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = LMTPlatConstants.ali_accessKeySecret;//你的accessKeySecret，参考本文档步骤2

        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();

        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(phoneNumbers);//13916805133-15855891980
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("罗美特平台");
        //必填:短信模板-可在短信控制台中找到 -SMS_105980105      -SMS_142947036
        request.setTemplateCode(templateCode);//SMS_142947036-您的校验码：${code}，您正在绑定手机号，成为罗美特平台会员，感谢您的支持！
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败


        //request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");

        // gson使用和 实现随机4位的整数
        int x;// 定义两变量
        Random ne = new Random();// 实例化一个random的对象ne
        x = ne.nextInt(9999 - 1000 + 1) + 1000;// 为变量赋随机值1000-9999

        Gson gsonMsg = new Gson();
        JsonObject msgJsonObject = new JsonObject();

        //msgJsonObject.addProperty("name", "Zhangxi");

        // int 转 string 1》String.valueOf(i) 2》 Integer.toString(i) 3》 i+""

        msgJsonObject.addProperty("code", String.valueOf(x));

        String gg = gsonMsg.toJson(msgJsonObject);


        // request.setTemplateParam("{\"code\":\"1235\"}");

        request.setTemplateParam(gg);//短信模板变量替换JSON串,友情提示:如果JSON中需要带换行符,请参照标准的JSON协议。

        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功
            System.out.println("请求成功:"+sendSmsResponse.getCode()+sendSmsResponse.getMessage());
            System.out.println("发送短信成功:"+sendSmsResponse.getCode()+sendSmsResponse.getMessage());

            //请求成功，将短信验证码随机数字 存入session之中
            requestForSession.getSession().setAttribute("sessionMobileMsgYZM",String.valueOf(x));
            System.out.println("发送短信成功-ok-session验证码："+requestForSession.getSession().getAttribute("sessionMobileMsgYZM"));

        }else if(sendSmsResponse.getCode() != null && !sendSmsResponse.getCode().equals("OK")){
            System.out.println("请求失败："+sendSmsResponse.getCode()+"-"+sendSmsResponse.getMessage());
        }
        return  sendSmsResponse;

    }



    /**
     * sendMessageWithMobileNoAndTemplateCode(String phoneNumbers,String templateCode)
     * 报警使用
     * @param phoneNumbers 要发送给的手机号
     * @param templateCode 短信模板-可在短信控制台中找到
     * @param templateParamJson 此参数为 处理短信发送模板所需的所有参数 处理成json串
     * @throws Exception
     */

    public  static SendSmsResponse sendMessageWithMobileNoAndTemplateCode(String phoneNumbers,String templateCode,String templateParamJson)  throws Exception  {

        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）

        //替换成你的AK
        final String accessKeyId = LMTPlatConstants.ali_accessKeyId;//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = LMTPlatConstants.ali_accessKeySecret;//你的accessKeySecret，参考本文档步骤2

        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();

        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(phoneNumbers);//13916805133-15855891980
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("罗美特平台");
        //必填:短信模板-可在短信控制台中找到 -SMS_105980105      -SMS_142947036
        request.setTemplateCode(templateCode);//SMS_142947036-您的校验码：${code}，您正在绑定手机号，成为罗美特平台会员，感谢您的支持！
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败


        //request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");

        // request.setTemplateParam("{\"code\":\"1235\"}");

        request.setTemplateParam(templateParamJson);//短信模板变量替换JSON串,友情提示:如果JSON中需要带换行符,请参照标准的JSON协议。

        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功
            System.out.println("请求成功:"+sendSmsResponse.getCode()+sendSmsResponse.getMessage());
            System.out.println("发送短信成功:"+sendSmsResponse.getCode()+sendSmsResponse.getMessage());

        }else if(sendSmsResponse.getCode() != null && !sendSmsResponse.getCode().equals("OK")){
            System.out.println("请求失败："+sendSmsResponse.getCode()+"-"+sendSmsResponse.getMessage());
        }

        return sendSmsResponse;
    }



    public static void main(String[] args) throws Exception {

        //测试发送-需要request 不可以main方法测试
        //sendMessageWithMobileNoAndTemplateCode("15855891980",SendMessagesUtils.SMS_142947036);


        //SendMessagesUtils.sendMessageWithMobileNoAndTemplateCode("15855891980"  ,SendMessagesUtils.SMS_142947036,templateParamJson);

    }



}
