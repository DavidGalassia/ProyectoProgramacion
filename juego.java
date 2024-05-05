import java.util.*;

public class juego {
    public static final Scanner input = new Scanner(System.in);
    public static int puntos = 0;
    public static Timer timer = new Timer();


    public static void juego_inicio(ArrayList<String> palabras_a_encontrar, char[][] sopa_de_letras) {
        ArrayList<String> palabras_encontradas = new ArrayList<>();

        //Crea un array para ver que palabaras ha encontrado,
        // y otro para poder tener una lista de las palabras que falta por encontrar

        ArrayList<String> palabras_a_encontrar_menos_encontradas = new ArrayList<>(palabras_a_encontrar);

        System.out.println("  Introduzca '1' cuando desee iniciar el juego");
        System.out.println("  1. Iniciar el juego");
        int inicio = input.nextInt();
        int i = 0;
        long tiempoInicio = System.currentTimeMillis(); //Se toma el tiempo del sistema al iniciar el juego

        if (inicio != 1) {
            System.out.println("Opcion no disponible");

        } else {

            imprimir_sopa(sopa_de_letras);

            System.out.println(" Las palabras a buscar en la sopa de palabras son:");

            for (i = 0; i < palabras_a_encontrar.size(); i++) {

                System.out.print(palabras_a_encontrar.get(i));

                if ((i + 1) % 5 == 0 || i == palabras_a_encontrar.size() - 1) {
                    System.out.println(" ");
                } else {
                    System.out.print(", ");
                }
            }

            boolean encontrando_palabras = true;

            while (encontrando_palabras) {
                System.out.println("  Opciones en la sopa de letras:");
                System.out.println("  1. Ingresar palabra encontrada");
                System.out.println("  2. Rendirse");
                int eleccion = input.nextInt();

                if (eleccion == 2){ //Si se rinde

                    elegir_rendirse(palabras_a_encontrar_menos_encontradas, tiempoInicio);
                    encontrando_palabras = false;

                }else if (eleccion == 1){
                    System.out.println("  Ingrese la palabra encontrada");
                    String palabra_encontrada = input.next();

                    if (!palabras_a_encontrar.contains(palabra_encontrada)) {
                        System.out.println("Esa palabra no se encuentra dentro de la lista a buscar");

                    } else {

                        boolean encontro_palabra = buscador_palabras(sopa_de_letras, palabra_encontrada);

                        if (encontro_palabra) {

                            System.out.println("Ha encontrado la palabra "+palabra_encontrada);
                            palabras_encontradas.add(palabra_encontrada);
                            palabras_a_encontrar_menos_encontradas.remove(palabra_encontrada);

                            for (i = 0; i < palabras_a_encontrar_menos_encontradas.size(); i++) {

                                System.out.print(palabras_a_encontrar_menos_encontradas.get(i));

                                if ((i + 1) % 5 == 0 || i == palabras_a_encontrar_menos_encontradas.size() - 1) {
                                    System.out.println(" ");
                                } else {
                                    System.out.print(", ");
                                }
                            }

                        }else{
                            System.out.println("Posicion incorrecta");
                        }
                    }

                    if (palabras_encontradas.containsAll(palabras_a_encontrar)) {
                        encontrando_palabras = false;

                        long tiempoFin = System.currentTimeMillis(); //Se toma el tiempo del sistema al terminar el juego
                        long tiempoTotal = tiempoFin - tiempoInicio;
                        // El tiempo tomado en terminar la sopa de letra es el tiempo final menos el inicial

                        // Calcular los puntos obtenidos basados en el tiempo total
                        puntos = calcularPuntos(tiempoTotal);

                        System.out.println("Felicidades, has completado la sopa de letras");
                        System.out.println("Tu tiempo total fue de: " + (tiempoTotal / (1000*60)) + " minutos.");
                        System.out.println("Obtuviste " + puntos + " puntos.");

                        // Detener el temporizador
                        timer.cancel();

                    }

                }else{
                    System.out.println("  Opcion no disponible");
                }
            }
        }
    }

    public static void elegir_rendirse(ArrayList<String> palabras_a_encontrar_menos_encontradas, long tiempoInicio){
        System.out.println("  Ha elegido rendise");
        System.out.println("  Las palabras que faltaban por encontrar son:");

        for (int i = 0; i < palabras_a_encontrar_menos_encontradas.size(); i++) {
            System.out.print(palabras_a_encontrar_menos_encontradas.get(i));
            if ((i + 1) % 5 == 0 || i == palabras_a_encontrar_menos_encontradas.size() - 1) {
                System.out.println(" ");
            } else {
                System.out.print(", ");
            }
        }

        long tiempoFin = System.currentTimeMillis(); //Se toma el tiempo del sistema al terminar el juego
        long tiempoTotal = tiempoFin - tiempoInicio;

        System.out.println("Tu tiempo total fue de: " + (tiempoTotal / (1000)) + " segundos.");
        System.out.println("Obtuviste " + 0 + " puntos.");

        timer.cancel();
    }
    public static int calcularPuntos(long tiempo) {
        int puntos = 0;

        // Convertir el tiempo a minutos
        double minutos = (double) tiempo / (1000 * 60);

        if (minutos >= 20) {
            puntos = 10;
        } else if (minutos >= 15) {
            puntos = 25;
        } else if (minutos >= 10) {
            puntos = 40;
        } else {
            puntos = 60;
        }

        return puntos;
    }
    public static void imprimir_sopa(char[][] sopa_de_letras) {

        String[][] posiciones = new String[sopa_de_letras.length][sopa_de_letras.length];

        int y,x;

        System.out.println("  Ejemplo de posiciones (x,y)");

        for (y = 0; y < 5; y++) {
            for (x = 0; x < 5; x++) {

                posiciones[y][x] = (x) + ", " + (y);

                System.out.print(" | " + posiciones[y][x]);

            }
            System.out.println(" ");
        }

        System.out.println(" ");
        System.out.println("  Sopa de letras: ");
        System.out.println(" ");

        for (y = 0; y < sopa_de_letras.length; y++) {
            for (x = 0; x < sopa_de_letras.length; x++) {
                System.out.print(" | " + sopa_de_letras[y][x]);
            }
            System.out.println(" ");
        }

        System.out.println(" ");
    }
    public static boolean buscador_palabras(char[][] sopa_de_letras, String palabra_encontrada){

        System.out.println(" Escriba aqui la x primera coordenada de su posicion: (x₁,y₁)");
        int primera_posicion_x = input.nextInt();

        System.out.println(" Escriba aqui la y primera coordenada de su posicion: (x₁,y₁)");
        int primera_posicion_y = input.nextInt();

        System.out.println(" Escriba aqui la x ultima coordenada de su posicion: (x₂,y₂)");
        int ultima_posicion_x = input.nextInt();

        System.out.println(" Escriba aqui la y ultima coordenada de su posicion: (x₂,y₂)");
        int ultima_posicion_y = input.nextInt();

        boolean primera_posicion = buscar_primera_posicion(sopa_de_letras, primera_posicion_x, primera_posicion_y, palabra_encontrada);
        boolean ultima_posicion = buscar_ultima_posicion(sopa_de_letras, ultima_posicion_x, ultima_posicion_y, palabra_encontrada);

        return (primera_posicion && ultima_posicion);

    }
    public static boolean buscar_primera_posicion(char[][] sopa_de_letras, int x, int y, String palabra_encontrada) {

        boolean Abajo = (y + 1) < sopa_de_letras.length;
        boolean Derecha = (x + 1) < sopa_de_letras.length;
        boolean Arriba = (y - 1) >= 0;
        boolean Izquierda = (x - 1) >= 0;

        char Segunda_Letra = palabra_encontrada.toUpperCase().charAt(1);

        if (sopa_de_letras[y][x] == palabra_encontrada.toUpperCase().charAt(0)) {

            if (Arriba && (Segunda_Letra == sopa_de_letras[y - 1][x])){
                return true;
            }

            if (Arriba && Derecha && (Segunda_Letra == sopa_de_letras[y - 1][x + 1])){
                return true;
            }

            if (Arriba && Izquierda && (Segunda_Letra == sopa_de_letras[y - 1][x - 1])){
                return true;
            }

            if (Abajo && (Segunda_Letra == sopa_de_letras[y + 1][x])) {
                return true;
            }

            if (Abajo && Derecha && (Segunda_Letra == sopa_de_letras[y + 1][x + 1])) {
                return true;
            }

            if (Abajo && Izquierda && (Segunda_Letra == sopa_de_letras[y + 1][x - 1])) {
                return true;
            }

            if (Derecha && (Segunda_Letra == sopa_de_letras[y][x + 1])) {
                return true;
            }

            if (Izquierda && (Segunda_Letra == sopa_de_letras[y][x - 1])) {
                return true;
            }
        }
        return false;
    }
    public static boolean buscar_ultima_posicion(char[][] sopa_de_letras, int x, int y, String palabra_encontrada) {

        boolean Abajo = (y + 1) <= sopa_de_letras.length;
        boolean Derecha = (x + 1) <= sopa_de_letras.length;
        boolean Arriba = (y - 1) >= 0;
        boolean Izquierda = (x - 1) >= 0;

        char Penultima_Letra = palabra_encontrada.toUpperCase().charAt(palabra_encontrada.length() - 2);

        if (sopa_de_letras[y][x] == palabra_encontrada.toUpperCase().charAt(palabra_encontrada.length() - 1)) {

            if (Arriba && Penultima_Letra == sopa_de_letras[y - 1][x]){
                return true;
            }

            if (Arriba && Derecha && Penultima_Letra == sopa_de_letras[y - 1][x + 1]){
                return true;
            }

            if (Arriba && Izquierda && Penultima_Letra == sopa_de_letras[y - 1][x - 1]){
                return true;
            }

            if (Abajo && Penultima_Letra == sopa_de_letras[y + 1][x]) {
                return true;
            }

            if (Abajo && Derecha && Penultima_Letra == sopa_de_letras[y + 1][x + 1]) {
                return true;
            }

            if (Abajo && Izquierda && Penultima_Letra == sopa_de_letras[y + 1][x - 1]) {
                return true;
            }

            if (Derecha && Penultima_Letra == sopa_de_letras[y][x + 1]) {
                return true;
            }

            if (Izquierda && Penultima_Letra == sopa_de_letras[y][x - 1]) {
                return true;
            }
        }
        return false;
    }
}
