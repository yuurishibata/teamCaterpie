public interface AlgorithmInterface {
    //アルゴリズム前の事前処理
    public void preProcess();
    //アルゴリズム処理
    public void process();
    //アルゴリズム後の事後処理
    public void postProcess();
    //ブログテキストの適性の返却
    public boolean isQualified();
    //デバッグ用コンソール出力
    public void print();
    //整数値の返却(executeメソッドの返却値)
    public int getIntegerResult();
    //実数値の返却(processメソッドの計算結果)
    public double getDoubleResult();
    
}
