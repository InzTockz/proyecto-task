# ============================================================
# ETAPA 1: "La cocina" — aquí se compila Angular
# ------------------------------------------------------------
# Usamos una imagen con Node.js SOLO para construir la app.
# Nada de esta etapa (Node, node_modules, código fuente) llega
# a la imagen final: solo nos llevamos el resultado (dist/).
# ============================================================
FROM node:22-alpine AS build

WORKDIR /app

# 1) Primero solo la lista de ingredientes (dependencias).
#    Si package.json y package-lock.json no cambian, Docker
#    reutiliza esta capa de caché y no vuelve a descargar todo.
COPY package.json package-lock.json ./

# 2) "npm ci" instala EXACTAMENTE las versiones del package-lock.json.
#    Es más rápido y reproducible que "npm install" (ideal para producción).
RUN npm ci

# 3) Ahora sí copiamos el código fuente
#    (lo que NO se copia está definido en .dockerignore).
COPY . .

# 4) Compilamos en modo producción: código minificado, archivos con
#    hash (main-XXXX.js) y sin source maps (no se expone tu código).
RUN npm run build -- --configuration production

# ============================================================
# ETAPA 2: "La vitrina" — imagen final, liviana, solo para servir
# ------------------------------------------------------------
# NGINX es un servidor web muy rápido para archivos estáticos.
# La imagen final pesa pocos MB porque no lleva Node ni node_modules.
# ============================================================
FROM nginx:alpine

# Reemplazamos la configuración por defecto de NGINX por la nuestra.
# Sin esto, al presionar F5 en una ruta como /task sale error 404.
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Traemos SOLO los archivos compilados desde la etapa "build"
COPY --from=build /app/dist/task-battilana/browser /usr/share/nginx/html

# Puerto donde escucha NGINX dentro del contenedor
EXPOSE 80

# No hace falta ENTRYPOINT: la imagen de nginx ya arranca el servidor sola.