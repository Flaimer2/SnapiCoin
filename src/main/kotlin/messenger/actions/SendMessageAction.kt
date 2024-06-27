package ru.snapix.snapicoin.messenger.actions

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bukkit.Bukkit
import ru.snapix.library.message

@Serializable
@SerialName("sendmessage")
class SendMessageAction(val sender: String, val receiver: String, val message: String) : Action() {
    override fun executeIncomingMessage() {
        Bukkit.getPlayer(receiver)?.message(message, "sender" to sender, "receiver" to receiver)
    }
}