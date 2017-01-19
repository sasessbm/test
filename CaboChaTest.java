package test;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.omg.CORBA.portable.InputStream;

import com.cignoir.cabocha.Cabocha;
import com.cignoir.node.Chunk;
import com.cignoir.node.Sentence;
import com.cignoir.node.Token;

public class CaboChaTest {

	public static void main(String[] args) throws Exception{
		
		// ファイルを読み込みモードでオープンする。ファイルが存在しなかったりする場合に FileNotFoundException がスローされる。
		FileReader fr = new FileReader("C:\\Users\\sase\\Desktop\\test\\output.txt");
		//FileReader fr = new FileReader("C:\\Users\\sase\\Desktop\\test\\aaa.txt");

		// ファイルを読むための便利なクラス BufferedReader のオブジェクトを作る。
		BufferedReader br = new BufferedReader(fr);

		// 読み込んだ1行の文字列を格納するための変数を定義する。
		String line;

		// 1行目を読んでみる。もし、空のファイルなら、line には null がセットされる。
		line = br.readLine();
		
		//元のデータ格納用リスト
		List<String> checkList = new ArrayList<String>();

		// ファイルの最後まで来て null が返ってくるまで、処理を繰り返す。
		while( line != null )
		{
			// 1行読み込むに成功するたびに、リストに追加。
			checkList.add(line);

			// readLine メソッドを使ってもう1行読み込む。
			line = br.readLine();
		}

		// ストリームを閉じて、BufferedReader のリソースを開放する。
		// このとき、同時にFileReader のcloseも行われるので、fr.close() は必要ない。なので、ファイルもここで閉じられます。
		br.close();
		
		for(String str : checkList) {
			Run(str);
		}

		//Run("太郎は花子が読んでいる本を次郎に渡した");
		//		Cabocha cabocha = new Cabocha(/* cabocha.exe　のフルパス */"C:\\Users\\sase\\Desktop\\CaboCha\\bin\\cabocha.exe");
		//		byte[] bytes = "自然言語処理はマンマシンインタフェースの手法として魅力的である。".getBytes();
		//		System.out.println("自然言語処理はマンマシンインタフェースの手法として魅力的である。".equals(new String(bytes, "UTF-8")) );
		//		//String inputStr = new String("自然言語処理はマンマシンインタフェースの手法として魅力的である。".getBytes("UTF-16"), "UTF-8");
		//		Sentence sentence = cabocha.execute(new String(bytes, "UTF-8"));
		//		//System.out.println(sentence.getChunks());
		//		
		//		//List<Chunk> chunkList = new ArrayList<Chunk>();
		//		
		//		//chunkList = String eucjpStr = new String(sentence.getChunks().getBytes("EUC_JP"), "EUC_JP");
		//		List<Chunk> chunkList = sentence.getChunks();
		//		String tmp = "";
		//		String tmp2 = "";
		//		// Sample 1
		//		for (Chunk chunk : chunkList) {
		//			List<Token> tokens = chunk.getTokens();
		//			for (Token token : tokens) {
		//				tmp = new String(token.getBase().getBytes("UTF-8"), "UTF-8");
		//				tmp2 = new String(token.getBase().getBytes("UTF-8"), "UTF-8");
		//				System.out.println(tmp + ": " + tmp2);
		//			}
		//		}
	}

	//String型で文字列を受け取り、それを係り受け解析する関数
	public static void Run(String text){
		
		String cabochaPath = "C:\\Users\\sase\\Desktop\\CaboCha\\bin\\cabocha.exe";
		//String cabochaPath = "C:\\Program Files (x86)\\MeCab\\bin\\mecab.exe";
		//String command     = cabochaPath;
		//Process process    = null;
		try {

			//UTF-8のBOMを除去するための準備←textファイルから読み込む場合を考慮
			byte [] bytes = {-17, -69, -65};
			String btmp= new String(bytes, "UTF-8");

			//BOM除去
			text=text.replaceAll(btmp, "");

			//cabochaの実行開始　ラティス形式で出力(-f1の部分で決定、詳しくはcabochaのhelp参照)
			ProcessBuilder pb = new ProcessBuilder(cabochaPath, "-f1");
			//ProcessBuilder pb = new ProcessBuilder(cabochaPath, "");
			Process process = pb.start();

			//実行途中で文字列を入力(コマンドプロンプトで文字を入力する操作)
			OutputStreamWriter osw = new OutputStreamWriter(process.getOutputStream(), "UTF-8");
			osw.write(text);
			osw.close();

			//出力結果を読み込む
			java.io.InputStream is = process.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));

			//一行ずつ読み込むための文字列の変数を用意
			String Line="";

			//出力結果の各行毎に配列へ格納するためのリストを用意
			ArrayList out = new ArrayList();

			//最後の行までやり続ける
			while ((Line = br.readLine()) != null) {
				//読み込んだ行をリストへ格納
				out.add(Line);

				//行をコンソールへ表示
				System.out.println(Line);
			}

			//プロセス終了
			process.destroy();
			process.waitFor();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}



}
