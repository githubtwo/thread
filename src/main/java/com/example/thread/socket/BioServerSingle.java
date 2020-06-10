package com.example.thread.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: thread
 * @description:
 * @author: huangyizeng
 * @create: 2020-05-30 18:12
 */
public class BioServerSingle {

    public static void main(String[] args) {
        int port = 8080;
        ServerSocket server = null;
        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            server = new ServerSocket(port);
            while(true){
                socket = server.accept();
                inputStream = socket.getInputStream();

                int length = 0;
                byte[] req = new byte[1024];
                while ((length = inputStream.read(req)) > 0){
                    System.out.println("req:" + new String(req,0,length));
                    //...
                    outputStream = socket.getOutputStream();
                    outputStream.write("req".getBytes());
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
