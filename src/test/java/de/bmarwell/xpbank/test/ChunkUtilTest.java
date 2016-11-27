package de.bmarwell.xpbank.test;

import code.husky.Backend;
import code.husky.DatabaseConnectorException;

import com.empcraft.xpbank.ExpBankConfig;
import com.empcraft.xpbank.err.ConfigurationException;
import com.empcraft.xpbank.test.helpers.ConfigHelper;
import com.empcraft.xpbank.test.helpers.FakeServer;
import com.empcraft.xpbank.test.helpers.FakeWorld;
import com.empcraft.xpbank.util.ChunkUtil;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;

@PrepareForTest({ ExpBankConfig.class, JavaPlugin.class })
public class ChunkUtilTest {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ChunkUtilTest.class);

  @Rule
  public PowerMockRule rule = new PowerMockRule();

  private World world;

  private ExpBankConfig config;

  @Before
  public void setUp() throws ConfigurationException, IOException,
      InvalidConfigurationException, DatabaseConnectorException {
    this.config = ConfigHelper.getFakeConfig().withLanguage("english")
        .withBackend(Backend.YAML.toString()).build();

    try {
      FakeServer fakeServer = new FakeServer();
      Bukkit.setServer(fakeServer);
    } catch (UnsupportedOperationException ex) {
      LOG.trace("Using existing server.", ex);
    }

    world = new FakeWorld("name", Environment.NORMAL);
  }

  @Test
  public void testGetLoadedChunksAroundLocation() {

    Location loc = world.getSpawnLocation();
    Collection<Chunk> loadedChunksAroundLocation = ChunkUtil.getLoadedChunksAroundLocation(loc, config);

    LOG.debug("Chunks gefunden: [{}].", loadedChunksAroundLocation.size());
    Assert.assertTrue(loadedChunksAroundLocation.size() <= 9);

    for (Chunk chunk : loadedChunksAroundLocation) {
      LOG.debug("X: [{}]. Z: [{}].", chunk.getX(), chunk.getZ());
    }
  }
}
