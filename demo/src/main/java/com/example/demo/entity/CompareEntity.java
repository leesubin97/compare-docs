package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "doc_compare")
public class CompareEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_index")
    private int docIndex;

    @Column(name = "doc_order")
    private int docOrder;

    @Column(name = "doc_content")
    private String docContent;

    @Column(name = "doc_title")
    private String docTitle;

    @Column(name = "doc_section")
    private String docSection;

    @Column(name = "doc_chapter")
    private String docChapter;

    @Column(name = "content_root1")
    private String contentRoot1;

    @Column(name = "content_root2")
    private String contentRoot2;

    @Column(name = "content_root3")
    private String contentRoot3;

    @Column(name = "content_root4")
    private String contentRoot4;

    @Column(name = "content_root5")
    private String contentRoot5;

    @Column(name = "content_root6")
    private String contentRoot6;
}
