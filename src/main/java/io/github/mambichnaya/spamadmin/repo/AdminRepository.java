package io.github.mambichnaya.spamadmin.repo;

import io.github.mambichnaya.spamadmin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByTgId(Long tgId);
    Boolean existsAdminsByTgId(Long tgId);
}
