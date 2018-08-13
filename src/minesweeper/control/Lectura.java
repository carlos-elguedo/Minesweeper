/**
 * Clase Lectura
 * Esta clase sera la encargada del ingreso de datos por parte del usuario
 */
package minesweeper.control;

import java.util.Scanner;


/**
 *
 * @author Carlos Andrés Elguedo Padilla
 * @version 1.0
 */
public class Lectura {
    //Atributos de la clase
    /*Esta variable representara el texto ingresado por el usuario*/
    private String text_readed;
    
    
    Scanner lectura;
    /**
     * Constructor de la clase
     */
    public Lectura(){
        lectura = new Scanner(System.in);
    }
    
     
    /**
     * Este metodo es el encargado de recibir la entrada del usuario por consola
     * @param text_show
     * @return El texto leido
     */
    public String readInput(String text_show){
        //Mostramos el mensaje que vera el usuario
        System.out.print(text_show);
        
        //Capturamos la entrada
        this.text_readed = lectura.nextLine();
        
        //Retornomos el texto leido
        return this.text_readed;
    }
    
    
    
}//Fin de la clase
