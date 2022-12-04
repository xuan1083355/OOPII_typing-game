import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import javax.swing.*;

public class G1 extends JFrame {
    private JLabel bgLabel;
    private ImageIcon bg;

    public G1() {
        super("打字遊戲");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 700);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        Game1 g = new Game1();
        this.add(g);
        this.addKeyListener(g);// 註冊鍵盤事件
        g.addKeyListener(g);// 註冊鍵盤事件
        bg = new ImageIcon(getClass().getResource("301.png")); // 背景圖片
        bgLabel = new JLabel(bg); // 把背景圖顯示在Label中
        bgLabel.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight()); // 把含有背景圖之Label位置設置為圖片剛好填充整個版面
        // 把内容視窗轉為JPanel，否則不能使用setOpaque()來使視窗變成透明
        JPanel imagePanel = (JPanel) this.getContentPane();
        imagePanel.setOpaque(false);
        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE)); // 把背景圖添加到分層窗格的最底層以作為背景

    }

    public class Game1 extends JPanel implements KeyListener {
        int xx[] = new int[10];
        int yy[] = { 330, 360, 390, 420, 450, 480, 510, 540, 570, 600 };
        String word[] = new String[10];  //放射出來的十個單字
        char[] abc=new char[26];   //存放單字庫
        String data[]=new String[29];
        int point = 0;
        int life = 3;
        int keywordIndex=0;
        JButton change = new JButton("選擇模式");
        JButton again = new JButton("再一次");

        public Game1() {
            this.setOpaque(false);
            //放單字庫
            for(int j=0;j<26;j++){
                abc[j]=(char)(97+j);
                data[j]=(new Character(abc[j])).toString();
                if (data[j].equalsIgnoreCase("T")) {
                    data[j]="D";
                }
                if (data[j].equalsIgnoreCase("U")) {
                    data[j]="A";
                }
                if (data[j].equalsIgnoreCase("R")) {
                    data[j]="Q";
                }
                if (data[j].equalsIgnoreCase("L")) {
                    data[j]="P";
                }
                if (data[j].equalsIgnoreCase("E")) {
                    data[j]="B";
                }
                if (data[j].equalsIgnoreCase("C")) {
                    data[j] ="V";
                }
                if (data[j].equalsIgnoreCase("O")) {
                    data[j]="F";
                }
                if (data[j].equalsIgnoreCase("G")) {
                    data[j]="H";
                }
                if (data[j].equalsIgnoreCase("Y")) {
                    data[j]="J";
                }
                if (data[j].equalsIgnoreCase("N")) {
                    data[j]="K";
                }
            }
            data[26]="TURTLE";
            data[27]="Ecology";
            data[28]="GREEN";
            for (int i = 0; i < 10; i++) {
                xx[i] = (int) (600 + (Math.random() * 200));
                word[i] = data[(int)(Math.random()*29)];
            }
        }

        public void paint(Graphics g) {
            if (life > 0) {
                for (int i = 0; i < 10; i++) {
                    g.setColor(Color.darkGray);
                    g.setFont(new Font("SansSerif", Font.BOLD, 40));
                    g.drawString(word[i].toUpperCase(), xx[i], yy[i]);
                }
                g.setColor(Color.red);
                g.setFont(new Font("SansSerif", Font.BOLD, 20));
                g.drawString("Your grade:" + point, 10, 30);
                g.drawString("HP:" + life, 10, 50);
                move();
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
                        new G1();
                        dispose();
                    }
                });
            }
        }

        public void move() {
            for (int i = 0; i < 10; i++) {
                xx[i]--;
                if (xx[i] == 200) {
                    xx[i] = 800;
                    word[i] = data[(int)(Math.random()*29)];
                    life -= 1;
                }
                try {
                    if (point < 1000) {
                        Thread.sleep(3);
                    } else if (point < 5000) {
                        Thread.sleep(5);
                    } else {
                        Thread.sleep(1); // 找找可不可以比1豪秒快
                    }
                } catch (InterruptedException e) {

                }
                repaint();
            }
        }

        public void keyPressed(KeyEvent e) {
            String keyS=e.getKeyText(e.getKeyCode());
            int stat = 801;
            int nowIndex = -1;

            for (int i = 0; i < 10; i++) {
                if(word[i].length()>0) {
                    if (keyS.equalsIgnoreCase(word[i].substring(0, 1))) {
                        if (word[i].length() == 1) {
                            if (xx[i] < stat && i!=keywordIndex) {
                                nowIndex = i;
                            }
                            if ( i == keywordIndex) {
                                life += 1;
                                point+=100;
                                xx[keywordIndex] = 800;
                                word[keywordIndex] = data[(int)(Math.random()*29)];
                                keywordIndex = -1;
                            }
                        } else if (word[i].length() > 1) {   //道具的關鍵字
                            keywordIndex = i;
                            word[i] = word[i].substring(1, word[i].length());
                            point+=100;
                        }
                    }
                }
            }
            if(e.getKeyCode()==KeyEvent.VK_SHIFT){}
            else if (nowIndex != -1) {
                xx[nowIndex] = 800;
                word[nowIndex] = data[(int)(Math.random()*29)];
                point+= 100;
            } else if(keywordIndex!=-1){

            }
            else {
                point-=100;
            }
            repaint();

        }

        public void keyTyped(KeyEvent e) {
        }

        public void keyReleased(KeyEvent e) {

        }
    }

    public static void main(String[] args) {
        new G1();
    }
}