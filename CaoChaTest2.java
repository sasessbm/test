package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.OutputStream;

public class CaoChaTest2 {

	public static void main(String[] args) {
		String cabochaPath = "C:\\Users\\sase\\Desktop\\CaboCha\\bin\\cabocha.exe";
		//String cabochaPath = "C:\\Program Files (x86)\\MeCab\\bin\\mecab.exe";
		String command     = cabochaPath;
		Process process    = null;
		try {
		    process = Runtime.getRuntime().exec(command);
		    java.io.OutputStream out= process.getOutputStream();
		    OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8");
		    //OutputStreamWriter writer = new OutputStreamWriter(out, "Shift_JIS");
		    writer.write("太郎は花子が読んでいる本を次郎に渡した");
		    writer.close();
		    java.io.InputStream is = process.getInputStream();
		    BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		    //BufferedReader br = new BufferedReader(new InputStreamReader(is, "Shift_JIS"));
		    String line;
		    while ((line = br.readLine()) != null) {
		        System.out.println(line);
		    }
		    process.destroy();
		    process.waitFor();
		} catch( Exception e ) {
		    System.out.println(e);
		}

	}

}
