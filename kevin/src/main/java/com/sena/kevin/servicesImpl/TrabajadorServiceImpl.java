package com.sena.kevin.servicesImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sena.kevin.dtos.TrabajadorDto;
import com.sena.kevin.mappers.TrabajadorMapper;
import com.sena.kevin.models.usuarios.Trabajador;
import com.sena.kevin.repositoryes.TrabajadorRepository;
import com.sena.kevin.services.TrabajadorServices;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrabajadorServiceImpl implements TrabajadorServices {

    private final TrabajadorRepository trabajadorRepository;
    private final TrabajadorMapper trabajadorMapper;

    // Guardar trabajador
    @Override
    public TrabajadorDto guardar(TrabajadorDto trabajadorDto) {
        Trabajador trabajador = trabajadorMapper.toEntidad(trabajadorDto);
        Trabajador trabajadorguardado = trabajadorRepository.save(trabajador);

        return trabajadorMapper.toDto(trabajadorguardado);
    }

    // Guardar varios trabajadores (Lote)
    @Override
    public List<TrabajadorDto> guardarTodos(List<TrabajadorDto> trabajadorDtos) {
        List<Trabajador> trabajadores = trabajadorDtos.stream()
                .map(trabajadorMapper::toEntidad)
                .toList();

        List<Trabajador> trabajadoresGuardados = trabajadorRepository.saveAll(trabajadores);

        return trabajadoresGuardados.stream()
                .map(trabajadorMapper::toDto)
                .toList();
    }

    // Listar todos los trabajadores
    @Override
    public List<TrabajadorDto> listarTodos() {
        return trabajadorRepository.findAll()
                .stream()
                .map(trabajadorMapper::toDto)
                .toList();
    }

    // Consultar trabajador por ID
    @Override
    public TrabajadorDto buscarPorId(Long id) {
        Trabajador trabajador = trabajadorRepository.findById(id)
                .orElseThrow(() ->
                    new EntityNotFoundException(
                        "Trabajador no encontrado con ID: " + id));

        return trabajadorMapper.toDto(trabajador);
    }

    // Actualizar trabajador
    @Override
    public TrabajadorDto actualizar(Long id, TrabajadorDto trabajadorDto) {
        Trabajador trabajadorEncontrado = trabajadorRepository.findById(id)
                .orElseThrow(() ->
                    new EntityNotFoundException(
                        "No se encuentra trabajador con ID: " + id));

        trabajadorEncontrado.setNom(trabajadorDto.getNomb());
        trabajadorEncontrado.setApe(trabajadorDto.getApell());
        trabajadorEncontrado.setDir(trabajadorDto.getDirec());
        trabajadorEncontrado.setTel(trabajadorDto.getTele());

        Trabajador trabajadorActualizado = trabajadorRepository.save(trabajadorEncontrado);

        return trabajadorMapper.toDto(trabajadorActualizado);
    }

    // Eliminar trabajador
    @Override
    public void eliminar(Long id) {
        Trabajador trabajadorEncontrado = trabajadorRepository.findById(id)
                .orElseThrow(() ->
                    new EntityNotFoundException(
                        "No se encuentra trabajador con ID: " + id));

        trabajadorRepository.delete(trabajadorEncontrado);
    }
}