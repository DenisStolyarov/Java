package part1;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ConnectionInternet {

    public static  void  ReadText(String path){
        try{
            URL url=new URL(path);
            URLConnection connection=url.openConnection();
            InputStream urlData=connection.getInputStream();
            String type=connection.getContentType();
            if(type==null|| !type.startsWith("text")){
                throw  new Exception("URL does not a text files");
            }
            while(true){
                int data=urlData.read();
                if(data<0){
                    break;
                }
                System.out.print((char)data);
            }


        }catch (Exception e){
            System.out.print(e.getMessage());
        }

    }


}
