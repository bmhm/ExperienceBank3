package com.empcraft.xpbank.listeners;

import com.empcraft.xpbank.ExpBankConfig;
import com.empcraft.xpbank.text.YamlLanguageProvider;
import com.empcraft.xpbank.threads.InsertPlayerThread;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

  private ExpBankConfig config;
  private YamlLanguageProvider ylp;

  public PlayerJoinListener(final ExpBankConfig config, final YamlLanguageProvider ylp) {
    this.config = config;
    this.ylp = ylp;
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    // insert player if not exists.
    InsertPlayerThread ipt = new InsertPlayerThread(event.getPlayer(), config, ylp);
    Bukkit.getServer().getScheduler().runTaskAsynchronously(config.getPlugin(), ipt);

    config.getExperienceCache().addPlayer(event.getPlayer().getUniqueId(), this.config);
  }

  public ExpBankConfig getConfig() {
    return this.config;
  }


  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("config", config)
        .append("ylp", ylp)
        .toString();
  }
}
