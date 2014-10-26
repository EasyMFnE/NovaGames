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
 * Exception thrown when a MinigameComponent cannot be found for a specified class name.
 */
public class ComponentNotFoundException extends Exception {

  private static final long serialVersionUID = -97761168561034002L;

  private String componentName;

  /**
   * @param componentName The name of the unfound component.
   */
  public ComponentNotFoundException(String componentName) {
    this.componentName = componentName;
  }

  /**
   * @return The name of the unfound component.
   */
  public String getComponentName() {
    return componentName;
  }

}
