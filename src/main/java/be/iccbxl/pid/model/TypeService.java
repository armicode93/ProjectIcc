package be.iccbxl.pid.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TypeService {
    @Autowired
    private TypeRepository typeRepository;

    public List<Type> getAllType(){
        List<Type> types = new ArrayList<>();

        typeRepository.findAll().forEach(types::add);

        return types;
    }

    public Type get(String id) {
        Long indice = (long) Integer.parseInt(id);
        Optional<Type> type = typeRepository.findById(indice);

        return type.isPresent() ? type.get() : null;
    }

    public void add(Type type){
        typeRepository.save(type);
    }
    public void update(String id, Type type)  {
        typeRepository.save(type);
    }

    public void delete(String id) {
        Long indice = (long) Integer.parseInt(id);

        typeRepository.deleteById(indice);
    }


}
