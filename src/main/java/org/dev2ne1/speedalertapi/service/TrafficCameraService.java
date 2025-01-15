package org.dev2ne1.speedalertapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@Service
public class TrafficCameraService {

    private final RestTemplate restTemplate;

    @Value("${api.key}") // application.properties에서 api.key 값을 읽어옵니다.
    private String apiKey;

    public String getTrafficCameraData(String latitude, String longitude) throws IOException {

        // API Key 인코딩
        String encodedApiKey = URLEncoder.encode(apiKey, StandardCharsets.UTF_8.toString());

        // 기본 URL과 파라미터 설정
        URI uri = UriComponentsBuilder.fromHttpUrl("http://api.data.go.kr/openapi/tn_pubr_public_unmanned_traffic_camera_api")
                .queryParam("serviceKey", encodedApiKey)
                .queryParam("pageNo", 1)
                .queryParam("numOfRows", 100)
                .queryParam("type", "xml")
                .queryParam("latitude", latitude)
                .queryParam("longitude", longitude)
                .build(true)
                .toUri();

            log.info("Request URI: {}", uri);
            try {
                // 요청 실행 및 결과 반환
                return restTemplate.getForObject(uri, String.class);
            } catch (Exception e) {
                // 예외 처리
                System.err.println("Error while fetching traffic camera data: " + e.getMessage());
                throw new RuntimeException("Failed to fetch traffic camera data", e);
            }
    }
}