package com.BackendGaraLunch.repo;

import com.BackendGaraLunch.persistence.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends CrudRepository<RoleEntity,Long> {
    List<RoleEntity> findRoleEntitiesByRoleEnumIn(List<String> roleName);//Este metodo va a buscar los roles que coincidan, con esto nos aseguramos que los roles existan
}
