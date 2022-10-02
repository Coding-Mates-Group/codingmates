package com.gbc.codingmates.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;


@ActiveProfiles("test")
@SpringBootTest
class FileHandlerTest {

    public static final String TEST_PNG_NAME = "testImage.png";
    public static final String TEST_GIF_NAME = "testImage.gif";
    public static final String TEST_JPG_NAME = "testImage.jpg";
    public static final String TEST_PROFILE_ROOT_PATH = "/test/profiles";
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private FileHandler fileHandler;


    @Test
    public void userPngProfileSaveTest() throws IOException {
        //given
        Resource resource = resourceLoader.getResource("classpath:" + TEST_PNG_NAME);
        MultipartFile file = new MockMultipartFile("file", "test.png", "image/png",
            resource.getInputStream());

        //when
        String fileRelativePath = fileHandler.saveProfileImage(file, -1L);

        //then
        File userProfile = new File(
            FileHandler.PROJECT_SRC_PATH + fileRelativePath);
        assertThat(userProfile.exists()).isTrue();

        userProfile.delete();
    }

    @Test
    public void userGIFProfileSaveTest() throws IOException {
        //given
        Resource resource = resourceLoader.getResource("classpath:" + TEST_GIF_NAME);
        MultipartFile file = new MockMultipartFile("file", "test.png", "image/gif",
            resource.getInputStream());

        //when
        String fileRelativePath = fileHandler.saveProfileImage(file, -1L);

        //then
        File userProfile = new File(
            FileHandler.PROJECT_SRC_PATH + fileRelativePath);
        assertThat(userProfile.exists()).isTrue();

        userProfile.delete();
    }

    @Test
    public void userJpgProfileSaveTest() throws IOException {
        //given
        Resource resource = resourceLoader.getResource("classpath:" + TEST_JPG_NAME);
        MultipartFile file = new MockMultipartFile("file", "test.png", "image/jpg",
            resource.getInputStream());

        //when
        String fileRelativePath = fileHandler.saveProfileImage(file, -1L);

        //then
        File userProfile = new File(
            FileHandler.PROJECT_SRC_PATH + fileRelativePath);
        assertThat(userProfile.exists()).isTrue();

        userProfile.delete();
    }

}