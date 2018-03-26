/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ble.Functionalities;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ble.SyntaxAnalyzer.Data;
import ble.objects.Array2dBle;
import ble.objects.ArrayAbstract;
import ble.objects.ArrayBle;
import ble.objects.ArrayInterface;

/**
 *
 * @author Randell
 */
public class ArraysFunct {
    private static String f;									//holder for the function call
    private static String arrName;	
    static Pattern p;
    static Matcher m, mat;
    
    //used in if line is arrayName[ndx][optional ndx] = value
    //private static boolean holder = false; //true if insert function otherwise false;
    //private static Object value; //holds the VALUE to be inserted
    
    public static void arrayStuff(String line, Map<String,Data> memory)
    {
        String pat = "[a-zA-z0-9]*(\\s*)?=";        
        p = Pattern.compile(pat);
        m = p.matcher(line);
        if(m.find()){
            //System.out.println(m.group());
            String patt = "[a-zA-Z0-9]*";
            p = Pattern.compile(patt);
            m = p.matcher(m.group());
            if(m.find()){
                //System.out.println();
                arrName = m.group(); 
                System.out.println("Array Name: "+arrName);
                memory.put(arrName, null);
            }
            //System.out.println(m.find()?m.group():"None");
        }
        String pat1 = "(=(\\s*)?)?[A-Za-z]*\\((\\s*)?.*\\)";//[a-zA-Z0-9]*((\\s*)?\\,((\\s*)?[\'\"a-zA-Z0-9]*((\\s*)?\\,(\\s*)?[\'\"a-zA-Z0-9]*(\\s*)?)?(\\s*)?)?)?\\)";
        p = Pattern.compile(pat1);
        m = p.matcher(line);
        if(m.find()){
            String patt = "[A-Za-z]*\\(.*\\)";
            p = Pattern.compile(patt);
            m = p.matcher(m.group());
            if(m.find()){
                f = m.group();  
                System.out.println("Array Function: "+f);
            }
            //System.out.println(m.find()?m.group():"None");
        }
    }
    
        public static String  getFunction() {
                String ret = null;
		String pattern = "[a-zA-Z]*";			// regex to be used in order to get the function call
		
		p = Pattern.compile(pattern);
		m = p.matcher(f);
		if(m.find()) {
			ret = m.group();						// saves the calling function to the class variable "f";
			System.out.println("Function Name: " +ret);
                        //return true;
		}
		return ret;
	}
		/**
	 * gets the parameters in the calling function
	 * @return parameters of the function
	 */
	private static String[] getParameters() {
                System.out.println("Getting parameters . . .");
		String pattern = "\\(.*\\)";
                //String split = "(\\s*)?\\,(\\s*)?";
		String paramGroup;// = new String("arr,1");
                //paramGroup.
		String[] params = null;
		p = Pattern.compile(pattern);
		m = p.matcher(f);
                
		if(m.find()) {
                    
                    paramGroup = m.group();
                    System.out.println(paramGroup);
                    
                    paramGroup = paramGroup.replaceAll("\\(|\\)", "");
                    paramGroup = paramGroup.replaceAll("\\s", "");
                    paramGroup = paramGroup.replaceAll("'|\"", "");
                    System.out.println("Param Group: "+paramGroup);
                    if(!paramGroup.equalsIgnoreCase("")){
                        params = paramGroup.split(",");
                       
                    } 
                    
		}
		return params;
	}
    /**
     * finds any corresponding array function that source file has specify
     * @param memory  - holds the value of the objects
     * @return any specified errors or value
     */
    public static Object findFunction(Map<String,Data> memory) {
                //System.out.println("Memory: "+memory);
		Object returnValue = null;					// object class that is used to return to the compiler
		//String pattern = "\\w*";					// regex to be used in order to get the desired function
		String function;
		String[] params;							// parameters of the called function		
		
		//p = Pattern.compile(pattern);
		//m = p.matcher(f);
		//m.find();
		//function = m.group();
		//search for the desired function to be conducted
		function = getFunction();
                params = getParameters();
		switch(function) {
			case "search":					
				// parameters (1) ArrayName (2) Value to be searched
				if(params.length == 2) {
					returnValue = (memory.containsKey(params[0]))? search(params,memory) : (String) "Array not found";
				} else {
					returnValue = (String) "Invalid Parameters";
				}
				
				break;
			
			case "size": 			
				// parameters (1) ArrayNAme
				if(params.length == 1) {
					returnValue = (memory.containsKey(params[0])) ? size(params,memory) : (String) "Array not found";
				} else {
					returnValue = (String) "Invalid Parameters";
				}
				
				break;
			case "delete":			
				// parameters (1) ArrayName (2) Value
				if(params.length == 2 || params.length == 3) {
					returnValue = (memory.containsKey(params[0])) ? delete(params,memory) : (String) "Array not found";
				} else {
					returnValue = (String) "Invalid Parameters";
				}
				
				break;
			case "insert": 		
				// parameters (1) ArrayName (2) Value (3)[Optional] Position
				if(params.length >= 2 && params.length <= 4) {
                                        //System.out.println(params[0]);
					returnValue = (memory.containsKey(params[0])) ? insert(params,memory) : (String) "Array not found";
				} else {
					returnValue = (String) "Invalid Parameters";
				}
				
				break;
			case "retrieve": 		
				// parameters (1) ArrayName (2)[Optional] Position
				if(params.length >= 1 && params.length <= 2) {
					returnValue = (memory.containsKey(params[0])) ? retrieve(params,memory) : (String) "Array not found";
				} else {
					returnValue = (String) "Invalid Parameters";
				}
				
				break;
			case "stringToArray":
                                break;
                                
			default: 
				if(params == null) {
                                        System.out.println("Array Initialization . . .");
					ArrayBle arr = new ArrayBle();
					Data<ArrayBle> data = new Data<ArrayBle>(false);
					//data.setNewScope();
					data.setValue(arr);
					memory.put(f, data);
                                        returnValue = (String) "Array Initialized";
				}else if(params.length == 2) {
                                        System.out.println("Array2D Initialization . . .");
                                        ArrayBle[][] arr2d = new ArrayBle[Integer.parseInt(params[0])][Integer.parseInt(params[1])];
                                        Data<ArrayBle[][]> data2d = new Data<ArrayBle[][]>(false);
                                      //  data2d.setScope(0);
                                        data2d.setValue(arr2d);
                                        memory.put(f, data2d);
                                        returnValue = (String) "Array2D Initialized";
                                    }else{
                                        returnValue = (String) "Invalid Parameters";
                                    }
                                
		}
		
		return returnValue;
	}
	
	/**
	 * finds the index of the given value
	 * @param params -> parameters of search function (1) - ArrayName (2) - Value
	 * @param memory -> memory of the compiler
	 * @return the index of the first occurrence of the value else returns -1
	 */
	private static int search(String[] params, Map<String,Data> memory) {
		System.out.println("Searching . . . ");
                int i;
		int returnValue = -1;
		Data<ArrayAbstract> data = (Data<ArrayAbstract>) memory.get(params[0]);
                Data<Object> x = new Data<>(false);
                ArrayAbstract array = (ArrayAbstract) data.getValue();
		if(data == null){
                    data = new Data<ArrayAbstract>(false);
                }
                
                if(array.getClass().isAssignableFrom(ArrayBle.class)){
                    array = (ArrayBle) array;
                    if(array == null){
                        array = new ArrayBle();
                    }
                    x = (Data<Object>) memory.get(arrName);
                    if(x == null){
                        x = new Data<>(false);
                    } 
                    if(!array.search(params[1]).equals(-1)){
                        x.setValue("true");
                    }else{
                        x.setValue("false");
                    }
                } else if (array.getClass().isAssignableFrom(Array2dBle.class)){
                    array = (Array2dBle) array;
                     if(array == null){
                        array = new Array2dBle();
                    }
                    x = (Data<Object>) memory.get(arrName);
                    if(x == null){
                        x = new Data<>(false);
                    } 
                    if(!array.search(params[1]).equals(-1)){
                        x.setValue("true");
                    }else{
                        x.setValue("false");
                    }
                }
                
                memory.put(arrName, x);
		
		return returnValue;
	}
	
	
	/**
	 * gets the size of the corresponding array
	 * @param params -> parameters of size function (1) - ArrayName
	 * @param memory -> memory of compiler
	 * @return the size of the array
	 */
	private static Integer size(String[] params, Map<String,?> memory) {
		System.out.println("Getting Size . . . ");
                ArrayInterface array = (ArrayInterface) memory.get(params[0]);

		return array.size();
	}
	
	/**
	 * deletes the object with the specific value
	 * @param params -> parameters of the delete function (1) - ArrayName (2) - Value
	 * @param memory -> memory of compiler
	 * @return the class of the deleted object
	 */
	private static Object delete(String[] params, Map<String,?> memory) {
		System.out.println("Deleting . . .");
                Object returnValue = null;
		
                System.out.println("Class: "+memory.get(params[0]).getClass().getSimpleName());
                //if(memory.get(params[0]).getClass() == "ArrayBle"){
                if(params.length == 2){
                    Data<ArrayBle> data = (Data<ArrayBle>) memory.get(params[0]);
                    if(data == null){
                        data = new Data<ArrayBle>(false);
                    }
                    ArrayBle array = (ArrayBle) data.getValue();
                    if(array == null){
                        array = new ArrayBle();
                    }

                    if(array.isEmpty()) {
                            returnValue = (String) "ERROR: -1 is not a valid index";
                    } else {
                            returnValue = array.delete(params[1]);
                    }
                } else if (params.length == 3) {
                    Data<Array2dBle> data = (Data<Array2dBle>) memory.get(params[0]);
                    if(data == null){
                        data = new Data<Array2dBle>(false);
                    }
                    Array2dBle array = (Array2dBle) data.getValue();
                    if(array == null){
                        array = new Array2dBle();
                    }

                    if(array.isEmpty() || array.checkPosExists(params[1], params[2])) {
                        returnValue = (String) "ERROR: -1 is not a valid index";
                    } else {
                        returnValue = array.deleteAtPos(params[1], params[2]);
                    }
                }
		
		return returnValue;
	}
		
	/**
	 * insert at a given position or insert last
	 * @param params
	 * @param memory
	 * @return
	 */
	private static String insert(String[] params, Map<String, Data> memory) {
		System.out.println("Inserting . . . ");
                String message = null;
		
                Data<ArrayAbstract> data = (Data<ArrayAbstract>) memory.get(params[0]);
                ArrayAbstract array = (ArrayAbstract)data.getValue();
                
                if(data == null){
                    data = new Data<ArrayAbstract>(false);
                }
                
                if (array.getClass().isAssignableFrom(ArrayBle.class)) {
                    array = (ArrayBle) data.getValue();
                    if(array == null){
                        array = new ArrayBle();
                    }   

                    String key = Integer.toString(array.size());
                    Integer pos = array.size();
                    if(params.length == 2) {
                        array.insert(key, params[1]);
                    } else if (params.length == 3) {
                        try {
                            pos = Integer.parseInt(params[2]);
                        } catch (NumberFormatException e) {
                            key = params[2];
                        }
                        array.insert(key, params[1], pos);
                    }
                } else if (array.getClass().isAssignableFrom(Array2dBle.class)) {
                    array = (Array2dBle) data.getValue();
                    if(array == null){
                        array = new Array2dBle();
                    }   

                    String key = Integer.toString(array.size());
                    Integer pos = array.size();
                    if(params.length == 4) {
                        array.insert(params[2], params[3], params[1]);
                    }
                }
                
                data.setValue(array);

                memory.put(params[0], data);

		return message;
	}
	
	/**
	 * retrieve the value of the array in the position or first index
	 * @param params
	 * @param memory
	 * @return
	 */
	private static Object retrieve(String[] params, Map<String, Data> memory) {
		System.out.println("Retrieving . . . ");
		Object ret = null;
		//ArrayBle array = (ArrayBle) memory.get(params[0]);
		Data<ArrayAbstract> data = (Data<ArrayAbstract>) memory.get(params[0]);
                Data<Object> x = (Data<Object>) memory.get(arrName);
                 if(x == null){
                    x = new Data<>(false);
                }
                if(data == null){
                    data = new Data<ArrayAbstract>(false);
                }
                
                ArrayAbstract array = (ArrayAbstract) data.getValue();
                if(array == null){
                    array = new ArrayBle();
                } else if(array.getClass().isAssignableFrom(ArrayBle.class)) {
                    array = (ArrayBle) array;
                    if(params.length == 1) {
			System.out.println("retrieving first position");
			ret = array.get(0);
                    } else if(params.length == 2) {
                            ret = array.get(params[1]);
                    } 
                } else if(array.getClass().isAssignableFrom(Array2dBle.class)) {
                    array = (Array2dBle) array;
                    if(params.length == 3) {
                        ret = array.getValueAt(params[1],params[2]);
                    }
                }
                
                x.setValue(ret);
                memory.put(arrName, x);
                
		return ret;
	}
}
