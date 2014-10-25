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
import net.easymfne.novagames.minigame.MinigameState;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Cancellable event representing a minigame's change from one state to another. As this event can
 * be cancelled, Listener priority is very important. Listeners that depend on the cancellation of
 * the event to not be changed after reading it MUST use Priority.MONITOR.
 */
public class MinigameStateChangeEvent extends Event implements Cancellable {

  private static final HandlerList handlers = new HandlerList();

  private Minigame minigame;
  private MinigameComponent componentResponsible;
  private MinigameState oldState;
  private MinigameState newState;
  private boolean cancelled;

  /**
   * @param minigame The Minigame instance this event pertains to.
   * @param oldState The current state of the Minigame.
   * @param newState The desired new state of the Minigame.
   * @param componentResponsible The MinigameComponent responsible for the event.
   */
  public MinigameStateChangeEvent(Minigame minigame, MinigameState oldState,
      MinigameState newState, MinigameComponent componentResponsible) {
    this.minigame = minigame;
    this.componentResponsible = componentResponsible;
    this.oldState = oldState;
    this.newState = newState;
    this.cancelled = false;
  }

  /**
   * @return The MinigameComponent responsible for the event.
   */
  public MinigameComponent getComponentResponsible() {
    return componentResponsible;
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
   * @return The current state of the Minigame.
   */
  public MinigameState getOldState() {
    return oldState;
  }

  /**
   * @return The desired new state of the Minigame.
   */
  public MinigameState getNewState() {
    return newState;
  }

  /**
   * /* @return Whether the event has been cancelled (and thus the state change will not be
   * applied).
   */
  @Override
  public final boolean isCancelled() {
    return cancelled;
  }

  /**
   * /* @param cancelled Whether the event should be cancelled.
   */
  @Override
  public void setCancelled(boolean cancelled) {
    this.cancelled = cancelled;
  }

}
