package com.sena.kevin.servicesImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sena.kevin.dtos.ClienteDto;
import com.sena.kevin.dtos.ClienteDtoResponse;
import com.sena.kevin.mappers.ClienteMapper;
import com.sena.kevin.models.usuarios.Cliente;
import com.sena.kevin.models.usuarios.TiposCliente;
import com.sena.kevin.repositoryes.ClienteRepository;
import com.sena.kevin.repositoryes.TipoClienteRepository;
import com.sena.kevin.services.ClienteServices;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteServices {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final TipoClienteRepository ticlirepo;

    @Override 
    public ClienteDtoResponse guardar (ClienteDto clienteDto) {

        //buscar por id de tipocliente e base de datos
        TiposCliente tipo = ticlirepo.findById(clienteDto.getTipcli())
                .orElseThrow(() -> new EntityNotFoundException("Tipo cliente no encontrado"));

        //2. Convierte Dto en Entidad
        Cliente cliente = clienteMapper.toCliente(clienteDto);

        //3. El Id del cliente deber ser nul porque es autoincremetal
        cliente.setId(null);

        //4. asigna tipocliente"id"
        cliente.setTipoCliente(tipo);
        //5. guarda cliente en base de datos

        Cliente clienteGuardado = clienteRepository.save(cliente);

        return clienteMapper.toDto(clienteGuardado);
    }

    // Listar todos los clientes
    @Override
    public List<ClienteDtoResponse> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toDto)
                .toList();
    }

    // Consultar cliente por ID
    @Override
    public ClienteDtoResponse buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() ->
                    new EntityNotFoundException("Cliente no encontrado con ID: " + id));

        return clienteMapper.toDto(cliente);
    }

    // Actualizar cliente
    @Override
    public ClienteDtoResponse actualizar(Long id, ClienteDto clienteDto) {
        Cliente clienteEncontrado = clienteRepository.findById(id)
                .orElseThrow(() ->
                    new EntityNotFoundException(
                        "No se encuentra cliente con ID: " + id));

        clienteEncontrado.setNom(clienteDto.getNomb());
        clienteEncontrado.setApe(clienteDto.getApell());
        clienteEncontrado.setDir(clienteDto.getDirec());
        clienteEncontrado.setTel(clienteDto.getTele());
        
        // Atributos específicos del cliente
        clienteEncontrado.setTipoCliente(ticlirepo.findById(clienteDto.getTipcli())
                .orElseThrow(()->new EntityNotFoundException(
                    "no se encuentra tipo de cliente" +clienteDto.getTipcli()
                )));
        clienteEncontrado.setPuntos(clienteDto.getPts());

        Cliente clienteActualizado = clienteRepository.save(clienteEncontrado);

        return clienteMapper.toDto(clienteActualizado);
    }

    // Eliminar cliente
    @Override
    public void eliminar(Long id) {
        Cliente clienteEncontrado = clienteRepository.findById(id)
                .orElseThrow(() ->
                    new EntityNotFoundException(
                        "No se encuentra cliente con ID: " + id));

        clienteRepository.delete(clienteEncontrado);
    }
}