package co.edu.unbosque.Service;

// FormaPagoService.java

import co.edu.unbosque.Model.FormaPago;
import co.edu.unbosque.Repository.FormaPagoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FormaPagoService {

    private final FormaPagoRepository formaPagoRepository;

    @Autowired
    public FormaPagoService(FormaPagoRepository formaPagoRepository) {
        this.formaPagoRepository = formaPagoRepository;
    }

    public List<FormaPago> listarFormasPago() {
        return formaPagoRepository.findAll();
    }

    public Optional<FormaPago> obtenerFormaPagoPorId(int id) {
        return formaPagoRepository.findById(id);
    }

    public FormaPago crearFormaPago(FormaPago formaPago) {
        return formaPagoRepository.save(formaPago);
    }

    public FormaPago actualizarFormaPago(int id, FormaPago formaPago) {
        if (formaPagoRepository.existsById(id)) {
            formaPago.setId(id);
            return formaPagoRepository.save(formaPago);
        } else {
            return null;
        }
    }

    public boolean eliminarFormaPago(int id) {
        if (formaPagoRepository.existsById(id)) {
            formaPagoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
