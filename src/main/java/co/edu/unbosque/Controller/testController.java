package co.edu.unbosque.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/test")
@Transactional
@CrossOrigin(origins = {"http://localhost:8081","http://localhost:8080", "*"})
public class testController {

	public testController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping(path="/saludar")
	public ResponseEntity<String> saludar(){
		return new ResponseEntity<String>("Buenas", HttpStatus.ACCEPTED);		
	}
	            
	@GetMapping(path="/sumar")
	public ResponseEntity<String> sumar(@RequestParam Double num1,@RequestParam Double num2){
		if (num1 < 0 || num2 < 0) {
			return new ResponseEntity<String>("No acepto numeros negativos", HttpStatus.PRECONDITION_FAILED);
		}else {
			return new ResponseEntity<String>("la suma es: " + (num1 + num2), HttpStatus.CREATED );
		}
	}	
	@GetMapping(path = "/restar")
    public ResponseEntity<String> restar(@RequestParam Double num1, @RequestParam Double num2) {
        return new ResponseEntity<>("La resta es: " + (num1 - num2), HttpStatus.CREATED);
    }

    @GetMapping(path = "/multiplicar")
    public ResponseEntity<String> multiplicar(@RequestParam Double num1, @RequestParam Double num2) {
        return new ResponseEntity<>("La multiplicación es: " + (num1 * num2), HttpStatus.CREATED);
    }

    @GetMapping(path = "/dividir")
    public ResponseEntity<String> dividir(@RequestParam Double num1, @RequestParam Double num2) {
        if (num2 == 0) {
            return new ResponseEntity<>("No se puede dividir por cero", HttpStatus.PRECONDITION_FAILED);
        } else {
            return new ResponseEntity<>("La división es: " + (num1 / num2), HttpStatus.CREATED);
        }
    }

	@GetMapping(path = "/porcentaje")
    public ResponseEntity<String> calcularPorcentaje(@RequestParam Double numero, @RequestParam Double porcentaje) {
        if (porcentaje < 0 || porcentaje > 100) {
            return new ResponseEntity<>("El porcentaje debe estar entre 0 y 100", HttpStatus.PRECONDITION_FAILED);
        } else {
            double resultado = (numero * porcentaje) / 100;
            return new ResponseEntity<>("El " + porcentaje + "% de " + numero + " es: " + resultado, HttpStatus.CREATED);
        }
    }

    @GetMapping(path = "/logaritmo")
    public ResponseEntity<String> calcularLogaritmo(@RequestParam Double numero, @RequestParam Double base) {
        if (numero <= 0 || base <= 1) {
            return new ResponseEntity<>("El número debe ser positivo y la base del logaritmo debe ser mayor que 1", HttpStatus.PRECONDITION_FAILED);
        } else {
            double resultado = Math.log(numero) / Math.log(base);
            return new ResponseEntity<>("El logaritmo base " + base + " de " + numero + " es: " + resultado, HttpStatus.CREATED);
        }
    }
}
	



