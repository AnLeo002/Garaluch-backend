package com.BackendGaraLunch;

import com.BackendGaraLunch.persistence.PermissionEntity;
import com.BackendGaraLunch.persistence.RoleEntity;
import com.BackendGaraLunch.persistence.RoleEnum;
import com.BackendGaraLunch.persistence.UserEntity;
import com.BackendGaraLunch.repo.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class BackendGaraLunchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendGaraLunchApplication.class, args);
	}
	/*@Bean
	CommandLineRunner init(UserRepo repo, PasswordEncoder passwordEncoder){
		return args -> {
			//Create permissions
			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE")
					.build();
			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ")
					.build();
			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE")
					.build();
			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE")
					.build();
			PermissionEntity refactorPermission = PermissionEntity.builder()
					.name("REFACTOR")
					.build();
			//Create roles
			RoleEntity roleAdmin = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionEntities(Set.of(updatePermission,createPermission,deletePermission))
					.build();
			RoleEntity roleFather = RoleEntity.builder()
					.roleEnum(RoleEnum.FATHER)
					.permissionEntities(Set.of(refactorPermission))
					.build();
			RoleEntity roleTeacher = RoleEntity.builder()
					.roleEnum(RoleEnum.TEACH)
					.permissionEntities(Set.of(readPermission))
					.build();
			//Create users
			UserEntity userAndre = UserEntity.builder()
					.username("andre")
					.password(passwordEncoder.encode("1234"))
					.tel("31438979")
					.name("andre")
					.lastName("Saavedra")
					.email("lusle@Gmail.com")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();
			UserEntity userBritney = UserEntity.builder()
					.username("britney")
					.password(passwordEncoder.encode("1234"))
					.tel("31438979")
					.name("britney")
					.lastName("rojas")
					.email("bri@Gmail.com")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleTeacher))
					.build();
			UserEntity userJulian = UserEntity.builder()
					.username("julian")
					.password(passwordEncoder.encode("1234"))
					.tel("31438979")
					.name("julian")
					.lastName("Saavedra")
					.email("juli@Gmail.com")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleTeacher))
					.build();
			UserEntity userCesar = UserEntity.builder()
					.username("cesar")
					.password(passwordEncoder.encode("1234"))
					.tel("31438979")
					.name("cesar")
					.lastName("Saavedra")
					.email("cesar@Gmail.com")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleFather))
					.build();

			repo.saveAll(List.of(userBritney,userAndre,userCesar,userJulian));
		};
	}*/
}
