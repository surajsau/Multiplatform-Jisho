package `in`.surajsau.jisho.data.expected

import com.squareup.sqldelight.db.SqlDriver
import org.koin.core.scope.Scope

internal expect fun Scope.createDriver(databaseName: String): SqlDriver