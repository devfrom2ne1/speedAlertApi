package org.dev2ne1.speedalertapi.service;
import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;

@RequiredArgsConstructor
@Service
public class TextToSpeechService {

    public byte[] textToSpeech(String text) throws Exception {
        // 서비스 계정 키 파일 경로
        String keyFilePath = "src/main/resources/gcp-key.json";

        // GoogleCredentials 로드
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(keyFilePath));
        TextToSpeechSettings settings = TextToSpeechSettings.newBuilder()
                .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                .build();

        // TextToSpeechClient 생성
        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create(settings)) {
            // 요청 구성
            SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();

            // 음성 설정
            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                    .setLanguageCode("ko-KR")
                    .setSsmlGender(SsmlVoiceGender.NEUTRAL)
                    .build();

            // 오디오 설정
            AudioConfig audioConfig = AudioConfig.newBuilder()
                    .setAudioEncoding(AudioEncoding.MP3)
                    .build();

            // API 호출
            SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);

            // 음성 데이터를 파일로 저장
            ByteString audioContents = response.getAudioContent();
            try (OutputStream out = new FileOutputStream("output.mp3")) {
                out.write(audioContents.toByteArray());
            }

            return audioContents.toByteArray();
        }
    }
}
