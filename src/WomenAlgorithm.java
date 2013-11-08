public class WomenAlgorithm implements AlgorithmInterface {

    String title;
    String content;
    String[] keywords = { "キレイ", "きれい", "リボン", "カーラー", "ポーチ", "化粧", "マスカラ",
			  "ブラジャー", "ヘアピン", "カチューシャ", "サロン", "ハーフアップ", "ネイル", "美脚", "イアリング",
			  "ヒール", "キャミソール", "フリル", "レースピース", "ワンピース", "ワンピ", "スカート", "ブラウス",
			  "ショーパン", "ワンピース", "かわい", "可愛", "カワイ", "女性", "フェミニン", "わたし", "私",
			  "女の子", "ボーイッシュ", "ショートパンツ" };

    private final static int RESULT = 2;

    public WomenAlgorithm(String title, String content) {

	this.title = title;
	this.content = content;

    }

    public boolean process() {

	for (int i = 0; i < keywords.length; i++) {

	    if (title.contains(keywords[i]) || content.contains(keywords[i])) {

		return true;

	    }
	}

	return false;
    }

    /**
     * public int getValue() { return this.value; }
     **/

}
