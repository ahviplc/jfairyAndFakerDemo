package com.lc.demoapp.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;


/**
 * @date 2018年6月11日10:34:27
 * redis操作工具类
 */
public class redisJedisUtils {


    private static Jedis jedis;

    //host
    static final String DATASOURCE_HOST = "localhost";

    //端口号
    static final int DATASOURCE_PORT = 6379;

    //密码
    static final String DATASOURCE_PASS = "123456";

    //Redis Select 命令用于切换到指定的数据库，数据库索引号 index 用数字值指定，以 0 作为起始索引值。
    static final int DATASOURCE_SELECT = 0;


    static {

        //连接本地的 Redis 服务
        jedis = new Jedis(DATASOURCE_HOST, DATASOURCE_PORT);

        //密码
        //jedis.auth(DATASOURCE_PASS);

        jedis.select(DATASOURCE_SELECT);

        if (jedis.isConnected()) {
            System.out.println("连接成功:");

            //查看服务是否运
            System.out.println("服务正在运行: " + jedis.ping());

        } else {
            System.out.println("密钥不对，请核实！");
        }


        Client ccClient = jedis.getClient();

        System.out.println("端口:" + ccClient.getPort() + "-IP:" + ccClient.getHost());


    }


    public static void textString() {
        System.out.println("String类型测试开始：");
        jedis.set("lmt", "lmtkf");
        System.out.println("redis存储字段lmt:" + jedis.get("lmt"));
        jedis.close();
    }

    /**
     * 测试存储多个映射，该方法可用于存储对象
     */
    public static void textHash() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "张三");
        map.put("username", "张三");
        map.put("password", "123123");

        String result = jedis.hmset("01", map);// 设置键为01
        System.out.println("存储后方法的返回值：" + result);
        System.out.println("取出值：" + jedis.hgetAll("01"));
        jedis.close();
    }

    public static void textList() {
        System.out.println("List类型测试开始。。。");
        jedis.lpush("lst", "List01", "List02");
        System.out.println("List中第一个数据为：" + jedis.lindex("lst", 1));
        System.out.println("List中第二个数据为：" + jedis.lindex("lst", 2));
        jedis.close();
    }

    public static void textSet() {
        System.out.println("测试Set类型开始。。。");
        jedis.sadd("set", "set01", "set02");
        Set<String> set = jedis.smembers("set");
        System.out.println("Set中各个数据为：");
        for (String string : set) {
            System.out.println(string);
        }
        jedis.close();
    }

    /**
     * 成员根据sorce进行从小到大的排序。
     */
    public static void textSortSet() {
        System.out.println("开始测试SortSet...");
        Map<String, Double> map = new HashMap<String, Double>();

        map.put("张三", (double) 1);
        map.put("王五", (double) 2);
        map.put("李四", (double) 3);
        jedis.zadd("sortset", map);

        Set<String> set = jedis.zrange("sortset", 0, 2);
        System.out.println("SortSet中的元素：");
        for (String string : set) {
            System.out.println(string);
        }
        jedis.close();
    }

    /**
     * list-对象存储-转成json存入-还可以取出来-转成对象
     *
     * @throws Exception
     */
//    public static void testListOadmin() throws Exception {
//        Oadmin p = new Oadmin();
//        p.setAdmid(Long.valueOf(123456789));
//        p.setAdmpwd("123");
//        p.setAdmuser("lc1");
//        p.setAdmusername("ahviplc1");
//
//
//        Oadmin p1 = new Oadmin();
//        p1.setAdmid(UUID.randomUUID().getLeastSignificantBits());
//        p1.setAdmpwd("123");
//        p1.setAdmuser("lc111");
//        p1.setAdmusername("ahviplc111");
//
//        Oadmin p2 = new Oadmin();
//        p2.setAdmid(UUID.randomUUID().getLeastSignificantBits());
//        p2.setAdmpwd("123");
//        p2.setAdmuser("lc2");
//        p2.setAdmusername("ahviplc2");
//
//        List<Oadmin> oadminList = new ArrayList<Oadmin>();
//
//        oadminList.add(p);
//        oadminList.add(p1);
//        oadminList.add(p2);
//
//        //jackson json处理
//        ObjectMapper mapperList = new ObjectMapper();
//        //list 转 jsonList
//        String oadminListJson = mapperList.writeValueAsString(oadminList);
//        //写入redis
//        jedis.set("oadminListJson", oadminListJson);
//
//        String oadminJson = mapperList.writeValueAsString(p);
//
//        /*{"admid":-6357282822652755682,"admuser":"lc","admusername":"ahviplc","admpwd":"123"}*/
//
//        jedis.set("oadminJson", oadminJson);
//
//        List<Oadmin> oadminList1 = new ArrayList<Oadmin>();
//        oadminList1.add(p1);
//        String oadminListJson1 = mapperList.writeValueAsString(oadminList1);
//
//        jedis.lpush("oadminListJson1", oadminListJson1);
//        System.out.println("oadminListJson1中第一个数据为：" + jedis.lindex("oadminListJson1", 0));
//
//        jedis.rpush("oadminAdmuser", p.getAdmuser());
//        jedis.rpush("oadminAdmuser", p1.getAdmuser());
//        jedis.rpush("oadminAdmuser", p2.getAdmuser());
//
//        jedis.rpush("oadminAdmusername", p.getAdmusername());
//        jedis.rpush("oadminAdmusername", p1.getAdmusername());
//        jedis.rpush("oadminAdmusername", p2.getAdmusername());
//
//        // 获取存储的数据并输出
//        List<String> list = jedis.lrange("oadminAdmusername", 0, 2);
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println("列表项为: " + list.get(i));
//        }
//
//        System.out.println(jedis.lrange("oadminAdmuser", 0, 10));
//
//        System.out.println("testListOadmin");
//
//
//        /*  ObjectMapper支持从byte[]、File、InputStream、字符串等数据的JSON反序列化。 */
//
//        String json = jedis.get("oadminJson");
//        Oadmin oadminOadmin = mapperList.readValue(json, Oadmin.class);
//        System.out.println(oadminOadmin);
//        System.out.println(oadminOadmin.getAdmid());
//
//        jedis.close();
//
//
//    }

    /**
     * main方法 测试
     *
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        System.out.println("--redis--");

        redisJedisUtils.textString();
        redisJedisUtils.textHash();
        redisJedisUtils.textList();
        redisJedisUtils.textSet();
        redisJedisUtils.textSortSet();
        //redisJedisUtils.testListOadmin();
        System.out.println();


    }


}
