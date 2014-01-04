getIndex <- function(string,reg){

  index <- c(unlist(regexpr(reg,string)))

}

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

getPair <- function(vectors){

  pointer <- 1
  count <- 1
  limit <- length(vectors)
  results <- c()

  while(TRUE){

  buffer <- c()

　　for(i in 1:count){

   buffer <- paste(vectors[pointer+(1-i)],buffer,sep="")
    
  }
   
  if(countRemoval(buffer,"[[:punct:]]")>= 10){

   results <- c(results,buffer)
   pointer <- pointer + 1
   count <- 1

  }else{

    count <- count + 1
    pointer <- pointer + 1
  
  }

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
