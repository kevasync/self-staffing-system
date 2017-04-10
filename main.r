#install.packages("class")
#library(class)

set.seed(2)# i think i need this to reproduce outcome??

raw = data.frame(read.csv('data/devData.csv', stringsAsFactors = F))
data = raw[-1]#remove dev name
data$Team  = factor(data$Team) # replace stringish team name with factor

training = data[which(data$Team != ""), ]
trainingData = training[, 1:7]
trainingLabels = training[,8] # extract factor representing team name for rows of training data

testData = data[which(data$Team == ""), 1: 7]

# odd number close to sq root of number of rows, odd desired cause it doesnt lead to tie
k = round(sqrt(nrow(trainingData))) #todo: add logic to round to odd when round would lead to even

predictions = knn(train = trainingData, test = testData, k = k, cl = trainingLabels)

#/ad hoc-ish analysis example
testData2 = matrix(nrow = 1, c(1, 1, 0, 2, 2, 1, 5))#good at android
testData2 = rbind(testData2, c(1, 1, 0, 2, 2, 4, 2))#ios

predictions2 = knn(train = trainingData, test = testData2, k = k, cl = trainingLabels)