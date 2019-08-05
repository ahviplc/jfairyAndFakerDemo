package com.lc.demoapp.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 工具类：
 * 1：格式化文件内容 去空格 去注解注释
 * 2：读取文件到list 并返回list
 * 3: 读取文件到list
 * 4: 实现写操作方法 从list写到file
 * @author
 * @dateTime 2018年11月20日11:17:50
 * @see
 * @since 1.0
 */
public class FormatTextUtil {

    public static void main(String[] args) {
        try {
            RandomAccessFile acf = new RandomAccessFile("d://FormatTextUtil.java", "r");
            String line;
            boolean isComment = true;
            while (null != (line = acf.readLine())) {
                // 去除前后空格
                // line.trim();
                String noneSpaceLine = removeAllSpace(line);

                // 简单的注解
                if (isSimpleComment(noneSpaceLine)) {
                    continue;
                }

                // 多行注解
                if (noneSpaceLine.startsWith("/*")) {
                    isComment = true;
                }
                if (isComment && noneSpaceLine.endsWith("*/")) {
                    isComment = false;
                    continue;
                }

                if (!isComment) {
                    System.out.println(noneSpaceLine);
                }
            }

            acf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 是否为单行注释
     * @param line
     * @return
     */
    private static boolean isSimpleComment(String line) {
        if (line.startsWith("//")) {
            return true;
        }
        return false;
    }

    /**
     * 注意: 这里移除所有的空格,如果只想移除前后两端的,请使用: line.trim();
     *
     * @param line
     *            行
     * @return String
     */
    private static String removeAllSpace(String line) {
        StringBuilder b = new StringBuilder(line.length());
        for (char ch : line.toCharArray()) {
            int num = (int) ch;
            if (num != 9 && num != 32) {
                b.append(ch);
            }
        }
        return b.toString();
    }



    /**
     * 读取文件到list
     * 并且返回list
     * @param filePathName  要读取的文件路径
     * @return List<String> 返回listStr
     */
    public static List<String> readFileToList2AndReturnList(String filePathName) throws IOException {
        File file = new File(filePathName);
        System.out.println("文件绝对路径 :"+file.getAbsolutePath());
        List<String> listStr = new ArrayList<String>();
        BufferedReader br = null;
        String str = null;
        try {
            //br = new BufferedReader(new FileReader(file));//这一种写法1 中文会乱码

            //这一种写法2 解决中文乱码问题 备注：因为InputStreamReader和BufferedReader都继承自Reader,而BufferedReader的构造器又是Reader.
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
            br = new BufferedReader(isr);

            while ((str = br.readLine())!= null) {
                listStr.add(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(listStr);
        br.close();//关闭
        return  listStr;
    }


    /**
     * 读取文件到list
     * @param filePathName 要读取的文件路径
     */
    public static void readFileToList2(String filePathName) throws IOException {
        File file = new File(filePathName);
        System.out.println("文件绝对路径 :"+file.getAbsolutePath());
        List<String> listStr = new ArrayList<String>();
        BufferedReader br = null;
        String str = null;
        try {

            //br = new BufferedReader(new FileReader(file));//这一种写法1 中文会乱码

            //这一种写法2 解决中文乱码问题 备注：因为InputStreamReader和BufferedReader都继承自Reader,而BufferedReader的构造器又是Reader.
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
            br = new BufferedReader(isr);

            while ((str = br.readLine())!= null) {
                listStr.add(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(listStr);
        br.close();//关闭
        writeListToFile(listStr,filePathName);// 调用写操作方法
    }


    /**
     * 实现写操作方法
     * 从list写到file
     * @param filePathName 要写入的文件路径
     */
    private static void writeListToFile(List<String> listStr,String filePathName) throws IOException {
        File file = new File(filePathName);// 要写入的文件路径
        if (!file.exists()) {// 判断文件是否存在
            try {
                file.createNewFile();// 如果文件不存在创建文件
                System.out.println("文件"+file.getName()+"不存在已为您创建!");
            } catch (IOException e) {
                System.out.println("创建文件异常!");
                e.printStackTrace();
            }
        } else {
            System.out.println("文件"+file.getName()+"已存在!");
        }

        for (String str : listStr) {// 遍历listStr集合
            FileOutputStream fos = null;
            PrintStream ps = null;
            try {
                fos = new FileOutputStream(file,true);// 文件输出流	追加
                ps = new PrintStream(fos);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String string  = str + "\r\n";// +换行
            ps.print(string); // 执行写操作

            ps.close();	// 关闭流
            fos.close();

        }

        System.out.println("文件写入完毕!");
    }

}
