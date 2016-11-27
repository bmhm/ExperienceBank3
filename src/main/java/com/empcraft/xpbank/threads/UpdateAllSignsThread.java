package com.empcraft.xpbank.threads;

import com.empcraft.xpbank.ExpBankConfig;
import com.empcraft.xpbank.logic.SignHelper;
import com.empcraft.xpbank.util.ChunkUtil;
import com.google.common.base.Preconditions;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UpdateAllSignsThread implements Runnable {

  private final Player player;
  private final Location location;
  private ExpBankConfig expBankConfig;

  public UpdateAllSignsThread(final Player player, final Location location,
      final ExpBankConfig expBankConfig) {
    this.player = player;
    this.location = location;
    this.expBankConfig = expBankConfig;
  }

  @Override
  public void run() {
    Preconditions.checkNotNull(player, "Player in UpdateAllSignsThread.");
    Preconditions.checkState(player.isOnline(), "Player not online in UpdateAllSignsThread");

    final Collection<Chunk> chunks = ChunkUtil.getLoadedChunksAroundLocation(location, expBankConfig);
    final List<BlockState> states = new ArrayList<>();

    for (Chunk chunk : chunks) {
      for (BlockState state : chunk.getTileEntities()) {
        states.add(state);
      }
    }

    for (BlockState current : states) {
      if (current instanceof Sign) {
        SignHelper.updateSign(player, (Sign) current, expBankConfig);
      }
    }
  }

}
