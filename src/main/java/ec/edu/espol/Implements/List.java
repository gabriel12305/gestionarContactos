package ec.edu.espol.Implements;

import java.io.Serializable;

public interface List<E> extends Iterable<E>, Serializable{
    
    public int size();

    public boolean isEmpty();

    public boolean contains(E e);

    public boolean add(int i, E e);

    public boolean addLast(E e);

    public E remove(int i);

    public void clear();

    public E get(int index);

    public boolean set(int index, E element);
    
    public boolean removeByElement(E element);

}
