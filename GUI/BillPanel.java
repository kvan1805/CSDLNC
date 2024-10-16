import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.Button;
import java.awt.GridLayout;

public class BillPanel extends JPanel {

    private JPanel billPanel;
    private JButton addBillButton;
    private JButton searchButton;
    private JTextField searchTextField;
    private JScrollPane scrollPane;
    private JTable billTable;
    private JFrame inforFrame;
    private JFrame addBillFrame;

    public BillPanel() {
        initComponents();
        createInforFrame();
        createAddBillFrame();
    }

    private void initComponents() {
        addBillButton = new JButton("Tạo");
        searchButton = new JButton("Tìm kiếm");
        searchTextField = new JTextField(20);
        scrollPane = new JScrollPane();
        billTable = new JTable();

        DefaultTableModel model = new DefaultTableModel(
                new Object[][] {
                        { 1, "Nguyễn Văn A", "16/10/2024", 15000.0 },
                        { 2, "Trần Thị B", "17/10/2024", 17000.0 }
                },
                new String[] { "Mã", "Tên nhân viên", "Ngày tạo", "Tổng tiền" });

        billTable.setModel(model);
        billTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                inforbillTableMouseClicked(evt);
            }
        });

        addBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                showAddStaffForm();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                searchStaff();
            }
        });

        scrollPane.setViewportView(billTable);

        billPanel = new JPanel();
        GroupLayout billPanelLayout = new GroupLayout(billPanel);
        billPanel.setLayout(billPanelLayout);
        billPanelLayout.setHorizontalGroup(
                billPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(billPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(searchTextField)
                                .addGap(18, 18, 18)
                                .addComponent(
                                        searchButton,
                                        GroupLayout.PREFERRED_SIZE, 150,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(addBillButton)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE))
                        .addGroup(billPanelLayout.createSequentialGroup()
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE,
                                        1400, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));
        billPanelLayout.setVerticalGroup(
                billPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(billPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(billPanelLayout.createParallelGroup(
                                        GroupLayout.Alignment.BASELINE)
                                        .addComponent(addBillButton)
                                        .addComponent(searchTextField,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchButton))
                                .addGap(20, 20, 20)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE,
                                        750, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(20, Short.MAX_VALUE)));

        setLayout(new GridLayout(1, 1));
        add(billPanel);
    }

    private void inforbillTableMouseClicked(MouseEvent evt) {
        int selectedRow = billTable.getSelectedRow();
        if (selectedRow != -1) {
            String id = billTable.getValueAt(selectedRow, 0).toString();
            String name = billTable.getValueAt(selectedRow, 1).toString();
            String nxb = billTable.getValueAt(selectedRow, 2).toString();
            String gia = billTable.getValueAt(selectedRow, 3).toString();

            inforFrame.getContentPane().removeAll();

            inforFrame.add(new JLabel("Mã hoá đơn:"));
            inforFrame.add(new JTextField(id));

            inforFrame.add(new JLabel("Tên nhân viên:"));
            inforFrame.add(new JTextField(name));

            inforFrame.add(new JLabel("Ngày tạo:"));
            inforFrame.add(new JTextField(nxb));

            inforFrame.add(new JLabel("Tổng tiền:"));
            inforFrame.add(new JTextField(gia));

            inforFrame.add(new Button("Cập nhật thông tin"));
            // inforFrame.add(new Button("Xoá hoá đơn"));

            inforFrame.setVisible(true);
            inforFrame.revalidate();
            inforFrame.repaint();
        }
    }

    private void showAddStaffForm() {
        addBillFrame.getContentPane().removeAll();

        addBillFrame.add(new JLabel("Mã hoá đơn:"));
        addBillFrame.add(new JTextField());

        addBillFrame.add(new JLabel("Tên nhân viên hoá đơn:"));
        addBillFrame.add(new JTextField());

        addBillFrame.add(new JLabel("Ngày tạo:"));
        addBillFrame.add(new JTextField());

        addBillFrame.add(new JLabel("Tổng tiền:"));
        addBillFrame.add(new JTextField());

        addBillFrame.add(new Button("Thêm hoá đơn"));

        addBillFrame.setVisible(true);
        addBillFrame.revalidate();
        addBillFrame.repaint();
    }

    private void createInforFrame() {
        inforFrame = new JFrame("Thông tin hoá đơn");
        inforFrame.setLayout(new GridLayout(5, 2, 10, 10));
        inforFrame.setSize(400, 200);
        inforFrame.setLocationRelativeTo(null);
        inforFrame.setVisible(false);
    }

    private void createAddBillFrame() {
        addBillFrame = new JFrame("Thêm hoá đơn");
        addBillFrame.setLayout(new GridLayout(5, 2, 10, 10));
        addBillFrame.setSize(400, 200);
        addBillFrame.setLocationRelativeTo(null);
        addBillFrame.setVisible(false);
    }

    private void searchStaff() {
        String keyword = searchTextField.getText().toLowerCase();

        DefaultTableModel model = (DefaultTableModel) billTable.getModel();
        DefaultTableModel filteredModel = new DefaultTableModel(
                new String[] { "Mã", "Tên nhân viên", "Ngày tạo", "Tổng tiền" }, 0);

        // Duyệt qua các dòng của bảng và lọc dữ liệu
        for (int i = 0; i < model.getRowCount(); i++) {
            String name = model.getValueAt(i, 1).toString().toLowerCase();
            String phone = model.getValueAt(i, 2).toString();

            if (name.contains(keyword) || phone.contains(keyword)) {
                filteredModel.addRow(new Object[] {
                        model.getValueAt(i, 0),
                        model.getValueAt(i, 1),
                        model.getValueAt(i, 2),
                        model.getValueAt(i, 3)
                });
            }
        }
        billTable.setModel(filteredModel);
    }
}
