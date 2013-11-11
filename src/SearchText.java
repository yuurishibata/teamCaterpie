public class SearchText {

    private String title;
    private String text;
    private String word;
    private int index = 0;
    private int from = 0;
    private int to = 0;
    private int textNumber;

    public SearchText(int textNumber, String title, String text, String word) {
	this.textNumber = textNumber;
	this.title = title;
	this.text = text;
	this.word = word;
    }

    public void search() {

	// title
	if (title.contains(word)) {

	    index = title.indexOf(word);
	    title = title.replaceAll(word, "[**[" + word + "]**]");
	    from = 0;
	    to = title.length() - 1;
	    System.out.printf("[%3d]%s\n", textNumber,
			      title.substring(from, to));

	}

	// text
	if (text.contains(word)) {

	    index = text.indexOf(word);
	    text = text.replaceAll(word, "[**[" + word + "]**]");

	    // to
	    if (index + 80 < text.length() - 1) {
		to = index + 80;
	    } else if (index + 70 < text.length() - 1) {
		to = index + 70;
	    } else if (index + 60 < text.length() - 1) {
		to = index + 60;
	    } else if (index + 50 < text.length() - 1) {
		to = index + 50;
	    } else if (index + 40 < text.length() - 1) {
		to = index + 40;
	    } else if (index + 30 < text.length() - 1) {
		to = index + 30;
	    } else if (index + 20 < text.length() - 1) {
		to = index + 20;
	    } else if (index + 10 < text.length() - 1) {
		to = index + 10;
	    } else {
		to = index + word.length();
	    }

	    // from

	    if (index - 70 > 0) {
		from = index - 70;
	    } else if (index - 60 > 0) {
		from = index - 60;
	    } else if (index - 50 > 0) {
		from = index - 50;
	    } else if (index - 40 > 0) {
		from = index - 40;
	    } else if (index - 30 > 0) {
		from = index - 30;
	    } else if (index - 20 > 0) {
		from = index - 20;
	    } else if (index - 10 > 0) {
		from = index - 10;
	    } else {
		from = 0;
	    }

	    // 例外を出さないための2つの処理
	    if (from <= 0) {
		from = 0;
	    }

	    if (to >= text.length()) {
		to = text.length();
	    }

	    System.out
		.printf("[%3d]%s\n", textNumber, text.substring(from, to));

	}

    }
}
