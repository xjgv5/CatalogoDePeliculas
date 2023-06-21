package Presentacion;

import Dominio.Pelicula;
import Servicio.IServicioPeliculas;
import Servicio.ServicioPeliculasArchivo;
import Servicio.ServicioPeliculasLista;

import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class CatalogoPeliculasApp {
    public static void main(String[] args) {
        var salir = false;
        var consola = new Scanner(System.in);

        //implementacion del servicio basado en la memoria
        //IServicioPeliculas servicioPeliculas = new ServicioPeliculasLista();
        IServicioPeliculas servicioPeliculas = new ServicioPeliculasArchivo();

        while (!salir){
            try{
                mostrarMenu();
                salir = ejecutarOpciones(consola, servicioPeliculas);
            } catch (Exception e){
                System.out.println("Ha ocurrido un error : " + e.getMessage());
            }
            System.out.println();
        } //fin while

    }
    private static void mostrarMenu(){
        System.out.print("""
                **** Catalogo de peliculas ****
                1. Agregar pelicula
                2. Listar peliculas
                3. Buscar pelicula
                4. Salir
                Elige una opcion:  """);
    }

    private static boolean ejecutarOpciones(Scanner consola, IServicioPeliculas servicioPeliculas){
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;
        switch (opcion){
            case 1 -> {
                System.out.println("Introduce el nombre de la pelicula");
                var nombrePelicula = consola.nextLine();
                servicioPeliculas.agregarPelicula(new Pelicula(nombrePelicula));
            }
            case 2 -> servicioPeliculas.listarPeliculas();
            case 3 -> {
                System.out.print("Ingresa el nombre de la pelicula a buscar: ");
                var peliculaBuscar = consola.nextLine();
                servicioPeliculas.buscarPelicula(new Pelicula(peliculaBuscar));
            }
            case 4 -> {
                System.out.println("Hasta pronto ...");
                salir = true;
            }
            default -> System.out.println("Opcion no reconocida " + opcion);
        }
        return salir;
    }

}