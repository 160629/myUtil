package arithmetic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

import org.junit.Test;

public class Day170405 {

	public Day170405() {
		// TODO Auto-generated constructor stub
	}
	
	//二分查找法
	public int halfMethod(int [] param, int key){
		
		int min = 0;
		
		int max = param.length-1;
		
		int mid = (max-min)/2;
		
		while(max > mid){
			
		
			if(param[mid]>key){
				
				max = mid - 1;
				mid = (max-min)/2;
			}
			
			if(param[mid]<key){
				
				min = mid + 1;
				mid = (max-min)/2+min;
			}
			
			if(param[mid] == key){
				
				return param[mid];
			}
		
		}
		
		return -1;
		
	}
	
	//读取文件中含有'a','A'的单词个数
	@Test
	public void readFile(){
		StringBuilder result = new StringBuilder();
		int count = 0;
			/**InputStream input = null;    
		    OutputStream output = null;    
		    try {
		           input = new FileInputStream(source);
		           output = new FileOutputStream(dest);        
		           byte[] buf = new byte[1024];        
		           int bytesRead;        
		           while ((bytesRead = input.read(buf)) > 0) {
		               output.write(buf, 0, bytesRead);
		           }
		    } finally {
		        input.close();
		        output.close();
		    }*/
        try{
            BufferedReader br = new BufferedReader(new FileReader( new File("D:/workspace-face/myUtil/src/resource/123.txt")));//构造一个BufferedReader类来读取文件
            BufferedWriter bw = new BufferedWriter(new FileWriter( new File("D:/workspace-face/myUtil/src/resource/abc.txt")));
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(s);
                bw.write(result.toString());
               
            } 
            bw.flush();
            br.close();  
            bw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        String s = result.toString();
        
		String[] split = s.split(" ");
		
		for (String string : split) {
			
			for (int i = 0; i < string.length(); i++) {
				if(string.charAt(i) == 'a' || string.charAt(i) == 'A'){
					
					++count;
				}
			}
		}
		
        System.out.println(count);

	}
	
	@SuppressWarnings("resource")
	@Test
	public void copyFile(){
		 File file1 = new File("D:/workspace-face/myUtil/src/resource/123.txt");
		 File file2 = new File("D:/workspace-face/myUtil/src/resource/12345.txt");
/*       Files中方法		 
 * 		try {
			Files.copy(file1.toPath(), file2.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		 FileChannel channel1 = null;
		 FileChannel channel2 = null;
		 try {
			 channel1 = new FileInputStream(file1).getChannel();
			 channel2 = new FileOutputStream(file2).getChannel();
			 channel2.transferFrom(channel1, 0, channel1.size());
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			
			try {
				channel1.close();
				channel2.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
		 
		 
		 
		 
		
	}

}
