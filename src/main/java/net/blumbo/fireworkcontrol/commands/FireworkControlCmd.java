package net.blumbo.fireworkcontrol.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.blumbo.fireworkcontrol.FireworkControl;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class FireworkControlCmd {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher,
                                CommandRegistryAccess registryAccess,
                                CommandManager.RegistrationEnvironment environment) {

    dispatcher.register(CommandManager.literal("fireworkcontrol").requires(source -> source.hasPermissionLevel(2))

        .then(CommandManager.literal("damagePercentage").executes(FireworkControlCmd::get)

                .then(CommandManager.argument("value", IntegerArgumentType.integer(0))
                    .executes(FireworkControlCmd::set)
                )
            )
        );

    }

    private static int get(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();
        source.sendMessage(Text.of("\n§7Firework Damage percentage is currently §b" +
                FireworkControl.damagePercentage + "%"));
        return 0;
    }

    private static int set(CommandContext<ServerCommandSource> context) {
        int value = context.getArgument("value", Integer.class);
        FireworkControl.damagePercentage = value;
        ServerCommandSource source = context.getSource();
        source.sendMessage(Text.of("§7Firework Damage percentage set to §b" + value + "%"));
        return 0;
    }

}
