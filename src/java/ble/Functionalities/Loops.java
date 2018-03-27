package ble.Functionalities;

import ble.SyntaxAnalyzer.Data;
import ble.SyntaxAnalyzer.DataTypes;
import static ble.SyntaxAnalyzer.SyntaxAnalyzer.CONDITION;
import ble.objects.Array2dBle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;

/**
 *
 * @author Max
 */
public class Loops {
    static ScriptEngineManager sem;
    static ScriptEngine se;
    static Object obj;
    static Pattern p;
    static Matcher m;
    static int n;
    

    static int repeat(String line, String[] lines, int idx) throws ScriptException, IOException {
        
        int newNdx = idx + 1;
        int lastNdx = newNdx;
        int tabs, checkTabs;
        ble.SyntaxAnalyzer.DataTypes data = new ble.SyntaxAnalyzer.DataTypes();
        p = Pattern.compile(CONDITION);
        m = p.matcher(line);
        line = line.split("\\(")[1];
        line = line.split("\\)")[0];
        
        String fakeLine = line;
        for(tabs = 0; fakeLine.contains("\t"); tabs++) {
            fakeLine = fakeLine.replaceFirst("\t", "");
        }
            
        tabs++;
        
        if(m.find()) {
            sem = new ScriptEngineManager();
            se = sem.getEngineByName("js");
            obj = se.eval(line);
           
            fakeLine = lines[newNdx];
            lastNdx = newNdx;
            for(checkTabs = 0; lines.length < newNdx && fakeLine.contains("\t"); checkTabs++) {
                fakeLine = fakeLine.replaceFirst("\t", "");
                if(checkTabs == tabs) {
                    lastNdx++;
                    fakeLine = lines[lastNdx];
                }
            }
            
            while(obj.toString().equals("true")) {
                for(idx = newNdx; idx <= lastNdx; idx++) {
                    MainProcess.process(lines, idx, data);
                }
            }
        } else {
            p = Pattern.compile("\\d+");
            m = p.matcher(line);
            if(m.find()){
                
                n = Integer.parseInt(m.group());
                
                while(n-- > 0) {
                    // TODO: Make this work for 2DArrays
                    if(lines.length > idx + 1) {
                        System.out.println("loop works! but with a problem cannot detect tab from next line due to all the lines had all their spaces removed");
                    }
                }
            } else {
                
                // This is where we loop arrays
                p = Pattern.compile("\\(.*\\)");
                m = p.matcher(line);
                if(m.find()){
                    String arrayName = m.group();
                    arrayName = arrayName.replaceAll("\\(|\\)", "");
                    Data<Array2dBle> var = (Data<Array2dBle>)DataTypes.vars.get(arrayName);
                    Array2dBle arr = var.getValue();
                    n = arr.size();
                    
                    while(n-- > 0){
                        
                    }
                }
            }
        }
        
        return lastNdx;
    }
    
    static void forLoop(String line) {
        // Unable to access variables as of yet
    }
}

