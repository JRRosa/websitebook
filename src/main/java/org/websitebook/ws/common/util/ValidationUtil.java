package org.websitebook.ws.common.util;

import java.util.Collection;
import java.util.Map;

public class ValidationUtil {
    
    private ValidationUtil() {}

    public static boolean isNull(Object object) {
        return object == null;
    }
	
	public static boolean isNull(Object... objects){
		boolean isInvalid = false;
		
		for (Object object : objects) {
			if (isNull(object)) {
				isInvalid = true;
				break;
			}
		}
		
		return isInvalid;
	}
	
	public static boolean isNullOrEmpty(String string){		
		return isNull(string) || (string.isEmpty());		
	}
	
	public static boolean isNullOrEmpty(String... strings){
		boolean isInvalid = false;
		
		for (String string : strings) {
			if (isNullOrEmpty(string)) {
				isInvalid = true;
				break;
			}
		}
		
		return isInvalid;
	}
		
	public static boolean isNullOrEmpty(Number... numbers) {
		boolean isInvalid = false;
		
		for (Number number : numbers) {
			if (isNullOrEmpty(number)) {
				isInvalid = true;
				break;
			}
		}
		
		return isInvalid;
	}
	
	public static boolean isNullOrEmpty(Collection<?> collection){
		return isNull(collection) || collection.isEmpty();
	}
	
	public static boolean isNullOrEmpty(Map<?, ?> map){
		return isNull(map) || map.isEmpty();
	}


}
