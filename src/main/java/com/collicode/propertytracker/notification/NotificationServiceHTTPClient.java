package com.collicode.propertytracker.notification;

import com.collicode.propertytracker.config.Constants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationServiceHTTPClient {
    private final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String sendEmail(String destinationAddress, String subject, String content, boolean isHtml) {
        return this.sendEmail(
                Constants.DEFAULT_EMAIL_SOURCE_ADDRESS,
                destinationAddress,
                subject,
                content,
                isHtml
        );
    }

    public String sendEmail(
            String sourceAddress,
            String destinationAddress,
            String subject,
            String content,
            boolean isHtml
    ) {

        log.info(
                "Request to send email. sourceAddress : {}, destinationAddress : {}, subject : {}, isHtml : {} ",
                sourceAddress,
                destinationAddress,
                subject,
                isHtml
        );

        String referenceId = UUID.randomUUID().toString();

        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("destinationAddress", destinationAddress);
        messageMap.put("messageType", "EMAIL");
        messageMap.put("subject", subject);
        messageMap.put("message", content);
        messageMap.put("referenceId", referenceId);
        messageMap.put("isHtml", String.valueOf(isHtml));
        messageMap.put("sourceAddress", sourceAddress);

        try {
            String jsonRequest = objectMapper.writeValueAsString(messageMap);

            RequestBody requestBody = RequestBody.create(jsonRequest,
                    MediaType.parse("application/json"));

            Request request = new Request.Builder().url(Constants.NOTIFICATION_URL).post(requestBody).build();
            try (Response ignored = okHttpClient.newCall(request).execute()) {
                log.info("Response: {} ", ignored);
                return referenceId;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendSMS(String msisdn, String content, String referenceId) {
        this.sendSMS(Constants.DEFAULT_SMS_SOURCE_ADDRESS, msisdn, content, referenceId);
    }

    public void sendSMS(String sourceAddress, String msisdn, String content, String referenceId) {
        log.info("Request to send sms. sourceAddress : {}, msisdn : {}", sourceAddress, msisdn);

        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("destinationAddress", msisdn);
        messageMap.put("messageType", "SMS");
        messageMap.put("message", content);
        messageMap.put("referenceId", referenceId);
        messageMap.put("sourceAddress", sourceAddress);

        try {
            String jsonRequest = objectMapper.writeValueAsString(messageMap); //JSON.stringly() - object

            log.info(jsonRequest);

            RequestBody requestBody = RequestBody.create(jsonRequest,
                    MediaType.parse("application/json"));

            Request request = new Request.Builder().url(Constants.NOTIFICATION_URL).post(requestBody).build();

            //
            try (Response ignored = okHttpClient.newCall(request).execute()) {
                log.info("Response: {} ", ignored);
                // TODO: save successfully terminated sm for individual baker for later reporting and billing
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//    String campaignId;
//    String msisdn;
//    String ref;
//    JsonNode  data;


//    @Scheduled(fixedDelay = 500000)
//    public void test() throws JsonProcessingException {
//
//        sendFeedbackQuestions(campaignId,msisdn,ref,data);
//    }
    public void sendFeedbackQuestions(String campaignId, String msisdn, String ref, JsonNode  data) {
        data = objectMapper.createObjectNode();

        Map<String, Object> feedbackMap = new HashMap<>();
        feedbackMap.put("campaignId", 818);
        feedbackMap.put("msisdn", msisdn);
        feedbackMap.put("ref", ref);
        feedbackMap.put("data",data);
        try {
            String jsonRequest = objectMapper.writeValueAsString(feedbackMap); //JSON.stringly() - object
            log.info(jsonRequest);
            RequestBody requestBody = RequestBody.create(jsonRequest,
                    MediaType.parse("application/json"));

            Request request = new Request.Builder().url(Constants.TIARA_CONNECT_ENDPOINT).post(requestBody).build();

            try (Response ignored = okHttpClient.newCall(request).execute()) {
                log.info("Response: {} ", ignored.toString());
            }

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
