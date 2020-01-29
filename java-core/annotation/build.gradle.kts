plugins {
    kotlin("jvm")
}

version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:${Versions.JUNIT}"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.withType<Test> {
    useJUnitPlatform()
}