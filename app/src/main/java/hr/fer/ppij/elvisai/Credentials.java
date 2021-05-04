package hr.fer.ppij.elvisai;

import android.util.Base64;
import android.util.Log;

public class Credentials {
    public String client_id;
    public String client_secret;
    private String encodedCredentials;

    public Credentials(){}

    public Credentials(String client_id, String client_secret){
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.encodeCredentials();
    }

    public void encodeCredentials(){
        String strToEncode = this.client_id + ":" + this.client_secret;
        this.encodedCredentials = Base64.encodeToString(strToEncode.getBytes(),Base64.NO_WRAP);
    }
    public String getClient_secret() {
        return this.client_secret;
    }

    public String getClient_id() {
        return this.client_id;
    }

    public String getEncodedCredentials() {
        return this.encodedCredentials;
    }
}
