plugins {
    kotlin("jvm")
    jacoco
}

version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:${Versions.JUNIT}"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.withType<Test> {
    useJUnitPlatform()
}