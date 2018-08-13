/*
    Clase Control
    Esta clase es la encargada de gestionar y controlar el flujo del programa
*/
package minesweeper.control;

import java.util.ArrayList;
import minesweeper.logic.Game;

/**
 *
 * @author Carlos Andrés Elguedo Padilla
 * @version 1.0
 */
public class Control {
    
    //Atributos de la clase
    /*Este objeto es sera el encargado de obtener los datos ingresados por el usuario*/
    private Lectura reader_data;
    
    /*Este objeto estara encargado de realizar las validaciones a los datos ingresados por el usuario*/
    private Validaciones validator;
    
    //Este pnjeto es el encargado de los procesos logicos del juegos
    private Game game;
    
    /*
        Contructor de la clase
        Dentro del contructor, instanciamos los objetos necesarios para los procesos de la clase
    */
    public Control(){
        reader_data = new Lectura();
        validator = new Validaciones();
    }
    
    
    
    /**
     * Este metodo da inicio al programa, es el encargado de controlar el flujo desde el inicio al fin del programa, 
     */
    public void startingProgram() {
        //Este arrayList de enteros, indicara la entraad inicial del usuario
        //La entrada inicial del usuario debe estar en formato 'width' 'heigth' 'mines'
        //Por ejemplo: 8 15 10
        ArrayList<Integer> INPUT_INITIAL;
        
        //Este arrayList de enteros, indicará los datos de coordenadas y la accion que quiere realizar el usuario en un turno
        ArrayList<Integer> INPUT_ROUND;
        
        //Variable que representa el texto inicial ingresado, el cual sera validado
        String text_initial = "";
        
        //Variable que representa el texto ingresado en un turno, el cual sera validado
        String inputRound = "";
        
        
        //Capturamos la entrada del usuario mientras que la entrada no este en formato correcto
        //La entrada inicial tiene que estar en formato correcto para poder continue_game
        do{
            //Capturamos el texto ingresado por el usuario
            text_initial = reader_data.readInput("Initial input:");
            
            //Validamos la entrada inicial
            //Quitamos los espacios en blancos al fnal e inicio de la cadena, antes de comprobarla
            INPUT_INITIAL = validator.validateInitialEntry(text_initial.trim());
            
            //Comprobamos la condicion del ciclo para seguir o repetir
        }while(INPUT_INITIAL.size() != 3);
        
        //comandos.clearConsole();
        
        //Como ya tenemos los datos iniciales correctos, ponemos crear el juego
        game = new Game(INPUT_INITIAL);
        
        //Para controlar el flujo del programa utilizaremos un ciclo while para repetir el siguiente proceso
        //El ciclo termina hasta que el juego haya terminado, haya perdido o ganado el usuario
        while(game.isGAME_END() == false){
            
            //Utilizaremos este ciclo do while para recibir y validar las entradas del usuarios
            //Este ciclo se repite hasta que la entrada este en formato correcto
            do{
                //Se reciben los datos ingresados por el usuario en un turno
                inputRound = reader_data.readInput("Round input:");
                
                //Se validan los datos recibidos por el usuario
                INPUT_ROUND = validator.RoundInputIsCorrect(inputRound);
                
                //Comprobamos la condicion del ciclo para seguir o repetir
            }while(INPUT_ROUND.size() != 3);
            
            //Continuamos con la entradas ingresadas por el usuario, enviandola al objeto juego, para que en su logica las procese
            game.continue_game(INPUT_ROUND.get(0), INPUT_ROUND.get(1), INPUT_ROUND.get(2));
            
        }//Fin del while controlador del flujo del programa
        
        //Cuando haya salido del ciclo anterior, el programa ha finalizado, por lo que hay que averiguar si el usuario gano o perdio
        //Si la propiedad VICTORY del objeto game, es true, indica que el usuario ha ganado el juego
        if(game.isVICTORY()){
            System.out.println("Felicidades, haz ganado");
        }else{
            //En caso contrario, indica que el juego ha terminado con derrota
            System.out.println("Ups, intentalo de nuevo");
        }
        
        
        
    }//Fin del metodo iniciar programa

    
    /**
     * 
     * GETTERS Y SETTERS
     */
    
    public Lectura getReader_data() {
        return reader_data;
    }

    public void setReader_data(Lectura reader_data) {
        this.reader_data = reader_data;
    }

    public Validaciones getValidator() {
        return validator;
    }

    public void setValidator(Validaciones validator) {
        this.validator = validator;
    }

    
    
    
}//Fin de la clase
