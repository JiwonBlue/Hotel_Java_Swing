package team5;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class MainGui extends JFrame implements ActionListener {

	JButton mainB, roomB, reserveB, infoB;
	JPanel buttonsP;
	Container cp;
	MainP mainP = new MainP(); // 메인 패널
	RoomP roomP = new RoomP(); // 객실 패널
	ReservationP reserveP = new ReservationP(); // 예약 패널
	Fixinfo infoP = new Fixinfo(); // 내 정보 패널

	Color c = new Color(122, 122, 122); // 연회색
	Color c1 = new Color(237, 211, 175); // 메인배경색(RGB,투명도), 원래 색 -227, 177, 152, 100
	Color c2 = new Color(77, 58, 50); // 커피원두색, 하단 버튼4개 색상
	Color c3 = new Color(227, 190, 168); // 주황색(홈 하이패널색), 투명도 200 없앰
	Color c4 = new Color(135, 103, 89); // 연갈색

	MainGui() {
		init();
	}

	public void actionPerformed(ActionEvent ae) { // 하단 버튼들을 눌렀을 때
		if (ae.getSource() == mainB) {
			setMainP();
		} else if (ae.getSource() == roomB) {
			setRoomP();
		} else if (ae.getSource() == reserveB) {
			setReserveP();
		} else if (ae.getSource() == infoB) {
			setInfoP();
		}
	}

	void init() {

		setbuttonsP();// 하단의 버튼 생성(메인/객실/예약/내정보)
		createPanels(); // 패널들 생성하는 메소드를 init에 올린다.
		mainB.addActionListener(this);
		roomB.addActionListener(this);
		reserveB.addActionListener(this);
		infoB.addActionListener(this);

		setUI();
	}

	// 버튼 위쪽에 패널을 4개 겹쳐둘 예정
	void createPanels() { // 패널 생성하는 메소드들 모음
		cp = getContentPane();

		cp.add(infoP.infoP); // 패널 4개 중 가장 아래에 있음 **여기 이렇게 겹칠 예정 **
		cp.add(reserveP.resP);
		cp.add(roomP.roomP);
		cp.add(mainP.mainP); // 가장 위에 있음 -> 열자마자 보이는 패널
		cp.add(buttonsP, BorderLayout.SOUTH);
	}

	// ex) setMainP()는 겹쳐진 4개의 패널들중 3개의 패널을 setVisible(false)로 가리고 main패널만 보이게 한다.
	void setMainP() {
		roomP.roomP.setVisible(false);
		reserveP.resP.setVisible(false);
		infoP.infoP.setVisible(false);
		cp.add(mainP.mainP);
		mainP.mainP.setVisible(true);
	}

	void setRoomP() {
		mainP.mainP.setVisible(false);
		reserveP.resP.setVisible(false);
		infoP.infoP.setVisible(false);
		cp.add(roomP.roomP);
		roomP.roomP.setVisible(true);
	}

	void setReserveP() {
		mainP.mainP.setVisible(false);
		roomP.roomP.setVisible(false);
		infoP.infoP.setVisible(false);
		cp.add(reserveP.resP);
		reserveP.resP.setVisible(true);
	}

	void setInfoP() {
		mainP.mainP.setVisible(false);
		roomP.roomP.setVisible(false);
		reserveP.resP.setVisible(false);
		cp.add(infoP.infoP);
		infoP.infoP.setVisible(true);
	}

	void setbuttonsP() {

		mainB = new JButton();
		roomB = new JButton();
		reserveB = new JButton();
		infoB = new JButton();

		mainB.setIcon(new ImageIcon("imgs\\mainB.png"));
		roomB.setIcon(new ImageIcon("imgs\\roomB.png"));
		reserveB.setIcon(new ImageIcon("imgs\\reserveB.png"));
		infoB.setIcon(new ImageIcon("imgs\\mypageB.png"));

		/* 버튼의 외곽선을 제거 */
		mainB.setBorderPainted(false);
		roomB.setBorderPainted(false);
		reserveB.setBorderPainted(false);
		infoB.setBorderPainted(false);

		/* 버튼을 클릭했을때 생기는 테두리 사용안함 */
		mainB.setFocusPainted(false);
		roomB.setFocusPainted(false);
		reserveB.setFocusPainted(false);
		infoB.setFocusPainted(false);

		/* 하단 버튼들을 담는 패널 */
		buttonsP = new JPanel(new GridLayout(1, 4));
		buttonsP.add(mainB);
		buttonsP.add(roomB);
		buttonsP.add(reserveB);
		buttonsP.add(infoB);
	}

	void setUI() {
		setTitle("hotel everest");
		setSize(400, 700); // 전체 사이즈
		setVisible(true);
		setLocation(200, 200);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
