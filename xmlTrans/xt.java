package xmlTrans;

import java.awt.Container;

import java.awt.Font;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.io.BufferedReader;

import java.io.FileReader;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Stack;

import java.util.StringTokenizer;

import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JMenu;

import javax.swing.JMenuBar;

import javax.swing.JMenuItem;

public class xt extends JFrame {

	Container contentPane;

	JLabel lbl = new JLabel();

	Information po;

	xt() {

		setTitle("XmlTranslator");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = getContentPane();

		this.setLayout(null);

		createMenu();

		setSize(1000, 1000);

		setVisible(true);

	}

	public void createMenu() {

		JMenuBar mb = new JMenuBar();

		JMenu file = new JMenu("파일");

		JMenuItem open = new JMenuItem("불러오기");

		open.addActionListener(new MenuActionListener());

		JMenuItem cl = new JMenuItem("빈 화면");

		cl.addActionListener(new MenuActionListener());

		file.add(open);

		file.add(cl);

		mb.add(file);

		setJMenuBar(mb);

	}

	class MenuActionListener implements ActionListener {

		@Override

		public void actionPerformed(ActionEvent e) {

			// TODO Auto-generated method stub

			Stack<String> sta = new Stack<String>();

			int a = 0, b = 10;

			boolean flag = true;

			Information info = new Information();

			Information p = info;

			Information s = info;

			if (e.getActionCommand().equals("불러오기")) {

				lbl.setText(null);

				contentPane.removeAll();

				try {

					FileReader fr = new FileReader("xml.txt");

					BufferedReader br = new BufferedReader(fr);

					FileReader fr2 = new FileReader("xt.txt");

					BufferedReader br2 = new BufferedReader(fr2);

					String str = "";

					String str2 = "";

					String str3 = "";

					StringTokenizer st;

					while ((str = br.readLine()) != null) {

						str2 += str;

					}

					st = new StringTokenizer(str2, ">");

					while (st.hasMoreTokens()) {

						str3 += st.nextToken();

					}

					st = new StringTokenizer(str3, "	");

					str3 = "";

					while (st.hasMoreTokens()) {

						str3 += st.nextToken();

					}

					st = new StringTokenizer(str3, "<");

					while (st.hasMoreTokens()) {

						str = st.nextToken();

						str3 = "";

						if (str.startsWith("/") && sta.empty()) {

							flag = false;

						} else if (str.startsWith("/")) {

							for (int i = 0; i < str.length(); i++) {

								if (str.charAt(i) != '/') {

									str3 += str.substring(i, i + 1);

								}

							}

							if (sta.lastElement().equals(str3)) {

								sta.pop();

								p = p.getParent();

							}

						} else {

							a = 10;

							if (!sta.empty()) {

								for (int i = 0; i < sta.size(); i++) {

									a += 100;

								}

							}

							sta.push(str);

							p.setChildren(info = new Information(p, str, a, b));

							p = info;

							info.btn.setBounds(info.getX(), info.getY(), info.getName().length() * 10 + 32, 20);

							contentPane.add(info.btn);

							b += 50;

						}

					}

					ArrayList<TrInfo> Tarr = new ArrayList<>();

					int count;

					int el;

					while ((str = br2.readLine()) != null) {

						st = new StringTokenizer(str, " ");

						str3 = "";

						str2 = st.nextToken();

						if (st.hasMoreTokens()) {

							str3 = st.nextToken();

						}

						if (str2.equals("<")) {

							Tarr.add(new TrInfo());

							Tarr.get(Tarr.size() - 1).setParents(str3);

						} else if (str2.equals("[")) {

							Tarr.get(Tarr.size() - 1).setCheck(str3);

						} else if (str2.equals("(")) {

							Tarr.get(Tarr.size() - 1).setArr(new tInfo());

							Tarr.get(Tarr.size() - 1).getArr().get(Tarr.get(Tarr.size() - 1).getArr().size() - 1)

									.setName(str3);

							if (st.hasMoreTokens()) {

								Tarr.get(Tarr.size() - 1).getArr().get(Tarr.get(Tarr.size() - 1).getArr().size() - 1)

										.setSequence(st.nextToken());

							}

						} else if (str2.equals(">")) {

							Tarr.get(Tarr.size() - 1).setSequence(str3);

						}

					}

					for (int loop = 0; loop < Tarr.size(); loop++) {

						el = 0;

						s.search(s, Tarr.get(loop).getParents());

						while (po != null) {

							for (int i = 0; i < Tarr.get(loop).getArr().size(); i++) {

								Tarr.get(loop).getArr().get(i).setFlag(true);

							}

							if (Tarr.get(loop).getCheck().equals(",")) {

								for (int i = 0; i < po.getChildren().size(); i++) {

									count = 0;

									for (int j = 0; j < Tarr.get(loop).getArr().size(); j++) {

										if (po.getChildren().get(i).getName()

												.equals(Tarr.get(loop).getArr().get(j).getName())) {

											count = 1;

										}

									}

									if (count == 0) {

										flag = false;

										break;

									}

								}

								for (int i = 0, j = 0; i < po.getChildren().size(); i++) {

									count = 0;

									for (; j < Tarr.get(loop).getArr().size();) {

										if (!po.getChildren().get(i).getName()

												.equals(Tarr.get(loop).getArr().get(j).getName())) {

											if (Tarr.get(loop).getArr().get(j).getSequence().equals("*")

													|| Tarr.get(loop).getArr().get(j).getSequence().equals("?")) {

												if (Tarr.get(loop).getSequence().equals("+")

														|| Tarr.get(loop).getSequence().equals("*")) {

													for (int k = 0; k < Tarr.get(loop).getArr().size(); k++) {

														Tarr.get(loop).getArr().get(k).setFlag(true);

													}

													j = 0;

												} else {

													Tarr.get(loop).getArr().get(j).setFlag(false);

													j++;

												}

											} else {

												if (Tarr.get(loop).getSequence().equals("+")

														|| Tarr.get(loop).getSequence().equals("*")) {

													for (int k = 0; k < Tarr.get(loop).getArr().size(); k++) {

														Tarr.get(loop).getArr().get(k).setFlag(true);

													}

													j = 0;

												} else {

													flag = false;

													break;

												}

											}

										} else {

											if (Tarr.get(loop).getArr().get(j).getSequence().equals("")

													|| Tarr.get(loop).getArr().get(j).getSequence().equals("?")) {

												Tarr.get(loop).getArr().get(j).setFlag(false);

												j++;

											} else {

												if (i < po.getChildren().size() - 1) {

													if (!po.getChildren().get(i).getName()

															.equals(po.getChildren().get(i + 1).getName())) {

														Tarr.get(loop).getArr().get(j).setFlag(false);

														j++;

													} else {

														Tarr.get(loop).getArr().get(j).setFlag(false);

													}

												}

											}

											count = 1;

											break;

										}

									}

									if (count == 0) {

										flag = false;

										break;

									}

								}

								if (po.getChildren().size() != 0) {

									for (int i = 0; i < Tarr.get(loop).getArr().size(); i++) {

										if (Tarr.get(loop).getArr().get(i).getFlag()) {

											if (Tarr.get(loop).getArr().get(i).getSequence().equals("")

													|| Tarr.get(loop).getArr().get(i).getSequence().equals("+")) {

												flag = false;

												break;

											}

										}

									}

								} else {

									if (Tarr.get(loop).getSequence().equals("")

											|| Tarr.get(loop).getSequence().equals("+")) {

										flag = false;

									}

								}

							} else if (Tarr.get(loop).getCheck().equals("|")) {

								for (int i = 0; i < po.getChildren().size(); i++) {

									count = 0;

									for (int j = 0; j < Tarr.get(loop).getArr().size(); j++) {

										if (po.getChildren().get(i).getName()

												.equals(Tarr.get(loop).getArr().get(j).getName())) {

											count = 1;

										}

									}

									if (count == 0) {

										flag = false;

										break;

									}

								}

								count = 0;

								for (int i = 0; i < Tarr.get(loop).getArr().size(); i++) {

									if (Tarr.get(loop).getArr().get(i).getSequence().equals("")

											|| Tarr.get(loop).getArr().get(i).getSequence().equals("+")) {

										count = 1;

										break;

									}

								}

								for (int j = 0; j < po.getChildren().size(); j++) {

									for (int k = 0; k < Tarr.get(loop).getArr().size(); k++) {

										if (po.getChildren().get(j).getName()

												.equals(Tarr.get(loop).getArr().get(k).getName())) {
											if (Tarr.get(loop).getArr().get(k).getFlag()) {

												if (Tarr.get(loop).getArr().get(k).getSequence().equals("+")

														|| Tarr.get(loop).getArr().get(k).getSequence().equals("*")) {

													if (j < po.getChildren().size() - 1) {

														if (!po.getChildren().get(j).getName()

																.equals(po.getChildren().get(j + 1).getName())) {

															Tarr.get(loop).getArr().get(k).setFlag(false);
															count--;

														}

													} else {

														Tarr.get(loop).getArr().get(k).setFlag(false);
														count--;

													}

												} else {

													Tarr.get(loop).getArr().get(k).setFlag(false);
													count--;

												}

											} else {

												flag = false;

												break;

											}

										}

									}

								}

								if (count != 0) {

									flag = false;

								}

							} else if (Tarr.get(loop).getCheck().equals("&")) {

								for (int i = 0; i < po.getChildren().size(); i++) {

									count = 0;

									for (int j = 0; j < Tarr.get(loop).getArr().size(); j++) {

										if (po.getChildren().get(i).getName()

												.equals(Tarr.get(loop).getArr().get(j).getName())) {

											count = 1;

										}

									}

									if (count == 0) {

										flag = false;

										break;

									}

								}

								count = 0;

								for (int i = 0; i < Tarr.get(loop).getArr().size(); i++) {

									if (Tarr.get(loop).getArr().get(i).getSequence().equals("")

											|| Tarr.get(loop).getArr().get(i).getSequence().equals("+")) {

										count++;

									}

								}

								for (int j = 0; j < po.getChildren().size(); j++) {

									for (int k = 0; k < Tarr.get(loop).getArr().size(); k++) {

										if (po.getChildren().get(j).getName()

												.equals(Tarr.get(loop).getArr().get(k).getName())) {

											if (Tarr.get(loop).getArr().get(k).getFlag()) {
												if (Tarr.get(loop).getArr().get(k).getSequence().equals("+")

														|| Tarr.get(loop).getArr().get(k).getSequence().equals("*")) {

													if (j < po.getChildren().size() - 1) {

														if (!po.getChildren().get(j).getName()

																.equals(po.getChildren().get(j + 1).getName())) {

															if (Tarr.get(loop).getArr().get(k).getSequence()
																	.equals("+")) {

																count--;

															}
															Tarr.get(loop).getArr().get(k).setFlag(false);

														}

													} else {
														if (Tarr.get(loop).getArr().get(k).getSequence().equals("+")) {

															count--;

														}
														Tarr.get(loop).getArr().get(k).setFlag(false);

													}

												} else {
													if (Tarr.get(loop).getArr().get(k).getSequence().equals("")) {

														count--;

													}
													Tarr.get(loop).getArr().get(k).setFlag(false);

												}

											} else {

												flag = false;

												break;

											}

										}

									}

								}

								if (count > 0) {

									flag = false;

								}

							}

							po = null;

							s.search(s, Tarr.get(loop).getParents());

						}

					}

					lbl.setFont(new Font("맑은 고딕", Font.BOLD, 20));

					contentPane.add(lbl);

					br.close();

					fr.close();

					br2.close();

					fr2.close();

				} catch (IOException e1) {

					// TODO Auto-generated catch block

					e1.printStackTrace();

				}

				if (!sta.empty() || !flag) {

					contentPane.removeAll();

					JButton btn = new JButton("error");

					btn.setBounds(contentPane.getWidth() / 2 - 50, contentPane.getHeight() / 2, 100, 20);

					contentPane.add(btn);

				}

				repaint();

			} else if (e.getActionCommand().equals("빈 화면")) {

				lbl.setText(null);

				contentPane.removeAll();

				repaint();

			}

		}

	}

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		new xt();

	}

	class Information {

		private int x, y;

		private String name;

		private Information parent = null;

		private ArrayList<Information> children = new ArrayList<>();

		private JButton btn;

		private boolean flag = true;

		public Information(Information parent, String name, int x, int y) {

			this.parent = parent;

			this.name = name;

			this.x = x;

			this.y = y;

			btn = new JButton(name);

			btn.addActionListener(new ActionListener() {

				String str;

				@Override

				public void actionPerformed(ActionEvent e) {

					for (int i = 0; i < children.size(); i++) {

						if (i == 0)

							str = "";

						str += children.get(i).getName();

						str += " ";

					}

					lbl.setText(name + "선택, 부모는 " + parent.getName() + "입니다. 자식은 " + str + "입니다.");

					lbl.setBounds(contentPane.getWidth() - (contentPane.getWidth() - contentPane.getWidth() / 3),

							contentPane.getHeight() - 100, lbl.getText().length() * 15, 20);

					str = null;

				}

			});

		}

		public void search(Information s, String name) {

			for (int i = 0; i < s.getChildren().size(); i++) {

				if (s.getChildren().get(i).getName().equals(name) && s.getChildren().get(i).getFlag()) {

					po = s.getChildren().get(i);

					po.setFlag(false);

					break;

				}

				search(s.getChildren().get(i), name);

			}

		}

		public Information() {

		}

		public int getX() {

			return x;

		}

		public void setX(int x) {

			this.x = x;

		}

		public int getY() {

			return y;

		}

		public void setY(int y) {

			this.y = y;

		}

		public String getName() {

			return name;

		}

		public ArrayList<Information> getChildren() {

			return children;

		}

		public void setChildren(Information children) {

			this.children.add(children);

		}

		public Information getParent() {

			return parent;

		}

		public void setParent(Information parent) {

			this.parent = parent;

		}

		public boolean getFlag() {

			return flag;

		}

		public void setFlag(boolean flag) {

			this.flag = flag;

		}

	}

	class TrInfo {

		private ArrayList<tInfo> arr = new ArrayList<>();

		private String parents, check, sequence = "";

		public ArrayList<tInfo> getArr() {

			return arr;

		}

		public void setArr(tInfo arr) {

			this.arr.add(arr);

		}

		public String getParents() {

			return parents;

		}

		public void setParents(String parents) {

			this.parents = parents;

		}

		public String getCheck() {

			return check;

		}

		public void setCheck(String check) {

			this.check = check;

		}

		public String getSequence() {

			return sequence;

		}

		public void setSequence(String sequence) {

			this.sequence = sequence;

		}

	}

	class tInfo {

		private String name = "", sequence = "";

		private boolean flag = true;

		public String getName() {

			return name;

		}

		public void setName(String name) {

			this.name = name;

		}

		public String getSequence() {

			return sequence;

		}

		public void setSequence(String sequence) {

			this.sequence = sequence;

		}

		public boolean getFlag() {

			return flag;

		}

		public void setFlag(boolean flag) {

			this.flag = flag;

		}

	}

}
