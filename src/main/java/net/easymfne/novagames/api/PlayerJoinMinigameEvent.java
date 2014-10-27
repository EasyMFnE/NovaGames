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
package net.easymfne.novagames.api;

import net.easymfne.novagames.minigame.Minigame;
import net.easymfne.novagames.minigame.MinigameComponent;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Cancellable event representing a player joining a Minigame.
 */
public class PlayerJoinMinigameEvent extends Event implements Cancellable {

  private static final HandlerList handlers = new HandlerList();

  private Player player;
  private Minigame minigame;
  private Location destination;
  private MinigameComponent componentResponsible;
  private boolean cancelled;

  /**
   * @param player The Player joining.
   * @param minigame The Minigame instance this event pertains to.
   * @param componentResponsible The MinigameComponent responsible for the event.
   */
  public PlayerJoinMinigameEvent(Player player, Minigame minigame, Location destination,
      MinigameComponent componentResponsible) {
    this.player = player;
    this.minigame = minigame;
    this.destination = destination;
    this.componentResponsible = componentResponsible;
    cancelled = false;
  }

  /**
   * @return The MinigameComponent responsible for the event.
   */
  public MinigameComponent getComponentResponsible() {
    return componentResponsible;
  }

  /**
   * @return The destination Location, possibly null.
   */
  public Location getDestination() {
    return destination;
  }

  /**
   * @see org.bukkit.event.Event#getHandlers()
   */
  @Override
  public HandlerList getHandlers() {
    return handlers;
  }

  /**
   * @return The Minigame instance this event pertains to.
   */
  public Minigame getMinigame() {
    return minigame;
  }

  /**
   * @return The Player joining the Minigame.
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * @return Whether the event has been cancelled (and thus the Player will not be allowed to join).
   */
  @Override
  public final boolean isCancelled() {
    return cancelled;
  }

  /**
   * @param cancelled Whether the event should be cancelled.
   */
  @Override
  public void setCancelled(boolean cancelled) {
    this.cancelled = cancelled;
  }

  /**
   * @param destination The new destination to set.
   */
  public void setDestination(Location destination) {
    this.destination = destination;
  }

}
