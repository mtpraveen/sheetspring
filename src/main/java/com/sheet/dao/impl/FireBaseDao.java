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
import java.util.HashMap; 

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
			mOptions = new FirebaseOptions.Builder().setDatabaseUrl("https://sheet-f131b.firebaseio.com/").setServiceAccount(new FileInputStream("/home/bridgeit/sheet-35461dfea2bd.json")).build();
			mfirebaseApp = FirebaseApp.initializeApp(mOptions,"mfirebaseApp");
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		return mfirebaseApp;
	}
	
	//save the data to firebase database
	public static void saveDataToFireBase(String mKey, Map<String, String> map)
	
	{	
		//intialising firebase
		FirebaseApp firebaseApp=initializeFireBase();
		// As an admin, the app has access to read and write all data, regardless of Security Rules
		
		DatabaseReference lRef = FirebaseDatabase.getInstance(firebaseApp).getReference("Engineers");
		DatabaseReference lUsersRef = lRef.child(mKey);
		lUsersRef.setValue(map);
		
		lUsersRef.addListenerForSingleValueEvent(new ValueEventListener() {
		    @Override
		    public void onDataChange(DataSnapshot pDataSnapshot) {
		        Object document = pDataSnapshot.getValue();
		//        System.out.println(document);
		    }
			@Override
			public void onCancelled(DatabaseError arg0) {
				System.err.println("Listener was cancelled");
				
			}
		});
		
	}
	
	//fetch the data from firebase database
	public static Map<String, String> fetchDataFromFireBase(String mKey)
	{	
		//intialising firebase
		FirebaseApp firebaseApp = initializeFireBase();
		
		// As an admin, the app has access to read and write all data, regardless of Security Rules
		DatabaseReference lRef = FirebaseDatabase.getInstance(firebaseApp).getReference("Engineers");
		DatabaseReference lUsersRef = lRef.child(mKey);
		
		ValueEventListener postListener = new ValueEventListener() {
		    @Override
		   public void onDataChange(DataSnapshot pDataSnapshot) {
		    	for (DataSnapshot child : pDataSnapshot.getChildren()) {
	                 for (DataSnapshot mData : child.getChildren()) {
	    //            	 System.out.println(mData);
	                	 
	                    }
	                }
		    }
		    @Override
		    public void onCancelled(DatabaseError pDatabaseError) {
		        System.out.println("The read failed: " + pDatabaseError.getCode());
		    }
		};
		lRef.addValueEventListener(postListener);
		
		return null;
		
	}
	
	 
	
}