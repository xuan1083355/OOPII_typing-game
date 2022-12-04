import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class Choose extends JFrame {
    private JLabel bgLabel;
    private JButton ch1, ch2, ch3;
    private ImageIcon bg;
    private JPanel imageJPanel;

    public Choose() {
        super("打字遊戲");
        this.setSize(900, 700);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bg = new ImageIcon(getClass().getResource("201.png"));
        bgLabel = new JLabel(bg);
        bgLabel.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());
        imageJPanel = (JPanel) this.getContentPane();
        imageJPanel.setOpaque(false);
        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));

        ch1 = new JButton("模式一");
        ch2 = new JButton("模式二");
        ch3 = new JButton("模式三");
        ch1.setFont(new Font("標楷體", Font.BOLD, 20));
        ch2.setFont(new Font("標楷體", Font.BOLD, 20));
        ch3.setFont(new Font("標楷體", Font.BOLD, 20));
        ch1.setBounds(60, 400, 200, 150);
        ch2.setBounds(340, 400, 200, 150);
        ch3.setBounds(640, 400, 200, 150);
        this.add(ch1);
        this.add(ch2);
        this.add(ch3);

        ch1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Choose.super.setVisible(false);
                new G1();
            }
        });

        ch2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Choose.super.setVisible(false);
                new G2();
            }
        });

        ch3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Choose.super.setVisible(false);
                new G3();
            }
        });
    }

    public static void main(String[] args) {
        new Choose();
    }
}