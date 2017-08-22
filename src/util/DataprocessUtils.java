package util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import entity.Auth;
import entity.Res;
import entity.Role;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class DataprocessUtils {
	
	/**
	 * 将图片压缩按本来的长宽比例压缩为100宽度的jpg图片
	 * @param inputStream
	 * @param realPath /surveyLogos目录的真实路径，后面没有斜杠
	 * @return 将生成的文件路径返回 surveyLogos/4198393905112.jpg
	 */
	public static String resizeImages(InputStream inputStream, String realPath) {
		
		OutputStream out = null;
		
		try {
			//1.构造原始图片对应的Image对象
			BufferedImage sourceImage = ImageIO.read(inputStream);

			//2.获取原始图片的宽高值
			int sourceWidth = sourceImage.getWidth();
			int sourceHeight = sourceImage.getHeight();
			
			//3.计算目标图片的宽高值
			int targetWidth = sourceWidth;
			int targetHeight = sourceHeight;
			
			if(sourceWidth > 100) {
				//按比例压缩目标图片的尺寸
				targetWidth = 100;
				targetHeight = sourceHeight/(sourceWidth/100);
				
			}
			
			//4.创建压缩后的目标图片对应的Image对象
			BufferedImage targetImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
			
			//5.绘制目标图片
			targetImage.getGraphics().drawImage(sourceImage, 0, 0, targetWidth, targetHeight, null);
			
			//6.构造目标图片文件名
			String targetFileName = System.nanoTime() + ".jpg";
			
			//7.创建目标图片对应的输出流
			out = new FileOutputStream(realPath+"/"+targetFileName);
			
			//8.获取JPEG图片编码器
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			
			//9.JPEG编码
			encoder.encode(targetImage);
			
			//10.返回文件名
			return "surveyLogos/"+targetFileName;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;
		} finally {
			//10.关闭流
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	/**
	 * 根据源字符串进行加密
	 * @param source
	 * @return
	 */
	public static String md5(String source) {
		
		if(source == null || source.length() == 0) {
			return null;
		}
		
		char[] characters = new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		StringBuilder builder = new StringBuilder();
		
		//1.获取源字符串对应的字节数组
		byte[] sourceBytes = source.getBytes();
		
		try {
			//2.获取MessageDigest对象
			MessageDigest digest = MessageDigest.getInstance("md5");
			
			//3.执行加密操作
			byte[] targetBytes = digest.digest(sourceBytes);
			
			//4.遍历数组
			for (int i = 0; i < targetBytes.length; i++) {
				byte b = targetBytes[i];
				
				//5.获取高四位和低四位的值
				int high = (b >> 4) & 15;
				int low = b & 15;
				
				//6.从字符数组中取值
				char highChar = characters[high];
				char lowChar = characters[low];
				
				//7.拼接字符
				builder.append(highChar).append(lowChar);
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return builder.toString();
	}
	
	/**
	 * 基于序列化、反序列化技术实现深度复制
	 * @param source
	 * @return
	 */
	public static Serializable deeplyCopy(Serializable source) {
		
		if(source == null) {
			return null;
		}
		
		Serializable target = null;
		
		//1.声明四个变量，用来保存四个流对象
		ByteArrayInputStream bais = null;
		ByteArrayOutputStream baos = null;
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		
		//2.try{}catch(Exception e){}finally{}
		try{
			
			//3.创建字节数组输出流
			baos = new ByteArrayOutputStream();
			
			//4.创建对象输出流
			oos = new ObjectOutputStream(baos);
			
			//5.对源对象进行序列化操作
			//从IO的角度来看，这里实际上是oos将序列化数据通过baos写入到了字节数组中
			oos.writeObject(source);
			
			//6.通过字节数组输出流获取字节数组
			byte[] bufferByteArray = baos.toByteArray();
			
			//7.在字节数组的基础上创建对象输入流
			bais = new ByteArrayInputStream(bufferByteArray);
			
			//8.创建对象输入流
			//从IO的角度来看，其实是ois通过bais从字节数组中读取到了对象序列化后的数据
			ois = new ObjectInputStream(bais);
			
			//9.通过对象输入流执行反序列化操作
			target = (Serializable) ois.readObject();
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			return null;
			
		}finally{
			
			if(oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		return target;
	}
	
	
	/**
	 * 从请求参数名中解析questionId
	 * @param key
	 * @return
	 */
	public static Integer parseQuestionId(String key) {
		
		String qIdStr = key.substring(1);
		
		return Integer.parseInt(qIdStr);
	}

	/**
	 * 将字符串数组转换为字符串
	 * [1,2,3]
	 * 1,2,3
	 * @param value
	 * @return
	 */
	public static String convertArr2Str(String[] value) {
		
		if(value == null || value.length == 0) {
			return "";
		}
		
		StringBuilder builder = new StringBuilder();
		
		for (String val : value) {
			builder.append(val).append(",");
		}
		
		//1,2,3,
		
		return removeLast(builder.toString());
	}
	
	/**
	 * 去除字符串最后的一个字符
	 * @param source
	 * @return
	 */
	public static String removeLast(String source) {
		
		if(source == null || source.length() == 0) {
			return source;
		}
		
		return source.substring(0, source.length()-1);
	}
	
	/**
	 * 将servletPath中后面附带的参数去掉
	 * 		/aaa/bbb/ccc
	 * 		/aaa/bbb/ccc/1
	 * 		/aaa/bbb/ccc/1/2
	 * 		/aaa/bbb/ccc/1/abc
	 * 		/aaa/bbb/ccc/ddd/paramStart/1[项目中没有，不考虑]
	 * @param servletPath
	 * @return
	 */
	public static String cutServletPath(String servletPath) {
		
		String[] split = servletPath.split("/");
		
		//考虑数组中第一个元素是空字符串的问题
		return "/"+split[1]+"/"+split[2]+"/"+split[3];
	}
	
	/**
	 * 计算权限码数组
	 * @param maxPos
	 * 		为了加1后得到用户权限码数组的长度
	 * @param roleSet
	 * 		不管是Admin还是User都是根据Role的集合来计算的
	 * @return
	 */
	public static String calculatCode(int maxPos, Set<Role> roleSet) {
		
		//1.根据最大的权限位的值计算用户权限码数组的长度
		int length = maxPos + 1;
		
		//2.创建用户权限码数组，使用int类型的好处是默认值为0
		int[] codeArr = new int[length];
		
		//3.如果roleSet无效，则直接将默认值数组转换为字符串返回，此时各个权限位上都是0
		if(roleSet == null || roleSet.size() == 0) {
			//各个权限位上的值应该都是0
			return converCodeArr2Str(codeArr);
		}
		
		//4.逐层遍历，目标是得到每一个Res对象
		for(Role role : roleSet) {
			Set<Auth> authSet = role.getAuthSet();
			for (Auth auth : authSet) {
				Set<Res> resSet = auth.getResSet();
				for (Res res : resSet) {
					
					//5.获取当前资源对象的权限码和权限位
					Integer resCode = res.getResCode();
					Integer resPos = res.getResPos();
					
					//6.以资源的权限位为下标从用户权限码数组中获取曾经保存过的值
					int oldValue = codeArr[resPos];
					
					//7.将用户权限码数组中取出的值和资源的权限码做或运算，得到新值
					int newValue = resCode | oldValue;
					
					//8.将新值放回数组
					codeArr[resPos] = newValue;
				}
			}
		}
		
		return converCodeArr2Str(codeArr);
	}
	
	/**
	 * 将int数组转换为字符串
	 * @param codeArr
	 * @return
	 */
	public static String converCodeArr2Str(int[] codeArr) {
		
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < codeArr.length; i++) {
			int code = codeArr[i];
			builder.append(code).append(",");
		}
		
		String codeStr = builder.toString();
		
		return removeLast(codeStr);
	}
	
	/**
	 * 验证权限
	 * @param codeArrStr
	 * @param res
	 * @return
	 * 	true：可以访问
	 * 	false：不可以访问
	 */
	public static boolean checkAuthority(String codeArrStr, Res res) {
		
		String[] split = codeArrStr.split(",");
		
		Integer resCode = res.getResCode();
		Integer resPos = res.getResPos();
		
		String codeStr = split[resPos];
		
		Integer code = Integer.parseInt(codeStr);
		
		Integer result = resCode & code;
		
		return result != 0;
	}
	
	/**
	 * 根据偏移量创建日志表的表名
	 * @param offset
	 * 		-1：上个月
	 * 		0：当前月
	 * 		1：下个月
	 * @return
	 */
	public static String generateTableName(int offset) {
		
		//2016_10→2017_01
		Calendar calendar = Calendar.getInstance();
		
		calendar.add(Calendar.MONTH, offset);
		
		Date time = calendar.getTime();
		
		//注意，年和月之间不能使用减号，用减号会被当做减法
		return "AUTO_LOG_"+new SimpleDateFormat("yyyy_MM").format(time);
	}
	
	/**
	 * 根据日志表表名的List集合生成子查询字符串
	 * SELECT * FROM `auto_log_2016_10` UNION
	 * SELECT * FROM `auto_log_2016_11` UNION
	 * SELECT * FROM `auto_log_2016_12` UNION
	 * SELECT * FROM `auto_log_2017_01`
	 * @param tableNameList
	 * @return
	 */
	public static String generateSubQuery(List<String> tableNameList) {
		
		if(tableNameList == null || tableNameList.size() == 0) {
			return "";
		}
		
		StringBuilder builder = new StringBuilder();
		
		for (String tableName : tableNameList) {
			builder.append("SELECT * FROM ").append(tableName).append(" UNION ");
		}
		
		String subQuery = builder.toString();
		
		return subQuery.substring(0, subQuery.lastIndexOf(" UNION "));
	}
}
