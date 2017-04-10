#library(class)
#library(plumber)

#* @get /suggest
suggestTeam = function(html,	js,	net, java,	functional, ios, android) {
  set.seed(2)# i think i need this to reproduce outcome??
  
  raw = data.frame(read.csv('data/devData.csv', stringsAsFactors = F))
  data = raw[-1]#remove dev name
  data$Team  = factor(data$Team) # replace stringish team name with factor
  
  training = data[which(data$Team != ""), ]
  trainingData = training[, 1:7]
  trainingLabels = training[, 8] # extract factor representing team name for rows of training data
  
  # odd number close to sq root of number of rows, odd desired cause it doesnt lead to tie
  k = round(sqrt(nrow(trainingData))) #todo: add logic to round to odd when round would lead to even  
  
  rating = matrix(nrow = 1, c(as.numeric(html), as.numeric(js), as.numeric(net), as.numeric(java), as.numeric(functional), as.numeric(ios), as.numeric(android)))
  knn(train = trainingData, test = rating, k = k, cl = trainingLabels)
}