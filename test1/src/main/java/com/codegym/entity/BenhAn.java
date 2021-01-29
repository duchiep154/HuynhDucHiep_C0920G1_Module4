package com.codegym.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="benh_an")
public class BenhAn {

    @Id
    @GeneratedValue(generator = "my_generator")
    @GenericGenerator(name = "my_generator", strategy = "com.codegym.common.MyGenerator")
    @Column(name="id")
    private String id;
    private String liDoNhapVien;
    private String phuongPhapDieutri;
    private Date ngayNhapVien;

    //    ------------------------------------ Tạo Mối Quan Hệ ---------------------------------------------
    @ManyToOne
    @JoinColumn(name = "bac_si_id", referencedColumnName = "id")
    private BacSi bacSi;

    @ManyToOne
    @JoinColumn(name = "benh_nhan_id", referencedColumnName = "id")
    private BenhNhan benhNhan;
//    ---------------------------------------------------------------------------------------------------


    public BenhAn() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLiDoNhapVien() {
        return liDoNhapVien;
    }

    public void setLiDoNhapVien(String liDoNhapVien) {
        this.liDoNhapVien = liDoNhapVien;
    }

    public String getPhuongPhapDieutri() {
        return phuongPhapDieutri;
    }

    public void setPhuongPhapDieutri(String phuongPhapDieutri) {
        this.phuongPhapDieutri = phuongPhapDieutri;
    }

    public Date getNgayNhapVien() {
        return ngayNhapVien;
    }

    public void setNgayNhapVien(Date ngayNhapVien) {
        this.ngayNhapVien = ngayNhapVien;
    }

    public BacSi getBacSi() {
        return bacSi;
    }

    public void setBacSi(BacSi bacSi) {
        this.bacSi = bacSi;
    }

    public BenhNhan getBenhNhan() {
        return benhNhan;
    }

    public void setBenhNhan(BenhNhan benhNhan) {
        this.benhNhan = benhNhan;
    }
}
