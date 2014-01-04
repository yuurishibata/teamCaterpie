library(arules)
library(arules)
od <- read.csv("onsen.csv",header=TRUE,row.names=1)
od
od <- as.matrix(od)
ot <- as(od,"transactions")
inspect(ot)
or <- apriori(ot,parameter=list(maxlen=3,support=0.04,confidence=0.55,ext=TRUE))
inspect(or)
(output <- capture.output(inspect(or)))

