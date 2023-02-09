package com.example.tugas1_marchellaps.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
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

}
