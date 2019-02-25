//TODO 10 PUT IN YOUR OWN PACKAGE STATEMENT
package com.example.KitchenApp;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button buttonPart1;
    Button buttonPart2AddPicture;
    Button buttonPart2GetPicture;
    ImageView imageViewPart2;
    String TAG = "my firebase app";

//    //TODO 10.1 Get a reference to the root node of the firebase database
//    DatabaseReference mRootDatabaseRef = FirebaseDatabase.getInstance().getReference();
//
//    //TODO 10.6 Get a reference to the root note of the firebase storage
//    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
//
//    DatabaseReference databaseReferencePart1;
//    DatabaseReference databaseReferencePart2;
//    DatabaseReference databaseReferenceSampleNodeValue;
//    ArrayList<String> randomStrings;
//
//    String CHILD_NODE_PART1 = "Part1";
//    String CHILD_NODE_PART2 = "Part2";
//
//    String SAMPLE_NODE  = "Pokemon";
//    TextView textViewSampleNodeValue;

//    // For random images button
//    ArrayList<Integer> images;
//    Button randomImage;
//    LinearLayout linearLayout;
//    LinearLayout.LayoutParams layoutParams;
//    int count = 0;


    // Listview system based on http://www.prandroid.com/2016/03/dynamic-add-and-remove-item-on-listview.html
    ListView listView;
//    EditText editTextView;
    ArrayList<Model> ItemModelList;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
//        editTextView = (EditText) findViewById(R.id.editTextView);
        Button fetchOrders = findViewById(R.id.fetchOrders);
        ItemModelList = new ArrayList<Model>();
        customAdapter = new CustomAdapter(getApplicationContext(), ItemModelList);
        listView.setEmptyView(findViewById(R.id.empty));
        listView.setAdapter(customAdapter);

        fetchOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hardcoded orders for demo purposes
                ItemModelList.add(new Model("Caifan"));
                ItemModelList.add(new Model("Western"));
                ItemModelList.add(new Model("Noodles"));
                ItemModelList.add(new Model("Wanton"));
                ItemModelList.add(new Model("Drinks"));
                customAdapter.notifyDataSetChanged();
            }
        });
    }

//    //@SuppressLint("NewApi")
//    public void addValue(View v) {
//        String name = editTextView.getText().toString();
//        if (name.isEmpty()) {
//            Toast.makeText(getApplicationContext(), "Plz enter Values",
//                    Toast.LENGTH_SHORT).show();
//        } else {
//            Model md = new Model(name);
//            ItemModelList.add(md);
//            customAdapter.notifyDataSetChanged();
//            editTextView.setText("");
//        }
//    }

    class Model {
        String name;
        public Model(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

    class CustomAdapter extends BaseAdapter {
        Context context;
        ArrayList<Model> itemModelList;
        public CustomAdapter(Context context, ArrayList<Model> modelList) {
            this.context = context;
            this.itemModelList = modelList;
        }
        @Override
        public int getCount() {
            return itemModelList.size();
        }
        @Override
        public Object getItem(int position) {
            return itemModelList.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = null;
            if (convertView == null) {
                LayoutInflater mInflater = (LayoutInflater) context
                        .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = mInflater.inflate(R.layout.list_item, null);
                TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
                ImageView imageReject = (ImageView) convertView.findViewById(R.id.imageReject);
                ImageView imageAccept = (ImageView) convertView.findViewById(R.id.imageAccept);
                Model m = itemModelList.get(position);
                tvName.setText(m.getName());
                final String itemName = m.getName();
                // click listener for remove button
                imageReject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, itemName + " Rejected", Toast.LENGTH_SHORT).show();
                        itemModelList.remove(position);
                        notifyDataSetChanged();
                    }
                });
                imageAccept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, itemName + " Accepted", Toast.LENGTH_SHORT).show();
                        itemModelList.remove(position);
                        notifyDataSetChanged();
                    }
                });
            }
            return convertView;
        }
    }
}

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        layoutParams = new LinearLayout.LayoutParams
//                (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//
//        linearLayout = findViewById(R.id.foodOrders);
//        randomImage = findViewById(R.id.addRandomImage);
//        randomImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final ImageView newOrder = new ImageView(MainActivity.this);
//                newOrder.setImageResource(R.drawable.pikachu);
//                linearLayout.addView(newOrder, layoutParams);
//
//                newOrder.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        PopupMenu orderOptions = new PopupMenu(MainActivity.this, newOrder);
//                        orderOptions.getMenuInflater().inflate(
//                                R.menu.order_options_menu, orderOptions.getMenu());
//                        orderOptions.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                            public boolean onMenuItemClick(MenuItem item) {
//                                String message;
//                                if (item.getTitle() == "Confirm Order") {
//                                    message = "Order Confirmed";
//                                } else if (item.getTitle() == "Reject Order") {
//                                    message = "Order Rejected";
//                                } else {
//                                    message = "Unknown menu item title";
//                                }
//                                linearLayout.removeView(newOrder);
//                                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
//                                return true;
//                            }
//                        });
//                    }
//                });
//            }
//        });

        ////////////////////////////////////////////////////////////////////////////////////////////
        //TODO 10.0 Example
//        textViewSampleNodeValue = findViewById(R.id.textViewSampleNodeValue);
//        databaseReferenceSampleNodeValue = mRootDatabaseRef.child(SAMPLE_NODE);
//        databaseReferenceSampleNodeValue.setValue("Psyduck");
//
//        databaseReferenceSampleNodeValue.addListenerForSingleValueEvent(
//                new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        textViewSampleNodeValue.setText((String) dataSnapshot.getValue());
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                }
//        );
//
//        //TODO 10.2 initialize the array of strings
//        randomStrings = new ArrayList<>();
//        randomStrings.add("pikachu");
//        randomStrings.add("snorlax");
//        randomStrings.add("charmander");
//
//        //TODO 10.3 get a reference to the child node
//        databaseReferencePart1 = mRootDatabaseRef.child(CHILD_NODE_PART1);
//
//        //TODO 10.4 Get a reference to the “Add a Random Word” button, set up the OnClickListener and upload a random word to firebase.
//        buttonPart1 = findViewById(R.id.buttonPart1);
//        buttonPart1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Select a random string
//                Random random = new Random();
//                int position = random.nextInt(randomStrings.size());
//                databaseReferencePart1.push().setValue(randomStrings.get(position)
//                );
//            }
//        });
//
//        //TODO 10.8 Build a HashMap object with your data
//        final Map<String, ImageData> imageDataMap = new HashMap<>();
//        imageDataMap.put("Miku", new ImageData("hatsunemiku", "vocaloid"));
//        imageDataMap.put("Pikachu", new ImageData("pikachu", "pokemon"));
//        imageDataMap.put("Totoro",new ImageData("totoro", "studio ghibli"));
//
//        //TODO 10.9 Get reference to the root of the child node part 2
//        databaseReferencePart2 = mRootDatabaseRef.child(CHILD_NODE_PART2);
//
//        //TODO 10.10 Get reference to the Add Pictures button and write code to upload the HashMap data when button is clicked
//        buttonPart2AddPicture = findViewById(R.id.buttonPart2AddPicture);
//        buttonPart2AddPicture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                databaseReferencePart2.setValue(imageDataMap);
//
//                // Complete TODO 10.11 here
//                for( String key: imageDataMap.keySet()){
//                    ImageData imageData = imageDataMap.get(key);
//                    String path = "images/" + imageData.filename + ".jpg";
//                    uploadFileToFirebaseStorage(imageData.filename,path);
//                    databaseReferencePart2.child(key).child("path").setValue(path);
//                }
//            }
//        });
//
//
//        //TODO 10.12 Get a reference to the widgets and write code to download an image randomly when the Get Picture button is clicked
//        buttonPart2GetPicture = findViewById(R.id.buttonPart2GetPicture);
//        imageViewPart2 = findViewById(R.id.imageViewPart2);
//        buttonPart2GetPicture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Get a Random Key
//                ArrayList<String> keys = new ArrayList<>(imageDataMap.keySet());
//                Random r = new Random();
//                int position = r.nextInt(keys.size());
//                final String searchKey = keys.get(position);
//                Log.i(TAG, keys.get(position));
//
//                //Invoke addListenerForSingleValueEvent to download the image
//                databaseReferencePart2.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot){
//                        for (DataSnapshot ds: dataSnapshot.getChildren()){
//                            Log.i(TAG, "key:" + ds.getKey());
//                            if( searchKey.equals( ds.getKey() ) ){
//                                Log.i(TAG, "path:"+ ds.child("path").getValue() );
//                                downloadFromFirebaseStorage(
//                                        (String) ds.child("path").getValue(),
//                                        imageViewPart2);
//                            }
//                        }
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) { }
//                });
//            }
//        });
//
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        Log.i(TAG,"onStart");
//
//        //TODO 10.5 invoke addValueEventListener on databaseReferencePart1
//        //TODO 10.5 get a reference to the LinearLayoutpart1 and dynanmicaly
//        databaseReferencePart1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                LinearLayout linearLayout = findViewById(R.id.linearLayoutPart1);
//                linearLayout.removeAllViews();
//                for(DataSnapshot ds : dataSnapshot.getChildren()){
//                    Log.i(TAG, ds.getKey());
//                    Log.i(TAG, (String) ds.getValue()) ;
//                    TextView textView = new TextView(MainActivity.this);
//                    textView.setText((String) ds.getValue());
//                    linearLayout.addView(textView);
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) { }
//        });
//    }
//
//    //TODO 10.7 Write a static inner class for Part2
//
//    static class ImageData{
//
//        String filename;
//        String description;
//
//        ImageData(String filename, String description){
//            this.filename = filename;
//            this.description = description;
//
//        }
//    }
//
//    void uploadFileToFirebaseStorage(String name, String path){
//
//        int resID = getResources().getIdentifier(name , "drawable", getPackageName());
//
//        Bitmap bitmap = BitmapFactory.decodeResource(
//                MainActivity.this.getResources(),
//                resID);
//
//        Log.i(TAG, "Res ID " + resID);
//        Log.i(TAG, "Bitmap " + bitmap.toString());
//
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//        byte[] data = byteArrayOutputStream.toByteArray();
//
//        StorageReference imageRef = storageReference.child(path);
//
//        UploadTask uploadTask = imageRef.putBytes(data);
//
//        uploadTask.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(MainActivity.this,
//                        "cannot upload",
//                        Toast.LENGTH_LONG).show();
//            }
//        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                Toast.makeText(MainActivity.this,
//                        "upload success",
//                        Toast.LENGTH_LONG).show();
//
//            }
//        });
//
//    }
//
//    void downloadFromFirebaseStorage(String path, final ImageView imageView){
//
//        final StorageReference imageRef = storageReference.child(path);
//
//        final long ONE_MB = 1024*1024;
//        imageRef.getBytes(ONE_MB).addOnSuccessListener(new OnSuccessListener<byte[]>() {
//            @Override
//            public void onSuccess(byte[] bytes) {
//
//                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//                imageView.setImageBitmap(bitmap);
//
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(MainActivity.this,
//                        "cannot download",
//                        Toast.LENGTH_LONG).show();
//            }
//        });
