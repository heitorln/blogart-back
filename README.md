# Blog Back-end

> Back-end for a Blog in which users register their accounts, create and delete Posts and Comments.
> It has user Authentication using Spring Security and JWT.

## Main Structure

> Client <--> Controller <--> Service <--> Repository (JPA)

## Controllers

- AuthenticationController: manages user registers and logins
- PostController: manages user's comments
- CommentController: manages user's comments

### Resources

1. Java 17
2. SpringBoot 3.1.3
3. PostgreSQL
4. SpringSecurity
5. JWT 4.4.0
6. Flyway for Migrations

### TODOs

- Implement Swagger for better documentation/testing
- Implement Photo Albums feature
- Implement editing posts/comments
- Treat expired JWT exception
- Fix Timezone of creation timestamps

#### Requests for testing
<details>
  <summary>Postman Collection</summary>
  
  ```json
  {
	"info": {
		"_postman_id": "ac478011-d6e7-40a1-af35-23d5378b613e",
		"name": "BlogArt",
		"schema": "https://schema.getpostman.com/json/collection/v2.0.0/collection.json",
		"_exporter_id": "29750469"
	},
	"item": [
		{
			"name": "RegisterUser",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"username\": \"jooj3\",\r\n    \"password\": \"1234567\",\r\n    \"name\": \"Joao Junior\",\r\n    \"email\": \"jooj@mail.com\"\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": "localhost:8080/auth/register"
			},
			"response": []
		},
		{
			"name": "Login",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"username\": \"jooj3\",\r\n    \"password\": \"1234567\"\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": "localhost:8080/auth/login"
			},
			"response": []
		},
		{
			"name": "CreatePost",
			"request": {
				"auth": {
					"type": "bearer",
					"bearer": {
						"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJibG9nYXJ0Iiwic3ViIjoiam9vajIiLCJleHAiOjE2OTUwODU2OTJ9.W8Pg-ETwpqrAFu8HsyPrMeF1pVGX6kh-G-IaKpQuKe4"
					}
				},
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"title\": \"Visual Chorus\",\r\n    \"content\": \"Como provar aquilo que não da pra tocar, mas é real? É só moldar, a mácula é apenas visual\",\r\n    \"userId\": 2\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": "localhost:8080/post"
			},
			"response": []
		},
		{
			"name": "GetAllPosts",
			"request": {
				"auth": {
					"type": "bearer",
					"bearer": {
						"token": ""
					}
				},
				"method": "GET",
				"header": [],
				"url": "localhost:8080/post"
			},
			"response": []
		},
		{
			"name": "DeletePost",
			"request": {
				"auth": {
					"type": "bearer",
					"bearer": {
						"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJibG9nYXJ0Iiwic3ViIjoiam9vajMiLCJleHAiOjE2OTUwNzMyNzJ9.fA2XC8sMWBXlsMLJWco4SRCrCBo2GIWNs2OsJePyZRc"
					}
				},
				"method": "DELETE",
				"header": [],
				"url": {
					"raw": "localhost:8080/post/3?userId=2",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"post",
						"3"
					],
					"query": [
						{
							"key": "userId",
							"value": "2"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "GetPost",
			"request": {
				"auth": {
					"type": "bearer",
					"bearer": {
						"token": ""
					}
				},
				"method": "GET",
				"header": [],
				"url": "localhost:8080/post/1"
			},
			"response": []
		},
		{
			"name": "CreateComment",
			"request": {
				"auth": {
					"type": "bearer",
					"bearer": {
						"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJibG9nYXJ0Iiwic3ViIjoiam9vajMiLCJleHAiOjE2OTUwODY1Mzl9.6UlLKSXBb9AT6P_KMv9O6ohj-cVufXghUlsLix7ZX3Q"
					}
				},
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"text\": \"Bem legal!\",\r\n    \"postId\": 7,\r\n    \"userId\": 3\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": "localhost:8080/comment"
			},
			"response": []
		},
		{
			"name": "GetAllCommentsFromPost",
			"protocolProfileBehavior": {
				"disableBodyPruning": true
			},
			"request": {
				"auth": {
					"type": "bearer",
					"bearer": {
						"token": ""
					}
				},
				"method": "GET",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "localhost:8080/comment?postId=7",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"comment"
					],
					"query": [
						{
							"key": "postId",
							"value": "7"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "GetComment",
			"protocolProfileBehavior": {
				"disableBodyPruning": true
			},
			"request": {
				"auth": {
					"type": "bearer",
					"bearer": {
						"token": ""
					}
				},
				"method": "GET",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": "localhost:8080/comment/2"
			},
			"response": []
		},
		{
			"name": "DeleteComment",
			"request": {
				"auth": {
					"type": "bearer",
					"bearer": {
						"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJibG9nYXJ0Iiwic3ViIjoiam9vajMiLCJleHAiOjE2OTUwODY1Mzl9.6UlLKSXBb9AT6P_KMv9O6ohj-cVufXghUlsLix7ZX3Q"
					}
				},
				"method": "DELETE",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "localhost:8080/comment/6?userId=3",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"comment",
						"6"
					],
					"query": [
						{
							"key": "userId",
							"value": "3"
						}
					]
				}
			},
			"response": []
		}
	]
}
  ```
</details>
