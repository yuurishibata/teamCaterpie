import java.util.ArrayList;

public class DegreeCalculationAlgorithm {

    private String title;
    private String text;
    private double menPoints;
    private double womenPoints;
    private String[] menWords = { "イケメン", "デッキシューズ", "ヒゲ", "ひげ", "ボクサー",
				  "トランクス", "カフス", "メンズ", "MENS", "Men's", "MEN'S", "男", "俺", "僕",
				  "Boy", "BOY", "ボーイ", "チノパン", "ミリタリー", "軍" };
    private String[] womenWords = { "キレイ", "きれい", "リボン", "カーラー", "ポーチ", "化粧",
				    "マスカラ", "ブラジャー", "ヘアピン", "カチューシャ", "サロン", "ハーフアップ", "ネイル", "美脚",
				    "イアリング", "ヒール", "キャミソール", "フリル", "レースピース", "ワンピ", "スカート", "ブラウス",
				    "ショーパン", "ワンピース", "かわい", "可愛", "カワイ", "女性", "フェミニン", "わたし", "私",
				    "女の子", "ボーイッシュ", "ショートパンツ" };
    private int textNumber;
    private ArrayList<String> matchWords = new ArrayList<String>();

    public DegreeCalculationAlgorithm(int textNumber, String title, String text) {
	this.textNumber = textNumber;
	this.title = title;
	this.text = text;
    }

    public void process() {

	System.out.printf("[%3d]", textNumber);

	// 男性
	for (String word : menWords) {

	    if (title.contains(word)) {
		System.out.print("1");
		matchWords.add(word);
	    }

	    if (text.contains(word)) {
		System.out.print("1");
		matchWords.add(word);
	    }
	}

	// 女性
	for (String word : womenWords) {

	    if (title.contains(word)) {
		System.out.print("2");
		matchWords.add(word);
	    }

	    if (text.contains(word)) {
		System.out.print("2");
		matchWords.add(word);
	    }
	}

	System.out.println("");
	for (String matchWord : matchWords) {

	    System.out.println(matchWord + ",");

	}

    }

}
