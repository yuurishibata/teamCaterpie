#バグが存在します。使用ｖしないでください。




#一つの数値を削除する関数
removeNumber <- function(string,number){
  
  
   List <- strsplit(string,number)
   Vector <- c(unlist(List))
 
   unit <-Vector[1]
 
   for(i in 1:length(Vector)-1){
  
   unit <- paste(unit,Vector[i+1],sep="")

   }  
  
   return(unit) 
 }
#複数の数値を削除する関数
removeNumbers <- function(string,Ns){

 for(x in 1:length(Ns)){

 string <- removeNumber(string,Ns[x])

 }

 return(string)

}
