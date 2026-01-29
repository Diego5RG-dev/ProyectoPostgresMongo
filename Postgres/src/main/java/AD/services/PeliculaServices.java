package AD.services;

import AD.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import AD.model.Pelicula;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServices {
    private final PeliculaRepository peliRepo;

    @Autowired
    public PeliculaServices(PeliculaRepository peliRepo) {
        this.peliRepo = peliRepo;
    }


    public Pelicula save(Pelicula pelicula) {
        return peliRepo.save(pelicula);
    }

    public boolean existe(Long id) {
        return peliRepo.existsById(id);
    }

    public void delete(Long id) {
        peliRepo.deleteById(id);
    }

    public List<Pelicula> obtenerPeliculaTitulo(String titulo) {
        return peliRepo.findByTitulo(titulo);
    }

    public List<Pelicula> obtenerPeliculaXenero(String xenero) {
        return peliRepo.findByXenero(xenero);
    }

    public Optional<Pelicula> findById(Long id) {
        return peliRepo.findById(id);
    }

    public List<Pelicula> obtenerTodasPeliculas() {
        return peliRepo.findAll();
    }

    public void deleteAll() {
        peliRepo.deleteAll();
    }
}


