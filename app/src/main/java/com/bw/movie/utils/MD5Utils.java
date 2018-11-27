package com.bw.movie.utils;

import java.security.MessageDigest;

/**
 * 作者：xujiahui
 * 时间：2018/11/27
 * 作用：MD5Utils
 */
public class MD5Utils {
    /*使用方式：例如，

    在用户注册接口实现类中的 user.setPassword(MD5Util.calc(person.getPassword()));

    在根据账号密码查询实体中:setParameter("password",MD5Util.cacl(password))//密码加密*/
    public final static String calc(String ss) {//MD5加密算法
        String s = ss == null ? "" : ss;//如果为空，则返回""
        char hexDigists[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                'e', 'f'};//字典

        try {
            byte[] strTemp = s.getBytes();//获取二进制
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);//执行加密
            byte[] md = mdTemp.digest();//加密结果
            int j = md.length;//结果长度
            char str[] = new char[j * 2];//字符数组
            int k = 0;
            for (int i = 0; i < j; i++) { //将二进制加密结果转化为字符
                byte byte0 = md[i];
                str[k++] = hexDigists[byte0 >>> 4 & 0xf];
                str[k++] = hexDigists[byte0 & 0xf];

            }
            return new String(str);//输出加密后的字符
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return null;
        }


    }


}

