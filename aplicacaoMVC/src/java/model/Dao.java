package model;

import java.util.ArrayList;

public interface Dao<T> {
    
   public T get(int id);   
   public ArrayList<T> getAll();
   public void insert(T t);
   public void update(T t);
   public void delete(int id);
}
