package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class ChangeStringType {

	public static void main(String[] args) throws Exception{
		//Xcode("C:\\Users\\sase\\Desktop\\test\\mecabTest.txt","C:\\Users\\sase\\Desktop\\test\\output.txt");
		ChangeStringCode();
	}


	public static void Xcode(String ifname, String ofname){
		try {
			FileInputStream in  = new FileInputStream(ifname);
			InputStreamReader inx = new InputStreamReader(in,"Shift_JIS");
			FileOutputStream out = new FileOutputStream(ofname);
			OutputStreamWriter outx = new OutputStreamWriter(out,"UTF-8");

			//読み込みと書き込み
			int c;
			int ct = 0;
			while ((c = inx.read()) != -1) {
				outx.write(c);
				ct++;
			}
			//System.out.println("以上" + ct + "文字");

			inx.close();
			in.close();
			outx.close();
			out.close();
		} catch (IOException e) {
			System.out.println( ifname + " がないのでは？" );
		}
	}

	public static void ChangeStringCode() throws Exception{
		for(int page_key = 1; page_key <2; page_key ++){

			if(10 < page_key && page_key < 20){ continue; }

			String folderNameOfNoDuplication = "C:\\Users\\sase\\Desktop\\テキストファイル群(重複無し)\\" + getFolderName(page_key) + "\\";
			String folderNameOfUTF8 = "C:\\Users\\sase\\Desktop\\textFile_UTF-8\\" + getFolderName(page_key) + "\\";

			//ファイル名の一覧を取得する
			File file = new File(folderNameOfNoDuplication);
			File files[] = file.listFiles();

			for(int i=0; i<files.length; i++){

				//薬剤名取得(ex アーテン.txt)
				String medicineFileName = files[i].getName();

				//元のデータ格納用リスト
				List<String> checkList = new ArrayList<String>();

				//重複無しスニペットが出力されるハッシュセット
				LinkedHashSet<String> outputkHash = new LinkedHashSet<String>();

				// ファイルを読み込みモードでオープンする。ファイルが存在しなかったりする場合に FileNotFoundException がスローされる。
				FileReader fr = new FileReader(files[i]);

				// ファイルを読むための便利なクラス BufferedReader のオブジェクトを作る。
				BufferedReader br = new BufferedReader(fr);

				// 読み込んだ1行の文字列を格納するための変数を定義する。
				String line;

				// 1行目を読んでみる。もし、空のファイルなら、line には null がセットされる。
				line = br.readLine();

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

				//重複あり→なし
				//outputkHash = getNoDuplicationText(checkList);

				//出力ファイル生成
				//File newFile = new File(folderNameOfUTF8 + medicineFileName);
				Xcode(folderNameOfNoDuplication + medicineFileName , folderNameOfUTF8 + medicineFileName);
//				newFile.createNewFile();
//				FileWriter filewriter = new FileWriter(newFile);
//				String line_sep = System.getProperty("line.separator");
//
//				//出力ファイルに書き込む
//				for(Iterator<String> j = outputkHash.iterator(); j.hasNext();){
//					filewriter.write(j.next());
//					filewriter.write(line_sep);
//				}
//
//				filewriter.close();
			}

		}
	}

	//フォルダ名取得
	public static String getFolderName(int page_key) throws Exception{

		switch(page_key){

		case 1:
			return "あ行";

		case 2:
			return "か行";

		case 3:
			return "さ行";

		case 4:
			return "た行";

		case 5:
			return "な行";

		case 6:
			return "は行";

		case 7:
			return "ま行";

		case 8:
			return "や行";

		case 9:
			return "ら行";

		case 10:
			return "わ行";

		case 20:
			return "英数";

		default:
			return "";

		}

	}



}
