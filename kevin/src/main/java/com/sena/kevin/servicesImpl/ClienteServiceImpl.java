package com.sena.kevin.servicesImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sena.kevin.dtos.ClienteDto;
import com.sena.kevin.mappers.ClienteMapper;
import com.sena.kevin.models.usuarios.Cliente;
import com.sena.kevin.repositoryes.ClienteRepository;
import com.sena.kevin.services.ClienteServices;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteServices {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    // Guardar
    @Override
    public ClienteDto guardar(ClienteDto clienteDto) {
        Cliente cliente = clienteMapper.toCliente(clienteDto);
        Cliente clienteguardado = clienteRepository.save(cliente);

        return clienteMapper.tDto(clienteguardado);
    }

    // Guardar varios clientes (Lote)
    @Override
    public List<ClienteDto> guardarTodos(List<ClienteDto> clienteDtos) {
        List<Cliente> clientes = clienteDtos.stream()
                .map(clienteMapper::toCliente)
                .toList();

        List<Cliente> clientesGuardados = clienteRepository.saveAll(clientes);

        return clientesGuardados.stream()
                .map(clienteMapper::tDto)
                .toList();
    }

    // Listar todos los clientes
    @Override
    public List<ClienteDto> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::tDto)
                .toList();
    }

    // Consultar cliente por ID
    @Override
    public ClienteDto buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() ->
                    new EntityNotFoundException(
                        "Cliente no encontrado con ID: " + id));

        return clienteMapper.tDto(cliente);
    }

    // Actualizar cliente
    @Override
    public ClienteDto actualizar(Long id, ClienteDto clienteDto) {
        Cliente clienteEncontrado = clienteRepository.findById(id)
                .orElseThrow(() ->
                    new EntityNotFoundException(
                        "No se encuentra cliente con ID: " + id));

        clienteEncontrado.setNom(clienteDto.getNomb());
        clienteEncontrado.setApe(clienteDto.getApell());
        clienteEncontrado.setDir(clienteDto.getDirec());
        clienteEncontrado.setTel(clienteDto.getTele());
        
        // Atributos específicos del cliente
        clienteEncontrado.setTipoCliente(clienteDto.getTipcli());
        clienteEncontrado.setPuntos(clienteDto.getPts());

        Cliente clienteActualizado = clienteRepository.save(clienteEncontrado);

        return clienteMapper.tDto(clienteActualizado);
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