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

import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public abstract class MinigameComponent implements Listener {

  private Minigame minigame;

  public void close() {
    HandlerList.unregisterAll(this);
  }

  public Minigame getMinigame() {
    return minigame;
  }

  public void init(Minigame minigame) {
    this.minigame = minigame;
    minigame.getPlugin().getServer().getPluginManager().registerEvents(this, minigame.getPlugin());
  }

}
