package co.edu.unbosque.Service;

import co.edu.unbosque.Model.Domicilio;
import co.edu.unbosque.Repository.DomicilioRepository;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomicilioService {

    private final Logger logger = LoggerFactory.getLogger(DomicilioService.class);

    @Autowired
    private DomicilioRepository domicilioRepository;

    /**
     * @return List<Domicilio>
     */

    public List<Domicilio> obtenerTodosLosDomicilios() {
        return domicilioRepository.findAll();
    }

    public Optional<Domicilio> obtenerDomicilioPorId(int id) {
        return domicilioRepository.findById(id);
    }

    public Domicilio guardarDomicilio(Domicilio domicilio) {
        return domicilioRepository.save(domicilio);
    }

    public boolean eliminarDomicilio(int id) {
        if (domicilioRepository.existsById(id)) {
            domicilioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
