# 🏨 호텔 에베레스트 (Java Swing)
> **Java Swing과 Oracle DB를 연동한 클래식 호텔 예약 관리 시스템**
> 프로젝트 기간: 2023.06.28 ~ 2023.07.13 (팀 프로젝트)

## 🛠 Tech Stack
- **Language**: Java
- **GUI Framework**: Java Swing, JavaFX (Web View 전용)
- **Database**: Oracle DB (JDBC 연동)
- **Architecture**: Interface 기반의 역할 분담 구조

---

## 📱 담당 기능 및 성과
- **PM & Database**: 프로젝트 팀장, 전체 기획 및 DB 스키마 설계
- **Core Logic**: 대부분의 Java 비즈니스 로직 구현 및 모든 SQL문 작성
- **UI/UX**: 메인 화면 및 상세 화면 UI 설계, **카카오맵 API** 연동 (JavaFX WebView 활용)
- **Connectivity**: **JDBC**를 활용한 안정적인 데이터 트랜잭션 처리 및 관리

---

## 🖼 프로젝트 프리뷰 (토글 클릭)

<details>
<summary>📸 시스템 스크린샷 (클릭)</summary>

![슬라이드1](images/slide_1.jpg)
![슬라이드2](images/slide_2.jpg)
![슬라이드3](images/slide_3.jpg)
![슬라이드4](images/slide_4.jpg)
![슬라이드5](images/slide_5.jpg)
![슬라이드6](images/slide_6.jpg)
</details>

<details>
<summary>🎥 작동 영상 (클릭)</summary>

[작동 영상 보기](images/demo.mp4)
</details>

---

## 💡 깨달은 점
- **기술적 유연성**: Java Swing만으로 부족한 기능(지도 표시 등)을 **JavaFX**와 혼합하여 해결하는 유연한 기술 스택 활용 능력을 길렀습니다.
- **인터페이스의 힘**: **Interface**를 미리 정의함으로써 팀원 간의 결합도를 낮추고 동시 개발이 가능한 환경을 구축했습니다.
- **데이터 핸들링**: 복잡한 예약 로직과 결제 시스템을 **SQL** 프로시저와 쿼리만으로 정교하게 제어하는 경험을 했습니다.

---

## 🔍 주요 코드 (SQL & Interface)

<details>
<summary>💾 SQL Query 구조 보기 (클릭)</summary>

```java
// 예약 가능 객실 조회 (중복 예약 방지 로직)
public static final String SQL9 ="select ROOM_CODE 객실번호, ROOM_TYPE 객실타입, ROOM_SIZE 객실면적, ROOM_COUNT 객실정원, ROOM_INFO 객실정보, ROOM_PRICE||'원' 객실가격 from ROOM_TABLE where ROOM_CODE not in(select ROOM_CODE from RESERVE_TABLE where (RESERVE_START_DAY between ? and ?) or (RESERVE_END_DAY between ? and ?)) order by ROOM_CODE";
```
</details>
