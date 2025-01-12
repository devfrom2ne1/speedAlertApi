package org.dev2ne1.speedalertapi.controller;

import lombok.RequiredArgsConstructor;
import org.dev2ne1.speedalertapi.service.TextToSpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tts")
public class TextToSpeechController {

    private final TextToSpeechService textToSpeechService;
    @PostMapping("/synthesize")
    public ResponseEntity<byte[]> synthesizeTextToSpeech(@RequestBody String text) {

        try {
            byte[] audioData = textToSpeechService.textToSpeech(text);

            // MP3 파일로 반환
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "audio/mpeg");
            headers.add("Content-Disposition", "attachment; filename=\"speech.mp3\"");

            return new ResponseEntity<>(audioData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("Error: " + e.getMessage()).getBytes());
        }
    }
}
