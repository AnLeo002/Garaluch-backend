package com.BackendGaraLunch.persistence;

import com.BackendGaraLunch.productsSystem.persisitence.InvoiceEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String tel;
    @Column(name = "is_enabled")
    private boolean isEnabled;
    @Column(name = "account_no_expired")
    private boolean accountNoExpired;
    @Column(name = "account_no_locked")
    private boolean accountNoLocked;
    @Column(name = "credential_no_expired")
    private boolean credentialNoExpired;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();
    @OneToMany(targetEntity = InvoiceEntity.class,fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "user")
    private List<InvoiceEntity> invoiceEntities = new ArrayList<>();
}
