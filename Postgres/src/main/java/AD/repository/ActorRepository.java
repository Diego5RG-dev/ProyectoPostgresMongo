package AD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import AD.model.Actor;

import java.util.List;

@Repository
public interface ActorRepository  extends JpaRepository<Actor, Long> {
    List<Actor> findByNome(String nome);
    List<Actor> findByNacionalidade(String nacionalidade);
}
