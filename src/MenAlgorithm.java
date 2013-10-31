public class MenAlgorithm implements AlgorithmStrategy {

    private String title;
    private String content;
    private String[] keywords = { "メンズ", "MENS", "Men's", "MEN'S", "男", "男性", "男性用",
			  "俺", "僕", "Boy", "BOY", "ボーイ" };

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

}
