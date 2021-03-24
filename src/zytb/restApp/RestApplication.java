package zytb.restApp;  
  
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;  
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;  
   
/** 
 * 应用 
 * @author waylau.com 
 * 2014-3-18 
 */  
public class RestApplication extends ResourceConfig {  
    public RestApplication() {  
   
     //服务类所在的包路径  
     packages("zytb.rest.impl");  
     //注册JSON转换器  
     register(JacksonJsonProvider.class); 
     register(MultiPartFeature.class);
    }  
}