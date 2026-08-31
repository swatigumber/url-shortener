Klyo — URL Shortener

A simple URL shortener built with Java 21, Spring Boot, PostgreSQL, Docker, and Render.

Klyo converts long URLs into short URLs and redirects users from the short URL back to the original URL.

It also includes a minimal web interface for shortening URLs directly from the browser.

🚀 Live Application

Web UI:
https://klyo-url-shortener.onrender.com

API Base URL:
https://klyo-url-shortener.onrender.com

The application is hosted on Render using Docker, with PostgreSQL data stored in Neon.

🛠️ Tech Stack
Java 21
Spring Boot
Spring Data JPA / Hibernate
PostgreSQL
Neon PostgreSQL
HTML / CSS / JavaScript
Docker
Docker Compose
Maven
GitHub Actions
Render
🏗️ Architecture
 ```text
                        Browser
                            │
                            ▼
                   ┌─────────────────┐
                   │     Render      │
                   │  Docker + App   │
                   └────────┬────────┘
                            │
                            ▼
                   ┌─────────────────┐
                   │   Spring Boot   │
                   │  URL Shortener  │
                   └────────┬────────┘
                            │
                         JPA / JDBC
                            │
                            ▼
                   ┌─────────────────┐
                   │   PostgreSQL    │
                   │      Neon       │
                   └─────────────────┘
```

For local development, PostgreSQL can be run using Docker Compose.

✨ Features
Minimal web UI for shortening URLs
Shorten long URLs through the REST API
Generate unique Base62 short codes
Return the existing short code when the same URL is shortened again
Redirect short URLs to their original URLs
Validate incoming URLs
Validate request payloads
Handle invalid short codes with appropriate HTTP responses
Global exception handling
PostgreSQL persistence
Dockerized application
CI build using GitHub Actions
Cloud deployment using Render
🌐 Web UI

Klyo provides a minimal browser-based interface for shortening URLs.

The interface contains:

Klyo branding
URL input field
Shorten button
Generated short URL
Error messages for invalid input

The frontend is intentionally simple and is served directly by the Spring Boot application.

src/main/resources/
└── static/
    ├── index.html
    ├── style.css
    └── script.js


No separate frontend application or Node.js server is required.

📡 API
**1. Shorten a URL**

POST

/shorten


Example:

POST https://klyo-url-shortener.onrender.com/shorten
Content-Type: application/json


Request:

{
  "longUrl": "https://google.com"
}


Response:

{
  "shortURL": "https://klyo-url-shortener.onrender.com/<shortCode>"
}


The generated short code is based on the database ID encoded using Base62.

**2. Redirect to the original URL**

GET

/{shortCode}


Example:

GET https://klyo-url-shortener.onrender.com/<shortCode>


The application looks up the short code and redirects the request to the corresponding long URL.

🔢 How Short Codes Work

Klyo uses the database-generated ID as the source for the short code.

Database ID
     │
     ▼
  Base62
     │
     ▼
 Short Code


This keeps the implementation simple while ensuring that each newly created URL mapping receives a unique ID.

The Base62 alphabet contains:

0-9
a-z
A-Z


which allows numeric IDs to be represented using relatively short strings.

🗄️ Database

The application uses PostgreSQL to store URL mappings.

Conceptually, the data looks like:

ID	Long URL	Short Code
1	https://google.com	1
2	https://x.com	2
3	https://github.com	3

The actual database is provided by Neon for the deployed application.

🐳 Running Locally
Prerequisites
Java 21
Docker Desktop
Git
1. Clone the repository
git clone https://github.com/swatigumber/url-shortener.git
cd url-shortener

2. Build the application

Windows:

.\mvnw.cmd clean package


Linux/macOS:

./mvnw clean package

3. Start with Docker Compose
docker compose up --build


The application will be available at:

http://localhost:9090


Open the URL in a browser to use the Klyo web interface.

🔐 Configuration

The application uses environment variables for database configuration.

spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}

app.base-url=${APP_BASE_URL:http://localhost:9090/}


For local development, these can be configured through IntelliJ's Run Configuration or environment variables.

The deployed application uses Render environment variables.

Do not commit database credentials or other secrets to GitHub.

🐋 Docker

The project uses a multi-stage Docker build.

Stage 1
Java 21 JDK
     │
     ├── Maven build
     │
     └── Generate JAR
             │
             ▼
Stage 2
Java 21 JRE
     │
     └── Run JAR


This allows the Docker image to build the application without requiring the generated target/ directory to be committed to Git.

🔄 CI/CD

GitHub Actions is used to automatically build the project when changes are pushed to the master branch.

The workflow:

Push to GitHub
      │
      ▼
GitHub Actions
      │
      ├── Setup Java 21
      ├── Maven build
      └── Docker image build


The application is then deployed to Render from the GitHub repository.

☁️ Deployment

The application is deployed using:

GitHub
   │
   ▼
Render
   │
   ▼
Docker
   │
   ▼
Spring Boot
   │
   ├── Web UI
   └── REST API
   │
   ▼
Neon PostgreSQL


The application uses the PORT environment variable provided by the hosting platform while defaulting to port 9090 for local development.

📌 Project Status

This project is primarily a learning project focused on understanding:

REST APIs
Spring Boot
JPA and Hibernate
PostgreSQL
URL shortening algorithms
Base62 encoding
Validation
Exception handling
Transactions
Docker
CI/CD
Cloud deployment
Basic frontend integration

The goal is to keep the implementation simple and understandable rather than build a production-scale URL-shortening platform.

📄 License

This project is for learning and educational purposes.
