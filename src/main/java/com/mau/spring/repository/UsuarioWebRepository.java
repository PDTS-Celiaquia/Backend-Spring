package com.mau.spring.repository;

import com.mau.spring.auth.UsuarioWeb;
import org.checkerframework.common.util.report.qual.ReportCreation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioWebRepository extends JpaRepository<UsuarioWeb, Long> {
        Optional<UsuarioWeb> findByEmail(String email);
}
