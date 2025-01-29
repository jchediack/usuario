package com.jchediack.usuario.infrastructure.business.converter;

import com.jchediack.usuario.infrastructure.business.dto.EnderecoDTO;
import com.jchediack.usuario.infrastructure.business.dto.TelefoneDTO;
import com.jchediack.usuario.infrastructure.business.dto.UsuarioDTO;
import com.jchediack.usuario.infrastructure.entity.Endereco;
import com.jchediack.usuario.infrastructure.entity.Telefone;
import com.jchediack.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {

    // Transforma de Entities para DTOs

    public Usuario paraUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setEnderecos(paraListaEndereco(usuarioDTO.getEnderecos()));
        usuario.setTelefones(paraListaTelefone((usuarioDTO.getTelefones())));
        return usuario;
    }

    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDTOS){
        return enderecoDTOS.stream().map(this::paraEndereco).toList();
    }

    public Endereco paraEndereco(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setRua(enderecoDTO.getRua());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setEstado(enderecoDTO.getEstado());
        endereco.setCep(enderecoDTO.getCep());
        return endereco;
    }

    public List<Telefone> paraListaTelefone(List<TelefoneDTO> telefoneDTOS){
        return telefoneDTOS.stream().map(this::paraTelefone).toList();
    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO){
        Telefone telefone = new Telefone();
        telefone.setTelefone(telefoneDTO.getTelefone());
        telefone.setDdd(telefoneDTO.getDdd());
        return telefone;
    }

    // Transforma de DTOs para Entities

    public UsuarioDTO paraUsuarioDTO(Usuario usuarioDTO) {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setEnderecos(paraListaEnderecoDTO(usuarioDTO.getEnderecos()));
        usuario.setTelefones(paraListaTelefoneDTO((usuarioDTO.getTelefones())));
        return usuario;
    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecoDTOS){
        return enderecoDTOS.stream().map(this::paraEnderecoDTO).toList();
    }

    public EnderecoDTO paraEnderecoDTO(Endereco enderecoDTO) {
        EnderecoDTO endereco = new EnderecoDTO();
        endereco.setRua(enderecoDTO.getRua());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setEstado(enderecoDTO.getEstado());
        endereco.setCep(enderecoDTO.getCep());
        return endereco;
    }

    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> telefoneDTOS){
        return telefoneDTOS.stream().map(this::paraTelefoneDTO).toList();
    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefoneDTO){
        TelefoneDTO telefone = new TelefoneDTO();
        telefone.setTelefone(telefoneDTO.getTelefone());
        telefone.setDdd(telefoneDTO.getDdd());
        return telefone;
    }

}
