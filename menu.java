import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class menu {

    private static Scanner input = new Scanner(System.in);
    private static Random random = new Random();
    //Concepto sacado de https://youtu.be/MqBbR8TyxW4?t=715

    //David Alejandro Arismendy Velasquez

    public static void main(String[] args) {
        ArrayList<String> palabras_a_encontrar = new ArrayList<>();

        boolean Menu = true;
        int[] caracteristicas_sopa;

        while (Menu) {
            System.out.println(" ");
            System.out.println("•´¯`•» Generador de sopas de letras «•´¯`•");
            System.out.println("Bienvenido al menu para crear su sopa de letras, digite su opcion respectivamente");
            System.out.println("1. Elegir un nivel de dificultad predeterminado");
            System.out.println("2. Crear una sopa de letras personalizada");
            System.out.println("3. Consultar puntajes");
            System.out.println("4. Salir del menu");
            int eleccion_menu = input.nextInt();

            switch (eleccion_menu) {

                case 1:
                    caracteristicas_sopa = sopa_predeterminada();
                    palabras_a_encontrar = seleccion_categorias(caracteristicas_sopa[1]);

                    //Entra a la clase sopa con las variables dadas
                    //{size_sopa, cantidad_palabras, orientacion_palabras}

                    sopa.constructor_sopa(caracteristicas_sopa[0], caracteristicas_sopa[1], caracteristicas_sopa[2], palabras_a_encontrar);
                    break;
                case 2:
                    caracteristicas_sopa = sopa_personalizada();
                    palabras_a_encontrar = seleccion_categorias(caracteristicas_sopa[1]);

                    sopa.constructor_sopa(caracteristicas_sopa[0], caracteristicas_sopa[1], caracteristicas_sopa[2], palabras_a_encontrar);
                    break;
                case 3:
                    int puntos = 0;

                    System.out.println("Su puntaje total hasta el momento es:");
                    System.out.println(puntos + juego.puntos);

                    //Saca los puntos de la clase juego

                    break;
                case 4:
                    System.out.println("Ha elegido salir del menu");
                    Menu = false; //Rompe el ciclo si quiere salir
                    break;

                default:
                    System.out.println("Opcion no existente: " + eleccion_menu);
            }
        }
    }

    public static int[] sopa_predeterminada() {

        //Funcion para determinar el nivel de dificultad que el usuario desea

        System.out.println("Ha elegido utilizar un nivel de dificultad predeterminado, ahora elija el nivel:");
        System.out.println("1. Facil: ");
        System.out.println("   Cuadricula de 10x10, 5-10 palabras de 3-5 letras, colocacion horizontal o vertical");
        System.out.println("2. Medio:");
        System.out.println("   Cuadricula de 15x15, 10-15 palabras de 6-8 letras, colocacion en todas las direcciones excepto inversa");
        System.out.println("3. Dificil:");
        System.out.println("   Cuadricula de 20x20, 15-20 palabras de 9+ letras, colocacion en todas las direcciones incluyendo inversa");
        int nivel_dificultad = input.nextInt();

        int[] caracteristicas_sopa; //Array para poder sacar multiples resultados de esta funcion

        switch (nivel_dificultad) {
            case 1:
                caracteristicas_sopa = new int[]{1, 1, 1};

                //Debido a que el codigo esta armado para poder ser ajustable en cada parametro, elegir niveles
                //simplemente significa tener 3 parametros iguales

                return caracteristicas_sopa;

            case 2:
                caracteristicas_sopa = new int[]{2, 2, 2};
                return caracteristicas_sopa;

            case 3:
                caracteristicas_sopa = new int[]{3, 3, 3};
                return caracteristicas_sopa;

            default:
                return new int[]{-1, -1, -1};

                //Si el nivel de dificultad ingresado por el usuario no existe en las opciones, la funcion devuelve
                // multiples -1 para dejar en claro dentro de la funcion que el usuario cometio un error

        }
    }

    public static int[] sopa_personalizada() {

        //Funcion para determinar cada parametro para construir la sopa de letras

        System.out.println("Ha elegido crear una sopa de letras personalizada:");
        System.out.println("  ¿De que tamaño quiere crear la sopa de letras?");
        System.out.println("  1. 10x10 ");
        System.out.println("  2. 15x15 ");
        System.out.println("  3. 20x20 ");

        int size_sopa = input.nextInt();

        System.out.println("  ¿Cuantas palabras desea añadir en la sopa de letras?:");
        System.out.println("  1. 5-10 (3-5 letras)");
        System.out.println("  2. 10-15 (6-8 letras)");
        System.out.println("  3. 15-20 (9+ letras)");
        int cantidad_palabras = input.nextInt();

        System.out.println("  ¿Que colocacion quiere?:");
        System.out.println("  1. Horizontal o vertical ");
        System.out.println("  2. Todas las direcciones excepto inversa ");
        System.out.println("  3. Todas las direcciones incluyendo inversa ");
        int orientacion_palabras = input.nextInt();

        //Las opciones ingresadas se meten en un array para poder devolverlas al menu

        int[] caracteristicas_sopa = {size_sopa, cantidad_palabras, orientacion_palabras};

        if (caracteristicas_sopa[0] >= 1 && caracteristicas_sopa[0] <= 3 &&
                caracteristicas_sopa[1] >= 1 && caracteristicas_sopa[1] <= 3 &&
                caracteristicas_sopa[2] >= 1 && caracteristicas_sopa[2] <= 3) {

            //Este if revisa si las opciones ingresadas por el usuario estan dentro de las dadas, entre 1 a 3.

            return caracteristicas_sopa;
        } else {
            return new int[]{-1, -1, -1};
        }
    }

    public static ArrayList<String> seleccion_categorias(int cantidad_palabras) {
        ArrayList<String> palabras_a_encontrar = new ArrayList<>();
        String [] palabras = new String[]{};

        System.out.println("  ¿De que categoria desea que sean las palabras de la sopa de letras?:");
        System.out.println("  1. Naturaleza ");
        System.out.println("  2. Tecnologia ");
        System.out.println("  3. Gimnasio ");

        int categoria_palabras = input.nextInt();

        switch (categoria_palabras){
            case 0:
                palabras_a_encontrar.add("HOLA");
                break;

                //Caso extra para comprobar el codigo

            case 1:
                if (cantidad_palabras == 1) { //Dependiendo de la cantidad de palabras, se elije la cantidad de letras que tiene cada una
                    palabras = new String[]{"Sol", "Mar", "Rio", "Flor", "Aire",
                            "Arbol", "Vida", "Luna", "Lago", "Playa"};
                    //Array del maximo de 10 palabras posibles para usar en esta configuracion

                    while (palabras_a_encontrar.size() < random.nextInt(6) + 5){//Se elije la cantidad de palabras por un random

                        //Como el random va de 0 a 5, si se le suma otros 5 podremos tener las opciones
                        //5,6,7,8,9 y 10. Esto se utiliza en las demas configuraciones tambien

                        String palabra_seleccionada = palabras[random.nextInt(palabras.length)];

                        //Se elije una palabra del array por un random que tiene como maximo el tamaño del array

                        if (!palabras_a_encontrar.contains(palabra_seleccionada)) {
                            //Si la lista de palabras a encontrar no contiene la seleccionada,
                            //entonces se añade
                            palabras_a_encontrar.add(palabra_seleccionada);
                        }
                    }
                } else if (cantidad_palabras == 2) {
                    palabras = new String[]{"Montaña", "Cascada", "Pradera", "Volcan", "Desierto",
                            "Glaciar", "Torrente", "Orquidea", "Pastizal", "Neblina",
                            "Arbusto", "Marejada", "Catarata", "Biotopo", "Planta"};

                    while (palabras_a_encontrar.size() < random.nextInt(6) + 10) {
                        String palabra_seleccionada = palabras[random.nextInt(palabras.length)];
                        if (!palabras_a_encontrar.contains(palabra_seleccionada)) {
                            palabras_a_encontrar.add(palabra_seleccionada);
                        }
                    }
                } else if (cantidad_palabras == 3) {
                    palabras = new String[]{"Biodiversidad", "Microclima", "Selvatico", "Vegetacion", "Biotecnologia",
                            "Geologico", "Ecosistema", "Temperatura", "Conservacion", "Hidroelectrica",
                            "Fototropismo", "Fisiografico", "Preservacion", "Hidrografia", "Bioacustica",
                            "Meteorologico", "Terrestre", "Oceanografico", "Hidrologico", "Naturaleza"};

                    while (palabras_a_encontrar.size() < random.nextInt(6) + 15) {
                        String palabra_seleccionada = palabras[random.nextInt(palabras.length)];
                        if (!palabras_a_encontrar.contains(palabra_seleccionada)) {
                            palabras_a_encontrar.add(palabra_seleccionada);
                        }
                    }
                }
                break;
            case 2:
                if (cantidad_palabras == 1) {
                    palabras = new String[]{"App", "Web", "API", "WiFi", "USB",
                            "Email", "Cloud", "Java", "HTML", "Python"};

                    while (palabras_a_encontrar.size() < random.nextInt(6) + 5) {
                        String palabra_seleccionada = palabras[random.nextInt(palabras.length)];
                        if (!palabras_a_encontrar.contains(palabra_seleccionada)) {
                            palabras_a_encontrar.add(palabra_seleccionada);
                        }
                    }
                } else if (cantidad_palabras == 2) {
                    palabras = new String[]{"Internet", "Pantalla", "Bluetooth", "Software", "Teclado",
                            "Cámara", "Impresora", "Satélite", "Memoria", "Altavoz",
                            "Digital", "Programa", "Servidor", "Monitor", "Batería"};

                    while (palabras_a_encontrar.size() < random.nextInt(6) + 10) {
                        String palabra_seleccionada = palabras[random.nextInt(palabras.length)];
                        if (!palabras_a_encontrar.contains(palabra_seleccionada)) {
                            palabras_a_encontrar.add(palabra_seleccionada);
                        }
                    }
                } else if (cantidad_palabras == 3) {
                    palabras = new String[]{"Computadora", "Internet", "Televisor", "Electrónica", "Automatización",
                            "Innovación", "Cibernética", "Programación", "Dispositivo", "Algoritmo",
                            "Robotización", "Telecomunicaciones", "Inteligencia", "Microprocesador", "Integración",
                            "Biometría", "Nanotecnología", "Sistematización", "Transistor", "Criptografía"};

                    while (palabras_a_encontrar.size() < random.nextInt(6) + 15) {
                        String palabra_seleccionada = palabras[random.nextInt(palabras.length)];
                        if (!palabras_a_encontrar.contains(palabra_seleccionada)) {
                            palabras_a_encontrar.add(palabra_seleccionada);
                        }
                    }
                }
                break;
            case 3:
                if (cantidad_palabras == 1) {
                    palabras = new String[]{"Gym", "Peso", "Flex", "Barra", "Sudor",
                            "Cinta", "Salto", "Manos", "Plank", "Banca"};

                    while (palabras_a_encontrar.size() < random.nextInt(6) + 5) {
                        String palabra_seleccionada = palabras[random.nextInt(palabras.length)];
                        if (!palabras_a_encontrar.contains(palabra_seleccionada)) {
                            palabras_a_encontrar.add(palabra_seleccionada);
                        }
                    }
                } else if (cantidad_palabras == 2) {
                    palabras = new String[]{"Entrenar", "Muscular", "Rutina", "Pesas", "Cardio",
                            "Abdomen", "Flexión", "Estirar", "Barra", "Fitness",
                            "Zumba", "Elíptica", "Saltar", "Brazos", "Prensa"};

                    while (palabras_a_encontrar.size() < random.nextInt(6) + 10) {
                        String palabra_seleccionada = palabras[random.nextInt(palabras.length)];
                        if (!palabras_a_encontrar.contains(palabra_seleccionada)) {
                            palabras_a_encontrar.add(palabra_seleccionada);
                        }
                    }
                } else if (cantidad_palabras == 3) {
                    palabras = new String[]{"Musculación", "Entrenador", "Flexibilidad", "Cardiovascular", "Calistenia",
                            "Resistencia", "Elípticas", "Calentamiento", "Fortalecer", "Abdominales",
                            "Planchado", "Levantador", "Estiramientos", "Mancuernas", "Ejercitador",
                            "Tonificación", "Acondicionamiento", "Flexionando", "Esprintador", "Hidratación"};

                    while (palabras_a_encontrar.size() < random.nextInt(6) + 15) {
                        String palabra_seleccionada = palabras[random.nextInt(palabras.length)];
                        if (!palabras_a_encontrar.contains(palabra_seleccionada)) {
                            palabras_a_encontrar.add(palabra_seleccionada);
                        }
                    }
                }
                break;

            default:
                System.out.println("Opcion no existente: " + categoria_palabras);
                break;
        }

        return palabras_a_encontrar;
    }
}
