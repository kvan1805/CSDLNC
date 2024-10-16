
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.Button;
import java.awt.GridLayout;

public class StaffPanel extends JPanel {

        private JPanel staffPanel;
        private JButton addStaffButton;
        private JButton searchStaffButton;
        private JTextField searchTextField;
        private JScrollPane scrollPane;
        private JTable staffTable;
        private JFrame inforFrame;
        private JFrame addStaffFrame;

        public StaffPanel() {
                initComponents();
                createInforFrame();
                createAddStaffFrame();
        }

        private void initComponents() {
                addStaffButton = new JButton("Thêm");
                searchStaffButton = new JButton("Tìm kiếm");
                searchTextField = new JTextField(20);
                scrollPane = new JScrollPane();
                staffTable = new JTable();

                DefaultTableModel model = new DefaultTableModel(
                                new Object[][] {
                                                { 1, "Nguyễn Văn A", "0123456789", 5000.0 },
                                                { 2, "Lê Thị B", "0987654321", 7000.0 }
                                },
                                new String[] { "Mã", "Tên", "Số điện thoại", "Lương" });

                staffTable.setModel(model);
                staffTable.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent evt) {
                                inforStaffTableMouseClicked(evt);
                        }
                });

                // Thêm ActionListener cho nút "Thêm"
                addStaffButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                                showAddStaffForm();
                        }
                });

                searchStaffButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                                searchStaff();
                        }
                });

                scrollPane.setViewportView(staffTable);

                // Layout cho staffPanel
                staffPanel = new JPanel();
                GroupLayout staffPanelLayout = new GroupLayout(staffPanel);
                staffPanel.setLayout(staffPanelLayout);
                staffPanelLayout.setHorizontalGroup(
                                staffPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(staffPanelLayout.createSequentialGroup()
                                                                .addGap(20, 20, 20)
                                                                .addComponent(searchTextField)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(searchStaffButton,
                                                                                GroupLayout.PREFERRED_SIZE, 150,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(addStaffButton)
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
                                                .addGroup(staffPanelLayout.createSequentialGroup()
                                                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE,
                                                                                1400, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE)));
                staffPanelLayout.setVerticalGroup(
                                staffPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(staffPanelLayout.createSequentialGroup()
                                                                .addGap(20, 20, 20)
                                                                .addGroup(staffPanelLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(addStaffButton)
                                                                                .addComponent(searchTextField,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(searchStaffButton))
                                                                .addGap(20, 20, 20)
                                                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE,
                                                                                750, GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(20, Short.MAX_VALUE)));

                // Add staffPanel to this panel
                setLayout(new GridLayout(1, 1));
                add(staffPanel);
        }

        private void inforStaffTableMouseClicked(MouseEvent evt) {
                int selectedRow = staffTable.getSelectedRow();
                if (selectedRow != -1) {
                        String id = staffTable.getValueAt(selectedRow, 0).toString();
                        String name = staffTable.getValueAt(selectedRow, 1).toString();
                        String sdt = staffTable.getValueAt(selectedRow, 2).toString();
                        String luong = staffTable.getValueAt(selectedRow, 3).toString();

                        inforFrame.getContentPane().removeAll();

                        inforFrame.add(new JLabel("Mã nhân viên:"));
                        inforFrame.add(new JTextField(id));

                        inforFrame.add(new JLabel("Tên nhân viên:"));
                        inforFrame.add(new JTextField(name));

                        inforFrame.add(new JLabel("Số điện thoại:"));
                        inforFrame.add(new JTextField(sdt));

                        inforFrame.add(new JLabel("Lương:"));
                        inforFrame.add(new JTextField(luong));

                        inforFrame.add(new Button("Cập nhật thông tin"));
                        inforFrame.add(new Button("Xoá nhân viên"));

                        inforFrame.setVisible(true);
                        inforFrame.revalidate();
                        inforFrame.repaint();
                }
        }

        private void showAddStaffForm() {
                // Hiển thị form thêm nhân viên
                addStaffFrame.getContentPane().removeAll();

                addStaffFrame.add(new JLabel("Mã nhân viên:"));
                addStaffFrame.add(new JTextField());

                addStaffFrame.add(new JLabel("Tên nhân viên:"));
                addStaffFrame.add(new JTextField());

                addStaffFrame.add(new JLabel("Số điện thoại:"));
                addStaffFrame.add(new JTextField());

                addStaffFrame.add(new JLabel("Lương:"));
                addStaffFrame.add(new JTextField());

                addStaffFrame.add(new Button("Thêm nhân viên"));

                addStaffFrame.setVisible(true);
                addStaffFrame.revalidate();
                addStaffFrame.repaint();
        }

        private void createInforFrame() {
                inforFrame = new JFrame("Thông tin nhân viên");
                inforFrame.setLayout(new GridLayout(5, 2, 10, 10));
                inforFrame.setSize(400, 200);
                inforFrame.setLocationRelativeTo(null);
                inforFrame.setVisible(false);
        }

        private void createAddStaffFrame() {
                addStaffFrame = new JFrame("Thêm nhân viên");
                addStaffFrame.setLayout(new GridLayout(5, 2, 10, 10));
                addStaffFrame.setSize(400, 200);
                addStaffFrame.setLocationRelativeTo(null);
                addStaffFrame.setVisible(false);
        }

        private void searchStaff() {
                String keyword = searchTextField.getText().toLowerCase();

                DefaultTableModel model = (DefaultTableModel) staffTable.getModel();
                DefaultTableModel filteredModel = new DefaultTableModel(
                                new String[] { "Mã", "Tên", "Số điện thoại", "Lương" }, 0);

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
                staffTable.setModel(filteredModel);
        }
}
