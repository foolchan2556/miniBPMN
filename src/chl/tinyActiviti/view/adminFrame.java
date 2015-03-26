package chl.tinyActiviti.view;

import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import chl.tinyActiviti.control.*;
import chl.tinyActiviti.model.Item;
import chl.tinyActiviti.model.User;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class adminFrame {

	protected Shell shell;
	private Table table;
	private Text text;
	private Text text_1;
	private Label label;
	private Label label_1;
	private TableViewer tableViewer;
	private Combo combo;
	private ArrayList<User> users;
	private User user;
	private Admin admin;
	private int currentRow;
	private TableItem item;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main() {
		try {
			adminFrame window = new adminFrame();
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
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		tableViewer = new TableViewer(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		table.setHeaderVisible(true);
		table.setBackground(SWTResourceManager.getColor(152, 251, 152));
		table.setBounds(0, 0, 442, 192);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnId = tableViewerColumn.getColumn();
		tblclmnId.setWidth(121);
		tblclmnId.setText("id");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnPassword = tableViewerColumn_1.getColumn();
		tblclmnPassword.setWidth(166);
		tblclmnPassword.setText("password");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn = tableViewerColumn_2.getColumn();
		tblclmnNewColumn.setWidth(147);
		tblclmnNewColumn.setText("power");
		
		class MyContentProvider implements IStructuredContentProvider {
			 
	        public Object[] getElements(Object inputElement) {
	            // TODO Auto-generated method stub
	            return ((List) inputElement).toArray();
	        }
	 
	        public void dispose() {
	            // TODO Auto-generated method stub
	 
	        }
	 
	        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	            // TODO Auto-generated method stub
	 
	        }
	 
	    }
		
		//注册内容提供器
		tableViewer.setContentProvider(new MyContentProvider());
		
		//注册内容显示器
		tableViewer.setLabelProvider(new ITableLabelProvider() {
			public String getColumnText(Object element, int columnIndex) {
			User user = (User)element;
			switch(columnIndex) {
		           case 0:
		                  return user.getid(); //第一列显示用户
		           case 1:
		                  return user.getpassword(); //第二列显示密码
		           case 2:
		                  return user.getpower(); //第三列显示权限
		    }
		    return null;
		}
		    public void addListener(ILabelProviderListener listener) {}
			public void dispose() {}
			public boolean isLabelProperty(Object element, String property) {
				return false;
			}
		        public void removeListener(ILabelProviderListener listener) {}
				@Override
				public Image getColumnImage(Object arg0, int arg1) {
					// TODO Auto-generated method stub
					return null;
				}
		});
		try {
			admin = new Admin();
			users = admin.getUsers();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		tableViewer.setInput(users);
		tableViewer.refresh();
		
		tableViewer.addDoubleClickListener(new IDoubleClickListener(){  
			public void doubleClick(DoubleClickEvent e) {  
				currentRow = tableViewer.getTable().getSelectionIndex();
				item = tableViewer.getTable().getItem(currentRow);
				text.setText(item.getText(0));
	            text_1.setText(item.getText(1));
	            if(item.getText(0).equals("new"))
	            {
	            	text.setText("");
	            	text.setEditable(true);
	            }
	            else
	            	text.setEditable(false);
			}  
		});
		
		text = new Text(shell, SWT.BORDER);
		text.setBackground(SWTResourceManager.getColor(152, 251, 152));
		text.setEditable(false);
		text.setBounds(10, 217, 70, 18);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBackground(SWTResourceManager.getColor(152, 251, 152));
		text_1.setBounds(94, 217, 70, 18);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				MessageBox mb = new MessageBox( shell, SWT.ICON_INFORMATION | SWT.OK);
				mb.setText("操作结果");
				
				if(text.getText().equals("new"))
				{
					mb.setMessage("请设置id");
					return;
				}
				else
				{
				user = new User();
				user.setid(text.getText());
				user.setpassword(text_1.getText());
				if(combo.getSelectionIndex()==0)
					user.setpower("worker");
				else if(combo.getSelectionIndex()==1)
					user.setpower("manager");
				else
					user.setpower("admin");
				
				
				if(item.getText(0).equals("new"))
				{
					try {
						admin.addUser(user);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						mb.setMessage("该用户已存在");
						mb.open();
					}
				} else
					try {
						admin.updateUser(user);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						mb.setMessage("修改失败");
						mb.open();
					}
				}
				try {
					users = admin.getUsers();
					tableViewer.setInput(users);
					tableViewer.refresh();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(277, 215, 72, 22);
		btnNewButton.setText("\u63D0\u4EA4");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				try {
					admin.delUser(text.getText());
					users = admin.getUsers();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tableViewer.setInput(users);
				tableViewer.refresh();
			}
		});
		btnNewButton_1.setBounds(360, 215, 72, 22);
		btnNewButton_1.setText("\u5220\u9664");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel.setBounds(10, 198, 54, 12);
		lblNewLabel.setText("  \u7528   \u6237");
		
		label = new Label(shell, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setBounds(94, 198, 54, 12);
		label.setText("  \u5BC6   \u7801");
		
		label_1 = new Label(shell, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_1.setBounds(177, 198, 54, 12);
		label_1.setText("  \u6743   \u9650");
		
		combo = new Combo(shell, SWT.NONE);
		combo.setBackground(SWTResourceManager.getColor(152, 251, 152));
		combo.setItems(new String[] {"worker", "manager", "admin"});
		combo.setBounds(177, 217, 70, 20);

	}
}
