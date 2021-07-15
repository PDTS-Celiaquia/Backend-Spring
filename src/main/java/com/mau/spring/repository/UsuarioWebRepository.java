package com.mau.spring.repository;

import com.mau.spring.auth.UsuarioWeb;
import org.checkerframework.common.util.report.qual.ReportCreation;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Interfaz responsable de Alta y consulta (proxiammente Baja y modificación) de Usuarios Web
 */
@Repository
public interface UsuarioWebRepository {

        UsuarioWeb guardar(UsuarioWeb user);

        Optional<UsuarioWeb> buscar(String id);

        Optional<UsuarioWeb> buscarPorEmail(String email);
}
