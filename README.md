
## Api
### 1. 속도 측정 및 위반 여부 확인 엔드포인트:  
- HTTP Method: POST
- URL: /api/speed/check
- Request Parameters:
  - speed (double): 측정된 속도
  - carType (String): 차량 종류
- Response:
  - String: 위반 여부와 벌금액에 대한 메시지

### 2. 차량 종류에 따른 벌금액 계산 엔드포인트:  
- HTTP Method: GET
- URL: /api/fine
- Request Parameters:
  - carType (String): 차량 종류
- Response:
  - double: 벌금액

### 3. TTS API를 사용하여 알림을 보내는 엔드포인트:
- HTTP Method: POST
- URL: /api/notify
- Request Parameters:
  - carType (String): 차량 종류
  - fine (double): 벌금액
- Response:
  - String: 알림 전송 결과
