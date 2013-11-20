public class HashCodeDBTest {

    public static void main(String[] args) {

	// テスト関数
	HashCodeDB hcdb = new HashCodeDB();
	RData rd = hcdb.getRData("フリル", "ウエスト");
	System.out.println(rd.getLift());
	RData rd2 = hcdb.getRData("ワンピース", "素敵");
	System.out.println(rd2.getSupport());
	RData rd3 = hcdb.getRData("協創型", "ソフトウエア開発");
	System.out.println(rd3.getConfidence());

    }
}
