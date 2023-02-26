package com.gbc.codingmates.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class SaveFileToS3Event extends ApplicationEvent {

    private final MultipartFile multipartFile;
    private final String fileName;
    private String fileURL;

    protected SaveFileToS3Event(MultipartFile multipartFile, String fileName) {
        super(SaveFileToS3Event.class.getName());
        this.multipartFile = multipartFile;
        this.fileName = fileName;
    }

    public static SaveFileToS3Event of(MultipartFile multipartFile, String fileName) {
        SaveFileToS3Event event = new SaveFileToS3Event(multipartFile, fileName);

        validate(event);

        return event;
    }

    private static void validate(SaveFileToS3Event event) {
        if (ObjectUtils.isEmpty(event.getMultipartFile())) {
            throw new IllegalArgumentException(event.getSource() + " require multipartFile");
        }
        if (ObjectUtils.isEmpty(event.getFileName())) {
            throw new IllegalArgumentException(event.getSource() + " require fileName");
        }
    }

    public void saveFileURL(String fileURL) {
        this.fileURL = fileURL;
    }
}
