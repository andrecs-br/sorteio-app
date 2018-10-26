# prazo-pedido-app

## Repositório da aplicação prazo-pedido-app responsável por tratar os prazos dos pedidos.

** JIRA Issue Template (Change) **

http://jira.vr.com.br:8080/browse/CHAN-57485

** Caminho no repositorio **

http://desenv.smartnet.com.br/web/repository/br/com/vr/prazo-pedido-app/1.0.0/prazo-pedido-app-1.0.0.jar

** Inicializacao **

PROFILE=prd APPNAME=prazo-pedido-app VERSION=1.0.0 ARTIFACT=$APPNAME-$VERSION.jar

** Comando para inicialização: **

java -Xmx128m -Xms128m -Dspring.profiles.active=$PROFILE -jar /opt/springboot/$APPNAME/$ARTIFACT >/dev/null 2>&1 &