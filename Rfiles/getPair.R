#正規表現を受け取って、その文字に合致する
#文字の位置を返却する関数
getIndex <- function(string,reg){

  index <- c(unlist(regexpr(reg,string)))

}

#正規表現に合致する文字の個数をカウントして
#その個数を返却する関数
countRemoval <- function(string,character){

   count <- 0
   while((index <- getIndex(string,character))!=-1){

    count <- count + 1

    x <- substring(string,0,index-1)
    y <- substring(string,index+1,nchar(string))

    string <- paste(x,y,sep="")
   }
   
   return(count)
}

#連関規則のコンソール出力を保持するオブジェクト
#を、連関規則のペアごとに一つのベクトルに格納するように
#再構成する関数。
getPair <- function(vectors){
　
　#ポインタの位置をベクトルの先頭にする。
  pointer <- 1
  #カウント数を所有する変数。２列以上に行が
  #「連関規則」のペアの出力がまたがるときに
  #この変数が利用される。
  count <- 1
　#繰り返し上限
  limit <- length(vectors)
  #返却用の文字列ベクトル
  results <- c()

  while(TRUE){

      
    buffer <- c()

    #bufferに暫定的に、文字列を格納する。
    #この時にcountの数だけループの回数を変化させる。
　　for(i in 1:count){
   
    buffer <- paste(vectors[pointer+(1-i)],buffer,sep="")
    
    }
   
    if(countRemoval(buffer,"[[:punct:]]")>= 10){
      #bufferが結果の一つのベクトルとして確定した。resultsに格納する。
      results <- c(results,buffer)
      #一行でだけでも「連関規則」のペアになる場合、あるいは
      #複数行でペアが確定した場合にはポインタの位置をずらす
      #ことが必要になる。
      pointer <- pointer + 1
      #カウントの初期化
      count <- 1

    }else{
      
      #一行では連関規則のペアにならないときのみ、
      #countに数値が加算される。
      count <- count + 1
      #ポインタの位置を後方にずらす
      pointer <- pointer + 1
  
    }

   #ループの回数が上限を上回る。返却値を返す。
   if(pointer > limit){
      return(results)
   }

  }
}
#関数テスト
output <- readLines("o.txt")
output <- output[-1]
results <- getPair(output)
as <- readLines("Desktop.txt")
as <- as[-1]
results2 <- getPair(as)
