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
}
