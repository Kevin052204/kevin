package com.sena.kevin.servicesImpl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.sena.kevin.dtos.TrabajadorDto;
import com.sena.kevin.dtos.TrabajadorDtoResponse;
import com.sena.kevin.mappers.TrabajadorMapper;
import com.sena.kevin.models.usuarios.TiposTrabajador;
import com.sena.kevin.models.usuarios.Trabajador;
import com.sena.kevin.repositoryes.TrabajadorRepository;
import com.sena.kevin.repositoryes.TipostrabajadorRepository;
import com.sena.kevin.services.TrabajadorServices;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrabajadorServiceImpl implements TrabajadorServices {

    private final TrabajadorRepository trabajadorRepository;
    private final TrabajadorMapper trabajadorMapper;
    private final TipostrabajadorRepository titrabrepo;

// Guardar trabajador
    @Override
    public TrabajadorDtoResponse guardar(TrabajadorDto trabajadorDto) {

        // 1.Buscar id de tipo trabajador en base de datos
        TiposTrabajador tipo = titrabrepo.findById(trabajadorDto.getCargo())
        .orElseThrow(() -> new EntityNotFoundException("Tipo Trabajador no encontrado"));

        // 2. Convierte Dto a Entidad
        Trabajador trabajador = trabajadorMapper.toEntidad(trabajadorDto);

        // 3. El id del trabajador debe ser null porque es autoincrementable
        trabajador.setId(null);

        // 4. Asigna tipo trabajador
        trabajador.setCargo(tipo);

        // 5. Guarda trabajador en base de datos
        Trabajador trabajadorguardado = trabajadorRepository.save(trabajador);

        return trabajadorMapper.toDto(trabajadorguardado);
    }
// Listar todos los clientes
    @Override
    public List<TrabajadorDtoResponse> listarTodos() {
        return trabajadorRepository.findAll()
                .stream()
                .map(trabajadorMapper::toDto)
                .toList();
    }
// Consultar cliente por ID
    @Override
    public TrabajadorDtoResponse buscarPorId(Long id) {
        Trabajador trabajador = trabajadorRepository.findById(id)
                .orElseThrow(() ->
                    new EntityNotFoundException(
                        "Trabajador no encontrado : " + id));

        return trabajadorMapper.toDto(trabajador);
    }
    // Actualizar cliente
    @Override
    public TrabajadorDtoResponse actualizar(Long id, TrabajadorDto trabajadorDto) {
        Trabajador trabajadorEncontrado = trabajadorRepository.findById(id)
                .orElseThrow(() ->
                    new EntityNotFoundException(
                        "No se encuentra cliente con ID: " + id));

        trabajadorEncontrado.setNom(trabajadorDto.getNomb());
        trabajadorEncontrado.setApe(trabajadorDto.getApell());
        trabajadorEncontrado.setDir(trabajadorDto.getDirec());
        trabajadorEncontrado.setTel(trabajadorDto.getTele());

        //Atributos especificos del cliente
        trabajadorEncontrado.setCargo(titrabrepo.findById(trabajadorDto.getCargo())
            .orElseThrow(()->new EntityNotFoundException(
                "No se encuentra tipo de cliente" + trabajadorDto.getCargo()
            )));


        trabajadorEncontrado.setSueldo(trabajadorDto.getSueldo());

        Trabajador trabajadorActualizado = trabajadorRepository.save(trabajadorEncontrado);

        return trabajadorMapper.toDto(trabajadorActualizado);
    }
    // Eliminar usuario
    @Override
    public void eliminar(Long id) {

        Trabajador trabajadorEncontrado = trabajadorRepository.findById(id)
                .orElseThrow(() ->
                    new EntityNotFoundException(
                        "No se encuentra trabajador con ID: "+id ));

        trabajadorRepository.delete(trabajadorEncontrado);
    }
}