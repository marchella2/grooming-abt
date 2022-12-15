package com.example.tugas1_marchellaps.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mst_book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // untuk generate auto increment
    private Long id;

    @Column(name = "judul_buku", nullable = false)
    private String judulBuku;

    @Column(name = "penerbit", nullable = false)
    private String penerbit;

    @Column(name = "pengarang", nullable = false)
    private String pengarang;

    @Column(name = "kategori_buku", nullable = false)
    private String kategoriBuku;

    @Column(name = "tahun_terbit", nullable = false)
    @JsonFormat(pattern = "yyyy")
    private Date tahunTerbit;

    public Book(Long id, String judulBuku, String penerbit, String pengarang, String kategoriBuku, Date tahunTerbit) {
        this.id = id;
        this.judulBuku = judulBuku;
        this.penerbit = penerbit;
        this.pengarang = pengarang;
        this.kategoriBuku = kategoriBuku;
        this.tahunTerbit = tahunTerbit;
    }

    public Book() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJudulBuku() {
        return judulBuku;
    }

    public void setJudulBuku(String judulBuku) {
        this.judulBuku = judulBuku;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getKategoriBuku() {
        return kategoriBuku;
    }

    public void setKategoriBuku(String kategoriBuku) {
        this.kategoriBuku = kategoriBuku;
    }

    public Date getTahunTerbit() {
        return tahunTerbit;
    }

    public void setTahunTerbit(Date tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }
}
