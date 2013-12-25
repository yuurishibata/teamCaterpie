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

