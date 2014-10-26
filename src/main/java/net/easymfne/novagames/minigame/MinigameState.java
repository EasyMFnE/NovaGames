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

/**
 * Enumeration of the various potential states of a Minigame.
 */
public enum MinigameState {

  /**
   * The Minigame has been created and components initialized.
   * */
  CREATED,

  /**
   * The Minigame is open for players to join.
   */
  OPEN,

  /**
   * The Minigame is transitioning from OPEN to ACTIVE. Not strictly necessary, but can be useful
   * for situations where this transition is not instantaneous.
   */
  STARTING,

  /**
   * The Minigame is active and currently running.
   */
  ACTIVE,

  /**
   * The Minigame is transitioning from ACTIVE to FINISHED. Not strictly necessary, but can be
   * useful for situations where this transition is not instantaneous.
   */
  ENDING,

  /**
   * The Minigame is finished, and should not experience any further activity.
   */
  FINISHED,

  /**
   * The Minigame has been completely closed and all of its components have been destroyed.
   */
  DESTROYED;

}
