#library(class)
#library(plumber)

set.seed(2)# i think i need this to reproduce outcome??

data = data.frame(read.csv('data/devData.csv', stringsAsFactors = F))
data$Team  = factor(data$Team) # replace stringish team name with factor

training = data[which(data$Team != ""), ]

trainingData = training[, 2:8]
teamLabels = training[, 9] # extract factor representing team name for rows of training data
devLabels = training[, 1] # neareset dev name
# odd number close to sq root of number of rows, odd desired cause it doesnt lead to tie
k = round(sqrt(nrow(trainingData))) #todo: add logic to round to odd when round would lead to even  

createRating <- function(html, js, net, java, functional, ios, android) {
  matrix(nrow = 1, c(as.numeric(html), as.numeric(js), as.numeric(net), as.numeric(java), as.numeric(functional), as.numeric(ios), as.numeric(android)))
}

#* @get /suggestTeam
suggestTeam = function(html,	js,	net, java,	functional, ios, android) {
  rating = createRating(html, js, net, java, functional, ios, android)
  knn(train = trainingData, test = rating, k = k, cl = trainingLabels)
}

#* @get /suggestDev
suggestDev = function(html,	js,	net, java,	functional, ios, android) {
  rating = createRating(html, js, net, java, functional, ios, android)
  knn(train = trainingData, test = rating, k = k, cl = devLabels)
}