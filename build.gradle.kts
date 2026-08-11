plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.google.ksp) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.spotless) apply false
}

subprojects {
    apply(plugin = "com.diffplug.spotless")

    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("**/build/**/*.kt")
            ktlint().editorConfigOverride(
                mapOf(
                    "ktlint_function_naming_ignore_when_annotated_with" to "Composable",
                    "android" to "true",
                    "ktlint_standard_no-unused-imports" to "enabled"
                ),
            )
            trimTrailingWhitespace()
            endWithNewline()
        }
        kotlinGradle {
            target("**/*.gradle.kts")
            targetExclude("**/build/**/*.gradle.kts")

            ktlint()
            trimTrailingWhitespace()
            endWithNewline()
        }
    }
}