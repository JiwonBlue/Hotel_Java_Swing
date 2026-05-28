package team5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.time.LocalDate;

class Fixinfo extends JFrame implements ActionListener {
	Container cp;
	BackImpl back = new BackImpl();

	DefaultTableModel model;
	JTable t;
	Vector<String> columnNames;
	Vector<Vector> rowData;
	BackImpl backEnd;
	JScrollPane scrollpane;
	LocalDate now = LocalDate.now();
	String nowS = now.toString();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	// 수정
	JButton phoneB, pwdB, nameB, idB;
	JPanel FixP, MyP;
	JPanel gogakP, idP, phoneP, pwdP, nameP, bdayP, infoP;
	JTabbedPane jtab;
	JLabel gogakL, idL, phoneL, pwdL, nameL, bdayL, bdayTL, mybdayL, idTL;
	JTextField pwdTF, nameTF, phoneTF, idTF;
	Color c1 = new Color(051, 051, 051); // c1 = 메인 배경 색상
	Color c2 = new Color(135, 206, 250); // 버튼 색: 하늘색

	// 예약정보
	JComboBox Year_start, Month_start, Day_start, Year_end, Month_end, Day_end;
	JPanel searchP;
	JButton searchB, cancleB;
	JLabel toFromL;
	String year, month, day;
	String phone, pwd, id, name;

	String patternY = "yyyy";
	SimpleDateFormat simpleDateFormatY = new SimpleDateFormat(patternY);
	String patternM = "MM";
	SimpleDateFormat simpleDateFormatM = new SimpleDateFormat(patternM);
	String patternD = "dd";
	SimpleDateFormat simpleDateFormatD = new SimpleDateFormat(patternD);

	String sysdateY = simpleDateFormatY.format(new Date());
	String sysdateM = simpleDateFormatM.format(new Date());
	String sysdateD = simpleDateFormatD.format(new Date());

	Fixinfo() {
		init();
	}

	void init() {
		setFixP();
		setMyP();

		jtab = new JTabbedPane();

		jtab.addTab("고객정보 수정", FixP);
		jtab.addTab("내 결제내역", MyP);
		jtab.setBounds(55, 500, 350, 5000);
		jtab.setBackground(new Color(255, 0, 0, 0));
		infoP = new JPanel(null);
		infoP.setLayout(new BorderLayout());
		infoP.setBackground(Color.WHITE);
		infoP.add(jtab);

		try {
			InputStream inputStream1 = new BufferedInputStream(new FileInputStream("ttf/Happiness-Sans-Bold.ttf"));
			Font f1 = Font.createFont(Font.TRUETYPE_FONT, inputStream1);
			gogakL.setFont(f1.deriveFont(Font.PLAIN, 20));
			idL.setFont(f1.deriveFont(Font.PLAIN, 15)); // (라벨이름).setFont(f1);
			idTL.setFont(f1.deriveFont(Font.PLAIN, 12));
			phoneL.setFont(f1.deriveFont(Font.PLAIN, 15));
			phoneB.setFont(f1.deriveFont(Font.PLAIN, 12));
			pwdL.setFont(f1.deriveFont(Font.PLAIN, 15));
			pwdB.setFont(f1.deriveFont(Font.PLAIN, 12));
			nameL.setFont(f1.deriveFont(Font.PLAIN, 15));
			nameB.setFont(f1.deriveFont(Font.PLAIN, 12));
			bdayL.setFont(f1.deriveFont(Font.PLAIN, 15));
			bdayTL.setFont(f1.deriveFont(Font.PLAIN, 12));
			searchB.setFont(f1.deriveFont(Font.PLAIN, 12));
			cancleB.setFont(f1.deriveFont(Font.PLAIN, 12));

		} catch (FontFormatException | IOException e) {
		}
		// Container cp = getContentPane();
		// cp.add(infoP);
		// setUI();
	}

	void setMyP() {

		backEnd = new BackImpl();
		columnNames = backEnd.PaySelectColumnNames(LoginP.id, "nowS", "nowS"); // 처음 겉값처럼 현재 날짜 넣어야함 둘다
		rowData = backEnd.PaySelectRows(LoginP.id, "nowS", "nowS"); // 처음 겉값처럼 현재 날짜 넣어야함 둘다

		MyP = new JPanel();
		MyP.setBackground(c1);

		Year_start = new JComboBox();
		Month_start = new JComboBox();
		Day_start = new JComboBox();
		Year_end = new JComboBox();
		Month_end = new JComboBox();
		Day_end = new JComboBox();
		searchP = new JPanel(new FlowLayout());
		searchP.setBackground(new Color(255, 0, 0, 0));
		toFromL = new JLabel("~");
		searchB = new JButton(" 검    색 ");
		cancleB = new JButton("예약취소");
		toFromL.setForeground(Color.WHITE);
		cancleB.setBackground(c2);
		cancleB.setFocusPainted(false);
		cancleB.setBorderPainted(false);
		searchB.setBackground(c2);
		searchB.setBorderPainted(false);
		searchB.setFocusPainted(false);

		for (int i = 2023; i >= 1900; i--) { // 년
			Year_start.addItem(i);
			Year_end.addItem(i);
		}
		for (int j = 1; j < 13; j++) { // 월

			String jj = String.format("%02d", j);
			Month_start.addItem(jj);
			Month_end.addItem(jj);
		}
		for (int k = 1; k <= 31; k++) { // 일
			String kk = String.format("%02d", k);
			Day_start.addItem(kk);
			Day_end.addItem(kk);
		}

		Year_start.setSelectedItem(sysdateY);
		Month_start.setSelectedItem(sysdateM);
		Day_start.setSelectedItem(sysdateD);
		Year_end.setSelectedItem(sysdateY);
		Month_end.setSelectedItem(sysdateM);
		Day_end.setSelectedItem(sysdateD);

		searchP.add(Year_start);
		searchP.add(Month_start);
		searchP.add(Day_start);
		searchP.add(toFromL);
		searchP.add(Year_end);
		searchP.add(Month_end);
		searchP.add(Day_end);

		model = new DefaultTableModel(rowData, columnNames) {
			public boolean isCellEditable(int row, int column) { // 테이블 수정 못하게
				return false;
			}
		};
		t = new JTable(model);
		t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		t.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 용민이 가로 스크롤
		scrollpane = new JScrollPane(t);
		scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);// 용민이 가로 스크롤
		scrollpane.setPreferredSize(new Dimension(380, 440));// 용민이 가로 스크롤

		MyP.add(searchP);
		MyP.add(scrollpane);
		MyP.add(searchB);
		MyP.add(cancleB);

		searchB.addActionListener(this);
		cancleB.addActionListener(this);
	}

	// 고객수정
	void setFixP() {

		FixP = new JPanel(null);
		FixP.setBackground(c1);

		gogakP = new JPanel(new GridLayout(0, 1));
		gogakP.setBackground(new Color(255, 0, 0, 0)); // 전체 패널 투명하게
		gogakL = new JLabel("회원정보 수정");
		gogakL.setForeground(Color.WHITE);

		phoneP = new JPanel(new GridLayout(0, 3));
		phoneP.setBackground(new Color(255, 0, 0, 0));
		phoneL = new JLabel("휴대폰번호");
		phoneL.setForeground(Color.WHITE);
		phoneTF = new JTextField(11);
		phoneB = new JButton("수정");
		phoneB.setBackground(c2);
		phoneB.setBorderPainted(false);
		phoneB.setFocusPainted(false);
		phoneTF.setText(phone);

		idP = new JPanel(new GridLayout(0, 2));
		idP.setBackground(new Color(255, 0, 0, 0));
		idL = new JLabel("아이디");
		idTL = new JLabel("※ 아이디 변경불가");
		idL.setForeground(Color.WHITE);
		idTL.setForeground(Color.WHITE);

		pwdP = new JPanel(new GridLayout(0, 3));
		pwdP.setBackground(new Color(255, 0, 0, 0));
		pwdL = new JLabel("비밀번호");
		pwdL.setForeground(Color.WHITE);
		pwdTF = new JTextField(11);
		pwdB = new JButton("수정");
		pwdB.setBackground(c2);
		pwdB.setBorderPainted(false);
		pwdB.setFocusPainted(false);
		pwdTF.setText(pwd);

		nameP = new JPanel(new GridLayout(0, 3));
		nameP.setBackground(new Color(255, 0, 0, 0));
		nameL = new JLabel("성명");
		nameL.setForeground(Color.WHITE);
		nameTF = new JTextField(11);
		nameB = new JButton("수정");
		nameB.setBackground(c2);
		nameB.setBorderPainted(false);
		nameB.setFocusPainted(false);
		nameTF.setText(name);

		bdayP = new JPanel(new GridLayout(0, 2));
		bdayP.setBackground(new Color(255, 0, 0, 0));
		bdayL = new JLabel("생년월일");
		bdayTL = new JLabel("※ 생년월일 변경불가");
		bdayL.setForeground(Color.WHITE);
		bdayTL.setForeground(Color.WHITE);

		bdayP.setBounds(67, 350, 250, 30);
		nameP.setBounds(67, 250, 250, 25);
		pwdP.setBounds(67, 200, 250, 25);
		idP.setBounds(67, 150, 250, 30);
		phoneP.setBounds(67, 300, 250, 25);
		gogakP.setBounds(130, 70, 250, 30);

		gogakP.add(gogakL);
		phoneP.add(phoneL);
		phoneP.add(phoneTF);
		phoneP.add(phoneB);
		nameP.add(nameL);
		nameP.add(nameTF);
		nameP.add(nameB);
		pwdP.add(pwdL);
		pwdP.add(pwdTF);
		pwdP.add(pwdB);
		idP.add(idL);
		idP.add(idTL);
		bdayP.add(bdayL);
		bdayP.add(bdayTL);

		FixP.add(gogakP);
		FixP.add(idP);
		FixP.add(phoneP);
		FixP.add(pwdP);
		FixP.add(nameP);
		FixP.add(bdayP);

		nameB.addActionListener(this);
		pwdB.addActionListener(this);
		phoneB.addActionListener(this);

	}

	void setUI() {
		setTitle("hotel everest");
		setSize(400, 700); // 전체 사이즈
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		String StartDay = combineStartDate();
		String EndDay = combineEndDate();

		String changeName = nameTF.getText().trim();
		String changePwd = pwdTF.getText().trim();
		String changePhone = phoneTF.getText().trim();

		if (ae.getSource() == nameB) {
			back.changeName(changeName, LoginP.id);
		} else if (ae.getSource() == pwdB) {
			back.changePwd(changePwd, LoginP.id);
		} else if (ae.getSource() == phoneB) {
			back.changePhone(changePhone, LoginP.id);
		}

		if (obj instanceof JButton) {
			String btn = ae.getActionCommand();
			if (btn.equals(" 검    색 ")) {
				columnNames = backEnd.PaySelectColumnNames(LoginP.id, StartDay, EndDay);
				rowData = backEnd.PaySelectRows(LoginP.id, StartDay, EndDay);
				model = new DefaultTableModel(rowData, columnNames) {
					public boolean isCellEditable(int row, int column) { // 테이블 수정 못하게
						return false;
					}
				};
				t.setModel(model);
			} else if (btn.equals("예약취소")) {
				int row = t.getSelectedRow();
				String myPayCode = t.getModel().getValueAt(row, 0).toString().trim();
				String myReserveCode = t.getModel().getValueAt(row, 1).toString().trim();
				Date dateReserveStartDay;
				Date dateNow;
				try {
					dateReserveStartDay = formatter.parse(back.getMyReserveStartDay(myReserveCode));
					dateNow = formatter.parse(nowS);

					if (t.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(this, "결제내역을 선택해 주시기 바랍니다.");
					} else if (dateNow.equals(dateReserveStartDay)) {
						JOptionPane.showMessageDialog(this, "입실 당일 예약 취소는 불가능합니다.");
					} else if (dateNow.after(dateReserveStartDay)) {
						JOptionPane.showMessageDialog(this, "입실 당일 이후 예약 취소는 불가능합니다.");
					} else {
						back.deletePayCode(myPayCode);
						back.deleteReserveCode(myReserveCode);
						columnNames = backEnd.PaySelectColumnNames(LoginP.id, StartDay, EndDay);
						rowData = backEnd.PaySelectRows(LoginP.id, StartDay, EndDay);
						model = new DefaultTableModel(rowData, columnNames) {
							public boolean isCellEditable(int row, int column) { // 테이블 수정 못하게
								return false;
							}
						};
						t.setModel(model);
					}
				} catch (Exception e) {
				}
			}
		}

	}

	String combineStartDate() {
		String day = null;
		day = Year_start.getSelectedItem().toString() + "-" + Month_start.getSelectedItem().toString() + "-"
				+ Day_start.getSelectedItem().toString();
		return day.trim();
	}

	String combineEndDate() {
		String day = null;
		day = Year_end.getSelectedItem().toString() + "-" + Month_end.getSelectedItem().toString() + "-"
				+ Day_end.getSelectedItem().toString();
		return day.trim();
	}

	/*
	 * public static void main(String[] args) { new Fixinfo(); }
	 */

}