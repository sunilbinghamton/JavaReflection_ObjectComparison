package genericDeser.util;

import java.io.BufferedReader;
import java.io.IOException;
import genericDeser.fileOperations.FileProcessor;
import genericDeser.logger.Logger;
import java.lang.Class;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


/**
 * @author Sunil
 *
 */
public class PopulateObjects {
	FileProcessor fileObj;
	BufferedReader br; 
	String methodName, type, value;
	String[] temp;
	Class<?> cls = null;
	Class<?> instance= null;
	Object result = null;	
	Object obj = null;
	Method meth;
	Map<String, Object> dataTypes = new HashMap<>();
	Map<String, Class<?>> classTypes = new HashMap<>();
	Map<Object, Integer> firstClassStruct = new HashMap<>();
	Map<Object, Integer> secondClassStruct = new HashMap<>();
	
	Integer uniqueInstanceFirst=0, totalInstanceFirst=0;
	Integer uniqueInstanceSecond=0, totalInstanceSecond=0;
	
	int ucFirst=0, ucSecond=0, tFirst=0, tSecond=0;
  
	/**
	 * @param fileObjIn file object to constructor
	 * @param brIn buffer reader object to initialize constructor
	 */
	public PopulateObjects(FileProcessor fileObjIn, BufferedReader brIn){
		fileObj = fileObjIn;
		br = brIn;
		dataTypes.put("float", Float.TYPE);
		dataTypes.put("int", Integer.TYPE);
		dataTypes.put("short", Short.TYPE);
		dataTypes.put("double", Double.TYPE);
		dataTypes.put("boolean", Boolean.TYPE);
		dataTypes.put("String", String.class);	

		Logger.writeMessage("Logger: In Populate Objects Constructor:", Logger.DebugLevel.CONSTRUCTOR);
	}
	
	public void deserObjects(){
		String line;
		
		try {
			
			while( (line = fileObj.hasLine()) != null){
				
				if (line.contains("fqn")){
					if (obj != null){
						addToTable(obj);
					}
					//String clsName =  "genericDeser.util.First";
					//System.out.println(line.split(":")[1]);
					cls = Class.forName(line.split(":")[1]); 
					obj = cls.newInstance();
				}
				
				else {
					Class<?>[] signature = new Class[1];
					temp = line.replace(",","").split(" ");
					type = temp[0].split("=")[1];
					
					signature[0] = (Class<?>) dataTypes.get(type);
					//System.out.println("Signature value: "+ signature[0].toString());
									
					methodName = "set" + temp[1].split("=")[1];
					value =  temp[2].split("=")[1];	
					
					meth = cls.getMethod(methodName, signature);					 
					
				    Object[] params = new Object[1];
				    				    
				    if (type.equalsIgnoreCase("int"))
				    	params[0] = new Integer(value);
				    else if (type.equalsIgnoreCase("float"))
				    	params[0] = new Float(value);
				    else if (type.equalsIgnoreCase("double"))
				    	params[0] = new Double(value);
				    else if (type.equalsIgnoreCase("string"))
				    	params[0] = new String(value);
				    else if (type.equalsIgnoreCase("short"))
				    	params[0] = new Short(value);
				    else if (type.equalsIgnoreCase("boolean"))
				    	params[0] = new Boolean(value);				    
				    
					meth.invoke(obj, params);
										
				}
								
				
		} // end of while loop
			addToTable(obj);
			calculateUniqueInstances();
			calculateTotalInstances();
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (ClassNotFoundException e) {
			System.out.println("No class found: " + e.getMessage());
	        System.exit(1);
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.out.println("No such method: " + e.getMessage());
	        System.exit(1);
		} catch (SecurityException e) {
			System.out.println("Security exception: " + e.getMessage());
	        System.exit(1);
			
		} catch (InstantiationException e) {
			System.out.println("Error in class instantiation: " + e.getMessage());
	        System.exit(1);
			
		} catch (IllegalAccessException e) {
			 System.out.println("Illegal access: " + e.getMessage());
	         System.exit(1);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			System.out.println("Invocation target exception: " + e.getMessage());
	        System.out.println("Exception: " + e.getTargetException().getMessage());
	        System.exit(1);
		} finally{
			
		 }	
		
	}

	/**
	 * 
	 */
	public void printNumberofInstances() {
		Logger.writeMessage("Logger: Number of unique First objects: " + uniqueInstanceFirst.toString(), Logger.DebugLevel.PRINT);
		Logger.writeMessage("Logger: Total Number of First objects: " + totalInstanceFirst.toString(), Logger.DebugLevel.PRINT);
		Logger.writeMessage("Logger: Number of unique Second objects: " + uniqueInstanceSecond.toString(), Logger.DebugLevel.PRINT);
		Logger.writeMessage("Logger: Total Number of Second objects: " + totalInstanceSecond.toString(), Logger.DebugLevel.PRINT);
	}

	/**
	 * 
	 */
	private void calculateTotalInstances() {
		Iterator<?> itFirst = firstClassStruct.entrySet().iterator();
		Iterator<?> itSecond = secondClassStruct.entrySet().iterator();
		
		
		
		while(itFirst.hasNext()){
			Map.Entry<Object, Integer> pair = (Entry<Object, Integer>)itFirst.next();
			totalInstanceFirst = totalInstanceFirst + (int)pair.getValue();  
		}
		
		while(itSecond.hasNext()){
			Map.Entry<Object, Integer> pair = (Entry<Object, Integer>)itSecond.next();
			totalInstanceSecond = totalInstanceSecond + (int)pair.getValue();  
		}
		
		
	}

	/**
	 * 
	 */
	private void calculateUniqueInstances() {
		uniqueInstanceFirst = firstClassStruct.size();
		uniqueInstanceSecond = secondClassStruct.size();
		
	}

	/**
	 * @param obj
	 */
	private void addToTable(Object obj) {
		if (obj instanceof First){
			if (firstClassStruct.containsKey(obj)){
				Integer curntCount = firstClassStruct.get(obj);
				//System.out.println(curntCount);
				firstClassStruct.put(obj, ++curntCount);
			}
			
			else{
				firstClassStruct.put(obj, 1);
			}
				
		}
		
		else if (obj instanceof Second){
			
			if (secondClassStruct.containsKey(obj)){
				Integer curntCount = secondClassStruct.get(obj);
				secondClassStruct.put(obj, ++curntCount);
			}
			
			else{
				secondClassStruct.put(obj, 1);
			}
				
		}
			
	}
	

}
