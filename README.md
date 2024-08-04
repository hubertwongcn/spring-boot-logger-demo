# Dynamically manage AppLogger Demo
This is a dynamic management AppLogger Demo to demonstrate how to use AppLogger for dynamic management.

1. clean and install [blt-core](https://github.com/hubertwongcn/blt-core)
2. reload all Maven projects, make sure there is no error
3. run Application
4. request `curl --location 'http://127.0.0.1:8080/api/users' \
   --header 'App-Config-Name: app2' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "id": 1,
   "username": "xxx",
   "email": "xxx@abc.com"
   }'`, then you will see output like `for example: log info ...Received request to create user: User(id=1, username=hubert, email=hubert@abc.com) with config: app2; BltAppLogger: BltAppLogger(level=WARM, format=XML)
   for example: log info ...create user: User(id=1, username=hubert, email=hubert@abc.com); BltAppLogger: BltAppLogger(level=WARM, format=XML)` on the console
5. request `curl --location 'http://127.0.0.1:8080/api/users/2' \
   --header 'App-Config-Name: app1'`, then you will see output like `for example: log info ...Received request to get user with id: 1 with config: app1; BltAppLogger: BltAppLogger(level=ERROR, format=TEXT)
   for example: log info ...get user: User(id=1, username=hubert, email=hubert@abc.com); BltAppLogger: BltAppLogger(level=ERROR, format=TEXT)` on the console