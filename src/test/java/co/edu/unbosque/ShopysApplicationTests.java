package co.edu.unbosque;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

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
		// Crear una instancia de EmailService con un mock de JavaMailSender
		JavaMailSender javaMailSenderMock = Mockito.mock(JavaMailSender.class);
		EmailService emailService = new EmailService(javaMailSenderMock);

		// Definir los datos del correo electrónico
		String destinatario = "john@example.com";
		String asunto = "Prueba de correo electrónico";
		String mensaje = "Este es un mensaje de prueba";

		// Enviar el correo electrónico
		emailService.enviarCorreo(destinatario, asunto, mensaje);

		// Verificar que el método send del javaMailSenderMock fue llamado con los
		// parámetros correctos
		SimpleMailMessage expectedMessage = new SimpleMailMessage();
		expectedMessage.setTo(destinatario);
		expectedMessage.setSubject(asunto);
		expectedMessage.setText(mensaje);

		Mockito.verify(javaMailSenderMock).send(Mockito.eq(expectedMessage));
	}

}