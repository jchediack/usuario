package com.jchediack.usuario.infrastructure.business;

import com.jchediack.usuario.infrastructure.business.converter.UsuarioConverter;
import com.jchediack.usuario.infrastructure.business.dto.UsuarioDTO;
import com.jchediack.usuario.infrastructure.entity.Usuario;
import com.jchediack.usuario.infrastructure.exceptions.ConflictException;
import com.jchediack.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.jchediack.usuario.infrastructure.repository.UsuarioRepository;
import com.jchediack.usuario.infrastructure.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          UsuarioConverter usuarioConverter,
                          PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioConverter = usuarioConverter;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

    public void emailExiste(String email) {
        if (verificaEmailExistente(email)) {
            throw new ConflictException("E-mail já cadastrado: " + email);
        }
    }

    public boolean verificaEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscaUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("E-mail não encontrado: " + email));
    }

    public void deletaUsuarioPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }

    public UsuarioDTO atualizaDadosUsuario(String token, UsuarioDTO dto) {
        // Busca o email do usuário dentro do Token
        String email = jwtUtil.extrairEmailToken(token.substring(7)); // Retira a palavra Bearer

        // Criptografia da senha
        dto.setSenha(dto.getSenha() != null ? passwordEncoder.encode(dto.getSenha()) : null );

        // Buscou os dados do usuario dentro do banco de dados
        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email não localizado"));

        // Mescla dados que recebeu na requisição DTO com os dados do banco de dados
        Usuario usuario = usuarioConverter.updateUsuario(dto, usuarioEntity);

        // Salva os dados do usuário convertido e depois pegou o retorno e converteu para UsuarioDTO
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

}