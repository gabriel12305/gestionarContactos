package ec.edu.espol.Implements;
import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {
    private static final long serialVersionUID = 006L;
    private E[] elements;
    private int capacity=1;
    private int effectiveSize;


    @SuppressWarnings("unchecked")
    public ArrayList(Class<E> type){
        elements = (E[]) Array.newInstance(type, capacity);
        effectiveSize=0;
    }

    @Override
    public int size() {
        return effectiveSize;
    }

    @Override
    public boolean isEmpty() {
        return effectiveSize==0;        
    }

    @Override
    public boolean contains(E e) {
        if(e!=null){
            for(E element: elements){
                if(e.equals(element)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean add(int index, E element) {
        if(element==null || index<0 || index> effectiveSize){
            System.out.println("Error: El indice esta fuera del rango.");
            return false;
        }
        else if (isFull()) {
            addCapacity();
        }
        for(int i = effectiveSize - 1; i >= index; i--){
            elements[i + 1] = elements[i];
        }
        elements[index]=element;
        effectiveSize++;
        return true;
    }

    @Override
    public boolean addLast(E e) {
        if(e==null){
            return false;
        }else if(isFull()){
            addCapacity();
        }
        elements[effectiveSize]=e;
        effectiveSize++;
        return true;
    }

    private boolean isFull(){
        return effectiveSize==capacity;
    }

    private void addCapacity(){
        @SuppressWarnings("unchecked")
        E[] tmp = (E[]) Array.newInstance(elements.getClass().getComponentType(), capacity + (int) Math.ceil(capacity/2.0));
        for(int i=0; i<capacity; i++){
            tmp[i]= elements[i];
        }
        elements= tmp;
        capacity=capacity+(int) Math.ceil(capacity/2.0);
    }

    @Override
    public E remove(int index) {
        if(index<0 || index>=effectiveSize){
            System.out.println("Error: El indice esta fuera del rango.");
            return null;
        }
        E elementremove= elements[index];
        for(int i=index; i<effectiveSize-1; i++){
            elements[i]=elements[i+1];
        }
        elements[effectiveSize-1]=null;
        effectiveSize--;
        return elementremove;
    }

    @Override
    public void clear() {
        for(int i=0; i<effectiveSize; i++){
            elements[i]=null;
        }
        effectiveSize=0;
    }

    @Override
    public E get(int index) {
        if(index<0 || index>=effectiveSize){
            System.out.println("Error: El indice esta fuera del rango.");
            return null;
        }
        return elements[index];
    }

    @Override
    public boolean set(int index, E element) {
        if(index<0 || index>=effectiveSize){
            System.out.println("Error: Indice fuera del rango.");
            return false;
        }
        elements[index]=element;
        return true;
    }

    @Override
    public boolean removeByElement(E element) {
        if (element == null) {
            System.out.println("Error: El elemento que deseas remover no existe.");
            return false;
        }

        for (int i = 0; i < effectiveSize; i++) {
            if (elements[i].equals(element)) {
                for (int j = i; j < effectiveSize - 1; j++) {
                    elements[j] = elements[j + 1];
                }
                elements[effectiveSize - 1] = null;
                effectiveSize--;
                return true;
            }
        }
        return false; 
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>(){
            int cursor=0;
            @Override
            public boolean hasNext() {
                return cursor<effectiveSize;
            }
            @Override
            public E next() {
                return elements[cursor++];
            }
        };
        return it;
    }

    public void order(Comparator<E> cmp){
        for (int i = 0; i < effectiveSize - 1; i++) {
            for (int j = 0; j < effectiveSize - i - 1; j++) {
                if (cmp.compare(elements[j], elements[j + 1]) > 0) {
                    E temp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                }
            }
        }
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]"; 
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < effectiveSize; i++) {
            sb.append(elements[i]);
            if (i < effectiveSize - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
