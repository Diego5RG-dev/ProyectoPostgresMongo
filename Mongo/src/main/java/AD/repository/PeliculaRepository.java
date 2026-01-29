package AD.repository;

import AD.model.Pelicula;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends MongoRepository<Pelicula, Long> {
}
