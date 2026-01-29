package AD.services;


import AD.model.Actor;
import AD.repository.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServices {
    private final ActorRepository actorRepo;

    public ActorServices(ActorRepository actorRepo) {
        this.actorRepo = actorRepo;
    }

    public List<Actor> findAll() {
        return actorRepo.findAll();
    }


    public Optional<Actor> findById(Long id) {
        return actorRepo.findById(id);
    }

    public Actor save(Actor actor) {
        return actorRepo.save(actor);
    }

    public boolean existsById(Long id) {
        return actorRepo.existsById(id);
    }

    public void deleteById(Long id) {
        actorRepo.deleteById(id);
    }

    public void deleteAll() {
        actorRepo.deleteAll();
    }
}
