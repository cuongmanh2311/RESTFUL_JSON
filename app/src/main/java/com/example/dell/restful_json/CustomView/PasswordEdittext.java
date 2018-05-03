package com.example.dell.restful_json.CustomView;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
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
public class PasswordEdittext extends EditText {
    Drawable eye,eyeG;
    //mặc định là password k hiển thị lên
    Boolean visible=false;
    Boolean userStrike=false;
    Drawable drawable;
    //̣độ trong suốt eye
    int ALPHA= (int) (255* .70f);
    public PasswordEdittext(Context context) {
        super(context);
        Init(null);
    }

    public PasswordEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init(attrs);
    }

    public PasswordEdittext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PasswordEdittext(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Init(attrs);
    }
    public void  Init(AttributeSet attrs)
    {
        //lấy thuoc tinh userStrike bên xml
        if(attrs!=null)
        {
            TypedArray array=getContext().getTheme().obtainStyledAttributes(attrs,R.styleable.PasswordEdittext,0,0);
            this.userStrike=array.getBoolean(R.styleable.PasswordEdittext_useStrike,false);
        }
        //ContextCompat : vì trang chủ sử dụng appcompat
        eye= ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_black_24dp).mutate();
        eyeG=ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_black_24dp).mutate();
        Setting();

    }
    public void Setting()
    {
        setInputType(InputType.TYPE_CLASS_TEXT |(visible? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD :InputType.TYPE_TEXT_VARIATION_PASSWORD ));
        Drawable[]drawables=getCompoundDrawables();
         drawable=userStrike && !visible? eyeG : eye;
        drawable.setAlpha(ALPHA);
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable ,drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //sự kiện chạm màn hình
        if(event.getAction()==MotionEvent.ACTION_UP &&event.getX()>=(getRight()-drawable.getBounds().width()))
        {
            visible=!visible;
            Setting();
            //tự động kiểm tra sự kiện click
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}
