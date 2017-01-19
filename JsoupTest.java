package test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTest {

	public static void main(String[] args) throws IOException {
		System.setProperty("http.proxyHost", "proxy.nagaokaut.ac.jp");
        System.setProperty("http.proxyPort", "8080");
		
		Document document = Jsoup.connect("http://www.tobyo.jp/reference/1-2-1-0.php?Regist=0&keyword=%E3%82%A2%E3%83%90%E3%82%B9%E3%83%81%E3%83%B3").get();
        //System.out.println(document.html());
        //Element element = document.getElementById("site-sort");
        //Elements dlTags = document.getElementsByTag("dl");
		Element divTag = document.getElementById("site-sort");
		Elements dlTags = divTag.select("dl");
		for (Element dlTag:dlTags){
			Elements aTags = dlTag.select("dt a.site-title");
			for (Element aTag:aTags){
				System.out.println("ブログ記事タイトル:" + aTag.text());
			}
			Elements ddTags = dlTag.select("dd");
			for (Element ddTag:ddTags){
				if(ddTag.className().equals("site-title-icon")){
					Elements aTagsInDDTag = ddTag.select("a");
					for (Element aTag:aTagsInDDTag){
						
						if(aTag.className().equals("site-title")){
							System.out.println("ブログタイトル:" + aTag.text());
						}
						else if(aTag.className().equals("inp-link")){
							System.out.println("病名:" + aTag.text());
						}
						else if(aTag.className().equals("")){
							String href = aTag.select("[href]").text();
							System.out.println("href:" + href);
						}
//						else{
//							System.out.println("aTag.className():" + aTag.className());
//						}
					}
					
//					String blogTitle = ddTag.select("a.site-title").text();
//					String blogTitle = ddTag.select("a.site-title").text();
//					System.out.println("ブログタイトル:" + blogTitle);
//					String diseaseName = ddTag.select("a.inp-link").text();
//					System.out.println("病名:" + diseaseName);
//					
//					if(a){
//						
//					}
//					String href = ddTag.select("a[href]").attr("href");
//					System.out.println("href:" + href);
					
				}else if(ddTag.className().equals("site-url")){
					System.out.println("URL:" + ddTag.text());
				}else{
					System.out.println("スニペット:" + ddTag.text());
				}
				
			}
			//System.out.println(dlTag);
			
			System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		}
		
		//System.out.println(divTag);
		
//		Elements ddTags = document.select("dl dd");
//        for (Element ddTag:ddTags) {
//        	//Elements ddTag = ddTags.getElementsByTag("dd");
//        	//System.out.println(ddTag.outerHtml());
//        	//System.out.println(ddTag.className());
//        	if(ddTag.className().equals("site-url")){
//        		System.out.println("site-url:" + ddTag.getElementsByClass("site-url").text());
//        		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
//        	}
//        	//System.out.println("site-url:" + ddTag.getElementsByClass("site-url").text());
//        	//System.out.println("-------------------------------------------------------------------------------------------------------------------------");
//        }
//        Elements aTags = document.select("dl dt a");
//        for (Element aTag:aTags) {
//        	if(aTag.className().equals("site-title")){
//        		System.out.println("site-title:" + aTag.getElementsByClass("site-title").text());
//        		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
//        	}
//        	
//        }

	}

}
