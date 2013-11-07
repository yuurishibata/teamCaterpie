import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Filtaration {

    public static void main(String[] args) throws IOException {

	test();

    }

    public static void test() throws IOException {

	String path = "sample.txt";

	BufferedReader in = new BufferedReader(new FileReader(path));

	String allTexts;// タイトルと本文が未分離のテキスト
	String[] contents;// タイトルと本文が分かれているテキスト

	for (int i = 0; i < 300; i++) {

	    // もし、テキストの取得に失敗したら、ただちに処理を終了。
	    if ((allTexts = in.readLine()) == null) {
		break;
	    }

	    // 全てのデータを分解。文字列型の配列変数contentsに各要素を格納。
	    contents = allTexts.split("");

	    // タイトルとテキストデータを取得できない場合は、次のブログ記事に移動
	    if (contents.length < 2) {
		continue;
	    }

	    // 出力
	    System.out.printf("[%3d] %d ", (i + 1),
			      execute(contents[1], contents[2]));

	    // 10件ごとに改行
	    if ((i + 1) % 10 == 0) {

		System.out.println("");

	    }

	}

	in.close();
    }

    public static int execute(String title, String content) {

	WomenAlgorithm wAlg = new WomenAlgorithm(title, content);
	MenAlgorithm mAlg = new MenAlgorithm(title, content);

	if (wAlg.process()) {

	    return 2;

	} else if (mAlg.process()) {

	    return 1;

	} else {

	    return 0;

	}

    }

}
