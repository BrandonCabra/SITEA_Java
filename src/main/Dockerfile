# Usamos Payara 6 con JDK 21 para compatibilidad con dependencias actuales
FROM payara/server-full:6.2024.10-jdk21
USER root
RUN apt-get update && apt-get install -y wget && rm -rf /var/lib/apt/lists/*
RUN wget -O $PAYARA_DIR/glassfish/domains/domain1/lib/mysql-connector-java-8.0.28.jar https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.28/mysql-connector-java-8.0.28.jar
USER payara

# Copiamos el archivo .war compilado al directorio de autodespliegue
# Asumimos que Maven genera el archivo 'SITEA_Java-1.0-SNAPSHOT.war' en la carpeta target
COPY target/*.war $DEPLOY_DIR/sitea.war

# Exponer el puerto por defecto de GlassFish (8080)
EXPOSE 8080
