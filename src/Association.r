＃kaimon.csvのファイルに合わせておく

kaimon.rule <- apriori(kaimon.tran)
summary(kaimon.rule)
inspect(kaimon.rule)

＃↓サポート0.2以上、コンフィデンスは0.7以上に限定したもの。
＃信頼度
＃　相関の強さ。xに続けてyがおこる割合のパーセント表示。
＃支持度
＃　データベー全体で、その組み合わせが起こる割合。
＃リフト値
＃　xが起こった条件によるyが起こる尤度（確からしさ）の増加を示す。


kaimon.rule <- apriori(kaimon.tran, parameter = list(supp= 0.2, conf=0.7, target="rules"))
summary(kaimon.rule)
inspect(kaimon.rule)
