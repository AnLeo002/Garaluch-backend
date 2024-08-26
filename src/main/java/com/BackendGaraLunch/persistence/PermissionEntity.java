package com.BackendGaraLunch.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "permissions")
@Builder
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false,updatable = false)//NO PODEMOS CAMBIAR LOS PERMISOS DADOS AL CREAR EL USUARIO
    private String name;

}
