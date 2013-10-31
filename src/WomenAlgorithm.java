public class WomenAlgorithm implements AlgorithmStrategy {

    private  String title;
    private String content;
    private String[] keywords = { "レースピース", "ワンピース", "ワンピ", "スカート", "ブラウス", "ショーパン",
			  "ワンピース", "かわい", "可愛", "カワイ", "女性", "フェミニン", "私", "女の子" };

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

}
