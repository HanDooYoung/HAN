package graphicsEditor;

import java.awt.BorderLayout;

import java.awt.Color;

import java.awt.Container;

import java.awt.Graphics;

import java.awt.Point;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import java.io.BufferedReader;

import java.io.FileReader;

import java.io.FileWriter;

import java.io.IOException;

import java.util.StringTokenizer;

import java.util.Vector;

import javax.swing.JFrame;

import javax.swing.JMenu;

import javax.swing.JMenuBar;

import javax.swing.JMenuItem;

import javax.swing.JPanel;

public class GE extends JFrame {

	Container contentPane;

	MyPanel panel = new MyPanel();

	int choice = 1;

	GE() {

		setTitle("GraphicsEditor");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = getContentPane();

		contentPane.add(panel, BorderLayout.CENTER);

		createMenu();

		setSize(600, 600);

		setVisible(true);

	}

	public void createMenu() {

		JMenuBar mb = new JMenuBar();

		JMenu file = new JMenu("파일");

		JMenu Menu = new JMenu("그리기 도구");

		JMenuItem save = new JMenuItem("저장");

		save.addActionListener(new MenuActionListener());

		JMenuItem open = new JMenuItem("불러오기");

		open.addActionListener(new MenuActionListener());

		file.add(save);

		file.add(open);

		JMenuItem li = new JMenuItem("선");

		li.addActionListener(new MenuActionListener());

		JMenuItem re = new JMenuItem("사각형");

		re.addActionListener(new MenuActionListener());

		JMenuItem ov = new JMenuItem("원");

		ov.addActionListener(new MenuActionListener());

		JMenuItem ar = new JMenuItem("원호");

		ar.addActionListener(new MenuActionListener());

		JMenuItem cl = new JMenuItem("빈 화면");

		cl.addActionListener(new MenuActionListener());

		Menu.add(li);

		Menu.add(re);

		Menu.add(ov);

		Menu.add(ar);

		Menu.add(cl);

		mb.add(file);

		mb.add(Menu);

		setJMenuBar(mb);

	}

	class MenuActionListener implements ActionListener {

		@Override

		public void actionPerformed(ActionEvent e) {

			// TODO Auto-generated method stub

			int i;

			if (e.getActionCommand().equals("사각형"))

				choice = 2;

			else if (e.getActionCommand().equals("원"))

				choice = 3;

			else if (e.getActionCommand().equals("원호"))

				choice = 4;

			else if (e.getActionCommand().equals("빈 화면")) {

				panel.lStart.removeAllElements();

				panel.lEnd.removeAllElements();

				panel.rStart.removeAllElements();

				panel.rEnd.removeAllElements();

				panel.oStart.removeAllElements();

				panel.oEnd.removeAllElements();

				panel.aStart.removeAllElements();

				panel.aEnd.removeAllElements();

				repaint();

			} else if (e.getActionCommand().equals("저장")) {

				try {

					FileWriter fw = new FileWriter("ex.txt");

					int num;

					if (!panel.lStart.isEmpty())

						fw.write("ㄱ\r\n");

					for (i = 0; i < panel.lStart.size(); i++) {

						num = (int) panel.lStart.elementAt(i).getX();

						fw.write(String.format("%d", num) + " ");

						num = (int) panel.lStart.elementAt(i).getY();

						fw.write(String.format("%d", num) + " ");

					}

					if (!panel.lStart.isEmpty())

						fw.write("\r\n");

					if (!panel.lEnd.isEmpty())

						fw.write("ㄴ\r\n");

					for (i = 0; i < panel.lEnd.size(); i++) {

						num = (int) panel.lEnd.elementAt(i).getX();

						fw.write(String.format("%d", num) + " ");

						num = (int) panel.lEnd.elementAt(i).getY();

						fw.write(String.format("%d", num) + " ");

					}

					if (!panel.lEnd.isEmpty())

						fw.write("\r\n");

					if (!panel.rStart.isEmpty())

						fw.write("ㄷ\r\n");

					for (i = 0; i < panel.rStart.size(); i++) {

						num = (int) panel.rStart.elementAt(i).getX();

						fw.write(String.format("%d", num) + " ");

						num = (int) panel.rStart.elementAt(i).getY();

						fw.write(String.format("%d", num) + " ");

					}

					if (!panel.rStart.isEmpty())

						fw.write("\r\n");

					if (!panel.rEnd.isEmpty())

						fw.write("ㄹ\r\n");

					for (i = 0; i < panel.rEnd.size(); i++) {

						num = (int) panel.rEnd.elementAt(i).getX();

						fw.write(String.format("%d", num) + " ");

						num = (int) panel.rEnd.elementAt(i).getY();

						fw.write(String.format("%d", num) + " ");

					}

					if (!panel.rEnd.isEmpty())

						fw.write("\r\n");

					if (!panel.oStart.isEmpty())

						fw.write("ㅁ\r\n");

					for (i = 0; i < panel.oStart.size(); i++) {

						num = (int) panel.oStart.elementAt(i).getX();

						fw.write(String.format("%d", num) + " ");

						num = (int) panel.oStart.elementAt(i).getY();

						fw.write(String.format("%d", num) + " ");

					}

					if (!panel.oStart.isEmpty())

						fw.write("\r\n");

					if (!panel.oEnd.isEmpty())

						fw.write("ㅂ\r\n");

					for (i = 0; i < panel.oEnd.size(); i++) {

						num = (int) panel.oEnd.elementAt(i).getX();

						fw.write(String.format("%d", num) + " ");

						num = (int) panel.oEnd.elementAt(i).getY();

						fw.write(String.format("%d", num) + " ");

					}

					if (!panel.oEnd.isEmpty())

						fw.write("\r\n");

					if (!panel.aStart.isEmpty())

						fw.write("ㅅ\r\n");

					for (i = 0; i < panel.aStart.size(); i++) {

						num = (int) panel.aStart.elementAt(i).getX();

						fw.write(String.format("%d", num) + " ");

						num = (int) panel.aStart.elementAt(i).getY();

						fw.write(String.format("%d", num) + " ");

					}

					if (!panel.aStart.isEmpty())

						fw.write("\r\n");

					if (!panel.aEnd.isEmpty())

						fw.write("ㅇ\r\n");

					for (i = 0; i < panel.aEnd.size(); i++) {

						num = (int) panel.aEnd.elementAt(i).getX();

						fw.write(String.format("%d", num) + " ");

						num = (int) panel.aEnd.elementAt(i).getY();

						fw.write(String.format("%d", num) + " ");

					}

					if (!panel.aEnd.isEmpty())

						fw.write("\r\n");

					fw.close();

				} catch (IOException e1) {

					// TODO Auto-generated catch block

					e1.printStackTrace();

				}

			} else if (e.getActionCommand().equals("불러오기")) {

				panel.lStart.removeAllElements();

				panel.lEnd.removeAllElements();

				panel.rStart.removeAllElements();

				panel.rEnd.removeAllElements();

				panel.oStart.removeAllElements();

				panel.oEnd.removeAllElements();

				panel.aStart.removeAllElements();

				panel.aEnd.removeAllElements();

				try {

					FileReader fr = new FileReader("ex.txt");

					BufferedReader br = new BufferedReader(fr);

					String str;

					String str2 = null;

					String str3;

					StringTokenizer st;

					while ((str = br.readLine()) != null) {

						if (str.startsWith("ㄱ"))

							str2 = "ㄱ";

						else if (str.startsWith("ㄴ"))

							str2 = "ㄴ";

						else if (str.startsWith("ㄷ"))

							str2 = "ㄷ";

						else if (str.startsWith("ㄹ"))

							str2 = "ㄹ";

						else if (str.startsWith("ㅁ"))

							str2 = "ㅁ";

						else if (str.startsWith("ㅂ"))

							str2 = "ㅂ";

						else if (str.startsWith("ㅅ"))

							str2 = "ㅅ";

						else if (str.startsWith("ㅇ"))

							str2 = "ㅇ";

						if (str2 == "ㄱ" && !str.startsWith("ㄱ")) {

							st = new StringTokenizer(str, " ");

							i = 0;

							while (st.hasMoreTokens()) {

								panel.lStart.add(

										new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

							}

						} else if (str2 == "ㄴ" && !str.startsWith("ㄴ")) {

							st = new StringTokenizer(str, " ");

							i = 0;

							while (st.hasMoreTokens()) {

								panel.lEnd.add(

										new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

							}

						}

						if (str2 == "ㄷ" && !str.startsWith("ㄷ")) {

							st = new StringTokenizer(str, " ");

							i = 0;

							while (st.hasMoreTokens()) {

								panel.rStart.add(

										new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

							}

						} else if (str2 == "ㄹ" && !str.startsWith("ㄹ")) {

							st = new StringTokenizer(str, " ");

							i = 0;

							while (st.hasMoreTokens()) {

								panel.rEnd.add(

										new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

							}

						} else if (str2 == "ㅁ" && !str.startsWith("ㅁ")) {

							st = new StringTokenizer(str, " ");

							i = 0;

							while (st.hasMoreTokens()) {

								panel.oStart.add(

										new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

							}

						} else if (str2 == "ㅂ" && !str.startsWith("ㅂ")) {

							st = new StringTokenizer(str, " ");

							i = 0;

							while (st.hasMoreTokens()) {

								panel.oEnd.add(

										new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

							}

						} else if (str2 == "ㅅ" && !str.startsWith("ㅅ")) {

							st = new StringTokenizer(str, " ");

							i = 0;

							while (st.hasMoreTokens()) {

								panel.aStart.add(

										new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

							}

						} else if (str2 == "ㅇ" && !str.startsWith("ㅇ")) {

							st = new StringTokenizer(str, " ");

							i = 0;

							while (st.hasMoreTokens()) {

								panel.aEnd.add(

										new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

							}

						}

					}

					br.close();

					fr.close();

				} catch (IOException e1) {

					// TODO Auto-generated catch block

					e1.printStackTrace();

				}

				repaint();

			} else {
				choice = 1;
			}

		}

	}

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		new GE();

	}

	class MyPanel extends JPanel {

		Vector<Point> lStart = new Vector<Point>();

		Vector<Point> lEnd = new Vector<Point>();

		Vector<Point> rStart = new Vector<Point>();

		Vector<Point> rEnd = new Vector<Point>();

		Vector<Point> oStart = new Vector<Point>();

		Vector<Point> oEnd = new Vector<Point>();

		Vector<Point> aStart = new Vector<Point>();

		Vector<Point> aEnd = new Vector<Point>();

		public MyPanel() {

			addMouseListener(new MouseAdapter() {

				public void mousePressed(MouseEvent e) {

					switch (choice) {

					case 1:

						lStart.add(e.getPoint());

						break;

					case 2:

						rStart.add(e.getPoint());

						break;

					case 3:

						oStart.add(e.getPoint());

						break;

					case 4:

						aStart.add(e.getPoint());

					}

				}

				public void mouseReleased(MouseEvent e) {

					switch (choice) {

					case 1:

						if (lStart.size() != lEnd.size())

							lEnd.add(e.getPoint());

						break;

					case 2:

						if (rStart.size() != rEnd.size())

							rEnd.add(e.getPoint());

						break;

					case 3:

						if (oStart.size() != oEnd.size())

							oEnd.add(e.getPoint());

						break;

					case 4:

						if (aStart.size() != aEnd.size())

							aEnd.add(e.getPoint());

					}

					repaint();

				}

			});

			addMouseMotionListener(new MouseAdapter() {

				public void mouseDragged(MouseEvent e) {

					switch (choice) {

					case 1:

						if (lStart.size() != lEnd.size())

							lEnd.add(e.getPoint());

						lEnd.set(lEnd.size() - 1, e.getPoint());

						break;

					case 2:

						if (rStart.size() != rEnd.size())

							rEnd.add(e.getPoint());

						rEnd.set(rEnd.size() - 1, e.getPoint());

						break;

					case 3:

						if (oStart.size() != oEnd.size())

							oEnd.add(e.getPoint());

						oEnd.set(oEnd.size() - 1, e.getPoint());

						break;

					case 4:

						if (aStart.size() != aEnd.size())

							aEnd.add(e.getPoint());

						aEnd.set(aEnd.size() - 1, e.getPoint());

					}

					repaint();

				}

			});

		}

		public void paintComponent(Graphics g) {

			super.paintComponent(g);

			g.setColor(Color.black);

			int i;

			for (i = 0; i < lStart.size(); i++) {

				g.drawLine((int) lStart.elementAt(i).getX(), (int) lStart.elementAt(i).getY(),

						(int) lEnd.elementAt(i).getX(), (int) lEnd.elementAt(i).getY());

			}

			for (i = 0; i < rStart.size(); i++) {

				if (rEnd.elementAt(i).getX() >= rStart.elementAt(i).getX()

						&& rEnd.elementAt(i).getY() >= rStart.elementAt(i).getY()) {

					g.drawRect((int) rStart.elementAt(i).getX(), (int) rStart.elementAt(i).getY(),

							(int) Math.abs(rStart.elementAt(i).getX() - rEnd.elementAt(i).getX()),

							(int) Math.abs(rStart.elementAt(i).getY() - rEnd.elementAt(i).getY()));

				} else if (rEnd.elementAt(i).getX() > rStart.elementAt(i).getX()

						&& rEnd.elementAt(i).getY() < rStart.elementAt(i).getY()) {

					g.drawRect((int) (rStart.elementAt(i).getX()), (int) (rEnd.elementAt(i).getY()),

							(int) Math.abs(rStart.elementAt(i).getX() - rEnd.elementAt(i).getX()),

							(int) Math.abs(rStart.elementAt(i).getY() - rEnd.elementAt(i).getY()));

				} else if (rEnd.elementAt(i).getX() < rStart.elementAt(i).getX()

						&& rEnd.elementAt(i).getY() > rStart.elementAt(i).getY()) {

					g.drawRect((int) (rEnd.elementAt(i).getX()), (int) (rStart.elementAt(i).getY()),

							(int) Math.abs(rStart.elementAt(i).getX() - rEnd.elementAt(i).getX()),

							(int) Math.abs(rStart.elementAt(i).getY() - rEnd.elementAt(i).getY()));

				} else {

					g.drawRect((int) rEnd.elementAt(i).getX(), (int) rEnd.elementAt(i).getY(),

							(int) Math.abs(rStart.elementAt(i).getX() - rEnd.elementAt(i).getX()),

							(int) Math.abs(rStart.elementAt(i).getY() - rEnd.elementAt(i).getY()));

				}

			}

			for (i = 0; i < oStart.size(); i++) {

				if (oEnd.elementAt(i).getX() >= oStart.elementAt(i).getX()

						&& oEnd.elementAt(i).getY() >= oStart.elementAt(i).getY()) {

					g.drawOval((int) oStart.elementAt(i).getX(), (int) oStart.elementAt(i).getY(),

							(int) Math.abs(oStart.elementAt(i).getX() - oEnd.elementAt(i).getX()),

							(int) Math.abs(oStart.elementAt(i).getY() - oEnd.elementAt(i).getY()));

				} else if (oEnd.elementAt(i).getX() > oStart.elementAt(i).getX()

						&& oEnd.elementAt(i).getY() < oStart.elementAt(i).getY()) {

					g.drawOval((int) (oStart.elementAt(i).getX()), (int) (oEnd.elementAt(i).getY()),

							(int) Math.abs(oStart.elementAt(i).getX() - oEnd.elementAt(i).getX()),

							(int) Math.abs(oStart.elementAt(i).getY() - oEnd.elementAt(i).getY()));

				} else if (oEnd.elementAt(i).getX() < oStart.elementAt(i).getX()

						&& oEnd.elementAt(i).getY() > oStart.elementAt(i).getY()) {

					g.drawOval((int) (oEnd.elementAt(i).getX()), (int) (oStart.elementAt(i).getY()),

							(int) Math.abs(oStart.elementAt(i).getX() - oEnd.elementAt(i).getX()),

							(int) Math.abs(oStart.elementAt(i).getY() - oEnd.elementAt(i).getY()));

				} else {

					g.drawOval((int) oEnd.elementAt(i).getX(), (int) oEnd.elementAt(i).getY(),

							(int) Math.abs(oStart.elementAt(i).getX() - oEnd.elementAt(i).getX()),

							(int) Math.abs(oStart.elementAt(i).getY() - oEnd.elementAt(i).getY()));

				}

			}

			for (i = 0; i < aStart.size(); i++) {

				if (aEnd.elementAt(i).getX() >= aStart.elementAt(i).getX()

						&& aEnd.elementAt(i).getY() >= aStart.elementAt(i).getY()) {

					g.drawArc((int) aStart.elementAt(i).getX(), (int) aStart.elementAt(i).getY(),

							(int) Math.abs(aStart.elementAt(i).getX() - aEnd.elementAt(i).getX()),

							(int) Math.abs(aStart.elementAt(i).getY() - aEnd.elementAt(i).getY()), 270, 270);

				} else if (aEnd.elementAt(i).getX() > aStart.elementAt(i).getX()

						&& aEnd.elementAt(i).getY() < aStart.elementAt(i).getY()) {

					g.drawArc((int) aStart.elementAt(i).getX(), (int) aEnd.elementAt(i).getY(),

							(int) Math.abs(aStart.elementAt(i).getX() - aEnd.elementAt(i).getX()),

							(int) Math.abs(aStart.elementAt(i).getY() - aEnd.elementAt(i).getY()), 180, 270);

				} else if (aEnd.elementAt(i).getX() < aStart.elementAt(i).getX()

						&& aEnd.elementAt(i).getY() > aStart.elementAt(i).getY()) {

					g.drawArc((int) aEnd.elementAt(i).getX(), (int) aStart.elementAt(i).getY(),

							(int) Math.abs(aStart.elementAt(i).getX() - aEnd.elementAt(i).getX()),

							(int) Math.abs(aStart.elementAt(i).getY() - aEnd.elementAt(i).getY()), 0, 270);

				} else {

					g.drawArc((int) aEnd.elementAt(i).getX(), (int) aEnd.elementAt(i).getY(),

							(int) Math.abs(aStart.elementAt(i).getX() - aEnd.elementAt(i).getX()),

							(int) Math.abs(aStart.elementAt(i).getY() - aEnd.elementAt(i).getY()), 90, 270);

				}

			}

		}

	}

}
