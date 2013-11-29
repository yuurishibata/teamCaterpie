import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Filtaration {

    public static void main(String[] args) throws IOException {

	//テスト
        test();

    }

    public static void test() throws IOException {

        String path = "sample.txt";

        BufferedReader in = new BufferedReader(new FileReader(path));

        String allTexts;
        String[] contents;

        for (int i = 0; i < 300; i++) {

            // もし、テキストの取得に失敗したら、ただちに処理を終了。
            if ((allTexts = in.readLine()) == null) {
                break;
            }

            // 全てのデータを分解。文字列型の配列変数contentsに各要素を格納。
            contents = allTexts.split("\t");

            // タイトルとテキストデータを取得できない場合は、次のブログ記事に移動
            if (contents.length < 3) {
                continue;
            }
            
            
            System.out.printf("[%3d]", i+1);
            execute(contents[1],contents[2]);
            

        }

        in.close();
        
        
        
    }
    
    private static int execute(String title,String text){
	
	
	AlgorithmInterface d = new DispersionAlgorithm(title,text);
         
         
	return d.process();
	
    }


}
