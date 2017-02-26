package cs.naosuke.contactlistapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateContactActivity extends AppCompatActivity {

    EditText edtId, edtName, edtPhone;
    Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        initInstance();

        Intent intent = getIntent();
        edtId.setText(String.valueOf(intent.getIntExtra("id", 0)));
        edtName.setText(intent.getStringExtra("name"));
        edtPhone.setText(intent.getStringExtra("phone"));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(edtId.getText().toString());
                String name = edtName.getText().toString();
                String phone = edtPhone.getText().toString();

                Contact contact = new Contact();
                contact.setId(id);
                contact.setName(name);
                contact.setPhone(phone);

                DBHelper helper = new DBHelper(UpdateContactActivity.this);

                helper.updateContact(contact);


                finish();
            }
        });
    }

    private void initInstance() {
        edtId = (EditText) findViewById(R.id.edt_update_id);
        edtName = (EditText) findViewById(R.id.edt_update_name);
        edtPhone = (EditText) findViewById(R.id.edt_update_phone);
        btnUpdate = (Button) findViewById(R.id.btn_update);
    }
}
