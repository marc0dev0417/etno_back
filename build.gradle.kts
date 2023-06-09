import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.5"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

group = "com.etno"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	implementation("jakarta.activation:jakarta.activation-api:2.1.1")

	// https://mvnrepository.com/artifact/io.springfox/springfox-swagger2
	implementation("io.springfox:springfox-swagger2:3.0.0")

	// https://mvnrepository.com/artifact/io.springfox/springfox-boot-starter
	implementation("io.springfox:springfox-boot-starter:3.0.0")

	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-mail
	implementation("org.springframework.boot:spring-boot-starter-mail:3.0.1")
	// https://mvnrepository.com/artifact/org.springframework/spring-context-support
	implementation("org.springframework:spring-context-support:6.0.4")


	// Security with JWT
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	implementation("org.apache.commons:commons-lang3:3.12.0")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

	testImplementation("org.springframework.security:spring-security-test")

	// https://mvnrepository.com/artifact/com.google.code.gson/gson
	implementation("com.google.code.gson:gson:2.10.1")

	// https://mvnrepository.com/artifact/com.google.firebase/firebase-admin
	implementation("com.google.firebase:firebase-admin:9.1.1")

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("mysql:mysql-connector-java")
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