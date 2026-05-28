package team5;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.*;
import java.time.LocalDate;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
//import java.time.LocalDate;

public class ReservationP extends JFrame implements ActionListener {

	BackImpl back = new BackImpl();
	DefaultTableModel model;
	JTable t;
	Vector<String> columnNames;
	Vector<Vector> rowData;
	BackImpl backEnd;
	JScrollPane scrollpane;
	LocalDate now = LocalDate.now();
	String nowS = now.toString();

	Color c1 = new Color(051, 051, 051); // c1 = 메인 배경 색상
	Color c2 = new Color(135, 206, 250); // 버튼 색: 하늘색

	JPanel resP = new JPanel();
	JLabel startDateLbl = new JLabel("이용 시작일");

	static JTextField startDateField = new JTextField();
	ImageIcon icon = new ImageIcon("imgs/Cal.png");
	Image im = icon.getImage();
	Image im2 = im.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
	ImageIcon icon2 = new ImageIcon(im2);
	JLabel startCalendar = new JLabel(icon2);
	JLabel arriveDateLbl = new JLabel("이용 종료일");

	static JTextField arriveDateField = new JTextField();

	JLabel humanLbl = new JLabel("인원수");

	static Integer audult[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
	static DefaultComboBoxModel<Integer> adultModel = new DefaultComboBoxModel<Integer>(audult);
	static JComboBox<Integer> audultAge = new JComboBox<Integer>(adultModel);

	JLabel payLbl = new JLabel("결제수단");

	static String cash[] = { "카드", "현금" };
	static DefaultComboBoxModel<String> cashModel = new DefaultComboBoxModel<String>(cash);
	static JComboBox<String> cashrd = new JComboBox<String>(cashModel);

	JLabel bankLbl = new JLabel("결제은행");

	static String paybank[] = { "미지정", "우리", "국민", "하나", "농협", "수협", "신한", "기업" };
	static DefaultComboBoxModel<String> bankModel = new DefaultComboBoxModel<String>(paybank);
	static JComboBox<String> paba = new JComboBox<String>(bankModel);

	JButton cancelBtn = new JButton(" 검   색 ");
	JButton nextBtn = new JButton("예약/결제");

	int calendarWindowTest = 0; // calendar가 켜졌을때는 한번 더 open되지 않도록 제한사항을 부여
	int clickCheck = 0; // 달력에서 클릭한 횟수를 통해 예약날짜가 체크되는 순서를 정한다.
	static int humanCount; // 사람 명수
	static int payObj;
	static int bankPaybank;

	public ReservationP() {
		backEnd = new BackImpl();
		columnNames = backEnd.selectColumnNames(); // 예약창에 처음 들어와서는 모든 객실을 보여준다.
		rowData = backEnd.selectRows(); // 예약창에 처음 들어와서는 모든 객실을 보여준다.
		//System.out.println(columnNames);

		setLayout(new BorderLayout());

		add("Center", resP);
		resP.setLayout(null);
		resP.setBackground(c1);

		resP.add(startDateLbl).setBounds(50, 20, 70, 30);
		resP.add(startDateField).setBounds(150, 20, 150, 30);
		resP.add(startCalendar).setBounds(320, 20, 40, 30);

		resP.add(arriveDateLbl).setBounds(50, 80, 70, 30);
		resP.add(arriveDateField).setBounds(150, 80, 150, 30);

		resP.add(humanLbl).setBounds(10, 450, 40, 30);
		resP.add(audultAge).setBounds(60, 450, 40, 30);
		resP.add(payLbl).setBounds(120, 450, 50, 30);
		resP.add(bankLbl).setBounds(240, 450, 50, 30);
		resP.add(cashrd).setBounds(180, 450, 50, 30);
		resP.add(paba).setBounds(300, 450, 75, 30); /* resP.add(audultLbl).setBounds(125,450,5,30); */
		audultAge.setBackground(Color.WHITE);

		resP.add(nextBtn).setBounds(200, 510, 90, 30);
		resP.add(cancelBtn).setBounds(100, 510, 90, 30);

		cancelBtn.setBackground(c2);

		nextBtn.setBackground(c2);

		arriveDateLbl.setForeground(Color.WHITE);
		humanLbl.setForeground(Color.WHITE);
		startDateLbl.setForeground(Color.WHITE);
		payLbl.setForeground(Color.WHITE);
		bankLbl.setForeground(Color.WHITE);

		model = new DefaultTableModel(rowData, columnNames) {
			public boolean isCellEditable(int row, int column) { // 테이블 수정 못하게
				return false;
			}
		};
		t = new JTable(model);
		t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 테이블 한 줄만 선택할 수 있게
		t.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 용민이 가로 스크롤
		t.getColumn("객실정보").setPreferredWidth(100);
		scrollpane = new JScrollPane(t);

		resP.add(scrollpane).setBounds(0, 120, 400, 300);

		try {
			InputStream inputStream1 = new BufferedInputStream(new FileInputStream("ttf/Happiness-Sans-Bold.ttf"));
			Font f1 = Font.createFont(Font.TRUETYPE_FONT, inputStream1);
			startDateLbl.setFont(f1.deriveFont(Font.PLAIN, 12));
			startDateField.setFont(f1.deriveFont(Font.PLAIN, 12));
			arriveDateLbl.setFont(f1.deriveFont(Font.PLAIN, 12));
			arriveDateField.setFont(f1.deriveFont(Font.PLAIN, 12));
			humanLbl.setFont(f1.deriveFont(Font.PLAIN, 12));
			audultAge.setFont(f1.deriveFont(Font.PLAIN, 12));
			payLbl.setFont(f1.deriveFont(Font.PLAIN, 12));
			bankLbl.setFont(f1.deriveFont(Font.PLAIN, 12));
			cashrd.setFont(f1.deriveFont(Font.PLAIN, 12));
			paba.setFont(f1.deriveFont(Font.PLAIN, 12));
			cancelBtn.setFont(f1.deriveFont(Font.PLAIN, 12));
			nextBtn.setFont(f1.deriveFont(Font.PLAIN, 12));

		} catch (FontFormatException | IOException e) {
		}

		startCalendar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				me.getSource();
				if (calendarWindowTest == 0) {
					new CustomCalendar();
					calendarWindowTest = 1;
				}
			}
		});

		cancelBtn.addActionListener(this);
		nextBtn.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae) {
		try {
			Object obj = ae.getSource();
			String payobj = combinepayobj();
			String bankpay = combinebankpay();
			int humanCount = getHumanCount();

			String startDate = startDateField.getText().trim();
			String arriveDate = arriveDateField.getText().trim();

			if (obj instanceof JButton) {
				String btn = ae.getActionCommand();

				if (btn.equals(" 검   색 ")) {
					if (startDateField.getText().equals("") || arriveDateField.getText().equals("")) {
						JOptionPane.showMessageDialog(this, "날짜를 선택해 주시기바랍니다");
					} else if (startDateField.getText().equals(arriveDateField.getText())) {
						JOptionPane.showMessageDialog(this, "같은 날짜 선택은 불가능합니다.");
					} else if (errorCheck() == 1) {
						JOptionPane.showMessageDialog(this, "당일보다 이전일은 선택할 수 없습니다");
					} else if (dayMinusCheck() == 1) {
						JOptionPane.showMessageDialog(this, "이용 시작일보다 이용 종료일이 이전일 수는 없습니다");
					} else {
						columnNames = backEnd.canReserveColumnNames(startDate, arriveDate);
						rowData = backEnd.canReserveRows(startDate, arriveDate);
						model = new DefaultTableModel(rowData, columnNames) {
							public boolean isCellEditable(int row, int column) { // 테이블 수정 못하게
								return false;
							}
						};
						t.setModel(model);
						t.getColumn("객실정보").setPreferredWidth(100);
						this.setVisible(false);
					}
				}
			}

			if (obj instanceof JButton) {
				String btn2 = ae.getActionCommand();

				if (btn2.equals("예약/결제")) {

					if (startDateField.getText().equals("") || arriveDateField.getText().equals("")) {
						JOptionPane.showMessageDialog(this, "날짜를 선택해 주시기바랍니다");
					} else if (startDateField.getText().equals(arriveDateField.getText())) {
						JOptionPane.showMessageDialog(this, "같은 날짜 선택은 불가능합니다.");
					} else if (errorCheck() == 1) {
						JOptionPane.showMessageDialog(this, "당일보다 이전일은 선택할 수 없습니다");
					} else if (dayMinusCheck() == 1) {
						JOptionPane.showMessageDialog(this, "이용 시작일보다 이용 종료일이 이전일 수는 없습니다");
					} else if (humanCount() == 1) {
						JOptionPane.showMessageDialog(this, "본인을 포함, 8명을 초과하여 예약 할 수 없습니다");
					} else if (t.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(this, "객실을 선택해 주세요");
					} else if (humanCount > Integer
							.parseInt(String.valueOf(t.getModel().getValueAt(t.getSelectedRow(), 3)))) {
						JOptionPane.showMessageDialog(this, "객실 정원을 초과하는 인원입니다.");
					} else {
						int row = t.getSelectedRow();
						int col = t.getSelectedColumn();
						for (int i = 0; i < t.getColumnCount(); i++) {
							//System.out.print(t.getModel().getValueAt(row, i) + "\t");
							// 예약 생성 필요
						}
						//System.out.println(humanCount + payobj + bankpay);

						String ChoiceRoomCode = t.getModel().getValueAt(row, 0).toString().trim();
						back.reserveInsert(LoginP.id, ChoiceRoomCode, startDate, arriveDate, humanCount);

						String newReserveCode = back.newReserveCode(LoginP.id);
						int newRoomPrice = back.newRoomPrice(newReserveCode);

						Date startDateFormat = new SimpleDateFormat("yyyy/MM/dd").parse(startDate);
						Date arriveDateFormat = new SimpleDateFormat("yyyy/MM/dd").parse(arriveDate);
						long diffSec = (arriveDateFormat.getTime() - startDateFormat.getTime()) / 1000; // 초 차이
						long diffDays = diffSec / (24 * 60 * 60); // 일자수 차이
						int dayCount = Long.valueOf(diffDays).intValue();
						int newPayMoney = newRoomPrice * dayCount;

						back.payInsert(newReserveCode, newPayMoney, payobj, bankpay, nowS);
						JOptionPane.showMessageDialog(this, "예약/결제 되었습니다.");
						columnNames = backEnd.canReserveColumnNames(startDate, arriveDate);
						rowData = backEnd.canReserveRows(startDate, arriveDate);
						model = new DefaultTableModel(rowData, columnNames) {
							public boolean isCellEditable(int row, int column) { // 테이블 수정 못하게
								return false;
							}
						};
						t.setModel(model);
						t.getColumn("객실정보").setPreferredWidth(100);
						this.setVisible(false);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public int humanCount() {
		int result = 0;
		humanCount = (int) audultAge.getSelectedItem();
		if (humanCount > 8)
			result = 1;
		return result;
	}

	int getHumanCount() {
		humanCount = (int) audultAge.getSelectedItem();
		return humanCount;
	}

	String combinepayobj() {
		String cpo = null;
		cpo = cashrd.getSelectedItem().toString();
		return cpo.trim();
	}

	String combinebankpay() {
		String cbp = null;
		cbp = paba.getSelectedItem().toString();
		return cbp.trim();
	}

	/// 예약 날짜가 도착 날짜보다 뒤로 설정해보는 엉뚱한 사람을 체크해라!
	public int dayMinusCheck() {
		int result = 0;
		int start = Integer.valueOf(startDateField.getText().replace("/", ""));
		int arrive = Integer.valueOf(arriveDateField.getText().replace("/", ""));
		int minusCheck = arrive - start;
		if (minusCheck < 0) {
			result = 1;
		}
		return result;
	}

	// 예약날짜 선택을 당일보다 전일로 설정 할 경우 걸러낸다
	public int errorCheck() {
		int result = 0;
		int start = Integer.valueOf(startDateField.getText().replace("/", ""));
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		int fmt = Integer.valueOf(format.format(date));
		int dateCheck = fmt - start;
		if (dateCheck > 0) {
			result = 1;
		}
		return result;
	}

	class CustomCalendar extends JFrame implements ActionListener, WindowListener {
		// 상단 지역
		JPanel bar = new JPanel();
		JButton lastMonth = new JButton("◀");

		JComboBox<Integer> yearCombo = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> yearModel = new DefaultComboBoxModel<Integer>();

		JLabel yLbl = new JLabel("년 ");

		JComboBox<Integer> monthCombo = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> monthModel = new DefaultComboBoxModel<Integer>();
		JLabel mLbl = new JLabel("월");
		JButton nextMonth = new JButton("▶");
		// 중앙 지역
		JPanel center = new JPanel(new BorderLayout());
		// 중앙 상단 지역
		JPanel cntNorth = new JPanel(new GridLayout(0, 7));
		// 중앙 중앙 지역
		JPanel cntCenter = new JPanel(new GridLayout(0, 7));

		// 요일 입력
		String dw[] = { "일", "월", "화", "수", "목", "금", "토" };

		Calendar now = Calendar.getInstance();
		int year, month, date;

		public CustomCalendar() {
			year = now.get(Calendar.YEAR);// 2021년
			month = now.get(Calendar.MONTH) + 1; // 0월 == 1월
			date = now.get(Calendar.DATE);
			for (int i = year; i <= year + 50; i++) {
				yearModel.addElement(i);
			}
			for (int i = 1; i <= 12; i++) {
				monthModel.addElement(i);
			}
			////////////////////////// 프레임///////////////////////////////////////////
			// 상단 지역
			add("North", bar);
			bar.setLayout(new FlowLayout());
			bar.setSize(300, 400);
			bar.add(lastMonth);
			////////////////////////// 달력/////////////////////////////////////////////
			bar.add(yearCombo);
			yearCombo.setModel(yearModel);
			yearCombo.setSelectedItem(year);

			bar.add(yLbl);
			bar.add(monthCombo);
			monthCombo.setModel(monthModel);
			monthCombo.setSelectedItem(month);

			bar.add(mLbl);
			bar.add(nextMonth);
			bar.setBackground(new Color(0, 210, 180));

			// 중앙 지역
			add("Center", center);
			// 중앙 상단 지역
			center.add("North", cntNorth);
			for (int i = 0; i < dw.length; i++) {
				JLabel dayOfWeek = new JLabel(dw[i], JLabel.CENTER);
				if (i == 0)
					dayOfWeek.setForeground(Color.red);
				else if (i == 6)
					dayOfWeek.setForeground(Color.blue);
				cntNorth.add(dayOfWeek);
			}

			// 중앙 중앙 지역
			center.add("Center", cntCenter);
			dayPrint(year, month);

			// 이벤트
			yearCombo.addActionListener(this);
			monthCombo.addActionListener(this);
			lastMonth.addActionListener(this);
			nextMonth.addActionListener(this);
			addWindowListener(this);

			// frame 기본 셋팅
			setTitle("Hotel everest calendar");
			setSize(400, 300);
			setVisible(true);
			setResizable(false);
			setLocation(760, 320);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}

		// 이벤트 처리
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if (obj instanceof JButton) {
				JButton eventBtn = (JButton) obj;
				int yy = (Integer) yearCombo.getSelectedItem();
				int mm = (Integer) monthCombo.getSelectedItem();
				if (eventBtn.equals(lastMonth)) { // 전달
					if (mm == 1 && yy == year) {
					} else if (mm == 1) {
						yy--;
						mm = 12;
					} else {
						mm--;
					}
				} else if (eventBtn.equals(nextMonth)) { // 다음달
					if (mm == 12) {
						yy++;
						mm = 1;
					} else {
						mm++;
					}
				}
				yearCombo.setSelectedItem(yy);
				monthCombo.setSelectedItem(mm);
			} else if (obj instanceof JComboBox) { // 콤보박스 이벤트 발생시
				createDayStart();
			}
		}

		private void createDayStart() {
			cntCenter.setVisible(false); // 패널 숨기기
			cntCenter.removeAll(); // 날짜 출력한 라벨 지우기
			dayPrint((Integer) yearCombo.getSelectedItem(), (Integer) monthCombo.getSelectedItem());
			cntCenter.setVisible(true); // 패널 재출력
		}

		// 날짜 출력
		public void dayPrint(int y, int m) {
			Calendar cal = Calendar.getInstance();
			cal.set(y, m - 1, 1);
			int week = cal.get(Calendar.DAY_OF_WEEK); // 1일에 대한 요일
			int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 1월에 대한 마지막 요일
			for (int i = 1; i < week; i++) { // 1월 1일 전까지 공백을 표시해라
				cntCenter.add(new JLabel(""));
			}

			for (int i = 0; i <= lastDate - 1; i++) { // 1월 마지막 날까지 숫자를 적어라, 그리고 토요일 일요일은 색깔을 입혀라
				JLabel day = new JLabel();
				day.setHorizontalAlignment(JLabel.CENTER);
				if ((week + i) % 7 == 0) {
					cntCenter.add(day).setForeground(Color.blue);
					day.setText(1 + i + "");
				} else if ((week + i) % 7 == 1) {
					cntCenter.add(day).setForeground(Color.red);
					day.setText(1 + i + "");
				} else {
					cntCenter.add(day);
					day.setText(1 + i + "");
				}
				day.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent me) {
						JLabel mouseClick = (JLabel) me.getSource();
						String str = mouseClick.getText();
						String y = "" + yearCombo.getSelectedItem();
						String m = "" + monthCombo.getSelectedItem();

						// 받은 "요일"이 1자리면 0을 붙여라
						if (str.equals(""))
							;
						else if (str.length() == 1)
							str = "0" + str;

						// 받은 "월"이 1자리면 0을 붙여라
						if (m.length() == 1)
							m = "0" + m;

						if (clickCheck == 0) {
							startDateField.setText(y + "/" + m + "/" + str);
							startDateField.setEnabled(false);
							clickCheck++;
						} else if (clickCheck == 1) {
							arriveDateField.setText(y + "/" + m + "/" + str);
							arriveDateField.setEnabled(false);
							clickCheck--;
						}
					}
				});
			}

		}

		public void windowOpened(WindowEvent e) {
			calendarWindowTest = 1;
		}

		public void windowClosing(WindowEvent e) {
			calendarWindowTest = 0;
		}

		public void windowClosed(WindowEvent e) {
		}

		public void windowIconified(WindowEvent e) {
		}

		public void windowDeiconified(WindowEvent e) {
		}

		public void windowActivated(WindowEvent e) {
		}

		public void windowDeactivated(WindowEvent e) {
		}

	}
}
