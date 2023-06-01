package co.edu.ucatolica.hisclinic.application.usecases.hisclinic;


public class UserException extends RuntimeException {
    public UserException(){
        super("El usuario ya esta registrado");
    }
    public UserException(String message){
        super(message);
    }

}