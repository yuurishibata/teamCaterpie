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
        String allTexts;
        String[] contents;
        
        BufferedReader in = new BufferedReader(new FileReader(path));
        //返却値の取得
        int result;

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
            result = execute(contents[1],contents[2]);
            //仮のアルゴリズムの結果出力
            System.out.println(result);
            
            
            //SearchText st = new SearchText(i+1,contents[1],contents[2],"メンズ");
            
            //st.search();
            

        }

        in.close();
        
        
        
    }
    
    private static int execute(String title,String text){
	
	//ポリモーフィズム
	AlgorithmInterface alg = new MorphemeAlgorithm(title,text);
	//事前処理
	alg.preProcess();
	//アルゴリズム実行
	alg.process();
	//事後処理
	alg.postProcess();
	//デバッグ用出力
	//alg.print();
	//結果返却
	return alg.getIntegerResult();
	
    }


}
