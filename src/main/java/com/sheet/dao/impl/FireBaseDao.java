package com.sheet.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.internal.Log;
import com.sheet.model.SheetModel;
import java.util.List; 
import java.util.ArrayList; 

public class FireBaseDao {
private static FirebaseApp mfirebaseApp = null;
	
	//Initialise the firebase database
	public static FirebaseApp  initializeFireBase(){
		if( mfirebaseApp != null)
		{
			return mfirebaseApp; 
		}
		FirebaseOptions mOptions=null;
		try {
			mOptions = new FirebaseOptions.Builder().setDatabaseUrl("https://spreadsheet-be529.firebaseio.com/").setServiceAccount(new FileInputStream("/home/bridgeit/spreadsheet-694f67dd5d86.json")).build();
			mfirebaseApp = FirebaseApp.initializeApp(mOptions);
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		return mfirebaseApp;
	}
	
	//save the data to firebase database
	public static void saveDataToFireBase(Map<String, String> map)
	
	{	
		//intialising firebase
		FirebaseApp firebaseApp = initializeFireBase();
		// As an admin, the app has access to read and write all data, regardless of Security Rules
		
		DatabaseReference lRef = FirebaseDatabase.getInstance( firebaseApp ).getReference("URL");
	//	DatabaseReference lUsersRef = lRef.child(mKey);
		lRef.setValue(map);
		
		lRef.addListenerForSingleValueEvent(new ValueEventListener() {
		    @Override
		    public void onDataChange(DataSnapshot pDataSnapshot) {
		        Object document = pDataSnapshot.getValue();
		        System.out.println(document);
		    }
			@Override
			public void onCancelled(DatabaseError arg0) {
				System.err.println("Listener was cancelled");
				
			}
		});
		
	}
	
	//fetch the data from firebase database
	public static Map<String, String> fetchDataFromFireBase(String pKey)
	{	
		//intialising firebase
		FirebaseApp firebaseApp = initializeFireBase();
		
		// As an admin, the app has access to read and write all data, regardless of Security Rules
		DatabaseReference lRef = FirebaseDatabase.getInstance(firebaseApp).getReference("URL");
		DatabaseReference lUsersRef = lRef.child(pKey);
		
		/*ValueEventListener postListener = new ValueEventListener() {
		    @Override
		   public void onDataChange(DataSnapshot pDataSnapshot) {
		    	for (DataSnapshot child : pDataSnapshot.getChildren()) {
	                 for (DataSnapshot mData : child.getChildren()) {
	                	 System.out.println(mData);
	                	 Map<String,String> map=(Map<String,String>) mData;
	                	 map.get("FacaeBookURL");
	                    }
	                }
		    }
		    @Override
		    public void onCancelled(DatabaseError pDatabaseError) {
		        System.out.println("The read failed: " + pDatabaseError.getCode());
		    }
		};*/
		/*lRef.addValueEventListener(postListener);*/
		
		return null;
		
	}
	
	 
	
}