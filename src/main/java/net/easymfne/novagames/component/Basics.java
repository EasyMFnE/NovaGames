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
package net.easymfne.novagames.component;

import net.easymfne.novagames.minigame.MinigameComponent;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

public class Basics extends MinigameComponent {

  public static Entity getDamager(EntityDamageByEntityEvent event) {
    if (event.getDamager() instanceof Projectile) {
      ProjectileSource shooter = ((Projectile) event.getDamager()).getShooter();
      if (shooter instanceof Entity) {
        return (Entity) shooter;
      }
    }
    return event.getDamager();
  }
  
  private boolean pveEnabled = false;
  private boolean pvpEnabled = false;

  public final boolean isPveEnabled() {
    return pveEnabled;
  }

  public final boolean isPvpEnabled() {
    return pvpEnabled;
  }

  @EventHandler(ignoreCancelled = true)
  public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
    Entity victim = event.getEntity();
    Entity damager = getDamager(event);
    if (damager.getType() != EntityType.PLAYER
        || !super.getMinigame().isParticipant((Player) damager)) {
      return;
    }

    if (victim.getType() == EntityType.PLAYER) {
      if (!pvpEnabled) {
        event.setCancelled(true);
      }
    } else {
      if (!pveEnabled) {
        event.setCancelled(true);
      }
    }
  }

}
