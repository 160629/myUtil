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
    	
        // 已分配内存中的剩余空间 ：
        long free = Runtime.getRuntime().freeMemory();
        // 分配内存：
        long total = Runtime.getRuntime().totalMemory();
        // 最大内存：
        long max = Runtime.getRuntime().maxMemory();
        // 已占用的内存：
        long used = total - free;
    	System.out.println(haoAddOne_2("2017010101"));
	}
}
