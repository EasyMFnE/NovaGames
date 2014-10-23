/*
 * This file is part of the NovaGames plugin by EasyMFnE.
 * 
 * NovaGames is free software: you can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or any later version.
 * 
 * Knapsacks is distributed in the hope that it will be useful, but without any warranty; without
 * even the implied warranty of merchantability or fitness for a particular purpose. See the GNU
 * General Public License for details.
 * 
 * You should have received a copy of the GNU General Public License v3 along with NovaGames. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package net.easymfne.novagames.minigame;

public enum MinigameState {
  CREATED, // Created and components initialized, but not yet opened to players.
  OPEN, // Open to players for joining.
  STARTING, // Minigame is transitioning from OPEN to ACTIVE.
  ACTIVE, // Minigame is currently running.
  ENDING, // Minigame is transitioning from ACTIVE to FINISHED.
  FINISHED, // Minigame has ended.
  CLOSED; // Minigame and all components have been closed.
}
