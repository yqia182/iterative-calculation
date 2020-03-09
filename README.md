# Instructions
## Configuration  
### Settings
#### Database
Install Mysql to your PC if you do not yet have a Mysql database. Configure the database name and credentials as, for example,
> spring.datasource.url=jdbc:mysql://127.0.0.1:3306/iterative?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true
spring.datasource.username=iterative
spring.datasource.password=123456

#### Parameters
Predict a range for each parameter in application-dev.properties. For example,

> iterative.param.suggestion.lag-range-min = 400  
iterative.param.suggestion.lag-range-max = 1200  
iterative.param.suggestion.rate-range-min =0.00200  
iterative.param.suggestion.rate-range-max = 0.00800  
iterative.param.suggestion.minOD-range-min= 0.00  
iterative.param.suggestion.minOD-range-max= 0.080  
iterative.param.suggestion.maxOD-range-min=0.90  
iterative.param.suggestion.maxOD-range-max=1.70  

This does not have to be precise. The ranges should be wide enough for the actual values falling into.
#### Dataset File
The dataset must be arranged in a CSV file like,
[![Csv File Demo](https://raw.githubusercontent.com/yqia182/iterative-calculation/master/doc/csv_demo.bmp "Csv File Demo")](https://raw.githubusercontent.com/yqia182/iterative-calculation/master/doc/csv_demo.bmp "Csv File Demo")

Configure the file path and name. For example,
> iterative.filepath.observed-data-csv-file-path=D:\\Desktop\\1.csv


### URI
Navigate to [locahost:8080](http://locahost:8080/ "locahost:8080") and click on "Run All Samples" or "Run One Sample".

### Results
Rrefer to the table named `final_result_permanent_entity` and select the results with hightest coef.
Always export the results before you drop any table.

# Introduction
## Gompertz Modeling of Growth Curves  
There is a modification to the Gompertz model so as to fit the model in to microorganism growth. If the population of the microorganism at a certain time is translated into optical density, then there is (Roncoroni, M., 2014),  

[![Modified Gompertz Model](https://raw.githubusercontent.com/yqia182/iterative-calculation/master/doc/Gompertz_img.bmp "Modified Gompertz Model")](https://raw.githubusercontent.com/yqia182/iterative-calculation/master/doc/Gompertz_img.bmp "Modified Gompertz Model")
minOD - Minimum Optical Density  
maxOD - Maximum Optical Density (Carrying Capacity)  
lag - The Duration of Lag Phase of the Growth  
μmax - Maximum Growth Rate (at Exponential Growth Phase)  


## Brute-Force Search & Greedy Algorithm  
To calculate the four parameters above, assumpted values for these four are tried out.  
Each combination of four values will form a formula. For each formula, the coeffcient of determination (coef) can be calcalated out with the dataset from observations.  
The coef is used to evaluate whether the four values are a good fit into your dataset.

There are way too many values to try out for these four parameters. Unreasonably high time complexity is a major problem with brute-force search alogorithm. Since this problem has greedy-choice properties, an alternative method, greedy algorithm is implemented in this project.


## Citations 
Roncoroni, M. (2014). Quantitative trait loci mapping in winemaking yeast (Doctoral dissertation, ResearchSpace@ Auckland).