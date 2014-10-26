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
package net.easymfne.novagames;

import java.util.UUID;

import net.easymfne.novagames.exception.LocationFormatException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

/**
 * Configuration helper class, with methods for accessing the configuration.
 */
public class ConfigHelper {

  /**
   * Parse a Location from a configuration String of format:
   * "<world_name>, <x>, <y>, <z>[,<yaw>,<pitch>" or "<world_uuid>, <x>, <y>, <z>[,<yaw>,<pitch>".
   * 
   * @param locationString Location configuration string.
   * @return Location The Location represented.
   * @throws LocationFormatException if the configuration string does not match a valid format.
   */
  public static Location parseLocationString(String locationString) throws LocationFormatException {
    String[] parts = locationString.split(",\\s*");
    if (parts.length < 4 || parts.length > 6) {
      throw new LocationFormatException(locationString, String.format(
          "Invalid argument count: %d (must be between %d and %d", parts.length, 4, 6));
    }
    World world = parseWorldString(parts[0]);
    if (world == null) {
      throw new LocationFormatException(locationString,
          String.format("Unknown world: %s", parts[1]));
    }
    double[] pos;
    try {
      pos =
          new double[] {Double.parseDouble(parts[1]), Double.parseDouble(parts[2]),
              Double.parseDouble(parts[3])};
    } catch (NumberFormatException e) {
      throw new LocationFormatException(locationString, String.format("Invalid coordinate: %s",
          e.getLocalizedMessage()));
    }
    float[] dir = new float[2];
    try {
      dir =
          new float[] {parts.length > 4 ? Float.parseFloat(parts[4]) : 0f,
              parts.length > 5 ? Float.parseFloat(parts[5]) : 0f};
    } catch (NumberFormatException e) {
      throw new LocationFormatException(locationString, String.format("Invalid yaw or pitch: %s",
          e.getLocalizedMessage()));
    }
    return new Location(world, pos[0], pos[1], pos[2], dir[0], dir[1]);
  }

  /**
   * Parse a World from a configuration String.
   * 
   * @param worldString The name or UUID of the World.
   * @return The world, or null if it does not exist.
   */
  public static World parseWorldString(String worldString) {
    if (worldString == null || worldString.isEmpty()) {
      return null;
    }
    try {
      return Bukkit.getServer().getWorld(UUID.fromString(worldString));
    } catch (IllegalArgumentException e) {
      return Bukkit.getServer().getWorld(worldString);
    }
  }

  private NovaGames plugin = null;

  public ConfigHelper(NovaGames plugin) {
    this.plugin = plugin;
  }

  public void reload() {
    plugin.reloadConfig();
  }

}
