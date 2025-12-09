FROM python:3.9-slim

WORKDIR /app

# Copiar dependencias e instalarlas
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Copiar el código
COPY . .

# Exponer el puerto (asumiendo que tu app.py corre en el 5000 o similar)
EXPOSE 5000

# Comando para iniciar
CMD ["python", "app.py"]
