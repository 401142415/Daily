package com.example.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.order.AllPayActivity.MyTask;
import com.example.order.AllPayActivity.ProductAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class All_OrderActivity extends Activity {

	private DBUtil dbUtil;
	private ListView listView;
	private ProductAdapter adapter;
	private int Num;
	private List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_suggestion);
		

		dbUtil = new DBUtil();
		listView = (ListView)this.findViewById(R.id.listView1);
				
		adapter = new ProductAdapter();
		

		 final Dialog alertDialog = new AlertDialog.Builder(this)
		.setMessage( "该操作无法撤销，是否进行？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() { 
                  
                 @Override 
                 public void onClick(DialogInterface dialog, int which) { 
                     // TODO Auto-generated method stub  
              	   new Thread(){  
              		 public void run()  
 					   {
 						dbUtil = new DBUtil();
 						dbUtil.deleteAll_Order();
 						new MyTask().execute();
 					   }  
              		 
              		   
  					}.start(); 
             // 	   Toast.makeText(CookActivity.this, "", 1).show();
              	
                 } 
             }).
             setNegativeButton("取消", new DialogInterface.OnClickListener() {
          	     
          	     @Override
          	     public void onClick(DialogInterface dialog, int which) {
          	      // TODO Auto-generated method stub
          	    	 
          	  //  	 Toast.makeText(CookActivity.this, "结账未完成", 1).show();
          	     }
          	    })
          	    .create();
		 
//		  final Dialog alertDialog1 = new AlertDialog.Builder(this)
//		.setMessage( "删除该意见？")
//				.setPositiveButton("确定", new DialogInterface.OnClickListener() { 
//                  
//                 @Override 
//                 public void onClick(DialogInterface dialog, int which) { 
//                     // TODO Auto-generated method stub  
//              	   new Thread(){  
//              		 public void run()  
// 					   {
//              			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
//
//						list = dbUtil.selectSuggestion(); 
//						
//						dbUtil.delete_A_Suggestion(list.get(Num).get("S_ID").toString().trim());
//						
//						new MyTask().execute();
// 					   }  
//              		 
//              		   
//  					}.start(); 
//             // 	   Toast.makeText(CookActivity.this, "", 1).show();
//              	
//                 } 
//             }).
//             setNegativeButton("取消", new DialogInterface.OnClickListener() {
//          	     
//          	     @Override
//          	     public void onClick(DialogInterface dialog, int which) {
//          	      // TODO Auto-generated method stub
//          	    	 
//          	  //  	 Toast.makeText(CookActivity.this, "结账未完成", 1).show();
//          	     }
//          	    })
//          	    .create();
//		 
		 
		new MyTask().execute();
		
		
//		listView.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				// TODO 自动生成的方法存根
//				
//				Num = position;
//				alertDialog1.show();
//					
//				
//			}
//			
//
//	    });	
		
		
		
		Button button = (Button)findViewById(R.id.button1);
		
		 button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				
				alertDialog.show();	
				}
		});
		
		
	}

	
	class MyTask extends AsyncTask<String, Void, List<HashMap<String, String>>>{

		@Override
		protected List<HashMap<String, String>> doInBackground(String... params) {
			// TODO 自动生成的方法存根
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

			list = dbUtil.selectO_Pay(); 
		
			
			return list;
		}
		
	
		
		@Override
		protected void onPostExecute(List<HashMap<String, String>> result) {
			// TODO 自动生成的方法存根
			super.onPostExecute(result);
			adapter.setDate(result);
			listView.setAdapter(adapter);
			
			adapter.notifyDataSetChanged();
			
		}
		
	}
	
	
	
	public class ProductAdapter extends BaseAdapter{

		
		List<HashMap<String, String>> list;
				
		public void setDate(List<HashMap<String, String>> list){
			
			this.list = list;
			
		}
		
		public List<HashMap<String, String>> getdate() {
			
			return list;
			
		}
		@Override
		public int getCount() {
			// TODO 自动生成的方法存根
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO 自动生成的方法存根
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO 自动生成的方法存根
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO 自动生成的方法存根
			
			View view = null;
			if(convertView == null){
				view = LayoutInflater.from(All_OrderActivity.this).inflate(R.layout.recipelistview,null);				
			}else {
				view = convertView;
			}
			
		//	Toast.makeText(RecipeActivity.this, list.get(position).get("R_Name").toString(), 1).show();
			TextView textview1 = (TextView)view.findViewById(R.id.textView4);
			textview1.setText(list.get(position).get("User_Name").toString());
			
			TextView textview2 = (TextView)view.findViewById(R.id.textView5);
			textview2.setText(list.get(position).get("R_Name").toString());
			
			TextView textview3 = (TextView)view.findViewById(R.id.textView1);
			textview3.setText(list.get(position).get("R_Price").toString());
			
			TextView textview6 = (TextView)view.findViewById(R.id.textView6);
			textview6.setText(list.get(position).get("Order_Time").toString());
			textview6.setTextColor(0xff32CD32);
			
			TextView textview7 = (TextView)view.findViewById(R.id.textView7);
			textview7.setText(list.get(position).get("Cook_Time").toString());
			textview7.setTextColor(0xffFFB90F);
			
			TextView textview8 = (TextView)view.findViewById(R.id.textView8);
			textview8.setText(list.get(position).get("Sent_Time").toString());
			textview8.setTextColor(android.graphics.Color.BLACK);
			
			return view;
		}
		
		
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.all__order, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
