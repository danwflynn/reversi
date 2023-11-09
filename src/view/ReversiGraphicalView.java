package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import model.ReadonlyIReversiModel;

public class ReversiGraphicalView extends JFrame implements IGraphicalView {
  private final ReadonlyIReversiModel model;

  private final BoardPanel boardPanel;
  JScrollPane scrollbarPanel;


  public ReversiGraphicalView(ReadonlyIReversiModel model) {
    super();
    this.model = model;
    int windowWidth = 1000;
    int windowHeight = 800;
    this.setSize(windowWidth, windowHeight);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLayout(null);



    this.boardPanel = new BoardPanel(this.model, windowWidth, windowHeight);
    Dimension boardPrefSize = this.boardPanel.getPreferredSize();
    //this.boardPanel.setBounds(50, 50, boardPrefSize.width, boardPrefSize.height);

    int ind = 1;
    for (HexagonTile h : this.boardPanel.getButtons()) {
      this.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          if (h.getHighlightedButton() != null) {
            h.changeSelection();
          }
        }
      });
      this.add(h);
      this.setComponentZOrder(h, ind);
      ind++;
    }

    // ....

    this.scrollbarPanel = new JScrollPane(this.boardPanel
            , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
    );

    this.getContentPane().add(scrollbarPanel);


    this.setComponentZOrder(this.boardPanel, ind);
    this.scrollbarPanel.setPreferredSize(new Dimension(300, 300));
    this.setPreferredSize(new Dimension(1500, 1500));
    this.setVisible(true);
    this.setResizable(false);

  }

  /**
   * Makes the View visible.
   */
  public void makeVisible() {
    this.setVisible(true);
  }
}
