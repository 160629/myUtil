package util;

import java.text.DecimalFormat;

public class StringUtil {
	
	private static final String STR_FORMAT = "0000000000000000";   
	  
    public static String haoAddOne_2(String liuShuiHao){  
        Integer intHao = Integer.parseInt(liuShuiHao);  
        intHao++;  
        DecimalFormat df = new DecimalFormat(STR_FORMAT);  
        return df.format(intHao);  
    } 
    
    public static void main(String[] args) {
    	System.out.println(haoAddOne_2("2017010101"));
	}
}
