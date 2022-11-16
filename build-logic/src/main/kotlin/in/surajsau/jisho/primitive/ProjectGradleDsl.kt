package `in`.surajsau.jisho.primitive

import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.getByType
import java.util.Optional

fun DependencyHandlerScope.implementation(
    artifact: Optional<Provider<MinimalExternalModuleDependency>>
) {
    add("implementation", artifact.get())
}

fun DependencyHandlerScope.bom(
    artifact: Optional<Provider<MinimalExternalModuleDependency>>
) {
    add("implementation", platform(artifact.get()))
}

fun DependencyHandlerScope.detektPlugins(
    artifact: Optional<Provider<MinimalExternalModuleDependency>>
) {
    add("detektPlugins", artifact.get())
}

val Project.libs get() = extensions.getByType<VersionCatalogsExtension>().named("libs")