package com.vip.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vip.dto.PicCountryDTO;
import com.vip.dto.PresignDTO;
import com.vip.dto.ReqPresignedDTO;
import com.vip.model.PicCountry;
import com.vip.repository.PicCountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadPicService {
    @Autowired
    private S3Folder s3Folder;

    @Autowired
    private PicCountryRepository picCountryRepository;

    public PresignDTO getPresignedURL(ReqPresignedDTO reqPresignedDTO) throws JsonProcessingException {
        Region region = Region.US_EAST_1; // Use your bucket's region
        String bucketName = reqPresignedDTO.getCountry()+"";
        String key = "your-object-key";
        PresignDTO presignDTO = new PresignDTO();

        try (S3Presigner presigner = S3Presigner.builder()
                .region(region)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build()) {

            // Create S3 client
            S3Client s3 = S3Client.builder()
                    .region(Region.US_EAST_1) // Change region as needed
                    .credentialsProvider(ProfileCredentialsProvider.create())
                    .build();

            //LocalDate currentDate = LocalDate.now();
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
            //String formattedDate = currentDate.format(formatter);

            String folderName = reqPresignedDTO.getPlaceId()+"/"+reqPresignedDTO.getMmyyyy()+"/"; // Must end with "/"

            if (!s3Folder.doesS3FolderExist(s3, bucketName, folderName)) {
                s3Folder.createS3Directory(s3, bucketName, folderName);
            }

            List<String> lst = new ArrayList<>();
            int n = reqPresignedDTO.getNro();
            for(int i=0;i<n;i++){
                GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                        .bucket(bucketName+"/"+folderName)
                        .key(key)
                        .build();

                GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                        .signatureDuration(Duration.ofMinutes(10))
                        .getObjectRequest(getObjectRequest)
                        .build();

                URL presignedUrl = presigner.presignGetObject(presignRequest).url();
                System.out.println("Presigned URL: " + presignedUrl);
                lst.add(presignedUrl.toString());
            }
            long timeCreated = System.currentTimeMillis();
            presignDTO.setUrls(lst);
            presignDTO.setTime(timeCreated+"");

            savePresignedToPicCountry(reqPresignedDTO, lst, timeCreated);
        }
        return presignDTO;
    }

    private void savePresignedToPicCountry(ReqPresignedDTO reqPresignedDTO, List<String> urls, long timeCreated) throws JsonProcessingException {

        PicCountryDTO picCountryDTO = new PicCountryDTO();
        picCountryDTO.setUrls(urls);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(picCountryDTO);

        System.out.println("JSON: " + json);

        PicCountry picCountry = new PicCountry();
        picCountry.setUserId(reqPresignedDTO.getUserUUID());
        picCountry.setPlaceId(reqPresignedDTO.getPlaceId()+"_"+timeCreated);
        picCountry.setJson(json);
        picCountry.setStatus("1");

        picCountryRepository.save(picCountry);
    }


}
