plugins {
    id("java")
    id("io.freefair.lombok") version "8.3"
}

group = "com.jorgebenavente"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.0")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}