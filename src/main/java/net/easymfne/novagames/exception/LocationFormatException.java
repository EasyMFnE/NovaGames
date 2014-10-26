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
package net.easymfne.novagames.exception;

/**
 * Exception thrown when a Location configuration String is not in a valid format.
 */
public class LocationFormatException extends Exception {

  private static final long serialVersionUID = 5605040644743442442L;

  private String invalidLocationString;
  private String reason;

  /**
   * @param invalidLocationString The Location configuration String of invalid format.
   */
  public LocationFormatException(String invalidLocationString, String reason) {
    this.invalidLocationString = invalidLocationString;
    this.reason = reason;
  }

  /**
   * @return The Location configuration String of invalid format.
   */
  public String getInvalidLocationString() {
    return invalidLocationString;
  }

  /**
   * @return The reason given for the LocationFormatException.
   */
  public String getReason() {
    return reason;
  }

}
