package com.teksystems.bootcamp.commerce_data.models;

import javax.persistence.*;

@Entity
@Table(name = "privileges")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer privilege_id;

    @Column
    private String privilege_name;

    public Authority() {
    }

    public String getAuthority() {
        return privilege_name;
    }

    public void setAuthority(String authority) {
        this.privilege_name = authority;
    }
}
