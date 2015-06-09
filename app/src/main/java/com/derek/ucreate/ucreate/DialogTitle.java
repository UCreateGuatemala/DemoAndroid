package com.derek.ucreate.ucreate;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * @author Stelian Morariu
 *
 */
public class DialogTitle extends Dialog implements android.view.View.OnClickListener {

    private boolean keyboardVisible = false;

    private EditText textBox;

    private Button okBtn;

    /**
     * @param context
     */
    public DialogTitle(Context context) {
        super(context);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.app.Dialog#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_dialog);
        getWindow().setLayout(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        setTitle("My Dialog");
        this.okBtn = (Button) findViewById(R.id.button1);
        this.okBtn.setOnClickListener(this);
        this.okBtn.requestFocus();

        this.textBox = (EditText) findViewById(R.id.editText1);
        this.textBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View arg0, boolean arg1) {
                InputMethodManager inputMgr = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        });

        this.textBox.requestFocus();
        this.keyboardVisible = true;

        LinearLayout rootLayout = (LinearLayout) findViewById(R.id.dlgRoot);
        rootLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                keyboardVisible = !keyboardVisible;
            }
        });
    }

    /*
     * (non-Javadoc)
     *
     * @see android.app.Dialog#onStop()
     */
    @Override
    protected void onStop() {
        super.onStop();

        if (keyboardVisible) {
            InputMethodManager inputMgr = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMgr.toggleSoftInput(0, 0);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        this.dismiss();

    }

}
