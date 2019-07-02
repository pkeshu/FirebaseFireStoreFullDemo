package com.keshar.firebasedomo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {
     private Button saveInitialDatainFirebaseBtn,getDataBtn;
     private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveInitialDatainFirebaseBtn=findViewById(R.id.save_initial_data);
        getDataBtn=findViewById(R.id.get_data_from_database_btn);
        firebaseFirestore=FirebaseFirestore.getInstance();
        saveInitialDatainFirebaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user=new User("Keshar","Paudel",21,"Kalopul Kathmandu");
                firebaseFirestore.collection("Users").document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "Data is Succesfully Added.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        getDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final List<User> userList=new ArrayList<>();

//                firebaseFirestore.collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//                        if(e!=null){
//                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//
//                        for(DocumentChange documentChange:queryDocumentSnapshots.getDocumentChanges()){
//                            User user=new User(documentChange.getDocument().getData().get("firstName").toString(),
//                                    documentChange.getDocument().getData().get("lastname").toString(),
//                                    Integer.valueOf(documentChange.getDocument().getData().get("age").toString()),
//                                    documentChange.getDocument().getData().get("address").toString()
//                                   );
//
//                            userList.add(user);
//                        }
//                        Toast.makeText(MainActivity.this, userList.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                firebaseFirestore.collection("Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot:task.getResult()){
                                User user=new User(documentSnapshot.getData().get("firstName").toString(),
                                        documentSnapshot.getData().get("lastname").toString(),
                                        Integer.valueOf(documentSnapshot.getData().get("age").toString()),
                                        documentSnapshot.getData().get("address").toString()
                                        );
                                userList.add(user);
                            }
                            Toast.makeText(MainActivity.this, userList.get(0).getFirstName(), Toast.LENGTH_SHORT).show();


                        }
                    }
                });

            }
        });
    }
}
