package webservice;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class MainClient {

	public static void main(String[] args) {
		
		JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
		jaxWsProxyFactoryBean.setAddress("http://localhost:6400/Cxf_test");
		jaxWsProxyFactoryBean.setServiceClass(HelloWorld.class);
		HelloWorld helloWorld = (HelloWorld) jaxWsProxyFactoryBean.create();
		String sayhello = "";
		try {
			sayhello = helloWorld.sayhello("king", 19);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(sayhello);

	}

}
