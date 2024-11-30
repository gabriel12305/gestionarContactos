package ec.edu.espol.Implements;

import java.util.Comparator;
import java.util.Iterator;

public class CircleLinkedList<E> implements List<E> {
    private static final long serialVersionUID = 005L;
    DoublyCircularNode<E> header;
    int size;

    public DoublyCircularNode<E> getHeader() {
        return header;
    }

    public int getSize() {
        return size;
    }

    public void setHeader(DoublyCircularNode<E> header) {
        this.header = header;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(E e) {
        if(e!=null && size>0){
            DoublyCircularNode<E> current=header;
            for(int i=0; i<size; i++){
                if(current.getContent().equals(e)){
                    return true;
                }
                current=current.getNext();
            }
        }
        return false;
    }
    
    @Override
    public boolean add(int index,  E value){
        if (value == null) {
            System.out.println("Error: No se aceptan valores nulos.");
            return false;
        }else if(index > size){
            System.out.println("Error: El indice esta fuera del rango.");
            return false;
        }
        DoublyCircularNode<E> nuevo = new DoublyCircularNode<>(value);
        if (header == null) {
            header = nuevo;
            nuevo.setNext(nuevo);
            nuevo.setPrevious(nuevo);
            size++;
            return true;
        }else if(index == 0){
            DoublyCircularNode<E> current = header;
            for (int i = 0; i < size - 1; i++) {
                current = current.getNext();
            }
            current.setNext(nuevo);
            nuevo.setPrevious(current);
            nuevo.setNext(header);
            header = nuevo;
            size++;
            return true;
 
        }else{
            DoublyCircularNode<E> current = header;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            current.getNext().setPrevious(nuevo);
            nuevo.setNext(current.getNext());
            current.setNext(nuevo);
            nuevo.setPrevious(current);
            size++;
            return true;
        }
    }

    @Override
    public boolean addLast(E value){
        if(value == null){
            System.out.println("Error: El indice esta fuera del rango.");
            return false;
        }
        DoublyCircularNode<E> nuevo = new DoublyCircularNode<>(value);
        if(header == null){
            header = nuevo;
            nuevo.setNext(nuevo);
            nuevo.setPrevious(nuevo);
            size++;
            return true;
        }else{
            nuevo.setPrevious(header.getPrevious());
            header.getPrevious().setNext(nuevo);
            nuevo.setNext(header);
            header.setPrevious(nuevo);
            size++;
            return true;
        }
    }
    @Override
    public E remove(int index){
        if (index >= size || index<0) {
            System.out.println("Error: El indice esta fuera del rango.");
            return null;
        }else if (size == 0) {
            System.out.println("Error: No hay elemento para remover.");
            return null;
        }
        DoublyCircularNode<E> current = header;
        if (index == 0) {
            for (int i = 0; i < size -1; i++) {
                current = current.getNext();
            }
            E valor = header.getContent();
            current.setNext(current.getNext().getNext());
            current.getNext().getNext().setPrevious(current);
            header = current.getNext();
            size--;
            return valor;
        }else{
            for (int i = 0; i < index-1; i++) {
                current = current.getNext();
            }
            E valor = current.getNext().getContent();
            current.getNext().getNext().setPrevious(current);
            current.setNext(current.getNext().getNext());
            size--;
            return valor;
        }
    }

    @Override
    public void clear(){
        header.setNext(null);
        header.setPrevious(null);
        size = 0;
    }
    @Override
    public E get(int index){
        if (index > size -1 || index<0) {
            System.out.println("Error: El indice esta fuera del rango.");
            return null;
        }
        DoublyCircularNode<E> current = header;
        if (index == 0) {
            return header.getContent();
           
        }else{
            for (int i = 0; i < index-1; i++) {
                current = current.getNext();
            }
            return current.getNext().getContent();
        }
    }
    @Override
    public boolean set(int index, E element){
        if (index > size -1) {
            System.out.println("Error: El indice esa fuera del rango.");
            return false;
        }else if(element==null){
            System.out.println("Error: No se aceptan valores nulos.");
            return false;
        }
        DoublyCircularNode<E> current = header;
        if (index == 0) {
            header.setContent(element);
            return true;
        }else{
            for (int i = 0; i < index-1; i++) {
                current = current.getNext();
            }
            current.getNext().setContent(element);
            return true;
        }
    }
    
    @Override
    public boolean removeByElement(E element){
        if (element == null) {
            System.out.println("Error: No se aceptan valores nulo.");
            return false;
        }else if(contains(element) == false){
            System.out.println("Error: El elemento que deseas remover no existe.");
            return false;
        }
 
        DoublyCircularNode<E> current = getNodo(element);
        if (current == header) {
            if (size == 1) {
                header = null;
            } else {
                header = header.getNext();
                current.getPrevious().setNext(current.getNext());
                current.getNext().setPrevious(current.getPrevious());
            }
        } else {
            current.getPrevious().setNext(current.getNext());
            current.getNext().setPrevious(current.getPrevious());
        }
        size--;
        return true;
    }

    private DoublyCircularNode<E> getNodo(E element){
        DoublyCircularNode<E> current = header;
        for (int i = 0; i < size; i++) {
            if (current.getContent() == element) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    public void imprimir(){
        DoublyCircularNode<E> current = header;
        for (int i = 0; i < size; i++) {
            System.out.println(current.getContent());
            current = current.getNext();
        }
    }
    
    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>(){
            int cursor = 0;
            DoublyCircularNode<E> current = header;
            
            @Override
            public boolean hasNext(){
                return cursor<size;
            }
            @Override
            public E next(){
                E valor = current.getContent();
                current = current.getNext();
                cursor++;
                return valor;
        
            }
        };
        return it;
    }
    
    public void order(Comparator<E> cmp) {
        if (header == null || size < 2) {
            return; // No es necesario ordenar si la lista está vacía o tiene un solo elemento
        }
    
        boolean swapped;
        do {
            swapped = false;
            DoublyCircularNode<E> current = header;
    
            for (int i = 0; i < size - 1; i++) {
                DoublyCircularNode<E> nextNode = current.getNext();
                if (cmp.compare(current.getContent(), nextNode.getContent()) > 0) {
                    // Intercambiar los contenidos de los nodos
                    E temp = current.getContent();
                    current.setContent(nextNode.getContent());
                    nextNode.setContent(temp);
                    swapped = true;
                }
                current = current.getNext();
            }
        } while (swapped);
    }
    
}
