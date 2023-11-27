package co.edu.unbosque;

import co.edu.unbosque.Controller.ClienteController;
import co.edu.unbosque.Model.Cliente;
import co.edu.unbosque.Service.ClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

class ClienteControllerTests {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @Test
    void obtenerClientePorId_DeberiaRetornarOkCuandoClienteExiste() {
        // Configurar el comportamiento del servicio simulado
        Cliente clienteMock = new Cliente();
        Mockito.when(clienteService.obtenerClientePorId(anyInt())).thenReturn(clienteMock);

        // Ejecutar el método del controlador que quieres probar
        ResponseEntity<Cliente> response = clienteController.obtenerClientePorId(1);

        // Verificar que se llame al método del servicio
        verify(clienteService).obtenerClientePorId(anyInt());

        // Verificar el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteMock, response.getBody());
    }

    @Test
    void obtenerClientePorId_DeberiaRetornarPreconditionFailedCuandoClienteNoExiste() {
        // Configurar el comportamiento del servicio simulado
        Mockito.when(clienteService.obtenerClientePorId(anyInt())).thenReturn(null);

        // Ejecutar el método del controlador que quieres probar
        ResponseEntity<Cliente> response = clienteController.obtenerClientePorId(1);

        // Verificar que se llame al método del servicio
        verify(clienteService).obtenerClientePorId(anyInt());

        // Verificar el resultado
        assertEquals(HttpStatus.PRECONDITION_FAILED, response.getStatusCode());
    }

    // Puedes seguir escribiendo pruebas similares para los demás métodos del
    // controlador
}
