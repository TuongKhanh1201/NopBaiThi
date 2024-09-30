package gui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.QLKhachHang;
import model.KhachHang;

/**
 *
 * Võ lê tường khánh: 
 */
public class FrmQLKhachHang extends JFrame {

    private JTable tblKhachHang;  
    private JButton btDocFile, btGhiFile;

    private DefaultTableModel model;
    private JTextField txtMax, txtMin, txtTB;
  
    private JCheckBox chkSapXep;

    private static final String FILE_NHAP = "input.txt";
    private static final String FILE_XUAT = "output.txt";

    private QLKhachHang qlkh = new QLKhachHang();

    public FrmQLKhachHang(String title) {
        super(title);
        createGUI();
        processEvent();
        pack();
        //setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createGUI() {

        //tạo JTable
        String[] columnNames = {"Mã số", "Họ tên chủ hộ","Số nhân khẩu", "Chỉ số cũ (m3)", "Chỉ số mới(m3)","Tiêu thụ(m3)","Vượt định mức", "Tiền trả(đồng)"};
        model = new DefaultTableModel(null, columnNames);
        tblKhachHang = new JTable(model);
        //canh lề cho cột trong JTable
        DefaultTableCellRenderer rightRender = new DefaultTableCellRenderer();
        rightRender.setHorizontalAlignment(JLabel.RIGHT);
        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(JLabel.CENTER);
        
        tblKhachHang.getColumnModel().getColumn(6).setCellRenderer(centerRender);
        tblKhachHang.getColumnModel().getColumn(7).setCellRenderer(rightRender);
        //tạo thành phần quản lý cuộn cho Jtable
        JScrollPane scrollTable = new JScrollPane(tblKhachHang);      
        //tạo các điều khiển nhập liệu  và các nút lệnh
        JPanel p1 = new JPanel();

        p1.add(btDocFile = new JButton("Nhập dữ liệu khách hàng"));
        p1.add(btGhiFile = new JButton("Xuất hóa đơn thanh toán"));

        JPanel p2 = new JPanel();
        p2.add(new JLabel("Mức tiêu thụ thấp nhất:"));
        p2.add(txtMin = new JTextField(10));
        
        p2.add(new JLabel("Mức tiêu thụ cao nhất:"));
        p2.add(txtMax = new JTextField(10));
        
        p2.add(new JLabel("Mức tiêu thụ trung bình:"));
        p2.add(txtTB = new JTextField(10));
        
        p2.add(chkSapXep = new JCheckBox("Sắp xếp"));

        //add các thành phần vào cửa sổ
        add(p1, BorderLayout.NORTH);
        add(scrollTable, BorderLayout.CENTER);
        add(p2, BorderLayout.SOUTH);

    } 
    private void processEvent() {

        btDocFile.addActionListener((e) -> {
            //gọi phương thuc đoc danh sách sinh viên từ tập tin của lớp QLSinhVien
            qlkh.DocKhachHang(FILE_NHAP);
            //hiển thị danh sách sinh viên lên JTable
            loadDataToJTable();
        });
        btGhiFile.addActionListener(( e) -> {
            boolean result = qlkh.GhiHoaDon(FILE_XUAT);
            if (result) {
                JOptionPane.showMessageDialog(this, "Xuất hóa đơn thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Xuất hóa đơn thất bại!");
            }
        });

        chkSapXep.addActionListener(( e) -> {
            if (chkSapXep.isSelected()) {
                qlkh.sapXepTheoMucTieuThu();
                loadDataToJTable();
            }
        });
}

    private void loadDataToJTable() {
        model.setRowCount(0);
        for (KhachHang kh : qlkh.getDsKhachHang()) {
            model.addRow(new Object[]{kh.getMaso(), kh.getHoten(), kh.getSonhankhau(), kh.getChisocu(),kh.getChisomoi()});
            
    }
         model.setRowCount(0);
        for (KhachHang kh : qlkh.getDsKhachHang()) {
            String vuotDinhMuc = kh.getTieuThu() > kh.getDinhMuc() ? "X" : "";
            model.addRow(new Object[]{
                    kh.getMaso(),
                    kh.getHoten(),
                    kh.getSonhankhau(),
                    kh.getChisocu(),
                    kh.getChisomoi(),
                    kh.getTieuThu(),
                    vuotDinhMuc,
                    kh.tinhTienTra()
            }
                    }
    }
