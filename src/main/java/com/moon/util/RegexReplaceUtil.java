package com.moon.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yujiangtao
 * @date 2021/3/15 下午2:26
 */
public class RegexReplaceUtil {

    public static void main(String[] args) throws Exception {
        File file = new File("/home/yujt/Desktop/test.txt");
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        String oneLine = null;
        String pattern = "'\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}'";
        Pattern r = Pattern.compile(pattern);
        while((oneLine = br.readLine()) != null) {
            // 'YYYY-MM-DD HH24:MI:SS'
            //System.out.println(oneLine);
            Matcher matcher = r.matcher(oneLine);
            List<String> groups = new ArrayList<>();
            while(matcher.find()) {
                String group = matcher.group(0);
                groups.add(group);
            }
            if(groups.size() == 0) {
                // nothing to do
            } else if(groups.size() == 1) {
                oneLine = oneLine.replaceFirst(groups.get(0), "to_date(" + groups.get(0) + " , 'YYYY-MM-DD HH24:MI:SS')");
            } else {
                if (groups.get(0).equals(groups.get(1))) {
                    oneLine = oneLine.replaceAll(groups.get(0), "to_date(" + groups.get(0) + " , 'YYYY-MM-DD HH24:MI:SS')");
                } else {
                    oneLine = oneLine.replaceFirst(groups.get(0), "to_date(" + groups.get(0) + " , 'YYYY-MM-DD HH24:MI:SS')");
                    oneLine = oneLine.replaceFirst(groups.get(1), "to_date(" + groups.get(1) + " , 'YYYY-MM-DD HH24:MI:SS')");
                }
            }

            System.out.println(oneLine);
        }
    }
}
