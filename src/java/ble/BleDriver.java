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

/**
 *
 * @author Max
 */
public class BleDriver {
    
    protected Network network = null;
    
    static SyntaxAnalyzer sa = new SyntaxAnalyzer();
    private static MainProcess mp = new MainProcess();
    private static ble.SyntaxAnalyzer.DataTypes dt = new ble.SyntaxAnalyzer.DataTypes();
    //Temporary for reading every .ble file for http upload to browser
    private static final File CONSTANT_FOLDER = new File("bledocs/");
    private static final File[] CONSTANT_LISTOFFILES = CONSTANT_FOLDER.listFiles();
    
    public static void drive() {
        
        System.out.println("...Preparing files for browser upload");
//        bleCode = new String(Files.readAllBytes(Paths.get("bledocs/"+file.getName())), StandardCharsets.UTF_8);
       /*             ArrayList<String> resultGet = new ArrayList<>();
                    String[] results;
                    String result = extractor.extractBle(file);
                    
                    System.out.println("result" + result);

                    if(syn.analyze(result)) {
                        System.out.println("Analyzed!!!");
                        String[] lines1 = result.split("\\n");
                      System.out.println("resultttt" + result);
                        for(int i1 = 0 ; i1 < lines1.length; i1++) {
                            String line = lines1[i1];
                        	if(!line.trim().equals("")) {
                        		resultGet.add(line);
                                System.out.println("Line :"+line);
                                //resultGet.add(line);
                                status = MainProcess.process(lines1,i1, data);
                            }
                        }
                        System.out.println("RESULT" + result);
	                    //results[0] = "varX = "+" "+MDASFunc.evalExp(result);

                        results = resultGet.toArray(new String[0]);
                        
                        System.out.println("***BLECODE"+bleCode);
                        ImbedHtml injectResult = new ImbedHtml(bleCode, results); // from ble to  html code 
                        Server server = new Server();

                        System.out.println("File: "+file.getName());
                        injectResult.imbedResults();
                        server.setContext(file.getName());
                        server.setResponse(injectResult.getHtmlCode());
						
                        server.setSocketNo(1026);
                        System.out.println("Located at: localhost:"+server.getSocketNo()+server.getContext());
                        Server.server(null);
                       // Server.server(status);
                        ctr++;
	                }
                }
            } */
    
    }
    
    
  /*  public static void main(String[] args) throws IOException, ScriptException, Exception {
        BleDriver driver = new BleDriver();

        String bleCode, temp;
        String[] lines;
        int i;
       
        
        bleCode = new String(Files.readAllBytes(Paths.get("helloworld.ble")), StandardCharsets.UTF_8);
        
        if(sa.analyze(bleCode)) {
            System.out.println("Correct Syntax!");
            // Getting rid of Comments
            bleCode = Comments.comments(bleCode);
            
            // Check the code line-by-line and check what function to use :(
            lines = bleCode.split(System.getProperty("line.separator"));
            
            for(i = 0; i < lines.length; i++) {
                MainProcess.process(lines,i,dt);
            }
            
        } else {
            System.out.println("Syntax Error");
        }
        
        //Assume output processed from special task no. 2...
        //Testing Display...
        /*
            bleCode = new String(Files.readAllBytes(Paths.get("bledocs/9999/testing.ble")), StandardCharsets.UTF_8);
            String[] results = new String[2];
        \    
            results[0] = "varX = 10";
            results[1] = "varY = 20";
        
            Display displaySample = new Display(bleCode, results);
            displaySample.display();
            
        return;
        */
        //Assume files come from http request or socket...
        /* int ctr = 8080;
        for (File file : CONSTANT_LISTOFFILES) {
                if (file.isFile()) {
                    System.out.println("...Preparing files for browser upload");
                    bleCode = new String(Files.readAllBytes(Paths.get("bledocs/"+file.getName())), StandardCharsets.UTF_8);
                    ImbedHtml injectResult = new ImbedHtml(bleCode, results);
                    Server server = new Server();
                    
                    System.out.println("File: "+file.getName());
                    injectResult.imbedResults();
                    server.setContext(file.getName());
                    server.setResponse(injectResult.getHtmlCode());
                    server.setSocketNo(ctr);
                    System.out.println("Located at: localhost:"+server.getSocketNo()+server.getContext());
                    
                    Server.server(null);
                    ctr++;
                }
        } 
    } */
    
    public Network network() {
        return this.network;
    }
}