package br.com.gokan.customhud.handler;

import net.mcreator.trueprimepiece.TrueprimepieceModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

public class PlayerCapabilityHandler {

    public static Double getMaxHealth(PlayerEntity player) {
        Capability<TrueprimepieceModVariables.PlayerVariables> playerVariablesCap = TrueprimepieceModVariables.PLAYER_VARIABLES_CAPABILITY;
        LazyOptional<TrueprimepieceModVariables.PlayerVariables> playerVariables = player.getCapability(playerVariablesCap);
        Double vidaMax = playerVariables.map(vars -> vars.maxhp).orElse(10.0);
        return playerVariables.map(vars -> vars.maxhp).orElse(10.0);
    }

//    public static Double getHealth(PlayerEntity player) {
//        Capability<TrueprimepieceModVariables.PlayerVariables> playerVariablesCap = TrueprimepieceModVariables.PLAYER_VARIABLES_CAPABILITY;
//        LazyOptional<TrueprimepieceModVariables.PlayerVariables> playerVariables = player.getCapability(playerVariablesCap);
//        return playerVariables.map(vars -> vars.);
//    }


    public static Double getStamina(PlayerEntity player) {
        Capability<TrueprimepieceModVariables.PlayerVariables> playerVariablesCap = TrueprimepieceModVariables.PLAYER_VARIABLES_CAPABILITY;
        LazyOptional<TrueprimepieceModVariables.PlayerVariables> playerVariables = player.getCapability(playerVariablesCap);
        return playerVariables.map(vars -> vars.Stamina).orElse(0.0);
    }

    public static Double getMaxStamina(PlayerEntity player) {
        Capability<TrueprimepieceModVariables.PlayerVariables> playerVariablesCap = TrueprimepieceModVariables.PLAYER_VARIABLES_CAPABILITY;
        LazyOptional<TrueprimepieceModVariables.PlayerVariables> playerVariables = player.getCapability(playerVariablesCap);
        return playerVariables.map(vars -> vars.MaxStamina).orElse(10.0);
    }



}
