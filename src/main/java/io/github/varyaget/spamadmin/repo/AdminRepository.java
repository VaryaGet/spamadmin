package io.github.varyaget.spamadmin.repo;

import io.github.varyaget.spamadmin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByTgId(Long tgId);
    Boolean existsAdminsByTgId(Long tgId);
}
