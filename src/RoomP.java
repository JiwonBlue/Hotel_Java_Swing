package team5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagLayout;
import java.awt.SystemColor;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class RoomP extends JFrame {

	Container cp;

	JPanel roomP; // 전체패널
	JPanel stnInP1, deluxInP1, suiteInP1; // 스탠다드,디럭스,스위트-객실상세정보 패널
	JPanel stnInP2, deluxInP2, suiteInP2; // 스탠다드,디럭스,스위트-전체사진 패널
	JPanel etcInP1, etcInP2; // 부대시설-레스토랑패널, 그외시설패널

	JLabel stnNameL, dlxNameL, suiNameL; // 객실상세정보-이름
	JLabel stnMainImgL, dlxMainImgL, suiMainImgL; // 객실상세정보-이미지
	JLabel stnExp1, stnExp2, stnExp3, dlxExp1, dlxExp2, dlxExp3, suiExp1, suiExp2, suiExp3, restrExp; // 객실상세정보-내용

	JLabel stnInImgFirst, dlxInImgFirst, suiInImgFirst; // 스탠다드,디럭스,스위트-전체사진탭에 첫번째사진
	JLabel stnInImgSec, dlxInImgSec, suiInImgSec; // 스탠다드,디럭스,스위트-전체사진탭에 두번째사진
	JLabel restrImgL, etcImgL; // 부대시설-레스토랑,그외시설 아이콘넣는 라벨

	JTabbedPane tpTop, tpStnInner, tpDlxInner, tpSuiInner, tpEtcInner; // tpTop-상위탭4개(스탠다스,디럭스,스위트,부대시설), 나머지는 이너탭들
	ImageIcon stn1Icon, stn2Icon, stn3Icon, dlx1Icon, dlx2Icon, dlx3Icon, sui1Icon, sui2Icon, sui3Icon; // 이미지넣는 아이콘
	ImageIcon restrIcon, etcIcon; // 레스토랑,그외시설-이미지넣는 아이콘

	public RoomP() {
		init();
	}

	void init() {

		// 필요한 컴포넌트 준비//
		roomP = new JPanel();
		roomP.setLayout(new BorderLayout());

		tpTop = new JTabbedPane();
		tpStnInner = new JTabbedPane();
		tpDlxInner = new JTabbedPane();
		tpSuiInner = new JTabbedPane();
		tpEtcInner = new JTabbedPane();

		stnInP1 = new JPanel();
		deluxInP1 = new JPanel();
		suiteInP1 = new JPanel();
		stnInP2 = new JPanel();
		deluxInP2 = new JPanel();
		suiteInP2 = new JPanel();
		etcInP1 = new JPanel();
		etcInP2 = new JPanel();

		standard();
		delux();
		suite();
		etc();

		// 제목 폰트 설정//
		try {
			InputStream inputStream1 = new BufferedInputStream(new FileInputStream("ttf/Hancom Gothic Regular.ttf"));
			Font f1 = Font.createFont(Font.TRUETYPE_FONT, inputStream1);
			stnNameL.setFont(f1.deriveFont(Font.BOLD, 20));
			dlxNameL.setFont(f1.deriveFont(Font.BOLD, 20));
			suiNameL.setFont(f1.deriveFont(Font.BOLD, 20));

		} catch (FontFormatException | IOException e) {
		}
		// 설명 폰트 설정//
		try {
			InputStream inputStream2 = new BufferedInputStream(new FileInputStream("ttf/Hancom Gothic Regular.ttf"));
			Font f2 = Font.createFont(Font.TRUETYPE_FONT, inputStream2);
			stnExp1.setFont(f2.deriveFont(Font.PLAIN, 13));
			dlxExp1.setFont(f2.deriveFont(Font.PLAIN, 13));
			suiExp1.setFont(f2.deriveFont(Font.PLAIN, 13));
		} catch (FontFormatException | IOException e) {
		}
		// 사진설명 폰트 설정//
		try {
			InputStream inputStream3 = new BufferedInputStream(new FileInputStream("ttf/Ghanachocolate.ttf"));
			Font f3 = Font.createFont(Font.TRUETYPE_FONT, inputStream3);
			stnExp2.setFont(f3.deriveFont(Font.PLAIN, 11));
			stnExp3.setFont(f3.deriveFont(Font.PLAIN, 11));
			dlxExp2.setFont(f3.deriveFont(Font.PLAIN, 11));
			dlxExp3.setFont(f3.deriveFont(Font.PLAIN, 11));
			suiExp2.setFont(f3.deriveFont(Font.PLAIN, 11));
			suiExp3.setFont(f3.deriveFont(Font.PLAIN, 11));
		} catch (FontFormatException | IOException e) {
		}

		// 탭이름 넣기, 탭마다 패널추가//
		tpStnInner.addTab("객실 상세정보", stnInP1);
		tpStnInner.addTab("전체 사진", stnInP2);
		tpTop.addTab("Standard Room", tpStnInner);
		tpDlxInner.add("객실 상세정보", deluxInP1);
		tpDlxInner.add("전체 사진", deluxInP2);
		tpTop.add("Delux Room", tpDlxInner);
		tpSuiInner.add("객실 상세정보", suiteInP1);
		tpSuiInner.add("전체 사진", suiteInP2);
		tpTop.add("Suite Room", tpSuiInner);
		tpEtcInner.add("레스토랑", etcInP1);
		tpEtcInner.add("그 외 시설", etcInP2);
		tpTop.add("부대시설", tpEtcInner);

		// 탭 색상
		tpTop.setBackgroundAt(0, (new Color(102, 153, 255)));
		tpTop.setBackgroundAt(1, (new Color(102, 153, 255)));
		tpTop.setBackgroundAt(2, (new Color(102, 153, 255)));
		tpTop.setBackgroundAt(3, (new Color(102, 153, 255)));
		tpStnInner.setBackgroundAt(0, (new Color(102, 153, 255)));
		tpStnInner.setBackgroundAt(1, (new Color(102, 153, 255)));
		tpDlxInner.setBackgroundAt(0, (new Color(102, 153, 255)));
		tpDlxInner.setBackgroundAt(1, (new Color(102, 153, 255)));
		tpSuiInner.setBackgroundAt(0, (new Color(102, 153, 255)));
		tpSuiInner.setBackgroundAt(1, (new Color(102, 153, 255)));
		tpEtcInner.setBackgroundAt(0, (new Color(102, 153, 255)));
		tpEtcInner.setBackgroundAt(1, (new Color(102, 153, 255)));

		// 탭 글자색
		tpTop.setForeground(Color.WHITE);
		tpStnInner.setForeground(Color.WHITE);
		tpDlxInner.setForeground(Color.WHITE);
		tpSuiInner.setForeground(Color.WHITE);
		tpEtcInner.setForeground(Color.WHITE);

		// 탭 폰트 설정
		try {
			InputStream inputStream4 = new BufferedInputStream(new FileInputStream("ttf/Happiness-Sans-Bold.ttf"));
			Font f4 = Font.createFont(Font.TRUETYPE_FONT, inputStream4);
			tpTop.setFont(f4.deriveFont(Font.PLAIN, 11));
			tpStnInner.setFont(f4.deriveFont(Font.PLAIN, 11));
			tpDlxInner.setFont(f4.deriveFont(Font.PLAIN, 11));
			tpSuiInner.setFont(f4.deriveFont(Font.PLAIN, 11));
			tpEtcInner.setFont(f4.deriveFont(Font.PLAIN, 11));
		} catch (FontFormatException | IOException e) {
		}

		// 패널 색상
		roomP.setBackground(SystemColor.WHITE); // roomP패널색상-화이트
		stnInP1.setBackground(new Color(051, 051, 051)); // 나머지패널색상-진한그레이
		deluxInP1.setBackground(new Color(051, 051, 051));
		suiteInP1.setBackground(new Color(051, 051, 051));
		stnInP2.setBackground(new Color(051, 051, 051));
		deluxInP2.setBackground(new Color(051, 051, 051));
		suiteInP2.setBackground(new Color(051, 051, 051));
		etcInP1.setBackground(new Color(051, 051, 051));
		etcInP2.setBackground(new Color(051, 051, 051));
	}

	void standard() { // 스탠다드룸 메소드

		// 스탠다드 첫번째탭//
		// 선언 및 생성//
		stnNameL = new JLabel("<html><br />《 STANDARD ROOM 》</body></html>"); // 라벨사용해서 스탠다드 제목입력
		stn1Icon = new ImageIcon("imgs/stnAll.jpg"); // 스탠다드첫번째탭 메인이미지아이콘 생성
		stnMainImgL = new JLabel(stn1Icon); // 스탠다드메인이미지를 라벨로 만들기
		stnExp1 = new JLabel(
				"<html><br /><body style='text-align:center;'><center><b>전망 리버뷰</b></center><br /><br />금액&nbsp;: &nbsp; 200,000원<br /><br />상세정보&nbsp;: &nbsp; 최대4인 / 더블 or 트윈(선택가능) / 20평"
						+ "<br /><br />어메니티&nbsp;: &nbsp; 에어컨 / 55인치TV / 냉장고 / 커피포트"
						+ "<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;생수 / 비데 / 욕실용품 / 드라이기 / 가운 "
						+ "<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Wi-Fi / 금연객실</body></html>",
				JLabel.CENTER); // 라벨사용해서 스탠다드 설명입력
		// 패널위에 추가해주기 & 글자색상//
		stnInP1.add(stnNameL); // 스탠다드첫번째패널에 제목추가
		stnNameL.setForeground(Color.WHITE); // 제목글자색:흰색
		stnInP1.add(stnMainImgL); // 스탠다드첫번째패널에 스탠다드라벨이미지 추가
		stnExp1.setForeground(Color.WHITE);// 설명글자색:흰색
		stnInP1.add(stnExp1); // 스탠다드첫번째패널에 설명추가

		// 스탠다드 두번째탭//
		// 선언 및 생성//
		stn2Icon = new ImageIcon("imgs/stnBed.jpg"); // 스탠다드두번째탭 첫번째이미지아이콘 생성
		stnInImgFirst = new JLabel(stn2Icon); // 스탠다드두번째탭 첫번째이미지아이콘을 라벨로 생성
		stnExp2 = new JLabel("「Standard Bedroom」"); // 스탠다드두번째탭 설명첫번째 라벨생성
		stn3Icon = new ImageIcon("imgs/stnRestroom.jpg"); // 스탠다드두번째탭 두번째이미지아이콘 생성
		stnInImgSec = new JLabel(stn3Icon); // 스탠다드두번째탭 두번째이미지아이콘을 라벨로 생성
		stnExp3 = new JLabel("「Standard Restroom」"); // 스탠다드두번째탭 설명두번째 라벨생성
		// 패널위에 추가해주기 & 글자색상//
		stnInP2.add(stnInImgFirst);
		stnInP2.add(stnExp2);
		stnExp2.setForeground(Color.WHITE);
		stnInP2.add(stnInImgSec);
		stnInP2.add(stnExp3);
		stnExp3.setForeground(Color.WHITE);
	}

	void delux() { // 디럭스룸 메소드
		// 디럭스 첫번째탭//
		// 선언 및 생성//
		dlxNameL = new JLabel("<html><br />《 DELUX ROOM 》</body></html>");
		dlx1Icon = new ImageIcon("imgs/dlxAll.jpg");
		dlxMainImgL = new JLabel(dlx1Icon);
		dlxExp1 = new JLabel(
				"<html><br /><body style='text-align:center;'><center><b>전망 시티뷰</b></center><br /><br />금액&nbsp;: &nbsp; 300,000원<br /><br />상세정보&nbsp;: &nbsp; 최대6인 / 더블 or 트윈(선택가능) / 30평"
						+ "<br /><br />어메니티&nbsp;: &nbsp; 에어컨 / 70인치TV / 냉장고 / 커피포트"
						+ "<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;생수 / 비데 / 욕조 / 욕실용품 / 드라이기 "
						+ "<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가운 / Wi-Fi / 금연객실</body></html>",
				JLabel.CENTER); // 라벨사용해서 스탠다드룸 제목입력
		// 패널위에 추가해주기 & 글자색상//
		deluxInP1.add(dlxNameL);
		dlxNameL.setForeground(Color.WHITE);
		deluxInP1.add(dlxMainImgL);
		dlxExp1.setForeground(Color.WHITE);
		deluxInP1.add(dlxExp1);

		// 디럭스 두번째탭//
		// 선언 및 생성//
		dlx2Icon = new ImageIcon("imgs/dlxBed.jpg");
		dlxInImgFirst = new JLabel(dlx2Icon);
		dlxExp2 = new JLabel("「Delux Bedroom」");
		dlx3Icon = new ImageIcon("imgs/dlxRestroom.jpg");
		dlxInImgSec = new JLabel(dlx3Icon);
		dlxExp3 = new JLabel("「Delux Restroom」");
		// 패널위에 추가해주기 & 글자색상//
		deluxInP2.add(dlxInImgFirst);
		deluxInP2.add(dlxExp2);
		dlxExp2.setForeground(Color.WHITE);
		deluxInP2.add(dlxInImgSec);
		deluxInP2.add(dlxExp3);
		dlxExp3.setForeground(Color.WHITE);
	}

	void suite() { // 스위트룸 메소드
		// 스위트 첫번째탭//
		// 선언 및 생성//
		suiNameL = new JLabel("<html><br />《 SUITE ROOM 》</body></html>");
		sui1Icon = new ImageIcon("imgs/suiAll.jpg");
		suiMainImgL = new JLabel(sui1Icon);
		suiExp1 = new JLabel(
				"<html><br /><body style='text-align:center;'><center><b>전망 시티&리버뷰</b></center><br /><br />금액&nbsp;: &nbsp; 400,000원<br /><br />상세정보&nbsp;: &nbsp; 최대8인 / 더블 or 트윈(선택가능) / 40평"
						+ "<br /><br />어메니티&nbsp;: &nbsp; 에어컨 / 79인치TV / 냉장고 / 커피포트"
						+ "<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;생수 / 비데 / 욕조 / 욕실용품 / 드라이기"
						+ "<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가운 / Wi-Fi / 금연객실</body></html>",
				JLabel.CENTER); // 라벨사용해서 스탠다드룸 제목입력
		// 패널위에 추가해주기 & 글자색상//
		suiteInP1.add(suiNameL);
		suiNameL.setForeground(Color.WHITE);
		suiteInP1.add(suiMainImgL);
		suiExp1.setForeground(Color.WHITE);
		suiteInP1.add(suiExp1);

		// 스위트 두번째탭//
		// 선언 및 생성//
		sui2Icon = new ImageIcon("imgs/suiKitchen.jpg");
		suiInImgFirst = new JLabel(sui2Icon);
		suiExp2 = new JLabel("「Suite Kitchen」");
		sui3Icon = new ImageIcon("imgs/suiRestroom.jpg");
		suiInImgSec = new JLabel(sui3Icon);
		suiExp3 = new JLabel("「Suite Restroom」");
		// 패널위에 추가해주기 & 글자색상//
		suiteInP2.add(suiInImgFirst);
		suiteInP2.add(suiExp2);
		suiExp2.setForeground(Color.WHITE);
		suiteInP2.add(suiInImgSec);
		suiteInP2.add(suiExp3);
		suiExp3.setForeground(Color.WHITE);
	}

	void etc() { // 부대시설 메소드
		// 레스토랑//
		restrIcon = new ImageIcon("imgs/restr.png");
		restrImgL = new JLabel(restrIcon);
		etcInP1.add(restrImgL);

		// 그외시설//
		etcIcon = new ImageIcon("imgs/EtcAll.png");
		etcImgL = new JLabel(etcIcon);
		etcInP2.add(etcImgL);

		roomP.add(tpTop); // 룸패널에 전체탭 올림
		cp = getContentPane();
		cp.add(roomP); // 컨테이너에 룸패널을 올림
	}

	void setUI() {
		setSize(400, 700);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	/*
	 * public static void main(String[] args) { new RoomP(); }
	 */
}