package org.obridge.test.database.converters;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import org.obridge.test.database.objects.*;

public final class SampleTypeOneConverter {

    private final static String TYPE_NAME = "SAMPLE_TYPE_ONE";

    private SampleTypeOneConverter(){
    }

    public static Struct getStruct(SampleTypeOne o, Connection connection) throws SQLException {

        if(o == null) {
            return null;
        }

        List<Object> struct = new ArrayList<Object>();

        struct.add(0, o.getAttrVarchar()); // ATTR_VARCHAR
        Clob cl1 = connection.createClob();
        cl1.setString(1, o.getAttrClob());
        struct.add(1, cl1); // ATTR_CLOB
        struct.add(2, o.getAttrInt()); // ATTR_INT
        struct.add(3, o.getAttrBigdec1()); // ATTR_BIGDEC_1
        struct.add(4, o.getAttrBigdec2()); // ATTR_BIGDEC_2
        struct.add(5, o.getDateA()); // DATE_A
        struct.add(6, o.getTimestB()); // TIMEST_B
        struct.add(7, o.getTimestC()); // TIMEST_C

        return connection.createStruct(TYPE_NAME, struct.toArray());
    }

    public static Array getListArray(List<SampleTypeOne> o, Connection connection) throws SQLException {

        if(o == null) {
            return connection.createArrayOf(TYPE_NAME, new Object[0]);
        }


        List<Object> array = new ArrayList<Object>(o.size());

        for(SampleTypeOne e : o) {
            array.add(SampleTypeOneConverter.getStruct(e, connection));
        }

        return connection.createArrayOf(TYPE_NAME, array.toArray());
    }

    public static SampleTypeOne getObject(Struct struct) throws SQLException {

        if(struct.getAttributes() == null || struct.getAttributes().length == 0) {
            return null;
        }

        SampleTypeOne result = new SampleTypeOne();

        Object[] attr = struct.getAttributes();

        if(attr[0] != null)
            result.setAttrVarchar((String)attr[0]); // ATTR_VARCHAR
        if(attr[1] != null)
            result.setAttrClob(((Clob)attr[1]).getSubString(1, (int)((Clob)attr[1]).length())); // ATTR_CLOB
        if(attr[2] != null)
            result.setAttrInt(((BigDecimal)attr[2]).intValue()); // ATTR_INT
        if(attr[3] != null)
            result.setAttrBigdec1((BigDecimal)attr[3]); // ATTR_BIGDEC_1
        if(attr[4] != null)
            result.setAttrBigdec2((BigDecimal)attr[4]); // ATTR_BIGDEC_2
        if(attr[5] != null)
            result.setDateA(new Date(((Timestamp)attr[5]).getTime())); // DATE_A
        if(attr[6] != null)
            result.setTimestB((Timestamp)attr[6]); // TIMEST_B
        if(attr[7] != null)
            result.setTimestC((Timestamp)attr[7]); // TIMEST_C

        return result;
    }

    public static List<SampleTypeOne> getObjectList(Array array) throws SQLException {
        List<SampleTypeOne> result = new ArrayList<SampleTypeOne>();
        ResultSet rs = null;

        try {
            rs = array.getResultSet();
            while (rs.next()) {
                result.add(SampleTypeOneConverter.getObject((Struct)rs.getObject(2)));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }

        return result;
    }

}