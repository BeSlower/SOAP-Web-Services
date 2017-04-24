# SOAP-Web-Services
I implemented a web service about singer related information retrieval.

## User Information Web Service
* Maintain two tables: login table and favorite_singer table;
* Four operations:
  * validate user
  * add new user
  * add favorite singer name
  * query favorite singer name
## Singer Music Web Service
* invoke external REST iTunes API by given a singer name
* response a json file ([see response description](https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/))
## Singer News Web Service
* invoke external REST iris.lore.ai API by given a singer name
* response a json file ([see response description](http://iris.lore.ai/documentation))
