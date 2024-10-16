import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class BookPanel extends JPanel {

    private JButton addBookButton;
    private JButton searchBookButton;
    private JTextField searchTextField;
    private JScrollPane scrollPane;
    private JTable bookTable;
    private JFrame inforFrame;
    private JFrame addBookFrame;

    public BookPanel() {
        initComponents();
        createInforFrame();
        createAddBookFrame();
    }

    private void initComponents() {
        addBookButton = new JButton("Thêm");
        searchBookButton = new JButton("Tìm kiếm");
        searchTextField = new JTextField(40); // Đặt chiều rộng ô tìm kiếm thành 40 ký tự
        scrollPane = new JScrollPane();
        bookTable = new JTable();

        // Tạo mô hình bảng và thêm dữ liệu mẫu
        DefaultTableModel model = new DefaultTableModel(
                new Object[][] {
                        { 1, "Sách thánh", "Kim đồng", 5000.0 },
                        { 2, "Sách truy hồn", "Sự thật", 7000.0 }
                },
                new String[] { "Mã", "Tên", "Nhà sản xuất", "Giá" });

        bookTable.setModel(model);
        bookTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                inforbookTableMouseClicked(evt);
            }
        });

        // Thêm ActionListener cho nút "Thêm"
        addBookButton.addActionListener(evt -> showAddBookForm());
        searchBookButton.addActionListener(evt -> searchBook());

        scrollPane.setViewportView(bookTable);

        // Layout cho bookPanel
        GroupLayout bookPanelLayout = new GroupLayout(this);
        setLayout(bookPanelLayout);
        bookPanelLayout.setHorizontalGroup(
                bookPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(bookPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(searchTextField)
                                .addGap(18, 18, 18)
                                .addComponent(searchBookButton)
                                .addGap(18, 18, 18)
                                .addComponent(addBookButton)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(bookPanelLayout.createSequentialGroup()
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1400, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));

        bookPanelLayout.setVerticalGroup(
                bookPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(bookPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(addBookButton)
                                .addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(searchBookButton))
                        .addGap(20, 20, 20)
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 750, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE));
    }

    private void inforbookTableMouseClicked(MouseEvent evt) {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow != -1) {
            String id = bookTable.getValueAt(selectedRow, 0).toString();
            String name = bookTable.getValueAt(selectedRow, 1).toString();
            String nxb = bookTable.getValueAt(selectedRow, 2).toString();
            String gia = bookTable.getValueAt(selectedRow, 3).toString();

            inforFrame.getContentPane().removeAll();
            inforFrame.add(new JLabel("Mã sách:"));
            inforFrame.add(new JTextField(id));

            inforFrame.add(new JLabel("Tên sách:"));
            inforFrame.add(new JTextField(name));

            inforFrame.add(new JLabel("Nhà sản xuất:"));
            inforFrame.add(new JTextField(nxb));

            inforFrame.add(new JLabel("Giá:"));
            inforFrame.add(new JTextField(gia));

            inforFrame.add(new JButton("Cập nhật thông tin"));
            inforFrame.add(new JButton("Xoá sách"));

            inforFrame.setVisible(true);
            inforFrame.revalidate();
            inforFrame.repaint();
        }
    }

    private void showAddBookForm() {
        // Hiển thị form thêm sách
        addBookFrame.getContentPane().removeAll();

        addBookFrame.add(new JLabel("Mã sách:"));
        addBookFrame.add(new JTextField());

        addBookFrame.add(new JLabel("Tên sách:"));
        addBookFrame.add(new JTextField());

        addBookFrame.add(new JLabel("Nhà sản xuất:"));
        addBookFrame.add(new JTextField());

        addBookFrame.add(new JLabel("Giá:"));
        addBookFrame.add(new JTextField());

        addBookFrame.add(new JButton("Thêm sách"));

        addBookFrame.setVisible(true);
        addBookFrame.revalidate();
        addBookFrame.repaint();
    }

    private void createInforFrame() {
        inforFrame = new JFrame("Thông tin sách");
        inforFrame.setLayout(new GridLayout(5, 2, 10, 10));
        inforFrame.setSize(400, 200);
        inforFrame.setLocationRelativeTo(null);
        inforFrame.setVisible(false);
    }

    private void createAddBookFrame() {
        addBookFrame = new JFrame("Thêm sách");
        addBookFrame.setLayout(new GridLayout(5, 2, 10, 10));
        addBookFrame.setSize(400, 200);
        addBookFrame.setLocationRelativeTo(null);
        addBookFrame.setVisible(false);
    }

    private void searchBook() {
        String keyword = searchTextField.getText().toLowerCase();
        DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
        DefaultTableModel filteredModel = new DefaultTableModel(
                new String[] { "Mã", "Tên", "Nhà sản xuất", "Giá" }, 0);

        // Duyệt qua các dòng của bảng và lọc dữ liệu
        for (int i = 0; i < model.getRowCount(); i++) {
            String name = model.getValueAt(i, 1).toString().toLowerCase();
            String nxb = model.getValueAt(i, 2).toString().toLowerCase();

            if (name.contains(keyword) || nxb.contains(keyword)) {
                filteredModel.addRow(new Object[] {
                        model.getValueAt(i, 0),
                        model.getValueAt(i, 1),
                        model.getValueAt(i, 2),
                        model.getValueAt(i, 3)
                });
            }
        }
        bookTable.setModel(filteredModel);
    }
}
