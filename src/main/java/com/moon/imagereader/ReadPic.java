package com.moon.imagereader;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class ReadPic {

    /**
     * 导入标签，使用metadata-extractor
     *
     * @param args
     */
    public static void main(String[] args) {
        readPic();
    }

    /**
     * 处理 单张 图片
     *
     * @return void
     * @date 2015-7-25 下午7:30:47
     */
    private static void readPic() {
        File jpegFile = new File("/home/yujt/Pictures/Wallpapers/Autumn_in_Kanas_by_Wang_Jinyu.jpg");
        Metadata metadata;
        try {
            metadata = JpegMetadataReader.readMetadata(jpegFile);
            Iterator<Directory> it = metadata.getDirectories().iterator();
            while (it.hasNext()) {
                Directory exif = it.next();
                Iterator<Tag> tags = exif.getTags().iterator();
                while (tags.hasNext()) {
                    Tag tag = (Tag) tags.next();
                    System.out.println(tag);
                }
            }
        } catch (JpegProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
