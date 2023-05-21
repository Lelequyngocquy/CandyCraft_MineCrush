import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class CandyGUI{
	Container cn;
	JFrame jf;
	JPanel jp;
	JPanel jp1;
	JLabel jb;
	JButton f5;
	
	public CandyGUI(){
		jf = new JFrame();																//initialize jframe

		jf.setTitle("Minecrush_CandyCraft");
		jf.setSize(1000,700);
		jf.setLocation(500,300);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		ImageIcon imageCover = new ImageIcon("cover.png");  				
		jf.setIconImage(imageCover.getImage()); 							
		jf.setResizable(false);
		
		
		jb = new JLabel();
		Dimension d1 = new Dimension();
		d1.height = 80;
		jb.setPreferredSize(d1);
		jb.setFont(jb.getFont().deriveFont(30.0f));
		
		
		jp1 = new JPanel(new BorderLayout());
		Dimension d2 = new Dimension();
		d2.height = 50;
		jp1.setLayout(null);
		jp1.setPreferredSize(d2);
		
		jp = new JPanel(new GridLayout(Candy.getRows(),Candy.getCols()));

		cn = jf.getContentPane();
		cn.setLayout(new BorderLayout());
		cn.add(jp1, BorderLayout.NORTH);
		cn.add(jb,BorderLayout.SOUTH);
		cn.add(jp);


		f5 = new JButton("Refresh!");
		f5.setBounds(450,15,100,30);
		f5.setFocusable(false);	
		jp1.add(f5,BorderLayout.CENTER);
	}
	
	public void setVisible(){
		jf.setVisible(true);
	}

	
	public void dispose(){
		jf.dispose();
	}



	
	public void panelRemoveAll(){
		jp.removeAll();
	}
	
	public void panelRevalidate(){
		jp.revalidate();
	}

	
	public JPanel getJp() {
		return jp;
	}
	
	public JLabel getJb() {
		return jb;
	}
	
	public JButton getF5(){
		return f5;
	}





}
	