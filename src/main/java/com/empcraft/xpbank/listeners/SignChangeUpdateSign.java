package com.empcraft.xpbank.listeners;

import com.empcraft.xpbank.ExpBankConfig;
import com.empcraft.xpbank.logic.SignHelper;
import com.empcraft.xpbank.util.ChunkUtil;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Updates existing signs.
 */
public class SignChangeUpdateSign implements Listener {

  private ExpBankConfig config;

  public SignChangeUpdateSign(final ExpBankConfig config) {
    this.config = config;
  }

  @EventHandler
  public void onSignChange(SignChangeEvent event) {
    final Location loc = event.getBlock().getLocation();
    final Collection<Chunk> chunks = ChunkUtil.getLoadedChunksAroundLocation(loc);

    List<Player> myplayers = new ArrayList<>();

    for (Chunk chunk : chunks) {
      for (Entity entity : chunk.getEntities()) {
        if ((entity instanceof Player) && (!myplayers.contains(entity))) {
          myplayers.add((Player) entity);
        }
      }
    }

    for (final Player user : myplayers) {
      SignHelper.scheduleUpdate(user, loc, config);
    }

    return;
  }
}
