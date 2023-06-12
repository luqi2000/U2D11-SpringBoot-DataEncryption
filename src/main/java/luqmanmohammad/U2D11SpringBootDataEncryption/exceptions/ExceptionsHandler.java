package luqmanmohammad.U2D11SpringBootDataEncryption.exceptions;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


//you will see classes wich one i want to customize
@RestControllerAdvice
public class ExceptionsHandler {

	
	//method that return response and for return response we need ResponseEntity
	//this method will return a messege when you try to register an user for example without name 
	//and if you go to UserRegistrationPayload you CAN modify the response messege 
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorsPayloadWithErrorsList> handleValidationErrors(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(error -> error.getDefaultMessage())
				.collect(Collectors.toList());

		ErrorsPayloadWithErrorsList payload = new ErrorsPayloadWithErrorsList("Ci sono stati errori nel body",
				new Date(), 400, errors);

		return new ResponseEntity<ErrorsPayloadWithErrorsList>(payload, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorsPayload> handleBadRequest(BadRequestException e) {
		ErrorsPayload payload = new ErrorsPayload(e.getMessage(), new Date(), 400);
		return new ResponseEntity<ErrorsPayload>(payload, HttpStatus.BAD_REQUEST);
	}
	
	//with e.getMessege it will return the messege which one i will send from parameter 
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorsPayload> handleNotFound(NotFoundException e) {
		ErrorsPayload payload = new ErrorsPayload(e.getMessage(), new Date(), 404);
		return new ResponseEntity<ErrorsPayload>(payload, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ErrorsPayload> handleUnauthorized(UnauthorizedException e) {

		ErrorsPayload payload = new ErrorsPayload(e.getMessage(), new Date(), 401);

		return new ResponseEntity<ErrorsPayload>(payload, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorsPayload> handleGeneric(Exception e) {
		System.out.println(e);
		ErrorsPayload payload = new ErrorsPayload("Errore Generico", new Date(), 500);
		return new ResponseEntity<ErrorsPayload>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}