package com.empcraft.xpbank.logic;

import com.empcraft.xpbank.ExpBankConfig;
import com.empcraft.xpbank.text.MessageUtils;
import com.empcraft.xpbank.threads.UpdateAllSignsThread;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import java.util.List;

public final class SignHelper {

  /**
   * Utility class.
   */
  private SignHelper() {
  }

  /**
   * Updates a specific sign if a player is near.
   *
   * @param player
   *          the player who must be near.
   * @param sign
   *          the Sign to be updated.
   * @param expBankConfig
   *          The plugin config.
   */
  public static void updateSign(Player player, Sign sign, final ExpBankConfig config) {
    Location location = sign.getLocation();
    Block block = location.getBlock();

    if (block == null) {
      config.getLogger().finer("not a block.");
      return;
    }

    if (block.getState() == null) {
      config.getLogger().finer("not a stateful block.");
      return;
    }

    if (!(block.getState() instanceof Sign)) {
      config.getLogger().finer("not a sign.");
      return;
    }

    if (player == null || !player.isOnline()) {
      config.getLogger().finer("player is not online.");
      return;
    }

    if (!location.getWorld().equals(player.getWorld())) {
      config.getLogger().finer("player is in another world.");
      return;
    }

    if (!location.getChunk().isLoaded()) {
      config.getLogger().finer("chunk is not loaded.");
      return;
    }

    double distance = location.distanceSquared(player.getLocation());

    if (distance > 1024 * 2) {
      config.getLogger().finer("sign is too far away: [" + Double.toString(distance) + "].");
      return;
    }

    String[] lines = SignHelper.getSignText(player, config);

    if (lines == null) {
      config.getLogger().finer("sign has no text.");
      return;
    }

    if (!lines[0].contains(config.getExperienceBankActivationString()) ||
        lines[0].contains(config.getSignContent().get(0))
        || lines[0].contains(MessageUtils.colorise(config.getSignContent().get(0)))) {
      return;
    }

    String[] signText = SignHelper.getSignText(player, config);
    for (int ii = 0; ii < signText.length; ii++) {
      config.getLogger().finer(
          "Setting line [{}] to [{}]."
              .replaceFirst("\\{\\}", Integer.toString(ii))
              .replaceFirst("\\{\\}", signText[ii]));
      sign.setLine(ii, signText[ii]);
    }

    player.sendSignChange(sign.getLocation(), signText);
    sign.update();
  }

  public static boolean isExperienceBankSignBlock(final Block block, final ExpBankConfig config) {
    boolean expBankSign = false;

    if (block.getType() != Material.SIGN_POST && block.getType() != Material.WALL_SIGN) {
      // listen only for signs.
      return expBankSign;
    }

    Sign sign = (Sign) block.getState();

    String firstLine = sign.getLines()[0];
    String coloredExpSign = MessageUtils.colorise(config.getSignContent().get(0));

    if (coloredExpSign.equals(firstLine)) {
      expBankSign = true;
    }

    return expBankSign;
  }

  public static String renderSignLines(String unrenderedLine, Player player,
      int storedPlayerExperience) {
    int playerCurrentXp = player.getTotalExperience();
    int levelsInBank = ExperienceLevelCalculator.getLevel(storedPlayerExperience);
    int levelafterWithdraw = ExperienceLevelCalculator
        .getLevel(storedPlayerExperience + playerCurrentXp);
    int leveldelta = levelafterWithdraw - player.getLevel();

    String renderedLine = unrenderedLine
        .replace(MessageUtils.MAGIC_KEYWORD_PLAYERNAME, player.getName())
        .replace(MessageUtils.MAGIC_KEYWORD_STORED_XP, Integer.toString(storedPlayerExperience))
        .replace(MessageUtils.MAGIC_KEYWORD_CURRENT_XP, Integer.toString(playerCurrentXp))
        .replace(MessageUtils.MAGIC_KEYWORD_CURRENT_LVL, Integer.toString(player.getLevel()))
        .replace(MessageUtils.MAGIC_KEYWORD_LEVELS_IN_BANK, Integer.toString(levelsInBank))
        .replace(MessageUtils.MAGIC_KEYWORD_LEVELS_GAIN_WITHDRAW, Integer.toString(leveldelta));

    return MessageUtils.colorise(renderedLine);
  }

  public static String[] getSignText(final Player player, final ExpBankConfig config) {
    int storedPlayerExperience;
    String[] lines = new String[4];
    List<String> configuredContent = config.getSignContent();

    storedPlayerExperience = config.getExperienceCache().get(player.getUniqueId()).get();

    for (int line = 0; line < 4; line++) {
      String evaluatedLine = renderSignLines(
          configuredContent.get(line),
          player,
          storedPlayerExperience);
      lines[line] = evaluatedLine;
    }

    return lines;
  }

  /**
   * Updates all Chunks around the location.
   *
   * @param player
   *          the player at the location which must be online.
   * @param location
   *          the location to update including nearby chunks.
   */
  public static void scheduleUpdate(final Player player, final Location location,
      final ExpBankConfig expBankConfig) {
    new UpdateAllSignsThread(player, location, expBankConfig).run();
  }
}
