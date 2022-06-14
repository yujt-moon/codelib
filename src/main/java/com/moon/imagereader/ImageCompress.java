package com.moon.imagereader;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author yujiangtao
 * @date 2021/1/15 下午5:22
 */
public class ImageCompress {

    public static void main(String[] args) throws IOException {
        OutputStream os = null;
        Thumbnails.of("/home/yujt/Downloads/6a836597718f7c04e7d65f5d51acdab.jpg").toOutputStream(os);
    }
}
