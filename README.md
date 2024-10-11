# API

## TRANSACTIONS

### Exercise

```
curl --location 'localhost:8080/api/v1/14537780/transactions?fromAccountingDate=2018-01-01&toAccountingDate=2018-12-01'
```

get by id:

```
curl --location 'localhost:8080/api/v1/14537780/transactions/1210436970002'
```

### Sandbox

```
TODO
```

## BALANCE

### Exercise

```
curl --location 'localhost:8080/api/v1/14537780/balance'
```

### Sandbox

```
curl --location 'https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/14537780/transactions?fromAccountingDate=2018-01-01&toAccountingDate=2018-01-12' \
--header 'Auth-Schema: S2S' \
--header 'Api-Key: FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP'
```

## MONEY TRANSFER

### Exercise

```
curl --location --request POST 'localhost:8080/api/v1/14537780/money-transfer'
```

##### Mocked fabrick response

```
curl --location --request POST 'localhost:8080/api/v1/14537780/money-transfer?mock=true'
```

### Sandbox

```
TODO
```

# DB

H2 CONSOLE
http://localhost:8080/api/h2-console
