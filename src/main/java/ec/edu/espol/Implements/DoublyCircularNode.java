package ec.edu.espol.Implements;

import java.io.Serializable;

public class DoublyCircularNode<E> implements Serializable {
    private static final long serialVersionUID = 004L;
    private E content;
    private DoublyCircularNode<E> next;
    private DoublyCircularNode<E> previous;

    public DoublyCircularNode(E content){
        this.content=content;
        this.next=null;
        this.previous=null;
    }

    public E getContent() {
        return content;
    }

    public DoublyCircularNode<E> getNext() {
        return next;
    }

    public DoublyCircularNode<E> getPrevious() {
        return previous;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public void setNext(DoublyCircularNode<E> next) {
        this.next = next;
    }

    public void setPrevious(DoublyCircularNode<E> previous) {
        this.previous = previous;
    }

}
