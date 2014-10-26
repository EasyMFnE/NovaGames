/*
 * This file is part of the NovaGames plugin by EasyMFnE.
 * 
 * NovaGames is free software: you can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or any later version.
 * 
 * NovaGames is distributed in the hope that it will be useful, but without any warranty; without
 * even the implied warranty of merchantability or fitness for a particular purpose. See the GNU
 * General Public License for details.
 * 
 * You should have received a copy of the GNU General Public License v3 along with NovaGames. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package net.easymfne.novagames.minigame;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

/**
 * A component of a Minigame, responsible for its own logic and listening for events such as changes
 * in the Minigame's state.
 */
public abstract class MinigameComponent implements Listener {

  private Minigame minigame;

  /**
   * Unregister all events and destroy the component so all of its objects can be garbage collected.
   */
  public void destroy() {
    HandlerList.unregisterAll(this);
  }

  /**
   * @return The Minigame instance associated with this MinigameComponent instance.
   */
  public Minigame getMinigame() {
    return minigame;
  }

  /**
   * Initialize the Minigame, registering its EventHandlers and performing any other necessary
   * actions.
   * 
   * @param minigame The Minigame instance the component should be associated with.
   * @param componentConfig The ConfigurationSection representing the component's configuration.
   */
  public void init(Minigame minigame, ConfigurationSection componentConfig) {
    this.minigame = minigame;
    minigame.getPlugin().getServer().getPluginManager().registerEvents(this, minigame.getPlugin());
  }

}
