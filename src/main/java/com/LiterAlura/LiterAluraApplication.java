package com.LiterAlura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    private Conversor conversor = new Conversor();

    public static void main(String[] args) {
        SpringApplication.run(LiterAluraApplication.class, args);
    }

    @Override
    public void run(String... args) {
        mostrarMenu();
    }

    private void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("""
                    
                    LITERALURA
                    1 - Buscar libro por título
                    2 - Listar libros guardados
                    3 - Listar autores guardados
                    4 - Lista de autores vivos por año
                    5 - Mostrar libros por idioma
                    0 - Salir
                    """);

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.println("Escriba el nombre del libro: ");
                    String titulo = scanner.nextLine();

                    String url = "https://gutendex.com/books/?search=" + titulo.replace(" ", "%20");

                    ConsumoAPI consumoAPI = new ConsumoAPI();
                    String json = consumoAPI.obtenerDatos(url);
            
                    Datos datos = conversor.obtenerDatos(json, Datos.class);

                    if (datos != null && datos.getResultados() != null && !datos.getResultados().isEmpty()) {
                        DatosLibro libroAPI = datos.getResultados().get(0);

                        Libro libro = new Libro(
                            libroAPI.getTitulo(),
                            libroAPI.getAutores() != null && !libroAPI.getAutores().isEmpty() ? libroAPI.getAutores().get(0).getNombre() : "Desconocido",
                            libroAPI.getIdiomas() != null && !libroAPI.getIdiomas().isEmpty() ? libroAPI.getIdiomas().get(0) : "Desconocido",
                            libroAPI.getDescargas()
                        );
                        Autor autor = new Autor(
                            libroAPI.getAutores().get(0).getNombre(),
                            libroAPI.getAutores().get(0).getNacimiento(),
                            libroAPI.getAutores().get(0).getFallecimiento()
                        );
                        autorRepository.save(autor);

                        libroRepository.save(libro);
                        System.out.println("Libro guardado:");
                        System.out.println(libro);
                    } else {
                        System.out.println("No se encontraron libros para esa búsqueda.");
                    }
                }
                case 2 -> {
                    var libros = libroRepository.findAll();
                    if (libros.isEmpty()) {
                        System.out.println("No hay libros guardados.");
                    } else {
                        System.out.println("Lista de libros guardados:");
                        libros.forEach(System.out::println);
                    }
                }
                case 3 -> {
                    var autores = autorRepository.findAll();
                    if (autores.isEmpty()) {
                        System.out.println("No hay autores guardados.");
                    } else {
                        System.out.println("📚 Lista de autores:");
                        autores.forEach(System.out::println);
                    }
                }
                case 4 -> {
                    System.out.println("Ingrese el año para filtrar autores vivos:");
                    int año = scanner.nextInt();
                    scanner.nextLine();

                    var autoresVivos = autorRepository.findAll().stream()
                            .filter(a -> (a.getNacimiento() != null && a.getNacimiento() <= año) &&
                                        (a.getFallecimiento() == null || a.getFallecimiento() >= año))
                            .toList();

                    if (autoresVivos.isEmpty()) {
                        System.out.println("No hay autores vivos en ese año.");
                    } else {
                        System.out.println(" Autores vivos en " + año + ":");
                        autoresVivos.forEach(System.out::println);
                    }
                }
                case 5 -> {
                    System.out.println("Ingrese el idioma para filtrar libros:");
                    String idioma = scanner.nextLine();

                    var librosPorIdioma = libroRepository.findAll().stream()
                            .filter(l -> l.getIdioma() != null && l.getIdioma().equalsIgnoreCase(idioma))
                            .toList();

                    if (librosPorIdioma.isEmpty()) {
                        System.out.println("No hay libros en ese idioma.");
                    } else {
                        System.out.println("Libros en " + idioma + ":");
                        librosPorIdioma.forEach(System.out::println);
                    }
                }
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida");
            }
        }
        scanner.close();
    }
}
