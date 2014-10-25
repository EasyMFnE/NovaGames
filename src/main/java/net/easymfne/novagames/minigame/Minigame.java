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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.easymfne.novagames.NovaGames;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class Minigame {

  private NovaGames plugin;
  private Map<Class<? extends MinigameComponent>, MinigameComponent> components;
  private MinigameState state;
  private Set<Player> participants;
  private Set<Player> spectators;

  public Minigame(NovaGames plugin) {
    this.plugin = plugin;
    components = new HashMap<Class<? extends MinigameComponent>, MinigameComponent>();
    state = MinigameState.CREATED;
  }

  /**
   * @param player Player to add to the participant registry.
   * @return Whether the player was added.
   */
  public final boolean addParticipant(Player player) {
    return participants.add(player);
  }

  /**
   * @param player Player to add to the participant registry.
   * @return Whether the player was added.
   */
  public final boolean addSpectator(Player player) {
    return spectators.add(player);
  }

  /**
   * Destroy the event and allow it to be garbage collected.
   */
  public void destroy() {
    participants.clear();
    spectators.clear();
    for (MinigameComponent component : components.values()) {
      component.destroy();
    }
  }

  public final MinigameComponent getComponent(Class<? extends MinigameComponent> componentClass) {
    return components.get(componentClass);
  }

  public final NovaGames getPlugin() {
    return plugin;
  }

  public final MinigameState getState() {
    return state;
  }

  public void init(ConfigurationSection eventConfig) {
    participants = new HashSet<Player>();
    spectators = new HashSet<Player>();
  }

  public final boolean isParticipant(Player player) {
    return participants.contains(player);
  }

  public final boolean isSpectator(Player player) {
    return spectators.contains(player);
  }

  public final boolean removeParticipant(Player player) {
    return participants.remove(player);
  }

  public final boolean removeSpectator(Player player) {
    return spectators.remove(player);
  }

  public final void setState(MinigameState state) {
    this.state = state;
  }

}
