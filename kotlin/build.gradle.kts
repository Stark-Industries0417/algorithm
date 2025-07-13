plugins {
    kotlin("jvm") version "2.1.20"
}

group = "code.test"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

sourceSets {
    main {
        kotlin {
            setSrcDirs(listOf(
                "baekjoon",
                "programmers",
                "leetcode"
            ))
        }
    }
}
tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}