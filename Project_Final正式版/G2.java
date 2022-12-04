import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class G2 extends JFrame {
    private JLabel bgLabel;
    private ImageIcon bg;

    public G2() {
        super("打字遊戲");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 700);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        Game2 g2 = new Game2();
        this.add(g2);
        this.addKeyListener(g2);// 註冊鍵盤事件
        g2.addKeyListener(g2);// 註冊鍵盤事件
        bg = new ImageIcon(getClass().getResource("401.png")); // 背景圖片
        bgLabel = new JLabel(bg); // 把背景圖顯示在Label中
        bgLabel.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight()); // 把含有背景圖之Label位置設置為圖片剛好填充整個版面
        // 把内容視窗轉為JPanel，否則不能使用setOpaque()來使視窗變成透明
        JPanel imagePanel = (JPanel) this.getContentPane();
        imagePanel.setOpaque(false);
        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE)); // 把背景圖添加到分層窗格的最底層以作為背景

    }

    public class Game2 extends JPanel implements KeyListener {
        int xx[] = { 229, 347, 345, 452, 562, 609, 655, 782, 825 };
        int yy[] = { 266, 100, 325, 239, 340, 101, 243, 117, 277 };
        char word[] = new char[27];
        String[] data = new String[28];
        int point = 0;
        int time = 60;
        int keywordIndex = -1; // 紀錄關鍵字的索引值
        Thread th;
        JButton change = new JButton("選擇模式");
        JButton again = new JButton("再一次");

        public Game2() { // 視窗大小:900*700
            this.setOpaque(false);
            for (int i = 0; i < 26; i++) {    //字母轉成字串，放入data裡
                word[i] = (char) ((97 + i));
                data[i] = (new Character(word[i])).toString();
                if (data[i].equalsIgnoreCase("A")) {
                    data[i]="C";
                }  //避免和單字相同的字母
                if (data[i].equalsIgnoreCase("P")) {
                    data[i]="U";
                }
                if (data[i].equalsIgnoreCase("L")) {
                    data[i]="T";
                }
                if (data[i].equalsIgnoreCase("E")) {
                    data[i]="W";
                }
                if (data[i].equalsIgnoreCase("G")) {
                    data[i]="H";
                }
                if (data[i].equalsIgnoreCase("O")) {
                    data[i]="K";
                }
                if (data[i].equalsIgnoreCase("D")) {
                    data[i]="R";
                }

            }
            data[26]="GOLD";
            data[27]="APPLE";
            th = new Thread(new timeChange());
            th.start();
        }

        public void paint(Graphics g) {

            if (time > 0) {
                for (int i = 0; i < 9; i++) {
                    g.setColor(Color.black);
                    g.setFont(new Font("SansSerif", Font.BOLD, 30));
                    g.drawString(data[i].toUpperCase(), xx[i], yy[i]);
                }
                g.setColor(Color.red);
                g.setFont(new Font("SansSerif", Font.BOLD, 20));
                g.drawString("Your grade:" + point, 10, 30);
                g.drawString("Time:" + time, 10, 50);
            } else {
                g.setColor(Color.red);
                g.setFont(new Font("SansSerif", Font.BOLD, 30));
                g.drawString("GAME OVER", 50, 170);
                g.drawString("Your grade: " + point, 50, 200);
                change.setFont(new Font("標楷體", Font.BOLD, 15));
                again.setFont(new Font("標楷體", Font.BOLD, 15));
                change.setBounds(50, 230, 100, 50);
                again.setBounds(152, 230, 100, 50);
                this.add(change);
                this.add(again);
                change.requestFocus();
                again.requestFocus();
                change.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Choose();
                        dispose();
                    }
                });
                again.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new G2();
                        dispose();
                    }
                });
            }
        }

        public void keyPressed(KeyEvent e) {
            String keyS = e.getKeyText(e.getKeyCode());
            int stat = 830;
            int nowIndex = -1;

            for (int i = 0; i < 9; i++) {
                if (keyS.equalsIgnoreCase(data[i].substring(0, 1))) {
                    if (data[i].length() == 1) {
                        if (xx[i] < stat) {
                            nowIndex = i;
                        }
                        if (keywordIndex > 0) {
                            time += 15;
                            keywordIndex = -1;
                        }
                    } else if (data[i].length() > 1) { // 道具的關鍵字
                        keywordIndex = i;
                        data[i] = data[i].substring(1, data[i].length());
                    }
                }
            }
            if (nowIndex != -1) {
                data[nowIndex] = data[(int) (Math.random() * 28)];
                point += 100;
            } else if (keywordIndex != -1) {
                point += 100;
            } else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            } else {
                point -= 100;
            }
        }

        public void keyTyped(KeyEvent e) {
        }

        public void keyReleased(KeyEvent e) {
        }

        private class timeChange implements Runnable {
            @Override
            public void run() {
                while (time > 0) {
                    try {
                        Thread.sleep(1000);
                        time--;
                    } catch (InterruptedException e) {
                    }
                    repaint();
                }
            }
        }
    }

    public static void main(String[] args) {
        new G2();
    }
}