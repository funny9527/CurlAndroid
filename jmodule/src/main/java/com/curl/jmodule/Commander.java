package com.curl.jmodule;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Commander {
	private static String TAG = "curl";

	private String terminal;
	private String security;

	static {
		System.loadLibrary("jcurl");
	}

	public void init(String terminal, String cert) {
	    this.terminal = terminal;
	    this.security = cert;
    }

    /**
     *
     * @param cmd
     * @return
     */
	public String execute(String cmd) {
		String[] arr = cmd.split(" ");
        List<String> keys = new ArrayList<>(arr.length);
        for (String temp : arr) {
            if (temp.trim().length() > 0) {
                keys.add(temp.trim());
            }
        }
        arr = new String[keys.size()];
        keys.toArray(arr);
		start(arr, terminal, security);

        FileInputStream inputStream = null;
        String buffer = null;
        try {
            inputStream = new FileInputStream(new File(terminal));
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            buffer = new String(data);
            Log.d(TAG, "finally done");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return buffer;
	}

	private native int start(String[] cmd, String terminal, String security);
}
