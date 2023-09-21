FROM node:16

EXPOSE 3000

WORKDIR /root

COPY ./package*.json .

RUN --mount=type=cache,target=/root/.npm/_cacache npm ci

COPY . .

CMD ["npm", "run", "start"]