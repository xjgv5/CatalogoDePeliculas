package Servicio;

import Dominio.Pelicula;

import java.io.*;

public class ServicioPeliculasArchivo implements IServicioPeliculas {

    private final String NOMBRE_ARCHIVO = "peliculas.txt";

    public ServicioPeliculasArchivo(){
        var archivo = new File(NOMBRE_ARCHIVO);
        try{
            //Si ya existe el archivo, no lo vuelve a crear
            if(archivo.exists()){
                System.out.println("El archivo ya ha sido creado");
            }
            else {
                var salida = new PrintWriter(new FileWriter(archivo));
                salida.close();
                System.out.println("Se ha creado el archivo");
            }
        } catch (Exception e){
            System.out.println("Oops! Ha ocurrido un error: " + e.getMessage());
        }
    }

    @Override
    public void listarPeliculas() {
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            System.out.println("Listado de peliculas");
            var entrada = new BufferedReader(new FileReader(archivo));
            //Leemos linea a linea el archivo
            String linea;
            linea = entrada.readLine();
            while(linea != null){
                var pelicula = new Pelicula(linea);
                System.out.println(pelicula);
                //se lee la siguiente linea
                linea = entrada.readLine();
            }
            entrada.close();
        } catch (Exception e){
            System.out.println("Oops! Ha ocurrido un error al leer el archivo: " + e.getMessage());
        }
    }

    @Override
    public void agregarPelicula(Pelicula pelicula) {
        var anexar = false;
        var archivo = new File(NOMBRE_ARCHIVO);
        try{
            //Revisamos si existe el archivo
            anexar = archivo.exists();
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            //agregamos la pelicula
            salida.println(pelicula);
            salida.close();
            System.out.println("Se agrego correctamente la pelicula al archivo !" );
        }catch (Exception e){
            System.out.println("Ocurrio un error al agregar la pelicula: " + e.getMessage());
        }
    }

    @Override
    public void buscarPelicula(Pelicula pelicula) {
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            //abrimos el archivo para la lectura
            var entrada = new BufferedReader(new FileReader(archivo));
            String lineaTexto;
            lineaTexto = entrada.readLine();
            var indice = 1;
            var encontrado = false;
            var peliculaBuscar = pelicula.getNombre();
            while (lineaTexto != null){
                if (peliculaBuscar != null && peliculaBuscar.equalsIgnoreCase(lineaTexto)){
                    encontrado = true;
                    break;
                }
                lineaTexto = entrada.readLine();
                indice++;
            }
            if (encontrado){
                System.out.println("La pelicula " + lineaTexto + " ha sido encontrada en el indice : " + indice);
            } else {
                System.out.println("La pelicula " + pelicula.getNombre() +  " no ha sido encontrada en nuestro archivo");
            }
            entrada.close();
        }catch (Exception e){
            System.out.println("Oops! Ha ocurrido un error ! " + e.getMessage());
        }
    }
}
