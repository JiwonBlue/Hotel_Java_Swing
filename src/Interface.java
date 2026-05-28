package team5;

import java.sql.SQLException;
import java.util.Vector;

public interface Interface {
	boolean memberInsert(String id, String name, String birthday, String phone, String inOut, String pwd ); //SQL1
	boolean checkPwd(String id, String pwd); //SQL2
	
	boolean checkId(String id); //SQL3
	boolean isAlreadyId(String id); //SQL3
	
	Vector<String> selectColumnNames(); //SQLs
	Vector<Vector> selectRows(); //SQLs
	
	void findInfo(String id); //SQL4
	String getName(String id); //SQL5
	boolean changePwd(String pwd, String id); //SQL6
	boolean changeName(String name, String id); //SQL7
	boolean changePhone(String phone, String id); //SQL8
	
	Vector<String> canReserveColumnNames(String start_day_choice, String end_day_choice); //SQL9
	Vector<Vector> canReserveRows(String start_day_choice, String end_day_choice); //SQL9
	
	Vector<String> PaySelectColumnNames(String id, String pay_start_day, String pay_end_day);//SQL10
	Vector<Vector> PaySelectRows(String id, String pay_start_day, String pay_end_day); //SQL10
	
	boolean deletePayCode(String payCode); //SQL11
	boolean deleteReserveCode(String ReserveCode); //SQL12
	String getMyReserveStartDay(String reserveCode); //SQL13
	boolean reserveInsert(String id, String room_code, String start_day_choice, String end_day_choice, int reserve_count); //SQL14
	boolean payInsert(String reserve_code, int pay_money, String pay_what, String pay_bank, String pay_day); //SQL15
	String newReserveCode(String id); //SQL16
	int newRoomPrice(String reserveCode); // SQL17

}
