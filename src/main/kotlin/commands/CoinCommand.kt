package ru.snapix.snapicoin.commands

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import ru.snapix.library.formattedName
import ru.snapix.library.libs.commands.BaseCommand
import ru.snapix.library.libs.commands.annotation.*
import ru.snapix.library.message
import ru.snapix.snapicoin.api.CoinApi
import ru.snapix.snapicoin.settings.Settings

@CommandAlias("%coin_command_main")
class CoinCommand : BaseCommand() {
    @Default
    @CatchUnknown
    @CommandCompletion("@players @nothing")
    fun coin(player: Player, args: Array<String>) {
        val config = Settings.message.commands().coin()

        if (args.isEmpty()) {
            val coin = CoinApi.user(player.name)?.coin ?: 0
            player.message(config.successSelf(), "name" to player.name, "coin" to coin)
            return
        }

        val other = args[0]
        val otherUser = CoinApi.user(other)

        if (otherUser == null) {
            player.message(config.notFound(), "name" to other)
            return
        }

        player.message(config.successOther(), "name" to otherUser.name, "coin" to otherUser.coin)
    }

    @CommandCompletion("@players @nothing")
    @Subcommand("%coin_command_deposit_coin")
    fun depositCoin(sender: CommandSender, args: Array<String>) {
        val config = Settings.message.commands().admin().depositCoin()

        if (args.size < 2) {
            sender.message(config.use())
            return
        }
        val receiver = args[0]

        var user = CoinApi.user(receiver)
        if (user == null) {
            sender.message(config.notFound(), "name" to receiver)
            return
        }

        val amount = args[1].toIntOrNull()
        if (!(amount != null && amount > 0)) {
            sender.message(config.errorAmount())
            return
        }

        val silent = args.find { it.equals("--silent", ignoreCase = true) || it.equals("-s", ignoreCase = true) } != null
        val message = args.find { it.equals("--message", ignoreCase = true) || it.equals("-m", ignoreCase = true) } != null

        val coinBefore = user.coin
        val senderName = sender.formattedName(Settings.config.consoleNameForSender())

        user = CoinApi.depositCoin(user, amount)

        if (!silent) {
            sender.message(config.successfullySender(), "sender" to senderName, "receiver" to user.name, "amount" to amount, "coin" to user.coin, "coin_before" to coinBefore)
        }

        if (!message) {
            CoinApi.sendMessage(senderName, user.name, config.successfullyReceiver(),"amount" to amount, "coin" to user.coin, "coin_before" to coinBefore)
        }
    }

    @CommandCompletion("@players @nothing")
    @Subcommand("%coin_command_withdraw_coin")
    fun withdrawCoin(sender: CommandSender, args: Array<String>) {
        val config = Settings.message.commands().admin().withdrawCoin()

        if (args.size < 2) {
            sender.message(config.use())
            return
        }
        val receiver = args[0]

        var user = CoinApi.user(receiver)
        if (user == null) {
            sender.message(config.notFound(), "name" to receiver)
            return
        }

        val amount = args[1].toIntOrNull()
        if (!(amount != null && amount > 0)) {
            sender.message(config.errorAmount())
            return
        }

        val silent = args.find { it.equals("--silent", ignoreCase = true) || it.equals("-s", ignoreCase = true) } != null
        val message = args.find { it.equals("--message", ignoreCase = true) || it.equals("-m", ignoreCase = true) } != null

        val coinBefore = user.coin
        val senderName = sender.formattedName(Settings.config.consoleNameForSender())

        user = CoinApi.withdrawCoin(user, amount)

        if (!silent) {
            sender.message(config.successfullySender(), "sender" to senderName, "receiver" to user.name, "amount" to if (user.coin == 0) coinBefore else amount, "coin" to user.coin, "coin_before" to coinBefore)
        }

        if (!message) {
            CoinApi.sendMessage(senderName, user.name, config.successfullyReceiver(), "amount" to if (user.coin == 0) coinBefore else amount, "coin" to user.coin, "coin_before" to coinBefore)
        }
    }

    @CommandCompletion("@players @nothing")
    @Subcommand("%coin_command_set_coin")
    fun setCoin(sender: CommandSender, args: Array<String>) {
        val config = Settings.message.commands().admin().setCoin()

        if (args.size < 2) {
            sender.message(config.use())
            return
        }
        val receiver = args[0]

        var user = CoinApi.user(receiver)
        if (user == null) {
            sender.message(config.notFound(), "name" to receiver)
            return
        }

        val amount = args[1].toIntOrNull()
        if (!(amount != null && amount > 0)) {
            sender.message(config.errorAmount())
            return
        }

        val silent = args.find { it.equals("--silent", ignoreCase = true) || it.equals("-s", ignoreCase = true) } != null
        val message = args.find { it.equals("--message", ignoreCase = true) || it.equals("-m", ignoreCase = true) } != null

        val coinBefore = user.coin
        val senderName = sender.formattedName(Settings.config.consoleNameForSender())

        user = CoinApi.withdrawCoin(user, amount)

        if (!silent) {
            sender.message(config.successfullySender(), "sender" to senderName, "receiver" to user.name, "amount" to amount, "coin" to user.coin, "coin_before" to coinBefore)
        }

        if (!message) {
            CoinApi.sendMessage(senderName, user.name, config.successfullyReceiver(), "amount" to amount, "coin" to user.coin, "coin_before" to coinBefore)
        }
    }
}