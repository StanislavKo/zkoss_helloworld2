cd db-migrations
g:\Programs\zkoss_7.0.3\liquibase-3.4.2-bin\liquibase.bat --url=jdbc:mysql://localhost:3306/zkoss01 --driver=com.mysql.jdbc.Driver --classpath=g:\ProjectsAll\ProjectsZKoss\Contact03\temp\libs\mysql-connector-java-5.1.40.jar --username=zkoss01 --password="zkoss01" --changeLogFile=v-1.0\changelog-v.1.0-cumulative.xml update