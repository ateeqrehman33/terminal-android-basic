package attitude.designs.terminaldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {


    Button commandRun;
    TextView Output;
    EditText command_container;
    boolean buttonFalg=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        commandRun=(Button)findViewById(R.id.button);
        command_container=(EditText)findViewById(R.id.Command_container);
        Output=(TextView)findViewById(R.id.textView2);
        Output.setMovementMethod(new ScrollingMovementMethod());
    }


    public void commandRunner(View v){
        if(buttonFalg){
            if(getRuntime()){
                buttonFalg=!buttonFalg;
                commandRun.setText("Clear");
            }
        }
        else{
            Output.setText(null);
            buttonFalg=!buttonFalg;
            commandRun.setText("Run");
        }
    }
    public boolean getRuntime(){
        if(command_container.getText().toString()==null){
            Toast.makeText(this, "Enter a command", Toast.LENGTH_SHORT).show();
            return false;
        }
        try{
            java.lang.Process p=Runtime.getRuntime().exec(command_container.getText().toString());
            BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream()));
            String Process_ip;
            StringBuffer b=new StringBuffer();
            while((Process_ip=reader.readLine())!=null){
                b.append("\n"+Process_ip);
            }
            reader.close();
            Output.setText(b.toString());
            return true;
        }catch (IOException e){
            Toast.makeText(this, "Unable to execute the command", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
