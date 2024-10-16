import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {

    private JPanel cardPanel;
    private JPanel buttonPanel;
    private JButton bookButton;
    private JButton billButton;
    private JButton staffButton;

    public Main() {
        initComponents();
    }

    private void initComponents() {
        cardPanel = new JPanel(new CardLayout());
        buttonPanel = new JPanel();

        JPanel staffPanel = new StaffPanel();
        JPanel bookPanel = new BookPanel();
        JPanel billPanel = new BillPanel();

        cardPanel.add(staffPanel, "Nhân viên");
        cardPanel.add(bookPanel, "Sách");
        cardPanel.add(billPanel, "Hoá đơn");

        bookButton = new JButton("Sách");
        billButton = new JButton("Hoá đơn");
        staffButton = new JButton("Nhân viên");

        // Cài đặt ActionListener cho các nút
        bookButton.addActionListener(e -> switchPanel("Sách"));
        staffButton.addActionListener(e -> switchPanel("Nhân viên"));
        billButton.addActionListener(e -> switchPanel("Hoá đơn"));

        // Cài đặt layout cho buttonPanel
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Căn các nút về phía góc trên bên trái
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Thêm khoảng cách giữa các nút
        buttonPanel.add(bookButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Khoảng cách dọc 10px
        buttonPanel.add(billButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Khoảng cách dọc 10px
        buttonPanel.add(staffButton);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.15;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(buttonPanel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.85;
        add(cardPanel, gbc); // Thay staffPanel bằng cardPanel

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 900);
        setLocationRelativeTo(null);
    }

    private void switchPanel(String panelName) {
        CardLayout cl = (CardLayout) (cardPanel.getLayout());
        cl.show(cardPanel, panelName); // Chuyển đến panel tương ứng
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
