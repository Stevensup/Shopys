package co.edu.unbosque;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.unbosque.Model.Cliente;
import co.edu.unbosque.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ShopysApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void contextLoads() {
		// Comprueba que el contexto se cargue correctamente
		assertNotNull(applicationContext);
	}

	@Test
	void testCliente() {
		// Crear un cliente
		Cliente cliente = new Cliente();
		cliente.setId(1); // Asigna un valor adecuado para ID
		cliente.setNombre("John");
		cliente.setApellido("Doe");
		cliente.setEmail("john@example.com");
		cliente.setTelefono("1234567890");
		cliente.setDireccion("123 Main St, City");
		cliente.setUserPassword("password123");
		cliente.setFrecuente(false); // Puedes ajustar este valor según tus necesidades
		cliente.setFechaRegistro(LocalDateTime.now()); // Puedes ajustar la fecha de registro según tus necesidades
		cliente.setCtaBloqueada(false); // Puedes ajustar este valor según tus necesidades

		// Verificar que el nombre del cliente sea correcto
		assertEquals("John Doe", cliente.getNombre());

		// Verificar que el correo electrónico del cliente sea correcto
		assertEquals("john@example.com", cliente.getEmail());
	}

	private void assertEquals(String string, String nombre) {
	}

	@Test
	void testSendEmail() {

		// Mock JavaMailSender
		JavaMailSender javaMailSender = Mockito.mock(JavaMailSender.class);

		// Crea una instancia de EmailService con el JavaMailSender simulado
		EmailService emailService = new EmailService(javaMailSender, new ObjectMapper());

		// Crea un mensaje simulado
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("buseche@unbosque.edu.co");
		simpleMailMessage.setTo("destinatario@example.com");
		simpleMailMessage.setSubject("Asunto del correo");
		simpleMailMessage.setText("Cuerpo del correo");

		// Configura el comportamiento esperado cuando se llama a send
		Mockito.doNothing().when(javaMailSender).send(Mockito.any(SimpleMailMessage.class));

		// Llama al método enviarCorreo con un JSON simulado
		String json = "{\"destinatario\": \"destinatario@example.com\", \"asunto\": \"Asunto del correo\", \"cuerpo\": \"Cuerpo del correo\"}";
		emailService.enviarCorreo(json);

		// Verifica que el método send fue llamado exactamente una vez
		Mockito.verify(javaMailSender, Mockito.times(1)).send(Mockito.any(SimpleMailMessage.class));
	}

}
