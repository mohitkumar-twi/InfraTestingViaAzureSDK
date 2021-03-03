package Utils;

import com.jcraft.jsch.*;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CommonUtils {

    private static Logger logger = Logger.getLogger(CommonUtils.class);

    public String executeCommand(String command) {
        StringBuffer output = new StringBuffer();
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {

        }
        return output.toString();
    }

    public String runSSHcommands(Session ses, String command) {
        String output = "";
        ChannelExec channel = null;
        try {
            channel = (ChannelExec) ses.openChannel("exec");
        } catch (JSchException e) {
            logger.error("Channel couldn't be Opened for Execution");
        }
        channel.setCommand(command);
        try {
            channel.connect();
        } catch (JSchException e) {
            logger.error("Channel Not Connected");
        }

        InputStream in = null;
        if (channel.isConnected()) {
            try {
                in = channel.getInputStream();
            } catch (IOException e) {
                logger.error("<====No InputStream Found====>");
            }
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = "";
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;

            } catch (IOException e) {
                logger.error("<====No Output Found====>");
            }
            output = output + line + "\n";

        }
        return output;
    }

    public Session loginBySSH(String username, String host ,String password) {
        Session session = null;
        try {
            session = new JSch().getSession(username, host);
            session.setPassword(password);
            session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            if (session.isConnected()) {
                logger.info("<======Device Connected======>");
            }
        } catch (Exception e) {
            logger.error("<======Device Not Connected======>");

        }
        return session;

    }

    public void disconnectDevice(Session session) {
        session.disconnect();
        if (!session.isConnected()) {
            logger.info("<=======Device Disconnected=======>");
        }
    }



}
