package team5;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainP {

	JFrame frame;
	JButton mapb;
	JPanel mainP;

	public MainP() {
		initialize();
		// frame.setVisible(true);
	}

	void initialize() {
		frame = new JFrame();
		frame.setSize(400, 700); // 프레임 크기 설정
		frame.setLocationRelativeTo(null); // 프레임을 화면 가운데에 배치
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		mainP = new JPanel();
		mainP.setBounds(0, 0, 386, 663);
		frame.getContentPane().add(mainP);
		mainP.setLayout(null);
		mainP.setBackground(SystemColor.desktop); // main패널 색상변경

		JButton mainbtn1 = new JButton("");
		mainbtn1.setBounds(0, 573, 90, 90);
		mainbtn1.setIcon(new ImageIcon("imgs\\main1.png"));
		mainP.add(mainbtn1);

		JButton roombtn1 = new JButton("");
		roombtn1.setBounds(97, 573, 90, 90);
		roombtn1.setIcon(new ImageIcon("imgs\\room1.png"));
		mainP.add(roombtn1);

		JButton reserbtn2 = new JButton("");
		reserbtn2.setBounds(199, 573, 90, 90);
		reserbtn2.setIcon(new ImageIcon("imgs\\reserve1.png"));
		mainP.add(reserbtn2);

		JButton infobtn3 = new JButton("");
		infobtn3.setBounds(296, 573, 90, 90);
		infobtn3.setIcon(new ImageIcon("imgs\\info1.png"));
		mainP.add(infobtn3);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 2, 2);
		mainP.add(scrollPane);

		JLabel mainimg = new JLabel("New label");
		mainimg.setIcon(new ImageIcon("imgs\\main.png"));
		mainimg.setBounds(0, 0, 386, 573);
		mainP.add(mainimg);

		JPanel jido = new JPanel();
		mapb = new JButton("");
		jido.add(mapb);
		jido.setBounds(10, 500, 50, 50);
		mapb.setIcon(new ImageIcon("imgs\\jido.png"));
		mainP.add(jido);
		mapb.setBackground(new Color(000, 000, 000));
		mainP.revalidate();
		mainP.repaint();
		mapb.setBorderPainted(false);
		mapb.setFocusPainted(false);

		JPanel roomp = new JPanel();
		roomp.setBounds(0, 0, 386, 663);
		frame.getContentPane().add(roomp);

		JButton room2btn = new JButton("버튼을눌러라!!!!!!");
		room2btn.setFont(new Font("굴림", Font.PLAIN, 30));
		roomp.add(room2btn);

		roomp.setVisible(false);

		room2btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainP.setVisible(true);
				roomp.setVisible(false);
				new ReservationP();
			}
		});

		mainbtn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();

				roomp.setVisible(true);
				mainP.setVisible(false);
			}
		});

		mapb.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				new Map();

			}
		});
	}
}