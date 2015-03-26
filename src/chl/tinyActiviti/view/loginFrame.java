package chl.tinyActiviti.view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import chl.tinyActiviti.control.*;
import chl.tinyActiviti.model.*;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.List;
import org.eclipse.wb.swt.SWTResourceManager;

public class loginFrame {

	protected Shell shlTinyActiviti;
	private Text text;
	private Text text_1;
	private User user = new User();
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			loginFrame window = new loginFrame();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlTinyActiviti.open();
		shlTinyActiviti.layout();
		while (!shlTinyActiviti.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlTinyActiviti = new Shell();
		shlTinyActiviti.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		shlTinyActiviti.setSize(450, 300);
		shlTinyActiviti.setText("Tiny Activiti");
		
		Label lblNewLabel = new Label(shlTinyActiviti, SWT.SHADOW_IN);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 14, SWT.BOLD));
		lblNewLabel.setBounds(120, 139, 64, 25);
		lblNewLabel.setText("\u7528\u6237\u540D");
		
		Label lblNewLabel_1 = new Label(shlTinyActiviti, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 14, SWT.BOLD));
		lblNewLabel_1.setBounds(120, 173, 64, 25);
		lblNewLabel_1.setText("\u5BC6    \u7801");
		
		text = new Text(shlTinyActiviti, SWT.BORDER);
		text.setForeground(SWTResourceManager.getColor(0, 0, 0));
		text.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 14, SWT.NORMAL));
		text.setBounds(225, 139, 125, 25);
		
		text_1 = new Text(shlTinyActiviti, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 14, SWT.NORMAL));
		text_1.setBounds(225, 173, 125, 25);
		
		Button btnNewButton = new Button(shlTinyActiviti, SWT.NONE);
		btnNewButton.setForeground(SWTResourceManager.getColor(0, 255, 127));
		btnNewButton.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 14, SWT.BOLD));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				user.setid(text.getText());
				user.setpassword(text_1.getText());
		
				try
				{
					if(Login.check(user))
					{
						if(Session.loginPower.equals("worker"))
						{
							workerFrame.main();
							shlTinyActiviti.close();
						}
						else if(Session.loginPower.equals("manager"))
						{
							managerFrame.main();
							shlTinyActiviti.close();
						}
						else if(Session.loginPower.equals("admin"))
						{
							adminFrame.main();
							shlTinyActiviti.close();
						}
					}
					
				}
				catch(Exception e1){} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnNewButton.setBounds(312, 209, 83, 35);
		btnNewButton.setText("\u767B\u5F55");
		
		Label lblNewLabel_2 = new Label(shlTinyActiviti, SWT.NONE);
		lblNewLabel_2.setImage(SWTResourceManager.getImage("C:\\Documents and Settings\\Administrator\\\u684C\u9762\\logo.jpg"));
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lblNewLabel_2.setBounds(0, 0, 442, 114);

	}
}
