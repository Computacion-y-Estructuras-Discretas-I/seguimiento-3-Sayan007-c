package structures;

import java.lang.IndexOutOfBoundsException;
import java.lang.StackOverflowError;

public class PilaGenerica<T> {
    private T[] arreglo;
    private int top;
    private int size;

    @SuppressWarnings("unchecked")
    public PilaGenerica(int n) {
        this.arreglo = (T[]) new Object[n];
        this.size = n;
        this.top = 0;
    }

    public boolean Push(T x) {
        if (this.top < this.size) {
            this.arreglo[top] = x;
            this.top++;
            return true;
        }
        return false; // pila llena
    }

    public T Pop() {
        if (this.top > 0) {
            this.top--;
            return this.arreglo[top];
        }
        return null; // pila vacía
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public T peek() {
        if (this.top > 0) {
            return this.arreglo[top - 1];
        }
        return null;
    }

    public int getTop() {
        return top;
    }

    public int getSize() {
        return size;
    }
}
