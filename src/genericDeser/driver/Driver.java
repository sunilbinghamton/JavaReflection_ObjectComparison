package genericDeser.driver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import genericDeser.fileOperations.FileProcessor;
import genericDeser.logger.Logger;
import genericDeser.util.PopulateObjects;


/**
 * @author Sunil
 *
 */
public class Driver {

	static String inputFile;
	static int debugLevel;


	
	public static void main(String args[]){
		// Argument error handling
				if (args.length != 2) {
					System.out.println("Missing Run time arguments(InputFile/DEBUG LEVEL).. Existing Program.");
					System.exit(-1);
				}
				else{
					inputFile = args[0];
					debugLevel = Integer.parseInt(args[1]);
					
					if (debugLevel > 4 || debugLevel <0){
						System.out.println("DEBUG VALUE not in range (0-4)..Existing Program.");
						System.exit(-1);
					}
								
				}
				
		try {
				Logger.setDebugValue(debugLevel);
			
				BufferedReader br = new BufferedReader(new FileReader(inputFile));
				FileProcessor fileObj= new FileProcessor( null );
				fileObj.setBufferObj(br);
				
				PopulateObjects populate = new PopulateObjects(fileObj, br) ;				
				populate.deserObjects();	
				populate.printNumberofInstances();
						
				br.close();
				
						    
		}
		catch (FileNotFoundException  e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		} finally{
			
		}
		

		
	}
	
}
