package co.edu.ucatolica.hisclinic.application.usecases.hisclinic;

public class FechaDeNacimientoVaciaException extends RuntimeException {
    public FechaDeNacimientoVaciaException(){
        super("Debe llenar la fecha de nacimiento");
    }
    public FechaDeNacimientoVaciaException(String message){
        super(message);
    }

}