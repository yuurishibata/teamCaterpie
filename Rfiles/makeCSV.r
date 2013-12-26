#注意
#このプログラムはプログラムを完了するとワークスペースに
#存在する変数あるいはオブジェクトをすべて削除するように
#作成されています。

#なのでその点に必ず配慮してください。


#ファイルの入力からCSVファイルの出力まで
library(RMeCab)
#入力ファイルのパスと出力ファイルのパスを指定する。
inputpath = "sample-SJIS.txt";
outputpath = "C:/Users/TO/Documents/R.csv"#最後はファイル名
#全てのテキストデータを読み込む
alltexts <- readLines(inputpath)
#この後、必要な関数を変数に格納する。
#形態素解析した文章を元通りに復元する関数
remake <- function(x){

       string <- x[1]

       for(i in 2:length(x)){

       string <- paste(string,x[i],sep="")
 #最後の引数にカンマを入れると各形態素をカンマ区切りに
 #してくれます。

 }
 
 return(string)

}
#引数の文字列を形態素解析する関数
#形態素の順番通りに数値を割り振る、この関数に引数の数値を渡すと
#その数値をそのエントリーのブログ番号として各列の先頭としてすべての行
#に追記する。
#library(RMeCab)
makeMorphemes <- function(string,number){

	      morphemes <- RMeCabC(string)
	      morphemes <- c(unlist(morphemes))
	      
	      blogNumber = number

	      result <- ""
	      times <- length(morphemes)#追加

	      for(i in 1:times){#length(morphemes)＝＞times
	      
	      number = i
	      morphemes[i]
	      
	      line <- paste(blogNumber,number,sep=",")
	      line <- paste(line,morphemes[i],sep=",")

	      if(i!=times){#追加
	      line <- paste(line,"\n")
	      }#追加

	      result <- paste(result,line)

	      #print(result)
		
		}

		return(result)

}
#上のカンマ区切りを利用した関数makeCSV.
#ブログのURLを活用した後に実行しなければならない。
makeCSV <- function(blogs){
 
 for(a in 1:length(blogs)){
 
 blogs[a] <- makeMorphemes(blogs[a],a)
 
 }
 return(blogs) 
}
#次にURLのみをブログから除去する関数を変数に追加。
#removeURL関数ダミーデータからURLを削除するプログラム
removeURL <- function(x){
  
  splited <- strsplit(x,"\t")
  Vsplited <- c(unlist(splited))
  title <- Vsplited[2]
  content <- Vsplited[3]
  
  return(contents <- paste(title,content,""))
 
 }
#removeURLs関数
 removeURLs <- function(x){
 
 
  for(i in 1:length(x)){
     x[i] <- removeURL(x[i])
  }
 
  return(x)
 }
#必要な関数が準備できたので、
#これからコードを記述していく。
alltexts <- removeURLs(alltexts)
alltexts <- makeCSV(alltexts)
write(alltexts,file=outputpath)
rm(list=ls())


