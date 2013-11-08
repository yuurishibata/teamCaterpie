public class MenAlgorithm implements AlgorithmInterface {

    private String title;
    private String content;
    private String[] keywords = { "デッキシューズ", "ヒゲ", "ひげ", "ボクサー", "トランクス",
				  "カフス", "メンズ", "MENS", "Men's", "MEN'S", "男", "俺", "僕", "Boy",
				  "BOY", "ボーイ", "チノパン" };

    private final static int RESULT = 1;

    public MenAlgorithm(String title, String content) {

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
