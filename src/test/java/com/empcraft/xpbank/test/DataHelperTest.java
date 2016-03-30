package com.empcraft.xpbank.test;

import code.husky.Backend;
import code.husky.DatabaseConnectorException;

import com.empcraft.xpbank.ExpBankConfig;
import com.empcraft.xpbank.logic.DataHelper;
import com.empcraft.xpbank.test.helpers.ConfigHelper;
import com.empcraft.xpbank.test.helpers.FakeServer;
import com.empcraft.xpbank.test.helpers.OfflinePlayer;
import com.empcraft.xpbank.text.YamlLanguageProvider;

import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;

import java.util.UUID;

@PrepareForTest({ ExpBankConfig.class, JavaPlugin.class, Player.class })
public class DataHelperTest {

  @Rule
  public PowerMockRule rule = new PowerMockRule();
  private ExpBankConfig config;
  private YamlLanguageProvider ylp;
  private FakeServer server;

  @Before
  public void setUp() throws Exception {
    this.config = ConfigHelper.getFakeConfig().withLanguage("english")
        .withBackend(Backend.SQLITE.toString()).build();
    this.ylp = new YamlLanguageProvider(config);
    this.server = new FakeServer();
    server.createWorld(UUID.randomUUID().toString(), Environment.NORMAL);

    DataHelper dh = new DataHelper(ylp, config);
    dh.createTableIfNotExists();
  }

  @Test
  public void testGetSavedExperienceFromPlayer_nullPlayer() throws DatabaseConnectorException {
    DataHelper dh = new DataHelper(ylp, config);
    Player nullplayer = null;
    int savedExperience = dh.getSavedExperience(nullplayer);

    Assert.assertEquals(0, savedExperience);
  }

  @Test
  public void testGetSavedExperienceFromPlayer_nullUUID() throws DatabaseConnectorException {
    DataHelper dh = new DataHelper(ylp, config);
    UUID nullplayer = null;
    int savedExperience = dh.getSavedExperience(nullplayer);

    Assert.assertEquals(0, savedExperience);
  }

  @Test
  public void testGetSavedExperienceFromPlayer_OfflinePlayer() throws DatabaseConnectorException {
    DataHelper dh = new DataHelper(ylp, config);
    Player someone = new OfflinePlayer("testPlayer1", server);
    int savedExperience = dh.getSavedExperience(someone);

    Assert.assertEquals(0, savedExperience);
  }

}
