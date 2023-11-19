package com.spaapp.model.business.manager;

import com.spaapp.model.business.exception.PropertyFileNotFoundException;
import com.spaapp.model.domain.SpaApComposite;
import com.spaapp.model.services.spaservices.manager.PropertyManager;
     
public abstract class ManagerSuperType {

	static
	{
    	try
		{
    		ManagerSuperType.loadProperties();  
		}
    	catch (PropertyFileNotFoundException propertyFileNotFoundException)
		{
    	   System.out.println ("Application Properties failed to be loaded - Application exiting...");
    	   System.exit(1);
		}				
	} 
	public abstract boolean performAction(String commandString, SpaApComposite spaApComposite); 
	
	
    public static void loadProperties () throws PropertyFileNotFoundException
	{
		
		String propertyFileLocation = System.getProperty("prop_location");
		
        if (propertyFileLocation != null)
        { 
          PropertyManager.loadProperties(propertyFileLocation);
        }
        else
        {
          System.out.println("Property file location not set. Passed-in value is: " + propertyFileLocation + ".");
          throw new PropertyFileNotFoundException ("Property file location not set", null);         
        }
    	
	} // end loadProperties
}