package com.krloxz.auctionsniper;

import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import com.objogate.wl.swing.probe.ValueMatcherProbe;

/**
 * Tests {@link MainWindow} in integration with the Swing framework.
 *
 * @author Carlos Gomez
 *
 */
class MainWindowIntTest {

  private final SnipersTableModel tableModel = new SnipersTableModel();
  private final MainWindow mainWindow = new MainWindow(this.tableModel);
  private final AuctionSniperDriver driver = new AuctionSniperDriver(100);

  @Test
  void makesUserRequestWhenJoinButtonClicked() {
    final ValueMatcherProbe<String> buttonProbe =
        new ValueMatcherProbe<>(is("item-123"), "join request");
    this.mainWindow.addUserRequestListener(itemId -> buttonProbe.setReceivedValue(itemId));
    this.driver.startBiddingFor("item-123");
    this.driver.check(buttonProbe);
  }

}
