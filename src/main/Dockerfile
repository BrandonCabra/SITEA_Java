# Usamos una imagen base que ya tenga GlassFish 5 y Java 8
FROM payara/server-full:5.2022.5-jdk8
# Nota: Payara 5 es derivado de GlassFish 5 y suele ser más estable para Docker, 
# pero si requieres estrictamente GlassFish original, busca 'oracle/glassfish:5.0'
RUN wget -O $PAYARA_DIR/glassfish/domains/domain1/lib/mysql-connector-java-8.0.28.jar https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.28/mysql-connector-java-8.0.28.jar

# Copiamos el archivo .war compilado al directorio de autodespliegue
# Asumimos que Maven genera el archivo 'SITEA_Java-1.0-SNAPSHOT.war' en la carpeta target
COPY target/*.war $DEPLOY_DIR/sitea.war

# Exponer el puerto por defecto de GlassFish (8080)
EXPOSE 8080
