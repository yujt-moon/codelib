package com.moon;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yujiangtao
 * @date 2021/5/18 下午11:20
 */
public class Test {

    /**
     * 将文件转换成byte数组
     * @param tradeFile
     * @return
     */
    public static byte[] file2byte(File tradeFile){
        byte[] buffer = null;
        try
        {
            FileInputStream fis = new FileInputStream(tradeFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return buffer;
    }

    public static void main(String[] args) {
//        byte[] bytes = file2byte(new File("/home/yujt/Downloads/16k.pcm"));
//        System.out.println(Arrays.toString(bytes));

        // 对本地语音文件进行识别
//        String path = "/home/yujt/Downloads/16k.pcm";
//        AipSpeech client = new AipSpeech("24198150", "kPcWA0MApcpjYFGRr5Y91feZ", "8PB05UBnsAExU0v3ngqymtFZL49UVUCN");
//        AipSpeech client = new AipSpeech("24194598", "d1FqBvYaZ6IrVnGfPBnaLFad", "rSUv7qrZYTdlYe0GxgrMgQNSXhzcXYwf");
//        JSONObject asrRes = client.asr(path, "pcm", 16000, null);
//        System.out.println(asrRes);

//        List<String> list = Arrays.asList("1", "2", "3", "4", "5");
//        List<String> subList = list.subList(1, 3);
//        subList.add("6");
//        subList.forEach(System.out::print);

//        Stu stu = new Stu();
//
//        try {
//            Field name = Stu.class.getDeclaredField("name");
//            //name.setAccessible(true);
//            Object o = name.get(stu);
//            System.out.println(o);
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            e.printStackTrace();
//        }

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        List<String> list = new ArrayList<>();
        list.add("abbb");
        list.add("aaa");
        list.add("abc");

        Collections.sort(list);
        System.out.println(Arrays.toString(list.toArray(new String[0])));
    }
}
