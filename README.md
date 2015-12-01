

1. Baixar o projeto e executar em uma IDE que suporte os framewords utilizados (JSF,JPA,Hibernate,CDI,PrimeFaces)

2. Utilizar um servidor de aplicação que suporte os frameworks e tenha suporte a EJBs

2.1 Utilizado o servidor de APlicação JBOSS

passos para configurar o postgres:

http://www.horochovec.com.br/blog/2014/10/07/configurando-um-datasource-do-postgresql-no-wildfly/

obs.: As bibliotecas devem estar presentes na pasta WEB-INF/lib para que o servidor possa acessá-las corretamente

As libs estão todas inclusas no projeto

configurar o arquivo ${JBOSS_HOME}/standalone/configuration/standalone.xml para que seja semelhante a este para configurar o upload de imagens

            <server name="default-server">
                <http-listener name="default" socket-binding="http" redirect-socket="https"/>
                <host name="default-host" alias="localhost">
                    <location name="/" handler="welcome-content"/>
                    <location name="/images" handler="images"/>
                    <filter-ref name="server-header"/>
                    <filter-ref name="x-powered-by-header"/>
                </host>
            </server>
            
            <handlers>
                <file name="welcome-content" path="${jboss.home.dir}/welcome-content"/>
                <file name="images" path="/data/quadrinhos" directory-listing="true"/>
            </handlers>

Criar a pasta "/data/quadrinhos" e permitir leitura e gravação.

obs.: Para o projeto funcionar no windows é necessario trocar dentro do arquivo /src/comicstore/compras/beans/FileUploadBean.java o caminho da pasta de Upload

######### Abilitar acesso remote ao JBOSS #########

http://www.thegeekstuff.com/2014/02/jboss-allow-remote-connections/



IDE utilizada - IntelliJ IDEA
