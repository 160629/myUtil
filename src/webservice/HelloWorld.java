package webservice;

import javax.jws.WebService;

@WebService
public interface HelloWorld {
	
	public String sayhello(String name,int age);
}
