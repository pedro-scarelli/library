package com.login.user.domain.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_addresses")
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_id")
    private UUID id;

    @Column(name = "st_cep", nullable = false, length = 8)
    private String cep;

    @Column(name = "st_logradouro", nullable = false)
    private String logradouro;

    @Column(name = "st_complemento")
    private String complemento;

    @Column(name = "st_bairro", nullable = false)
    private String bairro;

    @Column(name = "st_localidade", nullable = false)
    private String localidade;

    @Column(name = "st_uf", nullable = false, length = 2)
    private String uf;

    @Column(name = "st_numero")
    private String numero;

    @Column(name = "dt_created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    @OneToOne(mappedBy = "address")
    private User user;
}