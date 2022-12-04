import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class Start extends JFrame {
    private JLabel bgLabel1;
    private JButton btn1;
    private ImageIcon bg1;
    private JPanel imagePanel1;

    public Start() {
        // 開始介面
        // 建立標題、佈局
        super("打字遊戲");
        // 設定窗體寬高
        this.setSize(900, 700);
        // 窗體置中
        this.setLocationRelativeTo(null);
        this.setLayout(null);// 清空佈局管理器，即取消原來的邊界佈局管理器
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 設立frame的背景圖片
        bg1 = new ImageIcon(getClass().getResource("101.png")); // 背景圖片
        bgLabel1 = new JLabel(bg1); // 把背景圖顯示在Label中
        bgLabel1.setBounds(0, 0, bg1.getIconWidth(), bg1.getIconHeight()); // 把含有背景圖之Label位置設置為圖片剛好填充整個版面
        // 把内容視窗轉為JPanel，否則不能使用setOpaque()來使視窗變成透明
        imagePanel1 = (JPanel) this.getContentPane();
        imagePanel1.setOpaque(false);
        this.getLayeredPane().add(bgLabel1, new Integer(Integer.MIN_VALUE)); // 把背景圖添加到分層窗格的最底層以作為背景

        // 建立START按鈕
        btn1 = new JButton("START");
        btn1.setBounds(250, 350, 400, 180);
        this.add(btn1);
        btn1.setFont(new Font("SansSerif", Font.BOLD, 30));
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Start.super.setVisible(false);
                Choose f2 = new Choose();
                f2.setVisible(true);
                f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 若無這行，按叉後城市仍會繼續跑!!!
            }
        });
    }

    public static void main(String[] args) {
        new Start();
    }
}