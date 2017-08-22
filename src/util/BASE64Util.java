package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;





import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/** 
* @ClassName  PDFUtil 
* @Description   PDF转成Base64工具类
* @author zhang.m 
* @date 2017年1月9日 下午1:27:10
*/
public class BASE64Util {
	private final static Logger logger = LoggerFactory.getLogger(BASE64Util.class);
	
	public static String PDFToBase64(File file)throws Exception { 
		BASE64Encoder encoder = new BASE64Encoder(); 
		FileInputStream fin =null; 
		BufferedInputStream bin =null; 
		ByteArrayOutputStream baos = null; 
		BufferedOutputStream bout =null; 
		try { 
			fin = new FileInputStream(file); 
			bin = new BufferedInputStream(fin); 
			baos = new ByteArrayOutputStream(); 
			bout = new BufferedOutputStream(baos); 
			byte[] buffer = new byte[1024]; 
			int len = bin.read(buffer); 
			while(len != -1){ 
				bout.write(buffer, 0, len); 
				len = bin.read(buffer); 
			} 
			//刷新此输出流并强制写出所有缓冲的输出字节 
			bout.flush(); 
			byte[] bytes = baos.toByteArray(); 
			return encoder.encodeBuffer(bytes).trim(); 
		} catch (FileNotFoundException e) { 
			logger.error(e.getMessage());
			throw new Exception(e);
		} catch (IOException e) { 
			logger.error(e.getMessage());
			throw new Exception(e);
		}finally{ 
			fin.close(); 
			bin.close(); 
			bout.close(); 
		}
	}
	
	public static void Base64ToPDf(String base64Content,String filePath)throws Exception{
		 BASE64Decoder decoder = new BASE64Decoder();
	        BufferedInputStream bis = null;
	        FileOutputStream fos = null;
	        BufferedOutputStream bos = null;

	        try {
	            byte[] bytes = decoder.decodeBuffer(base64Content);//base64编码内容转换为字节数组
	            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
	            bis = new BufferedInputStream(byteInputStream);
	            File file = new File(filePath);
	            File path = file.getParentFile();
	            if(!path.exists()){
	                path.mkdirs();
	            }
	            fos = new FileOutputStream(file);
	            bos = new BufferedOutputStream(fos);

	            byte[] buffer = new byte[1024];
	            int length = bis.read(buffer);
	            while(length != -1){
	                bos.write(buffer, 0, length);
	                length = bis.read(buffer);
	            }
	            bos.flush();
	        } catch (Exception e) {
	        	logger.error(e.getMessage());
				throw new Exception(e.getMessage());
	        }finally{
	            bis.close();
	            fos.close();
	            bos.close();
	        }

	}
	public static void main(String[] args) {
		try {
			String str=PDFToBase64(new File("E:\\11.jpg"));
			System.out.println(str);
//			Base64ToPDf(str, "E:\\于子洋1.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
