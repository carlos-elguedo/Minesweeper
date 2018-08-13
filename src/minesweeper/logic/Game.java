/*
    Clase Game
    Esta clase es la encargada la logica del juego
*/
package minesweeper.logic;

import java.util.ArrayList;
import minesweeper.print.Print;

/**
 * @author Carlos Elguedo Padilla
 * @version 1.0
 */
public class Game {
    
    //Atributos de la clase
    //Indica el ancho del tablero
    private final int width;
    
    //Indica la altura del tablero
    private final int height;
    
    //Indica la cantidad de minas del tablero
    private final int mines;
    
    //Indica la matriz de elementos de celdas del juego
    private Cell[][] matrixElements;
    
    //Este objeto es el encargado de configurar el juego
    private Setting setting;
    
    //Objeto encargado de imprimir en pantalla
    private Print print;
    
    //Variable que indica el estado de vitoria del juego
    private boolean VICTORY = false;
    
    //Variable que indica el estado de finalizacion del juego
    private boolean GAME_END = false;
    
    /**
     * Constructor de la clase
     * @param initialInput datos que inica las configuracioens del tablero
     */
    public Game(ArrayList<Integer> initialInput){
        //Obtenemos el ancho del tablero desde los datos recibidos
        this.width = initialInput.get(0);
        //Obtenemos la altura del tablero desde los datos recibidos
        this.height = initialInput.get(1);
        //Obtenemos la cantidad de minas del tablero desde los datos recibidos
        this.mines = initialInput.get(2);
        
        //Inicializamos el objeto de configuraciones con los datos recibidos
        setting = new Setting(width, height, mines);
        
        //Iniciamos el objeto impresor con los datos recibidos
        print = new Print(width, height);
        
        //Obtenemos la matriz de juego configurada y lista para empezar el juego
        this.matrixElements = setting.fillBoard();
        
        //Mostramos la matriz inical del juego
        print.printBoard(matrixElements); 
    }
    
    
    /**
     * Este metodo es para continuar con el proceso logico del juego
     * Dependiendo de los datos recibidos, cambia el estado del juego
     * @param row fila a descubrio o marcar
     * @param col columna a descubrio o marcar
     * @param action tipo de accion a realizar: DESCUBRIIR O MARCAR/DESMARCAR
     */
    public void continue_game(int row, int col, int action){
        //Utilizamos un switch para averiguar el tipo de accion a realizar por el usuario
        switch(action){
            //Caso 1 para descubir una celda
            case 1:
                //Si la celda seleccionada aun no ha sido seleccionada  (su estado es false)
                if(this.matrixElements[row][col].isState() == false){
                    //Cambiamos el estado de la celda a true, lo que indica que esta descubierta o seleccionada
                    this.matrixElements[row][col].setState(true);
                    
                    //Ahora, verificamos si la celda estaba marcada
                    if(this.matrixElements[row][col].isMarked() == true){
                        //Si la celda estaba marcada como bandera, le cambiamos ese estado (la desmarcamos)
                        this.matrixElements[row][col].setMarked(false);
                    }
                    
                    //Game over
                    //Si la celda descubierta contiene una mina, el juego a finalizado
                    if(this.matrixElements[row][col].getContent().equals("*")){
                        //Indicamos que el juego ha terminado
                        this.GAME_END = true;
                    }
                    
                    //Si la celda descubierta esta vacia (contenia 0 bombas a su alrededor)
                    //Procedmos a descubrir las adyacentes
                    if(this.matrixElements[row][col].getContent().equals("0")){
                        //Cabiamos el contenido a '-' para indicar que la celda esta vacia
                        this.matrixElements[row][col].setContent("-");
                        //Descubrimos las celdas adyacentes
                        discoverAdjacent(row, col);
                    }
                    
                }
                break;
                
            //Caso 1 para Marcar una celda
            case 2:
                //Si la celda a marcar aun no ha ha sido descubierta
                if(this.matrixElements[row][col].isState() == false){
                    //Si la celda a marcar ya estaba marcada, se procede a desnarcar
                    if(this.matrixElements[row][col].isMarked() == true){
                        //Se desmarca la celda
                        this.matrixElements[row][col].setMarked(false);
                    }else{
                        //Si la celda no estaba marcada, procedemos a marcarla
                        this.matrixElements[row][col].setMarked(true);
                    }
                    
                }
                break;
             
            //Para cuando se quiera continuar sin una accion correcta
            default:
                System.out.println("Action unrecognizable");
                break;
                
        }//Fin dek switch
        
        //Despues de procesar la accion, mostramos el tablero
        print.printBoard(this.matrixElements);
        //Llamamos a este metodo para conocer el estado del juego
        gameState();
        
    }
    
    
    
    /**
     * Este metodo Es para conocer el estado del juego
     * Dependiendo de como se encuentra la matriz de elementos, se actualizan las variables que indican el estado del juego
     */
    public void gameState(){
        
        //Estos son contadores son respectivamente para:
        //Obtener el toral de las celdas sin descubrir
        //Obtener el total de las celdas marcadas como banderas
        int counterCellsUnselect = 0, counterCellsMarked = 0;
        
        //Si el juego no ha finalizado
        if(!this.GAME_END){
            //Realizamos un ciclo doble para recorrere la matriz y conocer el toral de celdas sin seleccionar
            for(int i = 0; i < this.width; i++){
                for(int j = 0; j < this.height; j++){
                    //Si la celda actual no ha sido descubierta/seleccionada, se suma 1 al contador respectivo
                    if(this.matrixElements[i][j].isState() == false){
                        counterCellsUnselect++;
                    }
                }
            }//Fin de los recorridos
            
            //Ahora, se comprueba si el total de las celdas sin seleccionar sea igual a el numero de minas
            if(counterCellsUnselect == this.mines){
                //Si entra en aqui, indica que el usuario tiene celdas sin descubrir iguales al numero de minas
                //Por lo que procedemos a comprobar si estan todas marcadas
                for(int i = 0; i < this.width; i++){
                    for(int j = 0; j < this.height; j++){
                        //Si la celda actual esta marcada como bandera se aumenta el contador respectivo
                        if(this.matrixElements[i][j].isMarked()== true){
                            counterCellsMarked++;
                        }
                    }
                }//Fin de los recorridos
            }
            
            //Despues de los anteriores pasos podemos realizar la siguiente comprobacion para saber si el juego ha finalizado
            //Ya se ha comprobado que el numero de celdas sin marcar sea igual al numero de minas previamente
            //Si el numero de celdas marcadas es igual al numero de minas
            if(counterCellsMarked == this.mines){
                //Actualizamos los estados del juego
                this.VICTORY = true;
                this.GAME_END = true;
            }
        }//Fin del if que indica que el juego ha concluido
        
    }
    
    
    
    /**
     * eSTE METODO ES EL ENCARGADO DE GESTIONAR LA LOGICA DE DESCUBRIR LAS CELDAS ADYAECENTES
     * CUANDO EL USUARIO O EL ALGORITMO DESCUBRE UN '0' ('-') 
     * @param row FILA ORIGINAL, DONDE SE DESCUBRIO UN 0
     * @param column COLUMNA ORIGINAL DONDE SE ENCUENTRA UN 0 
     */
    public void discoverAdjacent(int row, int column){
    
        //Varibles locales al metodo, que indicaran las coordenas de una columna a revisar su contenido
        int row_to_review = 0, column_to_review = 0;
        
        //EMPEZAMOS A REVISAR LAS CELDAS ADYACENTES A UNA POCICION RECIBIDA
        //Esquina superior izquierda
        row_to_review = row - 1;
        column_to_review = column-1;
        if((row_to_review >= 0) && (column_to_review >= 0)){
            verifyCellAdjacent(row_to_review, column_to_review);
        }
        
        
        //Superior medio
        row_to_review = row-1;
        column_to_review = column;
        if((row_to_review >= 0)){
            verifyCellAdjacent(row_to_review, column_to_review);
        }
        
        //Esquina superior derecha
        row_to_review = row - 1;
        column_to_review = column + 1;
        if((row_to_review >= 0) && (column_to_review < this.height)){
            verifyCellAdjacent(row_to_review, column_to_review);
        }
        
        //Medio izquierdo
        row_to_review = row;
        column_to_review = column - 1;
        if( (column_to_review >= 0)){
            verifyCellAdjacent(row_to_review, column_to_review);
        }
        
        
        //Medio derecho
        row_to_review = row;
        column_to_review = column + 1;
        if( (column_to_review < this.height)){
            verifyCellAdjacent(row_to_review, column_to_review);
        }
        
        //Esquina Inferior izquierda
        row_to_review = row + 1;
        column_to_review = column-1;
        if((row_to_review < this.width) && (column_to_review >= 0)){
            verifyCellAdjacent(row_to_review, column_to_review);
        }
        
        
        //Medio inferior
        row_to_review = row + 1;
        column_to_review = column;
        if((row_to_review < this.width)){
            verifyCellAdjacent(row_to_review, column_to_review);
        }
        
        
        //Esquida inferior derecha
        row_to_review = row + 1;
        column_to_review = column + 1;
        if((row_to_review < this.width) && (column_to_review < this.height)){
            verifyCellAdjacent(row_to_review, column_to_review);
        }
    
    }
    
    
    
    /**
     * Este metodo es encargado de verificar el contenido de una celda adyacente recibida
     * y ejecutar acciones dependiendo de lo que haya en dicha celda adyacente
     * @param r fila de celda adyacente
     * @param c columna de celda adyacente
     */
    public void verifyCellAdjacent(int r, int c){
        //Si la celda adyacente no contiene una mina, procedemos a descubrir su contenido y realizar acciones dependiendo de dicho contenido
        if(!this.matrixElements[r][c].getContent().equals("*")){
            //Si la celda adyacente contiene un '0' ('-')
            if(this.matrixElements[r][c].getContent().equals("0")){
                //Se decubre la celda
                this.matrixElements[r][c].setState(true);
                this.matrixElements[r][c].setContent("-");
                //Recursivamente se llama al metodo descubrir adyacentes para seguir con la logica del juego
                //Esto hace que el juego fluya de manera adecuada, descubriendo celdas vacias adyacentes
                discoverAdjacent(r, c);
            }else{
                //Cuando la celda adyacente es un numero
                //No es bomba ni cero
                if(this.matrixElements[r][c].isState() == false){
                    //Se decubre
                    this.matrixElements[r][c].setState(true);
                }
            }
            
        }//Fin del if que comprueba si el contenido adyacente es una bomba
    }
    
    
    
    
    /*
        Metodos setters y getters de la clase
    */
    public boolean isVICTORY() {
        return this.VICTORY;
    }

    public void setVICTORY(boolean VICTORY) {
        this.VICTORY = VICTORY;
    }

    public boolean isGAME_END() {
        return this.GAME_END;
    }

    public void setGAME_END(boolean GAME_END) {
        this.GAME_END = GAME_END;
    }
    
    
    
    
}
