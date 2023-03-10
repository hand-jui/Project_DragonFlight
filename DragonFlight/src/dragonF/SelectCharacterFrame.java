package dragonF;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SelectCharacterFrame extends JFrame {

	ImageIcon imageicon;
	Image image;
	MyPanel mypanel;

	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;

	public SelectCharacterFrame() {

		initData();
		setInitLayout();
		addEventListener();

	}

	public void initData() {

		setTitle("SelectCharacter");
		setSize(800, 900);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		button1 = new JButton(new ImageIcon("images/Character_Nella.png"));
		button2 = new JButton(new ImageIcon("images/Character_Rose.png"));
		button3 = new JButton(new ImageIcon("images/Character_Roxy.png"));
		button4 = new JButton(new ImageIcon("images/Character_Chloe.png"));

		// 캐릭터 테두리에 맞게 버튼
//		button1.setBorderPainted(false);
//		button2.setBorderPainted(false);
//		button3.setBorderPainted(false);
//		button4.setBorderPainted(false);
//		
//		button1.setContentAreaFilled(false);
//		button2.setContentAreaFilled(false);
//		button3.setContentAreaFilled(false);
//		button4.setContentAreaFilled(false);
//		
//		button1.setFocusPainted(false);
//		button2.setFocusPainted(false);
//		button3.setFocusPainted(false);
//		button4.setFocusPainted(false);

		imageicon = new ImageIcon("images/GameBackground1.png");
		image = imageicon.getImage();
		mypanel = new MyPanel();

	}

	public void setInitLayout() {

		setVisible(true);
		setResizable(false);

		mypanel.add(button1);
		mypanel.add(button2);
		mypanel.add(button3);
		mypanel.add(button4);
		mypanel.setLayout(new FlowLayout(FlowLayout.CENTER, 80, 80));
		add(mypanel);

	}

	public void addEventListener() {

		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// button1 = (JButton) e.getSource();
				// button1.setIcon(new ImageIcon("images/Close_button.png"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button1 = (JButton) e.getSource();
				button1.setIcon(new ImageIcon("images/Character_Nella.png"));
			}

		});
		button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// button2 = (JButton) e.getSource();
				// button2.setIcon(new ImageIcon("images/Close_button.png"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button2 = (JButton) e.getSource();
				button2.setIcon(new ImageIcon("images/Character_Rose.png"));
			}
		});
		button3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// button3 = (JButton) e.getSource();
				// button3.setIcon(new ImageIcon("images/Close_button.png"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button3 = (JButton) e.getSource();
				button3.setIcon(new ImageIcon("images/Character_Roxy.png"));
			}
		});
		button4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// button4 = (JButton) e.getSource();
				// button4.setIcon(new ImageIcon("images/Close_button.png"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button4 = (JButton) e.getSource();
				button4.setIcon(new ImageIcon("images/Character_Chloe.png"));
			}
		});

		// 게임창으로 넘어감
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new GameFrame();
				// new Frame();
				// new FallingBack();
				setVisible(false);

			}
		});

		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new GameFrame();
				// new Frame();
				// new FallingBack();
				setVisible(false);

			}
		});

		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new GameFrame();
				// new Frame();
				// new FallingBack();
				setVisible(false);

			}
		});

		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new GameFrame();
				// new Frame();
				// new FallingBack();
				setVisible(false);

			}
		});

	}

	// JPanel 내부 클래스 (배경 이미지 + 글자)
	class MyPanel extends JPanel {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
//			g.setFont(new Font("SB 어그로 BOLD", Font.PLAIN, 50));
			g.setFont(new Font("함초롱바탕", Font.BOLD, 50));
			g.setColor(Color.white);
			g.drawString("캐릭터를 선택해주세요", 130, 780);
		}
	}

	public static void main(String[] args) {
		new SelectCharacterFrame();
	}

}
