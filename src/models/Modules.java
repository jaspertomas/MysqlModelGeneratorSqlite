package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MySqlDBHelper;

public class Modules {
    //------------FIELDS-----------
    public static final String tablename="modules";
    //field names
    public static String[] fields={
            "id"
            ,"namespace"
            ,"name"
            ,"description"
            ,"version"
            ,"outputdir"
            ,"type"
            ,"options"
            ,"viewLayout"
            ,"formLayout"
            ,"addLayout"
            ,"editLayout"
            ,"xml"
            };
    //field types
    public static String[] fieldtypes={
            "bigint(11)"
            ,"varchar(255)"
            ,"varchar(50)"
            ,"text"
            ,"varchar(10)"
            ,"varchar(255)"
            ,"varchar(50)"
            ,"varchar(255)"
            ,"text"
            ,"text"
            ,"text"
            ,"text"
            ,"text"
            };
    //-----------------------

    public Long id;
    public String namespace;
    public String name;
    public String description;
    public String version;
    public String outputdir;
    public String type;
    public String options;
    public String viewLayout;
    public String formLayout;
    public String addLayout;
    public String editLayout;
    public String xml;

    public Modules() {
    }
    public Modules(ResultSet rs) {
        try {
            id=rs.getLong("id");
            namespace=rs.getString("namespace");
            name=rs.getString("name");
            description=rs.getString("description");
            version=rs.getString("version");
            outputdir=rs.getString("outputdir");
            type=rs.getString("type");
            options=rs.getString("options");
            viewLayout=rs.getString("viewLayout");
            formLayout=rs.getString("formLayout");
            addLayout=rs.getString("addLayout");
            editLayout=rs.getString("editLayout");
            xml=rs.getString("xml");
        } catch (SQLException ex) {
            Logger.getLogger(Modules.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

//	public String getUuid()
//	{
//		return id.toString()+"-";
//	}

    public Long getId() {
            return id;
    }

    public void setId(Long id) {
            this.id = id;
    }

    public String getNamespace() {
            return namespace;
    }

    public void setNamespace(String namespace) {
            this.namespace = namespace;
    }

    public String getName() {
            return name;
    }

    public void setName(String name) {
            this.name = name;
    }

    public String getDescription() {
            return description;
    }

    public void setDescription(String description) {
            this.description = description;
    }

    public String getVersion() {
            return version;
    }

    public void setVersion(String version) {
            this.version = version;
    }

    public String getOutputdir() {
            return outputdir;
    }

    public void setOutputdir(String outputdir) {
            this.outputdir = outputdir;
    }

    public String getType() {
            return type;
    }

    public void setType(String type) {
            this.type = type;
    }

    public String getOptions() {
            return options;
    }

    public void setOptions(String options) {
            this.options = options;
    }

    public String getViewLayout() {
            return viewLayout;
    }

    public void setViewLayout(String viewLayout) {
            this.viewLayout = viewLayout;
    }

    public String getFormLayout() {
            return formLayout;
    }

    public void setFormLayout(String formLayout) {
            this.formLayout = formLayout;
    }

    public String getAddLayout() {
            return addLayout;
    }

    public void setAddLayout(String addLayout) {
            this.addLayout = addLayout;
    }

    public String getEditLayout() {
            return editLayout;
    }

    public void setEditLayout(String editLayout) {
            this.editLayout = editLayout;
    }

    public String getXml() {
            return xml;
    }

    public void setXml(String xml) {
            this.xml = xml;
    }


    //database functions
    public ArrayList<String> implodeFieldValuesHelper(boolean withId)
    {
            ArrayList<String> values=new ArrayList<String>(); 
            if(withId)values.add(id.toString());

            //add values for each field here
            values.add(id.toString());
            values.add(namespace);
            values.add(name);
            values.add(description);
            values.add(version);
            values.add(outputdir);
            values.add(type);
            values.add(options);
            values.add(viewLayout);
            values.add(formLayout);
            values.add(addLayout);
            values.add(editLayout);
            values.add(xml);

            return values;
    }
    public void delete()
    {
            Modules.delete(this);
    }
    public void save()
    {
            if(id==null || id==0)
                    Modules.insert(this);
            else
                    Modules.update(this);
    }
    public String toString()
    {
            return id.toString();
    }

    //-------------------------TABLE FUNCTIONS---------------------

    //-----------getter functions----------
    /*
    public static Modules getByName(String name)
    {
            HashMap<String,Modules> map=select(" name = '"+name+"'");
            for(Modules item:map.values())return item;
            return null;
    }	
    */
    public static Modules getById(Long id) {
            HashMap<String,Modules> map=select(" id = '"+id.toString()+"'");
            for(Modules item:map.values())return item;
            return null;
    }
    //-----------database functions--------------

    public static void delete(Long id)
    {
        Connection conn=MySqlDBHelper.getInstance().getConnection();            
        Statement st = null;
        try { 
            st = conn.createStatement();
            st.executeUpdate("delete from "+tablename+" where id = '"+id.toString()+"';");
        } catch (SQLException ex) {
            Logger.getLogger(Modules.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void delete(Modules item)
    {
        delete(item.getId());
    }
    public static void insert(Modules item)
    {
        Connection conn=MySqlDBHelper.getInstance().getConnection();            
        Statement st = null;
        boolean withid=false;
        try { 
            st = conn.createStatement();
            //for tables with integer primary key
            if(fieldtypes[0].contentEquals("integer"))withid=false;                
            //for tables with varchar primary key
            else if(fieldtypes[0].contains("varchar"))withid=true;                
            st.executeUpdate("INSERT INTO "+tablename+" ("+implodeFields(withid)+")VALUES ("+implodeValues(item, withid)+");");
        } catch (SQLException ex) {
            Logger.getLogger(Modules.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void update(Modules item)
    {
        Connection conn=MySqlDBHelper.getInstance().getConnection();            
        Statement st = null;
        boolean withid=false;
        try { 
            st = conn.createStatement();
            st.executeUpdate("update "+tablename+" set "+implodeFieldsWithValues(item,false)+" where id = '"+item.getId()+"';");
        } catch (SQLException ex) {
            Logger.getLogger(Modules.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static HashMap<String, Modules> select(String conditions)
    {
        if(conditions.isEmpty())conditions = "1";
            Connection conn=MySqlDBHelper.getInstance().getConnection();
            Statement st = null;
            ResultSet rs = null;
            try { 
                st = conn.createStatement();
//                rs = st.executeQuery("SELECT VERSION()");
                rs = st.executeQuery("SELECT * from "+tablename+" where "+conditions);

                HashMap<String, Modules> items=new HashMap<String, Modules>();
                while (rs.next()) {
                    items.put(rs.getString("id"), new Modules(rs));
                }
                return items;
            } catch (SQLException ex) {
                Logger.getLogger(Modules.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                return null;
            }

    }
    //-----------database helper functions--------------
    public static String implodeValues(Modules item,boolean withId)
    {
            ArrayList<String> values=item.implodeFieldValuesHelper(withId);
            String output="";
            for(String value:values)
            {
                    if(!output.isEmpty())
                            output+=",";
                    output+="'"+value+"'";
            }
            return output;
    }
    public static String implodeFields(boolean withId)
    {
            String output="";
            for(String field:fields)
            {
                    if(!withId && field.contentEquals("id"))continue;
                    if(!output.isEmpty())
                            output+=",";
                    output+=field;
            }
            return output;
    }
    public static String implodeFieldsWithValues(Modules item,boolean withId)
    {
            ArrayList<String> values=item.implodeFieldValuesHelper(true);//get entire list of values; whether the id is included will be dealt with later.

            if(values.size()!=fields.length)
            {
                    System.err.println("Modules:implodeFieldsWithValues(): ERROR: values length does not match fields length");
            }

            String output="";
            for(int i=0;i<fields.length;i++)
            {
                    if(!withId && fields[i].contentEquals("id"))continue;
                    if(!output.isEmpty())
                            output+=",";
                    output+=fields[i]+"='"+values.get(i)+"'";
            }
            return output;
    }	
    public static String implodeFieldsWithTypes()
    {
            String output="";
            for(int i=0;i<fields.length;i++)
            {
                    if(fields[i].contentEquals(fields[0]))//fields[0] being the primary key
                            output+=fields[i]+" "+fieldtypes[i]+" PRIMARY KEY";
                    else
                            output+=","+fields[i]+" "+fieldtypes[i];
            }
            return output;
    }	
    public static String createTable()
    {
            return "CREATE TABLE IF NOT EXISTS "+tablename+" ("+implodeFieldsWithTypes()+" );";
    }
    public static String deleteTable()
    {
            return "DROP TABLE IF EXISTS "+tablename;
    }
}
