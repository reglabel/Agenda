package com.example.agenda.ui.views;

import android.view.View;
import android.widget.TextView;

import com.example.agenda.R;

public class ViewHolderAlunoItem {
    public final TextView nome;
    public final TextView telefone;

    public ViewHolderAlunoItem(View view) {
        nome = view.findViewById(R.id.item_aluno_nome);
        telefone = view.findViewById(R.id.item_aluno_telefone);
    }
}
