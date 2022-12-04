import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import javax.swing.*;

public class G3 extends JFrame {
    private JLabel bgLabel;

    private ImageIcon bg;

    public G3() {
        super("打字遊戲");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 700);
        this.setVisible(true);
        bg = new ImageIcon(getClass().getResource("501.png")); // 背景圖片
        bgLabel = new JLabel(bg); // 把背景圖顯示在Label中
        bgLabel.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight()); // 把含有背景圖之Label位置設置為圖片剛好填充整個版面
        // 把内容視窗轉為JPanel，否則不能使用setOpaque()來使視窗變成透明
        JPanel imagePanel = (JPanel) this.getContentPane();
        imagePanel.setOpaque(false);
        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE)); // 把背景圖添加到分層窗格的最底層以作為背景
        this.setLocationRelativeTo(null);
        Game3 g3 = new Game3();
        this.add(g3);
        this.addKeyListener(g3);// 註冊鍵盤事件
        g3.addKeyListener(g3);// 註冊鍵盤事件

    }

    public class Game3 extends JPanel implements KeyListener {
        int time = 0;
        int nowIndex = 0, index = 0;
        String[] data = new String[29];
        char[] word = new char[26];
        boolean key=false,stop=false;
        Thread th;
        JButton change = new JButton("選擇模式");
        JButton again = new JButton("再一次");

        public Game3() {
            index=(int)(Math.random()*29);
            this.setOpaque(false);
            //儲存單字庫
            for (int i = 0; i < 26; i++) {
                word[i] = (char) ((97 + i));
                data[i] = (new Character(word[i])).toString();  //字母轉成字串，放入data裡
                if (data[i].equalsIgnoreCase("E")) {
                    data[i]="Z";
                }
                if (data[i].equalsIgnoreCase("C")) {
                    data[i]="U";
                }
                if (data[i].equalsIgnoreCase("O")) {
                    data[i]="T";
                }
                if (data[i].equalsIgnoreCase("N")) {
                    data[i]="W";
                }
                if (data[i].equalsIgnoreCase("M")) {
                    data[i]="H";
                }
                if (data[i].equalsIgnoreCase("Y")) {
                    data[i]="K";
                }
                if (data[i].equalsIgnoreCase("P")) {
                    data[i]="J";
                }
                if (data[i].equalsIgnoreCase("A")) {
                    data[i]="R";
                }
            }
            data[26]="ECONOMY";
            data[27]="MONEY";
            data[28]="PAY";
            th = new Thread(new timeChange());
            th.start();
        }

        public void paint(Graphics g) {
            if (nowIndex < 12) {
                g.setColor(Color.black);
                g.setFont(new Font("SansSerif", Font.BOLD, 30));
                g.drawString(data[index].toUpperCase(), 250, 500);
                g.setColor(Color.red);
                g.setFont(new Font("SansSerif", Font.BOLD, 30));
                g.drawString("Time:" + time, 20, 50);
            } else {
                try {
                    Thread.sleep(1000); // 防止遊戲結束後繼續加秒的畫面
                } catch (InterruptedException e) {
                }
                g.setColor(Color.red);
                g.setFont(new Font("SansSerif", Font.BOLD, 30));
                g.drawString("GAME OVER", 200, 500);
                g.drawString("Take time: " + time, 200, 550);
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
                        new G3();
                        dispose();
                    }
                });
            }
        }

        private class timeChange implements Runnable {
            @Override
            public void run() {
                while (nowIndex < 12) {
                    if(stop==true){
                        time-=5;
                        stop=false;
                    }
                    try {
                        Thread.sleep(1000);
                        time++;
                    } catch (InterruptedException e) {
                    }
                    repaint();
                }

            }
        }

        public void keyPressed(KeyEvent e) {
            String keyS=e.getKeyText(e.getKeyCode());
            if (keyS.equalsIgnoreCase(data[index].substring(0,1))) {
                if (data[index].length() <= 1) {   //長度<=1時
                    if (key == true) {
                        stop=true;
                        key=false;
                    }
                    index = (int) (Math.random() * 29);
                    nowIndex++;
                } else {   //長度>1，表示為道具關鍵字
                    key = true;
                    data[index] = data[index].substring(1, data[index].length());
                }
            }else if(e.getKeyCode()==KeyEvent.VK_SHIFT){}
        }

        public void keyTyped(KeyEvent e) {
        }

        public void keyReleased(KeyEvent e) {

        }
    }

    public static void main(String[] args) {
        new G3();
    }
}