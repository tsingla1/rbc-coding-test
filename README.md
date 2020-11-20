### How To Build And Run The App
#### Prerequisites
- Java 11+
- Maven 3+

``mvn clean install``

### How To Run The App
Hit Run on /Rbc/src/main/java/rbc/manage/records/Starter.java

### Sample Queries

#### Upload Multiple Entries
curl --request POST \
  --url http://localhost:8080/records/enterBulkRecords \
  --header 'content-type: application/json' \
  --data '[
		{"stockTicker": "cc",
 "epochMs": 10000,
 "price":14
	},
		{"stockTicker": "cc",
 "epochMs": 1234,
 "price": 142
	},
	{"stockTicker": "dd",
 "epochMs": 123,
 "price": 12
	},
		{"stockTicker": "ee",
 "epochMs": 1234,
 "price": 142
	}
]
'

#### Upload Single Entry

curl --request POST \
  --url http://localhost:8080/records/enterRecord \
  --header 'content-type: application/json' \
  --data '{
 "stockTicker": "dd",
 "epochMs": 123434,
 "price": 12
	}
'

#### Retrieve Entries for a stock ticker

curl --request GET \ --url http://localhost:8080/records/dd
