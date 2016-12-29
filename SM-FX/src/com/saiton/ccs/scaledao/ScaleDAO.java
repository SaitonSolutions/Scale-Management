package com.saiton.ccs.scaledao;

import com.saiton.ccs.database.Starter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.Codec;
import org.owasp.esapi.codecs.OracleCodec;

public class ScaleDAO {

    public static Starter star;//db connection
    Codec ORACLE_CODEC = new OracleCodec();
    private final Logger log = Logger.getLogger(this.getClass());

    public String generateID() {

        Integer id = null;
        String cid = null;
        String final_id = null;
        if (star.con == null) {
            log.error("Databse connection failiure.");
            return null;
        } else {
            try {

                Statement st = star.con.createStatement();
                Statement ste = star.con.createStatement();
                ResultSet rs = st.executeQuery(
                        "SELECT MAX(id) as ID FROM scale");

                while (rs.next()) {
                    id = rs.getInt("id");
                }
                ResultSet rss = ste.executeQuery(
                        "SELECT weight_scale_id FROM scale WHERE id= " + id
                        + "");

                while (rss.next()) {
                    cid = rss.getString("weight_scale_id");
                }

                if (id != 0) {
                    String original = cid.split("D")[1];
                    int i = Integer.parseInt(original) + 1;

                    if (i < 10) {
                        final_id = "WID000" + i;
                    } else if (i >= 10 && i < 100) {
                        final_id = "WID00" + i;
                    } else if (i >= 100 && i < 1000) {
                        final_id = "WID0" + i;
                    } else if (i >= 1000 && i < 10000) {
                        final_id = "WID" + i;
                    }
                    return final_id;

                } else {
                    return "WID0001";
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException |
                    SQLException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {
                    log.error("Exception tag --> " + "Split character error");
                } else if (e instanceof NumberFormatException) {
                    log.error("Exception tag --> "
                            + "Invalid number found in current id");
                } else if (e instanceof SQLException) {
                    log.error("Exception tag --> " + "Invalid sql statement "
                            + e.getMessage());
                }
                return null;

            } catch (Exception e) {
                log.error("Exception tag --> " + "Error");
                return null;
            }
        }
    }

    public Boolean insertScale(
            String scaleId,
            String scaleName,
            String comPort,
            String buardrate
            ) {
        String encodedScaleId = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                scaleId);
        String encodedScalename = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                scaleName);
        String encodedcomPort = ESAPI.encoder().
                encodeForSQL(ORACLE_CODEC, comPort);
        String encodedBuardrate = ESAPI.encoder().encodeForSQL(
                ORACLE_CODEC, buardrate);

       
        if (star.con == null) {
            log.error("Databse connection failiure.");
            return false;
        } else {
            try {

                PreparedStatement ps = star.con.prepareStatement(
                        "INSERT INTO scale_register("
                                + "`scale_id`,"
                                + "`scale_name`,"
                                + "`com_port`,"
                                + "`board_rate`"
                                + " ) "
                                + "VALUES(?,?,?,?)");

                ps.setString(1, encodedScaleId);
                ps.setString(2, encodedScalename);
                ps.setString(3, encodedcomPort);
                ps.setString(4, encodedBuardrate);
              
                int val = ps.executeUpdate();

                if (val == 1) {
                    return true;
                } else {
                    return false;
                }

            } catch (SQLException e) {

                if (e instanceof SQLException) {
                    log.error("Exception tag --> " + "Invalid sql statement "
                            + e.getMessage());
                }
                return false;

            } catch (Exception e) {
                log.error("Exception tag --> " + "Error");
                return false;
            }
        }
    }

    public ArrayList<ArrayList<String>> searchCustomerDetailsDetails(
            String searchTerm) {

        String encodedSearchTerm = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                searchTerm);

        String customerCode = null;
        String customerName = null;

        ArrayList<ArrayList<String>> mainList
                = new ArrayList<ArrayList<String>>();

        if (star.con == null) {

            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return null;

        } else {
            try {

                String query = "SELECT * "
                        + "From customer "
                        + "Where customer_code LIKE ? or customer_name LIKE ? ";
                        

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encodedSearchTerm + "%");
                pstmt.setString(2, encodedSearchTerm + "%");
                ResultSet r = pstmt.executeQuery();

                while (r.next()) {

                    ArrayList<String> list = new ArrayList<String>();

                    customerCode = r.getString("customer_code");
                    customerName = r.getString("customer_name");
                    

                    list.add(customerCode);
                    list.add(customerName);

                    mainList.add(list);

                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {

                    log.error(
                            "Exception tag --> "
                            + "Invalid entry location for list");

                } else if (e instanceof SQLException) {

                    log.error("Exception tag --> " + "Invalid sql statement");

                } else if (e instanceof NullPointerException) {

                    log.error("Exception tag --> " + "Empty entry for list");

                }
                return null;
            } catch (Exception e) {

                log.error("Exception tag --> " + "Error");

                return null;
            }
        }
        return mainList;
    }
    
      public ArrayList<ArrayList<String>> searchSizeDetailsDetails(
            String searchTerm) {

        String encodedSearchTerm = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                searchTerm);

        String size = null;
        

        ArrayList<ArrayList<String>> mainList
                = new ArrayList<ArrayList<String>>();

        if (star.con == null) {

            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return null;

        } else {
            try {

                String query = "SELECT * "
                        + "From size "
                        + "Where size LIKE ?  ";
                        

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encodedSearchTerm + "%");
                
                ResultSet r = pstmt.executeQuery();

                while (r.next()) {

                    ArrayList<String> list = new ArrayList<String>();

                    size = r.getString("size");
                    
                    

                    list.add(size);
                    

                    mainList.add(list);

                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {

                    log.error(
                            "Exception tag --> "
                            + "Invalid entry location for list");

                } else if (e instanceof SQLException) {

                    log.error("Exception tag --> " + "Invalid sql statement");

                } else if (e instanceof NullPointerException) {

                    log.error("Exception tag --> " + "Empty entry for list");

                }
                return null;
            } catch (Exception e) {

                log.error("Exception tag --> " + "Error");

                return null;
            }
        }
        return mainList;
    }
  
    
}
