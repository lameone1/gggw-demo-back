package com.gggw.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.gggw.service.counter.Counter;

/**
 * ClassName:ClassUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2016-9-27 上午9:46:55 <br/>
 * @author   cgw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@SuppressWarnings("all") 
public class ClassUtils {
	public static List<Class> getAllClassesByInterface(Class c) {
		List<Class> returnClassList = new ArrayList<Class>();
		
		if (c.isInterface()) {
			String packageName = c.getPackage().getName();
			try {
				List<Class> allClasses = getClasses(packageName);
				for (int i = 0; i < allClasses.size(); i++) {  
                    if (c.isAssignableFrom(allClasses.get(i))) {  
                        if (!c.equals(allClasses.get(i))) {  
                            returnClassList.add(allClasses.get(i));  
                        }  
                    }  
                }  
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		 return returnClassList;  
	}
	
	private static List<Class> getClasses(String packageName) throws Exception {
		ClassLoader c1 = Thread.currentThread().getContextClassLoader();
		String parh = packageName.replace(".", "/");
		Enumeration<URL> resources = c1.getResources(parh);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		
		List<Class> classes = new ArrayList<Class>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		
		return classes;
	}
	
	private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>(); 
		
		if (!directory.exists()) {  
            return classes;  
        }  
		
		File[] files = directory.listFiles();  
        
        for (File file : files) {  
              
            if (file.isDirectory()) {                    
                assert !file.getName().contains(".");  
                classes.addAll(findClasses(file, packageName + "." + file.getName()));  
            } else if (file.getName().endsWith(".class")) {  
                  
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));  
            }  
        } // end for  
          
        return classes;  
	}
	
	public static void main(String[] args) {
		 List<Class> clazzList =  getAllClassesByInterface(Counter.class);
		 System.out.println(clazzList);
	}
}

