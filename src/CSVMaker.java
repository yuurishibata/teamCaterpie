import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Filtaration {

    public static void main(String[] args) throws IOException {

	// タイトルと本文が分かれていないのテキスト
	String allTexts;
	// タイトルと本文が分かれているテキスト
	String[] contents;
	String path = "sample.txt";
	BufferedReader in = new BufferedReader(new FileReader(path));

	for (int i = 0; i < 500; i++) {

	    // もし、テキストの取得に失敗したら、ただちに処理を終了。
	    if ((allTexts = in.readLine()) == null) {
		break;
	    }

	    // データをURL、TITLE、TEXTに分ける
	    contents = allTexts.split("\t");

	    // タイトルとテキストが無い場合は次のブログへ
	    if (contents.length < 2) {
		continue;
	    }

	    FileOutputStream csvfile = new FileOutputStream(
							    "C:\\Users\\to\\Documents\\rdoc\\each-blog" + i + ".txt");
	    OutputStreamWriter out = new OutputStreamWriter(csvfile,
							    "Shift_JIS");

	    out.write(i + 1 + "\t" + contents[1] + "\t" + contents[2] + "\n");
	    out.close();


	}

	in.close();

    }



}
