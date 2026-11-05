package com.sena.kevin.servicesImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sena.kevin.dtos.UsuarioDto;
import com.sena.kevin.mappers.UsuarioMapper;
import com.sena.kevin.models.usuarios.Usuario;
import com.sena.kevin.repositoryes.UsuarioRepository;
import com.sena.kevin.services.UsuarioServices;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UsuarioServicesImpl implements UsuarioServices {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    // Guardar usuario
    @Override
    public UsuarioDto guardar(UsuarioDto usuarioDto) {

        Usuario usuario = usuarioMapper.ConvierteaEntidad(usuarioDto);
        Usuario usuarioguardado = usuarioRepository.save(usuario);

        return usuarioMapper.ConvierteaDto(usuarioguardado);
    }

    // Listar todos los usuarios
    @Override
    public List<UsuarioDto> listarTodos() {

        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::ConvierteaDto)
                .toList();
    }

    // Consultar usuario por ID
    @Override
    public UsuarioDto buscarPorId(long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                    new EntityNotFoundException(
                        "Usuario no encontrado: " + id));

        return usuarioMapper.ConvierteaDto(usuario);
    }

    // Actualizar usuario
    @Override
    public UsuarioDto actualizar(Long id, UsuarioDto usuarioDto) {

        Usuario usuarioEncontrado = usuarioRepository.findById(id)
                .orElseThrow(() ->
                    new EntityNotFoundException(
                        "No se encuentra usuario con ID: " + id));

        usuarioEncontrado.setNom(usuarioDto.getNomb());
        usuarioEncontrado.setApe(usuarioDto.getApell());
        usuarioEncontrado.setDir(usuarioDto.getDirec());
        usuarioEncontrado.setTel(usuarioDto.getTele());

        Usuario usuarioActualizado = usuarioRepository.save(usuarioEncontrado);

        return usuarioMapper.ConvierteaDto(usuarioActualizado);
    }

    // Eliminar usuario
    @Override
    public void eliminar(Long id) {

        Usuario usuarioEncontrado = usuarioRepository.findById(id)
                .orElseThrow(() ->
                    new EntityNotFoundException(
                        "No se encuentra usuario con ID: "+id ));

        usuarioRepository.delete(usuarioEncontrado);
    }

    @Override
    public List<UsuarioDto> guardarTodos(List<UsuarioDto> usuarioDtos) {
        List<Usuario> usuarios = usuarioDtos.stream()
                .map(usuarioMapper::ConvierteaEntidad)
                .toList();

        List<Usuario> usuariosGuardados = usuarioRepository.saveAll(usuarios);

        return usuariosGuardados.stream()
                .map(usuarioMapper::ConvierteaDto)
                .toList();
    }
}