package dao;

import java.util.List;

public interface CRUD<E> {
    public List<E> getAll();
    public List<E> getAllByName(String name);


    public boolean create(E e);
    public boolean edit(int id,E e);
    public boolean delete(int id);
    public E findById(int id);
}
