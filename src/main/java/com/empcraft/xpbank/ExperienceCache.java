/**
 *
 */
package com.empcraft.xpbank;

import com.empcraft.xpbank.text.YamlLanguageProvider;
import com.empcraft.xpbank.threads.BukkitChangePlayerExperienceThread;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Manages Experience Cache.
 */
public class ExperienceCache extends ConcurrentHashMap<UUID, AtomicInteger> {
  /**
   * Serial.
   */
  private static final long serialVersionUID = -8729259291859204345L;

  public AtomicInteger addPlayer(UUID uuid, final ExpBankConfig config) {
    final AtomicInteger atomicInteger = new AtomicInteger();
    AtomicInteger setValue = this.putIfAbsent(uuid, atomicInteger);
    if (setValue != null) {
      config.getLogger().info("Error inserting new Player with UUID [" + uuid + "].");
    }

    return atomicInteger;
  }

  public void addExperience(Player player, int delta, final ExpBankConfig config,
      final YamlLanguageProvider language) {
    // contains putIfAbsent.
    addPlayer(player.getUniqueId(), config);

    int newValue = this.get(player.getUniqueId()).addAndGet(delta);

    config.getLogger().info("Player new experience in bank: " + newValue);

    BukkitChangePlayerExperienceThread experienceThread = new BukkitChangePlayerExperienceThread(
        player, delta, config, language);
    Bukkit.getScheduler().runTask(config.getPlugin(), experienceThread);
  }

  public void substractExperience(Player player, int withdrawAmount, final ExpBankConfig config,
      final YamlLanguageProvider ylp) {
    addExperience(player, withdrawAmount * -1, config, ylp);
  }
}
