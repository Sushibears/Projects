package com.teksystems.bootcamp.commerce_data.models;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="user_login")
public class LoginUser {

    @Id
    @GeneratedValue
    private Integer customer_id;

    @Column(name = "user_username")
    private String username;

    @Column(name = "user_password")
    private String password;

    @Column
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "privilege")
    private Set<Authority> authority;

    public Integer getId() {
        return customer_id;
    }

    public void setId(Integer id) {
        this.customer_id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user_username) {
        this.username = user_username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Authority> getAuthority() {
        return authority;
    }

    public void setAuthority(Set<Authority> authority) {
        this.authority = authority;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customer_id == null) ? 0 : customer_id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LoginUser other = (LoginUser) obj;
        if (customer_id == null) {
            if (other.customer_id != null)
                return false;
        } else if (!customer_id.equals(other.customer_id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + customer_id + ", username=" + username + ", password=" + password + "]";
    }
}