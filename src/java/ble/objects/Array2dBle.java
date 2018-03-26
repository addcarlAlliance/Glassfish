/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ble.objects;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author christian
 */
public class Array2dBle extends ArrayAbstract {
    private List<Array2dElement<?>> l;
    
    public Array2dBle() {
        this.l = new ArrayList<>();
    }
    
    public void insert(String row, String key, Object value) {
        ArrayBle tempRow;
        Array2dElement temp;
        int x;
        if(!this.checkRowExists(row)){
            tempRow = new ArrayBle();
            tempRow.insert(key, value);
        }else{
            for(x = 0; x < l.size(); x++){
                if(l.get(x).getKey().equals(row)){
                    tempRow = l.get(x).getRow();
                    tempRow.insert(key, value);
                    //l.get(x).setRow(tempRow);
                    temp = new Array2dElement(key, tempRow);
                    
                    l.set(x, temp);
                }
            }
        }
    }
    
    public Object deleteAtPos(String row, String col){
        int x, y;
        Object value = null;
        ArrayBle tempRow;
        Array2dElement temp;
        if(this.checkPosExists(row, col)){
            for(x = 0; x < l.size() && !l.get(x).getKey().equals(row); x++){}
            tempRow = l.get(x).getRow();
            for(y = 0; y < tempRow.size() && !tempRow.getKeyAt(y).equals(col); y++){}
            value = tempRow.get(y);
            tempRow.deleteAt(y);
            temp = new Array2dElement(col, tempRow);
            l.set(x, temp);
        }
        return value;
    }
    
    public boolean isEmpty() {
        return l.isEmpty();
    }

    public int size() {
        return l.size();
    }
    
    public boolean checkRowExists(String RowKey){
        int x;
        boolean ret;
        ret = false;
        
        for(Array2dElement row : l){
            if(row.getKey().equals(RowKey)){
                ret = true;
                break;
            }
        }
        return ret;
    }
    
    public boolean checkPosExists(String row, String col){
        boolean ret = false;
        int x;
        for(x = 0; x < l.size() && !l.get(x).getKey().equals(row); x++){}
        if(l.get(x).getKey().equals(row)){
            ret = true;
        }
        return ret;
    
    }
    
    public Object getValueAt(String row, String col){
        Object ret = 0;
        int x, y;
        ArrayBle temp;
        for(x = 0; x < l.size() && !l.get(x).getKey().equals(row); x++){}
        if(l.get(x).getKey().equals(row)){
            temp = l.get(x).getRow();
            ret = temp.get(col);
        }
        return ret;
    }
    
    public Object search (Object value) {
            Integer i = -1;
            Boolean found = false;
            if(!l.isEmpty()) {
                for(i = 0; i < l.size() && !found; i++){
                    ArrayBle l2 = l.get(i).getRow();
                    if((int)l2.search(value) > 0){
                        found = true;
                    }
                }    
            }

            return i;
    }  
    
}
