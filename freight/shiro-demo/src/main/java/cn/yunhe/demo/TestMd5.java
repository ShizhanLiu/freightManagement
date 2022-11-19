package cn.yunhe.demo;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @Author:Shizhan
 * @ProjectName:freight-codes
 * @currentTime: 2022/9/6 16:48
 */
public class TestMd5 {

    public static void main(String[] args) {
        String pwd = "111";
        String salt = "aaa";

        String pwd_md5 = new Md5Hash(pwd).toString();
        System.out.println("md5加密，不加盐="+pwd_md5);

        Md5Hash pwd_salt = new Md5Hash(pwd, salt);
        System.out.println("md5加密，加盐="+pwd_salt);

        Md5Hash pwd_salt_2 = new Md5Hash(pwd, salt, 2);//md5(md5(pwd+salt))
        System.out.println("md5两次加密，加盐，并散列运算3次="+pwd_salt_2);

        String pwd_md5_salt_3 = new SimpleHash("MD5", pwd, salt, 3).toString();
        System.out.println("md5三次加密，加盐="+pwd_md5_salt_3);



    }

}
