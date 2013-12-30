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

#これによって、クラスター解析や潜在意味インデキシングなどの
#関数を利用するための準備が出来た。
