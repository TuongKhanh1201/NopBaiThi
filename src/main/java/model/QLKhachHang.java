package model;

import java.util.ArrayList;
import java.util.Collections;
import util.FileHelper;

/**
 *
 * võ lê tường khánh:
 */
public class QLKhachHang {

    private ArrayList<KhachHang> dsKhachHang;

    public QLKhachHang() {
        dsKhachHang = new ArrayList<>();
    }

    public QLKhachHang(ArrayList<KhachHang> dsKhachHang) {
        this.dsKhachHang = dsKhachHang;
    }

    public ArrayList<KhachHang> getDsKhachHang() {
        return dsKhachHang;
    }

    public void setDsKhachHang(ArrayList<KhachHang> dsKhachHang) {
        this.dsKhachHang = dsKhachHang;
    }

    public void DocKhachHang(String filename) {
        //sinh viên viết code 
        ArrayList<String> data = FileHelper.readFileText(filename); //doc file
        //đổ dữ liệu vào danh sách
        dsKhachHang.clear();
        for (String item : data) {
            String[] arr = item.split(";");
            KhachHang kh = new KhachHang();
            kh.setMaso(arr[0]);
            kh.setHoten(arr[1]);
            kh.setSonhankhau(Integer.parseInt(arr[3]));
            kh.setChisocu(Double.parseDouble(arr[4]));
            kh.setChisomoi(Double.parseDouble(arr[5]));
            dsKhachHang.add(kh);
        }
    }

    public boolean GhiHoaDon(String filename) {
        ArrayList<String> data = new ArrayList<>();
        for (KhachHang kh : dsKhachHang) {
            String info = kh.getMaso() + ";" + kh.getHoten() + ";" + kh.getTieuThu() + ";" + kh.tinhTienTra();
            data.add(info);
        }
        return FileHelper.writeFileText(filename, data);
    }

    public void sapXepTheoMucTieuThu() {
        //sinh viên viết code  
        Comparator<KhachHang> cmp = (kh2,kh1) ->{
            return Double.compare(kh1.getTieuThu,kh2.getTieuThu());
            
        };
        Collections 
    }

    public double getTieuThuCaoNhat() {
        double max = 0;
        for (KhachHang kh : dsKhachHang) {
            double tieuThu = kh.getTieuThu();
            if (tieuThu > max) {
                max = tieuThu;
            }
        }
        return max;
    }

    public double getTieuThuThapNhat() {
        double min = 0;
        return min;
    }

    public double getTieuThuTrungBinh() {
        if (dsKhachHang.isEmpty()) {
            return 0;
        }
        double tongTieuThu = 0;
        for (KhachHang kh : dsKhachHang) {
            tongTieuThu += kh.getTieuThu();
        }
        return tongTieuThu / dsKhachHang.size();
    }
}
