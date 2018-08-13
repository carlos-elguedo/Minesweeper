/**
 * Clase Validaciones
 * Esta clase es la encargada de hacer las comprobaciones necesarias a los datos ingresados por el usuario
 * aqui se verificara la validez de cada dato ingresado por el usuario
 */
package minesweeper.control;

import java.util.ArrayList;



/**
 *
 * @author Carlos Andrés Elguedo Padilla
 * @version 1.0
 */
public class Validaciones{
    
    //Atributos de la clase
    
    /*En esta variable se almacenara el texto total que fue ingresado por el usuario*/
    private String text_to_verify;
    
    //Se almacena estas variables desde la validacion de la entrada inicial, para ser usadas para futuras comprobaciones
    //Filas del cuadro de juego,
    private int filas;
    //Columnas del cuadro de juegpo
    private int rows;

    
    /**
     * Constructor de la clase
     */
    public Validaciones(){
        //Inicializamos las variables locales
        this.text_to_verify = "";
        this.filas = 0;
        this.rows = 0;
    }
    
    
    /**
     * Este metodo es para verificar la entrada inicial
     * @param text cadena a verificar
     * @return Un arrayList con los datos comprobados, si lo retorno vacio, indica que la entrada es incorrecta
     */
    public ArrayList<Integer> validateInitialEntry(String text){
        //Esta variable indicará el estado de la comprobacion para ralizar validaciones propias de la funcion
        boolean incorrect = false;
        
        //Argumentos iniciales para el juego
        //Este arrayList es donde se almacenaran los datos del usuarios para ser retornados
        ArrayList<Integer> data = new ArrayList<Integer>();
        
        //Estas variables indican la entrada inicial del usuario en tipo de datos 'enteros'
        int n1 = 0, n2 = 0, n3 = 0;
        
        //Se copia el valor recibido al atributo local
        this.text_to_verify = text;
        
        //Obtenemos un array de la entrada inicial para realizar comprobaciones
        String[] initialInput = text_to_verify.split(" ");
        
        //Primero, comprobamos que la entrada este en formato de datos correctos (3 datos separados por UN espacio)
        if(initialInput.length == 3){
            //Segundo, se verifican que las entradas iniciales sean numeros y no letras
            //A el metodo que verifica que sean numeros, se le pasa como atributo el texto a verificar:
            if (isCorrectNumber(initialInput[0]) && isCorrectNumber(initialInput[1]) && isCorrectNumber(initialInput[2])){
                
                //NO HAY NECESIDAD DE COLOCAR TRY CATCH, DEBIDO A QUE EN LA INSTRUCCION ANTERIOR SE UTILIZO
                //Le asignamos el valor correspondiente a las variables enteras
                n1 = Integer.parseInt(initialInput[0]);
                n2 = Integer.parseInt(initialInput[1]);
                n3 = Integer.parseInt(initialInput[2]);
                
                
                //Validaciones iniciales para el tablero
                
                //Cuando alguas de las medidas es muy pequeña
                if(n1 * n2 < 4){
                    entryNotValid(4);
                    incorrect = true;
                }
                
                //Si el numero de minas supera la cantidad de celdas existentes
                if(n3 >= (n1 * n2) && incorrect == false){
                    entryNotValid(3);
                    incorrect = true;
                }
                
                //Cuandpo no hay minas
                if(n3 == 0 && incorrect == false){
                    entryNotValid(5);
                    incorrect = true;
                }
                
                
                //Si despues de las comprobaciones anteriores, 'incorrecto' es falso, indica que la entrada inical fue correcta
                if(incorrect == false){
                    //Añadimos los datos al arrayList de retorno
                    data.add(n1);
                    data.add(n2);
                    data.add(n3);
                    
                    //Aignamos a las varibles locales, los datos recibidos
                    this.filas = n1;
                    this.rows = n2;
                }
                
            }//Fin del if que comprueba que los datos ingresados sean numeros
        }else{
            //El texto no esta en formato correcto
            entryNotValid(1);
        }
        
        //Retornomos el arrayList de retorno, vacio o lleno, deacuerdo a las comprobaciones realizadas anteriormente
        return data;
    }
    
    
    /**
     * Este metodo es para verificar que la entrada de un turno sea correcta
     * @param text texto a verificar
     * @return Un arrayList con los datos comprobados, si lo retorno vacio, indica que la entrada es incorrecta
     */
    public ArrayList<Integer> RoundInputIsCorrect(String text){
        //Variable que indica el estado de la comprobacion
        boolean correct = false;
        
        ////Este arrayList es donde se almacenaran los datos del usuarios para ser retornados
        ArrayList<Integer> data = new ArrayList<Integer>();
        
        //Estas variables indican la entrada inicial del usuario en tipo de datos 'enteros'
        int n1 = 0, n2 = 0, n3 = 0;
        
        //Se copia el valor recibido al atributo local
        this.text_to_verify = text;
        
        //Obtenemos un array de la entrada inicial para realizar comprobaciones
        String[] input = this.text_to_verify.split(" ");
        
        //Primero, comprobamos que la entrada este en formato de datos correctos (3 datos separados por UN espacio)
        if(input.length == 3){
            //Segundo, se verifican que las entradas iniciales sean numeros y no letras
            //A el metodo que verifica que sean numeros, se le pasa como atributo el texto a verificar:
            if (isCorrectNumber(input[0]) && isCorrectNumber(input[1])){
                
                //NO HAY NECESIDAD DE COLOCAR TRY CATCH, DEBIDO A QUE EN LA INSTRUCCION ANTERIOR SE UTILIZO
                //Le asignamos el valor correspondiente a las variables enteras
                n1 = Integer.parseInt(input[0]);
                n2 = Integer.parseInt(input[1]);
                
                //VEEIFICAMOS QUE LOS NUMEROS INGRESADOS SEAN CORRECTOS
                //Se verifican que los dos primeros numeros sean menores a la cantidad de filas y rows existentes
                if(n1 >= this.filas || n2 >= this.rows){
                    entryNotValid(6);
                }else{
                    //Ahora, se verifica que haya ingreado un texto de accion correcto
                    //Para cuando va a descubrir una celda
                    if(input[2].equals("u") || input[2].equals("U")){
                        //La variable entera 'n3' indica el tipo de accion de arealizar: 1 INDICA DESCUBRIR
                        n3 = 1;
                        //La entrada es correcta
                        correct = true;
                    }

                    //Para cuando va a marcar una celda
                    if(input[2].equals("m") || input[2].equals("M")){
                        //La variable entera 'n3' indica el tipo de accion de arealizar: 2 INDICA MARCAR COMO BANDERA
                        n3 = 2;
                        //La entrada es correcta
                        correct = true;
                    }
                }//Fin del else de compruba que la entrada no sea mayor a las dimensiones del cuadro de juego
                
            }//Fin del if que verifica que los dos datos ingresados sean numeros
            
        }//Fin del if que comprueba el formato correcto de los datos
        
        //Si despues de las validaciones acteriores 'correct' es true, indica que la entrada fue correcta
        if(correct){
            //Añadimos los datos al arrayList de retorno
            data.add(n1);
            data.add(n2);
            data.add(n3);
        }
        
        //Retornomos el arrayList de retorno, vacio o lleno, deacuerdo a las comprobaciones realizadas anteriormente
        return data;
    }//Fin del metodo
    
    
    
    
    
    /**
     * Esta funcion es para comprobar si una parte del texto ingresado por el usuario es del formato correcto (Numerico)
     * Se entiende por parte, uno de los lados divididos por la ',' de la entrada del texto ingresado por el usuario
     * @param cadena text_to_verify de texto que se va a verificar
     * @return verdadero si el texto recibido es numerico y o esta dentro del rango
     */
    public boolean isCorrectNumber(String text){
        /*Variable futura del valor de retorno*/
        boolean return_data = false;
        
        /*
        Debido a que la text_to_verify de digitos puede ser muy larga, sobrepasando la capacidad de los tipos de datos comunes, se realiza lo siguiente:
        Se convierte la text_to_verify a un Arreglo, separando cada caracter individualmente
        Se comprobara si cada caracter es un numero
        Esto nos garantizara la aceptacion de expresiones con digitos muy largos.
        */
        /*Obtenemos un array de todos los elementos del texto*/
        String[] elements = text.split("");
        
        //Realizamos un ciclo para comprobar los elementos de la text_to_verify recibida, si encontramos un elemento no numerico, terminamos el ciclo inmediatamaente
        for(int i = 0; i<elements.length; i++){
            //Realizamos un procedimiento para hacer la conversión los elementos de la text_to_verify a numericos (Integer)
            try {
                //Se convierte cada elemento, pero no es necesario almacenarlo
                Integer.parseInt(elements[i]);
                //Si la comprobacion fue satisfactoria, indica que ese elemento es numerico, y podemos pasar al siguiente
                return_data = true;
            } catch (NumberFormatException ex) {
                //Si ocurre al gun error en la conversion a numericos de la text_to_verify recibida, quiere decir la text_to_verify no es numerica
                return_data = false;
                //Mostramos un texto en consola que indique lo sucedido
                entryNotValid(2);
                //Terminamos el ciclo inmediatamente
                break;
            }
        }//Fin del for que recorre todos los elementos de la text_to_verify
        
        //Retornamos un valor indicando el resultado de los procedimientos de esta funcion
        return return_data;
    
    }//Fin de la funcion isCorrectNumber
    
    
    /**
     * Esate metodo es para imprimir en pantalla que un texto ingresado no es valido, indicando el tipo de error cometico
     * 1 para cuando el texto no esta en formato correcto
     * 2 para cuando el texto no pudo se procesado por contener caracteres no numericos
     * 3 para cuando la cantidad de minas no puede ser admitidas
     * 4 para cuando el tablero es muy pequeño
     * 5 No ha ingresados minas para colocar
     * 6 Las coordenadas de una celda ingresada superan el tamaño del tablero
     * defaul para cuando el texto tiene error de formato diferente
     * @param error_type indica que tipo de error se va a mostrar en pantalla
     */
    public void entryNotValid(int error_type){
        //Se analiz el parametro recibido para mostrar el mensaje especificado correctamente
        switch(error_type){
            
            //La entrada inicial no esta en formato separadas por " "
            case 1:
                System.err.println("Por favor verifica la entrada inicial formato: 'fil' 'col' 'min'");
                break;
            
            
            //Para cuando la text_to_verify ingresada contiene caracteres diferentes a los digitos
            case 2:
                System.err.println("El texto contiene caracteres distintos a los digitos");
                break;
                
            
            //Para cuando la primera parte de la text_to_verify supera no esta en rango entre 1 y 10
            case 3:
                //Cuando el numero de minas supera las celdas existentes
                System.err.println("No se puede crear esa cantidad de minas");
                
                break;
                
            
            //Para cuando el tablero es muy pequeño
            case 4:
                System.err.println("El tablero es muy pequeño (minimo: 2x2)");
                break;
                
                
            case 5:
                System.err.println("No hay minas que colocar");
                break;
            
            case 6:
                System.err.println("La coordenada ingresada supera el limite del tablero");
                break;
            
                
            //Para cuando la variable recibida no ingreso a ninguno de los casos anteriores
            default:
                System.err.println("No se puede procesar el texto ingresado");
                break;
            
        }//Fin del switch
        
    }//Fin de la funcion entryNotValid, la cual muestra en consola el tipo de error
    
    
    
    
    
    /**
     * Metodos Getters y Setters de la clase
     */
    
    public String getText_to_verify() {
        return text_to_verify;
    }

    public void setText_to_verify(String text_to_verify) {
        this.text_to_verify = text_to_verify;
    }
}
