FROM timbru31/java-node:11-jdk-erbium

RUN apt update
# install maven
RUN apt install -y maven
# update to npm
RUN npm install -g npm

# copy files
COPY . /app
WORKDIR /app

# build backend
RUN cd server && mvn package -Dmaven.test.skip=true
# build typescript package
RUN cd frontend/package && npm install && npm run build
# build frontend client
RUN cd frontend/client && npm install && npm run build
# run server
WORKDIR "/app/server"
CMD [ "./run --gui" ]
