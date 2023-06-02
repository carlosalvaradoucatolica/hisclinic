package co.edu.ucatolica.hisclinic.application.usecases.hisclinic;

public class MenorDeEdadException extends RuntimeException {
    public MenorDeEdadException(){
        super("El usuario debe ser mayor de edad");
    }
    public MenorDeEdadException(String message){
        super(message);
    }

}