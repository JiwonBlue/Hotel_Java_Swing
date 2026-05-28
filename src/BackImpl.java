package team5;

import java.sql.*;
import java.sql.Date;

import javax.swing.table.*;
import javax.swing.JOptionPane;
import java.util.*;

public class BackImpl implements Interface {

	Connection con;
	Statement stmt;
	PreparedStatement pstmt1, pstmt2, pstmt3, pstmt4, pstmt5, pstmt6, pstmt7, pstmt8, pstmt9, pstmt10, pstmt11, pstmt12,
			pstmt13, pstmt14, pstmt15, pstmt16, pstmt17;
	Info i = new Info();
	Sql s = new Sql();
	Vector<String> memInfo = null;

	public BackImpl() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(i.URL, i.USR, i.PWD);
			con.setAutoCommit(false);
			stmt = con.createStatement();
			pln("연결완료");
		} catch (ClassNotFoundException cnfe) {
			pln(cnfe.toString());
		} catch (SQLException se) {
			pln(se.toString());
		}
	}

	@Override
	// SQL1 = "insert into MEMBER_TABLE values(?,?,?,?,?,?)"
	public boolean memberInsert(String id, String pwd, String name, String phone, String birthday, String inOut) {
		ResultSet rs = null;
		try {
			pstmt1 = con.prepareStatement(s.SQL1);
			pstmt1.setString(1, id);
			pstmt1.setString(2, pwd);
			pstmt1.setString(3, name);
			pstmt1.setString(4, phone);
			pstmt1.setString(5, birthday);
			pstmt1.setString(6, inOut);

			rs = pstmt1.executeQuery();

			System.out.println("insert!");
			con.commit();

		} catch (SQLException se) {
			System.out.println("에러발생1" + se);
			if (se.getErrorCode() == 12899) { // 중복된 key가 있을때
				showBox("입력하신 정보가 올바르지 않습니다. 다시 확인해주세요.", "everest hotel");
			}
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se1) {
				System.out.println("실패!");
			}
		}
		return false;
	}

	@Override
	// SQL2 = "select MEMBER_PWD from MEMBER_TABLE where MEMBER_ID=?"
	public boolean checkPwd(String id, String pwd) {
		ResultSet rs = null;
		rs = null;
		try {
			pstmt2 = con.prepareStatement(s.SQL2);
			pstmt2.setString(1, id);
			rs = pstmt2.executeQuery();

			String pwDB = null;

			if (rs.next())
				pwDB = rs.getString(1).trim();
			//System.out.println("pw:" + pwd);
			//System.out.println("pwDB:" + pwDB);

			if (pwd.equals(pwDB)) {
				showBox("환영합니다. everest hotel 입니다.", "everest hotel");
				return true;
			} else if (!pwd.equals(pwDB)) {
				showBox("비밀번호가 일치하지 않습니다.", "everest hotel");
				return false;
			}

		} catch (SQLException se) {
			System.out.println("에러발생2" + se);
			return false;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se1) {
				System.out.println("실패!");
			}
		}
		return false;
	}

	@Override
	// SQL3 = "select MEMBER_ID from MEMBER_TABLE where MEMBER_ID=?"
	public boolean checkId(String id) {
		ResultSet rs = null;
		rs = null;
		try {
			pstmt3 = con.prepareStatement(s.SQL3);
			pstmt3.setString(1, id);
			rs = pstmt3.executeQuery();
			if (rs.next())
				return true;
			else if (!rs.next()) {
				showBox("존재하지 않는 아이디입니다.", "everest hotel");
				LoginP.tfId.setText("");
				LoginP.pwf.setText("");
				LoginP.tfId.requestFocus();
				return false;
			}

		} catch (SQLException se) {
			System.out.println("에러발생3:" + se);
			return false;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se1) {
				System.out.println("실패!");
			}
		}
		return false;
	}

	@Override
	// SQL3 = "select MEMBER_ID from MEMBER_TABLE where MEMBER_ID=?"
	public boolean isAlreadyId(String id) { // 아이디만 검사
		ResultSet rs = null;
		rs = null;
		try {
			pstmt3 = con.prepareStatement(s.SQL3);
			pstmt3.setString(1, id);
			rs = pstmt3.executeQuery();
			if (rs.next()) {
				showBox("이미 사용 중인 아이디입니다.", "everest hotel");
				return false;
			} else if (!rs.next() && id.length() < 12) {
				showBox("회원가입 되었습니다.", "everest hotel");
				return true;
			} else if (!rs.next() && id.length() >= 12) {
				showBox("올바르지 않은 아이디입니다.", "everest hotel");
				return false;
			}

		} catch (SQLException se) {
			System.out.println("에러발생 4:" + se);
			return false;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se1) {
				System.out.println("실패!");
			}
		}
		return false;
	}

	@Override
	// SQLs = "select ROOM_CODE 객실번호, ROOM_TYPE 객실타입, ROOM_SIZE 객실면적, ROOM_COUNT
	// 객실정원, ROOM_INFO 객실정보, ROOM_PRICE||'원' 객실가격 from ROOM_TABLE order by ROOM_CODE
	// asc"
	public Vector<String> selectColumnNames() {
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		Vector v = new Vector();

		try {
			rs = stmt.executeQuery(s.SQLs);
			rsmd = rs.getMetaData();
			int cc = rsmd.getColumnCount();
			int i = 1;
			while (i <= cc) {
				String s = rsmd.getColumnName(i);
				v.add(s);
				i++;
			}
			return v;
		} catch (SQLException se) {
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
			}
		}
	}

	@Override
	// SQLs = "select ROOM_CODE 객실번호, ROOM_TYPE 객실타입, ROOM_SIZE 객실면적, ROOM_COUNT
	// 객실정원, ROOM_INFO 객실정보, ROOM_PRICE||'원' 객실가격 from ROOM_TABLE order by ROOM_CODE
	// asc"
	public Vector<Vector> selectRows() {
		ResultSet rs = null;
		Vector v = new Vector();
		Vector v2 = new Vector();

		try {
			rs = stmt.executeQuery(s.SQLs);
			ResultSetMetaData rsmd = rs.getMetaData();
			int cc = rsmd.getColumnCount();
			while (rs.next()) {
				int i = 1;
				v2 = new Vector();
				while (i <= cc) {
					String s = rs.getString(i);
					v2.add(s);
					i++;
				}
				v.add(v2);
			}
			return v;
		} catch (SQLException se) {
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
			}
		}
	}

	@Override
	// SQL4 = "select
	// MEMBER_PWD,MEMBER_NAME,MEMBER_PHONE,MEMBER_BIRTHDAY,MEMBER_IN_OUT from
	// MEMBER_TABLE where MEMBER_ID=?"
	public void findInfo(String id) {
		ResultSet rs = null;
		rs = null;
		try {
			pstmt4 = con.prepareStatement(s.SQL4);
			pstmt4.setString(1, id);
			con.commit();
			rs = pstmt4.executeQuery();
			while (rs.next()) {
				memInfo = new Vector<String>();
				for (int i = 1; i <= 4; i++) {
					String data = rs.getString(i);
					memInfo.add(data);
				}
			}
		} catch (SQLException se) {
			System.out.println("findInfo() se: " + se);
		}
	}

	@Override
	// SQL5 = "select MEMBER_NAME from MEMBER_TABLE where MEMBER_ID=?"
	public String getName(String id) {
		ResultSet rs = null;
		String name = "";
		try {
			pstmt5 = con.prepareStatement(s.SQL5);
			pstmt5.setString(1, id);
			rs = pstmt5.executeQuery();
			if (rs.next())
				name = rs.getString(1);
			return name;

		} catch (SQLException se) {
			System.out.println("getname() : " + se);
			return name;
		}
	}

	@Override
	// SQL6 = "update MEMBER_TABLE set MEMBER_PWD=? where MEMBER_ID=?"
	public boolean changePwd(String pwd, String id) {

		try {
			pstmt6 = con.prepareStatement(s.SQL6);
			pstmt6.setString(1, pwd);
			pstmt6.setString(2, id);
			int i = pstmt6.executeUpdate();
			if (i > 0) {
				showBox("비밀번호 변경 완료하였습니다.", "everest hotel");
				con.commit();
			} else
				showBox("올바르지 않은 비밀번호입니다.", "everest hotel");
		} catch (SQLException se) {
			System.out.println("에러발생 6:" + se);
			showBox("올바르지 않은 비밀번호입니다.", "everest hotel");
			return false;
		}
		return false;
	}

	@Override
	// SQL7 = "update MEMBER_TABLE set MEMBER_NAME=? where MEMBER_ID=?"
	public boolean changeName(String name, String id) {
		try {
			pstmt7 = con.prepareStatement(s.SQL7);
			pstmt7.setString(1, name);
			pstmt7.setString(2, id);
			int i = pstmt7.executeUpdate();
			if (i > 0) {
				showBox("이름 변경 완료하였습니다.", "everest hotel");
				con.commit();
			} else
				showBox("올바르지 않은 이름입니다.", "everest hotel");
		} catch (SQLException se) {
			System.out.println("에러발생 7:" + se);
			showBox("올바르지 않은 이름입니다.", "everest hotel");
			return false;
		}
		return false;
	}

	@Override
	// SQL8 = "update MEMBER_TABLE set MEMBER_PHONE=? where MEMBER_ID=?"
	public boolean changePhone(String phone, String id) {
		try {
			pstmt8 = con.prepareStatement(s.SQL8);
			pstmt8.setString(1, phone);
			pstmt8.setString(2, id);
			int i = pstmt8.executeUpdate();
			if (i > 0) {
				showBox("핸드폰번호 변경 완료하였습니다.", "everest hotel");
				con.commit();
			} else
				showBox("올바르지 않은 번호입니다.", "everest hotel");
		} catch (SQLException se) {
			System.out.println("에러발생 8:" + se);
			showBox("올바르지 않은 번호입니다.", "everest hotel");
			return false;
		}
		return false;
	}

	@Override
	// SQL9 = "select * from ROOM_TABLE where ROOM_CODE not in(select ROOM_CODE from
	// RESERVE_TABLE where RESERVE_START_DAY >= ? or RESERVE_END_DAY <= date_add(?,
	// interval -1 day)";
	public Vector<String> canReserveColumnNames(String start_day_choice, String end_day_choice) {
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		Vector v = new Vector();
		try {
			pstmt9 = con.prepareStatement(s.SQL9);
			pstmt9.setString(1, start_day_choice);
			pstmt9.setString(2, end_day_choice);
			pstmt9.setString(3, start_day_choice);
			pstmt9.setString(4, end_day_choice);
			rs = pstmt9.executeQuery();
			rsmd = rs.getMetaData();
			int cc = rsmd.getColumnCount();
			int i = 1;
			while (i <= cc) {
				String s = rsmd.getColumnName(i);
				v.add(s);
				i++;
			}
			return v;
		} catch (SQLException se) {
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
			}
		}
	}

	@Override
	// SQL9 = "select * from ROOM_TABLE where ROOM_CODE not in(select ROOM_CODE from
	// RESERVE_TABLE where RESERVE_START_DAY >= ? or RESERVE_END_DAY <= date_add(?,
	// interval -1 day)";
	public Vector<Vector> canReserveRows(String start_day_choice, String end_day_choice) {
		ResultSet rs = null;
		Vector v = new Vector();
		Vector v2 = new Vector();

		try {
			pstmt9 = con.prepareStatement(s.SQL9);
			pstmt9.setString(1, start_day_choice);
			pstmt9.setString(2, end_day_choice);
			pstmt9.setString(3, start_day_choice);
			pstmt9.setString(4, end_day_choice);
			rs = pstmt9.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int cc = rsmd.getColumnCount();
			while (rs.next()) {
				int i = 1;
				v2 = new Vector();
				while (i <= cc) {
					String s = rs.getString(i);
					v2.add(s);
					i++;
				}
				v.add(v2);
			}
			return v;
		} catch (SQLException se) {
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
			}
		}
	}

	@Override
	// SQL10 = "select PAY_CODE 결제코드,RESERVE_CODE 예약코드,PAY_MONEY||'원' 결제금액, PAY_WHAT
	// 결제수단, PAY_BANK 결제은행, to_char(PAY_DAY, 'YYYY-MM-DD') 결제일시 from PAY_TABLE
	// natural join MEMBER_TABLE where MEMBER_ID=? and (PAY_DAY between ? and ?)
	// group by PAY_CODE, RESERVE_CODE, PAY_MONEY, PAY_WHAT, PAY_BANK, PAY_DAY order
	// by PAY_CODE desc";
	public Vector<String> PaySelectColumnNames(String id, String pay_start_day, String pay_end_day) {
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		Vector v = new Vector();
		try {
			pstmt10 = con.prepareStatement(s.SQL10);
			pstmt10.setString(1, id);
			pstmt10.setString(2, pay_start_day);
			pstmt10.setString(3, pay_end_day);
			rs = pstmt10.executeQuery();
			rsmd = rs.getMetaData();
			int cc = rsmd.getColumnCount();
			int i = 1;
			while (i <= cc) {
				String s = rsmd.getColumnName(i);
				v.add(s);
				i++;
			}
			return v;
		} catch (SQLException se) {
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
			}
		}
	}

	@Override
	// SQL10 = "select PAY_CODE 결제코드,RESERVE_CODE 예약코드,PAY_MONEY||'원' 결제금액, PAY_WHAT
	// 결제수단, PAY_BANK 결제은행, to_char(PAY_DAY, 'YYYY-MM-DD') 결제일시 from PAY_TABLE
	// natural join MEMBER_TABLE where MEMBER_ID=? and (PAY_DAY between ? and ?)
	// group by PAY_CODE, RESERVE_CODE, PAY_MONEY, PAY_WHAT, PAY_BANK, PAY_DAY order
	// by PAY_CODE desc";
	public Vector<Vector> PaySelectRows(String id, String pay_start_day, String pay_end_day) {
		ResultSet rs = null;
		Vector v = new Vector();
		Vector v2 = new Vector();

		try {
			pstmt10 = con.prepareStatement(s.SQL10);
			pstmt10.setString(1, id);
			pstmt10.setString(2, pay_start_day);
			pstmt10.setString(3, pay_end_day);
			rs = pstmt10.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int cc = rsmd.getColumnCount();
			while (rs.next()) {
				int i = 1;
				v2 = new Vector();
				while (i <= cc) {
					String s = rs.getString(i);
					v2.add(s);
					i++;
				}
				v.add(v2);
			}
			return v;
		} catch (SQLException se) {
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
			}
		}
	}

	@Override
	// SQL11 = "delete from PAY_TABLE where PAY_CODE=?";
	public boolean deletePayCode(String payCode) {
		try {
			pstmt11 = con.prepareStatement(s.SQL11);
			pstmt11.setString(1, payCode);
			int i = pstmt11.executeUpdate();
			if (i > 0) {
				con.commit();
			} else
				showBox("올바르지 않은 결제코드입니다.", "everest hotel");
		} catch (SQLException se) {
			System.out.println("에러발생 11:" + se);
			showBox("올바르지 않은 결제코드입니다.", "everest hotel");
			return false;
		}
		return false;
	}

	@Override
	// SQL12 = "delete from RESERVE_TABLE where RESERVE_CODE=?";
	public boolean deleteReserveCode(String ReserveCode) {
		try {
			pstmt12 = con.prepareStatement(s.SQL12);
			pstmt12.setString(1, ReserveCode);
			int i = pstmt12.executeUpdate();
			if (i > 0) {
				showBox("예약 및 결제 취소되었습니다.", "everest hotel");
				con.commit();
			} else
				showBox("올바르지 않은 예약코드입니다.", "everest hotel");
		} catch (SQLException se) {
			System.out.println("에러발생 12:" + se);
			showBox("올바르지 않은 예약코드입니다.", "everest hotel");
			return false;
		}
		return false;
	}

	@Override
	// SQL13 = "select RESERVE_START_DAY from RESERVE_TABLE where RESERVE_CODE=?";
	public String getMyReserveStartDay(String reserveCode) {
		ResultSet rs = null;
		rs = null;
		String startDay = "";
		try {
			pstmt13 = con.prepareStatement(s.SQL13);
			pstmt13.setString(1, reserveCode);
			rs = pstmt13.executeQuery();

			while (rs.next()) {
				startDay = rs.getString(1);
				pln(startDay);
			}
			return startDay;

		} catch (SQLException se) {
			System.out.println("에러발생13" + se);
			return "";
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se1) {
				System.out.println("실패!");
			}
		}
	}

	@Override
	// SQL14 = "insert into RESERVE_TABLE
	// values('reserve'||RESERVE_TABLE_SEQ.nextval, ?, ?, ?, ?, ?)";
	public boolean reserveInsert(String id, String room_code, String start_day_choice, String end_day_choice, int reserve_count) {
		ResultSet rs = null;
		try {
			pstmt14 = con.prepareStatement(s.SQL14);
			pstmt14.setString(1, id);
			pstmt14.setString(2, room_code);
			pstmt14.setString(3, start_day_choice);
			pstmt14.setString(4, end_day_choice);
			pstmt14.setInt(5, reserve_count);

			rs = pstmt14.executeQuery();

			System.out.println("예약완료!");
			con.commit();

		} catch (SQLException se) {
			System.out.println("에러발생14" + se);
			showBox("예약 정보에 문제가 있습니다. 다시 확인해주세요", "everest hotel");
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se14) {
				System.out.println("실패!");
			}
		}
		return false;
	}

	@Override
	// SQL15 = "insert into PAY_TABLE values('pay'||PAY_TABLE_SEQ.nextval, ?, ?, ?,
	// ?, ?)";
	public boolean payInsert(String reserve_code, int pay_money, String pay_what, String pay_bank, String pay_day) {
		ResultSet rs = null;
		try {
			pstmt15 = con.prepareStatement(s.SQL15);
			pstmt15.setString(1, reserve_code);
			pstmt15.setInt(2, pay_money);
			pstmt15.setString(3, pay_what);
			pstmt15.setString(4, pay_bank);
			pstmt15.setString(5, pay_day);

			rs = pstmt15.executeQuery();

			System.out.println("결제완료!");
			con.commit();

		} catch (SQLException se) {
			System.out.println("에러발생15" + se);
			showBox("결제 정보에 문제가 있습니다. 다시 확인해주세요", "everest hotel");
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se15) {
				System.out.println("실패!");
			}
		}
		return false;
	}

	@Override
	// SQL16 = "select RESERVE_CODE from (select RESERVE_CODE, rank() over(partition
	// by MEMBER_ID order by RESERVE_CODE desc) as rn from RESERVE_TABLE natural
	// join MEMBER_TABLE where MEMBER_ID=? group by RESERVE_CODE, MEMBER_ID) where
	// rn = 1";
	public String newReserveCode(String id) {
		ResultSet rs = null;
		Vector v = new Vector();
		Vector v2 = new Vector();

		try {
			pstmt16 = con.prepareStatement(s.SQL16);
			pstmt16.setString(1, id);
			rs = pstmt16.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int cc = rsmd.getColumnCount();
			rs.next();
			rs.getString(1);
			return rs.getString(1);

		} catch (SQLException se) {
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
			}
		}
	}

	@Override
	// SQL17 = "select ROOM_PRICE from ROOM_TABLE natural join RESERVE_TABLE where
	// RESERVE_CODE=? group by ROOM_PRICE";
	public int newRoomPrice(String reserveCode) {
		ResultSet rs = null;
		Vector v = new Vector();
		Vector v2 = new Vector();

		try {
			pstmt17 = con.prepareStatement(s.SQL17);
			pstmt17.setString(1, reserveCode);
			rs = pstmt17.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int cc = rsmd.getColumnCount();
			rs.next();
			rs.getInt(1);
			return rs.getInt(1);
		} catch (SQLException se) {
			return -1;
		} catch (NumberFormatException ne) {
			return -1;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
			}
		}
	}

	public void setFieldEmpty() {
		SignupP.tfId.setText("");
		SignupP.tfPw.setText("");
		SignupP.tfName.setText("");
		SignupP.tfphone.setText("");
		SignupP.tfId.requestFocus();
	}

	public void showBox(String contents, String title) {
		JOptionPane.showMessageDialog(null, contents, title, JOptionPane.INFORMATION_MESSAGE);
	}

	void pln(String str) {
		System.out.println(str);
	}

	void p(String str) {
		System.out.print(str);
	}
}
