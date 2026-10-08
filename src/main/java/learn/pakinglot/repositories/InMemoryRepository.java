package learn.pakinglot.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import learn.pakinglot.models.BaseModel;

public class InMemoryRepository<T extends BaseModel> {
    Map<Long, T> map;
    public static Long counter=0L;

    public InMemoryRepository() {
        map = new HashMap<>();
    }

    public void save(T item) {
        if(item.getId()==null) {
            item.setId(++counter);
        }

        if(item.getCreatedAt()==null) {
            item.setCreatedAt(new Date());
        }

        item.setUpdatedAt(new Date());

        map.put(counter, item);
    }

    public T findById(Long id) {
        return map.get(id);
    }

    public List<T> findAll() {
        return new ArrayList<>(map.values());
    }
}
