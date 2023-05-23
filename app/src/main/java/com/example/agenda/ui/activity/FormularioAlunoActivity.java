package com.example.agenda.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {
    public static final String TITULO_APPBAR = "Novo aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final AlunoDAO dao = new AlunoDAO();

    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();
        configuraBotaoSalvar();

        Intent dados = getIntent();
        if(dados.hasExtra("aluno")) {
            aluno = dados.getExtras().getParcelable("aluno");
            campoNome.setText(aluno.getNome());
            campoTelefone.setText(aluno.getTelefone());
            campoEmail.setText(aluno.getEmail());
        } else {
            aluno = new Aluno();
        }
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.activity_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Aluno alunoCriado = criaAluno();
//                salva(alunoCriado);
                preencheAluno();
                if(aluno.temIdValido()){
                    dao.edita(aluno);
                } else{
                    dao.salva(aluno);
                }
                finish();
            }
        });
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private boolean validarDados(){
        boolean isValid = true;

        if(campoNome.getText().toString().length() == 0){
            customToastError("Nome é obrigatório!");
            isValid = false;
        }
        if(campoTelefone.getText().toString().length() < 11){
            customToastError("Telefone é obrigatório e deve conter 11 dígitos (com DD e 9)!");
            isValid =  false;
        }
        if(campoEmail.getText().toString().length() == 0 || campoEmail.getText().toString().indexOf('@') == -1){
            customToastError("Email é obrigatório e deve conter @!");
            isValid = false;
        }
        return isValid;
    }

    private void customToastError(String mensagem){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, (LinearLayout) findViewById(R.id.custom_toast_layout_id));
        TextView text = (TextView) layout.findViewById(R.id.custom_toast_text_id);
        text.setText(mensagem);
        Toast toast = Toast.makeText(getApplicationContext(),
                mensagem, Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    };
}