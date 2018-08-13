/*
 * Clase Minesweeper
 * Esta es la clase principal del programa
 */
package minesweeper;

import minesweeper.control.Control;

/**
 *
 * @author Carlos Andrés Elguedo
 * * @version 1.0
 */
public class Minesweeper {

    /**
     * Metodo main para dar inicio 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Se crea e inicializa un objeto controlador, encargado de controlar el flujo del programa
        Control controller = new Control();
        
        //Desde el objeto anterior se dara inicio al programa
        controller.startingProgram();
     
        
    }
    
}
