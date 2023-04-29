import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.6"
	id("io.spring.dependency-management") version "1.1.0"

	id("org.jetbrains.kotlin.plugin.jpa") version "1.8.21"

	id("org.flywaydb.flyway") version "9.16.3"

	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
}


group = "com.konoha"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	implementation("org.jetbrains.kotlin:kotlin-reflect")

	implementation("org.postgresql:postgresql:42.2.27")
	implementation("org.hibernate:hibernate-validator:8.0.0.Final")
	implementation("org.flywaydb:flyway-core:9.17.0")

	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
