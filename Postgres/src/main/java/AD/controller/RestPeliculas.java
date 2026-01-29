package AD.controller;


import AD.model.Pelicula;
import AD.services.ActorServices;
import AD.services.PeliculaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestPeliculas.MAPPING)
public class RestPeliculas {

    public static final String MAPPING = "/postgres/peliculas";

    @Autowired
    private PeliculaServices peliculaService;

    @Autowired
    private ActorServices actorService;

    @GetMapping
    public List<Pelicula> getAll() {
        return peliculaService.obtenerTodasPeliculas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> getById(@PathVariable Long id) {
        return peliculaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("titulo/{titulo}")
    public ResponseEntity<List<Pelicula>> getByTitulo(@PathVariable String titulo){
        List<Pelicula> p = peliculaService.obtenerPeliculaTitulo(titulo);
        if (p == null || p.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else return ResponseEntity.ok(p);

    }

    @PostMapping
    public ResponseEntity<Pelicula> create(@RequestBody Pelicula pelicula) {
        Pelicula guardada = peliculaService.save(pelicula);
        return ResponseEntity.ok(guardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> update(@PathVariable Long id,
                                           @RequestBody Pelicula datos) {


        var peliculaOptional = peliculaService.findById(id);
        if (peliculaOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Pelicula peliculaToUpdate = peliculaOptional.get();
        peliculaToUpdate.setTitulo(datos.getTitulo());
        peliculaToUpdate.setXenero(datos.getXenero());
        Pelicula updatedPelicula = peliculaService.save(peliculaToUpdate);
        return ResponseEntity.ok(updatedPelicula);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!peliculaService.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        peliculaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        peliculaService.deleteAll();
        return ResponseEntity.noContent().build();
    }

}