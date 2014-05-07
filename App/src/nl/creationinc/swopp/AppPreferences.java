package nl.creationinc.swopp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppPreferences {
	public static final String KEY_PREFS_USER_ID = "user_id";
	public static final String KEY_PREFS_USER_NAME = "user_name";
	public static final String KEY_PREFS_USER_PHONE = "user_phone";
	public static final String KEY_PREFS_USER_EMAIL = "user_email";
	public static final String KEY_PREFS_USER_PICURL = "user_picurl";
    private static final String APP_SHARED_PREFS = AppPreferences.class.getSimpleName(); //  Name of the file -.xml
    private SharedPreferences _sharedPrefs;
    private Editor _prefsEditor;

    public AppPreferences(Context context) {
        this._sharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        this._prefsEditor = _sharedPrefs.edit();
    }
    
    public int getUserId() {
        return _sharedPrefs.getInt(KEY_PREFS_USER_ID, 0);
    }
    
    public String getUserName() {
        return _sharedPrefs.getString(KEY_PREFS_USER_NAME, null);
    }
    
    public String getUserPhone() {
        return _sharedPrefs.getString(KEY_PREFS_USER_PHONE, null);
    }
    
    public String getUserEmail() {
        return _sharedPrefs.getString(KEY_PREFS_USER_EMAIL, null);
    }
    
    public String getUserPicUrl() {
        return _sharedPrefs.getString(KEY_PREFS_USER_PICURL, null);
    }
    
    public void saveUserId(int id) {
        _prefsEditor.putInt(KEY_PREFS_USER_ID, id);
        _prefsEditor.commit();
    }
    
    public void saveUserName(String text) {
        _prefsEditor.putString(KEY_PREFS_USER_NAME, text);
        _prefsEditor.commit();
    }
    
    public void saveUserPhone(String text) {
        _prefsEditor.putString(KEY_PREFS_USER_PHONE, text);
        _prefsEditor.commit();
    }
    
    public void saveUserEmail(String text) {
        _prefsEditor.putString(KEY_PREFS_USER_EMAIL, text);
        _prefsEditor.commit();
    }
    
    public void saveUserPicUrl(String text) {
        _prefsEditor.putString(KEY_PREFS_USER_PICURL, text);
        _prefsEditor.commit();
    }
    
    public void saveUserPrefs(int id, String username, String phone, String email, String picurl) {
    	_prefsEditor.putInt(KEY_PREFS_USER_ID, id);
    	_prefsEditor.putString(KEY_PREFS_USER_NAME, username);
    	_prefsEditor.putString(KEY_PREFS_USER_PHONE, phone);
        _prefsEditor.putString(KEY_PREFS_USER_EMAIL, email);
        _prefsEditor.putString(KEY_PREFS_USER_PICURL, picurl);
        _prefsEditor.commit();
    }
    
    public void destroyUserPrefs() {
    	_prefsEditor.putInt(KEY_PREFS_USER_ID, 0);
        _prefsEditor.putString(KEY_PREFS_USER_NAME, null);
        _prefsEditor.putString(KEY_PREFS_USER_PHONE, null);
        _prefsEditor.putString(KEY_PREFS_USER_EMAIL, null);
        _prefsEditor.putString(KEY_PREFS_USER_PICURL, null);
        _prefsEditor.commit();
    }
}
