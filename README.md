# Self Staffing System
A tool to provide suggestions on teams you would fit on and developers you are similar to skills-wise

* To run, in R Studio:
 ```
library(plumber)
app = plumb('main.r')
app$run(port=8888)
```


* Example call: ```localhost:8888/suggest?html=3&js=4&net=5&java=0&functional=0=&ios=0&android=1```

