package cs.naosuke.contactlistapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tvContact;
    ListView lvContact;
    EditText edtSearch;
    Button btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstance();
    }

    private void initInstance() {
        tvContact = (TextView) findViewById(R.id.tv_contact);
        lvContact = (ListView) findViewById(R.id.lv_contact);
        edtSearch = (EditText) findViewById(R.id.edt_search);
        btnSearch = (Button) findViewById(R.id.btn_search);
    }


    @Override
    protected void onResume() {
        super.onResume();

        final DBHelper helper = new DBHelper(this);
        final ArrayList<Contact> contacts = helper.getAllContact();

        MyAdapter adapter = new MyAdapter(this,
                contacts);

        lvContact.setAdapter(adapter);

        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this,
//                        contacts.get(position).getId() + "",
//                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, UpdateContactActivity.class);
                intent.putExtra("id", contacts.get(position).getId());
                intent.putExtra("name", contacts.get(position).getName());
                intent.putExtra("phone", contacts.get(position).getPhone());

                startActivity(intent);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtSearch.getText().toString();
                ArrayList<Contact> contacts = helper.getContactByName(name);
                MyAdapter adapter = new MyAdapter(MainActivity.this,
                        contacts);

                lvContact.setAdapter(adapter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.menu_add :
                intent = new Intent(this, AddContactActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}
