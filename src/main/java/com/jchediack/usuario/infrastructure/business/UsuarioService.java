package com.jchediack.usuario.infrastructure.business;

import com.jchediack.usuario.infrastructure.business.converter.UsuarioConverter;
import com.jchediack.usuario.infrastructure.business.dto.UsuarioDTO;
import com.jchediack.usuario.infrastructure.entity.Usuario;
import com.jchediack.usuario.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioConverter usuarioConverter) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioConverter = usuarioConverter;
    }

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }
}