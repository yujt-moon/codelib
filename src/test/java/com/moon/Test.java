package com.moon;

import com.moon.util.URLUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yujiangtao
 * @date 2018/2/5 20:02
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(URLUtils.decodeUrl("http:\\/\\/203.205.158.69\\/vweixinp.tc.qq.com\\/1007_0791e744278e4456b9a797132537d793.f10.mp4?vkey=6AD417CDAE1875AD4753DF9F0EBB19AB32A0FB9181DC9DBB33D5FF0A523A90927826EAB2E71813F47EEBA91D68BDA61653930F88B79F60A8B05F7E801E01FD2C6CAB934BCCACBAC017DA1B3E4AEBE8200933D523A62D690C&sha=0&save=1"));
        String s1 = "hello";
        String s2 = "he" + new String("llo");
        System.out.println(s1 == s2);

        // 处理日期类型
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;
        try {
            parsed = sdf.parse(sdf.format(now));
        } catch (ParseException e) {
            // 解析错误默认为空
        }
        System.out.println(parsed);
    }

    public void Test() {
        float f = 0.99f;
        double d = 0.99;
    }

    @org.junit.Test
    public void date() {
        System.out.println(new Date());
    }

    @org.junit.Test
    public void testList() {
        Bean bean1 = new Bean("1", 10);
        Bean bean2 = new Bean("2", 20);
        Bean bean3 = new Bean("3", 30);
        Bean bean4 = new Bean("4", 100);
        List<Bean> beans = new ArrayList<Bean>();
        beans.add(bean3);
        beans.add(bean2);
        beans.add(bean1);
        beans.add(bean4);

        int score = 21;


    }

    private class Bean {
        private String levelCode;
        private int limit;

        public Bean(String levelCode, int limit) {
            this.levelCode = levelCode;
            this.limit = limit;
        }

        public String getLevelCode() {
            return levelCode;
        }

        public void setLevelCode(String levelCode) {
            this.levelCode = levelCode;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }
    }
}
