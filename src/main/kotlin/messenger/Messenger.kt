package ru.snapix.snapicoin.messenger

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import ru.snapix.library.libs.kreds.connection.AbstractKredsSubscriber
import ru.snapix.library.redis.async
import ru.snapix.library.redis.redisClient
import ru.snapix.library.redis.subscribe
import ru.snapix.snapicoin.messenger.actions.Action
import ru.snapix.snapicoin.messenger.actions.SendMessageAction
import ru.snapix.snapicoin.snapiCoin

object Messenger {
    private val module = SerializersModule {
        polymorphic(Action::class) {
            subclass(SendMessageAction::class)
        }
    }
    val json = Json {
        serializersModule = module
        encodeDefaults = true
        ignoreUnknownKeys = true
    }
    const val KEY_REDIS_MESSENGER = "snapicoin"

    fun enable() {
        subscribe(object : AbstractKredsSubscriber() {
            val logger = snapiCoin.logger

            override fun onMessage(channel: String, message: String) {
                val action = json.decodeFromString<Action>(message)
                action.executeIncomingMessage()
            }

            override fun onSubscribe(channel: String, subscribedChannels: Long) {
                logger.info("Success subscribed to channel: $channel")
            }

            override fun onUnsubscribe(channel: String, subscribedChannels: Long) {
                logger.info("Success unsubscribed to channel: $channel")
            }

            override fun onException(ex: Throwable) {
                logger.severe("Exception while handling subscription to redis: ${ex.stackTrace}")
            }
        }, KEY_REDIS_MESSENGER)
    }

    fun sendOutgoingMessage(action: Action) {
        redisClient.async {
            publish(KEY_REDIS_MESSENGER, json.encodeToString(action))
        }
    }
}
