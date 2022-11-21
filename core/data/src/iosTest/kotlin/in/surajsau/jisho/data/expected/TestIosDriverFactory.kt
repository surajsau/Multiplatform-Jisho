package `in`.surajsau.jisho.data.expected

import co.touchlab.sqliter.DatabaseConfiguration
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.squareup.sqldelight.drivers.native.wrapConnection
import `in`.surajsau.jisho.data.db.Jisho

internal actual fun testDbConnection(): SqlDriver {
    val schema = Jisho.Schema
    return NativeSqliteDriver(
        DatabaseConfiguration(
            name = "jmdict",
            version = schema.version,
            create = { connection ->
                wrapConnection(connection) { schema.create(it) }
            },
            upgrade = { connection, oldVersion, newVersion ->
                wrapConnection(connection) { schema.migrate(it, oldVersion, newVersion) }
            },
            inMemory = true
        )
    )
}
