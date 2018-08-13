/*
 * clase Print
 * Esta clase es para mostrar en pantalla la matriz del buscaminas
 */
package minesweeper.print;

import minesweeper.logic.Cell;

/**
 *
 * @author Carlos Andrés Elguedo Padilla
 */
public class Print {

    //Atributos de la clse
    private int rows;
    private int columns;
    
    /**
     * Este constructor recube el tamaño del tablero
     * @param rows
     * @param columns 
     */
    public Print(int rows, int columns) {
        this.columns = columns;
        this.rows = rows;
    }
    
    
    /**
     * Metodo encargado de imprimir la matriz del juego
     * @param matrixElements elementos a imprimir
     */
    public void printBoard(Cell[][] matrixElements){
        
        //Mostramos lineas en blanco para limpiar la pantalla
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
        
        //Realizamos este ciclo repetitivo para mostrar todos los elementos de la matriz
        for(int i = 0; i < this.rows; i++){
            for(int j = 0;j < this.columns; j++){
                //Si la celda actual esta sin descubrir y no ha sido marcada
                if(matrixElements[i][j].isState() == false && matrixElements[i][j].isMarked() == false){
                    //La mostramos
                    System.out.print(". ");
                }
                
                //Si la celda actual ha sido descubierta
                if(matrixElements[i][j].isState() == true){
                    //Mostramos su contenido y un espacio separador
                    System.out.print(matrixElements[i][j].getContent() + " ");
                }
                
                //Si la celda actual ha sido marcada
                if(matrixElements[i][j].isMarked()){
                    //Mostramos la bandera
                    System.out.print("P ");
                }
                
            }
            //Separar las filas
            System.out.println("");
        }//Fin de los recorridos
        //Linea separadora final
        System.out.println("");
    }
}
