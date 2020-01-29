import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Versions.KOTLIN apply false
    kotlin("plugin.spring") version Versions.KOTLIN apply false
    id("org.jetbrains.kotlin.plugin.jpa") version Versions.KOTLIN apply false
    id("org.springframework.boot") version Versions.SPRINGBOOT apply false
    id("io.spring.dependency-management") version Versions.SPRING_DEPENDENCY_MANAGEMENT apply false
}

allprojects {
    group = "kz.test.project"
}

subprojects {
    repositories {
        jcenter()
        mavenCentral()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

}