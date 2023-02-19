package `in`.surajsau.jisho.data.expected

import app.cash.sqldelight.db.SqlDriver
import org.koin.core.scope.Scope

public expect fun Scope.createDriver(databaseName: String): SqlDriver