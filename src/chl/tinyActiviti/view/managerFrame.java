package chl.tinyActiviti.view;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class managerFrame {
	protected Shell shlManagerView;
	private ArrayList<Item> items;
	private Table table;
	private Text text;
	private Text text_1;
	private TableViewer tableViewer;
	private Combo combo;
	private int currentRow;
	private ArrayList<String> workers;
	private DateTime dateTime_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main() {
		try {
			managerFrame window = new managerFrame();
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
		shlManagerView.open();
		shlManagerView.layout();
		while (!shlManagerView.isDisposed()) {
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
		shlManagerView = new Shell();
		shlManagerView.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		shlManagerView.setSize(450, 332);
		shlManagerView.setText("Tiny Activiti");
		shlManagerView.setLayout(new FormLayout());
		
		Button btnNewButton = new Button(shlManagerView, SWT.NONE);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.top = new FormAttachment(0);
		fd_btnNewButton.left = new FormAttachment(0);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("\u6D41\u7A0B\u6587\u4EF6");
		
		Button btnNewButton_1 = new Button(shlManagerView, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				items.get(currentRow).setassignee(workers.get(combo.getSelectionIndex()));
				items.get(currentRow).setdeadline(new Date(dateTime_1.getYear()-1900,dateTime_1.getMonth(),dateTime_1.getDay()));
				tableViewer.refresh();
			}
		});
		FormData fd_btnNewButton_1 = new FormData();
		fd_btnNewButton_1.bottom = new FormAttachment(100, -10);
		fd_btnNewButton_1.right = new FormAttachment(100, -93);
		btnNewButton_1.setLayoutData(fd_btnNewButton_1);
		btnNewButton_1.setText("\u786E\u8BA4\u6307\u6D3E");
		
		tableViewer = new TableViewer(shlManagerView, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		table.setHeaderVisible(true);
		table.setBackground(SWTResourceManager.getColor(152, 251, 152));
		table.setToolTipText("");
		FormData fd_table = new FormData();
		fd_table.top = new FormAttachment(btnNewButton, 2);
		fd_table.left = new FormAttachment(btnNewButton, 0, SWT.LEFT);
		fd_table.right = new FormAttachment(0, 442);
		table.setLayoutData(fd_table);
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_1 = tableViewerColumn_1.getColumn();
		tblclmnNewColumn_1.setWidth(89);
		tblclmnNewColumn_1.setText("Type");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_2 = tableViewerColumn_2.getColumn();
		tblclmnNewColumn_2.setWidth(91);
		tblclmnNewColumn_2.setText("Name");
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnName = tableViewerColumn_3.getColumn();
		tblclmnName.setWidth(99);
		tblclmnName.setText("Assignee");
		
		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_3 = tableViewerColumn_4.getColumn();
		tblclmnNewColumn_3.setWidth(154);
		tblclmnNewColumn_3.setText("Deadline");
		
		text = new Text(shlManagerView, SWT.BORDER);
		fd_table.bottom = new FormAttachment(text, -21);
		text.setBackground(SWTResourceManager.getColor(152, 251, 152));
		text.setEditable(false);
		FormData fd_text = new FormData();
		fd_text.top = new FormAttachment(btnNewButton, 195);
		fd_text.left = new FormAttachment(0, 10);
		text.setLayoutData(fd_text);
		
		text_1 = new Text(shlManagerView, SWT.BORDER);
		text_1.setBackground(SWTResourceManager.getColor(152, 251, 152));
		text_1.setEditable(false);
		FormData fd_text_1 = new FormData();
		fd_text_1.left = new FormAttachment(text, 23);
		fd_text_1.top = new FormAttachment(text, 0, SWT.TOP);
		text_1.setLayoutData(fd_text_1);
		
		dateTime_1 = new DateTime(shlManagerView, SWT.BORDER);
		dateTime_1.setBackground(SWTResourceManager.getColor(152, 251, 152));
		FormData fd_dateTime_1 = new FormData();
		fd_dateTime_1.right = new FormAttachment(100, -10);
		fd_dateTime_1.top = new FormAttachment(text, 0, SWT.TOP);
		dateTime_1.setLayoutData(fd_dateTime_1);
		
		ComboViewer comboViewer = new ComboViewer(shlManagerView, SWT.NONE);
		combo = comboViewer.getCombo();
		fd_dateTime_1.left = new FormAttachment(combo, 33);
		fd_text_1.right = new FormAttachment(100, -230);
		combo.setBackground(SWTResourceManager.getColor(152, 251, 152));
		combo.setItems(new String[] {"myself"});
		FormData fd_combo = new FormData();
		fd_combo.top = new FormAttachment(text, 0, SWT.TOP);
		fd_combo.left = new FormAttachment(text_1, 25);
		combo.setLayoutData(fd_combo);
        try {
        	workers = new Manager().getWorkers();
        	int num = workers.size();
        	String[] temp = new String[num];
        	for(int i = 0;i < num;i++)
        		temp[i] = workers.get(i);
			combo.setItems(temp);
			
			Button btnNewButton_2 = new Button(shlManagerView, SWT.NONE);
			btnNewButton_2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseUp(MouseEvent e) {
					MessageBox mb = new MessageBox(shlManagerView, SWT.ICON_INFORMATION | SWT.OK);
					mb.setText("部署结果");
                    try {
						if(new Manager().deployProc(items))
						{
							mb.setMessage("部署成功");
							mb.open();
						}
						else
						{
							mb.setMessage("部署失败");
							mb.open();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						mb.setMessage("数据已存在！");
						mb.open();
					}
				}
			});
			btnNewButton_2.setText("\u90E8\u7F72\u6D41\u7A0B");
			FormData fd_btnNewButton_2 = new FormData();
			fd_btnNewButton_2.right = new FormAttachment(100, -10);
			fd_btnNewButton_2.bottom = new FormAttachment(btnNewButton_1, 0, SWT.BOTTOM);
			btnNewButton_2.setLayoutData(fd_btnNewButton_2);
			
			Label lblNewLabel = new Label(shlManagerView, SWT.NONE);
			lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
			lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
			lblNewLabel.setFont(SWTResourceManager.getFont("华文楷体", 10, SWT.NORMAL));
			FormData fd_lblNewLabel = new FormData();
			fd_lblNewLabel.left = new FormAttachment(table, -142);
			fd_lblNewLabel.top = new FormAttachment(btnNewButton, 4, SWT.TOP);
			fd_lblNewLabel.right = new FormAttachment(table, 0, SWT.RIGHT);
			lblNewLabel.setLayoutData(fd_lblNewLabel);
			lblNewLabel.setText("欢迎你，"+Session.loginUser);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
		                  return item.getassignee(); //第三列显示委派人
		           case 3:
		        	   if(item.getdeadline()==null)
		        		   	return null;
		        	   else
		        	   	  	return item.getdeadline().toString(); //第四列显示截止日期
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
		items = Preader.parser("C:\\bpmn.xml");
		tableViewer.setInput(items);
		
		
		tableViewer.addDoubleClickListener(new IDoubleClickListener(){  
	        public void doubleClick(DoubleClickEvent e) {  
	        	currentRow = tableViewer.getTable().getSelectionIndex();
	            TableItem item = tableViewer.getTable().getItem(currentRow);  
	            text.setText(item.getText(0));
	            text_1.setText(item.getText(1));
	            combo.select(currentRow);
	            dateTime_1.setData(items.get(currentRow).getdeadline());	        }  
	    });  
		
		

	}
}
