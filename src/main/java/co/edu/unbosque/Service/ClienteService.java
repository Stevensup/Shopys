package co.edu.unbosque.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.edu.unbosque.Model.Cliente;
import co.edu.unbosque.Repository.ClienteRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ClienteService {

	
    private final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    private ClienteRepository clienteRepository;


    public Cliente obtenerClientePorId(int id) {
        return clienteRepository.findById(id).orElse(null);
    }
    
    @Transactional
    public Cliente crearCliente(Cliente cliente) {
        logger.info("Llegó al método crearCliente. Cliente: {}", cliente);
        Cliente nuevoCliente = clienteRepository.save(cliente);
        logger.info("Cliente guardado en la base de datos. Nuevo cliente: {}", nuevoCliente);
        return nuevoCliente;
    }
    public Cliente actualizarCliente(int id, Cliente cliente) {
        if (clienteRepository.existsById(id)) {
            cliente.setId(id);
            return clienteRepository.save(cliente);
        } else {
            return null;
        }
    }

    public boolean eliminarCliente(int id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    
    
}

