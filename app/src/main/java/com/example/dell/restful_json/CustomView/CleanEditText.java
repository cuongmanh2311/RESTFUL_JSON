package com.example.dell.restful_json.CustomView;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.example.dell.restful_json.R;

/**
 * Created by Dell on 4/30/2018.
 */

@SuppressLint("AppCompatCustomView")
public class CleanEditText extends EditText {
    Drawable cleanX,cleanNoX;
    Drawable drawable;
    Boolean visible=false;
    public CleanEditText(Context context) {
        super(context);
        Init();
    }

    public CleanEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init();
    }

    public CleanEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CleanEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Init();
    }
    public void Init()
    {
        cleanX= ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24dp).mutate();
        //hình trong suốt mặc định của android
        cleanNoX=ContextCompat.getDrawable(getContext(), android.R.drawable.screen_background_light_transparent).mutate();
        Setting();
    }
    public void Setting()
    {
        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[]drawables=getCompoundDrawables();
        drawable= visible ? cleanX : cleanNoX;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }
    //sự kiện kiểm tra edittext đã nhập chữ hay chưa
    //lenghAfter:  chiều dài chuỗi sau khi nhập
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        //ngay vị trí đầu chưa có data
        if(lengthAfter==0&&start==0)
        {
            visible=false;
            Setting();
        }else{
            visible=true;
            Setting();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(MotionEvent.ACTION_DOWN==event.getAction()&&event.getX()>=(getRight()-drawable.getBounds().width()))
        {
            setText("");
        }
        return super.onTouchEvent(event);
    }
}
