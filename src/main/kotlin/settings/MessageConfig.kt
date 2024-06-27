package ru.snapix.snapicoin.settings

import ru.snapix.library.libs.dazzleconf.annote.ConfDefault.DefaultString
import ru.snapix.library.libs.dazzleconf.annote.ConfKey
import ru.snapix.library.libs.dazzleconf.annote.SubSection
import ru.snapix.snapicoin.settings.MessageConfig.Commands.AdminCommand

interface MessageConfig {
    @SubSection
    fun commands(): Commands
    interface Commands {
        @ConfKey("coin")
        @SubSection
        fun coin(): CoinCommand
        interface CoinCommand {
            @ConfKey("success-self")
            @DefaultString("Монет %name% %coin%")
            fun successSelf(): String

            @ConfKey("success-other")
            @DefaultString("Монет %coin% %name%")
            fun successOther(): String

            @ConfKey("not-found")
            @DefaultString("Не найден %name%")
            fun notFound(): String
        }

        @ConfKey("admin")
        @SubSection
        fun admin(): AdminCommand
        interface AdminCommand {
            @ConfKey("deposit-coin")
            @SubSection
            fun depositCoin(): DepositCoinCommand
            interface DepositCoinCommand {
                @ConfKey("use")
                @DefaultString("Используйте")
                fun use(): String

                @ConfKey("not-found")
                @DefaultString("&fНет игрока %name%")
                fun notFound(): String

                @ConfKey("error-amount")
                @DefaultString("&fНе правильное количество %amount%")
                fun errorAmount(): String

                @ConfKey("successfully-receiver")
                @DefaultString("&fВы успешно получили %sender% %receiver% %amount% %coin% %coin_before%")
                fun successfullyReceiver(): String

                @ConfKey("successfully-sender")
                @DefaultString("&fВы успешно отправили %sender% %receiver% %amount% %coin% %coin_before%")
                fun successfullySender(): String
            }

            @ConfKey("withdraw-coin")
            @SubSection
            fun withdrawCoin(): WithdrawCoinCommand
            interface WithdrawCoinCommand {
                @ConfKey("use")
                @DefaultString("Используйте")
                fun use(): String

                @ConfKey("not-found")
                @DefaultString("&fНет игрока %name%")
                fun notFound(): String

                @ConfKey("error-amount")
                @DefaultString("&fНе правильное количество %amount%")
                fun errorAmount(): String

                @ConfKey("successfully-receiver")
                @DefaultString("&fВас успешно обокрали %sender% %receiver% %amount% %coin% %coin_before%")
                fun successfullyReceiver(): String

                @ConfKey("successfully-sender")
                @DefaultString("&fВы успешно украли %sender% %receiver% %amount% %coin% %coin_before%")
                fun successfullySender(): String
            }

            @ConfKey("set-coin")
            @SubSection
            fun setCoin(): SetCoinCommand
            interface SetCoinCommand {
                @ConfKey("use")
                @DefaultString("Используйте")
                fun use(): String

                @ConfKey("not-found")
                @DefaultString("&fНет игрока %name%")
                fun notFound(): String

                @ConfKey("error-amount")
                @DefaultString("&fНе правильное количество %amount%")
                fun errorAmount(): String

                @ConfKey("successfully-receiver")
                @DefaultString("&fВам успешно установили %sender% %receiver% %amount% %coin% %coin_before%")
                fun successfullyReceiver(): String

                @ConfKey("successfully-sender")
                @DefaultString("&fВы успешно установили %sender% %receiver% %amount% %coin% %coin_before%")
                fun successfullySender(): String
            }
        }
    }
}