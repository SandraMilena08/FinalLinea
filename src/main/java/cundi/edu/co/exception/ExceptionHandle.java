package cundi.edu.co.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ExceptionHandle extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ModelNotFoundException.class)
	public final ResponseEntity<ExceptionWrapper>manejadorModelNotFoundException(ModelNotFoundException e, 
			WebRequest request){
		System.out.println("Entro 1");
		e.printStackTrace();
		ExceptionWrapper ew= new ExceptionWrapper(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), 
				e.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(ew, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ArgumentRequiredException.class)
	public final ResponseEntity<ExceptionWrapper>manejadorConflicException(ArgumentRequiredException e, 
			WebRequest request){
		e.printStackTrace();
		ExceptionWrapper ew= new ExceptionWrapper(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.toString(), 
				e.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(ew, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(ConflicException.class)
	public final ResponseEntity<ExceptionWrapper>manejadorConflicException(ConflicException e, 
			WebRequest request){
		e.printStackTrace();
		ExceptionWrapper ew= new ExceptionWrapper(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.toString(), 
				e.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(ew, HttpStatus.CONFLICT);
	}

	//Se divide por 0 
	@ExceptionHandler(ArithmeticException.class)
	public final ResponseEntity<ExceptionWrapper> manejadorModelArithmeticException(ArithmeticException e,
			WebRequest request){
		e.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
					"Ha ocurrido un error aritmetico", request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(ew, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
	//Enviar de tipo Text siendo JSON
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), HttpStatus.UNSUPPORTED_MEDIA_TYPE.toString(), 
				ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(ew, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
				ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(ew, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//Metodo incorrecto
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.METHOD_NOT_ALLOWED.value(), HttpStatus.METHOD_NOT_ALLOWED.toString(), 
				"Ha enviado un metodo incorrecto", request.getDescription(false));
		return new ResponseEntity<Object>(ew, HttpStatus.METHOD_NOT_ALLOWED);
	}

	//Mal escrito el JSON
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(), 
				"Error en la estructura del JSON", request.getDescription(false));
		return new ResponseEntity<Object>(ew, HttpStatus.BAD_REQUEST);	
	}

	//Argumentos no validos --  Que sea legible el mensaje
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String mensaje = "";
		System.out.println(ex.getErrorCount());
		
		for(int i = 0 ; i < ex.getErrorCount(); i++) {
			mensaje = mensaje + " " + ex.getFieldErrors().get(i).getField() + " : " + ex.getFieldErrors().get(i).getDefaultMessage();
		}
	
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(), 
				mensaje, request.getDescription(false));
		return new ResponseEntity<Object>(ew, HttpStatus.BAD_REQUEST);
	}

	//Por averiguar
	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
				ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(ew, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//Escritura mal en URL
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		System.out.println("Entro 4");
		ex.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), 
				ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(ew, HttpStatus.NOT_FOUND);
	}		
	
	//Cualquier otra exception
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionWrapper> manejadorModelException(Exception e,
			WebRequest request){
		e.printStackTrace();
		ExceptionWrapper ew = new ExceptionWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
					"Ha ocurrido un error", request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(ew, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
