/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.drivers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

/**
 * Add your docs here.
 */
public class LatchedEventListener {
  private static List<LatchedEventListener> listeners = new ArrayList<>();

  public static void listenAll() {
    for(LatchedEventListener lel : listeners)
      lel.listen();
  }

  private final BooleanSupplier event;
  private final Runnable action;

  private boolean latch;

  public LatchedEventListener(BooleanSupplier event, Runnable action) {
    this.event = event;
    this.action = action;
    listeners.add(this);
  }

  public void listen() {
    if(event.getAsBoolean()) {
      if(!latch) {
        action.run();
        latch = true;
      }
    } else {
      latch = false;
    }
  }
}
