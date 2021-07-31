echo "-----在打包，使用dev配置--------"
mvn clean package -P dev & java -jar ./target/fims-crm-mid.jar 
echo "--------运行jar文件--------"
java -jar ./target/fims-crm-mid.jar 


