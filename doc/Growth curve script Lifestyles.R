#Miguel's weight losss curves

data <- read.csv("30degcurvestricky.csv")

library(nlstools)
stderr <- function(x) sqrt(var(x)/length(x))


#Plot points for each strain on same graph - I don't know how to do a loop so need to change which columns to plot
pdf("A1.pdf",height=9,width=12)
plot(c(),xlim=c(0,170),ylim=c(0,1),xlab="Time (hours)",ylab="Weight Loss (g)",main="A1")
points(data[,1],data[,2],pch=1)
points(data[,1],data[,3],pch=17)
points(data[,1],data[,4],pch=4)
legend("topright", pch=c(1,17,4,5),legend=c(expression(paste("Replicate 1")),
                                            expression(paste("Replicate 2")),
                                            expression(paste("Replicate 3"))))
       dev.off()
       
       ?points
       
       
       time<-as.numeric(data$time)
       time<-time/24
       time
       strains<-colnames(data[,2:ncol(data)])
       data<-data[,2:ncol(data)]
       modgompz<-formula(LOG10N ~ RS + (LOG10Nmax) * exp(-exp(mumax * exp(1) * (lag - t)/((LOG10Nmax) * log(10)) + 1)))
       
       
       ###alternatively, do 1 regression per sample, then take the statistics of the parameters within replicates
       ##also, generate a matrix 'dat' w/parameters for each replicate to do statistics later
       gparameters<-list(RS=0,lag=0.2, mumax=0.3,  LOG10Nmax = 0.8)
       curves<-list()
       raw<-list()
       dat<-data.frame(strain=strains,RS=numeric(21),lag=numeric(21),mumax=numeric(21),efficiency=numeric(21))
       
       ##replace the original loop in NLS_sarah_winea.R, with the following
       ## it will create a pdf graph for each fit using the correct data
       
       for(i in 1:ncol(data)){
         temp<-na.omit(as.data.frame(cbind(c(time), c(data[,(i)]))))
         colnames(temp)<-c("t","LOG10N")
         fit <- nls(modgompz,temp, gparameters,control=nls.control(maxiter = 200,minFactor=1/4096,warnOnly=T),trace = TRUE)
         pdf(file=paste("plot",colnames(data)[i],".pdf",sep=""),width=7,height=5)
         plotfit(fit,smooth=T,xlab="Time (days)",ylab="Weight loss (g)")
         dev.off()
         curves[[i]]<-fit
         raw[[i]]<-temp
         dat[i,2:5]<-coef(fit)
       }
       ##vectors containing fits, use for plotting histograms
       RS=NULL
       lag=NULL
       mumax=NULL
       eff=NULL
       for (i in 1:117){
         RS<-c(RS,coef(curves[[i]])[1])
         lag<-c(lag,coef(curves[[i]])[2])
         mumax<-c(mumax,coef(curves[[i]])[3])
         eff<-c(eff,coef(curves[[i]])[4])  
       }
       
       #Table of curve parameters (i.e. RS, lag, mumax, eff)
       names <- t(data[1,])
       names
       statsall <- cbind(names,RS,lag,mumax,eff)
       statsall
       
       write.csv(statsall,"fermentation_parameters_all.csv")
       
       warnings()
       
       #test ferment parameters - added treatments to fermentation_parameters_all.persistence manually
       fermparam <- read.csv("fermentation_parameters_test.csv")
       
       fermaov <- aov(fermparam$lag ~ fermparam$Yeast*fermparam$Oxygen)
       summary(fermaov)
       
       fermaov1 <- aov(fermparam$mumax ~ fermparam$Yeast*fermparam$Oxygen)
       summary(fermaov1)
       
       
       ##histograms of the coefficients
       par(mfrow=c(2,2))
       hist(RS,breaks=c(((min(RS)*10-1):(max(RS)*10+1))*0.1))
       hist(lag,breaks=c(((min(lag)*10-1):(max(lag)*10+1))*0.1))
       hist(mumax,breaks=c(((min(mumax)*10-1):(max(mumax)*10+1))*0.1))
       hist(eff,breaks=c(((min(eff)*10-1):(max(eff)*10+1))*0.1))
       
       dev.off()
       
       
       ##create a 'stats' matrix containing mean, SE of each parameter for each strain
       ## this part works for triplicates that are next to each other in the dataset
       stats<-data.frame()
       for (i in 1:(length(curves)/3)){
         stats[i,1]<-mean(c(coef(curves[[(i*3)-2]])[1],coef(curves[[(i*3)-1]])[1],coef(curves[[(i*3)]])[1]))
         stats[i,2]<-stderr(c(coef(curves[[(i*3)-2]])[1],coef(curves[[(i*3)-1]])[1],coef(curves[[(i*3)]])[1]))
         stats[i,3]<-mean(c(coef(curves[[(i*3)-2]])[2],coef(curves[[(i*3)-1]])[2],coef(curves[[(i*3)]])[2]))
         stats[i,4]<-stderr(c(coef(curves[[(i*3)-2]])[2],coef(curves[[(i*3)-1]])[2],coef(curves[[(i*3)]])[2]))
         stats[i,5]<-mean(c(coef(curves[[(i*3)-2]])[3],coef(curves[[(i*3)-1]])[3],coef(curves[[(i*3)]])[3]))
         stats[i,6]<-stderr(c(coef(curves[[(i*3)-2]])[3],coef(curves[[(i*3)-1]])[3],coef(curves[[(i*3)]])[3]))
         stats[i,7]<-mean(c(coef(curves[[(i*3)-2]])[4],coef(curves[[(i*3)-1]])[4],coef(curves[[(i*3)]])[4]))
         stats[i,8]<-stderr(c(coef(curves[[(i*3)-2]])[4],coef(curves[[(i*3)-1]])[4],coef(curves[[(i*3)]])[4]))
       }
       colnames(stats)<-c("mean_RS", "SEM_RS", "mean_lag","SEM_lag","mean_mumax","SEM_mumax","mean_eff","SEM_eff")
       