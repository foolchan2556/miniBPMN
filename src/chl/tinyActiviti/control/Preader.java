package chl.tinyActiviti.control;

import chl.tinyActiviti.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Preader {
	public static void main(){
		parser("C:\\bpmn.xml");
	}
	public static ArrayList<Item> parser (String filename) {
		ArrayList<Item> item = new ArrayList<Item>();	
		// Open the file that is the first 
		// command line parameter
		FileInputStream fstream;
		FileInputStream f2;
		try {
			fstream = new FileInputStream(filename);
			f2 = new FileInputStream(filename);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			DataInputStream in2 = new DataInputStream(f2);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
			String strLine;
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
			  //Add the startEvent
			  int index = strLine.indexOf("<startEvent");
			  if(index!=-1){
				  int index1 = strLine.indexOf("id=")+4;
				  int index2 = strLine.indexOf('"',index1);
				  String id = strLine.substring(index1, index2);
				  Item startEvent = new Item();
				  startEvent.setthisItem(id);
				  startEvent.settype("startEvent");
				  item.add(startEvent);
			  }
			  //Find the sequenceFlow line and get 2 references
			  index = strLine.indexOf("<sequenceFlow");
			  if(index!=-1){
				  int index1 = strLine.indexOf("sourceRef");
				  int index2 = strLine.indexOf('"',index1)+1;
				  Item curitem = new Item();
				  Iterator<Item> it = null;
				  boolean dup;
				  index1 = strLine.indexOf('"',index2);
				  String sourceRef = strLine.substring(index2, index1);
				  index1 = strLine.indexOf("targetRef");
				  index2 = strLine.indexOf('"',index1)+1;
				  index1 = strLine.indexOf('"',index2);
				  String targetRef = strLine.substring(index2, index1);
				  dup = false;
				  it = item.iterator();
				  while(it.hasNext())
				  {
					  curitem = it.next();
					  if(curitem.getrefItem().contains(targetRef))
					  {
						  curitem.setrefItem(sourceRef);
						  dup = true;
					  }
				  }
				  if(dup == false)
				  {
					  Item event = new Item();
					  event.setthisItem(targetRef);
					  event.setrefItem(sourceRef);
					  item.add(event);
				  }
			  }
			}
			while ((strLine = br2.readLine()) != null)   {
				int index1 = strLine.indexOf("id=");
				int index2 = strLine.indexOf("sequenceFlow");
				int index3 = strLine.indexOf("process"); 
				int index4 = strLine.indexOf("bpmndi");
				if(index1!=-1&&index2==-1&&index3==-1&&index4==-1){
					String itemName = null;
					String itemType = null;
					index1 = strLine.indexOf('<')+1;
					index2 = strLine.indexOf(' ',index1);
					itemType = strLine.substring(index1, index2);
					if(strLine.indexOf("name=")!=-1)
					{
						index3 = strLine.indexOf("name=")+6;
						index4 = strLine.indexOf('"',index3);
						itemName = strLine.substring(index3, index4);
					}
					
					for(Item thisitem: item){
						index1 = strLine.indexOf(thisitem.getthisItem());
						index3 = strLine.indexOf(thisitem.getthisItem());
						if(index1!=-1 && index3 != -1)
						{
							thisitem.settype(itemType);
							thisitem.setname(itemName);				
						}
					}
				}
			}
			for(Item thisitem: item){
				System.out.println(thisitem.getthisItem()+" ref:"+thisitem.getrefItem().toString()+" type:"+thisitem.gettype()+" name:"+thisitem.getname());
			}
			//Close the input stream
			in.close();
			in2.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return item;
	}
}
