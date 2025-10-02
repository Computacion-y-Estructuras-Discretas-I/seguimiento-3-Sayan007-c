package structures;

import java.util.LinkedList;

import java.lang.UnsupportedOperationException;
import java.lang.NullPointerException;

public class TablasHash {
    private LinkedList<Integer>[] tabla;
    private int size;

    @SuppressWarnings("unchecked")
    public TablasHash(int n) {
        this.tabla = new LinkedList[n];
        this.size = n;
    }

    public boolean search(int k, int value) {
        int pos = this.hashFunction(k);
        LinkedList<Integer> found = this.tabla[pos];
        if (found != null) {
            for (Integer c : found) {
                if (c == value) {
                    return true;
                }
            }
        }
        return false;
    }

    public void insert(int k, int value) {
        int pos = this.hashFunction(k);
        LinkedList<Integer> found = this.tabla[pos];
        if (found == null) {
            found = new LinkedList<>();
            this.tabla[pos] = found;
        }
        found.add(value);
    }

    public void delete(int k, int v) {
        int pos = this.hashFunction(k);
        LinkedList<Integer> found = this.tabla[pos];
        if (found != null) {
            found.remove((Integer) v);
        }
    }

    private int hashFunction(int k) {
        return Math.abs(k) % this.size;
    }
}
