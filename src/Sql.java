package team5;

class Sql {
	//회원가입 정보 넣기
	public static final String SQL1 = "insert into MEMBER_TABLE values(?,?,?,?,?,?)";
	//로그인 아이디에 맞는 비밀번호 확인 -> 오류 비밀번호가 일치하지 않습니다.
	public static final String SQL2 = "select MEMBER_PWD from MEMBER_TABLE where MEMBER_ID=?";
	//아이디가 멤버테이블에 존재하는지 확인 -> 오류 존재하지 않는 아이디입니다. //회원가입시 오류 -> 이미 사용 중인 아이디입니다.
	public static final String SQL3 = "select MEMBER_ID from MEMBER_TABLE where MEMBER_ID=?";
	//객실 테이블 정보 불러오기
	public static final String SQLs = "select ROOM_CODE 객실번호, ROOM_TYPE 객실타입, ROOM_SIZE 객실면적, ROOM_COUNT 객실정원, ROOM_INFO 객실정보, ROOM_PRICE||'원' 객실가격 from ROOM_TABLE order by ROOM_CODE asc";
	//아이디에 맞는 정보들
	public static final String SQL4 = "select MEMBER_PWD,MEMBER_NAME,MEMBER_PHONE,MEMBER_BIRTHDAY,MEMBER_IN_OUT from MEMBER_TABLE where MEMBER_ID=?";
	//아이디에 맞는 이름
	public static final String SQL5 = "select MEMBER_NAME from MEMBER_TABLE where MEMBER_ID=?";
	//현재 아이디 확인하고 비밀번호 바꾸기
	public static final String SQL6="update MEMBER_TABLE set MEMBER_PWD=? where MEMBER_ID=?";
	//현재 아이디 확인하고 핸드폰번호 바꾸기
	public static final String SQL7="update MEMBER_TABLE set MEMBER_NAME=? where MEMBER_ID=?";
	//현재 아이디 확인하고 이름 바꾸기
	public static final String SQL8="update MEMBER_TABLE set MEMBER_PHONE=? where MEMBER_ID=?";
	//예약 가능 객실 정보만 나오게하기
	public static final String SQL9 ="select ROOM_CODE 객실번호, ROOM_TYPE 객실타입, ROOM_SIZE 객실면적, ROOM_COUNT 객실정원, ROOM_INFO 객실정보, ROOM_PRICE||'원' 객실가격 from ROOM_TABLE where ROOM_CODE not in(select ROOM_CODE from RESERVE_TABLE where (RESERVE_START_DAY between ? and ?) or (RESERVE_END_DAY between ? and ?)) order by ROOM_CODE";
	//내 예약 정보 나오게하기
	public static final String SQL10="select PAY_CODE 결제코드,RESERVE_CODE 예약코드,PAY_MONEY||'원' 결제금액, PAY_WHAT 결제수단, PAY_BANK 결제은행, to_char(PAY_DAY, 'YYYY-MM-DD') 결제일시 from PAY_TABLE natural join MEMBER_TABLE where MEMBER_ID=? and (PAY_DAY between ? and ?) group by PAY_CODE, RESERVE_CODE, PAY_MONEY, PAY_WHAT, PAY_BANK, PAY_DAY order by PAY_CODE desc";
	//결제 코드가 맞으면 결제 테이블 행 지우기
	public static final String SQL11="delete from PAY_TABLE where PAY_CODE=?";
	//예약 코드가 맞으면 예약 테이블 행 지우기
	public static final String SQL12="delete from RESERVE_TABLE where RESERVE_CODE=?";
	//예약 코드가 똑같은 행의 예약 시작날 가져오기
	public static final String SQL13="select RESERVE_START_DAY from RESERVE_TABLE where RESERVE_CODE=?";
	//예약 테이블에 값 넣기
	public static final String SQL14="insert into RESERVE_TABLE values('reserve'||RESERVE_TABLE_SEQ.nextval, ?, ?, ?, ?, ?)";
	//결제 테이블에 값 넣기
	public static final String SQL15="insert into PAY_TABLE values('pay'||PAY_TABLE_SEQ.nextval, ?, ?, ?, ?, ?)";
	//방금넣은 예약코드 구하기(내 아이디를 조건으로 찾은 최신 예약코드 1개)
	public static final String SQL16="select RESERVE_CODE from (select RESERVE_CODE, rank() over(partition by MEMBER_ID order by RESERVE_CODE desc) as rn from RESERVE_TABLE natural join MEMBER_TABLE where MEMBER_ID=? group by RESERVE_CODE, MEMBER_ID) where rn = 1";
	//방금넣은 객실가격 구하기
	public static final String SQL17="select ROOM_PRICE from ROOM_TABLE natural join RESERVE_TABLE where RESERVE_CODE=? group by ROOM_PRICE";
}