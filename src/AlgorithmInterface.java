public interface AlgorithmInterface {
    
    //アルゴリズム前の事前処理
    public void preProcess();
    //アルゴリズム処理
    public void process();
    //アルゴリズム後の事後処理
    public void postProcess();
    //デバッグ用コンソール出力
    public void print();
    //整数値返却
    public int getIntegerResult();
    //実数値返却
    public double getDoubleResult();
}
