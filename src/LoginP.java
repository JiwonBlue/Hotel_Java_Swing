package team5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class LoginP extends JFrame {

	static String id, pwd, name;

	BackImpl back = new BackImpl();
	JButton btnSignin, btnLogin;
	static public JTextField tfId, tfPw;
	static public JPasswordField pwf;

	public LoginP() {
		init();
	}

	void init() {

		ImageIcon logo = new ImageIcon("imgs/EverestLogo.png");
		JLabel hotelLogo = new JLabel(logo);

		JLabel loginL = new JLabel("LOGIN");
		loginL.setForeground(Color.WHITE);

		// 글자수 제한 기능
		JLabel lId = new JLabel("ID");
		lId.setForeground(Color.WHITE);
		tfId = new JTextField("", 11);
		tfId.addKeyListener(new Key());

		JPanel pId = new JPanel(new GridLayout(2, 1));
		pId.setBackground(new Color(255, 0, 0, 0)); // 패널 투명하게
		pId.add(lId);
		pId.add(tfId);

		JLabel lPw = new JLabel("PASSWORD");
		lPw.setForeground(Color.WHITE);
		tfPw = new JTextField();
		tfPw.addKeyListener(new Key());

		pwf = new JPasswordField();
		pwf.addKeyListener(new Key());

		JPanel pPw = new JPanel(new GridLayout(2, 1));
		pPw.setBackground(new Color(255, 0, 0, 0)); // 패널 투명하게
		pPw.add(lPw);
		pPw.add(pwf);

		JPanel loginsignP = new JPanel();
		loginsignP.setBackground(new Color(255, 0, 0, 0)); // 패널 투명하게
		btnLogin = new JButton("로그인");
		btnLogin.addActionListener(new btnLogin());
		btnSignin = new JButton("회원가입");
		btnSignin.addActionListener(new btnSignin());
		JLabel SigninL = new JLabel("회원이 아니신 경우 회원가입 부탁드립니다.");
		SigninL.setForeground(Color.WHITE);
		Color cc = new Color(135, 206, 250); // 하늘색
		btnLogin.setBackground(cc); // 버튼색 하늘색
		btnLogin.setBorderPainted(false);
		btnLogin.setFocusPainted(false);
		btnSignin.setBackground(cc); // 버튼색 하늘색
		btnSignin.setBorderPainted(false);
		btnSignin.setFocusPainted(false);
		loginsignP.add(btnLogin);
		loginsignP.add(btnSignin);
		loginsignP.add(SigninL);

		// ttf 폰트 적용하기
		try {
			InputStream inputStream1 = new BufferedInputStream(new FileInputStream("ttf/Happiness-Sans-Bold.ttf"));
			Font f1 = Font.createFont(Font.TRUETYPE_FONT, inputStream1);
			loginL.setFont(f1.deriveFont(Font.PLAIN, 30));
			lId.setFont(f1.deriveFont(Font.PLAIN, 12));
			lPw.setFont(f1.deriveFont(Font.PLAIN, 12));
			btnLogin.setFont(f1.deriveFont(Font.PLAIN, 12));
			btnSignin.setFont(f1.deriveFont(Font.PLAIN, 12));
			SigninL.setFont(f1.deriveFont(Font.PLAIN, 12));
		} catch (FontFormatException | IOException e) {
		} // ttf 파일 없을 때 발생하는 예외

		// 메인 패널에 배경 색 넣기 (+ 모든 나머지 패널은 투명하게 해야함)
		JPanel pMain = new JPanel(); // 괄호 안 new FlowLayout() 없어도 됨
		Color c = new Color(051, 051, 051); // RGB+투명도 설정 //메인색상 찐그레이
		pMain.setBackground(c);

		pMain.setLayout(null);
		pMain.add(hotelLogo);
		pMain.add(loginL);
		pMain.add(pId);
		pMain.add(pPw);
		pMain.add(loginsignP);

		hotelLogo.setBounds(45, 50, 300, 200);
		loginL.setBounds(150, 260, 100, 50);
		pId.setBounds(90, 320, 200, 50);
		pPw.setBounds(90, 370, 200, 50);
		loginsignP.setBounds(13, 460, 360, 90);

		Container cp = getContentPane();
		cp.add(pMain);

		setUI();
		addWindowListener(new Window());
	}

	void setUI() {
		setVisible(true);
		setTitle("Hotel Everest");
		setSize(400, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	class btnLogin implements ActionListener// 로그인 버튼 기능
	{
		public void actionPerformed(ActionEvent ae) {
			String myPass = String.valueOf(pwf.getPassword());
			//System.out.println(myPass);

			if (ae.getSource() == btnLogin) {
				if (tfId.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "아이디를 입력하세요", "Hotel Everest", JOptionPane.INFORMATION_MESSAGE);
					tfId.requestFocus();
				} else if (!tfId.getText().trim().isEmpty() && myPass.isEmpty()) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요", "Hotel Everest",
							JOptionPane.INFORMATION_MESSAGE);
					pwf.requestFocus();
				} else if (!tfId.getText().trim().isEmpty() && !myPass.isEmpty()) {
					String id = tfId.getText().trim();
					LoginP.id = id;

					LoginP.name = back.getName(id);
					if (back.checkId(id) && back.checkPwd(id, myPass)) {
						setVisible(false);
						new MainGui();
					}

				}
			}
		}
	}

	class btnSignin implements ActionListener // 회원가입 버튼 기능
	{
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() == btnSignin) {
				new SignupP();
				setVisible(false);
			}
		}
	}

	class Key extends KeyAdapter {
		public void keyPressed(KeyEvent ke) {
			int key = ke.getKeyCode();
			if (key == KeyEvent.VK_ENTER) { // 엔터 치면
				btnLogin.doClick(); // 로그인 버튼 눌러라
			}
		}
	}

	class Window extends WindowAdapter {
		public void windowDeactivated(WindowEvent we) {
		}

		public void windowOpened(WindowEvent we) {
			tfId.requestFocus();
		}
	}

	void pln(String str) {
		System.out.println(str);

	}
	/*
	 * public static void main(String[] args) { new LoginP(); }
	 */
}
