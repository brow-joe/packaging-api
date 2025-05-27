# database

Data structure, inserts (mock) and data query.


### Docker-compose

```sh
$ docker-compose up -d
```

### Security

**[pgadmin4](http://localhost:5050/)**

| USER               | PASSWORD |
|--------------------|----------|
| guest@chavito.com  | guest    |

**[postgres](postgres:5432)**

| DATABASE | PASSWORD | USER   |
|----------|----------|--------|
| chavito  | guest    | guest  |


#### SQL

* [Schema](./db/01-schema.sql)
* [Insert](./db/02-data.sql)
* [Query](find.sql)
