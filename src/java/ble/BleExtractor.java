package ble;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Cadigal, Dimpas, Gelbolingo, Gimenez, Po
 *
 */
public class BleExtractor {
	private final String regex = "(?<=(<@BLE))(\\w|\\d|\\n|[().,\\-:;@#$%^&*\\[\\]\"'+–/\\/®°°!?{}|`~=]|\\t|\\s)+?(?=(@>))";
	
	public BleExtractor() {
		
	}
	
	public String extractBle(String bleCode) throws FileNotFoundException, IOException {
		String temp = "", text = null;
	/*	BufferedReader reader = new BufferedReader(new StringReader(bleCode));
		
		while((text = reader.readLine()) != null) {
			temp += text;
			temp += "\n";
		}
		
	*/	
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(bleCode);
		//String filename = file.getName();
		
		
		//BufferedWriter writer = new BufferedWriter(new FileWriter(new File("bledocs/extracted_"+filename)));
		if(m.find()) {
                    return m.group();
                } else {
                    return "";
                }
		//writer.write(m.group());
		//System.out.println(m.group());
		
		//writer.close();
		//reader.close();
		
	}
}
