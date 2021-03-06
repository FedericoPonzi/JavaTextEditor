/*
 * Simple Java Text Editor - It's really worth it.
 * @version: 0.2
 * @author: Federico Ponzi
 * With help of:
 * http://docs.oracle.com/javase/tutorial/uiswing/components/menu.html
 * http://lgiambr.math.unipa.it/lezioniLP10_11/GUI.pdf
 * http://google.it
 * ToDo:
 * On saving, it' s like it copies the text twice.
 * On saving, \n dosen't work (on Windows's Systems i think).
 * Do the menu's routin (Open routin left).
 * Adding a simple Help page on menu
 * Adding a Status Bar (with line's count, path of the file actually open and time of last save)
 * Add the "Auto start a new line" function to edit menu.
 * Adding check for empty textArea before saving
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

/**
 * @author Federico Ponzi
 * @version 0.02
 */

public class TextEditor
	{
		static JFrame frame; // The Frame
		JScrollPane scrollPane; // Scrolling
		
		private String path; // Path of the file. - it should be generated by
								// time if empty.
		JTextArea textArea; // Main text area
		int charCounter = 0; // Counter of the characters in the textArea
		
		/**
		 * The costructor
		 */
		public TextEditor()
			{
			}
		
		/**
		 * @return JMenuBar
		 */
		public JMenuBar createJMenuBar()// Creating the menu bar.
			{
				// Creates a menubar for a JFrame
				JMenuBar menuBar = new JMenuBar();
				
				// Add the menubar to the frame
				// setJMenuBar(menuBar);
				
				// Define and add two drop down menu to the menubar
				JMenu fileMenu = new JMenu("File");
				JMenu editMenu = new JMenu("Edit");
				menuBar.add(fileMenu);
				menuBar.add(editMenu);
				
				// Create and add simple menu item to one of the drop down menu
				JMenuItem newAction = new JMenuItem("New");
				newAction.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed( ActionEvent arg0)
							{
								// Open & Read Function.
							}
					});
				// Creating Menu Item: Open
				JMenuItem openAction = new JMenuItem("Open");
				openAction.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed( ActionEvent arg0)
							{
								
								String s = (String) JOptionPane
										.showInputDialog(frame,
												"Inserisci la path:",
												"Customized Dialog",
												JOptionPane.PLAIN_MESSAGE,
												null, null,
												"Insert\\Complete\\Path\\To\\File.txt");
								
								if ((s != null) && (s.length() > 0)) path = s; // If
																				// something
																				// inserted,
																				// set
																				// it
																				// as
																				// path.
							}
						
					});
				
				// Creating Menu Item: Save
				JMenuItem saveAction = new JMenuItem("Save");
				saveAction.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed( ActionEvent arg0)
							{
								try
									{
										while (path == null)
											{
												String s = (String) JOptionPane
														.showInputDialog(
																frame,
																"Inserisci la path:",
																"Customized Dialog",
																JOptionPane.PLAIN_MESSAGE,
																null, null,
																"Insert\\Complete\\Path\\To\\File.txt");
												
												if ((s != null)
														&& (s.length() > 0))
													path = s; // If something
																// inserted, set
																// it as path.
												else if (s == null)
													{
														JOptionPane
																.showMessageDialog(
																		frame,
																		"File non salvato",
																		"Attenzione",
																		1);
														return;
														
													}
											}
										// Set the path of the file.
										
										if (path != null)
											{
												// Simply Save the File.
												FileWriter fstream = new FileWriter(
														path);
												BufferedWriter out = new BufferedWriter(
														fstream);
												out.write(textArea.getText()); // Write
																				// the
																				// input
																				// Text
																				// of
																				// textArea
												String text = textArea
														.getText();
												int totalLines = textArea
														.getLineCount();
												for (int i = 0; i < totalLines; i++) // Create
																						// for
																						// statment
																						// to
																						// fix
																						// \n
																						// issue
													{
														int start = textArea
																.getLineStartOffset(i);
														int end = textArea
																.getLineEndOffset(i);
														String line = text
																.substring(
																		start,
																		end);
														out.write(line + "\n"); // Thinks
																				// this
																				// will
																				// work
																				// only
																				// on
																				// Linux
																				// Systems...
													}
												out.close(); // Close the Stream
												JOptionPane
														.showMessageDialog(
																frame,
																"Text Saved successfully.");
												
											}
									}
								catch (Exception e)
									{
										// Catch exception if any
										System.err
												.println("Error saving text: "
														+ e.getMessage());
									}
							}
					}
				
				);
				// Creating Menu item: Exit
				JMenuItem exitAction = new JMenuItem("Exit");
				exitAction.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed( ActionEvent arg0)
							{
								System.exit(0); // Set Exit Button
							}
					});
				// Creating Menu Item: Cut
				JMenuItem cutAction = new JMenuItem("Cut");
				cutAction.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed( ActionEvent arg0)
							{
								textArea.cut();
								// Cut Function.
							}
					});
				// Menu Item: Copy
				JMenuItem copyAction = new JMenuItem("Copy");
				copyAction.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed( ActionEvent arg0)
							{
								textArea.copy();
							}
					});
				
				// Menu Item: Paste
				JMenuItem pasteAction = new JMenuItem("Paste");
				pasteAction.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed( ActionEvent arg0)
							{
								textArea.paste();
							}
					});
				// Menu Item: Print
				JMenuItem printAction = new JMenuItem("Print");
				printAction.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed( ActionEvent arg0)
							{
								// Print routine
								try
									{
										textArea.print();
									}
								catch (Exception e)
									{
										System.err.println("Error:" + e);
									}
							}
					});
				// Adding the menu element together
				// Adding FileMenu elements:
				fileMenu.add(newAction);
				fileMenu.add(openAction);
				fileMenu.add(saveAction);
				fileMenu.add(printAction);
				fileMenu.addSeparator();
				fileMenu.add(exitAction);
				// Adding editMenu entries:
				editMenu.add(cutAction);
				editMenu.add(copyAction);
				editMenu.add(pasteAction);
				return menuBar;
			}
		
		/**
		 * @return Container
		 */
		public Container createContentPane()
			{
				// Creating the Scrolled TextArea:
				JPanel contentPane = new JPanel(new BorderLayout());
				contentPane.setOpaque(true);
				textArea = new JTextArea(5, 30); // The textArea
				scrollPane = new JScrollPane(textArea); // The scroller
				contentPane.add(scrollPane, BorderLayout.CENTER); // Adding the
																	// croller
				return contentPane; // Return the panel.
			}
		
		// The statusBar
		private JPanel createStatusPanel()
			{
				JPanel statusPanel = new JPanel();
				statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
				statusPanel
						.setPreferredSize(new Dimension(frame.getWidth(), 16));
				statusPanel.setLayout(new BoxLayout(statusPanel,
						BoxLayout.X_AXIS));
				// Creo il label
				
				final JLabel statusLabel = new JLabel();
				statusLabel.setText("Characters: 0");
				textArea.addKeyListener(new KeyAdapter()
					{
						@Override
						public void keyTyped( KeyEvent e)
							{
								statusLabel.setText("Characters: "
										+ textArea.getText().length());
								
							};
					});
				
				statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
				statusPanel.add(statusLabel);
				
				return statusPanel;
			}
		
		private static void loadGUI()
			{
				// Create and setup the GUI.
				frame = new JFrame(
						"Simple Java Text Editor. It's really worth it."); // Creating
																			// JFrame
																			// with
																			// title
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Setting
																		// close
																		// operation
				
				// Create and setup the content pane.
				TextEditor textEd = new TextEditor(); // Creating new textEditor
														// object
				frame.setJMenuBar(textEd.createJMenuBar()); // Loading Menu
				frame.setContentPane(textEd.createContentPane()); // Loading
																	// Textarea
				frame.add(textEd.createStatusPanel(), BorderLayout.SOUTH); // Loading
																			// status
																			// bar
				
				// Display the window.
				Toolkit tk = Toolkit.getDefaultToolkit();
				Dimension screen = tk.getScreenSize(); // Getting the screensize
				
				frame.setSize(screen.width / 2, screen.height / 2); // setting
																	// the
																	// window's
																	// size.
				frame.setVisible(true); // setting frame to Visible
			}
		
		@SuppressWarnings("javadoc")
		public static void main( String[] args)
			{
				javax.swing.SwingUtilities.invokeLater(new Runnable()
					{
						@Override
						public void run()
							{
								loadGUI();
							}
					});
			}
		
	}