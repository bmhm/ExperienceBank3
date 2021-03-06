package com.empcraft.xpbank.text;

import com.empcraft.xpbank.ExpBankConfig;
import com.empcraft.xpbank.err.ConfigurationException;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.logging.Level;

public class YamlLanguageProvider {
  private final YamlConfiguration langYaml;
  private ExpBankConfig config;

  public YamlLanguageProvider(final ExpBankConfig config)
      throws ConfigurationException {
    this.config = config;
    File languageFile = config.getLanguageFile();

    try {
      this.langYaml = YamlConfiguration.loadConfiguration(languageFile);
    } catch (IllegalArgumentException iae) {
      throw new ConfigurationException(iae);
    }
  }

  public String getMessage(Text key) {
    String message = MessageUtils.colorise(langYaml.getString(key.name()));

    if (null == message || "".equals(message)) {
      config.getLogger().log(Level.INFO, "Could get Message for key [" + key + "].");
      message = "";
    }

    return message;
  }

}
