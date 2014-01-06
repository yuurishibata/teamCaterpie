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
#を、連関規則の各ペアを一つの文字列ベクトルに結合する
#形でベクトルを再構成する、ペアリング関数
getPair <- function(vectors){
　
　#ポインタの位置をベクトルの先頭にする。
  pointer <- 1
  #カウント数を所有する変数。２列以上に行が
  #「連関規則」のペアの出力がまたがるときに
  #この変数が利用される。
  count <- 1
  #無限ループを避けるセキュリティカウント
  securityCount <- 0
　#繰り返し上限
  limit <- length(vectors)
  #返却用の文字列ベクトル
  results <- c()

  while(TRUE){
    #セキュリティカウント
    securityCount <- securityCount + 1
    #毎回必ずbufferを初期化する      
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

   #無限ループだと判断したら
   if(securityCount > 1000*1000){
      break
   }    

  }
}
isNextBlank <- function(first,second){
 
 if(first==""&&second==""){
    return(TRUE)
 }else{
    return(FALSE)    
 }

}
removeBlank <- function(string){
 s <- c(unlist(strsplit(string," ")))
 times <- length(s)-1
 count <- 0
 result <- c()
 for(i in 1:times){
  if(isNextBlank(s[i],s[i+1])){
     result <-  c(result,i+1)
     count <- count + 1
  }
 }
  if(count > 0){
    s <- s[-result]
    s <- remake(s)
    return(s)
 }else{
    s <- string
    return(s)
 
 }

}
removeBlanks <- function(as){

 times <- length(as)

 for(i in 1:times){
   
   as[i] <- removeBlank(as[i])

 }
 
 return(as)

}

remake <- function(splitedParts){ 

  result <- ""
  times <- length(splitedParts)

  for(i in 1:times){
     result <- paste(result,splitedParts[i],sep=" ")
  }

  result <- substring(result,2,nchar(result))
  return(result)

}
getRuleNumber <- function(string){
   
  index <- c(unlist(regexpr(" ",string)))
  number <- substring(string,0,index-1)

  return(as.numeric(number))

}
getLhs <- function(string){


   index1 <-  c(unlist(regexpr("[{]",string)))
   index2 <-  c(unlist(regexpr("[}]",string)))

   escape <- 0

   result <- substring(string,index1+1,index2-1)
   
   while((removePosition <- c(unlist(regexpr("[[:punct:]]",result))))!=-1){

   escape <- escape + 1

    x <- substring(result,0,removePosition-1)
    y <- substring(result,removePosition+1,nchar(string))

    result <- paste(x,y,sep="")

    if(escape > 100){
      break
    }

   }

   return(result)
}

getRhs <- function(string){

  index1 <-  c(unlist(regexpr("[{]",string)))
  index2 <-  c(unlist(regexpr("[}]",string)))
  #1個目のlhs以後の文字列の取得
  rest <- substring(string,index2+1,nchar(string))

  escape <- 0
  
  #そのrestの中でのrhs
  index1 <-  c(unlist(regexpr("[{]",rest)))
  index2 <-  c(unlist(regexpr("[}]",rest)))

  rest <- substring(rest,index1+1,index2-1)  
   #カンマなどをrhsから除去するプログラム
   while((removePosition <- c(unlist(regexpr("[[:punct:]]",rest))))!=-1){

   escape <- escape + 1

    x <- substring(rest,0,removePosition-1)
    y <- substring(rest,removePosition+1,nchar(rest))

    rest <- paste(x,y,sep="")

    if(escape > 100){
      break
    }

   }
 
  return(rest)    

}
getRealNumbers <- function(string){

  index <-  c(unlist(regexpr("[}]",string)))
  #1個目のlhs以後の文字列の取得
  rest <- substring(string,index+1,nchar(string))
  index2 <-  c(unlist(regexpr("[}]",rest)))
  rest <- substring(rest,index2+1,nchar(rest))
  rest <- c(unlist(strsplit(rest," ")))

  results <- c()

  for(i in 1:length(rest)){
   if((index <- c(unlist(regexpr("[[:punct:]]",rest[i]))))!=-1){
      results <- c(results,rest[i])
   }
  }

  return(results)

}
#文字列を分割した数を返却する。
#文字列を分割した数を返却する。
getNumber <- function(string){

    splited <- c(unlist(strsplit(string," ")))
          
    count <- 0

    for(i in 1:length(splited)){   
     if((index <- c(unlist(regexpr("[[:space:]]",splited[i])))) < 0){
                  count <- count + 1
     }

     if(splited[i]==""){
       count <- count -1
     }   
    }

   return(count)
      
}
makeCSVLine <- function(string){
 #返却用変数
 line <- c()
 
  num <- getRuleNumber(string)
  lhs <- getLhs(string)
  rhs <- getRhs(string)
  numbers <- getRealNumbers(string)
  
  line <- paste(num,lhs,sep=",")
  line <- paste(line,rhs,sep=",")
  #実数値4つ
  support <- numbers[1]
  confidence <- numbers[2]
  lconfidence <- numbers[3]
  lift <- numbers[4]

  line <- paste(line,support,sep=",")
  line <- paste(line,confidence,sep=",")
  line <- paste(line,lconfidence,sep=",")
  line <- paste(line,lift,sep=",")

  lhsNumber <- getNumber(lhs)
  line <- paste(line,lhsNumber,sep=",")
  rhsNumber <- getNumber(rhs)
  line <- paste(line,rhsNumber,sep=",")
  
  return(line)

}
makeCSVs <- function(strings){

  results <- c()
  
  times <- length(strings)

  for(i in 1:times){
   
   results <- c(results,makeCSVLine(strings[i]))
  
  }    
  
  return(results)
}

##############
##プログラム##
##############

library(arules)
library(arules)

#od <- read.csv("onsen.csv",header=TRUE,row.names=1)
#od <- as.matrix(od)
#ot <- as(od,"transactions")
#or <- apriori(ot,parameter=list(maxlen=3,support=0.04,confidence=0.55,ext=TRUE))
#output <- capture.output(inspect(or))
#as <- output

#連関規則の出力ファイルを引数に指定する

as <- readLines("Desktop.txt")
as <- as[-1]
as <- getPair(as)
as <- removeBlanks(as) 
as <- makeCSVs(as)

#ファイル出力file引数にファイル名を指定する
write(as,file="seventh.csv")

#事後処理
rm(as)
rm(countRemoval)
rm(getIndex)
rm(getLhs)
rm(getNumber)
rm(getPair)
rm(getRealNumbers)
rm(getRhs)
rm(getRuleNumber)
rm(removeBlanks)
rm(removeBlank)
rm(remake)
rm(makeCSVs)
rm(makeCSVLine)
rm(isNextBlank)
