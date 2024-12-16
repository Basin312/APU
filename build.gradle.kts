plugins {
	java
	id("org.springframework.boot") version "3.3.6"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "Apu"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
<<<<<<< HEAD
		languageVersion = JavaLanguageVersion.of(21)
=======
		languageVersion = JavaLanguageVersion.of(17)
>>>>>>> 1c22eda28d6da27508d7fd1176ea820e399106df
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	runtimeOnly("org.postgresql:postgresql")
	implementation("org.springframework.boot:spring-boot-devtools:3.4.0")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
