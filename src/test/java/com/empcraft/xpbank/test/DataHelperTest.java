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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@PrepareForTest({ ExpBankConfig.class, JavaPlugin.class, Player.class })
public class DataHelperTest {

  private static final String TEST_PLAYER1 = "testPlayer1";
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

    Player player1 = new OfflinePlayer(TEST_PLAYER1, server);
    HashMap<UUID, Integer> players = new HashMap<UUID, Integer>();
    players.put(player1.getUniqueId(), 0);
    dh.bulkSaveEntriesToDb(players);
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
    Player someone = new OfflinePlayer(TEST_PLAYER1, server);
    int savedExperience = dh.getSavedExperience(someone);

    Assert.assertEquals(0, savedExperience);
  }

  @Test
  public void testGetSavedExperienceFromAll() throws DatabaseConnectorException {
    DataHelper dh = new DataHelper(ylp, config);
    Map<UUID, Integer> savedExperience = dh.getSavedExperience();

    Assert.assertTrue(savedExperience.size() >= 1);
  }

  @Test
  public void testBulkSaveEntry() throws DatabaseConnectorException {
    DataHelper dh = new DataHelper(ylp, config);
    HashMap<UUID, Integer> players = new HashMap<>();
    UUID randomUUID = UUID.randomUUID();
    players.put(randomUUID, 42);
    dh.bulkSaveEntriesToDb(players);

    int savedExperience = dh.getSavedExperience(randomUUID);

    Assert.assertEquals(42, savedExperience);
  }

  @Test
  public void testBulkSaveEntry_null() throws DatabaseConnectorException {
    DataHelper dh = new DataHelper(ylp, config);
    HashMap<UUID, Integer> players = null;
    UUID randomUUID = UUID.randomUUID();
    dh.bulkSaveEntriesToDb(players);

    int savedExperience = dh.getSavedExperience(randomUUID);

    Assert.assertEquals(0, savedExperience);
  }

  @Test
  public void testBulkSaveEntry_empty() throws DatabaseConnectorException {
    DataHelper dh = new DataHelper(ylp, config);
    HashMap<UUID, Integer> players = new HashMap<>();
    UUID randomUUID = UUID.randomUUID();
    dh.bulkSaveEntriesToDb(players);

    int savedExperience = dh.getSavedExperience(randomUUID);

    Assert.assertEquals(0, savedExperience);
  }

}
