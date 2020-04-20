package com.dmall.component.rbac.shiro.util;

import com.dmall.common.constants.Constants;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @description: 密码工具类
 * @author: created by hang.yu on 2020/1/11 17:12
 */
public class PasswordUtil {

    /**
     * 加密
     */
    public static String getPassword(String salt, String password){
        return new SimpleHash(Constants.MD5,password, ByteSource.Util.bytes(salt),Constants.ENCRYPT_TIME).toString();
    }

    public static boolean checkPassword(String salt, String sourcePassword, String password){
        return password.equals(getPassword(salt, sourcePassword));
    }

}
