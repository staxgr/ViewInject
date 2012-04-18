package se.tap2.viewinject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class ViewInjectActivity extends Activity {

	@Inject(id=R.id.ratingBar)
	RatingBar mRatingBar;
	
	@Inject(id=R.id.replyText)
	TextView mReplyText;
	
	@Inject(id=R.id.questionText)
	TextView mQuestionText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ViewInject.injectViews(this);
        
        mQuestionText.setText("What do you think about ViewInject?");
        mReplyText.setText(":-|");
        
        mRatingBar.setRating(2.5f);
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				if(rating > 3.0) {
					mReplyText.setText(":-)");
				} else if(rating <= 2.0) {
					mReplyText.setText(":-(");
				} else {
					mReplyText.setText(":-|");
				}
			}
		});
        
    }
}