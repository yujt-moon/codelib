package com.moon.xml;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yujiangtao
 * @date 2021/4/8 上午10:11
 */
public class Test {

    public static void main(String[] args) throws Exception {
//        ObjectMapper xmlMapper = new XmlMapper();
//        String xml = xmlMapper.writeValueAsString(new StaticTemplate());
//        System.out.println(xml);


        Pattern pattern = Pattern.compile("<!\\[CDATA\\[(\\S+)\\]\\]>");
        Matcher matcher = pattern.matcher("<![CDATA[弄好m,haha]]>");
        while (matcher.find()) {
            String content = matcher.group(1);

            System.out.println(content);
        }
    }


}
