/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ble.objects;

/**
 *
 * @author Sean Cadigal
 */
public abstract class ArrayAbstract {

    public int size() {
        return 0;
    }

    /**
     *
     * @param key
     * @param param
     */
    public void insert(String key, Object param) {
    }

    /**
     *
     * @param key
     * @param param
     * @param pos
     */
    public void insert(String key, Object param, int pos) {
    }
    
    public void insert(String row, String key, Object value){
        
    }

    /**
     *
     * @param value
     * @return
     */
    public Object search(Object value) {
        return null;
    }

    public Object get(int i) {
       return null;
    }
    
    public Object get(String key) {
        return null;
    }

    public Object getValueAt(String param, String param0) {
      return null;
    }
    
}
