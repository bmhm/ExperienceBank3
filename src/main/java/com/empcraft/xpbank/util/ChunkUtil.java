/*
 * (c) Copyright 2016 Experience Bank 3.
 *
 * This file is part of Experience Bank 3.
 *
 * Experience Bank 3 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Experience Bank 3 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Experience Bank 3. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.empcraft.xpbank.util;

import com.empcraft.xpbank.ExpBankConfig;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;

public final class ChunkUtil {

  private ChunkUtil() {
    // private
  }

  public static Optional<Chunk> getLoadedChunk(World world, Location loc) {
    Preconditions.checkNotNull(loc);
    Optional<Chunk> loadedChunk = Optional.absent();

    final Chunk chunkAt = world.getChunkAt(loc);
    if (chunkAt.isLoaded()) {
      loadedChunk = Optional.of(chunkAt);
    }

    return loadedChunk;
  }

  public static Collection<Chunk> getChunksAroundPlayer(Player player, ExpBankConfig config) {
    final Location playerLocation = player.getLocation();

    config.getLogger().log(java.util.logging.Level.FINER,
        "Getting Chunks around Player location: [" + playerLocation + "].");

    return getLoadedChunksAroundLocation(playerLocation, config);
  }

  public static Collection<Chunk> getLoadedChunksAroundLocation(Location loc, ExpBankConfig config) {
    Preconditions.checkNotNull(loc);
    final List<Chunk> chunkAroundList = new ArrayList<>();
    final World world = loc.getWorld();

    /*
     * In a imaginary 9x9 board, the player stands in the middle.
     * This small piece of code will check if nearby chunks in this board are loaded.
     * If so, they will be added to the around list.
     */
    for (int x = -1; x <= 1; x++) {
      for (int z = -1; z <= 1; z++) {
        config.getLogger().log(Level.FINER, "Requesting Chunk at X:[" + x + "], Z:[" + z + "].");
        Optional<Chunk> chunk = getLoadedChunk(world, loc.clone().add(x * 16.0D, 0.0D, z * 16.0D));

        if (chunk.isPresent()) {
          chunkAroundList.add(chunk.get());
        }
      }
    }

    return chunkAroundList;
  }
}
