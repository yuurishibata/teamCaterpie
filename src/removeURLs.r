#removeURL関数ダミーデータからURLを削除するプログラム
> removeURL <- function(x){
+  
+  splited <- strsplit(x,"\t")
+  Vsplited <- c(unlist(splited))
+  title <- Vsplited[2]
+  content <- Vsplited[3]
+  
+  return(contents <- paste(title,content,""))
+ 
+ }
#関数のテスト
> one <- removeURL(text[1])
> one
[1] "http://ameblo.jp/cubesugar-evo/ 帰ってきた！ "
> 
#removeURLs関数
> removeURLs <- function(x){
+ 
+ 
+  for(i in 1:length(x)){
+     x[i] <- removeURL(x[i])
+  }
+ 
+  return(x)
+ }
>
#関数のテスト 
> splitedtexts <- removeURLs(text)
> splitedtexts[1:3]
[1] "http://ameblo.jp/cubesugar-evo/ 帰ってきた！ "      
[2] "http://ameblo.jp/cubesugar-evo/ アウターコーデ "    
[3] "http://ameblo.jp/cubesugar-evo/ ゆったりトップス達 "
