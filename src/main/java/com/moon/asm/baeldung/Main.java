package com.moon.asm.baeldung;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author yujiangtao
 * @date 2020/8/28 下午5:57
 */
public class Main {

    @Test
    public void testAddField() throws Exception {
        CustomClassWriter writer = new CustomClassWriter();
        byte[] bytes = writer.addField();
        writeToFile("/src/main/java/com/moon/asm/baeldung/Demo.class", bytes);
    }

    @Test
    public void testPublicizeMethod() throws Exception {
        CustomClassWriter writer = new CustomClassWriter();
        byte[] bytes = writer.publicizeMethod();
        writeToFile("/src/main/java/com/moon/asm/baeldung/Demo.class", bytes);
    }

    @Test
    public void testAddInterface() throws Exception {
        CustomClassWriter writer = new CustomClassWriter();
        byte[] bytes = writer.addInterface();
        writeToFile("/src/main/java/com/moon/asm/baeldung/Demo.class", bytes);
    }

    private void writeToFile(String filePath, byte[] classByte) throws Exception {
        File file = new File(System.getProperty("user.dir") + filePath);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(classByte);
        fos.flush();
        fos.close();
    }
}
