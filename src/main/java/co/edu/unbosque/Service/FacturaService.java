package co.edu.unbosque.Service;

import co.edu.unbosque.Model.Factura;
import co.edu.unbosque.Repository.FacturaRepository;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaService {

    private final Logger logger = LoggerFactory.getLogger(FacturaService.class);

    @Autowired
    private FacturaRepository facturaRepository;

    /**
     * @return
     */
    public List<Factura> obtenerTodasLasFacturas() {
        return facturaRepository.findAll();
    }

    /**
     * @param id
     * @return Optional<Factura>
     */
    public Optional<Factura> obtenerFacturaPorId(int id) {
        return facturaRepository.findById(id);
    }

    public Factura crearFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    public boolean eliminarFactura(int id) {
        if (facturaRepository.existsById(id)) {
            facturaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
