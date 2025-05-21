package com.vip.service;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3Folder {

    //TODO implement Cache
    public static boolean doesS3FolderExist(S3Client s3, String bucketName, String folderPrefix) {
        ListObjectsV2Request request = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .prefix(folderPrefix.endsWith("/") ? folderPrefix : folderPrefix + "/")
                .maxKeys(1)
                .build();

        ListObjectsV2Response response = s3.listObjectsV2(request);
        return !response.contents().isEmpty();
    }

    public void createS3Directory(S3Client s3, String bucketName, String folderName){
            // Create the "folder" by uploading an empty object
            PutObjectRequest putRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(folderName)
                    .build();

            s3.putObject(putRequest, RequestBody.fromBytes(new byte[0]));

            System.out.println("Directory '" + folderName + "' created in bucket: " + bucketName);
    }
}
