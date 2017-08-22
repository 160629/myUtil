package webservice;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class MainServer {
	
	public static void main(String[] args) {
		JaxWsServerFactoryBean jaxWsServerFactoryBean = new JaxWsServerFactoryBean();
		jaxWsServerFactoryBean.setAddress("http://localhost:6400/Cxf_test");
		jaxWsServerFactoryBean.setServiceClass(HelloWorldImpl.class);
		Server server = jaxWsServerFactoryBean.create();
		server.start();
		
	}
}
