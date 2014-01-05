getRuleNumber <- function(string){
   
  index <- c(unlist(regexpr(" ",string)))
  print(index)
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

    x <- substring(string,0,removePosition-1)
    y <- substring(string,removePosition+1,nchar(string))

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
  one <- numbers[1]
  two <- numbers[2]
  three <- numbers[3]
  four <- numbers[4]

  line <- paste(line,one,sep=",")
  line <- paste(line,two,sep=",")
  line <- paste(line,three,sep=",")
  line <- paste(line,four,sep=",")
    
  return(line)

}

  
  