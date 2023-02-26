package com.gbc.codingmates.listener;

import com.gbc.codingmates.event.SaveFileToS3Event;
import com.gbc.codingmates.utils.S3FileService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveFileToS3EventListener {

    private final S3FileService s3FileService;

    @EventListener
    public void saveFile(SaveFileToS3Event event) {
        try {
            String fileURL = s3FileService.upload(
                event.getMultipartFile(), event.getFileName()
            );
            event.saveFileURL(fileURL);
        } catch (IOException e) {
            throw new RuntimeException("S3 file save ERROR " + event.getFileName());
        }
    }
}
