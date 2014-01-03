#語彙頻度集計表の文字列ベクトルを作成する

#ワーキングディレクトリにdirという名前のディレクトリが
#存在しないことを確認したのちこのプログラムを実行する。

#ディレクトリの作成
dirname <- "dir"
dirpath <- getwd()
dirpath <- paste(dirpath,"/",sep="")
dirpath <- paste(dirpath,dirname,sep="")
dir.create(dirpath)

#その中に各ブログデータが入っているテキストデータを作成する
allblogs <- readLines("sample-SJIS.txt")

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
#URLを削除する
allblogs <- removeURLs(allblogs)

###ここに記号処理のプログラムを挿入する。

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

 regs <- c("[[:digit:]]","[[:punct:]]")
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

#上のremoveCharactersFromBlogs関数の利用

allblogs <- removeCharactersFromBlogs(allblogs)


#ファイルの作成。ブログのデータの個数分
makefiles <- function(allblogs,path){

 times <- length(allblogs)
 
 filepath <- paste(path,"/",sep="")

 for(i in 1:times){
   #ファイル名を作る作業
   filename <- "entry"
   filename <- paste(filename,i,sep="")
   filename <- paste(filename,".txt",sep="")
   #ディレクトリと結びつける作業
   filepath <- paste(filepath,filename,sep="")
   write(allblogs[i],file=filepath)
   filepath <- paste(path,"/",sep="")

 }

}

#ファイルに各テキストデータを一つ一つ格納していく
#これによってRMeCabﾗｲﾌﾞﾗﾘの諸関数を実行することが出来る。
makefiles(allblogs,dirpath)

#先ほど作成した対象ディレクトリのパスを取得する関数
getPath <- function(dirname){

 #パスの作成
 subjectDir <- getwd()
 subjectDir <- paste(subjectDir,"/",sep="")
 subjectDir <- paste(subjectDir,dirname,sep="")
 print(subjectDir)

}

#悲劇。語彙頻度集計表を作成するにあたり、ディレクトリの新規作成は
#不必要だということが分かった。

#整序された語彙頻度集計表を作成する関数
getPath <- function(dirname){


 subjectDir <- getwd()
 subjectDir <- paste(subjectDir,"/",sep="")
 subjectDir <- paste(subjectDir,dirname,sep="")

 return(subjectDir)
 
}

#パスに指定されたファイルの語彙頻度集計表を整序した形で作成する関数
getSortedWordFreq <- function(filename){

 
 path <- getPath(filename) 
 wordFreq <- RMeCabFreq(path)

 #wordFreqオブジェクトから
 #"名詞"・"形容詞"・"動詞"・"助動詞"を取り出して
 #かつ、細分類が"非自立"でないもののみを抽出する

 wordFreq <- wordFreq[(wordFreq$Info1=="名詞"|
                       wordFreq$Info1=="形容詞"|
                       wordFreq$Info1=="動詞"|
                       wordFreq$Info1=="助動詞")
                       &(wordFreq$Info2 != "非自立"),]

 #テキストデータ全体で少なくとも3回の単語の出現
 #がなければ、集計表のリストに載らない
 wordFreq <- wordFreq[wordFreq$Freq > 3, ]
 #ソート作業
 wordFreq <- wordFreq[rev(order(wordFreq$Freq)),]
 #結果返却
 return(wordFreq)

}

#この中で正規表現でアルファベットのものとパンクチュエーション
#を取り除いた表を作る関数

#さらに、ひとが必要ないと捉える「あれ」「これ」「こと」などを削除
#していく関数が必要になるのでそれを追加する。
