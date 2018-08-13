/*
 * Calse Setting
Esta clase es la encargada de establecer las configuraciones inicales al tablero
 */
package minesweeper.logic;

import java.util.Random;

/**
 *
 * @author Carlos Andrés Elguedo
 */
public class Setting {
    
    //Atribuutos de la clase
    //Indica la matriz de elementos de celdas del juego
    private Cell[][] elementsData;
    
    //Indica la altura del tablero
    private int columns;
    
    //Indica el ancho del tablero
    private int rows;
    
    //Indica la cantidad de minas del tablero
    private int mines;
    
    //Objeto para obtener numeros aleatoreos
    private Random random = new Random();
    
    /**
     * Constructor de la clase
     * @param rows
     * @param columns
     * @param mines 
     */
    public Setting(int rows, int columns, int mines){
        this.columns = columns;//Columnas
        this.rows = rows;//Filas
        this.mines = mines;
    }
    
    
    /**
     * Metodo encargado de configurar y llenar la matriz del juego
     * @return una matriz de celdas lista para empezar el juego
     */
    public Cell[][] fillBoard(){
        //Se instancia la matriz local
        this.elementsData = new Cell[this.rows][this.columns];
        
        //Se llena la matriz lccal con ´.'
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.columns; j++){
                //inicializamos las posiciones de la matriz
                elementsData[i][j] = new Cell(i, j, ".");
            }
        }//Fin de los recorridos
        
        //Se llama este metodo encargado de colocar las minas en la matriz de juego
        putMines();
        
        //Se llama a este metodo para calcular y colocar los elementos numericos de la matriz
        calculateInitialCells();
        
        //Despues de los pasos anteriores ya se tiene a la matriz lista
        return this.elementsData;
    }
    
    
    /**
     * Este metodo coloca las minas en la matriz
     */
    public void putMines(){
        int coorMineX = 0;
        int coorMineY = 0;
        
        int counterMinesPlaced = this.mines;
        
        //Mientras aun queden minas por colocar, sigue colocando
        while(counterMinesPlaced > 0){
            //Se obtiene una pocicion aleatorea
            coorMineX = getRandomNumber(this.rows);
            coorMineY = getRandomNumber(this.columns);
            //Se copmprueba que en la pocicion aleatorea no haya una mina
            if(this.elementsData[coorMineX][coorMineY].getContent().equals(".")){
                //Se coloca la mina
                this.elementsData[coorMineX][coorMineY].setContent("*");
                counterMinesPlaced--;
            }
        }//Fin del while
    }//Fin del metodo colocar minas
    
    
    /**
     * Metodo para calcular el numero de minas adyacentes a una celda
     */
    public void calculateInitialCells(){
        for(int i = 0; i < this.rows; i++){
            for(int j = 0;j < this.columns; j++){
                //Si la celda actual no contiene una mina, se procede a calcular las minas de su alrededor
                if(!this.elementsData[i][j].getContent().equals("*")){
                    //La celda actual se actualiza con el numero de minas que hay a su alrededor
                    this.elementsData[i][j].setContent(calculateNumberMinesAdjacent(i, j));
                }
            }
        }//Fin de los recorridos
    }
    
    /**
     * Este metodo es el encargado de calcular el numero de minas adyacentes de una celda dada
     * @param row dila a revisar
     * @param column columna a revisar
     * @return numero de minas adyacentes
     */
    public String calculateNumberMinesAdjacent(int row, int column){
        String data = "";
        
        //Varibles locales al metodo, que indicaran las coordenas de una columna a revisar su contenido
        int row_to_review = 0, column_to_review = 0;
        
        //Contador que indica la cantidad de minas adyacentes
        int counterMines = 0;
        
        //Seprocede a calcular las minas adyacentes, dependiendo de la posicion a revisar
        //Esquina superior izquierda
        row_to_review = row - 1;
        column_to_review = column-1;
        if((row_to_review >= 0) && (column_to_review >= 0)){
            counterMines += verificarMinasAdyacente(row_to_review, column_to_review);
        }
        
        
        //Superior medio
        row_to_review = row-1;
        column_to_review = column;
        if((row_to_review >= 0)){
            counterMines += verificarMinasAdyacente(row_to_review, column_to_review);
        }
        
        //Esquina superior derecha
        row_to_review = row - 1;
        column_to_review = column + 1;
        if((row_to_review >= 0) && (column_to_review < this.columns)){
            counterMines += verificarMinasAdyacente(row_to_review, column_to_review);
        }
        
        //Medio izquierdo
        row_to_review = row;
        column_to_review = column - 1;
        if( (column_to_review >= 0)){
            counterMines += verificarMinasAdyacente(row_to_review, column_to_review);
        }
        
        
        //Medio derecho
        row_to_review = row;
        column_to_review = column + 1;
        if( (column_to_review < this.columns)){
            counterMines += verificarMinasAdyacente(row_to_review, column_to_review);
        }
        
        //Esquina Inferior izquierda
        row_to_review = row + 1;
        column_to_review = column-1;
        if((row_to_review < this.rows) && (column_to_review >= 0)){
            counterMines += verificarMinasAdyacente(row_to_review, column_to_review);
        }
        
        
        //Medio inferior
        row_to_review = row + 1;
        column_to_review = column;
        if((row_to_review < this.rows)){
            counterMines += verificarMinasAdyacente(row_to_review, column_to_review);
        }
        
        
        //Esquida inferior derecha
        row_to_review = row + 1;
        column_to_review = column + 1;
        if((row_to_review < this.rows) && (column_to_review < this.columns)){
            counterMines += verificarMinasAdyacente(row_to_review, column_to_review);
        }
        
        
        
        //El testo a retornar se actualiza con la cantidad de minas encontradas
        data = "" + counterMines;
        return data;
    }
    
    
    /**
     * Este metodo es para saber en una celda dada hay una mina
     * @param r fila de la celda dada
     * @param c columna de la celda dada
     * @return 1 si la comprobacion es correcta (si hay una mina en la celda dada)
     */
    public int verificarMinasAdyacente(int r, int c){
        int counter = 0;
        //Se comprueba que en la celda dada haya una mina
        if(this.elementsData[r][c].getContent().equals("*")){
            counter++;
        }
        return counter;
    }
    
    /**
     * Este metodo es para obtener un numero aleatoreo
     * @param limit maximo valor menos uno, que puede tomar el numero aleatoreo
     * @return 
     */
    public int getRandomNumber(int limit){
        
        return random.nextInt(limit);
        
    }
    
}//Fin de la clase
