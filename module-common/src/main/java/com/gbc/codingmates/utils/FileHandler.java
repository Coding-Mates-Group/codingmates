package com.gbc.codingmates.utils;

import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileHandler {

    @Value("${spring.member-profile.path}")
    public String MEMBER_PROFILE_ABSOLUTE_PATH;
    public static final String PROJECT_SRC_PATH = new File("").getAbsolutePath();
    public static final String FILE_EXTENSION_JPG = ".jpg";
    public static final String FILE_EXTENSION_PNG = ".png";
    public static final String FILE_EXTENSION_GIF = ".gif";

//    //"^([\\S]+(\\.(?i)(jpg|png|gif|bmp|java))$)"
//    public static final Pattern LINUX_FILE_PATTERN = Pattern.compile("^/|(/[\\w-]+)+$");

    public static String getRandomProfilePath() {
        int imageNumber = (int) (Math.random() * 10 % 6 + 1); // 1 ~ 6
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PROJECT_SRC_PATH);
        stringBuilder.append("/images/default/");
        stringBuilder.append(imageNumber);
        stringBuilder.append(".png");
        return stringBuilder.toString();
    }

    public String saveProfileImage(MultipartFile multipartFile, Long memberId) {
        if (multipartFile.isEmpty()) {
            throw new IllegalArgumentException("profile image is empty");
        }

        String fileExtensionType = getFileExtensionByContentType(
            multipartFile.getContentType());

        save(multipartFile,
            stringAppender(new Object[]{PROJECT_SRC_PATH, MEMBER_PROFILE_ABSOLUTE_PATH}),
            stringAppender(new Object[]{memberId, fileExtensionType}));

        String relativePath = stringAppender(
            new Object[]{MEMBER_PROFILE_ABSOLUTE_PATH, "/", memberId, fileExtensionType});

        return relativePath;
    }

    private void save(MultipartFile multipartFile, String dirPath, String fileName) {
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = stringAppender(new String[]{dirPath, "/", fileName});

        file = new File(filePath);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            throw new IllegalArgumentException(
                "Error occurs while save profile image" + e.getMessage());
        }
    }

    private String stringAppender(Object[] arr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            stringBuilder.append(arr[i]);
        }
        return stringBuilder.toString();
    }

    private String getFileExtensionByContentType(String contentType) {
        if (contentType.contains("image/jpeg") || contentType.contains("image/jpg")) {
            return FILE_EXTENSION_JPG;
        } else if (contentType.contains("image/png")) {
            return FILE_EXTENSION_PNG;
        } else if (contentType.contains("image/gif")) {
            return FILE_EXTENSION_GIF;
        } else {
            throw new IllegalArgumentException("profile extention type must be jpeg,png,gif");
        }
    }


}
