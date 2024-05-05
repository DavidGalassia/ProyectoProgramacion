import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class sopa {

    private static Scanner input = new Scanner(System.in);
    private static Random random = new Random();

    public static void constructor_sopa(int size_sopa, int cantidad_palabras,int orientacion_palabras, ArrayList<String> palabras_a_encontrar) {

        if (size_sopa == -1 && cantidad_palabras == -1 && orientacion_palabras == -1 && palabras_a_encontrar.isEmpty()) {
            System.out.println("No se puede crear la sopa de letras, ha elegido opciones no disponibles");

            //Si hubo un error en la entrada, no deja que el codigo siga

        }else{
            agregar_palabras(palabras_a_encontrar);

            char [][] sopa_de_letras;
            switch (size_sopa) {
                case 1:
                    sopa_de_letras = new char [10][10];

                    meter_palabras(palabras_a_encontrar, orientacion_palabras, sopa_de_letras);
                    rellenar_vacio(sopa_de_letras);


                    juego.juego_inicio(palabras_a_encontrar, sopa_de_letras);

                    break;
                case 2:
                    sopa_de_letras = new char [15][15];

                    meter_palabras(palabras_a_encontrar, orientacion_palabras, sopa_de_letras);
                    rellenar_vacio(sopa_de_letras);

                    juego.juego_inicio(palabras_a_encontrar, sopa_de_letras);


                    break;
                case 3:
                    sopa_de_letras = new char [20][20];

                    meter_palabras(palabras_a_encontrar, orientacion_palabras, sopa_de_letras);
                    rellenar_vacio(sopa_de_letras);

                    juego.juego_inicio(palabras_a_encontrar, sopa_de_letras);

                    break;
            }
        }
    }

    public static void agregar_palabras (ArrayList<String> palabras_a_encontrar){

        System.out.println("  Las palabras que se van a añadir a la sopa son: ");

        //Imprime todo el array de palabras

        for (int i = 0; i < palabras_a_encontrar.size(); i++) {

            System.out.print(palabras_a_encontrar.get(i));

            if ((i + 1) % 5 == 0 || i == palabras_a_encontrar.size() - 1) {
                System.out.println(" ");
            } else {
                System.out.print(", ");
            }
        }


        System.out.println("  ¿Desea añadir mas palabras?");
        System.out.println("  1. Si");
        System.out.println("  2. No");
        int pregunta_add = input.nextInt();

        if (pregunta_add == 1) {
            input.nextLine();
            System.out.println("  Ingrese las palabras que desea añadir (separadas por espacios):");
            String palabras_a_agregar = input.nextLine();
            String[] palabras_agregadas = palabras_a_agregar.split("\\s+"); // Dividir por espacios
            palabras_a_encontrar.addAll(Arrays.asList(palabras_agregadas));
        }
    }

    public static void meter_palabras(ArrayList<String> palabras_a_encontrar, int orientacion_palabras, char[][] sopa_de_letras) {
        int direccion;
        boolean palabra_agregada = false;

        switch (orientacion_palabras) {
            case 1:
                for (String palabra : palabras_a_encontrar) {
                    for (int i = 0; i < 60; i++){  //Intenta meter la palabra a la sopa 60 veces, y si no funciona, no la mete
                        direccion = random.nextInt(2) + 1; //Decide la direccion por un random

                        if (direccion == 1){
                            palabra_agregada = meter_palabra_horizontal(palabra, sopa_de_letras);
                        }else{
                            palabra_agregada = meter_palabra_vertical(palabra, sopa_de_letras);
                        }

                        if (palabra_agregada == true){ //Si consiguio agregar la palabra, sale del ciclo
                            break;
                        }
                    }
                }

                break;
            case 2:
                for (String palabra : palabras_a_encontrar) {
                    for (int i = 0; i < 60; i++){
                        direccion = random.nextInt(4) + 1; //Decide la direccion por un random

                        if (direccion == 1){
                            meter_palabra_horizontal(palabra, sopa_de_letras);
                        }else if (direccion == 2){
                            meter_palabra_vertical(palabra, sopa_de_letras);
                        }else if (direccion == 3){
                            meter_palabra_diagonal_arriba(palabra, sopa_de_letras);
                        }else{
                            meter_palabra_diagonal_abajo(palabra, sopa_de_letras);
                        }

                        if (palabra_agregada == true){ //Si consiguio agregar la palabra, sale del ciclo
                            break;
                        }
                    }
                }
                break;
            case 3:
                for (String palabra : palabras_a_encontrar) {
                    for (int i = 0; i < 60; i++){
                        direccion = random.nextInt(8) + 1; //Decide la direccion por un random

                        if (direccion == 1){
                            meter_palabra_horizontal(palabra, sopa_de_letras);
                        }else if (direccion == 2){
                            meter_palabra_vertical(palabra, sopa_de_letras);
                        }else if (direccion == 3){
                            meter_palabra_diagonal_arriba(palabra, sopa_de_letras);
                        }else if (direccion == 4){
                            meter_palabra_diagonal_abajo(palabra, sopa_de_letras);
                        }else if (direccion == 5){
                            meter_palabra_horizontal_inversa(palabra, sopa_de_letras);
                        }else if (direccion == 6){
                            meter_palabra_vertical_inversa(palabra, sopa_de_letras);
                        }else if (direccion == 7){
                            meter_palabra_diagonal_arriba_inversa(palabra, sopa_de_letras);
                        }else{
                            meter_palabra_diagonal_abajo_inversa(palabra, sopa_de_letras);
                        }

                        if (palabra_agregada == true){ //Si consiguio agregar la palabra, sale del ciclo
                            break;
                        }
                    }
                }
                break;
        }
    }

    //En estas funciones, se revisa si la posicion donde se va a poner la palabra esta vacia
    //si si esta vacia, sigue corriendo este ciclo de revisar hasta que los espacios disponibles
    //sean iguales a la longitud de la palabra

    //Cuando la cantidad de los espacios vacios es igual a la longitud de la palabra, la mete en la sopa de letras

    public static boolean meter_palabra_horizontal(String palabra, char[][] sopa_de_letras) {
        int filaInicio = random.nextInt(sopa_de_letras.length);
        int columnaInicio = random.nextInt(sopa_de_letras.length);
        int palabra_cabe = 0;
        int i;

        boolean cabe_horizontal = columnaInicio + palabra.length() <= sopa_de_letras.length;

        if (cabe_horizontal) {
            for (i = 0; i < palabra.length(); i++) {
                if (sopa_de_letras[filaInicio][columnaInicio + i] == '\u0000') {
                    palabra_cabe = palabra_cabe + 1;
                }
            }

            if (palabra_cabe == palabra.length()) {
                for (i = 0; i < palabra.length(); i++) {
                    sopa_de_letras[filaInicio][columnaInicio + i] = palabra.toUpperCase().charAt(i);
                }
                return true; //Consiguio meter la palabra
            }else{
                return false; //No consiguio meter la palabra
            }
        }
        return false;
    }

    public static boolean meter_palabra_vertical(String palabra, char[][] sopa_de_letras) {
        int filaInicio = random.nextInt(sopa_de_letras.length);
        int columnaInicio = random.nextInt(sopa_de_letras.length);
        int palabra_cabe = 0;
        int i;

        boolean cabe_vertical = filaInicio + palabra.length() <= sopa_de_letras.length;

        if (cabe_vertical) {
            for (i = 0; i < palabra.length(); i++) {
                if (sopa_de_letras[filaInicio + i][columnaInicio] == '\u0000') {
                    palabra_cabe = palabra_cabe + 1;
                }
            }

            if (palabra_cabe == palabra.length()) {
                for (i = 0; i < palabra.length(); i++) {
                    sopa_de_letras[filaInicio + i][columnaInicio] = palabra.toUpperCase().charAt(i);
                }
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public static boolean meter_palabra_diagonal_arriba(String palabra, char[][] sopa_de_letras) {
        int filaInicio = random.nextInt(sopa_de_letras.length);
        int columnaInicio = random.nextInt(sopa_de_letras.length);
        int palabra_cabe = 0;
        int i;

        boolean cabe_horizontal = columnaInicio + palabra.length() <= sopa_de_letras.length;
        boolean cabe_diagonal_arriba = filaInicio - palabra.length() >= 0 && cabe_horizontal;

        if (cabe_diagonal_arriba) {
            for (i = 0; i < palabra.length(); i++) {
                if (sopa_de_letras[filaInicio - i][columnaInicio + i] == '\u0000') {
                    palabra_cabe = palabra_cabe + 1;
                }
            }

            if (palabra_cabe == palabra.length()) {
                for (i = 0; i < palabra.length(); i++) {
                    sopa_de_letras[filaInicio - i][columnaInicio + i] = palabra.toUpperCase().charAt(i);
                }
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public static boolean meter_palabra_diagonal_abajo(String palabra, char[][] sopa_de_letras) {
        int filaInicio = random.nextInt(sopa_de_letras.length);
        int columnaInicio = random.nextInt(sopa_de_letras.length);
        int palabra_cabe = 0;
        int i;

        boolean cabe_horizontal = columnaInicio + palabra.length() <= sopa_de_letras.length;
        boolean cabe_vertical = filaInicio + palabra.length() <= sopa_de_letras.length;

        boolean cabe_diagonal_abajo = cabe_horizontal && cabe_vertical;

        if (cabe_diagonal_abajo) {
            for (i = 0; i < palabra.length(); i++) {
                if (sopa_de_letras[filaInicio + i][columnaInicio + i] == '\u0000') {
                    palabra_cabe = palabra_cabe + 1;
                }
            }

            if (palabra_cabe == palabra.length()) {
                for (i = 0; i < palabra.length(); i++) {
                    sopa_de_letras[filaInicio + i][columnaInicio + i] = palabra.toUpperCase().charAt(i);
                }
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public static boolean meter_palabra_horizontal_inversa(String palabra, char[][] sopa_de_letras) {
        int filaInicio = random.nextInt(sopa_de_letras.length);
        int columnaInicio = random.nextInt(sopa_de_letras.length);
        int palabra_cabe = 0;
        int i;

        boolean cabe_horizontal_inversa = columnaInicio - palabra.length() >= 0;

        if (cabe_horizontal_inversa) {
            for (i = 0; i < palabra.length(); i++) {
                if (sopa_de_letras[filaInicio][columnaInicio - i] == '\u0000') {
                    palabra_cabe = palabra_cabe + 1;
                }
            }

            if (palabra_cabe == palabra.length()) {
                for (i = 0; i < palabra.length(); i++) {
                    sopa_de_letras[filaInicio][columnaInicio - i] = palabra.toUpperCase().charAt(i);
                }
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public static boolean meter_palabra_vertical_inversa(String palabra, char[][] sopa_de_letras) {
        int filaInicio = random.nextInt(sopa_de_letras.length);
        int columnaInicio = random.nextInt(sopa_de_letras.length);
        int palabra_cabe = 0;
        int i;

        boolean cabe_vertical_inversa = filaInicio - palabra.length() >= 0;

        if (cabe_vertical_inversa) {
            for (i = 0; i < palabra.length(); i++) {
                if (sopa_de_letras[filaInicio - i][columnaInicio] == '\u0000') {
                    palabra_cabe = palabra_cabe + 1;
                }
            }

            if (palabra_cabe == palabra.length()) {
                for (i = 0; i < palabra.length(); i++) {
                    sopa_de_letras[filaInicio - i][columnaInicio] = palabra.toUpperCase().charAt(i);
                }
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public static boolean meter_palabra_diagonal_arriba_inversa(String palabra, char[][] sopa_de_letras) {
        int filaInicio = random.nextInt(sopa_de_letras.length);
        int columnaInicio = random.nextInt(sopa_de_letras.length);
        int palabra_cabe = 0;
        int i;

        boolean cabe_horizontal_inversa = columnaInicio - palabra.length() >= 0;
        boolean cabe_diagonal_arriba_inversa = filaInicio + palabra.length() <= sopa_de_letras.length && cabe_horizontal_inversa;

        if (cabe_diagonal_arriba_inversa) {
            for (i = 0; i < palabra.length(); i++) {
                if (sopa_de_letras[filaInicio + i][columnaInicio - i] == '\u0000') {
                    palabra_cabe = palabra_cabe + 1;
                }
            }

            if (palabra_cabe == palabra.length()) {
                for (i = 0; i < palabra.length(); i++) {
                    sopa_de_letras[filaInicio + i][columnaInicio - i] = palabra.toUpperCase().charAt(i);
                }
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public static boolean meter_palabra_diagonal_abajo_inversa(String palabra, char[][] sopa_de_letras) {
        int filaInicio = random.nextInt(sopa_de_letras.length);
        int columnaInicio = random.nextInt(sopa_de_letras.length);
        int palabra_cabe = 0;
        int i;

        boolean cabe_diagonal_abajo_inversa = filaInicio - palabra.length() >= 0 && columnaInicio - palabra.length() >= 0;

        if (cabe_diagonal_abajo_inversa) {
            for (i = 0; i < palabra.length(); i++) {
                if (sopa_de_letras[filaInicio - i][columnaInicio - i] == '\u0000') {
                    palabra_cabe = palabra_cabe + 1;
                }
            }

            if (palabra_cabe == palabra.length()) {
                for (i = 0; i < palabra.length(); i++) {
                    sopa_de_letras[filaInicio - i][columnaInicio - i] = palabra.toUpperCase().charAt(i);
                }
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public static void rellenar_vacio(char[][] sopa_de_letras){

        for (int i = 0; i < sopa_de_letras.length; i++){
            for (int j = 0; j < sopa_de_letras.length; j++){
                if (sopa_de_letras[i][j] == '\u0000'){
                    sopa_de_letras[i][j] = (char) (random.nextInt(26) + 'A');
                }
            }
        }
    }
}
