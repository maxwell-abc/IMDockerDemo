echo "-----�ڴ����ʹ��dev����--------"
mvn clean package -P dev & java -jar ./target/fims-crm-mid.jar 
echo "--------����jar�ļ�--------"
java -jar ./target/fims-crm-mid.jar 


