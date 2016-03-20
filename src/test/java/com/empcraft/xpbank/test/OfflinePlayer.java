package com.empcraft.xpbank.test;

import com.empcraft.xpbank.logic.ExperienceLevelCalculator;

import org.bukkit.Achievement;
import org.bukkit.BanList;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Particle;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Vehicle;
import org.bukkit.entity.Villager;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.InventoryView.Property;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class OfflinePlayer implements Player {
  private final transient Server server;
  private transient Location location = new Location(null, 0, 0, 0, 0, 0);
  private transient World world;
  private transient org.bukkit.OfflinePlayer base;
  private boolean allowFlight = false;
  private boolean isFlying = false;
  private String name = null;
  private int totalExperience = 0;

  public OfflinePlayer(final UUID uuid, final Server server) {
    this.server = server;
    this.world = server.getWorlds().get(0);
    this.base = server.getOfflinePlayer(uuid);
    this.name = base.getName();
  }

  public OfflinePlayer(final String name, final Server server) {
    this.server = server;
    this.world = server.getWorlds().get(0);
    this.base = server.getOfflinePlayer(name);
    this.name = name;
  }

  @Override
  public void sendMessage(final String string) {
  }

  @Override
  public String getDisplayName() {
    return base.getName();
  }

  @Override
  public void setDisplayName(String string) {
  }

  @Override
  public void setCompassTarget(Location lctn) {
  }

  @Override
  public InetSocketAddress getAddress() {
    return null;
  }

  @Override
  public void kickPlayer(String string) {
  }

  @Override
  public PlayerInventory getInventory() {
    return null;
  }

  @Override
  public ItemStack getItemInHand() {
    return null;
  }

  @Override
  public void setItemInHand(ItemStack is) {
  }

  @Override
  public double getHealth() {
    return 0;
  }

  @Override
  public void setHealth(double d) {
  }

  @Override
  public boolean isInsideVehicle() {
    return false;
  }

  @Override
  public boolean leaveVehicle() {
    return false;
  }

  @Override
  public Vehicle getVehicle() {
    return null;
  }

  @Override
  public Location getLocation() {
    return location;
  }

  @Override
  public World getWorld() {
    return world;
  }

  public void setLocation(Location loc) {
    location = loc;
    world = loc.getWorld();
  }

  public void teleportTo(Location lctn) {
  }

  public void teleportTo(Entity entity) {
  }

  @Override
  public int getEntityId() {
    return -1;
  }

  @Override
  public boolean performCommand(String string) {
    return false;
  }

  @Override
  public int getRemainingAir() {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void setRemainingAir(int i) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public int getMaximumAir() {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void setMaximumAir(int i) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public boolean isSneaking() {
    return false;
  }

  @Override
  public void setSneaking(boolean bln) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void updateInventory() {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void chat(String string) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public double getEyeHeight() {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public double getEyeHeight(boolean bln) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public List<Block> getLineOfSight(HashSet<Byte> hs, int i) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public Block getTargetBlock(HashSet<Byte> hs, int i) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public List<Block> getLastTwoTargetBlocks(HashSet<Byte> hs, int i) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public int getFireTicks() {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public int getMaxFireTicks() {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void setFireTicks(int i) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public Server getServer() {
    return server;
  }

  public Vector getMomentum() {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  public void setMomentum(Vector vector) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void setVelocity(Vector vector) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public Vector getVelocity() {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void damage(double d) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void damage(double d, Entity entity) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public Location getEyeLocation() {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void sendRawMessage(String string) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public Location getCompassTarget() {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public int getMaximumNoDamageTicks() {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void setMaximumNoDamageTicks(int i) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public double getLastDamage() {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void setLastDamage(double d) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public int getNoDamageTicks() {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void setNoDamageTicks(int i) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public boolean teleport(Location lctn) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public boolean teleport(Entity entity) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public Entity getPassenger() {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public boolean setPassenger(Entity entity) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public boolean isEmpty() {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public boolean eject() {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void saveData() {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void loadData() {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public boolean isSleeping() {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public int getSleepTicks() {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public List<Entity> getNearbyEntities(double d, double d1, double d2) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public boolean isDead() {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public float getFallDistance() {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void setFallDistance(float f) {
  }

  @Override
  public void setSleepingIgnored(boolean bln) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public boolean isSleepingIgnored() {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void awardAchievement(Achievement a) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void removeAchievement(Achievement achievement) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public boolean hasAchievement(Achievement achievement) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void incrementStatistic(Statistic ststc) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void decrementStatistic(Statistic statistic) throws IllegalArgumentException {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void incrementStatistic(Statistic ststc, int i) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void decrementStatistic(Statistic statistic, int i) throws IllegalArgumentException {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void setStatistic(Statistic statistic, int i) throws IllegalArgumentException {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public int getStatistic(Statistic statistic) throws IllegalArgumentException {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void incrementStatistic(Statistic ststc, Material mtrl) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void decrementStatistic(Statistic statistic, Material material)
      throws IllegalArgumentException {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public int getStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void incrementStatistic(Statistic ststc, Material mtrl, int i) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void decrementStatistic(Statistic statistic, Material material, int i)
      throws IllegalArgumentException {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void setStatistic(Statistic statistic, Material material, int i)
      throws IllegalArgumentException {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void incrementStatistic(Statistic statistic, EntityType entityType)
      throws IllegalArgumentException {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void decrementStatistic(Statistic statistic, EntityType entityType)
      throws IllegalArgumentException {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public int getStatistic(Statistic statistic, EntityType entityType)
      throws IllegalArgumentException {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void incrementStatistic(Statistic statistic, EntityType entityType, int i)
      throws IllegalArgumentException {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void decrementStatistic(Statistic statistic, EntityType entityType, int i) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void setStatistic(Statistic statistic, EntityType entityType, int i) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void playNote(Location lctn, byte b, byte b1) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void sendBlockChange(Location lctn, Material mtrl, byte b) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void sendBlockChange(Location lctn, int i, byte b) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void setLastDamageCause(EntityDamageEvent ede) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public EntityDamageEvent getLastDamageCause() {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public void playEffect(Location lctn, Effect effect, int i) {
    throw new UnsupportedOperationException("notSupportedYet");
  }

  @Override
  public boolean sendChunkChange(Location lctn, int i, int i1, int i2, byte[] bytes) {
    return true;
  }

  @Override
  public void playNote(Location lctn, Instrument i, Note note) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setPlayerTime(long l, boolean bln) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public long getPlayerTime() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public long getPlayerTimeOffset() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean isPlayerTimeRelative() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void resetPlayerTime() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean isPermissionSet(String string) {
    return false;
  }

  @Override
  public boolean isPermissionSet(Permission prmsn) {
    return false;
  }

  @Override
  public boolean hasPermission(String string) {
    return false;
  }

  @Override
  public boolean hasPermission(Permission prmsn) {
    return false;
  }

  @Override
  public PermissionAttachment addAttachment(Plugin plugin, String string, boolean bln) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public PermissionAttachment addAttachment(Plugin plugin) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public PermissionAttachment addAttachment(Plugin plugin, String string, boolean bln, int i) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public PermissionAttachment addAttachment(Plugin plugin, int i) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void removeAttachment(PermissionAttachment pa) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void recalculatePermissions() {
  }

  @Override
  public Set<PermissionAttachmentInfo> getEffectivePermissions() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void sendMap(MapView mv) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public GameMode getGameMode() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setGameMode(GameMode gm) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public int getLevel() {
    return ExperienceLevelCalculator.getLevel(getTotalExperience());
  }

  @Override
  public void setLevel(int i) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public int getTotalExperience() {
    return totalExperience;
  }

  @Override
  public void setTotalExperience(int i) {
    this.totalExperience = i;
  }

  @Override
  public float getExhaustion() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setExhaustion(float f) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public float getSaturation() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setSaturation(float f) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public int getFoodLevel() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setFoodLevel(int i) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean isSprinting() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setSprinting(boolean bln) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setPlayerListName(String name) {
  }

  @Override
  public String getPlayerListName() {
    return name;
  }

  @Override
  public int getTicksLived() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setTicksLived(int i) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public double getMaxHealth() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void giveExp(int i) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public float getExp() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setExp(float f) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean teleport(Location lctn, TeleportCause tc) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean teleport(Entity entity, TeleportCause tc) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Player getKiller() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  void setName(final String name) {
    this.name = base.getName();
    if (this.name == null) {
      this.name = name;
    }
  }

  @Override
  public void sendPluginMessage(Plugin plugin, String string, byte[] bytes) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Set<String> getListeningPluginChannels() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setAllowFlight(boolean bln) {
    allowFlight = bln;
  }

  @Override
  public boolean getAllowFlight() {
    return allowFlight;
  }

  @Override
  public void setBedSpawnLocation(Location lctn) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setBedSpawnLocation(Location lctn, boolean force) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void playEffect(EntityEffect ee) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void hidePlayer(Player player) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void showPlayer(Player player) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean canSee(Player player) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean addPotionEffect(PotionEffect pe) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean addPotionEffect(PotionEffect pe, boolean bln) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean addPotionEffects(Collection<PotionEffect> clctn) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean hasPotionEffect(PotionEffectType pet) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void removePotionEffect(PotionEffectType pet) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<PotionEffect> getActivePotionEffects() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public <T extends Projectile> T launchProjectile(Class<? extends T> arg0) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public EntityType getType() {
    return EntityType.PLAYER;
  }

  @Override
  public <T> void playEffect(Location lctn, Effect effect, T t) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean setWindowProperty(Property prprt, int i) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public InventoryView getOpenInventory() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public InventoryView openInventory(Inventory invntr) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public InventoryView openWorkbench(Location lctn, boolean bln) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public InventoryView openEnchanting(Location lctn, boolean bln) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void openInventory(InventoryView iv) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void closeInventory() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public ItemStack getItemOnCursor() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setItemOnCursor(ItemStack is) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setMetadata(String string, MetadataValue mv) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public List<MetadataValue> getMetadata(String string) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean hasMetadata(String string) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void removeMetadata(String string, Plugin plugin) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean isConversing() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void acceptConversationInput(String string) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean beginConversation(Conversation c) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void abandonConversation(Conversation c) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void sendMessage(String[] strings) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean isBlocking() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void abandonConversation(Conversation arg0, ConversationAbandonedEvent arg1) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean isFlying() {
    return isFlying;
  }

  @Override
  public void setFlying(boolean arg0) {
    isFlying = arg0;
  }

  @Override
  public int getExpToLevel() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean hasLineOfSight(Entity entity) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean isValid() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setFlySpeed(float value) throws IllegalArgumentException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setWalkSpeed(float value) throws IllegalArgumentException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public float getFlySpeed() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public float getWalkSpeed() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Inventory getEnderChest() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void playSound(Location arg0, Sound arg1, float arg2, float arg3) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void giveExpLevels(int i) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean getRemoveWhenFarAway() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setRemoveWhenFarAway(boolean bln) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public EntityEquipment getEquipment() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setCanPickupItems(boolean bln) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean getCanPickupItems() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Location getLocation(Location lctn) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setTexturePack(String string) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setResourcePack(String s) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setMaxHealth(double i) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void resetMaxHealth() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setCustomName(String string) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public String getCustomName() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setCustomNameVisible(boolean bln) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean isCustomNameVisible() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setPlayerWeather(WeatherType arg0) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public WeatherType getPlayerWeather() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void resetPlayerWeather() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean isOnGround() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Scoreboard getScoreboard() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setScoreboard(Scoreboard scrbrd)
      throws IllegalArgumentException, IllegalStateException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public int _INVALID_getLastDamage() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void _INVALID_setLastDamage(int i) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void _INVALID_damage(int i) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void _INVALID_damage(int i, Entity entity) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public int _INVALID_getHealth() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void _INVALID_setHealth(int i) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public int _INVALID_getMaxHealth() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void _INVALID_setMaxHealth(int i) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void playSound(Location arg0, String arg1, float arg2, float arg3) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean isHealthScaled() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setHealthScaled(boolean arg0) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setHealthScale(double arg0) throws IllegalArgumentException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public double getHealthScale() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean isLeashed() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Entity getLeashHolder() throws IllegalStateException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean setLeashHolder(Entity arg0) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public <T extends Projectile> T launchProjectile(Class<? extends T> type, Vector vector) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void sendSignChange(Location arg0, String[] arg1) throws IllegalArgumentException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Location getBedSpawnLocation() {
    return null;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public UUID getUniqueId() {
    return base.getUniqueId();
  }

  @Override
  public boolean isOp() {
    return base.isOp();
  }

  @Override
  public void setOp(boolean value) {
    base.setOp(value);
  }

  @Override
  public boolean isOnline() {
    return base.isOnline();
  }

  @Override
  public boolean isBanned() {
    if (base.getName() == null && getName() != null) {
      return server.getBanList(BanList.Type.NAME).isBanned(getName());
    }
    return base.isBanned();
  }

  @Override
  public void setBanned(boolean banned) {
    if (base.getName() == null && getName() != null) {
      if (banned) {
        server.getBanList(BanList.Type.NAME).addBan(getName(), null, null, null);
      } else {
        server.getBanList(BanList.Type.NAME).pardon(getName());
      }
    }
    base.setBanned(banned);
  }

  @Override
  public boolean isWhitelisted() {
    return base.isWhitelisted();
  }

  @Override
  public void setWhitelisted(boolean value) {
    base.setWhitelisted(value);
  }

  @Override
  public Player getPlayer() {
    return base.getPlayer();
  }

  @Override
  public long getFirstPlayed() {
    return base.getFirstPlayed();
  }

  @Override
  public long getLastPlayed() {
    return base.getLastPlayed();
  }

  @Override
  public boolean hasPlayedBefore() {
    return base.hasPlayedBefore();
  }

  @Override
  public Map<String, Object> serialize() {
    return base.serialize();
  }

  @Override
  public List<Block> getLineOfSight(Set<Material> set, int i) {
    throw new UnsupportedOperationException("Not supported yet."); // To change body of generated
                                                                   // methods, choose Tools |
                                                                   // Templates.
  }

  @Override
  public Block getTargetBlock(Set<Material> set, int i) {
    throw new UnsupportedOperationException("Not supported yet."); // To change body of generated
                                                                   // methods, choose Tools |
                                                                   // Templates.
  }

  @Override
  public List<Block> getLastTwoTargetBlocks(Set<Material> set, int i) {
    throw new UnsupportedOperationException("Not supported yet."); // To change body of generated
                                                                   // methods, choose Tools |
                                                                   // Templates.
  }

  @Override
  public MainHand getMainHand() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public InventoryView openMerchant(Villager trader, boolean force) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isGliding() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void setGliding(boolean gliding) {
    // TODO Auto-generated method stub

  }

  @Override
  public AttributeInstance getAttribute(Attribute attribute) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setGlowing(boolean flag) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean isGlowing() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Entity getSpectatorTarget() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setSpectatorTarget(Entity entity) {
    // TODO Auto-generated method stub

  }

  @Override
  public void sendTitle(String title, String subtitle) {
    // TODO Auto-generated method stub

  }

  @Override
  public void resetTitle() {
    // TODO Auto-generated method stub

  }

  @Override
  public void spawnParticle(Particle particle, Location location, int count) {
    // TODO Auto-generated method stub

  }

  @Override
  public void spawnParticle(Particle particle, double x, double y, double z, int count) {
    // TODO Auto-generated method stub

  }

  @Override
  public <T> void spawnParticle(Particle particle, Location location, int count, T data) {
    // TODO Auto-generated method stub

  }

  @Override
  public <T> void spawnParticle(Particle particle, double x, double y, double z, int count,
      T data) {
    // TODO Auto-generated method stub

  }

  @Override
  public void spawnParticle(Particle particle, Location location, int count, double offsetX,
      double offsetY, double offsetZ) {
    // TODO Auto-generated method stub

  }

  @Override
  public void spawnParticle(Particle particle, double x, double y, double z, int count,
      double offsetX, double offsetY, double offsetZ) {
    // TODO Auto-generated method stub

  }

  @Override
  public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX,
      double offsetY, double offsetZ, T data) {
    // TODO Auto-generated method stub

  }

  @Override
  public <T> void spawnParticle(Particle particle, double x, double y, double z, int count,
      double offsetX, double offsetY, double offsetZ, T data) {
    // TODO Auto-generated method stub

  }

  @Override
  public void spawnParticle(Particle particle, Location location, int count, double offsetX,
      double offsetY, double offsetZ, double extra) {
    // TODO Auto-generated method stub

  }

  @Override
  public void spawnParticle(Particle particle, double x, double y, double z, int count,
      double offsetX, double offsetY, double offsetZ, double extra) {
    // TODO Auto-generated method stub

  }

  @Override
  public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX,
      double offsetY, double offsetZ, double extra, T data) {
    // TODO Auto-generated method stub

  }

  @Override
  public <T> void spawnParticle(Particle particle, double x, double y, double z, int count,
      double offsetX, double offsetY, double offsetZ, double extra, T data) {
    // TODO Auto-generated method stub

  }
}
