package com.moon.io;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * 文件拷贝
 *
 * @author yujiangtao
 * @date 2020/11/5 上午11:12
 */
public class FileCopy {

    /**
     * io
     * @param source
     * @param dest
     * @throws IOException
     */
    public static void copyFileByStream(File source, File dest) throws IOException {
        File file;
        try(InputStream is = new FileInputStream(source);
            OutputStream os = new FileOutputStream(dest);) {
            byte[] buffer = new byte[1024];
            int length;
            while((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }

    /**
     * nio
     * @param source
     * @param dest
     * @throws IOException
     */
    public static void copyFileByChannel(File source, File dest) throws IOException {
        File file;
        try (FileChannel sourceChannel = new FileInputStream(source).getChannel();
            FileChannel targetChannel = new FileOutputStream(dest).getChannel();) {
            for (long count = sourceChannel.size(); count > 0;) {
                long transferred = sourceChannel.transferTo(sourceChannel.position(), count, targetChannel);
                count -= transferred;
            }
        }
    }

    // Files.copy
}
