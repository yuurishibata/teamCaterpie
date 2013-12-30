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
