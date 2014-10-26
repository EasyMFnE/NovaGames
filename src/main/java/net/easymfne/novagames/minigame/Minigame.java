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
import net.easymfne.novagames.api.MinigameStateChangeEvent;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

/**
 * Minigame class, almost exclusively comprised of a collection of MinigameComponents. Does maintain
 * an object representing its state and a set of players that are participating in the event in some
 * form.
 */
public class Minigame {

  private NovaGames plugin;
  private Map<Class<? extends MinigameComponent>, MinigameComponent> components;
  private MinigameState state;
  private Set<Player> participants;

  /**
   * Construct a new Minigame instance with a reference to the plugin. Instantiates an empty
   * component map and defines the Minigame state to be CREATED.
   * 
   * @param plugin Reference to NovaGames plugin instance.
   */
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
   * Change the Minigame's state. Creates a new MinigameStateChangeEvent and has the PluginManager
   * call it. If the event is not cancelled, the state is changed.
   * 
   * @param newState The requested new state.
   * @param componentResponsible The MinigameComponent that is requesting the state change.
   * @return Whether the state was changed.
   */
  public final boolean changeState(MinigameState newState, MinigameComponent componentResponsible) {
    Cancellable event = new MinigameStateChangeEvent(this, state, newState, componentResponsible);
    plugin.getServer().getPluginManager().callEvent((Event) event);
    if (!event.isCancelled()) {
      state = newState;
      return true;
    }
    return false;
  }

  /**
   * Destroy the Minigame and its components, allowing everything to be garbage collected.
   */
  public void destroy() {
    state = MinigameState.DESTROYED;
    for (MinigameComponent component : components.values()) {
      component.destroy();
    }
    components.clear();
    participants.clear();
  }

  /**
   * @param componentClass The Class type of the requested component.
   * @return The component instance, or null if none exists.
   */
  public final MinigameComponent getComponent(Class<? extends MinigameComponent> componentClass) {
    return components.get(componentClass);
  }

  /**
   * @return Reference to the NovaGames plugin instance.
   */
  public final NovaGames getPlugin() {
    return plugin;
  }

  /**
   * @return The current MinigameState.
   */
  public final MinigameState getState() {
    return state;
  }

  /**
   * Initialize the Minigame by reading the component list from the configuration, creating new
   * instances of them and passing the configuration on.
   * 
   * @param eventConfig The ConfigurationSection representing the Minigame's configuration.
   */
  public void init(ConfigurationSection eventConfig) {
    participants = new HashSet<Player>();
    // TODO: Read list of component class names from config, creating them and adding them to the
    // components map.
    for (MinigameComponent component : components.values()) {
      component.init(this, eventConfig); // TODO: replace eventConfig with the component subsection.
    }
  }

  /**
   * @param player The player.
   * @return Whether the player is counted among the Minigame's participants.
   */
  public final boolean isParticipant(Player player) {
    return participants.contains(player);
  }

  /**
   * @param player The player.
   * @return Whether the player was removed from the Minigame's participants. Will return false if
   *         the player was not a participant.
   */
  public final boolean removeParticipant(Player player) {
    return participants.remove(player);
  }

}
