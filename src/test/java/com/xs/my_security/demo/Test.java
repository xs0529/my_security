package com.xs.my_security.demo;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.crypto.digest.DigestUtil;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * <p>
 *
 * </p>
 *
 * @author XieShuang
 * @version v1.0
 * @since 2018-12-01
 */
public class Test {
    /*public static void main(String[] args) {
        File directory = new File("");//设定为当前文件夹
        try{
            System.out.println(directory.getCanonicalPath());//获取标准的路径
            System.out.println(directory.getAbsolutePath());//获取绝对路径
        }catch(Exception e){}
    }*/

    public static void main(String[] args) {
        String s = UUID.randomUUID().toString();
        System.out.println(s);
        System.out.println(DigestUtil.md5Hex(s+"token"));
    }

    @org.junit.Test
    public void DigestUtilTest(){
        System.out.println(DigestUtil.md5Hex(DigestUtil.md5Hex("123456")+"jkasdjfljdfiojk"));
        System.out.println(DigestUtil.md5Hex("123456"));
    }

    @org.junit.Test
    public void idcardUtilTest(){
        String ID_18 = "500234199612289735";
        //是否有效
        boolean valid = IdcardUtil.isValidCard(ID_18);

//年龄
        DateTime date = DateTime.now();

        int age = IdcardUtil.getAgeByIdCard(ID_18, date);
        Assert.assertEquals(age, 21);

//生日
        String birth = IdcardUtil.getBirthByIdCard(ID_18);
        Assert.assertEquals(birth, "19961228");
//省份
        String province = IdcardUtil.getProvinceByIdCard(ID_18);
        Assert.assertEquals(province, "重1庆");
    }
}
