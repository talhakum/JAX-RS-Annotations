    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restService;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Encoded;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.annotations.Form;


/**
 *
 * @author talha
 */
@Path("/test")
public class test {
    static Map<Integer, String> employee = new HashMap<>();

	static{
		employee.put(1, "talha kum");
		employee.put(2, "ramiz gorkem birdal");
		employee.put(3, "muhammed erdem isenkul");
	}
    @GET
    @Produces("application/json")
    @Path("/get")
    public String getir() {
        String dondurulecek="";
        for (Map.Entry<Integer, String> entry : employee.entrySet()) {
           dondurulecek+=entry.getKey() +" " +entry.getValue()+"\n";
        }
        return dondurulecek;   
    }
    @POST
    @Consumes("application/json")
    @Path("/ekle")
    public String ekle(Employee ekleEmployee) {
        employee.put(ekleEmployee.getEmployeeId(), ekleEmployee.getEmployeeName()+" "+ekleEmployee.getEmployeeSurname());
        String dondurulecek="";
        for (Map.Entry<Integer, String> entry : employee.entrySet()) {
           dondurulecek+=entry.getKey() +" " +entry.getValue()+"\n";
        }
        System.out.println(dondurulecek);
        return dondurulecek;  
    }
    
    @PUT
    @Consumes("application/json")
    @Path("/guncelle") 
    public String guncelle(Employee ekleEmployee) {
        
       for(Integer key : employee.keySet()) {
            if(key ==ekleEmployee.getEmployeeId())
            {
                employee.put(key, ekleEmployee.getEmployeeName()+" "+ekleEmployee.getEmployeeSurname());
                break;
            }
        }
        String dondurulecek="";
        for (Map.Entry<Integer, String> entry : employee.entrySet()) {
           dondurulecek+=entry.getKey() +" " +entry.getValue()+"\n";
        }
        System.out.println(dondurulecek);
        return dondurulecek;    
    }
 
    @DELETE
    @Path("/sil/{id}")
    @Consumes("application/json")
    public String sil(@PathParam("id") int id) {
        
        
        for(Integer key : employee.keySet()) {
            if(key == id)
            {
                System.out.println("Silinen: "+employee.get(key));
                employee.remove(key);
                break;
            }
        }
        
        String dondurulecek="";
        for (Map.Entry<Integer, String> entry : employee.entrySet()) {
           dondurulecek+=entry.getKey() +" " +entry.getValue()+"\n";
        }
        System.out.println(dondurulecek);
        return dondurulecek;   
        
    }
    @GET
    @Path("/getir/{advancedPath}")
    @Produces("application/json")
    public void getRecord(@PathParam("advancedPath") PathSegment advanced) {
        MultivaluedMap<String,String> gets=advanced.getMatrixParameters();
        System.out.println(gets.getFirst("ad"));
        System.out.println(gets.getFirst("soyad"));
    } 
    
    @GET
    public String getQueryWithHeader(@QueryParam("getQuery") String gottenValue,
            @HeaderParam("Host") String host) {
        return gottenValue+"\n"+host;
    }
    
    @GET
    public String getQuery(@QueryParam("getQuery") String getQuery) {
        return getQuery;
    }
      
     @GET 
     @Produces(MediaType.TEXT_HTML+";charset=UTF-8")
    public String getMatrix(@MatrixParam("getMatrix") 
    @DefaultValue("Varsayılan Değer.") String gottenValue) {
        return gottenValue;
    }
     
    
    
    @GET
    @Path("/talha/{parametre1}")
    public String getPath(@PathParam("parametre1") String yas) {
        
        return yas; 
    }

    @Path("/formParemeters")
    @Produces(MediaType.TEXT_HTML+";charset=UTF-8")
    @POST
    public Response formParametreExample(@Form  Employee employee ) {
        return Response.status(200).entity(employee.employeeId+"<br>"+
                employee.employeeName+"<br>"+employee.employeeSurname).build();
    }
    
   
  
    @GET
    @Path("/cookie")
    @Produces(MediaType.TEXT_HTML+";charset=UTF-8")
    public String getCookie(@CookieParam("ad") Cookie firstName,
                            @CookieParam("soyad") Cookie lastName){
        return "Cookie 1 ad: "+firstName.getValue()+"<br>"+"Cookie 2 soyad: "+lastName.getValue();
    }
    
    
    
    
    @Produces(MediaType.TEXT_HTML)
    @GET
    @Encoded
    public String returnParameters(@QueryParam("queryParam") String queryParam){	
        String decode="";
        try {
            decode=URLDecoder.decode(queryParam, "UTF-8");
        } catch (Exception e) {
        }
		return "Kodlanmis cikti "+queryParam+"<br>"+"Cozulmus cikti: "+decode;
	}
    
    
}   