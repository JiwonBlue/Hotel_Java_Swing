package team5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

@SuppressWarnings("unchecked")
public class SignupP extends JFrame {

	BackImpl b = new BackImpl();
	public JButton btnReturn, btnSignup;
	static public JTextField tfId, tfName, tfPw, tfphone;
	JComboBox<String> Year, Month, Day;
	JRadioButton rbI, rbO;// 라디오 버튼은 하나를 선택하면 기존에 선택된 버튼이 선택 해제가 되기 때문에,
							// 사용자가 잘못된 여러 개의 옵션을 선택하지 못하도록 방지해주는 역할을 한다.
	Color c1 = new Color(051, 051, 051); //// c1 = 메인 배경 색상
	Color c2 = new Color(135, 206, 250); // 버튼 색: 하늘색

	SignupP() {
		init();
	}

	void init() {
		ImageIcon logoimage = new ImageIcon("imgs/everestlogo.png");
		JLabel hotelLogo = new JLabel(logoimage);

		JLabel singinL = new JLabel("회원가입");
		singinL.setForeground(Color.WHITE);

		// 글자수 제한 기능
		JLabel lId = new JLabel("아이디(10자 이내)");
		lId.setForeground(Color.WHITE);
		tfId = new JTextField();
		tfId.addKeyListener(new Key());

		JLabel lPw = new JLabel("비밀번호(10자 이내)");
		lPw.setForeground(Color.WHITE);
		tfPw = new JTextField();
		tfPw.addKeyListener(new Key());

		JLabel lName = new JLabel("성명");
		lName.setForeground(Color.WHITE);
		tfName = new JTextField();
		tfName.addKeyListener(new Key());

		JLabel lBirth = new JLabel("생년월일");
		lBirth.setForeground(Color.WHITE);
		Year = new JComboBox();
		Month = new JComboBox();
		Day = new JComboBox();
		for (int i = 2023; i >= 1900; i--) { // 년
			String is = Integer.toString(i);
			Year.addItem(is);
		}
		for (int j = 1; j < 13; j++) { // 월
			String js = Integer.toString(j);
			Month.addItem(js);
		}
		for (int k = 1; k <= 31; k++) { // 일
			String ks = Integer.toString(k);
			Day.addItem(ks);
		}
		JLabel lPhone = new JLabel("전화번호");
		lPhone.setForeground(Color.WHITE);
		tfphone = new JTextField(" ", 11);
		tfphone.addKeyListener(new Key());

		JLabel linout = new JLabel("내/외국인");
		rbI = new JRadioButton("내국인");
		rbO = new JRadioButton("외국인");

		linout.setForeground(Color.WHITE);
		rbI.setForeground(Color.WHITE);
		rbO.setForeground(Color.WHITE);

		rbI.setBackground(c1); // 버튼 색 넣어준다
		rbO.setBackground(c1);
		rbI.setBorderPainted(false);
		rbI.setFocusPainted(false);
		rbO.setBorderPainted(false);
		rbO.setFocusPainted(false);

		ButtonGroup bgGender = new ButtonGroup();
		bgGender.add(rbI);
		bgGender.add(rbO);

		JPanel pId = new JPanel(new GridLayout(2, 1));
		pId.setBackground(new Color(255, 0, 0, 0)); // 패널 투명하게
		JPanel pPw = new JPanel(new GridLayout(2, 1));
		pPw.setBackground(new Color(255, 0, 0, 0)); // 패널 투명하게
		JPanel pName = new JPanel(new GridLayout(2, 1));
		pName.setBackground(new Color(255, 0, 0, 0)); // 패널 투명하게
		JPanel pBirth = new JPanel(new GridLayout(2, 1));
		pBirth.setBackground(new Color(255, 0, 0, 0)); // 패널 투명하게
		JPanel pPhone = new JPanel(new GridLayout(2, 1));
		pPhone.setBackground(new Color(255, 0, 0, 0)); // 패널 투명하게
		JPanel pBirthcombo = new JPanel();
		pBirthcombo.setBackground(new Color(255, 0, 0, 0));// 패널 투명하게
		JPanel pinout = new JPanel(new GridLayout(1, 3));
		pinout.setBackground(new Color(255, 0, 0, 0)); // 패널 투명하게
		pinout.add(linout);
		pinout.add(rbI);
		pinout.add(rbO);

		pId.add(lId);
		pId.add(tfId);
		pPw.add(lPw);
		pPw.add(tfPw);
		pName.add(lName);
		pName.add(tfName);
		pBirth.add(lBirth);
		pBirthcombo.add(Year);
		pBirthcombo.add(Month);
		pBirthcombo.add(Day);
		pPhone.add(lPhone);
		pPhone.add(tfphone);

		btnSignup = new JButton("가입하기");
		btnSignup.addActionListener(new btnSignup());
		btnSignup.setBackground(c2);
		btnSignup.setBorderPainted(false);
		btnSignup.setFocusPainted(false);

		btnReturn = new JButton("돌아가기");
		btnReturn.addActionListener(new btnReturn());
		btnReturn.setBackground(c2);
		btnReturn.setBorderPainted(false);
		btnReturn.setFocusPainted(false);

		// 폰트 적용하기
		try {
			InputStream inputStream1 = new BufferedInputStream(new FileInputStream("ttf/Happiness-Sans-Bold.ttf"));
			Font f1 = Font.createFont(Font.TRUETYPE_FONT, inputStream1);
			singinL.setFont(f1.deriveFont(Font.PLAIN, 30));
			lId.setFont(f1.deriveFont(Font.PLAIN, 13));
			lPw.setFont(f1.deriveFont(Font.PLAIN, 13));
			lName.setFont(f1.deriveFont(Font.PLAIN, 13));
			lBirth.setFont(f1.deriveFont(Font.PLAIN, 13));
			linout.setFont(f1.deriveFont(Font.PLAIN, 13));
			lPhone.setFont(f1.deriveFont(Font.PLAIN, 13));
			btnSignup.setFont(f1.deriveFont(Font.PLAIN, 12));
			btnReturn.setFont(f1.deriveFont(Font.PLAIN, 12));
		} catch (FontFormatException | IOException e) {
		}

		JPanel pMain = new JPanel(); // 괄호 안 new FlowLayout() 없어도 됨
		pMain.setBackground(c1);

		pMain.setLayout(null);
		pMain.add(hotelLogo);
		pMain.add(singinL);
		pMain.add(pId);
		pMain.add(pPw);
		pMain.add(pName);
		pMain.add(pBirth);
		pMain.add(pBirthcombo);
		pMain.add(pPhone);
		pMain.add(pinout);
		pMain.add(btnSignup);
		pMain.add(btnReturn);

		hotelLogo.setBounds(43, 30, 300, 180);
		singinL.setBounds(130, 160, 250, 160);
		pId.setBounds(90, 260, 200, 50);
		pPw.setBounds(90, 310, 200, 50);
		pName.setBounds(90, 360, 200, 50);
		pBirth.setBounds(90, 480, 200, 40);
		pBirthcombo.setBounds(90, 500, 200, 40);
		pPhone.setBounds(90, 420, 200, 50);
		pinout.setBounds(100, 550, 200, 15);
		btnSignup.setBounds(75, 575, 100, 30);
		btnReturn.setBounds(210, 575, 100, 30);

		Container cp = getContentPane();
		cp.add(pMain);

		setUI();
	}

	void setUI() {
		setVisible(true);
		setTitle("Hotel Everest");
		setSize(400, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	class Key extends KeyAdapter {
		public void keyPressed(KeyEvent ke) {
			int key = ke.getKeyCode();
			if (key == KeyEvent.VK_ENTER) {
				btnSignup.doClick();
			}
		}
	}

	class Window extends WindowAdapter {
		public void windowDeactivated(WindowEvent we) {
			tfName.setText("");
			tfPw.setText("");
			tfId.setText("");

		}

		public void windowOpened(WindowEvent we) {
			tfId.requestFocus();
		}

		public void windowClosing(WindowEvent we) {
		}
	}

	class btnReturn implements ActionListener // 돌아가기 버튼 기능
	{
		public void actionPerformed(ActionEvent ae) {
			setVisible(false);
			new LoginP(); // 로그인 화면으로 돌아간다
		}
	}

	class btnSignup implements ActionListener { // 가입하기 버튼 기능
		public void actionPerformed(ActionEvent ae) {

			String id = tfId.getText().trim();
			String pw = tfPw.getText().trim();
			String EName = tfName.getText().trim();
			String Phone = tfphone.getText().trim();
			String birth = combineDate();
			String inout = null;

			System.out.println(birth);

			if (rbI.isSelected())
				inout = "내국인";
			if (rbO.isSelected())
				inout = "외국인";

			if (ae.getSource() == btnSignup) {
				if (tfId.getText().trim().isEmpty()) {
					b.showBox("아이디를 입력하세요", "everest hotel");
				} else if (!tfId.getText().trim().isEmpty()) {
					if (tfPw.getText().trim().isEmpty()) {
						b.showBox("비밀번호를 입력하세요", "everest hotel");
						tfPw.requestFocus();
					} else if (!tfPw.getText().trim().isEmpty()) {
						if (tfName.getText().trim().isEmpty()) {
							b.showBox("이름을 입력하세요", "everest hotel");
							tfName.requestFocus();
						} else if (!tfName.getText().trim().isEmpty()) {
							if (b.isAlreadyId(id)) {
								b.memberInsert(id, pw, EName, Phone, birth, inout);
								setVisible(false);
								new LoginP();
							} else {
								b.setFieldEmpty();
							}
						}
					}
				}
			}
		}
	}

	String combineDate() {
		String birth = null;
		birth = Year.getSelectedItem().toString() + "-" + Month.getSelectedItem().toString() + "-"
				+ Day.getSelectedItem().toString();
		return birth.trim();
	}

	void pln(String str) {
		System.out.println(str);
	}
/*
	public static void main(String[] args) {
		new SignupP();
	}
	*/
}