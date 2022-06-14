package com.moon.io;

import com.moon.anno.Url;
import org.junit.Test;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * https://jenkov.com/tutorials/java-nio/channels.html
 * @author yujiangtaoa
 * @date 2022/4/28 下午2:42
 */
public class NIOTest {


    @Test
    @Url("https://jenkov.com/tutorials/java-nio/channels.html")
    public void testFileChannel() throws Exception {
        String pathDir = System.getProperty("user.dir");
        RandomAccessFile file = new RandomAccessFile(pathDir + "/src/main/resources/diskdata.txt", "rw");
//        RandomAccessFile file = new RandomAccessFile("/home/yujt/study/workspace/CodeLib/src/main/resources/diskdata.txt", "rw");
        FileChannel fileChannel = file.getChannel();

        // create buffer with capacity of 48 bytes
        ByteBuffer buffer = ByteBuffer.allocate(48);
        int bytesRead = fileChannel.read(buffer);   // read into buffer

        while (bytesRead != -1) {
            // System.out.println("Read " + bytesRead);
            buffer.flip();  // make buffer ready for read

            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());      // read 1 byte at a time
            }

            buffer.clear();     // make buffer ready for writing
            bytesRead = fileChannel.read(buffer);
        }
        fileChannel.close();
    }

    @Test
    @Url("https://jenkov.com/tutorials/java-nio/channel-to-channel-transfers.html")
    public void testTransfer() throws Exception {
        String pathDir = System.getProperty("user.dir");
        RandomAccessFile fromFile = new RandomAccessFile(pathDir + "/src/main/resources/diskdata.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile(pathDir + "/src/main/resources/tofile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();
        toChannel.transferFrom(fromChannel, position, count);
    }
}
