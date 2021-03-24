package zytb.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class WriteText {
	
	public static void print(String text){
		String path = WriteText.class.getResource("/").getPath();
		OutputStream outputStream = null;
		OutputStreamWriter out = null;
		try {
			outputStream = new FileOutputStream(path+"zytb.log",true);
			out = new OutputStreamWriter(outputStream,"UTF-8");
			out.write((text+"\r\n"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(out!=null){
					out.close();
				}
				if(outputStream!=null){
					outputStream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
