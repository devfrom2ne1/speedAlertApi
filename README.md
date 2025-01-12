
# 📢 속도 위반 벌금 알림 API
* 소개 : 규정 속도 위반 시, **벌금 금액**을 음성 알림으로 내보내는 API입니다.
* 기간 : 2025.01.11 ~ 2025.01.12

---

## 🚀 기술 스택 (Tech Stack)

### 🌐 Backend
- ![Java](https://img.shields.io/badge/Java-17-007396?style=flat-square&logo=java&logoColor=white)
- ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-6DB33F?style=flat-square&logo=springboot&logoColor=white)
- ![Gradle](https://img.shields.io/badge/Gradle-7.6-02303A?style=flat-square&logo=gradle&logoColor=white)

### ☁️ DevOps & CI/CD
- ![Docker](https://img.shields.io/badge/Docker-20.10.8-2496ED?style=flat-square&logo=docker&logoColor=white)
- ![AWS](https://img.shields.io/badge/AWS-Cloud-232F3E?style=flat-square&logo=amazonaws&logoColor=white)
- ![GitHub Actions](https://img.shields.io/badge/GitHub%20Actions-Automation-2088FF?style=flat-square&logo=githubactions&logoColor=white)

### 🔧 Tools
- ![Postman](https://img.shields.io/badge/Postman-API_Testing-FF6C37?style=flat-square&logo=postman&logoColor=white)
- ![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ%20IDEA-Ultimate-000000?style=flat-square&logo=intellijidea&logoColor=white)
- ![GitHub](https://img.shields.io/badge/GitHub-Repository-181717?style=flat-square&logo=github&logoColor=white)

---

## 🌐 외부 API 설명

### 🎙️ Google TTS (Text to Speech)  

- **기능**: 문자열(예: "벌금 4만원")을 mp3 음성 파일로 변환하여 반환합니다.  
- **용도**: 벌금 금액을 음성으로 사용자에게 알리는 데 활용됩니다.
  


### 🗺️ 전국무인교통단속카메라 표준 데이터  
- **기능**: 위도와 경도를 기반으로 해당 구역의 **제한속도**를 확인합니다.  
- **출처**: [전국무인교통단속카메라 표준 데이터](https://www.data.go.kr/data/15028200/standard.do)  
- **용도**: 속도 측정값과 비교하여 위반 여부를 판단하는 데 사용됩니다.


---

## 📚 API 명세  

### 🚗 속도 측정 및 위반 여부 확인
- **HTTP Method**: `POST`  
- **URL**: `/api/speed/check`  
- **Request Parameters**:  
  - `speed` (`double`): 측정된 속도  
  - `latitude` (`String`): 위도
  - `longitude` (`String`): 경도
- **Response**:  
  - `String`: 벌금액에 대한 메시지  

---

### 🔊 벌금 알림 음성 파일 생성
- **HTTP Method**: `POST`  
- **URL**: `/api/tts/notify`  
- **Request Parameters**:  
  - `fine` (`String`): 발화할 단어(ex. "벌금 8만원")
- **Response**:  
  - `audioData`: 알림 음성 파일(mp3)


---

## 📭 PostMan테스트 

<img width="1268" alt="image" src="https://github.com/user-attachments/assets/1a645a7f-6a2f-45c5-b7d6-5114252b43c5" />
