package spacexlaunches.main.beans;

import com.codename1.io.Externalizable;
import com.codename1.io.Util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Launches implements Externalizable {

    private static final int VERSION = 2;
    private String name;

    private ArrayList<Object> data;

    private Date startedAt;

    @Override
    public int getVersion() {
        return VERSION;
    }

    @Override
    public void externalize(DataOutputStream out) throws IOException {
        Util.writeUTF(name, out);
        Util.writeObject(data, out);
        if (startedAt != null) {
            out.writeBoolean(true);
            out.writeLong(startedAt.getTime());
        } else {
            out.writeBoolean(false);
        }
    }

    @Override
    public void internalize(int version, DataInputStream in) throws IOException {
        name = Util.readUTF(in);
        data = (ArrayList<Object>) Util.readObject(in);
        if (version > 1) {
            boolean hasDate = in.readBoolean();
            if (hasDate) {
                startedAt = new Date(in.readLong());
            }
        }
    }

    @Override
    public String getObjectId() {
        return "Launches";
    }
}
