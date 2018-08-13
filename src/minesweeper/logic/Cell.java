/*
 clase Cell
Esta clase representa una celda de la matriz del juego buscaminas
 */
package minesweeper.logic;

/**
 *
 * @author Carlos Aandrés Elguedo
 */
public class Cell {
    //Atributos de la clase
    //Este atributo indica el estado de la celda, si ha sido descubierta o no
    private boolean state;
    
    //Este atributo indica la coordenada x (fila) de la celda
    private int cooX;
    
    //Este atributo indica la coordenada y (columna) de la celda
    private int cooY;
    
    //Este atributo indica el contenido de la celda
    private String content;
    
    //Este atributo indica si la celda ha sido marcada como marked
    private boolean marked;

    /**
     * CONSTRUCTOR DE LA CLASE
     * @param cooX fila de la celda
     * @param cooY columna de la celda
     * @param content contenido de la celda
     */
    public Cell( int cooX, int cooY, String content) {
        this.state = false;
        this.cooX = cooX;
        this.cooY = cooY;
        this.content = content;
    }

    /**
     * Constructorpor defecto de la clase
     */
    public Cell() {
    }

    
    //METODOS GETTERS Y SETTERS
    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getCooX() {
        return cooX;
    }

    public void setCooX(int cooX) {
        this.cooX = cooX;
    }

    public int getCooY() {
        return cooY;
    }

    public void setCooY(int cooY) {
        this.cooY = cooY;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }
    
    
    
    
    
}
