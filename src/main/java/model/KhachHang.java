package model;

/**
 *
 * Võ Lê Tường Khánh:
 */
public class KhachHang {

    private String maso;
    private String hoten;
    private int sonhankhau;
    private double chisocu;
    private double chisomoi;

    //constructor
    public KhachHang() {
    }

    public KhachHang(String maso) {
        this.maso = maso;
    }

    public KhachHang(String maso, String hoten, int sonhankhau, double chisocu, double chisomoi) {
        this.maso = maso;
        this.hoten = hoten;
        this.sonhankhau = sonhankhau;
        this.chisocu = chisocu;
        this.chisomoi = chisomoi;
    }

    //setter và getter
    public String getMaso() {
        return maso;
    }

    public void setMaso(String maso) {
        this.maso = maso;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public int getSonhankhau() {
        return sonhankhau;
    }

    public void setSonhankhau(int sonhankhau) {
        this.sonhankhau = sonhankhau;
    }

    public double getChisocu() {
        return chisocu;
    }

    public void setChisocu(double chisocu) {
        this.chisocu = chisocu;
    }

    public double getChisomoi() {
        return chisomoi;
    }

    public void setChisomoi(double chisomoi) {
        this.chisomoi = chisomoi;
    }

    //phương thức tính toán    
    public double getTieuThu() {
        return chisomoi - chisocu;
    }

    public double getDinhMuc() {
        double tieuThu = getTieuThu();
        double dinhMuc = 0;

        // Tính định mức theo số khối nước tiêu thụ
        if (tieuThu >= 0 && tieuThu <= 4 * sonhankhau) {
            dinhMuc = tieuThu * 6700;
        } else if (tieuThu > 4 * sonhankhau && tieuThu <= 6 * sonhankhau) {
            dinhMuc = (4 * sonhankhau * 6700) + ((tieuThu - 4 * sonhankhau) * 12900);
        } else {
            dinhMuc = (4 * sonhankhau * 6700) + (2 * sonhankhau * 12900) + ((tieuThu - 6 * sonhankhau) * 14400);
        }

        return dinhMuc;
    }

    public double tinhTienTra() {
        double giaBan = getDinhMuc();
        double thueGTGT = giaBan * 0.05;
        double tdvtn = giaBan * 0.25;
        double thueTDVTN = tdvtn * 0.08;

        double tienTra = giaBan + thueGTGT + tdvtn + thueTDVTN;
        return tienTra;
    }
}
