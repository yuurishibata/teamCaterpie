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

#関数テスト
as <- readLines("Desktop.txt")
as <- as[-1]
as <- removeBlanks(as)
