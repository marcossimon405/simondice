package simondice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SimonDice {

    private static List<String> secuenciaSimon = new ArrayList<>();
    private static List<String> secuenciaUsuario = new ArrayList<>();
    private static Random random = new Random();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("¡Bienvenido a Simon Dice!");

        while (true) {
            agregarColorAleatorio();
            mostrarSecuencia();

            if (!verificarEntradaUsuario()) {
                System.out.println("¡Has perdido! Puntuación: " + (secuenciaSimon.size() - 1));
                break;
            }

            System.out.println("¡Correcto! ¡Siguiente nivel!");
        }
    }

    private static void agregarColorAleatorio() {
        List<String> colores = Arrays.asList("ROJO", "VERDE", "AZUL", "AMARILLO");
        int indiceColor = random.nextInt(colores.size());
        secuenciaSimon.add(colores.get(indiceColor));
    }

    private static void mostrarSecuencia() {
        System.out.println("Secuencia de Simon: " + secuenciaSimon);
        try {
            Thread.sleep(1000);  // Espera para que el usuario pueda ver la secuencia
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        limpiarConsola();
    }

    private static boolean verificarEntradaUsuario() {
        System.out.println("Tu turno. Ingresa la secuencia (ej. ROJO VERDE AZUL):");
        String entradaUsuario = scanner.nextLine().trim().toUpperCase();
        secuenciaUsuario = Arrays.asList(entradaUsuario.split("\\s+"));

        if (!secuenciaSimon.equals(secuenciaUsuario.subList(0, secuenciaSimon.size()))) {
            return false;
        }

        return true;
    }

    private static void limpiarConsola() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
