plugins {
	java
	id("org.springframework.boot") version "3.1.2"
	id("io.spring.dependency-management") version "1.1.2"
}

group = "org.sonar.jvm.squad"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	compileOnly("org.projectlombok:lombok:1.18.26")
	annotationProcessor("org.projectlombok:lombok:1.18.26")

	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-rest")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.testcontainers:mongodb:1.19.0")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("io.rest-assured:rest-assured:5.2.0")
	testImplementation("io.rest-assured:xml-path:5.2.0")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
