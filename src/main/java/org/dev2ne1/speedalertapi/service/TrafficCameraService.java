package org.dev2ne1.speedalertapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
@RequiredArgsConstructor
@Service
public class TrafficCameraService {

    private final RestTemplate restTemplate;

    @Value("${api.key}") // application.properties에서 api.key 값을 읽어옵니다.
    private String apiKey;

    public String getTrafficCameraData(String latitude, String longitude) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://api.data.go.kr/openapi/tn_pubr_public_unmanned_traffic_camera_api");
        urlBuilder.append("?serviceKey=" + URLEncoder.encode(apiKey, "UTF-8"));
        urlBuilder.append("&pageNo=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&numOfRows=" + URLEncoder.encode("100", "UTF-8"));
        urlBuilder.append("&type=" + URLEncoder.encode("xml", "UTF-8"));

        // 필요한 추가 파라미터들
//        urlBuilder.append("&mnlssRegltCameraManageNo=" + URLEncoder.encode("", "UTF-8"));
//        urlBuilder.append("&ctprvnNm=" + URLEncoder.encode("", "UTF-8"));
//        urlBuilder.append("&signguNm=" + URLEncoder.encode("", "UTF-8"));
//        urlBuilder.append("&roadKnd=" + URLEncoder.encode("", "UTF-8"));
//        urlBuilder.append("&roadRouteNo=" + URLEncoder.encode("", "UTF-8"));
//        urlBuilder.append("&roadRouteNm=" + URLEncoder.encode("", "UTF-8"));
//        urlBuilder.append("&roadRouteDrc=" + URLEncoder.encode("", "UTF-8"));
//        urlBuilder.append("&rdnmadr=" + URLEncoder.encode("", "UTF-8"));
//        urlBuilder.append("&lnmadr=" + URLEncoder.encode("", "UTF-8"));
        urlBuilder.append("&latitude=" + URLEncoder.encode("36.02879792", "UTF-8"));  /*위도*/
        urlBuilder.append("&longitude=" + URLEncoder.encode("128.5295922", "UTF-8"));  /*경도*/
//        urlBuilder.append("&itlpc=" + URLEncoder.encode("", "UTF-8"));
//        urlBuilder.append("&regltSe=" + URLEncoder.encode("", "UTF-8"));
//        urlBuilder.append("&lmttVe=" + URLEncoder.encode("", "UTF-8"));
//        urlBuilder.append("&regltSctnLcSe=" + URLEncoder.encode("", "UTF-8"));
//        urlBuilder.append("&ovrspdRegltSctnLt=" + URLEncoder.encode("", "UTF-8"));
//        urlBuilder.append("&prtcareaType=" + URLEncoder.encode("", "UTF-8"));
//        urlBuilder.append("&installationYear=" + URLEncoder.encode("", "UTF-8"));
//        urlBuilder.append("&institutionNm=" + URLEncoder.encode("", "UTF-8"));
//        urlBuilder.append("&phoneNumber=" + URLEncoder.encode("", "UTF-8"));
//        urlBuilder.append("&referenceDate=" + URLEncoder.encode("", "UTF-8"));
//        urlBuilder.append("&instt_code=" + URLEncoder.encode("", "UTF-8"));

        String url = urlBuilder.toString();
        System.out.println("url : "+url);
        URI uri = null; // URI 객체로 변환
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        System.out.println("uri : "+uri);
        // getForObject를 사용하여 응답을 가져옵니다.
        return restTemplate.getForObject(uri, String.class);
    }
}
