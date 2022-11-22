package com.gbc.codingmates.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.util.StringUtils;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EnvironmentVariableCredentialsProvider implements AWSCredentialsProvider {

//    @Override
//    public AWSCredentials getCredentials() {
//        String accessKey = System.getenv("AWS_ACCESS_KEY_ID");
//        if (accessKey == null) {
//            accessKey = System.getenv("AWS_ACCESS_KEY");
//        }
//
//        String secretKey = System.getenv("AWS_SECRET_KEY");
//        if (secretKey == null) {
//            secretKey = System.getenv("AWS_SECRET_ACCESS_KEY");
//        }
//
//        accessKey = StringUtils.trim(accessKey);
//        secretKey = StringUtils.trim(secretKey);
//        String sessionToken = StringUtils.trim(System.getenv("AWS_SESSION_TOKEN"));
//        if (!StringUtils.isNullOrEmpty(accessKey) && !StringUtils.isNullOrEmpty(secretKey)) {
//            return (AWSCredentials) (sessionToken == null ?
//                    new BasicAWSCredentials(accessKey, secretKey) :
//                    new BasicSessionCredentials(accessKey, secretKey, sessionToken));
//        }
//
//    }
//
//    @Override
//    public void refresh() {
//
//    }
}
