import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class RDataDB {

    // Rのデータを管理するHashMap
    private HashMap<String, RData> RMap = new HashMap<String, RData>();

    // コンストラクタ
    public RDataDB() {

	try {
	    // 各単語の実数値をRの統計データから登録する
	    setRData();

	} catch (IOException ioe) {

	    ioe.printStackTrace();
	    setEmergencyRData();

	}

    }

    private void setRData() throws IOException {
	// path
	String path = "test.csv";
	// reader
	//BufferedReader input = new BufferedReader(new FileReader(path));
	BufferedReader input = new BufferedReader( new InputStreamReader( new FileInputStream(path),"Shift-JIS"));
	// one line
	String line;
	String[] RDataWithMorpheme;
	// each R Data
	double support;
	double confidence;
	double lift;

	// setup each RData
	while ((line = input.readLine()) != null) {

	    RDataWithMorpheme = line.split(",");
	    // change Type from String to Double
	    support = Double.parseDouble(RDataWithMorpheme[2]);
	    confidence = Double.parseDouble(RDataWithMorpheme[3]);
	    lift = Double.parseDouble(RDataWithMorpheme[4]);

	    RMap.put(RDataWithMorpheme[1], new RData(support, confidence, lift));

	}

	// close
	input.close();
    }

    private void setEmergencyRData() {

    }

    public RData getRData(String word) {

	// もし、その単語の形態素を登録していない場合
	if (RMap.containsKey(word) == false) {
	    // 全ての値が「0.0」のRDataを返却
	    return new RData();
	} else {
	    // 登録されているRの統計データを返却
	    return RMap.get(word);

	}

    }

}
