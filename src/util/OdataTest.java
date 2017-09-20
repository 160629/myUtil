package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

import org.junit.Test;

import com.sun.org.apache.xml.internal.security.utils.Base64;


@SuppressWarnings("all")
public class OdataTest {
	public static Object[] getJsonResult(String resultJson) {	
		JSONObject resultObject = null;
		Object[] array = null;
		try {
			resultObject = JSONObject.fromObject(resultJson);
			JSONObject d = (JSONObject) resultObject.get("d");
			array = d.values().toArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (array == null) {
			array = new Object[0];
		}
		return array;
	}
	@Test
	public  void getList(){ 
		String ss ="{d:{EntitySets:[Operations,OPEScenarios,App]}}";
		getJsonResult(ss);
	}
	
	//解析字符串
	public static Map getData(String json,Map<String, List<Object>> medata){
		Map<String, String> data = new HashMap<String, String>();
		// 将json字符串转换成jsonObject
		JSONObject  jasonObject = JSONObject.fromObject(json);
		Iterator ite = jasonObject.keys();
		// 遍历jsonObject数据,添加到Map对象
		while (ite.hasNext()) {
			String key = ite.next().toString();
			String value = jasonObject.get(key).toString();
			data.put(key, value);
			
			if(value.startsWith("[")){  
				JSONArray fromObject = JSONArray.fromObject(value);	
				for (Object object : fromObject) {
					if(object.toString().contains(":")){
						getData(object.toString(),medata);
					}
				}
			}
			else if(value.startsWith("{")){
				getData(value, medata);
			}
		}
		
		return data;
	}
	
	
	//解析字符串把key相同的放一个组
	public static Map getMapData(String json,Map<String, List<Object>> medata){
		
	
		// 将json字符串转换成jsonObject
		JSONObject  jasonObject = JSONObject.fromObject(json);
		Iterator ite = jasonObject.keys();
		// 遍历jsonObject数据,添加到Map对象
		while (ite.hasNext()) {
			String key = ite.next().toString();
			String value = jasonObject.get(key).toString();
			if(medata.containsKey(key)&& !key.equals("d")&& !key.equals("results")&& !key.equals("__metadata")&&!key.equals("ID")){
				medata.get(key).add(value);
			}else {
				
				if(!key.equals("d")&& !key.equals("results")&& !key.equals("__metadata")&&!key.equals("ID")){
					List list = new ArrayList();
					list.add(value);
					medata.put(key, list);
				}
				
			}
			
			if(value.startsWith("[")){  
				JSONArray fromObject = JSONArray.fromObject(value);	
				for (Object object : fromObject) {
					if(object.toString().contains(":")){
						getMapData(object.toString(),medata);
					}
				}
			}
			else if(value.startsWith("{")&&!key.equals("__metadata")){
				getMapData(value, medata);
			}
		}
		
		return medata;
	}
	
	@Test
	public void test(){

	}
	
	public static void getData(String path,String userName,String passWord) throws Exception{
		HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
		/*
		 * + "&$"+URLEncoder.encode(
		 * "filter=(id eq 'http://hqsd5.sinopec.com:50100/sap/opu/odata/sap/ZTT_STDF_QD_NB_DUMPS_D_SRV/E0CCMEXCPJ_TT_STDF_QD_NB_DUMPS_DResults('000000000013009013003013001013001013001011010013007012007_I0INFOPROV0010SMD_EA1DI0SMD_LSID001TR4I0SMD_SITY001%23I0SMD_TIHH001%23I0SMD_TIHM001%23I0CALDAY0012017.07.20I0CALMONTH0012017.07I0CALWEEK0012017.29')"
		 * ,"utf-8")).openConnection();
		 */
		// 打开读取流设置
		conn.setDoInput(true);
		// 把ODATA服务的用户名密码写入到conn连接对象中
		String encoding = new String(Base64.encode(new String(userName + ":"
				+ passWord).getBytes()));
		conn.setRequestProperty("authorization", ("Basic " + encoding));
		// 打开连接
		conn.connect();
		// 创建读写流对象获得数据流
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				conn.getInputStream(), "UTF-8"));
		// 获得json或者xml数据
		String line;
		StringBuffer data = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			data.append(line);
		}
		Map<String, List<Object>> medata = new HashMap<String, List<Object>>();
		Map data2 = getMapData(data.toString(), medata);
		System.out.println(data2.get("A20CALDAY"));
	}
	
	public static void main(String[] args) {
		long startTime=System.currentTimeMillis();
		String path ="http://10.5.127.236:50100/sap/opu/odata/sap/ZTT_STDF_QD_NB_DUMPS_D_1_SRV_/E0CCMEXCPJ_TT_STDF_QD_NB_DUMPS_D(A0CALDAY='20170627',A0CALDAYTo='20170627')/Results?$format=json";
		String userName ="pywang";
		String passWord ="init1213";
		try {
			getData(path,userName,passWord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		long endTime=System.currentTimeMillis();
		
		System.out.println("执行时间："+(endTime-startTime));
	}

}
