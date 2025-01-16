package com.jchediack.usuario.infrastructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "telefone")
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "telefone", length = 10)
    private String telefone;
    @Column(name = "ddd", length = 3)
    private String ddd;

    public Telefone(){

    }

    public Telefone(Long id, String telefone, String ddd) {
        this.id = id;
        this.telefone = telefone;
        this.ddd = ddd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }
}
