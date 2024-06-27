package caches

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.snapix.library.redis.async
import ru.snapix.library.redis.redisClient
import ru.snapix.snapicoin.api.User
import ru.snapix.snapicoin.database.CoinDatabase

object Coins {
    private const val KEY_REDIS_USERS = "account_coins"

    fun getUser(name: String): User? {
        return redisClient.async {
            val user = hget(KEY_REDIS_USERS, name.lowercase())

            if (user != null) Json.decodeFromString<User>(user) else updateUser(name)
        }
    }

    fun updateUser(name: String): User? {
        return redisClient.async {
            val user = CoinDatabase.user(name)

            if (user != null) hset(KEY_REDIS_USERS, name.lowercase() to Json.encodeToString(user)) else hdel(KEY_REDIS_USERS, name.lowercase())

            user
        }
    }

    fun updateUser(user: User) {
        redisClient.async {
            hset(KEY_REDIS_USERS, user.name.lowercase() to Json.encodeToString(user))
        }
    }

    fun getUsers(): List<User> {
        return redisClient.async {
            hvals(KEY_REDIS_USERS).map { Json.decodeFromString<User>(it) }
        }
    }
}