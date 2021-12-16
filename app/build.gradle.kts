
plugins {
    id("groovy")
    id("application")
    // https://graalvm.github.io/native-build-tools/latest/index.html
    id("org.graalvm.buildtools.native") version "0.9.8"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
        vendor.set(JvmVendorSpec.matching("GraalVM Community"))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.slf4j/slf4j-simple
    implementation("org.slf4j:slf4j-simple:2.0.0-alpha5")

    // https://mvnrepository.com/artifact/org.apache.tinkerpop/gremlin-core
    implementation("org.apache.tinkerpop:gremlin-core:3.5.1")
    // https://mvnrepository.com/artifact/org.apache.tinkerpop/gremlin-groovy
    implementation("org.apache.tinkerpop:gremlin-groovy:3.5.1")
    // https://mvnrepository.com/artifact/org.apache.tinkerpop/gremlin-driver
    implementation("org.apache.tinkerpop:gremlin-driver:3.5.1")

    // https://mvnrepository.com/artifact/org.codehaus.groovy/groovy-console
    implementation("org.codehaus.groovy:groovy-console:3.0.9")
    implementation("org.codehaus.groovy:groovy-all:3.0.9")

    // https://mvnrepository.com/artifact/io.github.classgraph/classgraph
    implementation("io.github.classgraph:classgraph:4.8.138")

    testImplementation("org.spockframework:spock-core:2.0-groovy-3.0") // <6>
    testImplementation("junit:junit:4.13.2")
}

application {
    mainClass.set("io.github.babeloff.gremlin.GremlinConsole")
}

tasks {
    named<Test>("test") {
        useJUnitPlatform()
    }
}

graalvmNative {
    binaries {
        named("main") {
            javaLauncher.set(javaToolchains.launcherFor {
                languageVersion.set(JavaLanguageVersion.of(11))
                vendor.set(JvmVendorSpec.matching("GraalVM Community"))
            })
            imageName.set("gremlin-console")
            mainClass.set("io.github.babeloff.gremlin.GremlinConsole")
            useFatJar.set(true)
            debug.set(true)
            verbose.set(true)
            runtimeArgs.add("--help")
        }
    }

}