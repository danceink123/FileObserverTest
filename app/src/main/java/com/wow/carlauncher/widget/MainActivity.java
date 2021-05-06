package com.wow.carlauncher.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;


import com.feketga.fileobservertest.R;
import com.wow.carlauncher.widget.ty.TyEventInfo;
import com.wow.dudu.commonBridge.warp.ex.s2c.S2CTirePressureInfo;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mOutputText;
    private TextView mEventDump;
    private ScrollView mScrollCommandDump;
    private ScrollView mScrollEventDump;

    private EditText mFilePath;
    private int mPendingAction = -1;
    private Handler mHandler;
    private String monitorFolder = "/storage/emulated/0/steelmate/t620/Log/";
    private String newestLogFile = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOutputText = (TextView) findViewById(R.id.output_text);
        mEventDump = (TextView) findViewById(R.id.event_dump);

        mScrollCommandDump = (ScrollView) findViewById(R.id.scroll_command_dump);
        mScrollEventDump = (ScrollView) findViewById(R.id.scroll_event_dump);

        mFilePath = (EditText) findViewById(R.id.file_path);
        monitorFolder = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "steelmate/t620/Log/";
        mFilePath.setText(monitorFolder);
        mHandler = new Handler();
        startObserving();
    }

    public String getFilesAllName(String path) {
        try {
            File file = new File(path);
            File[] files = file.listFiles();
            if (files == null) {
                Log.e("error", "空目录");
                return null;
            }
            List<String> s = new ArrayList<>();
            int newestDate = 0;
//            String newestFileName = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            for (int i = 0; i < files.length; i++) {
                int curInt = Integer.parseInt(files[i].getName().replace("devicelog-", "").split(".")[0]);
                newestLogFile = curInt > newestDate ? files[i].getName() : newestLogFile;
            }
            appendToCommandDump(newestLogFile);
            return newestLogFile;
        } catch (Exception e) {
            appendToCommandDump(e.getMessage());
            return null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(
                mMessageReceiver, new IntentFilter(MyService.ACTION_EVENT));

        requestEventDump();
    }

    @Override
    protected void onPause() {
        super.onPause();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (MyService.ACTION_EVENT.equals(action)) {
                final String text = intent.getStringExtra(MyService.EXTRA_EVENT_DUMP);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        addToEventDump(text);
                    }
                });

            }
        }
    };

    private ArrayList<String> newList;

    private String tail(String strFilePath) {
        String path = mFilePath.getText().toString()+File.separator+strFilePath;
        newList = new ArrayList<String>();
        //打开文件
        File file = new File(path);
        RandomAccessFile fileHandler = null;
        try {
            fileHandler = new RandomAccessFile( file, "r" );
            long fileLength = fileHandler.length() - 1;
            StringBuilder sb = new StringBuilder();

            for(long filePointer = fileLength; filePointer != -1; filePointer--){
                fileHandler.seek( filePointer );
                int readByte = fileHandler.readByte();

                if( readByte == 0xA ) {
                    if( filePointer == fileLength ) {
                        continue;
                    }
                    break;

                } else if( readByte == 0xD ) {
                    if( filePointer == fileLength - 1 ) {
                        continue;
                    }
                    break;
                }

                sb.append( ( char ) readByte );
            }

            String lastLine = sb.reverse().toString();
            return lastLine;
        } catch( java.io.FileNotFoundException e ) {
            e.printStackTrace();
            return null;
        } catch( java.io.IOException e ) {
            e.printStackTrace();
            return null;
        } finally {
            if (fileHandler != null )
                try {
                    fileHandler.close();
                } catch (IOException e) {
                    /* ignore */
                }
        }
    }

    private String ReadTxtFile(String strFilePath) {
        String path = mFilePath.getText().toString()+File.separator+strFilePath;
        newList = new ArrayList<String>();
        //打开文件
        File file = new File(path);
        //如果path是传递过来的参数，可以做一个非目录的判断
        if (file.isDirectory()) {
            appendToCommandDump("参数为文件目录，无法打开.\n");
        } else {
            try {
                InputStream instream = new FileInputStream(file);
                if (instream != null) {
                    InputStreamReader inputreader = new InputStreamReader(instream);
                    BufferedReader buffreader = new BufferedReader(inputreader);
                    String line;
                    //分行读取
                    while ((line = buffreader.readLine()) != null) {
                        newList.add(line + "\n");
                        appendToCommandDump(line);
                    }
                    instream.close();
                }
            } catch (java.io.FileNotFoundException e) {
                appendToCommandDump("文件不存在:" + e.getMessage());
                //ReadTxtFile(strFilePath.replace("/storage/emulated/0",""));
            } catch (IOException e) {
                appendToCommandDump(e.getMessage());
            }
        }
        return strFilePath;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        mPendingAction = id;
//        ensurePermissionAndHandleAction();
        handleAction();

        return true;
    }

    private void handleAction() {
        switch (mPendingAction) {
            case R.id.action_clear_output:
                clearOutput();
                break;
            case R.id.action_create_file:
                createFile();
                break;
            case R.id.action_write_file:
                writeFile();
                break;
            case R.id.action_read_file:
                readFile();
                break;
            case R.id.action_delete_file:
                deleteFile();
                break;
            case R.id.action_make_directory:
                makeDirectory();
                break;
            case R.id.action_start_observing:
                startObserving();
                break;
            case R.id.action_stop_observing:
                stopObserving();
                break;
            default:
                assert false;
                break;
        }
    }

    private void clearOutput() {
        mOutputText.setText("");

        Intent intent = new Intent(this, MyService.class);
        intent.setAction(MyService.ACTION_COMMAND_CLEAR);
        startService(intent);
    }

    private void requestEventDump() {
        Intent intent = new Intent(this, MyService.class);
        intent.setAction(MyService.ACTION_COMMAND_DUMP);
        startService(intent);
    }

    private void createFile() {
        File file = new File(mFilePath.getText().toString()+File.separator+"123.txt");
        File path = file.getParentFile();
        try {
            if (!path.exists()) {
                path.mkdirs();
            }
            file.createNewFile();
            appendToCommandDump("CREATE: OK: File created: " + file.getAbsolutePath() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
//            e.getStackTrace();
//            appendToCommandDump("CREATE: ERROR: IOException: " + e.getMessage() + "\n");
    }

    private void writeFile() {
        File file = new File(mFilePath.getText().toString()+File.separator+"123.txt");
        try {
            FileOutputStream stream = new FileOutputStream(file.getAbsolutePath());
            String data = "Hello " + System.currentTimeMillis();
            stream.write(data.getBytes());
            stream.close();
            appendToCommandDump("WRITE: Text: " + data + "\n");
        } catch (FileNotFoundException e) {
            appendToCommandDump("WRITE: ERROR: FileNotFoundException: " + e.getMessage() + "\n");
        } catch (IOException e) {
            appendToCommandDump("WRITE: ERROR: IOException: " + e.getMessage() + "\n");
        }
    }

    private void readLogFile(String file){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(mFilePath.getText().toString()+File.separator+file));
            StringBuilder sb = new StringBuilder();
            appendToCommandDump("READ: Text: " + br.readLine() + "\n");
        } catch (FileNotFoundException e) {
            appendToCommandDump("READ: ERROR: FileNotFoundException: " + e.getMessage() + "\n");
        } catch (IOException e) {
            appendToCommandDump("READ: ERROR: IOException: " + e.getMessage() + "\n");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    appendToCommandDump("READ: ERROR: IOException: " + e.getMessage() + "\n");
                }
            }
        }
    }
    private void readFile() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(mFilePath.getText().toString()+newestLogFile));
            StringBuilder sb = new StringBuilder();
            appendToCommandDump("READ: Text: " + br.readLine() + "\n");
        } catch (FileNotFoundException e) {
            appendToCommandDump("READ: ERROR: FileNotFoundException: " + e.getMessage() + "\n");
        } catch (IOException e) {
            appendToCommandDump("READ: ERROR: IOException: " + e.getMessage() + "\n");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    appendToCommandDump("READ: ERROR: IOException: " + e.getMessage() + "\n");
                }
            }
        }
    }

    private void deleteFile() {
        File file = new File(mFilePath.getText().toString());
        if (file.delete()) {
            appendToCommandDump("DELETE: OK: Deleted: " + file.getAbsolutePath() + "\n");
        } else {
            appendToCommandDump("DELETE: ERROR: Cannot delete: " + file.getAbsolutePath() + "\n");
        }
    }

    private void makeDirectory() {
        File file = new File(mFilePath.getText().toString());
        if (file.mkdirs()) {
            appendToCommandDump("MKDIR: OK: Made: " + file.getAbsolutePath() + "\n");
        } else {
            appendToCommandDump("MKDIR: ERROR: Cannot make: " + file.getAbsolutePath() + "\n");
        }
    }

    private void appendToCommandDump(String text) {
        mOutputText.append(text+"\n");
        mScrollCommandDump.fullScroll(ScrollView.FOCUS_DOWN);
    }

    private void addToEventDump(String text) {
        mEventDump.setText(text);
        //appendToCommandDump(text.split(":")[0]);
        switch (text.split(":")[0]){
            case "CLOSE_WRITE":
                if (text.split(":")[1]!="null") {
//                    readLogFile(text.split(":")[1]);
                    String byteDataStr = tail(text.split(":")[1]);
                    if (byteDataStr.contains("TYPE:dataR | ")) {
                        String byteData = byteDataStr.substring(byteDataStr.indexOf("TYPE:dataR | ") + 13);
                        byte[] bytes = HexDump.hexStringToByteArray(byteData.replace(" ",""));
                        appendToCommandDump(byteData);
                        handleTyreData(bytes);
                    }
                }
                break;
            case "CREATE":
                break;
        }
        mScrollEventDump.fullScroll(ScrollView.FOCUS_DOWN);
    }

    private String dumpTyreInfo(TyreInfoBean tyreInfoBean){
        SpannableStringBuilder spn = new SpannableStringBuilder();

        String tyreName = "右后轮";
        if (tyreInfoBean.getPosition() == 2) {
            tyreName = "左前轮";
        } else if (tyreInfoBean.getPosition() == 1) {
            tyreName = "右前轮";
        } else if (tyreInfoBean.getPosition() == 4) {
            tyreName = "左后轮";
        }
        spn.append(tyreName + "--" + tyreInfoBean.getSensorId() + "，压力：" + tyreInfoBean.getAirPressure() + "，温度：" + tyreInfoBean.getTemperature() + "，压力值：" + tyreInfoBean.getAirValue() + "\n");
        //sendMessage(tyreInfoBean);
        return spn.toString();
    }

    private void handleTyreData(byte[] bArr){
        try{
            byte[] bArr2 = new byte[8];
            if (bArr != null && bArr[4] == 99 && bArr.length >= 7) {
                boolean z = true;
                if (bArr[5] == 0) {
                    if (bArr.length >= 15) {
                        System.arraycopy(bArr, 6, bArr2, 0, bArr2.length);
                        postTyreInfo(bArr2, false);
                    }
                } else if (bArr[5] != -1 && bArr[5] != -86 && bArr.length >= 14) {
                    System.arraycopy(bArr, 5, bArr2, 0, bArr2.length);
                    if (postTyreInfo(bArr2, true)) {
                    }
                }
            }
        }catch (Exception e){
            appendToCommandDump(e.getMessage());
        }

    }

    private boolean postTyreInfo(byte[] bArr,boolean z){
        boolean z2;
        synchronized (this) {
            z2 = false;
            if (bArr != null) {
                try {
                    if (bArr.length == 8) {
                        TyreInfoBean tyreInfoBean = getTyreInfoBean(bArr);
                        if (tyreInfoBean.getPosition() == 1) {
                            EventBus.getDefault().post(new TyEventInfo().setlFTirePressure(Float.parseFloat(tyreInfoBean.getTemperature())).setlFTemp(Integer.getInteger(tyreInfoBean.getTemperature())));
                        } else if (tyreInfoBean.getPosition() == 2) {
                            EventBus.getDefault().post(new TyEventInfo().setrFTirePressure(Float.parseFloat(tyreInfoBean.getTemperature())).setrFTemp(Integer.getInteger(tyreInfoBean.getTemperature())));
                        } else if (tyreInfoBean.getPosition() == 3) {
                            EventBus.getDefault().post(new TyEventInfo().setlBTirePressure(Float.parseFloat(tyreInfoBean.getTemperature())).setlBTemp(Integer.getInteger(tyreInfoBean.getTemperature())));
                        } else if (tyreInfoBean.getPosition() == 4) {
                            EventBus.getDefault().post(new TyEventInfo().setrBTirePressure(Float.parseFloat(tyreInfoBean.getTemperature())).setrBTemp(Integer.getInteger(tyreInfoBean.getTemperature())));
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return z2;
    }

    private TyreInfoBean getTyreInfoBean(byte[] bArr) {
        if (bArr == null || bArr.length != 8) {
            return null;
        }
        TyreInfoBean tyreInfoBean = new TyreInfoBean();
        StringBuilder sb = new StringBuilder();
        tyreInfoBean.setPosition(bArr[0] & 255);
        sb.append("0x");
        sb.append(HexDump.a(bArr[1]));
        sb.append(HexDump.a(bArr[2]));
        sb.append(HexDump.a(bArr[3]));
        tyreInfoBean.setSensorId(sb.toString());
        float parseFloat = Float.parseFloat(HexDump.getBigDecimalStr(((float) HexDump.getBigDecimalDou((double) (((bArr[4] << 8) & 768) | (bArr[5] & 255)), 0.025d)) + "", 1));
        tyreInfoBean.setAirValue(parseFloat);
        tyreInfoBean.setAirPressure(HexDump.getBigDecimalStr(parseFloat + "", 1));
        tyreInfoBean.setTemperature("" + ((bArr[6] & 255) + -50));
        tyreInfoBean.setState(bArr[7]);
        appendToCommandDump(dumpTyreInfo(tyreInfoBean));
        return tyreInfoBean;
    }

    private void startObserving() {
        Intent intent = new Intent(this, MyService.class);
        intent.setAction(MyService.ACTION_COMMAND_START);
        intent.putExtra(MyService.EXTRA_FILE_PATH, mFilePath.getText().toString());
        startService(intent);
    }

    private void stopObserving() {
        Intent intent = new Intent(this, MyService.class);
        intent.setAction(MyService.ACTION_COMMAND_STOP);
        startService(intent);
    }

}
