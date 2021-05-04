package application.demo.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TypeService {
    @Autowired
    private TypeRepository repository;

    public List<Type> getAllType(){
        List<Type> types = new ArrayList<>();

        repository.findAll().forEach(types::add);

        return types;
    }

    public Type getType(String id) {
        Long indice = (long) Integer.parseInt(id);
        Optional<Type> type = repository.findById(indice);

        return type.isPresent() ? type.get() : null;
    }

    public void add(Type type){
        repository.save(type);
    }
    public void update(String id, Type type)  {
        repository.save(type);
    }

    public void delete(String id) {
        Long indice = (long) Integer.parseInt(id);

        repository.deleteById(indice);
    }


}
