package ru.snapix.snapicoin.api

import caches.Coins
import ru.snapix.snapicoin.database.CoinDatabase
import ru.snapix.snapicoin.messenger.Messenger
import ru.snapix.snapicoin.messenger.actions.SendMessageAction

object CoinApi {
    fun createUser(name: String) {
        val user = User(name = name, coin = 0)

        CoinDatabase.createUser(user)
        Coins.updateUser(user)
    }

    fun removeUser(name: String) {
        CoinDatabase.removeUser(name)
        Coins.updateUser(name)
    }

    fun hasUser(name: String): Boolean {
        return user(name) != null
    }

    fun user(name: String): User? {
        return Coins.getUser(name)
    }

    fun users(): List<User> {
        return Coins.getUsers()
    }

    fun users(block: (User) -> Boolean): List<User> {
        return users().filter(block)
    }

    fun setCoin(user: User, coin: Int): User {
        user.coin = coin

        CoinDatabase.updateUser(user)
        Coins.updateUser(user)

        return user
    }

    fun depositCoin(user: User, coin: Int): User {
        require(coin > 0) { "amount must be greater than zero" }

        setCoin(user, user.coin + coin)

        return user
    }

    fun withdrawCoin(user: User, coin: Int): User {
        require(coin > 0) { "amount must be greater than zero" }

        var value = user.coin - coin
        if (value < 0) {
            value = 0
        }

        setCoin(user, value)

        return user
    }

    fun sendMessage(sender: String, receiver: String, message: String, vararg pairs: Pair<String, Any>) {
        var result = message
        pairs.forEach { result = result.replace("%${it.first}%", it.second.toString(), ignoreCase = true) }
        Messenger.sendOutgoingMessage(SendMessageAction(sender, receiver, result))
    }
}