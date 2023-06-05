package com.github.gun2.beadalbujok.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

public class ImageUtil {

    /**
     * base64로 파일을 저장하고 파일 명 반환
     * @param base64Data
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String saveImageFromBase64(String base64Data, String filePath) throws IOException {
        String uuid = UUID.randomUUID().toString();
        // Base64 디코딩을 통해 이미지 데이터 복원
        byte[] imageData = Base64.getDecoder().decode(base64Data);

        // 이미지 데이터를 파일로 저장
        try (FileOutputStream outputStream = new FileOutputStream(filePath + "/" + uuid)) {
            outputStream.write(imageData);
        }
        return uuid;
    }
}
