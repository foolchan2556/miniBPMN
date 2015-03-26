package chl.tinyActiviti.view;

import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import chl.tinyActiviti.control.*;
import chl.tinyActiviti.model.Item;
import chl.tinyActiviti.model.Session;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.graphics.Image;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class workerFrame {

	protected Shell shell;
	private Table table;
	private TableViewer tableViewer;
	private Combo combo;
	private Combo combo_1;
	private ArrayList<Item> items;
	private Worker worker = null;
	private int currentRow;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main() {
		try {
			workerFrame window = new workerFrame();
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
		shell.setText("\u4EFB\u52A1\u7BA1\u7406");
		
		combo = new Combo(shell, SWT.NONE);
		combo.setBackground(SWTResourceManager.getColor(152, 251, 152));
		combo.setItems(new String[] {"\u672A\u5230\u671F", "\u5DF2\u5230\u671F", "\u5168\u90E8"});
		combo.setBounds(0, 0, 87, 20);
		
		combo_1 = new Combo(shell, SWT.NONE);
		combo_1.setBackground(SWTResourceManager.getColor(152, 251, 152));
		combo_1.setItems(new String[] {"\u672A\u5C31\u7EEA", "\u5DF2\u5C31\u7EEA", "\u5DF2\u5B8C\u6210", "\u5168\u90E8"});
		combo_1.setBounds(105, 0, 87, 20);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e){
				int overdue, state;
				overdue = combo.getSelectionIndex();
				state = combo_1.getSelectionIndex();
				try {
					items = worker.queryItems(overdue, state);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tableViewer.setInput(items);
				tableViewer.refresh();
			}
		});
		btnNewButton.setBounds(210, 0, 72, 22);
		btnNewButton.setText("\u67E5\u8BE2");
		
		tableViewer = new TableViewer(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		table.setHeaderVisible(true);
		table.setBackground(SWTResourceManager.getColor(152, 251, 152));
		table.setBounds(0, 26, 442, 167);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnType = tableViewerColumn.getColumn();
		tblclmnType.setWidth(80);
		tblclmnType.setText("type");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnName = tableViewerColumn_1.getColumn();
		tblclmnName.setWidth(80);
		tblclmnName.setText("name");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnDeadline = tableViewerColumn_2.getColumn();
		tblclmnDeadline.setWidth(115);
		tblclmnDeadline.setText("deadline");
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnAvailable = tableViewerColumn_3.getColumn();
		tblclmnAvailable.setWidth(87);
		tblclmnAvailable.setText("available");
		
		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnDone = tableViewerColumn_4.getColumn();
		tblclmnDone.setWidth(70);
		tblclmnDone.setText("done");
		
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
			Item item = (Item)element;
			switch(columnIndex) {
				   case 0:
				          return item.gettype(); //第一列显示类别
				   case 1:
				          return item.getname(); //第二列显示名称
				   case 2:
				        if(item.getdeadline()==null)
				        	return null;
				        else
				        	return item.getdeadline().toString(); //第三列显示截止日期
				   case 3:
					   	if(item.getavailable())					//第四列显示是否就绪
					   		return new String("available");
					   	else
					   		return new String("unavailable");
				   case 4:
				       	if(item.getdone())
				       		return new String("done");
				       	else
				       		return new String("todo");
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
			worker = new Worker();
			items = worker.getItems();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		tableViewer.setInput(items);
		tableViewer.refresh();
				
		tableViewer.addDoubleClickListener(new IDoubleClickListener(){  
			public void doubleClick(DoubleClickEvent e) {  
				currentRow = tableViewer.getTable().getSelectionIndex();
			}  
		});  
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e){
				MessageBox mb = new MessageBox( shell, SWT.ICON_INFORMATION | SWT.OK);
				mb.setText("提交结果");
				if(!items.get(currentRow).getavailable())
				{
					mb.setMessage("需要完成前置任务！");
					mb.open();
				}
				else
				{
					items.get(currentRow).setdone(true);
					try {
						worker.setDone(items.get(currentRow).getthisItem());
						int overdue = combo.getSelectionIndex();
						int state = combo_1.getSelectionIndex();
						if(overdue==-1 && state==-1)
							items = worker.getItems();
						else
							items = worker.queryItems(overdue, state);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tableViewer.setInput(items);
					tableViewer.refresh();
					
					mb.setMessage("恭喜你，已完成该任务！");
					mb.open();
				}
			}
		});
		btnNewButton_1.setBounds(360, 234, 72, 22);
		btnNewButton_1.setText("\u4EFB\u52A1\u5B8C\u6210");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblNewLabel.setFont(SWTResourceManager.getFont("华文楷体", 10, SWT.NORMAL));
		lblNewLabel.setBounds(318, 3, 124, 17);
		lblNewLabel.setText("欢迎你，"+Session.loginUser);

	}
}
