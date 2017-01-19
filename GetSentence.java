package test;

public class GetSentence {

	public static void main(String[] args) {
		debug();

	}
	
	public static void debug(){
		String originalText = "<dd>...   2008年10月15日  このブログを購読する        <B>アクテムラ</B>、継続出来ることになりました♪  [ <B>アクテムラ</B> ]        昨日は午後から病院へ先日の血液検査の結果を聞きに行きました結果は前回98だった数値が、とりあえず60台まで下がったので<B>アクテムラ</B>を継続出来ることになりましたやったー本当に良......かったです、もう他に使えるの無いんだもんそして、飲み薬も、プレドニゾロンが１日、9ミリから8ミリに減量27日の<B>アクテムラ</B>の時、調子が良ければ、また減らすそうです嬉しいなっ最近は、寒いせいか、肩が上がらなくなったり、手が痛かったりしてますが全体的に見たら、調子は良いと言えま...</dd>";
		String removeDDTagText = removeDDTag(originalText);
		System.out.println(removeDDTagText);
		String removeBTagText = removeBTag(removeDDTagText);
		System.out.println(removeBTagText);

	}
	
	//<dd>... ...<dd>を削除
	public static String removeDDTag(String originalText){
		String removeDDText = "";
		removeDDText = originalText.substring(7,originalText.length()-8);
		return removeDDText;
	}
	
	public static String removeBTag(String beforeText){
		String afterText = "";
		afterText = beforeText.replaceAll("</B>","");
		afterText = afterText.replaceAll("<B>","");
		return afterText;
	}

}
