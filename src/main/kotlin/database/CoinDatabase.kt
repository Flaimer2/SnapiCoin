package ru.snapix.snapicoin.database

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import ru.snapix.snapicoin.api.User
import ru.snapix.snapicoin.settings.Settings

object UserTable : Table("account_coins") {
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name", 32).uniqueIndex()
    val coin: Column<Int> = integer("coin").default(0)

    override val primaryKey = PrimaryKey(id)
}

object CoinDatabase {
    private var database: Database

    init {
        val config = Settings.database
        database = Database.connect(
            url = "jdbc:mariadb://${config.host()}/${config.database()}",
            driver = "org.mariadb.jdbc.Driver",
            user = config.username(),
            password = config.password()
        )
    }

    fun load() {
        transaction(database) {
            SchemaUtils.create(UserTable)
        }
    }

    fun createUser(user: User) {
        transaction(database) {
            UserTable.insert {
                it[name] = user.name
                it[coin] = user.coin
            }
        }
    }

    fun removeUser(name: String) {
        transaction(database) {
            UserTable.deleteWhere { UserTable.name eq name }
        }
    }

    fun user(name: String): User? {
        return transaction(database) {
            UserTable.selectAll().where { UserTable.name eq name }.map {
                it.toUser()
            }
        }.firstOrNull()
    }

    fun users(): List<User> {
        return transaction(database) {
            UserTable.selectAll().map { it.toUser() }
        }
    }

    fun updateUser(user: User) {
        transaction(database) {
            UserTable.update({ UserTable.name eq user.name }) {
                it[coin] = user.coin
            }
        }
    }

    private fun ResultRow.toUser(): User {
        return User(this[UserTable.name], this[UserTable.coin])
    }
}