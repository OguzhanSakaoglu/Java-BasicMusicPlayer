package Pack1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MusicPlayer extends JPanel implements ActionListener
{
	JFrame window = new JFrame("Interface");
	JLabel info = new JLabel("Music Player");
	JButton addButton = new JButton("Add Music");
	JButton playButton = new JButton("Play");
	JButton stopButton = new JButton("Stop");
	JButton pauseButton = new JButton("Pause");
	JButton previousButton = new JButton("Previous");
	JButton nextButton = new JButton("Next");
	JComboBox list = new JComboBox();
	JFileChooser Chooser = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter("WAV Sound","wav");
	int returnValue;
	ArrayList<String> MusicLt = new ArrayList<String>();
	int index = 0;
	File selectedFile;
	String SelectedFile;
	File sound;
	AudioInputStream AIS;
	Clip clip;
	
	MusicPlayer()
	{
		 
		this.setBackground(Color.WHITE);
		window.add(this);
		
		//BUTTONS
		addButton.addActionListener(this);
		playButton.addActionListener(this);
		stopButton.addActionListener(this);
		
		//BUTTONS AND OTHER APPEARANCES
		info.setFont(new Font("",Font.ITALIC,20));
		window.add(info,BorderLayout.PAGE_END);
		
		window.add(addButton,BorderLayout.LINE_START);
		addButton.setBackground(Color.black);
		addButton.setForeground(Color.RED);
		addButton.setFont(new Font("",Font.ITALIC,30));
		
		window.add(playButton,BorderLayout.CENTER);
		playButton.setBackground(Color.black);
		playButton.setForeground(Color.YELLOW);
		playButton.setFont(new Font("",Font.ITALIC,30));
		
		window.add(stopButton,BorderLayout.LINE_END);
		stopButton.setBackground(Color.black);
		stopButton.setForeground(Color.GREEN);
		stopButton.setFont(new Font("",Font.ITALIC,30));
		
		window.add(list,BorderLayout.PAGE_START);
		list.setBackground(Color.black);
		list.setForeground(Color.CYAN);
		list.setFont(new Font("",Font.ITALIC,20));
		
		Chooser.setFileFilter(filter);
		
		//WINDOW APPEARANCES
		window.setSize(800,400);
		window.setLocation(100,100);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		if(ae.getSource() == addButton)
		{
			returnValue = Chooser.showOpenDialog(window);
			if(returnValue==Chooser.APPROVE_OPTION)
			{
				selectedFile = Chooser.getSelectedFile();
				MusicLt.add(selectedFile.toString());
				list.addItem(index+1 + "-" + selectedFile.getName());
				index++;
			}
		}	
			
			else if(ae.getSource()==playButton)
			{
				try
				{
					if(list.getSelectedIndex()!=-1)
					{
						sound = new File(MusicLt.get(list.getSelectedIndex()));
						AIS = AudioSystem.getAudioInputStream(sound);
						clip = AudioSystem.getClip();
						clip.open(AIS);
						clip.start();
					}
				}
				catch (NullPointerException e) 
				{
					e.printStackTrace();
				}
				
				catch (UnsupportedAudioFileException e) 
				{
					e.printStackTrace();
				}
				
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				
				catch (LineUnavailableException e) 
				{
					e.printStackTrace();
				}
			}
			else if (ae.getSource()==stopButton) 
			{
			clip.stop();
			}	
		}
	}
