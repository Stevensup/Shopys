package co.edu.unbosque.Service;

import co.edu.unbosque.Model.Cliente;
import co.edu.unbosque.Repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    private final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * @param id
     * @return Cliente
     */

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

    public Cliente autenticarCliente(String email, String userPassword) {
        // Buscar un cliente por su correo electrónico (email)
        Cliente cliente = clienteRepository.findByEmail(email);

        if (cliente != null) {
            // Verificar si la contraseña proporcionada coincide con la contraseña
            // almacenada
            if (cliente.getUserPassword() != null && cliente.getUserPassword().equals(userPassword)) {
                return cliente; // Autenticación exitosa
            }
        }

        return null; // Autenticación fallida
    }

    public void bloquearCliente(String email) {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente != null) {
            cliente.setCtaBloqueada(true);
            clienteRepository.save(cliente);
        }
    }

}
