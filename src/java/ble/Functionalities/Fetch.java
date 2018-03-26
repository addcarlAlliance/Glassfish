/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ble.Functionalities;

import ble.SyntaxAnalyzer.DataTypes;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Sean Cadigal
 */
public class Fetch {
    private static final String rgx = "fetch\\(\\s*([\"'])(get|post)\\1\\s*,\\s*\\1.*\\1\\s*\\)";
    private static String[] parameters;
    
    /**
     * Check if the line is a fetch statement
     * @return 
     */
    public static final boolean check(String line){
        Pattern p = Pattern.compile(rgx);
        Matcher m = p.matcher(line);
        
        return m.find();
    }
    
    private static String[] getParams(String line){
        String[] params = null;
        String paramRgx = "\\(\\s*([\"'])(get|post)\\1\\s*,\\s*\\1.*\\1\\s*\\)";
        Pattern p = Pattern.compile(paramRgx);
        Matcher m = p.matcher(line);
        
        if (m.find()) {
            String uncleanParams = m.group();
            uncleanParams = uncleanParams.replaceAll("\\(|\\)", "");
            params = uncleanParams.split(",");
            for (int i = 0; i < params.length; i++){
                params[i] = params[i].trim();
                params[i] = params[i].replace("\"'", "");
            }
        }
        
        return params;
    }
    
    public Fetch(){
        
    }
    
    public static String fetchExecute(String line){
        Fetch.parameters = getParams(line);
        String value = null;
        if (Fetch.parameters.length == 2) {
            String preceed = null;
            if (Fetch.parameters[0].equalsIgnoreCase("get")) {
                preceed = "g_";
            } else if (Fetch.parameters[0].equalsIgnoreCase("post")) {
                preceed = "p_";
            }
            String varName = preceed+Fetch.parameters[1];
            if (DataTypes.doesVarExist(varName)) {
                value = DataTypes.getValueString(varName);
            }
        }
        return value;
    }
}
