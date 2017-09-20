package send;

/*import java.io.IOException;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;*/
 
public class SendMessage {
 
   /* *//**  注意需要引入包：commons-logging-1.1.1.jar、commons-httpclient-3.1.jar、commons-codec-1.4.jar
     * @param args
     * @throws IOException
     * @throws HttpException
     *//*
    public static void main(String[] args) throws HttpException, IOException {
        HttpClient client=new HttpClient();
        PostMethod post=new PostMethod("http://gbk.sms.webchinese.cn");
        //在头文件中设置转码
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");
        NameValuePair[] data={
                new NameValuePair("Uid","网建注册用户名"),
                new NameValuePair("Key","短信密钥"),
                new NameValuePair("smsMob","接收号码"),
                new NameValuePair("smsText","短信内容")       
        };
        post.setRequestBody(data);
        client.executeMethod(post);
        Header[] headers=post.getRequestHeaders();
        int statusCode=post.getStatusCode();
        System.out.println("statusCode:"+statusCode);
        for(Header h:headers){
            System.out.println(h.toString());
        }
        String result=new String(post.getResponseBodyAsString().getBytes("gbk"));
        System.out.println(result);
        post.releaseConnection();
                 */
 
    }