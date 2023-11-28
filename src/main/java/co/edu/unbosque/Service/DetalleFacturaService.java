package co.edu.unbosque.Service;

import co.edu.unbosque.Model.DetalleFactura;
import co.edu.unbosque.Repository.DetalleFacturaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleFacturaService {

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    /**
     * @return
     */
    public List<DetalleFactura> obtenerTodosLosDetallesFactura() {
        return detalleFacturaRepository.findAll();
    }

    /**
     * @param id
     * @return Optional<DetalleFactura>
     */
    public Optional<DetalleFactura> obtenerDetalleFacturaPorId(int id) {
        return detalleFacturaRepository.findById(id);
    }

    public DetalleFactura guardarDetalleFactura(DetalleFactura detalleFactura) {
        return detalleFacturaRepository.save(detalleFactura);
    }

    public boolean eliminarDetalleFactura(int id) {
        if (detalleFacturaRepository.existsById(id)) {
            detalleFacturaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
