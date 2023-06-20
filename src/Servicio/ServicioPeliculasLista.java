package Servicio;

import Dominio.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class ServicioPeliculasLista implements IServicioPeliculas {

    private final List<Pelicula> peliculas;

    public ServicioPeliculasLista(){
        this.peliculas = new ArrayList<>();
    }

    @Override
    public void listarPeliculas() {
        System.out.println("Listado de peliculas ... ");
        peliculas.forEach(System.out::println);
    }

    @Override
    public void agregarPelicula(Pelicula pelicula) {
        peliculas.add(pelicula);
        System.out.println("Se agrego la pelicula " + pelicula);
    }

    @Override
    public void buscarPelicula(Pelicula pelicula) {
        //regresa el indice de la pelicula encontrada en la lista
        var indice = peliculas.indexOf(pelicula);
        if (indice == -1)
            System.out.println("La pelicula " + pelicula + " no ha sido encontrada en el registro");
        else
            System.out.println("Pelicula encontrada en el indice : " + indice);

    }


    public static void main(String[] args) {
        var pelicula1 = new Pelicula("Batman");
        var pelicula2 = new Pelicula("Superman");

        //creamos el servicio
        IServicioPeliculas servicioPeliculas = new ServicioPeliculasLista();
        //Agregamos las peliculas a la lista
        servicioPeliculas.agregarPelicula(pelicula1);
        servicioPeliculas.agregarPelicula(pelicula2);
        //listamos las peliculas
        servicioPeliculas.listarPeliculas();

        //Buscado de una pelicula
        servicioPeliculas.buscarPelicula(pelicula2);
    }
}
