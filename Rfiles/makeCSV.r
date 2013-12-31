#ファイルの入力からCSVファイルの出力まで
library(RMeCab)
library(RMeCab)

#入力ファイルのファイル名
inputpath = "sample-SJIS.txt";
#出力ファイルのファイル名
filename = "onlyMorphemes.csv"

wd <- getwd()
wd <- paste(wd,"/",sep="")
wd <- paste(wd,filename,sep="")

#全てのテキストデータを読み込む
alltexts <- readLines(inputpath)

#不要語削除の鍵を握るプログラム
#正規表現を第二引数に指定しても機能する

removeCharacter <- function(string,character){ 

 while((index <- c(unlist(regexpr(character,string))))!=-1){

  #文章前半
  X <- substring(string,0,index-1)
  #文章後半
  Y <- substring(string,index+1,nchar(string))
  #文章の再構成
  string <- paste(X,Y,sep="")

  }

 return(string)

}

#複数の不要語を削除するプログラム
#引数に受け取るブログは1つのみ

removeCharacters <- function(blog){
 #正規表現
 regs <- c("[[:digit:]]","[[:punct:]]")
 #不要語をここに記述する
 unnecessaryWords <- c("★","☆","。","●","○")

 for(i in 1:length(regs)){

 blog <- removeCharacter(blog,regs[i])

 }

 for(i in 1:length(unnecessaryWords)){

 blog <- removeCharacter(blog,unnecessaryWords[i])

 }

 return(blog)

}

#複数のブログから不要な数値・記号を取り除く処理を行う。
removeCharactersFromBlogs <- function(allblogs){

 times <- length(allblogs)

 for(i in 1:times){

 allblogs[i] <- removeCharacters(allblogs[i])

 }

 return(allblogs)

}

#引数の文字列を形態素解析する関数
#形態素の順番通りに数値を割り振る、この関数に引数の数値を渡すと
#その数値をそのエントリーのブログ番号として各列の先頭としてすべての行
#に追記する。
makeMorphemes <- function(string,number){

  #形態素解析の処理
　morphemes <- RMeCabC(morphemes)
  morphemes <- c(unlist(morphemes))
              
  blogNumber = number

  #返却する文字列
  result <- ""
  times <- length(morphemes)

  for(i in 1:times){
              
  number = i
  morphemes[i]
              
  line <- paste(blogNumber,number,sep=",")
  line <- paste(line,morphemes[i],sep=",")
　
  #各ブログの間に一行空かないようにする
  #処理
  if(i!=times){

  line <- paste(line,"\n",sep="")
  
  }

  result <- paste(result,line)
                
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

  #タブ区切りで分割
  splited <- strsplit(x,"\t")
  #splitedのベクトル化
  Vsplited <- c(unlist(splited))
  #タイトル
  title <- Vsplited[2]
  #本文
  content <- Vsplited[3]
  #その両方
  contents <- paste(title,content,"")

  return(contents)
 
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
alltexts <- removeCharactersFromBlogs(alltexts)
alltexts <- makeCSV(alltexts)
write(alltexts,file=wd)

#事後処理
#このプログラムで作成したオブジェクトすべて削除する
rm(alltexts)
rm(filename)
rm(inputpath)
rm(makeCSV)
rm(removeURLs)
rm(removeURL)
rm(wd)
rm(makeMorphemes)
rm(removeCharactersFromBlogs)
rm(removeCharacters)
rm(removeCharacter)
