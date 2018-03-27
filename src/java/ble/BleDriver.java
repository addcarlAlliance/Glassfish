package ble;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.script.ScriptException;

import ble.SyntaxAnalyzer.DataTypes;
import ble.Functionalities.MainProcess;
import ble.Network.Network;
import ble.Network.Http.Server;
import ble.SyntaxAnalyzer.Comments;
import ble.SyntaxAnalyzer.SyntaxAnalyzer;
import ble.injector.ImbedHtml;
import java.util.ArrayList;

/**
 *
 * @author Max
 */
public class BleDriver {
    
    protected Network network = null;
   
    static SyntaxAnalyzer sa = new SyntaxAnalyzer();
    private static MainProcess mp = new MainProcess();
    private static ble.SyntaxAnalyzer.DataTypes dt = new ble.SyntaxAnalyzer.DataTypes();
    private static final BleExtractor extractor = new BleExtractor();
    //Temporary for reading every .ble file for http upload to browser
    private static final File CONSTANT_FOLDER = new File("bledocs/");
    private static final File[] CONSTANT_LISTOFFILES = CONSTANT_FOLDER.listFiles();
    
    public static String drive(String bleCode, String path, String id) throws Exception {
        
        SyntaxAnalyzer syn = new SyntaxAnalyzer();
        System.out.println("...Preparing files for browser upload");
//        bleCode = new String(Files.readAllBytes(Paths.get("bledocs/"+file.getName())), StandardCharsets.UTF_8);
        ArrayList<String> resultGet = new ArrayList<>();
        String[] results;
        String status = "";
        ble.SyntaxAnalyzer.DataTypes data = new DataTypes();
        String result = extractor.extractBle(bleCode);
        ImbedHtml injectResult = new ImbedHtml();
        
        injectResult.setHtmlCode(bleCode);
                    
        System.out.println("result" + result);

        if(syn.analyze(result)) {
            System.out.println("Analyzed!!!");
            String[] lines1 = result.split("\\n");
           // System.out.println("resultttt" + result);
            for(int i1 = 0 ; i1 < lines1.length; i1++) {
                String line = lines1[i1];
                if(!line.trim().equals("")) {
                    resultGet.add(line);
                 //   System.out.println("Line :"+line);
                    //resultGet.add(line);
                    status = MainProcess.process(lines1, i1, data, bleCode, path, id);
                }
            }
            
           // System.out.println("RESULT" + result);
            
            results = resultGet.toArray(new String[0]);
          //  System.out.println("***BLECODE"+bleCode);
            
            injectResult.setResults(results); // from ble to  html code 
      //      Server server = new Server();
            injectResult.setHtmlCode(bleCode);
            injectResult.imbedResults(status);
            
            
            return injectResult.getHtmlCode();
       //     server.setContext(file.getName());
       //     server.setResponse(injectResult.getHtmlCode());

       //     server.setSocketNo(1026);
       //     System.out.println("Located at: localhost:"+server.getSocketNo()+server.getContext());
       //     Server.server(null);
           // Server.server(status);
        } else {
            return "Syntax Error";
        }
        
    }
}