# bddfun

Source related to article [Cucumber & Testcontainer: a perfect match for BDD](https://fpaparoni.medium.com/cucumber-testcontainer-a-bdd-perfect-match-956cf62cdf47)

To build the database:
```shell
cd database
docker build -t bddfun-database .
```

In Dev Containers:
```shell
cd /usr/local/sdkman/candidates/java/
rm current
ln -s /usr/local/sdkman/candidates/java/21.0.7-ms current
```