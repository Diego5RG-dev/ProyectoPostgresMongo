package AD;


import AD.model.Actor;
import AD.model.Pelicula;
import AD.services.ConexionService;
import AD.services.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Secuencia {

    private final ConexionService conexionSQL;

    private final PeliculaService peliculaService;

    @Autowired
    public Secuencia(ConexionService conexionSQL, PeliculaService peliculaService) {
        this.conexionSQL = conexionSQL;
        this.peliculaService = peliculaService;
    }

    public void executar() {


        List<Actor> actores = new ArrayList<>();


        // --- NUEVA PELÍCULA: Iron Man ---
        List<Actor> actoresIronMan = new ArrayList<>();

        Actor act1 = new Actor();
        act1.setNome("Robert");
        act1.setApelidos("Downey Jr.");
        act1.setNacionalidade("USA");

        Actor act2 = new Actor();
        act2.setNome("Gwyneth");
        act2.setApelidos("Paltrow");
        act2.setNacionalidade("USA");

        actoresIronMan.add(act1);
        actoresIronMan.add(act2);

        Pelicula pelicula1 = new Pelicula();
        pelicula1.setTitulo("Iron Man");
        pelicula1.setXenero("Acción/Sci-Fi");
        pelicula1.setAno(2008);
        pelicula1.setActores(actoresIronMan);

        // --- NUEVA PELÍCULA: The Avengers ---
        List<Actor> actoresAvengers = new ArrayList<>();

        Actor act3 = new Actor();
        act3.setNome("Chris");
        act3.setApelidos("Evans");
        act3.setNacionalidade("USA");

        Actor act4 = new Actor();
        act4.setNome("Scarlett");
        act4.setApelidos("Johansson");
        act4.setNacionalidade("USA");

        Actor act11 = new Actor();
        act11.setNome("Mark");
        act11.setApelidos("Ruffalo");
        act11.setNacionalidade("USA");

        actoresAvengers.add(act3);
        actoresAvengers.add(act4);
        actoresAvengers.add(act11);

        Pelicula pelicula2 = new Pelicula();
        pelicula2.setTitulo("The Avengers");
        pelicula2.setXenero("Acción");
        pelicula2.setAno(2012);
        pelicula2.setActores(actoresAvengers);

        System.out.println(">>> Guardando películas en PostgreSQL...");

        // ¡IMPORTANTE! Asignar el resultado a la variable para capturar el ID
        pelicula1 = conexionSQL.crearPelicula(pelicula1);
        System.out.println(">>> Película 1 creada con ID: " + (pelicula1 != null ? pelicula1.getId() : "NULL"));

        pelicula2 = conexionSQL.crearPelicula(pelicula2);
        System.out.println(">>> Película 2 creada con ID: " + (pelicula2 != null ? pelicula2.getId() : "NULL"));

        // 4. RECUPERAR DE SQL
        Pelicula p1Recuperada = conexionSQL.getPeliculaById(pelicula1.getId());
        System.out.println("Pelicula recuperada: " + p1Recuperada.getTitulo());

        // Recuperar por título
        List<Pelicula> p2Recuperada = conexionSQL.getPeliculaByTitulo("The Avengers");
        System.out.println("Pelicula recuperada: " + p2Recuperada.get(0).getTitulo());


        // 5. GUARDAR EN MONGO
        System.out.println(">>> Guardando en MongoDB...");
        peliculaService.crearActualizarPeliculaConActores(p1Recuperada);
        peliculaService.crearActualizarPeliculaConActores(p2Recuperada.get(0));

        // 6. GUARDAR PELÍCULAS A UN JSON
        System.out.println(">>> Guardando películas desde JSON en MongoDB...");
        peliculaService.exportarPeliculasAJson();

    }
}