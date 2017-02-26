package cs.naosuke.contactlistapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContactActivity extends AppCompatActivity {
    EditText edtName, edtPhone;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        initInstance();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper helper = new DBHelper(AddContactActivity.this);
                Contact contact = new Contact();
                contact.setName(edtName.getText().toString());
                contact.setPhone(edtPhone.getText().toString());

                helper.addContact(contact);

                finish();
            }
        });
    }

    private void initInstance() {
        edtName = (EditText) findViewById(R.id.edt_name);
        edtPhone = (EditText) findViewById(R.id.edt_phone);
        btnAdd = (Button) findViewById(R.id.btn_add);
    }
}
