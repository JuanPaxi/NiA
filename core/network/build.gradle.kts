import com.android.build.api.variant.BuildConfigField
import java.io.StringReader
import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.juanpaxi.nia.core.network"
    compileSdk {
        version =
            release(
                libs.versions.targetSdk
                    .get()
                    .toInt(),
            )
    }
    defaultConfig {
        minSdk =
            libs.versions.minSdk
                .get()
                .toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(libs.bundles.android.core)
    implementation(libs.bundles.network)
    implementation(libs.bundles.coil)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}

val backendUrl =
    providers
        .fileContents(
            isolated.rootProject.projectDirectory.file("local.properties"),
        ).asText
        .map { text ->
            val properties = Properties()
            properties.load(StringReader(text))
            properties["BACKEND_URL"]
        }.orElse("http://example.com")

androidComponents {
    onVariants {
        it.buildConfigFields!!.put(
            "BACKEND_URL",
            backendUrl.map { value ->
                BuildConfigField(type = "String", value = """"$value"""", comment = null)
            },
        )
    }
}
