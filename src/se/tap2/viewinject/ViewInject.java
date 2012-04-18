package se.tap2.viewinject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import android.app.Activity;
import android.view.View;

public class ViewInject {

	public static <T extends Activity> void injectViews(T activity) {
		@SuppressWarnings("unchecked")
		Class<T> cls = (Class<T>) activity.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			if(View.class.isAssignableFrom(field.getType())) {

				Inject injectAnnotation = field.getAnnotation(Inject.class);
				if(injectAnnotation != null) {
					try {
						View view = activity.findViewById(injectAnnotation.id());
						if(view == null) {
							throw new RuntimeException("No View for id " + injectAnnotation.id());
						}
						field.set(activity, view);
					} catch (NumberFormatException e) {
						throw new RuntimeException("No View for id " + injectAnnotation.id(), e);
					} catch (IllegalArgumentException e) {
						throw new RuntimeException("Error, cannot load " + injectAnnotation.id(), e);
					} catch (IllegalAccessException e) {
						throw new RuntimeException("Error, cannot load " + injectAnnotation.id(), e);
					}
				}
				
			}
		}
	}

}
