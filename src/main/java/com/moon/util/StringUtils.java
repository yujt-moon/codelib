package com.moon.util;

import com.moon.constant.FilePrefixStrategy;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * @author yujiangtao
 * @since 1.0
 */
public class StringUtils {

    /**
     * 判断当前字符串是否是空串或者为空
     * @param str 需要判断的字符串
     * @return
     */
    public static boolean isBlank(String str) {
        if(str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 判断当前字符串是否不是空串或者为空
     * @param str 需要判断的字符串
     * @return
     */
    public static boolean isNotBlank(String str) {
        if(str != null && !"".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 根据策略类型生成文件前缀
     * @param strategy 文件前缀策略枚举类型
     * @return
     */
    public static String generateFilePrefix(FilePrefixStrategy strategy) {
        String prefixStr = "";
        switch (strategy) {
            case DAYS:
                prefixStr = TimeUtils.formatCurrentDate("yyyyMMddHHmm");
                break;
            case UUID:
                prefixStr = UUID.randomUUID().toString();
                break;
            default:
                break;
        }
        return prefixStr;
    }

    /**
     * 将数据库的字段的格式转换成实体类的格式
     * @param dbName like category_id
     * @return like categoryId
     */
    public static String toCamelFormat(String dbName) {
        Pattern pattern = Pattern.compile("_[a-z]");
        Matcher matcher = pattern.matcher(dbName);
        while (matcher.find()) {
            if(matcher.start() != 0) {
                String group = matcher.group();
                String after = Character.toUpperCase(matcher.group().charAt(1)) + "";
                dbName = dbName.replaceAll(group, after);
            }
        }
        return dbName;
    }
}
