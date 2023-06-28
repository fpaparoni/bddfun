# bddfun

Source related to article [Cucumber & Testcontainer: a perfect match for BDD](https://fpaparoni.medium.com/cucumber-testcontainer-a-bdd-perfect-match-956cf62cdf47)


#### Before using this project you have to build the database image.  

```
// Run  
$ docker build -t bddfun-database ./database
```


#### Use watch command to see the running containers, the bddfun-database container should be started before test and stopped after test)  
```
// Show docker build image
$ watch docker images
 
  REPOSITORY        TAG       IMAGE ID       CREATED          SIZE
  bddfun-database   latest    65e5d206d4e3   29 seconds ago   376MB
```




#### After using this project you can clean up the database image.
```
// Remove docker image after we're done with the project and we want to clean up  
$ docker rm i bddfun-database 
``` 
